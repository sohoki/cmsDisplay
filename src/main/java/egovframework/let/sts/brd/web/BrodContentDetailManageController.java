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

import egovframework.let.sts.cnt.service.ContentFileInfoManageService;
import egovframework.let.sts.cnt.service.ContentFileInfoVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.let.sts.brd.service.BrodContentInfo;
import egovframework.let.sts.brd.service.BrodContentDetail;
import egovframework.let.sts.brd.service.BrodContentDetailTimeManagerService;
import egovframework.let.sts.brd.service.BrodContentDetailVO;
import egovframework.let.sts.brd.service.BrodContentDetailManagerService;
import egovframework.let.sts.brd.service.BrodContentInfoManageService;
import egovframework.let.sts.brd.service.BrodScheduleInfo;

import egovframework.let.sts.brd.service.BrodContentDetailTime;
import egovframework.let.sts.brd.service.BrodScheduleInfoVO;
import egovframework.let.sts.brd.service.BrodScheduleManagerService;


@Controller
public class BrodContentDetailManageController {

	
	
    private static final Logger LOGGER = LoggerFactory.getLogger(BrodContentDetailManageController.class);
	
	@Resource(name="BrodContentDetailService")
	private BrodContentDetailManagerService brodDetail;
	
	
	@Resource(name="BrodContentInfoService")
	private BrodContentInfoManageService brodContent;
	
	@Resource(name="ContentFileInfo")	
	private ContentFileInfoManageService conFileService;
	
	@Resource(name="egovMessageSource")
	protected EgovMessageSource egovMessageSource;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;
	
	@Resource(name="BrodScheduleManagerService")
	private BrodScheduleManagerService brodSchedule;	
	
	@Resource(name="BrodContentTimeManagerService")
	private BrodContentDetailTimeManagerService brodTime;	
	
	
	@RequestMapping(value="/backoffice/sub/brodManage/brodFileSearch.do")
	public ModelAndView selectFileSearch(HttpServletRequest request) throws Exception{
		String orgFileNm =  request.getParameter("orgFileNm") != null ? request.getParameter("orgFileNm") : "";
		
		ContentFileInfoVO searchVO = new ContentFileInfoVO();
		searchVO.setSearchCondition("orignlFileNm");
		searchVO.setSearchKeyword(orgFileNm);
		searchVO.setMediaType("MUSIC");
		searchVO.setFirstIndex(0);
		searchVO.setRecordCountPerPage(100);
		
		ModelAndView model = new ModelAndView("jsonView");
		if (searchVO.getNotConType() == null ) {  searchVO.setNotConType("");}
		
		
		return model.addObject("atchFileLst", conFileService.selectFilePageListByPagination(searchVO)); 
	}
	
	@RequestMapping(value="/backoffice/sub/brodManage/ContentReg.do")
	public String selectBrodContentDetail(@ModelAttribute("loginVO") LoginVO loginVO
									   , BrodContentDetailVO vo
						               , HttpServletRequest request
									   , BindingResult bindingResult
								       , ModelMap model)throws Exception {
		
		model.addAttribute("regist", vo);
		
		try{			
		    model.addAttribute("timeInfo", brodDetail.selectTimeCombo(brodContent.selectBrodContentTimeInfoChar(vo.getBrodCode())));			    
		    model.addAttribute("fileInfo", conFileService.selectFileListCombo());
		    
			if (vo.getMode().equals("Edt")){
				model.addAttribute("regist", brodDetail.selectBrodContenDetailt(vo.getBrodSeq()));
			}
		}catch (Exception e){
			System.out.println(e.toString());	
		}
		return "/backoffice/popup/ContentReg";
	}
	
