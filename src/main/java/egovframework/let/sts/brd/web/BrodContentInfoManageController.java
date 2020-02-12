package egovframework.let.sts.brd.web;


import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.ibm.icu.text.SimpleDateFormat;

import egovframework.let.sts.cnt.service.ContentFileInfoManageService;
import egovframework.let.sts.cnt.service.ContentFileInfoVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.let.sts.brd.service.BasciBrodInfoManageService;
import egovframework.let.sts.brd.service.BrodAnniversary;
import egovframework.let.sts.brd.service.BrodAnniversaryManagerService;
import egovframework.let.sts.brd.service.BrodAnniversaryVO;
import egovframework.let.sts.brd.service.BrodContentDetail;
import egovframework.let.sts.brd.service.BrodContentDetailManagerService;
import egovframework.let.sts.brd.service.BrodContentDetailVO;
import egovframework.let.sts.brd.service.BrodContentInfo;
import egovframework.let.sts.brd.service.BrodContentInfoVO;
import egovframework.let.sts.brd.service.BrodContentInfoManageService;
import egovframework.let.sts.brd.service.BrodOrganization;
import egovframework.let.sts.brd.service.BrodOrganizationVO;
import egovframework.let.sts.brd.service.BrodOrganizationManagerService;
import egovframework.let.sts.brd.service.BrodScheduleInfo;
import egovframework.let.sts.brd.service.BrodScheduleInfoVO;
import egovframework.let.sts.brd.service.BrodScheduleManagerService;


import egovframework.let.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;
import egovframework.let.sym.cnt.service.CenterInfo;
import egovframework.let.sym.cnt.service.CenterInfoManageService;
import egovframework.let.utl.fcc.service.UniSelectInfoManageService;
import egovframework.let.utl.fcc.service.UniSelectInfo;


