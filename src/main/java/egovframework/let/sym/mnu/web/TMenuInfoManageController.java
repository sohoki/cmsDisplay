package egovframework.let.sym.mnu.web;


import java.util.List;

import javax.annotation.Resource;


import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.Globals;
import egovframework.let.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;
import egovframework.let.sym.cnt.service.CenterInfoManageService;
import egovframework.let.sym.mnu.service.MenuInfoVO;
import egovframework.let.sym.mnu.service.TMenuInfoVO;
import egovframework.let.sym.rnt.service.RoleInfoManageService;
import egovframework.let.sym.mnu.service.TMenuInfoManageService;

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
public class TMenuInfoManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TMenuInfoManageController.class);

	@Resource(name = "TMenuInfoManageService")
	private TMenuInfoManageService tmenuInfoManageService;
	
	@Resource(name = "RoleInfoManageService")
	private RoleInfoManageService roleInfoManageService;
	
	
	
	@Resource(name="CmmnDetailCodeManageService")
	private EgovCcmCmmnDetailCodeManageService cmmnDetailCodeService;
	
	@Resource(name="CenterInfoManageService")
	private CenterInfoManageService centerInfoService;
	
	@Resource(name="egovMessageSource")
	protected EgovMessageSource egovMessageSource;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;
	
	@RequestMapping (value="/backoffice/sub/basicManage/tmenuList.do")
	public String selectManuInfoManageListByPagination(@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("searchVO") TMenuInfoVO searchVO
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

		       model.addAttribute("resultList",   tmenuInfoManageService.selectManuInfoManageListByPagination(searchVO) );
		       model.addAttribute("regist",   searchVO  );
		       int totCnt = tmenuInfoManageService.selectMenuInfoManageListTotCnt_S(searchVO) ;       
			   paginationInfo.setTotalRecordCount(totCnt);
		       model.addAttribute("paginationInfo", paginationInfo);
		       model.addAttribute("totalCnt", totCnt);
			
			   return "/backoffice/sub/basicManage/tmenuList";	
	
	}
	@RequestMapping(value="/backoffice/sub/basicManage/tmenuCombo.do")
	public ModelAndView menuCombo( HttpServletRequest request) throws Exception{
		
		String menuType = request.getParameter("menuType") != null ? request.getParameter("menuType") : "";
		String didId = request.getParameter("didId") != null ? request.getParameter("didId") : "";
		MenuInfoVO  vo = new MenuInfoVO();
		vo.setMenuType(menuType);
		ModelAndView model = new ModelAndView("jsonView");
		
		model.addObject("selectMenuGroup", tmenuInfoManageService.selectMenuInfoManageCombo());
		
		if ( ! menuType.equals("MENU_TYPE01") ){
			model.addObject("selectDidGroup", tmenuInfoManageService.selectTMenuComboLst(didId));
		}
		return model;
		
	}
	
	@RequestMapping(value="/backoffice/sub/basicManage/tmenuDetail.do")
	public String selectMenurInfoManageDetail(@ModelAttribute("loginVO") LoginVO loginVO
			,  TMenuInfoVO vo
			, HttpServletRequest request
			, BindingResult bindingResult
			, ModelMap model)throws Exception {
		
		model.addAttribute("selectMenuGroup", tmenuInfoManageService.selectMenuInfoManageCombo());		 		
		model.addAttribute("selectRoleGroup", roleInfoManageService.selectRoleIInfoManageCombo());
		
		model.addAttribute("selectMenuType", cmmnDetailCodeService.selectCmmnDetailCombo("EMT007"));		
		model.addAttribute("selectModelType", cmmnDetailCodeService.selectCmmnDetailCombo("EMT004"));
		
		
		
		model.addAttribute("regist", vo);
		
		System.out.println( "tmenuId:" + vo.getMenuId()  );
		
		if (!vo.getMode().equals("Ins")){
			vo = (TMenuInfoVO) tmenuInfoManageService.selectMenurInfoManageDetail(  vo.getMenuId());
			
	     	model.addAttribute("regist", vo );
	     	
	     	if (vo.getDidId() != null){
	     		model.addAttribute("selectDidGroup", tmenuInfoManageService.selectTMenuComboLst(vo.getDidId()));	
	     	}else {
	     		model.addAttribute("selectDidGroup", tmenuInfoManageService.selectTMenuComboLst(""));	
	     	}	     	
		}else {
			model.addAttribute("selectDidGroup", tmenuInfoManageService.selectTMenuComboLst(""));
		}
		return "/backoffice/sub/basicManage/tmenuDetail";
	}
	
	@RequestMapping(value="/backoffice/sub/basicManage/tmenuDeletel.do")
	public String  deleteCenterInfoManage(@ModelAttribute("loginVO") LoginVO loginVO,
			TMenuInfoVO vo
			, HttpServletRequest request
			, BindingResult bindingResult
			, ModelMap model)throws Exception{
		
		model.addAttribute("regist",vo );
		
		if (StringUtils.equals(vo.getMenuId(), Globals.REGITER_SYSTEM)){
			model.addAttribute("status", Globals.STATUS_FAIL);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.delete.system"));			
			return "redirect:/backoffice/sub/basicManage/tmenuList.do";
		}
				
		try{
		      int ret = 	tmenuInfoManageService.deleteMenuInfoManage(vo.getMenuId());		      
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
		
		return "redirect:/backoffice/sub/basicManage/tmenuList.do";
	}
	

	@RequestMapping (value="/backoffice/sub/basicManage/tmenuUpdate.do")	                                           
	public String updateCenterInfoManage(
			@ModelAttribute("loginVO") LoginVO loginVO,
			@ModelAttribute("TMenuInfoVO") TMenuInfoVO vo	
			, HttpServletRequest request
			, BindingResult bindingResult
			, ModelMap model) throws Exception{
		
		model.addAttribute("regist", vo);
		String meesage = "";
		String url = "/backoffice/sub/basicManage/tmenuList";
		
     	
		try{
			
		
			int ret  = 0;
			if (vo.getMode().equals("Ins")){
				ret = tmenuInfoManageService.insertMenuInfoManage(vo);
				meesage = "sucess.common.insert";
				url = "/backoffice/sub/basicManage/tmenuDetail";				
			}else {
				 ret = tmenuInfoManageService.updateMenuInfoManage(vo);;
				 meesage = "sucess.common.update";
				 url = "/backoffice/sub/basicManage/tmenuDetail";
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
			url = "/backoffice/sub/basicManage/tmenuDetail";
		}finally {
			return url;	
		}					
		
	}
	
	
}