	@RequestMapping(value="/backoffice/sub/brodManage/brodFileCheck.do")
	@ResponseBody
	public String selectContentRegCheck(HttpServletRequest request) throws Exception {
		String brodCode =  request.getParameter("brodCode") != null ? request.getParameter("brodCode") : "";
		String timeInterval =  request.getParameter("timeInterval") != null ? request.getParameter("timeInterval") : "";
		String atchFileId =  request.getParameter("atchFileId") != null ? request.getParameter("atchFileId") : "";
		
		BrodContentDetail vo = new BrodContentDetail();
		vo.setBrodCode(brodCode);
		vo.setIntervalSection(timeInterval);
		vo.setAtchFileId(atchFileId);
		
		return Integer.toString(brodDetail.selectContentRegCnt(vo));
		
	}
	
	
	@RequestMapping(value="/backoffice/sub/brodManage/brodContentDetailCopy.do")
	public String viewBrodContentDetailCopy (@ModelAttribute("loginVO") LoginVO loginVO
											   , BrodContentInfo vo
								               , HttpServletRequest request
											   , BindingResult bindingResult
										       , ModelMap model) throws Exception {		
		
		model.addAttribute("regist", vo);
		model.addAttribute("brodCopy", brodContent.selectBrodContentCopy(vo.getBrodCode()));
		
		//하단 내용 정리 하기 		
	    return "/backoffice/popup/ContentDetailCopy";
		
	}
	@RequestMapping(value="/backoffice/sub/brodManage/brodContentDetailCopyInsert.do")
	@SuppressWarnings("finally")	
	public String contentDetailCopy(@ModelAttribute("loginVO") LoginVO loginVO
								   , BrodContentDetailVO vo
					               , HttpServletRequest request
								   , BindingResult bindingResult
							       , ModelMap model) throws Exception {		
		
		model.addAttribute("regist", vo);
		String meesage = "";
		String url = "";
		
		int ret = 0;		
		try{
			
			
			    //일정 삭제 이후 인서트 하기 
			    brodDetail.deleteBrodContentBrodCode(vo.getBrodCode());
			    //이후 인서트 하기 
				ret = brodDetail.insertBrodContentCopy(vo);
				if (ret > 0){
					meesage = "sucess.common.insert";
					model.addAttribute("status", Globals.STATUS_SUCCESS);
					url = "forward:/backoffice/sub/brodManage/brodContentCopy.do";
				}else {
					//삭제 하기 				
					meesage = "fail.common.insert";
					url = "forward:/backoffice/sub/brodManage/brodContentCopy.do";
					model.addAttribute("status", Globals.STATUS_FAIL);					
				}				
			model.addAttribute("message", egovMessageSource.getMessage(meesage));	
			
		}catch (Exception e){
			
			System.out.println("e:"+e.toString());			
			
  	    	model.addAttribute("status", Globals.STATUS_FAIL);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.insert"));			
			url = "forward:/backoffice/sub/brodManage/brodContentCopy.do";
  	    }
  	    finally{
  	    	return url;
  	    }
	}
	//분 간격으로 입력 방법
	@RequestMapping(value="/backoffice/sub/brodManage/brodFileTimeCheck.do")
	@ResponseBody
	public String selectContentTimeCheck(HttpServletRequest request) throws Exception {
		String brodCode =  request.getParameter("brodCode") != null ? request.getParameter("brodCode") : "";
		String timeInterval =  request.getParameter("timeInterval") != null ? request.getParameter("timeInterval") : "";
		String timeIntervalInsertCnt =  request.getParameter("timeIntervalInsertCnt") != null ? request.getParameter("timeIntervalInsertCnt") : "";
		String contentInsertInterval =  request.getParameter("contentInsertInterval") != null ? request.getParameter("contentInsertInterval") : "";
		String contentStartDay =  request.getParameter("contentStartDay") != null ? request.getParameter("contentStartDay") : "";
		String contentEndDay =  request.getParameter("contentEndDay") != null ? request.getParameter("contentEndDay") : "";
		String atchFileId =  request.getParameter("atchFileId") != null ? request.getParameter("atchFileId") : "";
		
		
		BrodContentDetail vo = new BrodContentDetail();
		vo.setBrodCode(brodCode);
		String result = "";
		String timeIntervalResult="";
		
		
		vo.setAtchFileId(atchFileId);		
		
		BrodContentDetailTime imsiTime = new BrodContentDetailTime();
		
		for (int i = 0 ; i < Integer.parseInt(timeIntervalInsertCnt); i++){
			
			timeInterval = (i == 0)? timeInterval : String.valueOf(Integer.parseInt(timeInterval) +(Integer.parseInt(contentInsertInterval)));
			
			LOGGER.debug("timeInterval"+lenReplace(timeInterval,3));
			
			
			
			if (Integer.parseInt(timeInterval) > 50){
			   break;	
			}
			vo.setIntervalSection(lenReplace(timeInterval,3));	
			
			if (brodDetail.selectContentRegTimeImsiOverTableCheck(vo) > 600){
				//LOGGER.debug("기본값에서 찾을수 없음");
				vo.setIntervalSection( lenReplace(String.valueOf((Integer.parseInt(timeInterval) - 10)),3));
				
				//System.out.println("-10:"+brodDetail.selectContentRegTimeImsiOverTableCheck(vo));
				if (brodDetail.selectContentRegTimeImsiOverTableCheck(vo) > 600 || (Integer.parseInt(vo.getIntervalSection()) < 0) ){
					
					vo.setIntervalSection( lenReplace(String.valueOf((Integer.parseInt(timeInterval) + 20)),3));
					
					//System.out.println("+10:"+brodDetail.selectContentRegTimeImsiOverTableCheck(vo));
					if (brodDetail.selectContentRegTimeImsiOverTableCheck(vo) > 600 || (Integer.parseInt(vo.getIntervalSection())  > 50)){
						result =  "F";
						LOGGER.debug("for break");
						break;
					}else {
						//입력
						//LOGGER.debug("+10분 전에 하기");
						imsiTime.setIntervalSection(lenReplace(vo.getIntervalSection(),3));
						timeIntervalResult = timeIntervalResult + lenReplace(vo.getIntervalSection(),3)+",";
					}
					
				}else{
					//입력
					//LOGGER.debug("-10분 전에 하기");
					imsiTime.setIntervalSection(lenReplace(vo.getIntervalSection(),3));
					timeIntervalResult = timeIntervalResult + lenReplace(vo.getIntervalSection(),3)+","; 
				}
				
			}else {
			    //입력					
				imsiTime.setIntervalSection(lenReplace(timeInterval,3));	
				timeIntervalResult = timeIntervalResult + lenReplace(timeInterval,3)+",";
			}	
			imsiTime.setBrodCode(brodCode);
			imsiTime.setAtchFileId(atchFileId);
			imsiTime.setContentStartDay(contentStartDay);
			imsiTime.setContentEndDay(contentEndDay);
			
			brodTime.insertBrodContentDetailTime(imsiTime);
			//과거 시간
			
		}
		
		LOGGER.debug("timeIntervalResult:" + timeIntervalResult);
		
		brodTime.deleteBrodContentDetailTimeBrodCode(brodCode);
		
		result =  timeIntervalResult;
		return 	result;	
	}
	public String lenReplace(String txt, int i){
		if (txt.length() == i){
			return txt;
		}else if (txt.equals("0")){
			String zero_txt = ""; 
			for (int a=0; a < i ; a++ ){
				zero_txt += txt;
			}
			return zero_txt;
		}else {
			return "0"+txt;
		}
	}
	
