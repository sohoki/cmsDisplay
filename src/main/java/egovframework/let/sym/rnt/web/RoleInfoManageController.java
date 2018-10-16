package egovframework.let.sym.rnt.web;



import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.Globals;
import egovframework.let.sym.rnt.service.RoleInfoVO;
import egovframework.let.sym.rnt.service.RoleInfo;
import egovframework.let.sym.rnt.service.RoleInfoManageService;





import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller 
public class RoleInfoManageController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleInfoManageController.class);

	@Resource(name = "RoleInfoManageService")
	private RoleInfoManageService roleInfoManageService;
	
	
	@Resource(name="egovMessageSource")
	protected EgovMessageSource egovMessageSource;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;
	
	

	@RequestMapping (value="/backoffice/sub/basicManage/roleList.do")
	public String selectManuInfoManageListByPagination(@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("searchVO") RoleInfoVO searchVO
			, HttpServletRequest request
			, BindingResult bindingResult
			, ModelMap model) throws Exception {
		
		
		     if(  searchVO.getPageUnit() > 0  ){    	   
			    	   searchVO.setPageUnit(searchVO.getPageUnit());
		     }else {
			   searchVO.setPageUnit(propertiesService.getInt("pageUnit"));   
		     }
		     searchVO.setPageSize(propertiesService.getInt("pageSize"));
		       
		       
		       /** pageing */       
		   	  PaginationInfo paginationInfo = new PaginationInfo();
			  paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			  paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
			  paginationInfo.setPageSize(searchVO.getPageSize());

			  searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			  searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			  //searchVO.setLastIndex(paginationInfo.getFirstRecordIndex() + searchVO.getPageSize());
			  searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		       model.addAttribute("resultList",   roleInfoManageService.selectRoleInfoManageListByPagination(searchVO) );		       
		       model.addAttribute("regist",   searchVO );

		       int totCnt = roleInfoManageService.selectRoleIInfoManageListTotCnt_S(searchVO) ;      
			   paginationInfo.setTotalRecordCount(totCnt);
		       model.addAttribute("paginationInfo", paginationInfo);
		       model.addAttribute("totalCnt", totCnt);
			
			   return "/backoffice/sub/basicManage/roleList";	
	
	}
	
	@RequestMapping(value="/backoffice/sub/basicManage/roleDetail.do")
	public String selectMenurInfoManageDetail(@ModelAttribute("loginVO") LoginVO loginVO
			,  RoleInfoVO vo
			, HttpServletRequest request
			, BindingResult bindingResult
			, ModelMap model)throws Exception {
		
		model.addAttribute("regist", vo);
		//권한 설정
		model.addAttribute("author", roleInfoManageService.selectRoleAuthInfoManageCombo());
		
		if (!vo.getMode().equals("Ins")){
	     	model.addAttribute("regist", roleInfoManageService.selectRoleIrInfoManageDetail(vo.getRoleCode())    );	     		     
		}		
		return "/backoffice/sub/basicManage/roleDetail";
	}
	@SuppressWarnings("finally")
	@RequestMapping(value="/backoffice/sub/basicManage/roleDelete.do")
	public String  deleteRoleInfoManage(@ModelAttribute("loginVO") LoginVO loginVO,
			RoleInfoVO vo
			, HttpServletRequest request
			, BindingResult bindingResult
			, ModelMap model)throws Exception{
		
		model.addAttribute("regist",vo );
		
		if (StringUtils.equals(vo.getRoleCode(), Globals.REGITER_SYSTEM)){
			model.addAttribute("status", Globals.STATUS_FAIL);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.delete.system"));			
			return "redirect:/backoffice/sub/basicManage/roleList.do";
		}
				
		try{
		      int ret = 	roleInfoManageService.deleteRoleIInfoManage(vo.getRoleCode());		      
		      if (ret > 0 ) {		    	  
		    	  model.addAttribute("status", Globals.STATUS_SUCCESS);
		    	  model.addAttribute("message", egovMessageSource.getMessage("success.common.delete") );		    	  
		      }else {
		    	  throw new Exception();		    	  
		      }
		}catch (Exception e){
			model.addAttribute("status", Globals.STATUS_FAIL);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.delete"));			
		}		
		finally{
			return "redirect:/backoffice/sub/basicManage/roleList.do";	
		}
		
	}
	
	@SuppressWarnings("finally")
	@RequestMapping (value="/backoffice/sub/basicManage/roleUpdate.do")	                                           
	public String updateCenterInfoManage(			
			@ModelAttribute("loginVO") LoginVO loginVO,
			@ModelAttribute("RoleInfoVO") RoleInfoVO vo
			, HttpServletRequest request
			, BindingResult bindingResult
			, ModelMap model) throws Exception{
		
		model.addAttribute("regist", vo);
		String meesage = "";
		String url = "/backoffice/sub/basicManage/roleList";
		
		
     	
		try{
			
			int ret  = 0;
			if (vo.getMode().equals("Ins")){
				ret = roleInfoManageService.insertRoleIInfoManage(vo) ;
				meesage = "sucess.common.insert";
				url = "/backoffice/sub/basicManage/roleDetail";				
			}else {
				 ret = roleInfoManageService.updateRoleIInfoManage(vo);
				 meesage = "sucess.common.update";
				 url = "/backoffice/sub/basicManage/roleDetail";
			}			
			if (ret >0){
				model.addAttribute("status", Globals.STATUS_SUCCESS);
				model.addAttribute("message", egovMessageSource.getMessage(meesage));
						
			}else {				
				throw new Exception();
			}			
		}catch (Exception e){
			model.addAttribute("status", Globals.STATUS_FAIL);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.insert"));			
			System.out.println("Exception:" + e.toString());
			url = "/backoffice/sub/basicManage/roleDetail";
		}finally {
			return url;	
		}					
		
	}
	
	
	
	
	
	
	
	
}
