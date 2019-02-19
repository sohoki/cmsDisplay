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
	
	
	
	
	// 2019-02-11 create
	@RequestMapping ("/backoffice/sub/equiManage/groupListInfo.do")
	@ResponseBody
	public ModelAndView selectGroupListInfo(HttpServletRequest request) throws Exception {
			      
		ModelAndView model = new ModelAndView("jsonView");
		GroupInfoVO searchVO = new GroupInfoVO();

		String firstIndex = request.getParameter("firstIndex") == null ? "0" : request.getParameter("firstIndex");
		String lastIndex = request.getParameter("lastIndex") == null ? "0" : request.getParameter("lastIndex");
		String recordCountPerPage = request.getParameter("recordCountPerPage") == null ? "10" : request.getParameter("recordCountPerPage");
		
		String searchCondition = request.getParameter("searchCondition") == null ? "" : request.getParameter("searchCondition");
		String searchKeyword = request.getParameter("searchKeyword") == null ? "" : request.getParameter("searchKeyword");
		
		
		searchVO.setFirstIndex(Integer.parseInt(firstIndex));
		searchVO.setLastIndex(Integer.parseInt(lastIndex));
		searchVO.setRecordCountPerPage(Integer.parseInt(recordCountPerPage));
		
		searchVO.setSearchCondition(searchCondition);
		searchVO.setSearchKeyword(searchKeyword);
		
		model.addObject("resultList", didgroupInfoManageService.selectGroupInfoManageListByPagination(searchVO));
		
		return model;
	}
	
	
	
	@RequestMapping(value="/backoffice/sub/equiManage/modifyGroupInfo.do")
	@ResponseBody
	public String modifyGroupInfo( HttpServletRequest request) throws Exception{
		
		String result = "";
		
		GroupInfo groupInfo = new GroupInfo();
        
		
		String work = request.getParameter("work") == null ? "" : request.getParameter("work");
		String groupNm = request.getParameter("new_group_nm") == null ? "" : request.getParameter("new_group_nm");
        String groupCode = request.getParameter("new_group_code") == null ? "" : request.getParameter("new_group_code");
        try{
        	if(work != null && !work.equals("")){
        		int ret  = 0;
        		if(groupNm != null && !groupNm.equals("")){
        			groupInfo.setGroupNm(groupNm);
        			groupInfo.setGroupCode(groupCode);
        			if (work.equals("INSERT")){
        				groupCode = didgroupInfoManageService.selectLastInsertGroup();
        				groupInfo.setGroupCode(groupCode);
        				ret = didgroupInfoManageService.insertGroupInfoManage(groupInfo)  ;		
        			}else if(work.equals("UPDATE")) {
        				// 1. UPDATE의 경우에는  groupCode와  groupNm 둘 다 필수
        				 // ret = didgroupInfoManageService.updateGroupInfoManage(groupInfo);
        			}			
        			if (ret > 0){
        				// 성공
        				result = work+"|SUCCESS|"+groupCode;
        			}else {
        				// 실패
        				result = work+"|FAIL"+groupCode;
        			}	
        		} else {
        			// 그룹명 입력되지 않음
        			result = work+"|GROUPNM|EMPTY";
        		}
        	} else {
        		// 요청작업 명시되지 않음
        		result = "WORK|EMPTY";
        	}
		}catch (Exception e){
			result = "ERROR";
			e.printStackTrace();
		}
        
        return result;
		
	}
	
	@RequestMapping ("/backoffice/sub/equiManage/insertGroupInDid.do")
	@ResponseBody
	public String insertGroupInDid(HttpServletRequest request)throws Exception{
		String result = "";
		try{
			
			GroupDidInfoVO vo = new GroupDidInfoVO();
			String groupCode = request.getParameter("groupCode") == null ? "" : request.getParameter("groupCode");
			String addDeviceId = request.getParameter("addDeviceId") == null ? "" : request.getParameter("addDeviceId");
	        
			String[] addDevice = addDeviceId.split(",");
			vo.setGroupCode(groupCode);
			for(int i = 0; i < addDevice.length; i++){
				System.out.println("추가하고자 하는 DEVICE ID : " + addDevice[i]);
				vo.setDidId(addDevice[i]);
				if(i != 0){
					result += ",";
				}
				if(groupDidInfoManageService.insertGroupInfoManage(vo) > 0){
					result += addDevice[i]+"|SUCCESS";
				} else {
					result += addDevice[i]+"|FAIL";
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}				
		return result;
	}
	
	
}
