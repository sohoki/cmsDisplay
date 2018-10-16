package egovframework.let.sym.grp.web;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.List;



import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.Globals;
import egovframework.let.sym.did.service.DidInfoVO;
import egovframework.let.sym.grp.service.GroupInfo;
import egovframework.let.sym.grp.service.GroupInfoVO;
import egovframework.let.sym.grp.service.GroupInfoManageService;
import egovframework.let.sym.grp.service.GroupDidInfo;
import egovframework.let.sym.grp.service.GroupDidInfoVO;
import egovframework.let.sym.grp.service.GroupDidInfoManageService;






import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class GroupInfoManageController {

	
     private static final Logger LOGGER = LoggerFactory.getLogger(GroupInfoManageController.class);

	
     @Resource(name="GroupInfoManageService")        
     private GroupInfoManageService didgroupInfoManageService;
     
     @Resource(name="GroupDidInfoManageService")
     private GroupDidInfoManageService groupDidInfoManageService;
	
	@Resource(name="egovMessageSource")
	protected EgovMessageSource egovMessageSource;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;
	
	
	@RequestMapping ("/backoffice/sub/equiManage/did_groupList.do")
	public String selectGroupDidInfoManageListByPagination(@ModelAttribute("loginVO") LoginVO loginVO
															, @ModelAttribute("searchVO") GroupInfoVO searchVO
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
		  searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

	       model.addAttribute("resultList",   didgroupInfoManageService.selectGroupInfoManageListByPagination(searchVO) );

	       int totCnt = didgroupInfoManageService.selectGroupInfoManageListTotCnt_S(searchVO) ;       
		   paginationInfo.setTotalRecordCount(totCnt);
	       model.addAttribute("paginationInfo", paginationInfo);
	       model.addAttribute("totalCnt", totCnt);
	       
	       model.addAttribute("regist", searchVO);
	       
	       
	      return "/backoffice/sub/equiManage/did_groupList";
	}
	// groupCode 체크 
	@RequestMapping("/backoffice/sub/equiManage/IdCheck.do")
	@ResponseBody 
	public String selectIdCheck(HttpServletRequest request){
		
		String groupCode = request.getParameter("groupCode") != null ? request.getParameter("groupCode") : "";
		
		int idCheck = didgroupInfoManageService.selectGroupIDInfoManageListTotCnt_S(groupCode);		
		return Integer.toString(idCheck);
	}
	//ajax 로 값 보내기 
	@RequestMapping ("/backoffice/sub/equiManage/groupDetail.do")
	@ResponseBody 
	public String selectGroupDetail(HttpServletRequest request){
	    //	    
	    String groupCode = request.getParameter("groupCode") != null ? request.getParameter("groupCode") : "";
	    
	    GroupInfo  groupInfo  =	didgroupInfoManageService.selectGroupInfoManageDetail(groupCode);
        String grInfo =   groupInfo.getGroupNm()+"/"+groupInfo.getGroupUseYn();
	    
	 	return grInfo;
	}
	
	@RequestMapping("/backoffice/sub/equiManage/DidgroupLst.do")
	public ModelAndView selectDidLst(HttpServletRequest request) throws Exception
	{
		ModelAndView model = new 	ModelAndView("jsonView");
		String groupCode = request.getParameter("groupCode") != null ? request.getParameter("groupCode") : "";
		List<GroupDidInfoVO> groupDidTb = groupDidInfoManageService.selectGroupInfoManageListByPagination(groupCode);
		model.addObject("didLst", groupDidTb);
		
		
		List<GroupDidInfo> groupCmbLst = groupDidInfoManageService.selectComboLst();
		model.addObject("didCmbLst", groupCmbLst);
		
		return model;
	}	
	
	
	@RequestMapping("/backoffice/sub/equiManage/DidgroupDel.do")
	public ModelAndView selectDidDelLst(HttpServletRequest request) throws Exception
	{
		ModelAndView model = new 	ModelAndView("jsonView");
		String groupCode = request.getParameter("groupCode") != null ? request.getParameter("groupCode") : "";
		String didId = request.getParameter("didId") != null ? request.getParameter("didId") : "";
		
		int rnt = groupDidInfoManageService.deleteGroupInfoManage(didId);
		
		List<GroupDidInfoVO> groupDidTb = groupDidInfoManageService.selectGroupInfoManageListByPagination(groupCode);
		model.addObject("didLst", groupDidTb);
		return model;
	}
	
	//select Combo 리스트 보여주기 
	@RequestMapping("/backoffice/sub/equiManage/DidcomboLst.do")
	public ModelAndView selectDidComboLst(HttpServletRequest request) throws Exception
	{
		ModelAndView model = new 	ModelAndView("jsonView");		
		List<GroupDidInfo> groupCmbLst = groupDidInfoManageService.selectComboLst();
		model.addObject("didCmbLst", groupCmbLst);
		return model;
	}	
	@RequestMapping ("/backoffice/sub/equiManage/DidgroupInsret.do")
	public String insertGroupDid(@ModelAttribute("loginVO") LoginVO loginVO,
			GroupDidInfo vo,
			ModelMap model)throws Exception{
	
		
		try{
			
		      int ret = 	groupDidInfoManageService.insertGroupInfoManage(vo);		      
		      if (ret > 0 ) {		    	  
		    	  model.addAttribute("status", "didInsertOK");
		    	  model.addAttribute("groupCode", vo.getGroupCode() );
		      }else {		    	  
		    	  throw new Exception();		    	  
		      }
		}catch (Exception e){
			model.addAttribute("status", "didInsertFalse");
			model.addAttribute("groupCode", vo.getGroupCode() );
		}				
		return "forward:/backoffice/sub/equiManage/did_groupList.do";
		
	}
	
	@RequestMapping("/backoffice/sub/equiManage/didgroupDelete.do")
	public String deleteGroupInfoManage(@ModelAttribute("loginVO") LoginVO loginVO,
			GroupInfo vo,
			ModelMap model)throws Exception{
		
		model.addAttribute("regist",vo );
		
		if (StringUtils.equals(vo.getGroupCode(), Globals.REGITER_SYSTEM)){
			model.addAttribute("status", Globals.STATUS_FAIL);
			//model.addAttribute("message", egovMessageSource.getMessage("fail.common.delete.system"));			
			return "forward:/backoffice/sub/equiManage/didList.do";
		}
				
		try{
			
		      int ret = 	didgroupInfoManageService.deleteGroupInfoManage(vo.getGroupCode());		      
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
		return "forward:/backoffice/sub/equiManage/did_groupList.do";
	}
	
	@RequestMapping(value="/backoffice/sub/equiManage/didgroupUpdate.do")
	public String updateGroupManager(@ModelAttribute("loginVO") LoginVO loginVO
			, GroupInfo vo
			, ModelMap model) throws Exception {
		
		model.addAttribute("regist", vo);
		String meesage = "";
		String url = "redirect:/backoffice/sub/equiManage/did_groupList.do";
		     
		try{			
			int ret  = 0;
			if (vo.getMode().equals("Ins")){
				
				ret = didgroupInfoManageService.insertGroupInfoManage(vo)  ;
				meesage = "sucess.common.insert";
				url = "redirect:/backoffice/sub/equiManage/did_groupList.do";				
			}else {
				 ret = didgroupInfoManageService.updateGroupInfoManage(vo);
				 meesage = "sucess.common.update";
				 url = "redirect:/backoffice/sub/equiManage/did_groupList.do";
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
			url = "redirect:/backoffice/sub/equiManage/did_groupList.do";
		}finally {
			
		}					
		return url;
		
	
	}
	
	
	
	@RequestMapping(value="/backoffice/sub/equiManage/groupSearch.do")
	public ModelAndView selectDidGroupSearchList( HttpServletRequest request) throws Exception{
		ModelAndView model = new ModelAndView("jsonView");
        GroupInfoVO groupInfoVO = new GroupInfoVO();
        String grpSearchKeyword = request.getParameter("grpSearchKeyword") == null ? "" : request.getParameter("grpSearchKeyword");
        groupInfoVO.setSearchKeyword(grpSearchKeyword);
        return model.addObject("resultMap", didgroupInfoManageService.selectGroupInfoManageCombo(groupInfoVO));
		
	}
	
	
}