	@RequestMapping(value="/backoffice/sub/brodManage/brodContentDetailDel.do")
	@ResponseBody
	public String deleteContentDetailDel(HttpServletRequest request) throws Exception {
		String brodSeq =  request.getParameter("brodSeq") != null ? request.getParameter("brodSeq") : "";
		String brodCode =  request.getParameter("brodCode") != null ? request.getParameter("brodCode") : "";	
		
		//연동 인지 아닌지 먼저 확인 후 연동이 아니면 연동 부분 제거후 삭제 필요 		
		String basicBrodCode = brodContent.selectBrodContentBasciBrodCode(brodCode);		
		if (basicBrodCode != null){
			//
			brodContent.updateBrodBasicCodeCntMin(brodCode);			
			BrodContentInfo contentInfo = brodContent.selectBrodContentInfo(brodCode);
			contentInfo.setBasicBrodCode("");
			contentInfo.setBrodName(contentInfo.getCenterNm());
			brodContent.updateBrodContentBasicInfoName(contentInfo);
			contentInfo = null;
		}
		
		//연동 콘텐츠 먼저 삭제 후 
		BrodContentDetail vo = new BrodContentDetail();
		vo.setBrodSeq(brodSeq);
		vo.setBrodCode(brodCode);
		//연동 콘텐츠 삭제 
		int ret = brodDetail.deleteContentDetailBasciContent(vo);			
		vo = null;
		//실콘텐츠 삭제
		ret = brodDetail.deleteBrodContentDetail(brodSeq);		
		//여기 부분에 연결된 삭제 리스트 빼기 		
	    String retunrString = schUpdate(brodCode);
	    
		if (ret > 0){
			return "T";
		}else {
			return "F";
		}		
		
		
		
	}
	
	
	
