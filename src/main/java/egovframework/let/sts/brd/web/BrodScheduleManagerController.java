package egovframework.let.sts.brd.web;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.Globals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.let.sts.brd.service.BrodAnniversary;
import egovframework.let.sts.brd.service.BrodAnniversaryManagerService;
import egovframework.let.sts.brd.service.BrodContentDetail;
import egovframework.let.sts.brd.service.BrodContentDetailManagerService;
import egovframework.let.sts.brd.service.BrodContentInfoManageService;
import egovframework.let.sts.brd.service.BrodContentInfo;
import egovframework.let.sts.brd.service.BrodContentInfoVO;
import egovframework.let.sts.brd.service.BrodOrganizationVO;
import egovframework.let.sts.brd.service.BrodScheduleManagerService;
import egovframework.let.sts.brd.service.BrodScheduleInfo;
import egovframework.let.sts.brd.service.BrodScheduleInfoVO;
import egovframework.let.sym.cnt.service.CenterInfo;
import egovframework.let.sym.cnt.service.CenterInfoManageService;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class BrodScheduleManagerController {

	
	 private static final Logger LOGGER = LoggerFactory.getLogger(BrodScheduleManagerController.class);

	 @Resource(name="egovMessageSource")
	 protected EgovMessageSource egovMessageSource;
		
	 /** EgovPropertyService */
	 @Resource(name = "propertiesService")
	 protected EgovPropertyService propertiesService;
	 
	
	 @Resource(name="BrodContentInfoService")
	 private BrodContentInfoManageService brodContent;
	 
	 @Resource(name="BrodScheduleManagerService")
	 private BrodScheduleManagerService brodSchedue;
	 
	 @Resource(name="egovBrodIdGnrService")
	 private EgovIdGnrService egovBrodIdGnrService;
	 
	 //나중에 삭제 할수 있는 방법 찾기
	 @Resource(name="BrodContentDetailService")
	 private BrodContentDetailManagerService brodDetail;
	 
	 @Resource(name="BrodAnniversaryManagerService")
     private BrodAnniversaryManagerService anniverInfo;
	 
	 
	 @Resource(name="CenterInfoManageService")
	 private CenterInfoManageService centerInfoManageService;
	 //나중에 삭제 할수 있는 방법 찾기 끝 부분	 
	 //음원 콘텐츠 배포
	 @RequestMapping(value="/backoffice/sub/brodManage/playShedule.do")
	 public String selectLeftContentLst(@ModelAttribute("loginVO") LoginVO loginVO
									   ,@ModelAttribute("BrodContentInfoVO")  BrodContentInfoVO searchVO
						               , HttpServletRequest request
									   , BindingResult bindingResult
								       , ModelMap model) throws Exception {	
		 
		 
		  model.addAttribute("regist", searchVO);
	     
	      if(  searchVO.getPageUnit() > 0  ){    	   
			    	   searchVO.setPageUnit(searchVO.getPageUnit());
		   }else {
			   searchVO.setPageUnit(propertiesService.getInt("pageUnit"));   
		   }
		   searchVO.setPageSize(propertiesService.getInt("pageSize"));
		   searchVO.setSecGubun("SECGUBUN01");
		   searchVO.setCenterGubun("content");
	       
	      /** pageing */       
	   	  PaginationInfo paginationInfo = new PaginationInfo();
		  paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		  paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		  paginationInfo.setPageSize(searchVO.getPageSize());

		  searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		  searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		  searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

	       model.addAttribute("resultList",   brodContent.selectBrodContentLst(searchVO) );
	       
	       int totCnt = brodContent.selectBrodContentPageCnt(searchVO);       
		   paginationInfo.setTotalRecordCount(totCnt);
	       model.addAttribute("paginationInfo", paginationInfo);
	       model.addAttribute("totalCnt", totCnt);		       
	       model.addAttribute("regist", searchVO);		       
		 
		 return "/backoffice/sub/brodManage/playShedule";
	 }
	 
	 @RequestMapping(value="/backoffice/sub/brodManage/playSheduleStatus.do")
	 public String selectplaySheduleStatus(@ModelAttribute("loginVO") LoginVO loginVO
										   ,@ModelAttribute("searchVO") BrodScheduleInfoVO searchVO
							               , HttpServletRequest request
										   , BindingResult bindingResult
									       , ModelMap model) throws Exception {	
		 
		 
		  model.addAttribute("regist", searchVO);
	     
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
		  if (searchVO.getCreateCheck() == null){
			  //여기 부분 수정 들어가야함
			  System.out.println("searchVO null");
			  searchVO.setCreateCheck("");
		  }else {
			  System.out.println("searchVO"+ searchVO.getCreateCheck());  
		  }
		  

	       model.addAttribute("resultList",   brodSchedue.selectBrodScheduleStatusLst(searchVO) );
	       
	       int totCnt = brodSchedue.selectBrodScheduleStatusPageCnt(searchVO);       
		   paginationInfo.setTotalRecordCount(totCnt);
	       model.addAttribute("paginationInfo", paginationInfo);
	       model.addAttribute("totalCnt", totCnt);		       
	       model.addAttribute("regist", searchVO);		       
		 
		 return "/backoffice/sub/brodManage/playSheduleStatus";
	 }
	 
	 //우측 정보 보여 주기 
	 @RequestMapping(value="/backoffice/sub/brodManage/playRightShedule.do")
	 public ModelAndView selectRightLst(HttpServletRequest request) throws Exception{
		 String brodCode =  request.getParameter("brodCode") != null ? request.getParameter("brodCode") : "";
		 String searchKeyword =  request.getParameter("searchKeyword") != null ? request.getParameter("searchKeyword") : "";
		 
		 BrodScheduleInfoVO searchVO = new BrodScheduleInfoVO();
		 searchVO.setBrodCode(brodCode);
		 if (!searchKeyword.equals("")){
			 searchVO.setSearchKeyword(searchKeyword);
		 }		 
		 ModelAndView model = new ModelAndView("jsonView");		 
		 return model.addObject("schList", brodSchedue.selectBrodRigthLst(searchVO));
		 
		 
	 }
	 //여기 부분도 기념일 부분 수정 
	 @RequestMapping(value="/backoffice/sub/brodManage/brodCenterCnt.do")
	 @ResponseBody
	 public String schCountCheck(HttpServletRequest request) throws Exception{
		 String brodCode =  request.getParameter("brodCode") != null ? request.getParameter("brodCode") : "";
		 String centerId =  request.getParameter("centerId") != null ? request.getParameter("centerId") : "";
		 BrodScheduleInfo vo = new BrodScheduleInfo();
		 vo.setBrodCode(brodCode);
		 vo.setCenterId(centerId);
		 int ret =  brodSchedue.selectBrodScheduleCnt(vo);
		 vo = null;
		 return String.valueOf(ret);
	 }
	 
	 @RequestMapping(value="/backoffice/sub/brodManage/playRightSheduleUpdate.do")
	 @ResponseBody
	 public String scheduleUpdate(@ModelAttribute("loginVO") LoginVO loginVO
			                      , HttpServletRequest request) throws Exception{
		 String brodCode =  request.getParameter("brodCode") != null ? request.getParameter("brodCode") : "";
		 String checkVal =  request.getParameter("checkVal") != null ? request.getParameter("checkVal") : "";
		 
		 String centerStartTime =  request.getParameter("centerStartTime") != null ? request.getParameter("centerStartTime") : "";
		 String centerId =  request.getParameter("centerId") != null ? request.getParameter("centerId") : "";
		 String centerEndTime =  request.getParameter("centerEndTime") != null ? request.getParameter("centerEndTime") : "";
		 BrodScheduleInfo vo = new BrodScheduleInfo();
		 
		 vo.setBrodCode(brodCode);
		 vo.setCenterId(centerId);
		 vo.setBrodDay("20991231");
		 vo.setCenterStartTime(centerStartTime);
		 vo.setCenterEndTime(centerEndTime);
		 String preBrodCode = brodContent.selectBrodContentCenterPreBrodCode(centerId);
		 int ret = 0;
		 if (checkVal.equals("Y")){
			 
			 BrodContentInfo contentInfo = new BrodContentInfo();
			 contentInfo = brodContent.selectBrodContentInfo(brodCode);
			 contentInfo.setCenterId(centerId);
			 LOGGER.debug("brodCode:"+brodCode);
			 LOGGER.debug("get brodCode:"+contentInfo.getBrodCode());
			 //이전 작업이 있는지 확인 먼저 하기 
			 List<BrodScheduleInfo> schInfoLst = brodSchedue.selectBrodScheduleCreateCheckList(vo);
			 //check up데이트 하기 
			 BrodScheduleInfo voUpdate = new BrodScheduleInfo();
			 for (int i = 0; i < schInfoLst.size(); i++){
				 voUpdate.setScheduleSeq(schInfoLst.get(i).getScheduleSeq());
				 voUpdate.setCreateCheck("Y");
				 brodSchedue.updateBrodSchedule(voUpdate);				 
			 }
			 voUpdate = null;
			 
			 
			 String returnTxt = brodContentCheck(contentInfo, centerStartTime, centerEndTime, loginVO.getMberId() , preBrodCode);
			 
			 LOGGER.debug("returnTxt:"+returnTxt);
			 if (!returnTxt.equals("E")){
				 vo.setBrodCode(returnTxt);
				 ret = brodSchedue.deleteBrodScheduleOther(vo);
				 ret = brodSchedue.insertBrodSchedule(vo);
				 ret = brodSchedue.updateCenterSchedule(vo);	 
			 }else {
				 ret = 0;
			 }			 
		 }else {
			 //삭제 먼저 하기 
			 brodCode = brodContent.selectBrodContentCenterCheckBrodCode(centerId);
			 System.out.println("brodCode:" + brodCode);
			 if (brodCode !=  null){
				 //연결된 카운터 하나 삭제 하기
				 String basicBrodCode =  brodContent.selectBrodContentBasciBrodCodePreBrodCode(brodCode);
				 if (!basicBrodCode.equals("")){
					 ret = brodContent.updateBrodContentCenterCntMin(basicBrodCode);	 
				 }
				 //3개 다 지우기
				 brodDetail.deleteBrodContentBrodCode(brodCode);
				 anniverInfo.deleteBrodAnniverBrod(brodCode);
				 brodContent.deleteBrodContent(brodCode);
				  
			 }
			 ret = brodSchedue.deleteBrodSchedule(vo);
		 }		 
		 return String.valueOf(ret);
		 
	 }
	 
	 private String brodContentCheck(BrodContentInfo vo, String centerStartTime, String  centerEndTime , String MberId, String preBrodCode) throws Exception{
		 
		 String resultTxt = "";
		 int ret = 0;
		 String center_brodCode = brodContent.selectBrodContentCenterCheckBrodCode(vo.getCenterId());	
		 
		 
		 
		 try{
		
			 CenterInfo centerinfo = new  CenterInfo();
			 centerinfo = centerInfoManageService.selectCenterInfoManageDetail(vo.getCenterId());
			 String centerNm = centerinfo.getCenterNm();
			 
			 String oldbrodCode = vo.getBrodCode(); 
			 LOGGER.debug("oldbrodCode:"+oldbrodCode);
			 if ( center_brodCode == null  ){
				 //없으면 콘텐츠 신규 입력 
				 
				 vo.setPrebrodCode(vo.getBrodCode());
				 LOGGER.debug("prebrodCode:"+vo.getBrodCode());
				 center_brodCode = egovBrodIdGnrService.getNextStringId();				 
				 
				 vo.setBrodCode(center_brodCode);
				 vo.setCenterId(vo.getCenterId());
				 
				 vo.setBrodName(vo.getBrodName()+"_"+centerNm);
				 
				 				 
				 ret = brodContent.insertBrodContentCenterBrodCodeCopy(vo);
				 		 				 
			 }else {
				 vo.setBasicBrodCode(vo.getBrodCode());
				 
				 vo.setBrodCode(center_brodCode);
				 vo.setBrodName(vo.getBrodName()+"_"+centerNm);
				 
				 ret = brodContent.updateBrodContentCenter(vo);
				 if (preBrodCode != null){				
					 brodContent.updateBrodContentCenterCntMin(preBrodCode);
				 }				 
			 }
			 ret = brodContent.updateBrodContentCenterCntPlus(oldbrodCode);
			 
			 if (ret > 0){
				    BrodContentDetail detail = new BrodContentDetail();
					detail.setPrebrodCode(oldbrodCode);					
					detail.setBrodCode(center_brodCode);
					detail.setFrstRegisterId(MberId);							
					ret = brodDetail.insertBrodContentCenterCopy(detail);
					
					BrodAnniversary anniver = new BrodAnniversary();
					anniver.setPrebrodCode(oldbrodCode);				
					anniver.setBrodCode(center_brodCode);
					anniver.setFrstRegisterId(MberId);							
					ret =  anniverInfo.insertBrodAnniverCenterCopy(anniver);
					resultTxt =  center_brodCode;									
			 }
			 
		 }catch (Exception e){
			 brodDetail.deleteBrodContentBrodCode(center_brodCode);
			 anniverInfo.deleteBrodAnniverBrod(center_brodCode);
			 LOGGER.debug("brodContentCheck:" + e.toString());
			 resultTxt = "E";
		 }
		 return resultTxt;
		 
	 }
	 
}
