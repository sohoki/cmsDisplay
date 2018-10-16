package egovframework.let.sts.brd.web;



import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.Globals;
import egovframework.let.sts.brd.service.BrodAnniversaryManagerService;
import egovframework.let.sts.brd.service.BrodAnniversary;
import egovframework.let.sts.brd.service.BrodContentDetailManagerService;
import egovframework.let.sts.brd.service.BrodContentDetailVO;
import egovframework.let.sts.brd.service.BrodContentInfo;
import egovframework.let.sts.brd.service.BrodContentInfoManageService;
import egovframework.let.sts.brd.service.BrodScheduleInfo;
import egovframework.let.sts.brd.service.BrodScheduleInfoVO;
import egovframework.let.sts.brd.service.BrodScheduleManagerService;
import egovframework.let.sts.cnt.service.ContentFileInfoManageService;
import egovframework.let.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;
import egovframework.rte.fdl.property.EgovPropertyService;

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


@Controller
public class BrodAnniversaryManagerController {
	
	
	     private static final Logger LOGGER = LoggerFactory.getLogger(BrodAnniversaryManagerController.class);
		
		@Resource(name="BrodAnniversaryManagerService")
		private BrodAnniversaryManagerService annInfo;
		
		
		@Resource(name="egovMessageSource")
		protected EgovMessageSource egovMessageSource;
		
		@Resource(name="BrodContentInfoService")
		private BrodContentInfoManageService brodContent;
		
	    /** EgovPropertyService */
	    @Resource(name = "propertiesService")
	    protected EgovPropertyService propertiesService;
	    
	    @Resource(name="ContentFileInfo")	
		private ContentFileInfoManageService conFileService;
	    
		@Resource(name="BrodScheduleManagerService")
		private BrodScheduleManagerService brodSchedule;	

		@Autowired
		private DefaultBeanValidator beanValidator;
		
		@Resource(name="BrodAnniversaryManagerService")
		private BrodAnniversaryManagerService brodAnniService;
		
		
		@Resource(name="BrodContentDetailService")
		private BrodContentDetailManagerService brodDetail;
		