	@RequestMapping(value="/backoffice/sub/brodManage/brodContentDetailUpdate.do")
	public String selectBrodContentDetailUpdate(@ModelAttribute("loginVO") LoginVO loginVO
										   , BrodContentDetail vo
							               , HttpServletRequest request
										   , BindingResult bindingResult
									       , ModelMap model)throws Exception {
		
		model.addAttribute("regist", vo);
		String meesage = "";
		String brodHtml = "";
		String url = "forward:/backoffice/sub/brodManage/ContentReg.do";  		
		String timeIntervalResult = vo.getTimeIntervalResult().toString().substring(0, (vo.getTimeIntervalResult().toString().length() -1)) ;
  	    try{
  	    	int ret  = 0;
  	    	
			if (vo.getMode().equals("Ins")){	
				
				String[] timeArray = timeIntervalResult.split(",");
				for (int i = 0; i < timeArray.length; i++){
					vo.setIntervalSection(timeArray[i].toString());
					ret = brodDetail.insertBrodContentDetail(vo);					
				}
				
				//여기 부분에 직접 입력시 기존 연동 콘텐츠 연동 제거후 입력 하기 
				String basicBrodCode = brodContent.selectBrodContentBasciBrodCode(vo.getBrodCode());		
				if (basicBrodCode != null){
					LOGGER.debug("----------------------- 연결 콘텐츠 제거------------------------------");
					brodContent.updateBrodBasicCodeCntMin(vo.getBrodCode());			
					BrodContentInfo contentInfo = brodContent.selectBrodContentInfo(vo.getBrodCode());
					contentInfo.setBasicBrodCode("");
					contentInfo.setBrodName(contentInfo.getCenterNm());
					brodContent.updateBrodContentBasicInfoName(contentInfo);
					contentInfo = null;
					LOGGER.debug("----------------------- 연결 콘텐츠 제거 완료------------------------------");
				}else {
					LOGGER.debug("----------------------- 연결 콘텐츠 삭제------------------------------");
					brodDetail.deleteBrodBasicBrod(vo.getBrodCode());
					//연결 콘텐츠 인서트 하기 
					LOGGER.debug("----------------------- 연결 콘텐츠 입력------------------------------");				
					brodDetail.insertBrodContentScheduleOtherCopy(vo.getBrodCode());
				}
				
				
				meesage = "sucess.common.insert";
				brodHtml = timeIntervalResult;
				url = "forward:/backoffice/sub/brodManage/ContentReg.do";				
			}else {
				vo.setLastUpdusrId(loginVO.getMberId());
				 ret = brodDetail.updateBrodContentDetail(vo);
				 meesage = "sucess.common.update";
				 url = "forward:/backoffice/sub/brodManage/ContentReg.do";
			}		
			
			// 스케줄 업데이트 넣기 			
			String retunrString = schUpdate(vo.getBrodCode());
			//연결 스케줄 업데이트 넣기 
			
			if (ret >0){
				model.addAttribute("status", Globals.STATUS_SUCCESS);
				model.addAttribute("status_Schedule", retunrString);
				model.addAttribute("message", egovMessageSource.getMessage(meesage));
				model.addAttribute("brodHtml", brodHtml);
			}else {
				throw new Exception();
			}  	    	
  	    }catch (Exception e){
  	    	model.addAttribute("status", Globals.STATUS_FAIL);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.insert"));			
			url = "forward:/backoffice/sub/brodManage/ContentReg.do";
  	    }
  	    finally{
  	    	return url;
  	    }
		
	}
	
