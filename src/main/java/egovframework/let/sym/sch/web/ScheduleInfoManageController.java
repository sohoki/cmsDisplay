package egovframework.let.sym.sch.web;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.Globals;



import egovframework.let.sym.sch.service.ScheduleInfo;
import egovframework.let.sym.sch.service.ScheduleInfoVO;
import egovframework.let.sym.sch.service.ScheduleInfoManageService;
import egovframework.let.sym.sch.service.ContentSendHistoryInfoManagerService;




import egovframework.let.sym.grp.service.GroupDidInfoVO;
import egovframework.let.sym.grp.service.GroupInfoManageService;
import egovframework.let.sym.grp.service.GroupDidInfoManageService;
import egovframework.let.sym.grp.service.GroupInfoVO;
import egovframework.let.sym.sch.service.ContentSendHistoryInfo;




import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.let.uat.uia.service.EgovUserManagerService;
import egovframework.let.sts.brd.service.BrodScheduleInfoVO;
import egovframework.let.sts.cnt.service.ContentInfoManageService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller 
public class ScheduleInfoManageController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleInfoManageController.class);

	
	
	@Resource(name="GroupDidInfoManageService")
	private GroupDidInfoManageService didInfo;
	
	@Resource(name="ContentSendHistoryInfoManagerService")
	private ContentSendHistoryInfoManagerService SendHistory; 
	
	@Resource(name="ScheduleInfoManageService")
	private ScheduleInfoManageService scheduleInfoManageService;
	
	@Resource(name="GroupInfoManageService")
	private GroupInfoManageService groupInfoManageService;
	
	@Resource(name="ContentInfoManageService")
	private ContentInfoManageService conManageService;
	
	@Resource(name="egovMessageSource")
	protected EgovMessageSource egovMessageSource;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;
	
	
	@RequestMapping("/backoffice/sub/equiManage/schList.do")
    public String selectScheduleInfoManageListByPagination(@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("searchVO") ScheduleInfoVO searchVO
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

	       model.addAttribute("resultList",   scheduleInfoManageService.selectScheduleInfoManageListByPagination(searchVO)  );
	       int totCnt = scheduleInfoManageService.selectScheduleInfoManageListTotCnt_S(searchVO);
		   paginationInfo.setTotalRecordCount(totCnt);
	       model.addAttribute("paginationInfo", paginationInfo);
	       model.addAttribute("totalCnt", totCnt);
		
		   return "/backoffice/sub/equiManage/schList";			
	}
	//센터 정보 상세
	@RequestMapping (value="/backoffice/sub/equiManage/schDetail.do")
	public String selectScheduleInfoManageDetail(@ModelAttribute("loginVO") LoginVO loginVO
			                                                          , @ModelAttribute("ScheduleInfoVO") ScheduleInfoVO vo
			                                                          , HttpServletRequest request
			                                           				  , BindingResult bindingResult
																	  , ModelMap model ) throws Exception{	
		//group 명
        GroupInfoVO groupInfoVO = new GroupInfoVO();
        String grpSearchKeyword = request.getParameter("grpSearchKeyword") == null ? "" : request.getParameter("grpSearchKeyword");
        groupInfoVO.setSearchKeyword(grpSearchKeyword);
		model.addAttribute("selectGroup", groupInfoManageService.selectGroupInfoManageCombo(groupInfoVO));		
		//콘텐츠 번호
		model.addAttribute("selectContent", conManageService.selectNextCombo("0") );
		
		
		model.addAttribute("regist", vo);
		if (!vo.getMode().equals("Ins")){
	     	model.addAttribute("regist", scheduleInfoManageService.selectScheduleInfoManageDetail(vo.getSchCode()))	  ;   		     
		}
		
		return "/backoffice/sub/equiManage/schDetail";
	}
	@RequestMapping("/backoffice/sub/equiManage/schDetailSearch.do")
	public ModelAndView selectScheduleInfoManageDetail(HttpServletRequest request) throws Exception{
		
		 
		 String searchKeyword =  request.getParameter("searchKeyword") != null ? request.getParameter("searchKeyword") : "";
		 	 
		 ModelAndView model = new ModelAndView("jsonView");		 
		 return model.addObject("schList", conManageService.selectSearcHCombo(searchKeyword));
	}
	@RequestMapping (value="/backoffice/sub/equiManage/schView.do")
	public String selectScheduleInfoManageView(@ModelAttribute("loginVO") LoginVO loginVO
			                                                           ,  ScheduleInfoVO vo
			                                                           , HttpServletRequest request
			                                           				   , BindingResult bindingResult
																	   , ModelMap model ) throws Exception{	
		model.addAttribute("regist", vo);
		if (!vo.getMode().equals("Ins")){
	     	model.addAttribute("regist", scheduleInfoManageService.selectScheduleInfoManageDetailView(vo.getSchCode()));   		     
		}
		
		return "/backoffice/sub/equiManage/schView";
	}	
	
	@RequestMapping (value="/backoffice/sub/equiManage/schDelete.do")
	public String deleteScheduleInfoManage(@ModelAttribute("loginVO") LoginVO loginVO,
			ScheduleInfo vo
			, HttpServletRequest request
			, BindingResult bindingResult
			,ModelMap model)throws Exception{
		
		model.addAttribute("detail",vo );
		
		if (StringUtils.equals(vo.getSchCode(), Globals.REGITER_SYSTEM)){
			model.addAttribute("status", Globals.STATUS_FAIL);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.delete.system"));			
			return "forward:/backoffice/sub/equiManage/centerList.do";
		}
				
		try{
		      int ret = 	scheduleInfoManageService.deleteScheduleInfoManage(vo.getSchCode());		      
		      if (ret > 0 ) {		    	  
		    	  model.addAttribute("status", Globals.STATUS_SUCCESS);
		    	  model.addAttribute("message", egovMessageSource.getMessage("success.common.delete") );		    	  
		    	  int ret_res = SendHistory.deleteContentSendHistoryInfoManage(vo.getSchCode());		    	  
		      }else {
		    	  throw new Exception();		    	  
		      }
		}catch (Exception e){
			model.addAttribute("status", Globals.STATUS_FAIL);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.delete"));			
		}		
		
		return "forward:/backoffice/sub/equiManage/schList.do";
	}
	//체크 박스 삭제 
	@RequestMapping(value="/backoffice/sub/equiManage/delSchedule.do")
	@ResponseBody 
	public String delSchedule(HttpServletRequest request) throws Exception{
	   	
		String sch_code = request.getParameter("sch_code") != null ? request.getParameter("sch_code") : "";
		String[] inputParamArrays ;
		String returnValue = "F";
		inputParamArrays = sch_code.split(",");
		
		for (int i =0;  i < inputParamArrays.length; i++ ){
			int ret = 	scheduleInfoManageService.deleteScheduleInfoManage(inputParamArrays[i]);
			if(ret > 0){
				int ret_res = SendHistory.deleteContentSendHistoryInfoManage(inputParamArrays[i]);
			}
			//단말단에 나간 기록 삭제
			
						
            if (ret == 0){
            	returnValue = "F";
            	break ;
            }			
            else{
            	returnValue = "T";    	
            }
		}
				
		return returnValue;
	}
    
	@RequestMapping (value="/backoffice/sub/equiManage/schUpdate.do")	                                           
	public String updateScheduleInfoManage(
			@ModelAttribute("loginVO") LoginVO loginVO,
			@ModelAttribute("ScheduleInfo") ScheduleInfo vo
			, HttpServletRequest request
			, BindingResult bindingResult
			, ModelMap model) throws Exception{
		
		model.addAttribute("regist", vo);
		String meesage = "";
		String url = "redirect:/backoffice/sub/equiManage/schList.do";
		
	
		try{			
			int ret  = 0;
			if (vo.getMode().equals("Ins")){
				ret = scheduleInfoManageService.insertScheduleInfoManage(vo);
				meesage = "sucess.common.insert";
				url = "redirect:/backoffice/sub/equiManage/schList.do";				
			}else {
				 ret = scheduleInfoManageService.updateScheduleInfoManage(vo);
				 meesage = "sucess.common.update";
				 url = "forward:/backoffice/sub/equiManage/schView.do";
			}			
			if (ret >0){
				//단말기에 전송할 데이터 저장
				
				int ret_result =0;
				if (vo.getMode().equals("Ins")){				
					vo.setSchCode(  scheduleInfoManageService.selectScheduleMaxInfo()  );  
				} else {				   	
					ret_result = SendHistory.deleteContentSendHistoryInfoManage(vo.getSchCode());
				}
					
				ContentSendHistoryInfo sendHistory = new ContentSendHistoryInfo();
				List<GroupDidInfoVO> resultLst = didInfo.selectGroupInfoManageListByPagination(vo.getGroupCode());
				
				
				
				
				//단말기 전송할 구문 넣기 
				for (int i = 0 ; i < resultLst.size() ; i++    )
				{
					sendHistory.setDidId(resultLst.get(i).getDidId().toString() );
					sendHistory.setHisSeq("");
					sendHistory.setSchCode(vo.getSchCode());
					
					ret_result = SendHistory.insertContentSendHistoryInfoManage(sendHistory);
					
					/*if (vo.getMode().equals("Ins")){											
						ret_result = SendHistory.insertContentSendHistoryInfoManage(sendHistory);
					}else {
						// 수정 삭제후 다시 인서트 하기  
						ret_result = SendHistory.updateContentSendHistoryInfoManage(sendHistory);
					}*/
					if (  ret_result == 0 ){
						LOGGER.debug("단말단 개별 인서트 애러");
					}					
				}
				
				
				model.addAttribute("status", Globals.STATUS_SUCCESS);
				model.addAttribute("message", egovMessageSource.getMessage(meesage));
						
			}else {
				throw new Exception();
			}
			
		}catch (Exception e){
			model.addAttribute("status", Globals.STATUS_FAIL);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.insert"));			
			url = "redirect:/backoffice/sub/equiManage/schList.do";
		}finally {
			
		}					
		return url;
	}
	
}