		@Resource(name="CmmnDetailCodeManageService")
	    private EgovCcmCmmnDetailCodeManageService cmmnDetailCodeManageService;
		
		
		@RequestMapping(value="/backoffice/sub/brodManage/ContentSpReg.do")
		public String selectContentSpReg(@ModelAttribute("loginVO") LoginVO loginVO
										   , BrodAnniversary vo
							               , HttpServletRequest request
										   , BindingResult bindingResult
									       , ModelMap model)throws Exception {
			
			model.addAttribute("regist", vo);
			
			try{			
			    model.addAttribute("timeInfo", brodDetail.selectTimeHourCombo());			    
			    model.addAttribute("fileInfo", conFileService.selectFileListCombo());
			    model.addAttribute("anniversaryGubun", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT020"));
			    
				if (vo.getMode().equals("Edt")){
					
					vo = brodAnniService.selectBrodAnniver(vo.getBrodAnnSeq());
					if (vo.getAnniversaryGubun().equals("ANNGUBUN02")){
						vo.setAnniversaryTimeHour(vo.getAnniversaryTime().substring(0,2));
						vo.setAnniversaryTimeTime(vo.getAnniversaryTime().substring(2,4));
					}						
					model.addAttribute("regist", vo);
				}else {
					vo.setAnniversaryGubun("ANNGUBUN01");
					model.addAttribute("regist", vo);
				}
			}catch (Exception e){
				System.out.println(e.toString());	
			}
			return "/backoffice/popup/ContentSpReg";
		}
		
		@RequestMapping(value="/backoffice/sub/brodManage/ContentSpRegUpdate.do")
		@SuppressWarnings("finally")	
		public String updateBrodContentSpDetail(@ModelAttribute("loginVO") LoginVO loginVO
											    , BrodAnniversary vo
								               , HttpServletRequest request
											   , BindingResult bindingResult
										       , ModelMap model)throws Exception {
			
			model.addAttribute("regist", vo);
			String meesage = "";
			String url = "forward:/backoffice/sub/brodManage/ContentSpReg.do";  		
			
	  	    try{
	  	    	int ret  = 0;
				if (vo.getAnniversaryGubun().equals("ANNGUBUN02")){
				   vo.setAnniversaryTime(vo.getAnniversaryTimeHour()+vo.getAnniversaryTimeTime());	
				}	  	    	
				if (vo.getMode().equals("Ins")){	
										
					ret = brodAnniService.insertBrodAnniver(vo) ;
				
					
					meesage = "sucess.common.insert";
					url = "forward:/backoffice/sub/brodManage/ContentSpReg.do";				
				}else {
					 vo.setLastUpdusrId(loginVO.getMberId());
					 ret = brodAnniService.updateBrodAnniver(vo);
					 
					 
					 meesage = "sucess.common.update";
					 url = "forward:/backoffice/sub/brodManage/ContentSpReg.do";
				}	
				
				
				
				String retunrString = schUpdate(vo.getBrodCode(), "Ins");
				if (retunrString.equals("O")){
					LOGGER.debug("----------------------- 스케줄 업데이트 ------------------------------");
				}else {
					LOGGER.debug("----------------------- 스케줄 업데이트 실패 ------------------------------");
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
				url = "forward:/backoffice/sub/brodManage/ContentSpReg.do";
	  	    }
	  	    finally{
	  	    	return url;
	  	    }			
		}
		@RequestMapping(value="/backoffice/sub/brodManage/ContentSpRegDel.do")
		@ResponseBody
		public String deleteContentDetailDel(HttpServletRequest request) throws Exception {
			String brodAnnSeq =  request.getParameter("brodAnnSeq") != null ? request.getParameter("brodAnnSeq") : "";
			
			BrodAnniversary anni = brodAnniService.selectBrodAnniver(brodAnnSeq);
			String retunrString = schUpdate(anni.getBrodCode(), "Del");
			
							
			if (retunrString.equals("O")){
				LOGGER.debug("----------------------- 스케줄 업데이트 ------------------------------");
				int ret = brodAnniService.deleteBrodAnniver(brodAnnSeq);
				if (ret > 0){				  
					return "T";
				} else{
					return "F";
				}
			}else {					
				LOGGER.debug("----------------------- 스케줄 업데이트 실패 ------------------------------");
				return "F";
			}								
		}
		private String schUpdate(String brodCode, String mode) throws Exception{
			//인서트나 업데이트시 스케줄  재 등록 
			 int ret = 0;
			 LOGGER.debug("----------------------- 기본 정보 먼저 가지고 와서 상위 버전 있는지 확인------------------------------");
			 
			//여기 부분에 직접 입력시 기존 연동 콘텐츠 연동 제거후 입력 하기 
			String basicBrodCode = brodContent.selectBrodContentBasciBrodCode(brodCode);		
			if (basicBrodCode != null){
				LOGGER.debug("----------------------- 연결 콘텐츠 제거------------------------------");
				brodContent.updateBrodBasicCodeCntMin(brodCode);			
				BrodContentInfo contentInfo = brodContent.selectBrodContentInfo(brodCode);
				contentInfo.setBasicBrodCode("");
				contentInfo.setBrodName(contentInfo.getCenterNm());
				brodContent.updateBrodContentBasicInfoName(contentInfo);
				contentInfo = null;
				LOGGER.debug("----------------------- 연결 콘텐츠 제거 완료------------------------------");
			}else {
				LOGGER.debug("----------------------- 연결 기념 콘텐츠 삭제------------------------------");
				brodAnniService.deleteBrodAnnBasicBrod(brodCode);  				
				LOGGER.debug("----------------------- 연결 기념 콘텐츠 입력------------------------------");
				if (mode.equals("Ins")){
					 brodAnniService.insertBrodAnniverBasicBrodCodeCopy(brodCode);	 
				}
				
			}
			 
			 
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
			 ret =  1;
			 
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