	private String schUpdate(String brodCode) throws Exception{
		//인서트나 업데이트시 스케줄  재 등록 
		 int ret = 0;
		 
		 List<BrodContentInfo> contentInfo = brodContent.selectBrodContentBasciContent(brodCode);
		 
		 for (int a = 0; a <contentInfo.size(); a++ ){
			 
			 //연결된 콘텐츠 배포 정보가 있는지 확인 후 없으면 인서트 후 하기 
			 if (contentInfo.get(a).getCenterId() != null &&  contentInfo.get(a).getBrodCode() != ""){
				 BrodScheduleInfo schInfoCheck = new BrodScheduleInfo();
				 schInfoCheck.setBrodCode(contentInfo.get(a).getBrodCode());
				 schInfoCheck.setCenterId(contentInfo.get(a).getCenterId());
				 if (brodSchedule.selectBrodScheduleCnt(schInfoCheck) < 1)	 {
					 schInfoCheck.setCreateCheck("N");
					 schInfoCheck.setBrodDay("20991231");
					 ret = brodSchedule.insertBrodSchedule(schInfoCheck);	 
				 }
				 schInfoCheck = null;
			 }
			 //연결된 콘텐츠 배포 정보 확인 끝 
			 
			 List<BrodScheduleInfoVO> schedule = brodSchedule.selectBrodScheduleUpdateChanage(contentInfo.get(a).getBrodCode());
			 //업데이트 리스트 보여주기 
			 BrodScheduleInfo scheduleInfo = new BrodScheduleInfo();
			 for (int i = 0; i < schedule.size(); i++){
				 
				 scheduleInfo.setBrodCode(contentInfo.get(a).getBrodCode());
				 scheduleInfo.setCreateCheck("N");
				 scheduleInfo.setCenterId(schedule.get(i).getCenterId());
				 scheduleInfo.setBrodDay(schedule.get(i).getBrodDay());
				 scheduleInfo.setScheduleSeq(schedule.get(i).getScheduleSeq());
				 
				 if (schedule.get(i).getCreateCheck().equals("N")){
				     ret = 	 brodSchedule.updateBrodSchedule(scheduleInfo);
				 }else {	
					 //기존 콘텐츠 편성표 지우기 
					 ret = brodSchedule.deleteBrodScheduleSeq(schedule.get(i).getScheduleSeq());
					 //ret = brodSchedule.updateBrodSchedule(vo)
					 if (( schedule.size()-1) == i){
						 ret = brodSchedule.insertBrodSchedule(scheduleInfo);	 
					 }
					 
				 }
			 }
			 // 스케줄 N으로 되어 있으면 1개로 정리 하기
			 scheduleInfo.setCreateCheck("N");
			 
			 if (brodSchedule.selectBrodScheduleStateCnt(scheduleInfo) > 0){
				 brodSchedule.deleteBrodScheduleState(scheduleInfo);
			 }			 
			 scheduleInfo = null;	 
		 }	
		 
		 if (ret > 0){	
			 //변경 수정 내용 입력
			 BrodContentInfo voinfo = new  BrodContentInfo();
			 voinfo.setBrodCode(brodCode);
			 voinfo.setBrodChangeInfo("Y");			 
			 brodContent.updateBrodContentSchChange(voinfo);
			 voinfo = null;
			 return "O";
		 }else {
			 return "F";	 
		 }	 
	}
	
	
	
}