@Controller
public class BrodContentInfoManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BrodContentInfoManageController.class);
	
	@Resource(name="BrodContentInfoService")
	private BrodContentInfoManageService brodContent;
	
	@Resource(name="egovMessageSource")
	protected EgovMessageSource egovMessageSource;
	
	@Resource(name="BrodScheduleManagerService")
	private BrodScheduleManagerService brodSchedule;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;
	
	@Resource(name="egovBrodIdGnrService")
	private EgovIdGnrService egovBrodIdGnrService;
	
	@Resource(name="CmmnDetailCodeManageService")
    private EgovCcmCmmnDetailCodeManageService cmmnDetailCodeManageService;
	
	
	@Resource(name="ContentFileInfo")	
	private ContentFileInfoManageService conFileService;
	
	
	@Resource(name="BrodContentDetailService")
	private BrodContentDetailManagerService brodDetail;
	
	
	@Resource(name="BrodAnniversaryManagerService")
	private BrodAnniversaryManagerService anniverInfo;
	
	@Resource(name="UniSelectInfoManageService")
	private UniSelectInfoManageService uniSelectInfo;
	
	@Resource(name="CenterInfoManageService")
	private CenterInfoManageService centerInfoManageService;
	
	
	@Resource(name="BrodOrganizationService")
	private BrodOrganizationManagerService brodOrgService;
	
	@Resource(name="BasciBrodInfoManageService")
	private BasciBrodInfoManageService basicInfo;
	

			
	@RequestMapping ("/backoffice/sub/brodManage/brodContentList.do")	
	public String selectBrodContentLst(@ModelAttribute("loginVO") LoginVO loginVO
												 , @ModelAttribute("searchVO") BrodContentInfoVO searchVO
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
			  System.out.println(searchVO.getSecGubun());
			   
			  if (searchVO.getSecGubun() == null) { searchVO.setSecGubun("");}
			  if (searchVO.getCenterGubun() == null) { searchVO.setCenterGubun("");}
			   
			   
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
		       System.out.println("searchVO:" + searchVO.getPageUnit());
		       System.out.println("searchVO:" + searchVO.getSecGubun());
		       System.out.println("searchVO:" + searchVO.getCenterGubun());
		       		       
		       return "/backoffice/sub/brodManage/brodContentList";		
		
	}
	@RequestMapping(value="/backoffice/sub/brodManage/brodContentPlayListRightInfo.do")
	public ModelAndView selectBrodPlayListRight(@ModelAttribute("loginVO") LoginVO loginVO, @ModelAttribute("BrodContentInfo") BrodContentInfo brodContentInfo
			                                    , HttpServletRequest request) throws Exception{
		String atchFileId =  request.getParameter("atchFileId") != null ? request.getParameter("atchFileId") : "";
				LOGGER.info("brodContentInfo:======"+ brodContentInfo);
        ModelAndView model = new ModelAndView("jsonView");
        return model.addObject("contentList", brodContent.selectBrodRight(brodContentInfo));
        // return model.addObject("contentList", brodContent.selectBrodRight(atchFileId));
		
	}
	@RequestMapping(value="/backoffice/sub/brodManage/rightbrodContentDetailDel.do")
	@ResponseBody
	public String deleteRightContent(HttpServletRequest request) throws Exception{
		String splitCode =  request.getParameter("splitCode") != null ? request.getParameter("splitCode") : "";
		
		String insert_brodCode =  request.getParameter("insert_brodCode") != null ? request.getParameter("insert_brodCode") : "";		
		try{
			String[] brodSeqCodeArray = splitCode.substring(1).split(",");
			
			int ret = 0;			
			for (int i = 0; i < brodSeqCodeArray.length; i++ ){
				
				
				String[] delGubun = brodSeqCodeArray[i].toString().split("ㅣ");				
				
				
				if (delGubun[1].toString().equals("D")){
					ret = brodDetail.deleteBrodContentDetail(delGubun[0].toString());	
				}else {
					ret = anniverInfo.deleteBrodAnniver(delGubun[0].toString());
				}	

			}
			
			ret = scheduleUpdate(insert_brodCode);
			
			return "T";
		}catch(Exception e){
			LOGGER.debug("deleteRightContent:"+ e.toString());
			return "F";
		}
		
	}
	@RequestMapping(value="/backoffice/sub/brodManage/brodContentDetailCenterUpdate.do")	
	public String updateFileBrodSchedule(@ModelAttribute("loginVO") LoginVO loginVO
										   , BrodContentDetail vo
							               , HttpServletRequest request
										   , BindingResult bindingResult
									       , ModelMap model)throws Exception {
		
		String[] brodArray = vo.getInsert_brodCode().toString().split(",");
		String resutlMessage = "스케줄 음원 등록 <br>";
		String interval_section = vo.getIntervalSection();
		String centerNm = "";
		try{
			for (int i = 0; i < brodArray.length; i++){
				LOGGER.debug("brodCode:"+brodArray[i].toString());
				//시간 확인 하기 
				vo.setBrodCode(brodArray[i].toString());
				
				centerNm = brodContent.selectBrodContentCenterNm(brodArray[i].toString());
				
				if (brodDetail.selectContentRegTimeOverCheck(vo)> 600){
					if ((Integer.parseInt(interval_section) - 10) == -10){
						vo.setIntervalSection("050");
					}else{
						vo.setIntervalSection(egovframework.let.utl.fcc.service.EgovStringUtil.lenReplace(String.valueOf((Integer.parseInt(interval_section) - 10)),3));
					}
					if (brodDetail.selectContentRegTimeOverCheck(vo)> 600){
						
						if ((Integer.parseInt(interval_section) + 10) == 60){
							vo.setIntervalSection("000");
						}else {
							vo.setIntervalSection(egovframework.let.utl.fcc.service.EgovStringUtil.lenReplace(String.valueOf((Integer.parseInt(interval_section) + 10)),3));
						}
						if (brodDetail.selectContentRegTimeOverCheck(vo)> 600){
						
							resutlMessage = resutlMessage + centerNm + "(" + brodArray[i].toString() +"), 스케줄 등록 실패 <br>";									
						
						}else {
					
							resutlMessage = resutlMessage + centerNm + "(" + brodArray[i].toString() +"), 선택 대비 10분 후에 등록 완료 <br>";
							brodDetail.insertBrodContentDetail(vo);	
						}					
					}else {
					
						resutlMessage = resutlMessage + centerNm + "(" + brodArray[i].toString() +"), 선택 대비 10분 전에 등록 완료 <br>";
						brodDetail.insertBrodContentDetail(vo);
					}
					
				}else {
					
					resutlMessage = resutlMessage + centerNm + " (" +brodArray[i].toString() +") | 스케줄 등록 완료 <br>";
					brodDetail.insertBrodContentDetail(vo);
				}	
				
			}
			
			int ret = scheduleUpdate(vo.getInsert_brodCode());
			model.addAttribute("status", Globals.STATUS_SUCCESS);
			model.addAttribute("message", resutlMessage);			
		}catch(Exception e){
			model.addAttribute("status", Globals.STATUS_FAIL);
			resutlMessage = "업데이트 실패:" + e.toString(); 
			model.addAttribute("message", resutlMessage);		
			LOGGER.debug(e.toString());
		}
		return "forward:/backoffice/sub/brodManage/brodFileContentCopy.do";
	}

	@RequestMapping(value="/backoffice/sub/brodManage/brodContentAnnDetailCenterUpdate.do")	
	public String updateFileAnnBrodSchedule(@ModelAttribute("loginVO") LoginVO loginVO
										   , BrodAnniversary vo
							               , HttpServletRequest request
										   , BindingResult bindingResult
									       , ModelMap model)throws Exception {
		
		String[] brodArray = vo.getInsert_brodCode().toString().split(",");
		String resutlMessage = "특정방송등록 <br>";
		String centerNm = "";
		try{
			int ret = 0;
			for (int i = 0; i < brodArray.length; i++){
				centerNm = brodContent.selectBrodContentCenterNm(brodArray[i].toString());
				LOGGER.debug("brodCode:"+brodArray[i].toString());
				//시간 확인 하기 
				vo.setBrodCode(brodArray[i].toString());
				vo.setAnniverStartDay(vo.getContentStartDay());
				vo.setAnniverEndDay(vo.getContentEndDay());
				if (vo.getAnniversaryGubun().equals("ANNGUBUN02")){
					   vo.setAnniversaryTime(vo.getAnniversaryTimeHour()+vo.getAnniversaryTimeTime());	
					}
				ret = anniverInfo.insertBrodAnniver(vo) ;
				
				
				
				resutlMessage = resutlMessage + centerNm + "(" +brodArray[i].toString() + "), 특정방송 등록 완료<br>";
			}
			
			ret = scheduleUpdate(vo.getInsert_brodCode());
			model.addAttribute("status", Globals.STATUS_SUCCESS);
			model.addAttribute("message", resutlMessage);
		}catch(Exception e){
			model.addAttribute("status", Globals.STATUS_FAIL);
			resutlMessage = "업데이트 실패:" + e.toString(); 
			model.addAttribute("message", resutlMessage);		
			LOGGER.debug(e.toString());
		}
		return "forward:/backoffice/sub/brodManage/brodFileContentCopy.do";
		
		
	}
	//여기 부분 주소로 해서 호출 해 보기 
	private int scheduleUpdate(String insert_brodCode){
		try{
			BrodScheduleInfo scheduleInfo = new BrodScheduleInfo();
			BrodContentInfo contentInfo = new BrodContentInfo();
			String[] brodArray = insert_brodCode.split(",");
			for (int i =0; i < brodArray.length; i++ ){
								
				contentInfo = brodContent.selectBrodContentInfo(brodArray[i].toString());
				//연결 콘텐츠 카운터 삭제
				brodContent.updateBrodBasicCodeCntMin(brodArray[i].toString());
				contentInfo.setBasicBrodCode("");				
				brodContent.updateBrodContentBasicInfo(contentInfo);
				
				scheduleInfo.setCreateCheck("N");
				scheduleInfo.setCenterId(contentInfo.getCenterId());
				scheduleInfo.setBrodCode(brodArray[i].toString());
				brodSchedule.updateBrodScheduleCenter(scheduleInfo);
			}
			contentInfo = null;
			scheduleInfo = null;
			return 1;
		}catch(Exception e){
			LOGGER.debug("scheduleUpdate Error:" + e.toString());
			return 0;
		}
	}
	//음원 등록창
	@RequestMapping(value="/backoffice/sub/brodManage/brodFileContentCopy.do")
	public String selectPopCenterReg(@ModelAttribute("loginVO") LoginVO loginVO
								   , BrodContentInfoVO vo
					               , HttpServletRequest request
								   , BindingResult bindingResult
							       , ModelMap model) throws Exception {
		model.addAttribute("regist", vo);
		String insert_brodCode = request.getParameter("insert_brodCode") != null ? request.getParameter("insert_brodCode") : "";
		String atchFileId = request.getParameter("atchFileId") != null ? request.getParameter("atchFileId") : "";
		
		
		model.addAttribute("anniversaryGubun", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT020"));
		model.addAttribute("timeInfo1", brodDetail.selectTimeHourCombo());
		vo.setInsert_brodCode(insert_brodCode);
		//파일 정보 
		ContentFileInfoVO fileinfo = new ContentFileInfoVO();
		fileinfo = conFileService.selectFileDetail(atchFileId);
		model.addAttribute("fileInfo", fileinfo.getOrignlFileNm());
		model.addAttribute("timeInfo", brodDetail.selectTimeCombo("050"));
		
		
		return "/backoffice/popup/brodContentCopy";		
	}
	//편성표 보여주기 
	@RequestMapping(value="/backoffice/sub/brodManage/ContentBrodReport.do")
	public String selectBrodPageList(@ModelAttribute("loginVO") LoginVO loginVO
								   , BrodOrganization vo
					               , HttpServletRequest request
								   , BindingResult bindingResult
							       , ModelMap model) throws Exception {
		String centerId =  request.getParameter("centerId") != null ? request.getParameter("centerId") : "";
		
		if (vo.getCenterId() == null || centerId == ""){ vo.setCenterId("");}
		LOGGER.debug("centerId:" + vo.getCenterId());
		model.addAttribute("resultList",   brodOrgService.selectBrodOrgnizationPage(vo ));
		model.addAttribute("regist", brodContent.selectBrodContentInfo(vo.getBrodCode()));
		model.addAttribute("centerId", vo.getCenterId());
		return "/backoffice/popup/brodPageLst";
	}
	@RequestMapping("/backoffice/sub/brodManage/ContentBrodExcel.do")
	public ModelAndView selectBrodExcelList (@ModelAttribute("BrodOrganization") BrodOrganization searchVO)throws Exception{
		if (searchVO.getCenterId() == null){ searchVO.setCenterId("");}
		
		List<BrodOrganization> brodReport = brodOrgService.selectBrodOrgnizationPage(searchVO);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("brodReport", brodReport);
		
		return new ModelAndView("BrodOrgExcelView", map);
		
	}
	@RequestMapping(value="/backoffice/sub/brodManage/brodContentDetail.do")
	public String selectBrodContentDetail(@ModelAttribute("loginVO") LoginVO loginVO
									   , BrodContentInfoVO vo
						               , HttpServletRequest request
									   , BindingResult bindingResult
								       , ModelMap model)throws Exception {
		
		model.addAttribute("regist", vo);
		
		try{
			
		    model.addAttribute("brodInterval", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT019"));
		    // model.addAttribute("basicInfo", conFileService.selectFileListCombo());
		    model.addAttribute("basicInfo", basicInfo.selectBasicBrodCombo());
		    
			if (vo.getMode().equals("Edt")){
				model.addAttribute("regist", brodContent.selectBrodContentInfo(vo.getBrodCode()));
			}
		}catch (Exception e){
			System.out.println(e.toString());	
		}
		return "/backoffice/sub/brodManage/brodContentDetail";
	}
	@RequestMapping(value="/backoffice/sub/brodManage/brodContentUpdate.do")	
	@SuppressWarnings("finally")	
	public String updateBrodContent(@ModelAttribute("loginVO") LoginVO loginVO
								   , BrodContentInfo vo
					               , HttpServletRequest request
								   , BindingResult bindingResult
							       , ModelMap model) throws Exception {
		
		model.addAttribute("regist", vo);
		String meesage = "";
		String url = "forward:/backoffice/sub/brodManage/brodContentList.do";  		
		
  	    try{
  	    	int ret  = 0;
  	    	
  	    	 String preInterval = "";
  	    	
			if (vo.getMode().equals("Ins")){
				
				vo.setBrodCode( egovBrodIdGnrService.getNextStringId());
				vo.setFrstRegisterId(loginVO.getMberId());
				
				
				if(vo.getBasicFileId() == null || vo.getBasicFileId().equals("")){
					vo.setBasicFileId("FILE_000000000000001");
				}
				
				ret = brodContent.insertBrodContent(vo);
				meesage = "sucess.common.insert";
				url = "forward:/backoffice/sub/brodManage/brodContentList.do";				
			}else {
				 
				//시간이 줄어 들면 줄어든 시간 많큼 삭제
				
				 preInterval = brodContent.selectBrodContentTimeInfo(vo.getBrodCode());
				
				 vo.setLastUpdusrId(loginVO.getMberId());
				 ret = brodContent.updateBrodContent(vo);
				 
				 
				 meesage = "sucess.common.update";
				 url = "forward:/backoffice/sub/brodManage/brodContentList.do";
				 
			}			
			if (ret >0){
				model.addAttribute("status", Globals.STATUS_SUCCESS);
				model.addAttribute("message", egovMessageSource.getMessage(meesage));	
				
				//줄어든 시간 만큼 상세 스케줄 삭제
				String nowInterval = brodContent.selectBrodContentTimeInfo(vo.getBrodCode());
				if ( Integer.parseInt(preInterval) >  Integer.parseInt(nowInterval) ){
					BrodContentDetail detail = new BrodContentDetail();
					detail.setBrodCode(vo.getBrodCode());
					detail.setIntervalSection(nowInterval);					
					ret = brodDetail.deleteBrodContentTimeDel(detail);
				}
			}else {
				throw new Exception();
			}  	    	
  	    }catch (Exception e){
  	    	model.addAttribute("status", Globals.STATUS_FAIL);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.insert"));			
			url = "redirect:/backoffice/sub/brodManage/brodContentList.do";
  	    }
  	    finally{
  	    	return url;
  	    }
		    
		
	}
	@RequestMapping(value="backoffice/sub/brodManage/brodScheduleConfirm.do")
	@ResponseBody
	public String batchSchChnage(HttpServletRequest request)throws Exception{
		
		
		 String brodCode =  request.getParameter("brodCode") != null ? request.getParameter("brodCode") : ""; 
		 //업데이트 변경 시는 변경 내용을 schedule 쪽으로 변경 
		 int ret = 0;
		 List<BrodScheduleInfoVO> schedule = brodSchedule.selectBrodScheduleUpdateChanage(brodCode);
		 //업데이트 리스트 보여주기 
		 BrodScheduleInfo scheduleInfo = new BrodScheduleInfo();
		 for (int i = 0; i < schedule.size(); i++){
			 
			 scheduleInfo.setBrodCode(brodCode);
			 scheduleInfo.setCreateCheck("N");
			 scheduleInfo.setCenterId(schedule.get(i).getCenterId());
			 scheduleInfo.setBrodDay(schedule.get(i).getBrodDay());
			 
			 if (schedule.get(i).getCreateCheck().equals("N")){
			     ret = 	 brodSchedule.updateBrodSchedule(scheduleInfo);
			 }else {	
				 //기존 콘텐츠 편성표 지우기 						 
				 ret = brodSchedule.insertBrodSchedule(scheduleInfo);
			 }
		 }
		 scheduleInfo = null;
		 if (ret > 0){	
			 //변경 수정 내용 입력
			 BrodContentInfo vo = new  BrodContentInfo();
			 vo.setBrodCode(brodCode);
			 vo.setBrodChangeInfo("Y");			 
			 brodContent.updateBrodContentSchChange(vo);			 
			 return "O";
		 }else {
			 return "F";
		 }
		 
	}
	@RequestMapping(value="/backoffice/sub/brodManage/brodContentView.do")
	public String viewBrodContent (@ModelAttribute("loginVO") LoginVO loginVO
			   , BrodContentInfo vo
               , HttpServletRequest request
			   , BindingResult bindingResult
		       , ModelMap model) throws Exception {		
		vo = brodContent.selectBrodContentInfo(vo.getBrodCode());
		model.addAttribute("regist", vo);	
		//특정 방송 리스트 보여 주기 
		BrodAnniversary annBrod = new BrodAnniversary();
		annBrod.setBrodCode(vo.getBrodCode());
		annBrod.setBrodDay("");
		model.addAttribute("brodAnniver", anniverInfo.selectBrodAnniverLst(annBrod) );
		annBrod = null;
		//하단 내용 정리 하기 		
	    return "/backoffice/sub/brodManage/brodContentView";
		
	}
	@RequestMapping(value="/backoffice/sub/brodManage/brodContentCopy.do")
	public String viewBrodContentCopy (@ModelAttribute("loginVO") LoginVO loginVO
			   , BrodContentInfo vo
               , HttpServletRequest request
			   , BindingResult bindingResult
		       , ModelMap model) throws Exception {		
		
		model.addAttribute("regist", vo);
		//하단 내용 정리 하기 		
	    return "/backoffice/popup/ContentCopy";
		
	}
	
	
	//테이블 리스트 보여 주기
	@RequestMapping(value="/backoffice/sub/brodManage/timeContentLst.do")
	public ModelAndView selectTimeContent(HttpServletRequest request) throws Exception {
		String brodCode =  request.getParameter("brodCode") != null ? request.getParameter("brodCode") : "";
		String timeInterval =  request.getParameter("timeInterval") != null ? request.getParameter("timeInterval") : "";
		String brodDay = request.getParameter("brodDay") != null ? request.getParameter("brodDay") : "";
		
		ModelAndView model = new ModelAndView("jsonView");
		
		BrodContentDetailVO detail = new BrodContentDetailVO();
		detail.setBrodCode(brodCode);
		detail.setIntervalSection(timeInterval);
		detail.setBrodDay(brodDay);
		
		return model.addObject("contentList", brodDetail.selectBrodContentDetailLst(detail));
	}
	
	
	
	
	
	@RequestMapping(value="/backoffice/sub/brodManage/brodContentCopyInsertCnt.do")
	@ResponseBody
	public String contentCnt (HttpServletRequest request) throws Exception {
		
		String brodCode =  request.getParameter("brodCode") != null ? request.getParameter("brodCode") : "";
		BrodContentDetailVO searchVO = new BrodContentDetailVO();
		searchVO.setBrodCode(brodCode);
		searchVO.setIntervalSection("");
		
		return String.valueOf(brodDetail.selectBrodContentDetailPageCnt(searchVO));
	}
	
	//공용으로 카피 쓰기
	public int publicContentCopy(BrodContentInfo vo, String MberId , String CopyGubun)throws Exception{
		
		String oldbrodCode = vo.getBrodCode(); 
		String brodCode = egovBrodIdGnrService.getNextStringId();
		int resultTxt = 0;
		try{
		
			vo.setPrebrodCode(vo.getBrodCode());
			vo.setBrodCode( brodCode);
			vo.setFrstRegisterId(MberId);		
			
			int ret = 0;
			if (CopyGubun.equals("C")){
				ret = brodContent.insertBrodContentCopy(vo);
			}else{
				ret = brodContent.insertBrodContentCenterBrodCodeCopy(vo);
			}			
			if (ret > 0){
				//상세 리스트 복사
				
				BrodContentDetail detail = new BrodContentDetail();
				detail.setPrebrodCode(oldbrodCode);
				detail.setContentStartDay(vo.getContentStartDay());
				detail.setContentEndDay(vo.getContentEndDay());
				detail.setBrodCode(brodCode);
				detail.setFrstRegisterId(MberId);							
				ret = brodDetail.insertBrodContentCopy(detail);
				if (ret > 0){
					BrodAnniversary anniver = new BrodAnniversary();
					anniver.setPrebrodCode(oldbrodCode);
					anniver.setAnniverStartDay(vo.getContentStartDay());
					anniver.setAnniverEndDay(vo.getContentEndDay());
					anniver.setBrodCode(brodCode);
					anniver.setFrstRegisterId(MberId);							
					ret =  anniverInfo.insertBrodAnniverCopy(anniver);
					resultTxt =  1;					
				}else {
					//삭제 하기
					brodContent.deleteBrodContent(brodCode);					
					resultTxt = 2;
				}
					
			}
		}catch (Exception e){
				brodDetail.deleteBrodContentBrodCode(brodCode);
				brodContent.deleteBrodContent(brodCode);
				resultTxt = 3;
		}
		return resultTxt;
	}
	
	@RequestMapping(value="/backoffice/sub/brodManage/brodContentCopyInsert.do")	
	public String contentCopy(@ModelAttribute("loginVO") LoginVO loginVO
								   , BrodContentInfo vo
					               , HttpServletRequest request
								   , BindingResult bindingResult
							       , ModelMap model) throws Exception {		
		
		model.addAttribute("regist", vo);
		String meesage = "";
		String url = "";
		/*
		String oldbrodCode = vo.getBrodCode();
		String brodCode = "";
		int ret = 0;
		*/
		//콘텐츠 복사
		
		int result = publicContentCopy(vo, loginVO.getMberId(), "C");
		
		switch(result){
		   case 1 :  
			   meesage = "sucess.common.insert";
				model.addAttribute("status", Globals.STATUS_SUCCESS);
				url = "forward:/backoffice/sub/brodManage/brodContentCopy.do";
				break;
		   case 2 :
			   meesage = "fail.common.insert";
				url = "forward:/backoffice/sub/brodManage/brodContentCopy.do";
				model.addAttribute("status", Globals.STATUS_FAIL);
				break;
		   case 3 :
		        model.addAttribute("status", Globals.STATUS_FAIL);
			    model.addAttribute("message", egovMessageSource.getMessage("fail.common.insert"));			
			    url = "forward:/backoffice/sub/brodManage/brodContentCopy.do";
			   break;
		}
		model.addAttribute("message", egovMessageSource.getMessage(meesage));		
		return url;
	}
	
	
	@RequestMapping(value="/backoffice/sub/brodManage/brodContentDel.do")
	@ResponseBody
	public String deleteBrodContent(HttpServletRequest request) throws Exception {
		String brodCode =  request.getParameter("brodCode") != null ? request.getParameter("brodCode") : "";
		
		
		
		
		String[] brodCodeArray = brodCode.substring(1).split(",");
		int ret = 0;
		//연결되어 있는 모든것 삭제
		for (int i =0; i < brodCodeArray.length; i++){
			//배치 스케줄 삭제
			ret = brodOrgService.deleteContentToOrg(brodCodeArray[i].toString());
			//배치 내용 삭제			 
			ret = brodSchedule.deleteBrodScheduleAll(brodCodeArray[i].toString());			
			//기본음원 삭제
			brodDetail.deleteBrodContentBrodCodeALL(brodCodeArray[i].toString());
			//특정 방송 삭제			
			anniverInfo.deleteBrodAnniverBrodAll(brodCodeArray[i].toString());			
			//데이타 삭제			
			ret = brodContent.deleteBrodContentAll(brodCodeArray[i].toString());
			
		}		
		if (ret > 0){
			return "0";
		}else {
			return "F";
		}
	}
	@RequestMapping(value="/backoffice/sub/brodManage/brodComboLst.do")
	public ModelAndView selectBrodCombo (HttpServletRequest request)throws Exception{		
		ModelAndView model = new ModelAndView("jsonView");	
		model.addObject("brodInfoLst", brodContent.selectBrodContentCopy("000000000000000"));
		return (model);
		
	}	
	@RequestMapping(value="/backoffice/sub/brodManage/playCenterInfo.do")
	public String selectCenterBrodInfo(@ModelAttribute("loginVO") LoginVO loginVO
			                           , BrodContentInfo vo									  
						               , HttpServletRequest request
									   , BindingResult bindingResult									   
							           , HttpServletResponse response
								       , ModelMap model) throws Exception {	
		
		
		 
		
		try{
		
		String AnnDay = null;
		CenterInfo centerInfo = new CenterInfo();
		centerInfo.setCenterId(vo.getCenterId());
		//일자 확인 하기 
		if (vo.getCenterAnniverDay() == null  ){   
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	        Calendar c1 = Calendar.getInstance();
		    String strToday = sdf.format(c1.getTime());
		    
			vo.setCenterAnniverDay(strToday);
		}else {
			vo.setCenterAnniverDay(vo.getCenterAnniverDay());
		}	
		AnnDay = vo.getCenterAnniverDay();
	    centerInfo.setCenterSearchDay(vo.getCenterAnniverDay());
	    
	    LOGGER.debug("vo.getCenterAnniverDay:" + vo.getCenterAnniverDay());
	    LOGGER.debug("vo.centerId:" + vo.getCenterId());
		
	    
	    
	    
		String centerTimeInfo = centerInfoManageService.selectCenterTimeInfo(centerInfo);
		//이전 날짜나 변경된 부분이 있으면 여기서 변경 하기 
		
		String[] arrayInfo = centerTimeInfo.split("/");
		String brodCode = null;
		LOGGER.debug("centerTimeInfo:" + centerTimeInfo);
		
		if (centerTimeInfo == null || centerTimeInfo.equals("") || arrayInfo[2].toString().equals("")){
			//관련 내용이 아무것도 없을때 
			response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('연동되어진 음원 콘텐츠가 없습니다. 확인 부탁 드립니다.'); history.go(-1);</script>");
            out.flush();            
		}else {
			if (arrayInfo[3].toString().equals("REG")){				
				/*UniSelectInfo fnBasic = new UniSelectInfo();
				fnBasic.setInResult("BROD_CODE");
				fnBasic.setInTable("TB_CENTERINFO");
				fnBasic.setInColumn("CENTER_ID");
				fnBasic.setInValue(vo.getCenterId());
				
				brodCode = uniSelectInfo.selectUniColumn(fnBasic);	*/
				brodCode = arrayInfo[2].toString();
			}else {
				brodCode = arrayInfo[2].toString();
			}
			
			
			model.addAttribute("centerCombo", centerInfoManageService.selectCenterBrodCombo(vo.getCenterId()));
			vo = brodContent.selectBrodContentInfo(brodCode);
			//추가 확인 
			if (vo == null){
				response.setContentType("text/html; charset=UTF-8");
	            PrintWriter out = response.getWriter();
	            out.println("<script>alert('연동되어진 음원 콘텐츠가 없습니다. 확인 부탁 드립니다.'); history.go(-1);</script>");
	            out.flush(); 
			}else {
				vo.setCenterId(centerInfo.getCenterId());
				vo.setCenterAnniverDay(AnnDay);
				
				//brodCode 확인후 챙기기 
				BrodAnniversary annBrod = new BrodAnniversary();
				annBrod.setBrodCode(brodCode);
				annBrod.setBrodDay(vo.getCenterAnniverDay());
				LOGGER.debug("BrodDay:"+ vo.getCenterAnniverDay());
				model.addAttribute("brodAnniver", anniverInfo.selectBrodAnniverLst(annBrod) );
				annBrod = null;
				model.addAttribute("regist", vo);
			}
			
			
		}
		
				
		//하단 내용 정리 하기 		
		}catch(Exception e){
			System.out.println(e.toString());			
		}
		
		
		
	    return "/backoffice/sub/brodManage/playCenter";
	    
	}
	
	
	//방송 리스트 보여 주기
	@RequestMapping(value="/backoffice/sub/brodManage/ContentBrodConfirm.do")
	@ResponseBody
	public String selectBrodLst(@ModelAttribute("loginVO") LoginVO loginVO
								   , BrodOrganizationVO vo
					               , HttpServletRequest request
								   , BindingResult bindingResult
							       , ModelMap model) throws Exception {		
							
		//우선 편성표 삭제
		
		String startTime = "1000";
		String endTime = "2400";
		String dayGubun = "REG";
		
		UniSelectInfo fnBasic = new UniSelectInfo();
		fnBasic.setInResult("b.CODE_DC");
		fnBasic.setInTable("TB_BRODSCHEDULE a, LETTCCMMNDETAILCODE b");
		fnBasic.setInColumn("a.BROD_INTERVAL = b.code AND a.brod_code");
		fnBasic.setInValue(vo.getBrodCode());
		//재생 간격 만들어 놓기 
		String timeInterval = uniSelectInfo.selectUniColumn(fnBasic);
		
		BrodContentDetailVO detail = new BrodContentDetailVO();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
	    String strToday = sdf.format(c1.getTime());	
		sdf = null;		
		BrodAnniversary annBrod = new BrodAnniversary();
		try{
			int ret = 0;
			if (vo.getCenterId() == null || vo.getCenterId() == ""){
				//일반 편성표 일때 처리
				
				ret =  brodOrgService.deleteBrodOrganization(vo.getBrodCode());
				
				
				
				annBrod.setBrodCode(vo.getBrodCode());						    
				annBrod.setBrodDay(strToday);
				
				//30/1시간  간격 업데이트 2시간 은 추후 처리 
				//시간별 하기 //나누기는 어떻게 처리 
				 List<BrodAnniversaryVO> annDetail = anniverInfo.selectBrodAnniverLst(annBrod);
				
				 
				 detail.setBrodCode(vo.getBrodCode());
				 detail.setIntervalSection("");
				 detail.setBrodDay(strToday);
				 
				 List< BrodContentDetailVO> brodContent = brodDetail.selectBrodContentDetailLst(detail);
				 
				 for ( int i = 0; i < (   (((Integer.parseInt(endTime) - Integer.parseInt(startTime)) * 60)/60)/100); i++  ){
					//10분 간격 처리 하기
					for (int a =0; a < (Integer.parseInt(timeInterval)/10); a++ ){
						if (timeInterval.equals("30")){
							for (int interval=0; interval < 2; interval++) {						
								brodReport(i , a , annDetail, brodContent , startTime, timeInterval, interval, "", "REG01", "20991231");	
							}
						}else {
						      	brodReport(i , a , annDetail, brodContent , startTime, timeInterval, 0, "","REG01", "20991231");
						}
								 	
					}	
					
				}
				
			}else {
				//센터 편성표 삭제 
				//기념일 있을때 기념일 날짜 조회후 삭제
				ret =  brodOrgService.deleteBrodOrganizationCenterId(vo);
				CenterInfo centerInfo = new CenterInfo();
				centerInfo.setCenterId(vo.getCenterId());
				if (vo.getContentPlayDay() == null){	    
				    centerInfo.setCenterSearchDay(strToday);	
				}else {
					centerInfo.setCenterSearchDay(vo.getContentPlayDay());	
				}
				
				String centerTimeInfo = centerInfoManageService.selectCenterTimeInfo(centerInfo);
				String[] arrayInfo = centerTimeInfo.split("/");
				startTime = arrayInfo[0].toString();
				endTime = arrayInfo[1].toString();
				dayGubun = arrayInfo[3].toString();
				String regGubun = null;
				String centerId = vo.getCenterId();
				String brodDay = null;
				//기간 정리 하기
				
				//기념일 삭제 부분은 다시 생각하기
				
				if (dayGubun.equals("REG")){
					regGubun = "REG01";
					brodDay = "20991231";
				}else {
					brodDay = centerInfo.getCenterSearchDay();
				}
				
				annBrod.setBrodCode(vo.getBrodCode());						    
				annBrod.setBrodDay(strToday);
				List<BrodAnniversaryVO> annDetail = anniverInfo.selectBrodAnniverLst(annBrod);
				detail.setBrodCode(vo.getBrodCode());
				detail.setIntervalSection("");
				detail.setBrodDay(strToday);
				List< BrodContentDetailVO> brodContent = brodDetail.selectBrodContentDetailLst(detail);
				 
				for ( int i = 0; i < (   (((Integer.parseInt(endTime) - Integer.parseInt(startTime)) * 60)/60)/100); i++  ){
					//10분 간격 처리 하기
					for (int a =0; a < (Integer.parseInt(timeInterval)/10); a++ ){
						if (timeInterval.equals("30")){
							for (int interval=0; interval < 2; interval++) {						
								brodReport(i , a , annDetail, brodContent , startTime, timeInterval, interval, centerId, regGubun, brodDay);	
							}
						}else {
						      	brodReport(i , a , annDetail, brodContent , startTime, timeInterval, 0, centerId,regGubun, brodDay);
						}		 
					}					
				}
				
				annDetail = null;
				brodContent = null;
				
				//스케줄 아이디 등록 또는 업데이트
				BrodScheduleInfo schSearch = new BrodScheduleInfo();
				if (dayGubun.equals("REG")){
					
					schSearch.setBrodCode(vo.getBrodCode());
					schSearch.setCenterId(centerId);
					schSearch.setBrodDay(brodDay);
					schSearch.setCreateCheck("Y");
					
					if ( brodSchedule.selectBrodScheduleCnt(schSearch) > 0 ){
						//날짜 찾아서 입력
						brodSchedule.updateBrodScheduleCenter(schSearch);
					}else {
						brodSchedule.insertBrodSchedule(schSearch);
					}	
				}else {
					//기념일 날짜 조회후 업데이트 또는 인서트 하기 
					schSearch.setBrodCode(vo.getBrodCode());
					schSearch.setCenterId(centerId);
					schSearch.setBrodDay(brodDay);
					
					String brodSplit = brodSchedule.selectBrodScheduleAnniScheduleSeqDay(schSearch);
					schSearch.setCreateCheck("Y");
					
					if (brodSplit != null){
						String[] brodInfoLst = brodSplit.substring(1).split("/");					
						schSearch.setScheduleSeq(brodInfoLst[0].toString());
						brodSchedule.updateBrodSchedule(schSearch);
					}else {
						brodSchedule.insertBrodSchedule(schSearch);
					}
				}			
				schSearch = null;
				
			}
			annBrod = null;
			return "O";
		}catch (Exception e){
			LOGGER.debug("selectBrodLst Error:" + e.toString());			
			return "F";
		}
		 		
	}	
	//편성표 저장
	private int brodReport(int i , int a , List<BrodAnniversaryVO> annDetail, List< BrodContentDetailVO> brodContent , String startTime, String timeInterval, int forGubun, String centerId , String brodGubun, String conPlayDay){
 
		
		
		int totalTime = 0;
		int ret = 0;
		BrodOrganization org = new   BrodOrganization();
		for (int an = 0; an < annDetail.size(); an++) {
			if (!annDetail.get(an).getAnniversaryGubun().equals("ANNGUBUN01") 
					&& annDetail.get(an).getAnniversaryTime().equals(
							egovframework.let.utl.fcc.service.EgovStringUtil.lenReplace( 
									String.valueOf((Integer.parseInt(startTime.substring(0,2)) + (i))), 2)+""+
									egovframework.let.utl.fcc.service.EgovStringUtil.lenReplace(String.valueOf(a*10),2))
		    		&& forGubun == 0
		    	){
				
				//특정 시간 방송 먼저
				
				
				
				
				org.setBrodCode(annDetail.get(an).getBrodCode());
				org.setAtchFileId(annDetail.get(an).getAtchFileId());
				org.setBrodTime(egovframework.let.utl.fcc.service.EgovStringUtil.lenReplace( String.valueOf((Integer.parseInt(startTime.substring(0,2)) + (i*1))), 2)  
						            + ":" + a +""+ egovframework.let.utl.fcc.service.EgovStringUtil.secToMinTimeChart(String.valueOf(totalTime)) );
				org.setBrodGubun(brodGubun);
				org.setContentPlayDay(conPlayDay);
				org.setBrodSeq("0");
				org.setBrodAnnSeq(annDetail.get(an).getBrodAnnSeq());
				org.setCenterId(centerId);
				
				try {
					//ret = brodOrgService.insertBrodOrganization(org);
					if (org.getBrodTime().length() < 9){
						  ret = brodOrgService.insertBrodOrganization(org);
					}
				} catch (Exception e) {
					LOGGER.debug("brodReport error:" + e.toString() );
				}
				
				
		    	totalTime += Integer.parseInt(annDetail.get(an).getPlayTime());
		    	
		    }else if (!annDetail.get(an).getAnniversaryGubun().equals("ANNGUBUN01") 
					  && annDetail.get(an).getAnniversaryTime().equals(egovframework.let.utl.fcc.service.EgovStringUtil.lenReplace( 
									String.valueOf((Integer.parseInt(startTime.substring(0,2)) + (i))), 2)+""+egovframework.let.utl.fcc.service.EgovStringUtil.lenReplace(String.valueOf((a+3)*10),2))
		    		&& forGubun == 1
		    	){
				
				//특정 시간 방송 먼저
				
			
				
				org.setBrodCode(annDetail.get(an).getBrodCode());
				org.setAtchFileId(annDetail.get(an).getAtchFileId());
				org.setBrodTime(egovframework.let.utl.fcc.service.EgovStringUtil.lenReplace( String.valueOf((Integer.parseInt(startTime.substring(0,2)) + (i*1))), 2)  
						            + ":" + a +""+ egovframework.let.utl.fcc.service.EgovStringUtil.secToMinTimeChart(String.valueOf(totalTime)) );
				org.setBrodGubun(brodGubun);
				org.setContentPlayDay(conPlayDay);
				org.setBrodSeq("0");
				org.setBrodAnnSeq(annDetail.get(an).getBrodAnnSeq());
				org.setCenterId(centerId);
				
				try {
					//ret = brodOrgService.insertBrodOrganization(org);
					if (org.getBrodTime().length() < 9){
						  ret = brodOrgService.insertBrodOrganization(org);
					}
				} catch (Exception e) {
					LOGGER.debug("brodReport error:" + e.toString() );
				}
				
		    	totalTime += Integer.parseInt(annDetail.get(an).getPlayTime());
		    	
		    }else if (annDetail.get(an).getAnniversaryGubun().equals("ANNGUBUN01") && 
		    		  i == 0 && 
		    		  annDetail.get(an).getAnniversaryTime().equals( String.valueOf(a*10)) && 
		    		  forGubun == 0 ){
		    	//나누기 없을때
		    	
             
                
                org.setBrodCode(annDetail.get(an).getBrodCode());
				org.setAtchFileId(annDetail.get(an).getAtchFileId());
				org.setBrodTime(egovframework.let.utl.fcc.service.EgovStringUtil.lenReplace( String.valueOf((Integer.parseInt(startTime.substring(0,2)) + (i*1))), 2)  
						            + ":" + a +""+ egovframework.let.utl.fcc.service.EgovStringUtil.secToMinTimeChart(String.valueOf(totalTime)) );
				org.setBrodGubun(brodGubun);
				org.setContentPlayDay(conPlayDay);
				org.setBrodSeq("0");
				org.setBrodAnnSeq(annDetail.get(an).getBrodAnnSeq());
				org.setCenterId(centerId);
				
				try {
					if (org.getBrodTime().length() < 9){
						ret = brodOrgService.insertBrodOrganization(org);	
					}
					
				} catch (Exception e) {
					LOGGER.debug("brodReport error:" + e.toString() );
				}
                
		    	totalTime += Integer.parseInt(annDetail.get(an).getAnniversaryTime());
		    }
			else if (annDetail.get(an).getAnniversaryGubun().equals("ANNGUBUN01") && i > 0 && 
					( i% (Integer.parseInt(annDetail.get(an).getAnniversaryTime())/ 60) ) == 0	&& 
					((a*10) == Integer.parseInt(annDetail.get(an).getAnniversaryStartTime())) 
					&& forGubun == 0
					){
		    	//이후 나누기 생각						
		    	
		    	org.setBrodCode(annDetail.get(an).getBrodCode());
				org.setAtchFileId(annDetail.get(an).getAtchFileId());
				org.setBrodTime(egovframework.let.utl.fcc.service.EgovStringUtil.lenReplace( String.valueOf((Integer.parseInt(startTime.substring(0,2)) + (i*1))), 2)  
						            + ":" + a +""+ egovframework.let.utl.fcc.service.EgovStringUtil.secToMinTimeChart(String.valueOf(totalTime)) );
				org.setBrodGubun(brodGubun);
				org.setContentPlayDay(conPlayDay);
				org.setBrodSeq("0");
				org.setBrodAnnSeq(annDetail.get(an).getBrodAnnSeq());
				org.setCenterId(centerId);
				
				try {
					if (org.getBrodTime().length() < 9){
					 ret = brodOrgService.insertBrodOrganization(org);
					}
				} catch (Exception e) {
					LOGGER.debug("brodReport error:" + e.toString() );
				}
				
		    	totalTime += Integer.parseInt(annDetail.get(an).getPlayTime());
		    	
		    }else if (annDetail.get(an).getAnniversaryGubun().equals("ANNGUBUN01") && i == 0 && annDetail.get(an).getAnniversaryTime().equals( String.valueOf((a+3)*10)) && forGubun == 1 ){
		    	//나누기 없을때
		    	
              
		    	org.setBrodCode(annDetail.get(an).getBrodCode());
				org.setAtchFileId(annDetail.get(an).getAtchFileId());
				org.setBrodTime(egovframework.let.utl.fcc.service.EgovStringUtil.lenReplace( String.valueOf((Integer.parseInt(startTime.substring(0,2)) + (i*1))), 2)  
						            + ":" + a +""+ egovframework.let.utl.fcc.service.EgovStringUtil.secToMinTimeChart(String.valueOf(totalTime)) );
				org.setBrodGubun(brodGubun);
				org.setContentPlayDay(conPlayDay);
				org.setBrodSeq("0");
				org.setBrodAnnSeq(annDetail.get(an).getBrodAnnSeq());
				org.setCenterId(centerId);
				
				try {
					if (org.getBrodTime().length() < 9){
				  	   ret = brodOrgService.insertBrodOrganization(org);
					}
				} catch (Exception e) {
					LOGGER.debug("brodReport error:" + e.toString() );
				}
		    	totalTime += Integer.parseInt(annDetail.get(an).getPlayTime());
		    }
			else if (annDetail.get(an).getAnniversaryGubun().equals("ANNGUBUN01") && i > 0 && 
					( i% (Integer.parseInt(annDetail.get(an).getAnniversaryTime())/ 60) ) == 0	&& (((a+3)*10) == Integer.parseInt(annDetail.get(an).getAnniversaryStartTime())) 
					&& forGubun == 1
					){
		    	//이후 나누기 생각						
		    
				
				org.setBrodCode(annDetail.get(an).getBrodCode());
				org.setAtchFileId(annDetail.get(an).getAtchFileId());
				org.setBrodTime(egovframework.let.utl.fcc.service.EgovStringUtil.lenReplace( String.valueOf((Integer.parseInt(startTime.substring(0,2)) + (i*1))), 2)  
						            + ":" + a +""+ egovframework.let.utl.fcc.service.EgovStringUtil.secToMinTimeChart(String.valueOf(totalTime)) );
				org.setBrodGubun(brodGubun);
				org.setContentPlayDay(conPlayDay);
				org.setBrodSeq("0");
				org.setBrodAnnSeq(annDetail.get(an).getBrodAnnSeq());
				org.setCenterId(centerId);
				
				try {
					if (org.getBrodTime().length() < 9){
					  ret = brodOrgService.insertBrodOrganization(org);
					}
				} catch (Exception e) {
					LOGGER.debug("brodReport error:" + e.toString() );
				}
		    	totalTime += Integer.parseInt(annDetail.get(an).getPlayTime());
		    }
			
		}		
		
		
		
		
		for (int aa = 0; aa < brodContent.size(); aa++) {
			
			
			if (brodContent.get(aa).getIntervalSection().equals(egovframework.let.utl.fcc.service.EgovStringUtil.lenReplace(String.valueOf( a*10), 3))){
				//기념일 정리 해서 넣기 
				if (totalTime <= 600){
					if (timeInterval.equals("30")){						
						//구분을 넣어야 함 1시간 단위 설정시
						if (forGubun == 0){
							//데이터 베이스 넣기
							
							org.setBrodCode(brodContent.get(aa).getBrodCode());
							org.setAtchFileId(brodContent.get(aa).getAtchFileId());
							org.setBrodTime(egovframework.let.utl.fcc.service.EgovStringUtil.lenReplace( 
											String.valueOf((Integer.parseInt(startTime.substring(0,2)) + (i*1))), 2)  + ":" + a +""+ 
											egovframework.let.utl.fcc.service.EgovStringUtil.secToMinTimeChart(String.valueOf(totalTime)) );
							org.setBrodGubun(brodGubun);
							org.setContentPlayDay(conPlayDay);
							org.setBrodSeq(brodContent.get(aa).getBrodSeq());
							org.setBrodAnnSeq("0");
							org.setCenterId(centerId);
							
							try {
								ret = brodOrgService.insertBrodOrganization(org);
							} catch (Exception e) {
								LOGGER.debug("brodReport error:" + e.toString() );
							}
							
						}else {
							//데이터 베이스 넣기 
							
							org.setBrodCode(brodContent.get(aa).getBrodCode());
							org.setAtchFileId(brodContent.get(aa).getAtchFileId());
							org.setBrodTime(egovframework.let.utl.fcc.service.EgovStringUtil.lenReplace( 
											String.valueOf(
													(
													Integer.parseInt(startTime.substring(0,2)
														) + (i*1)
													)
											 ), 2)  + ":" + (a+3) +""+ 
											egovframework.let.utl.fcc.service.EgovStringUtil.secToMinTimeChart(String.valueOf(totalTime)));
							
							org.setBrodGubun(brodGubun);
							org.setContentPlayDay(conPlayDay);
							org.setBrodSeq(brodContent.get(aa).getBrodSeq());
							org.setBrodAnnSeq("0");
							org.setCenterId(centerId);
							
							try {
								ret = brodOrgService.insertBrodOrganization(org);
							} catch (Exception e) {
								LOGGER.debug("brodReport error:" + e.toString() );
							}
						}						
						totalTime +=  Integer.parseInt(brodContent.get(aa).getPlayTime());	
					}else {
						//데이터 베이스 넣기 
						System.out.println("정규시간"+egovframework.let.utl.fcc.service.EgovStringUtil.lenReplace( 
								String.valueOf((Integer.parseInt(
										startTime.substring(0,2)
										)
										+ (i*1)
										)
										), 2)  + ":" + a +""+ 
								egovframework.let.utl.fcc.service.EgovStringUtil.secToMinTimeChart(String.valueOf(totalTime)) +":"+ brodContent.get(aa).getAtchFileId() );
						
						org.setBrodCode(brodContent.get(aa).getBrodCode());
						org.setAtchFileId(brodContent.get(aa).getAtchFileId());
						org.setBrodTime(egovframework.let.utl.fcc.service.EgovStringUtil.lenReplace( 
										String.valueOf((Integer.parseInt(startTime.substring(0,2)) + (i*1))), 2)  + ":" + a +""+ 
										egovframework.let.utl.fcc.service.EgovStringUtil.secToMinTimeChart(String.valueOf(totalTime)));						
						org.setBrodGubun(brodGubun);
						org.setContentPlayDay(conPlayDay);
						org.setBrodSeq(brodContent.get(aa).getBrodSeq());
						org.setBrodAnnSeq("0");
						org.setCenterId(centerId);
						
						try {
							ret = brodOrgService.insertBrodOrganization(org);
						} catch (Exception e) {
							System.out.println("e:"+e.toString());
							LOGGER.debug("brodReport error:" + e.toString() );
						}
						
						totalTime +=  Integer.parseInt(brodContent.get(aa).getPlayTime());	
					}
				
				}else {
					break;
				}
				
			}	
			
			
		}
		org =null;
		return ret;
	}
	
	
}
