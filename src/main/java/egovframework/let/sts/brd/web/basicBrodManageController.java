package egovframework.let.sts.brd.web;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.let.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;
import egovframework.let.sym.cnt.service.CenterInfoManageService;
import egovframework.let.sym.cnt.service.CenterInfoVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
//기초




import egovframework.let.sts.brd.service.BasciBrodFileInfo;
import egovframework.let.sts.brd.service.BasciBrodFileInfoManageService;
import egovframework.let.sts.brd.service.BasciBrodFileInfoVO;
import egovframework.let.sts.brd.service.BasciBrodInfoManageService;
import egovframework.let.sts.brd.service.BasciBrodInfoVO;
import egovframework.let.sts.brd.service.BasciBrodInfo;
import egovframework.let.sts.brd.service.BasicBrodFileIntervalInfoManageService;
import egovframework.let.sts.brd.service.BasicBrodFileIntervalInfoVO;
import egovframework.let.sts.brd.service.BasicBrodScheduleInfo;
import egovframework.let.sts.brd.service.BasicBrodScheduleInfoManageService;
import egovframework.let.sts.brd.service.BasicBrodScheduleInfoVO;
import egovframework.let.sts.brd.service.BasicFileGroupInfoManageService;
import egovframework.let.sts.brd.service.BasicFileGroupInfoVO;
import egovframework.let.sts.brd.service.BasicFileGroupPlayInfoManageService;
import egovframework.let.sts.brd.service.BasicFileGroupPlayInfoVO;
import egovframework.let.sts.brd.service.BrodContentInfo;
import egovframework.let.sts.brd.service.BrodContentInfoManageService;
import egovframework.let.sts.brd.service.BrodScheduleInfo;
import egovframework.let.sts.brd.service.BrodScheduleInfoVO;
import egovframework.let.sts.cnt.service.ContentFileInfoManageService;
import egovframework.let.sts.cnt.service.ContentFileInfoVO;

@Controller
public class basicBrodManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(basicBrodManageController.class);
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    
    @Resource(name="CmmnDetailCodeManageService")
    private EgovCcmCmmnDetailCodeManageService cmmnDetailCodeManageService;
    
    @Resource(name="egovMessageSource")
	protected EgovMessageSource egovMessageSource;
    
    @Resource(name="BasciBrodInfoManageService")
    private BasciBrodInfoManageService basicService;
    
    @Resource(name="ContentFileInfo")	
	private ContentFileInfoManageService conFileService;
    
    @Resource(name="BasciBrodFileInfoManageService")
    private BasciBrodFileInfoManageService basicFileService;
    
    
    @Resource(name="BasicBrodScheduleInfoManageService")
    private BasicBrodScheduleInfoManageService schService;
    
    
    @Resource(name="BrodContentInfoService")
    private BrodContentInfoManageService brodService;
    
    @Resource(name="CenterInfoManageService")
    private CenterInfoManageService centerService;
    
    @Resource(name="BasicFileGroupInfoManageService")
    private BasicFileGroupInfoManageService groupService;
    
    @Resource(name="BasicBrodFileIntervalInfoManageService")
    private BasicBrodFileIntervalInfoManageService fileInterval;
    
	@Resource(name="BasicFileGroupPlayInfoManageService")
	private BasicFileGroupPlayInfoManageService groupPlayInfo;
	
	@Resource(name="CenterInfoManageService")
	private CenterInfoManageService centerInfoManageService;
	
    @Autowired
	private DefaultBeanValidator beanValidator;
	
	
	@RequestMapping("/backoffice/sub/brodManage/brodBasic.do")
	public String selectBrodBasicList(@ModelAttribute("loginVO") LoginVO loginVO
										   , @ModelAttribute("searchVO") BasciBrodInfoVO searchVO
							               , HttpServletRequest request
										   , BindingResult bindingResult
									       , ModelMap model)throws Exception {
		
		
		model.addAttribute("regist", searchVO);
		searchVO.setPageUnit(searchVO.getPageUnit());		
		searchVO.setPageSize(propertiesService.getInt("pageSize"));
		
		
		
		/** pageing */       
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		try{
		model.addAttribute("resultList",  basicService.selectBasciBrodLst(searchVO) );
		}catch (Exception e){
		System.out.println("error:"+e.toString());
		}
		int totCnt = basicService.selectBasciBrodPageCnt(searchVO);       
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("totalCnt", totCnt);		       
		model.addAttribute("regist", searchVO);		    
		
		return "/backoffice/sub/brodManage/brodBasicList";
		
	}
	@RequestMapping("/backoffice/sub/brodManage/brodBasicDel.do")
	@ResponseBody
	public String basicBrodDel(HttpServletRequest request) throws Exception{
		
		String delBasciSeq = request.getParameter("delBasciSeq") != null ? request.getParameter("delBasciSeq") : "";
		
		try{
			BasicBrodScheduleInfoVO vo = new BasicBrodScheduleInfoVO();
			if (delBasciSeq.contains(",")){
				String[] delBasciSeqs = delBasciSeq.split(",");
				for (int i =0; i < delBasciSeqs.length; i++){
					//먼저 배포 파일 막기 					
					vo.setCreateCheck("E");
					vo.setPreCreateCheck("Y");
					vo.setBasicCode(delBasciSeqs[i].toString() );			
					schService.updateBasicBrodScheduleCenterStateChange(vo);
					//센터 빈값으로 처리 					
					schService.updateBasicCodeCenterReset(delBasciSeqs[i].toString());					
					//연계된 파일 삭제 
					basicFileService.deleteBasciBrodBasicCode(delBasciSeqs[i].toString());
					//기초 음원 삭제
					basicService.deleteBasciBrod(delBasciSeqs[i].toString());					
				}
			}else {
				vo.setCreateCheck("E");
				vo.setPreCreateCheck("Y");
				vo.setBasicCode(delBasciSeq );			
				schService.updateBasicBrodScheduleCenterStateChange(vo);
				//센터 빈값으로 처리 					
				schService.updateBasicCodeCenterReset(delBasciSeq);					
				//연계된 파일 삭제 
				basicFileService.deleteBasciBrodBasicCode(delBasciSeq);
				//기초 음원 삭제
				basicService.deleteBasciBrod(delBasciSeq);
			}
			return "T";
		}catch (Exception e){
			LOGGER.debug("basicBrodDel:" + e.toString() );			
			return "F";
		}
		
		
	}
	@RequestMapping("/backoffice/sub/brodManage/brodBasicUpdate.do")
	@ResponseBody 
	public String basicBrodUpdate(@ModelAttribute("loginVO") LoginVO loginVO
										   , @ModelAttribute("vo") BasciBrodInfo vo
							               , HttpServletRequest request
										   , BindingResult bindingResult
									       , ModelMap model)throws Exception {
				
		int ret = 0;
	    if (vo.getMode().equals("Ins")){
	    	ret = basicService.insertBasciBrod(vo);
	    	vo.setBasicCode(basicService.selectMaxBrodCode());
	    	
	    }else if (vo.getMode().equals("Cpy")){
	    	String cpbasicCode = vo.getBasicCode();
	    		    	
	    	vo.setBasicCode(basicService.selectBasciCode());
	    	vo.setBasicCodePre(cpbasicCode);	    	
	    	ret = basicService.insertBasciBrodCopy(vo);
	    	
	    	//복사항 음원 인서트 하기 
	    	BasciBrodFileInfoVO fileVo = new BasciBrodFileInfoVO();
	    	fileVo.setBasicCodeCp(cpbasicCode);
	    	fileVo.setBasicCode(vo.getBasicCode());
	    	basicFileService.insertBasciBrodFileCopy(fileVo);
	    	
	    } else {
	    	ret = basicService.updateBasciBrod(vo);
	    }
	    LOGGER.debug("basicCode:"+vo.getBasicCode());
	    return ret > 0 ? vo.getBasicCode() : "F";
	}
	@RequestMapping("/backoffice/sub/brodManage/brodBasicCombo.do")
	public ModelAndView basicCombo()throws Exception{
		ModelAndView model = new ModelAndView("jsonView");		
		return  model.addObject("brodInfoLst", basicService.selectBasicBrodCombo());
	}
	
	
	
	@RequestMapping("/backoffice/sub/brodManage/brodBasicCenterList.do")
	public ModelAndView basicCenterlist(HttpServletRequest request)throws Exception{
		
		String centerGubun = request.getParameter("centerGubun") != null ? request.getParameter("centerGubun") : "";
		
		ModelAndView model = new ModelAndView("jsonView");		
		return  model.addObject("centerInfo", schService.selectBasicBrodScheduleCheckList(centerGubun));
	}

	@RequestMapping("/backoffice/sub/brodManage/brodBasicCenterUpdateS.do")
	@ResponseBody
	public String basicCenterUpdateS(HttpServletRequest request)throws Exception{
		
		
		String basicCode = request.getParameter("basicCode") != null ? request.getParameter("basicCode") : "";
		try{
			BasicBrodScheduleInfoVO vo = new BasicBrodScheduleInfoVO();
			vo.setCreateCheck("Y");
			vo.setBasicCode(basicCode);			
			schService.updateBasicBrodScheduleCenterStateChange(vo);
			return "S";
		}catch (Exception e){
			LOGGER.debug("basicCenterUpdate error:" + e.toString());
			return "F";
		}
		
		
	}
	@RequestMapping("/backoffice/sub/brodManage/brodBasicCenterUpdate.do")
	@ResponseBody
	public String basicCenterUpdate(HttpServletRequest request)throws Exception{
		
		String delBasciSeq = request.getParameter("CenterSeq") != null ? request.getParameter("CenterSeq") : "";
		String basicCode = request.getParameter("basicCode") != null ? request.getParameter("basicCode") : "";
		String checkValue = request.getParameter("checkValue") != null ? request.getParameter("checkValue") : "";
		
		/*LOGGER.debug("CenterSeq:" + delBasciSeq);
		LOGGER.debug("basicCode:" + basicCode);
		LOGGER.debug("checkValue:" + checkValue);*/
		
		
		BasicBrodScheduleInfoVO vo = new BasicBrodScheduleInfoVO();
		try{
			
			BrodContentInfo brodInfo = new BrodContentInfo();
			
			if (delBasciSeq.contains(",")){			
				String[] delBasciSeqs = delBasciSeq.substring(1).split(",");
				for (int i =0; i < delBasciSeqs.length; i++){
					vo.setCenterId(delBasciSeqs[i].toString().trim());
					vo.setBasicCode(basicCode);
					
					//2019.10.07 JDH 기본음원을 적용할떄 이미 다른 음원에 적용되있을경우 해당 음원스케줄 삭제
					schService.deleteBasicBrodScheduleOther(vo);
					
					brodInfo.setBrodCode(centerService.selectCenterInfoBrod(vo.getCenterId()));
					brodInfo.setCenterId(vo.getCenterId());
					
					if (checkValue.equals("Y")){
						
						vo.setCreateCheck("E");
						//기존 음원 업데이트 
						schService.updateBasicBrodScheduleCenterE(vo);					
						vo.setCreateCheck("Y");
						vo.setBasicCode(basicCode);
						//스케줄 업데이트
						schService.insertBasciBrodSchedule(vo);
						//센터 업데이트
						brodInfo.setBasicBrodCode(basicCode);
						
						
						/*schService.updateBasicCodeCenterUpdate(vo);
						//여기 부분 수정 필요 
*/						
						
					}else {
						schService.deleteBasicBrodScheduleCenter(vo);
						/*vo.setCreateCheck("E");
						schService.updateBasicBrodScheduleCenterE(vo);	
						vo.setBasicCode("");
						brodInfo.setBasicBrodCode("");*/
						
						
						// schService.updateBasicCodeCenterUpdate(vo);	
						
					}
					
					
					if(brodInfo.getBrodCode() != null){
						brodService.updateBrodContentBasicFileInfo(brodInfo);
					}
				}					
			}else {
				vo.setCenterId(delBasciSeq.trim());	
				vo.setBasicCode(basicCode);
				
				//2019.10.07 JDH 기본음원을 적용할떄 이미 다른 음원에 적용되있을경우 해당 음원스케줄 삭제
				schService.deleteBasicBrodScheduleOther(vo); 
				
				brodInfo.setBrodCode(centerService.selectCenterInfoBrod(vo.getCenterId()));
				brodInfo.setCenterId(vo.getCenterId());
				if (checkValue.equals("Y")){				
					vo.setCreateCheck("E");
					//기존 음원 업데이트
					
					schService.updateBasicBrodScheduleCenterE(vo);					
					vo.setCreateCheck("Y");
					vo.setBasicCode(basicCode);
					
					schService.insertBasciBrodSchedule(vo);
					brodInfo.setBasicBrodCode(basicCode);
				}else {
					schService.deleteBasicBrodScheduleCenter(vo);
					/*brodInfo.setBasicBrodCode("");
					vo.setCreateCheck("E");
					schService.updateBasicBrodScheduleCenterE(vo);	
					vo.setBasicCode("");*/						
				}
				
				if(brodInfo.getBrodCode() != null){
					brodService.updateBrodContentBasicFileInfo(brodInfo);
				}
				
				// schService.updateBasicCodeCenterUpdate(vo);
			}			
			basicService.updateBasciBrodCnt(basicCode);
			return "S";
		}catch(Exception e){
			LOGGER.debug("basicCenterUpdate error:" + e.toString());
			return "F";
		}			
	}
	@RequestMapping("/backoffice/sub/brodManage/brodBasicScheduleUpdateReset.do")
	@ResponseBody
	public String basicScheduleReset(@ModelAttribute("vo") BasicBrodScheduleInfoVO vo
							              , HttpServletRequest request
										  , BindingResult bindingResult
									      , ModelMap model)throws Exception{
		
		//Y로 되어 있는 모든것을 다 과거로 변경 		
		try{
			//과거 데이트 종료 
			schService.updateBasicBrodScheduleCenterStateChange(vo);
			//신규 생성
			schService.insertBasciBrodScheduleDistribute(vo.getBasicCode());
			//기존 삭제
			schService.deleteBasicBrodSchedule(vo);
			return  "T";
		}catch(Exception e){
			return  "F";
		}
		
	}
	
	
	//스케줄 페이징 관련 
	@RequestMapping("/backoffice/sub/brodManage/brodBasicScheduleList.do")
	public ModelAndView selectBrodScheduleList( HttpServletRequest request)throws Exception{
		
		String schPageIndex = request.getParameter("schPageIndex") != null ? request.getParameter("schPageIndex") : "";
		String schPageSize = request.getParameter("schPageSize") != null ? request.getParameter("schPageSize") : "";
		
		String centerId = request.getParameter("schCenterId") != null ? request.getParameter("schCenterId") : "";		
		String basicCode = request.getParameter("basicCode") != null ? request.getParameter("basicCode") : "";
		String createCheck = request.getParameter("schCreateCheck") != null ? request.getParameter("schCreateCheck") : "";
		String didDowncheck = request.getParameter("schDidDownCheck") != null ? request.getParameter("schDidDownCheck") : "";
		
		BasicBrodScheduleInfoVO searchVO = new BasicBrodScheduleInfoVO();
		searchVO.setPageIndex(Integer.valueOf(schPageIndex));
		searchVO.setPageUnit(Integer.valueOf(schPageSize));
		searchVO.setPageSize(Integer.valueOf(schPageSize));
		
		searchVO.setCenterId(centerId);
		searchVO.setBasicCode(basicCode);
		searchVO.setCreateCheck(createCheck);
		searchVO.setDidDowncheck(didDowncheck);
		
		
		
		/** 페이징 **/
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		
		
		ModelAndView model = new ModelAndView("jsonView");
		try{
			
			
			
			int totCnt = schService.selectBasciBrodScheduleLstCnt(searchVO);       
			paginationInfo.setTotalRecordCount(totCnt);
			
			Map resultMap = new HashMap();
			
			resultMap.put("schTotCnt", totCnt);
			resultMap.put("schList", schService.selectBasciBrodScheduleLst(searchVO));
			resultMap.put("paging", paginationInfo);
			
			
			model.addObject("resultMap", resultMap );
			
			return model;	
		}catch (Exception e){
			LOGGER.debug("selectBrodScheduleList error:" + e.toString());
			return null;
		}
	}
	@RequestMapping("/backoffice/sub/brodManage/brodBasicFileCheckUpdate.do")
	public ModelAndView basicAllLeftUpdate( HttpServletRequest request)throws Exception{
		
		
		ModelAndView model_A = new ModelAndView("jsonView");
		try{
			String delBasciSeq = request.getParameter("delBasciSeq") != null ? request.getParameter("delBasciSeq") : "";
			String basicCode = request.getParameter("basicCode") != null ? request.getParameter("basicCode") : "";
			String basicStartDay = request.getParameter("basicStartDay") != null ? request.getParameter("basicStartDay") : "";
			String basicEndDay = request.getParameter("basicEndDay") != null ? request.getParameter("basicEndDay") : "";
			/** 페이징 **/
			
			String pageIndex = request.getParameter("pageIndex") != null ? request.getParameter("pageIndex") : "";
			String pageSize = request.getParameter("pageSize") != null ? request.getParameter("pageSize") : "";
			String searchCondition = request.getParameter("searchCondition") != null ? request.getParameter("searchCondition") : "";
			String searchKeyword = request.getParameter("searchKeyword") != null ? request.getParameter("searchKeyword") : "";
			
            String[] delSeq = delBasciSeq.split(",");
						
			BasciBrodFileInfo vo = new BasciBrodFileInfo();
			vo.setBasicCode(basicCode);
			if (delBasciSeq.contains(",")){
				for (int i =0; i < delSeq.length; i++)
				{
					vo.setAtchFileId(delSeq[i].toString());
					vo.setBasicOrder("10");
					vo.setBasicStartDay(basicStartDay);
					vo.setBasicEndDay(basicEndDay);
					basicFileService.insertBasciBrodFile(vo);		
				}	
			}else {
				if (!delBasciSeq.equals("")){
					vo.setAtchFileId(delBasciSeq);
					vo.setBasicOrder("10");
					vo.setBasicStartDay(basicStartDay);
					vo.setBasicEndDay(basicEndDay);
					basicFileService.insertBasciBrodFile(vo);
				}
			}
			
			//배포&다운 -> 'N' 변경 
			schService.updateBasicBrodScheduleState(basicCode);
			
			ContentFileInfoVO fileVO = new ContentFileInfoVO();
			
			fileVO.setBasicCode(basicCode);
			fileVO.setPageUnit( Integer.valueOf(pageSize) );		
			fileVO.setPageSize( Integer.valueOf(pageSize) );
			fileVO.setPageIndex(Integer.valueOf(pageIndex));			
			fileVO.setSearchCondition(searchCondition);
			fileVO.setSearchKeyword(searchKeyword);
						
			
			return model_A.addObject("resultMap", basicPagingInfo(fileVO));
		}catch (Exception e){
			return null;
		}
		
	}
	@RequestMapping("/backoffice/sub/brodManage/brodBasicFileAllDel.do")
	public ModelAndView basicAllDelRight( HttpServletRequest request)throws Exception{
				
		try{
			String delBasciSeq = request.getParameter("delBasciSeq") != null ? request.getParameter("delBasciSeq") : "";
			String basicCode = request.getParameter("basicCode") != null ? request.getParameter("basicCode") : "";
			String pageIndex = request.getParameter("pageIndex") != null ? request.getParameter("pageIndex") : "";
			String pageSize = request.getParameter("pageSize") != null ? request.getParameter("pageSize") : "";
			String searchCondition = request.getParameter("searchCondition") != null ? request.getParameter("searchCondition") : "";
			String searchKeyword = request.getParameter("searchKeyword") != null ? request.getParameter("searchKeyword") : "";
		
			int ret = 0;
			
			if (delBasciSeq.contains(",")){
				String[] delSeq = delBasciSeq.substring(1).split(",");
				for (int i =0; i < delSeq.length; i++)
				{
				  ret = basicFileService.deleteBasciBrodFile(delSeq[i].toString()  );		
				}	
			}else {
				if (!delBasciSeq.equals("")){
					ret = basicFileService.deleteBasciBrodFile(delBasciSeq  );	
				}
				
			}
			
			//배포&다운 -> 'N' 변경 
			schService.updateBasicBrodScheduleState(basicCode);
				        		    			
			ContentFileInfoVO fileVO = new ContentFileInfoVO();
			fileVO.setBasicCode(basicCode);		
			fileVO.setPageUnit( Integer.valueOf(pageSize) );		
			fileVO.setPageSize( Integer.valueOf(pageSize) );
			fileVO.setPageIndex(Integer.valueOf(pageIndex));
		
			ModelAndView model_A = new ModelAndView("jsonView");			
			return model_A.addObject("resultMap", basicPagingInfo(fileVO)  );
			
		}catch(Exception e){
			LOGGER.debug("e:"+e.toString());
			return null;
		}
	}
	
	
	@RequestMapping("/backoffice/sub/brodManage/brodBasicFileUpdate.do")
	public ModelAndView basicUpdateRight( @ModelAttribute("vo") BasciBrodFileInfoVO vo			                                                    
							              , HttpServletRequest request
										  , BindingResult bindingResult
									      , ModelMap model)throws Exception{
		
		try{
			ModelAndView model_A = new ModelAndView("jsonView");
			boolean isUpdate = false;
			
			int ret = 0;
			if (vo.getFileGubun().equals("R")){
				ret = basicFileService.insertBasciBrodFile(vo);
			}else{				
				ret = basicFileService.deleteBasciBrodFile(vo.getBasicSeq());				
			}
			
			//배포&다운 -> 'N' 변경 
			schService.updateBasicBrodScheduleState(vo.getBasicCode());
			
			ContentFileInfoVO fileVO = new ContentFileInfoVO();
			fileVO.setBasicCode(vo.getBasicCode());		
			fileVO.setPageUnit( vo.getPageUnit());		
			fileVO.setPageSize( vo.getPageUnit() );
			fileVO.setPageIndex( vo.getPageIndex());			
			
			return model_A.addObject("resultMap", basicPagingInfo(fileVO) );
			
		}catch(Exception e){
			LOGGER.debug("basicUpdateRight Error:" + e.toString());
			return null;
		}
		
			
			
			
			
			
		//return basicFileService.insertBasciBrodFile(vo) > 0 ? "S" : "F";
		
	}
	
	//20200130 JDH Mapping명 중복
	/*@RequestMapping(value="/backoffice/sub/brodManage/basicDayInput.do")
	public String selectContentSpReg(@ModelAttribute("loginVO") LoginVO loginVO
									   , BasciBrodFileInfo vo
						               , HttpServletRequest request
									   , BindingResult bindingResult
								       , ModelMap model)throws Exception {
		
		model.addAttribute("regist", vo);
		return "/backoffice/popup/basicDayInput";
	}*/
	
	@RequestMapping("/backoffice/sub/brodManage/brodBasicFileList.do")
	public ModelAndView basicFileInfo(HttpServletRequest request)throws Exception{
		ModelAndView model = new ModelAndView("jsonView");
		String basicCode = request.getParameter("basicCode") != null ? request.getParameter("basicCode") : "";			
		return model.addObject("resultMap",  basicFileService.selectBasicBrodFileLst(basicCode) );		
	}
	private Map basicPagingInfo(ContentFileInfoVO  fileVO)throws Exception{
		
				
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(fileVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(fileVO.getPageUnit());
		paginationInfo.setPageSize(fileVO.getPageSize());		
		fileVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		fileVO.setLastIndex(paginationInfo.getLastRecordIndex());
		fileVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		
		Map resultMap = new HashMap();
		
		int totCnt = conFileService.selectBasicFilePageListByPaginationTotCnt_S(fileVO);       
		paginationInfo.setTotalRecordCount(totCnt);
		
		resultMap.put("paging", paginationInfo);
		resultMap.put("schList", conFileService.selectBasicFilePageListByPagination(fileVO));
		resultMap.put("atchFileLst", basicFileService.selectBasicBrodFileLst(fileVO.getBasicCode()) );
		
		return resultMap;
	}
	@RequestMapping("/backoffice/sub/brodManage/brodBasicDetail.do")
	public String selectBasicDetail(@ModelAttribute("loginVO") LoginVO loginVO
								   , @ModelAttribute("searchVO") BasciBrodInfoVO searchVO
					               , HttpServletRequest request
								   , BindingResult bindingResult
							       , ModelMap model)throws Exception {
		//기본 정보
		
		LOGGER.debug("basicCode:"+searchVO.getBasicCode());
		
		model.addAttribute("regist", basicService.selectBasciBrod(searchVO.getBasicCode()));
		//파일 리스트 
		ContentFileInfoVO fileVO = new ContentFileInfoVO();
		
		fileVO.setBasicCode(searchVO.getBasicCode());		
		fileVO.setPageUnit(searchVO.getPageUnit());		
		fileVO.setPageSize(propertiesService.getInt("pageSize"));
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(fileVO.getPageUnit());
		paginationInfo.setPageSize(fileVO.getPageSize());		
		fileVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		fileVO.setLastIndex(paginationInfo.getLastRecordIndex());
		fileVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		
		
		int totCnt = conFileService.selectBasicFilePageListByPaginationTotCnt_S(fileVO);       
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("totalCnt", totCnt);
		model.addAttribute("resultList",  conFileService.selectBasicFilePageListByPagination(fileVO) );
		//파일 리스트 정보 끝 부분 		
		model.addAttribute("resultListBasic", basicFileService.selectBasicBrodFileLst(searchVO.getBasicCode()) );
		
		return "/backoffice/sub/brodManage/brodBasicDetail";
		
	}
	
	@RequestMapping("/backoffice/sub/brodManage/brodBasicTimeDetail.do")
	public String selectBasicTimeDetail(@ModelAttribute("loginVO") LoginVO loginVO
									    , @ModelAttribute("searchVO") BasciBrodInfoVO searchVO
						                , HttpServletRequest request
									    , BindingResult bindingResult
								        , ModelMap model)throws Exception {
		//기본 정보
		
		model.addAttribute("regist", basicService.selectBasciBrod(searchVO.getBasicCode()));
		//groupinfo
		
		BasicFileGroupInfoVO groupInfo = new BasicFileGroupInfoVO();
		groupInfo.setBasicCode(searchVO.getBasicCode().trim());		
		groupInfo.setPageUnit(searchVO.getPageUnit());		
		groupInfo.setPageSize(propertiesService.getInt("pageSize"));
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(groupInfo.getPageUnit());
		paginationInfo.setPageSize(groupInfo.getPageSize());	
		groupInfo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		groupInfo.setLastIndex(paginationInfo.getLastRecordIndex());
		groupInfo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		List<BasicFileGroupInfoVO> fileGroupInfo = groupService.selectBasicGroupInfoLst(groupInfo);
		int totCnt = fileGroupInfo.size() >0 ? fileGroupInfo.get(0).getRnum() : 0;
		
		LOGGER.debug("totCnt:" + fileGroupInfo.size() + ":" + searchVO.getBasicCode().trim() + ":" + searchVO.getBasicCode().trim().length());
		
		paginationInfo.setTotalRecordCount(totCnt );
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("totalCnt", totCnt);
		model.addAttribute("resultList", fileGroupInfo);
		model.addAttribute("groupTimegubun", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT023"));
		
		
		return "/backoffice/sub/brodManage/brodBasicTimeDetail";
		
	}
	//시간 관련 정보  
	@RequestMapping("/backoffice/sub/brodManage/brodBasicTimeInfo.do")
	public ModelAndView selectBasicFileInfo( HttpServletRequest request)throws Exception {
			
		ModelAndView model = new ModelAndView("jsonView");
		try{
			String groupSeq = request.getParameter("groupSeq") != null ? request.getParameter("groupSeq") : "";
			
			if (!groupSeq.equals("")){
				model.addObject("status", "SUCCESS");
				model.addObject("groupInfo", groupService.selectBasicGroupInfoDetail(groupSeq));
			}else {
				throw new Exception();
			}
			
		}catch(Exception e){
			LOGGER.error("selectBasicFileInfo :" + e.toString());
			model.addObject("status", "FAIL");
		}
		return model;
	}
	//복사 
	
	@RequestMapping("/backoffice/sub/brodManage/brodGroupSelect.do")
	public ModelAndView brodGroupSelect( @ModelAttribute("loginVO") LoginVO loginVO
											 , HttpServletRequest request
								             , BindingResult bindingResult
										     , ModelMap model)throws Exception {
		
		ModelAndView mv = new ModelAndView("jsonView");
		try{
			mv.addObject("status", "SUCESS");
			mv.addObject("groupCombo", groupService.selectBasicGroupInfoCombo());
			
		}catch(Exception e){
			LOGGER.error("brodGroupSelect :" + e.toString());
			mv.addObject("status", "FAIL");
			mv.addObject("message", "처리 도중 문제가 발생 하였습니다.");
		}
		return mv;
	}
	//삭제
	@RequestMapping("/backoffice/sub/brodManage/brodBasicTimeDel.do")
	public ModelAndView updateBasicFileInfo( @ModelAttribute("loginVO") LoginVO loginVO
											 , HttpServletRequest request
								             , BindingResult bindingResult
										     , ModelMap model)throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
		try{
			
			
			String delBasciSeq = request.getParameter("delBasciSeq") != null ? request.getParameter("delBasciSeq") : "";
			String[] delSeq = delBasciSeq.split(",");
			
			if (delBasciSeq.contains(",")){
				for (int i =0; i < delSeq.length; i++){
					groupService.deleteBasicGroup(delSeq[i].toString());
				}	
			}else {
				if (!delBasciSeq.equals("")){
					groupService.deleteBasicGroup(delBasciSeq);
				}
			}
			mv.addObject("status", "SUCESS");
			
		}catch(Exception e){
			LOGGER.error("updateBasicFileInfo :" + e.toString());
			mv.addObject("status", "FAIL");
			mv.addObject("message", "처리 도중 문제가 발생 하였습니다.");
		}
		return mv;
		
	}
	@RequestMapping("/backoffice/sub/brodManage/brodBasicTimeUpdate.do")
	public ModelAndView updateBasicFileInfo( @ModelAttribute("loginVO") LoginVO loginVO
		                                     , @RequestBody BasicFileGroupInfoVO vo
			                                 , HttpServletRequest request
			                                 , BindingResult bindingResult
										     , ModelMap model)throws Exception {
		
		ModelAndView mv = new ModelAndView("jsonView");
		try{
			
			vo.setUserId(loginVO.getUserid());
			
			BasicFileGroupInfoVO groupInfo = groupService.selectBasicGroupPreCheck(vo);
			
			if (!groupInfo.getTimeCnt().equals("0")){
				mv.addObject("status", "FAIL");
				mv.addObject("message", "중복되는 시간이 있습니다.");
			}else if ( Integer.parseInt(groupInfo.getInutCnt()) > 1 && vo.getGroupTimegubun().equals("TIME_INPUT_1")){
				mv.addObject("status", "FAIL");
				mv.addObject("message", "일반 시간대는 1번만 편성 가능 합니다.");
			}else {
				int ret = 0;
				if(!vo.getMode().equals("Cpy")){
					ret = groupService.updateBasicGroupInfo(vo);
				}else{
					ret = groupService.updateBasicGroupInfoCopy(vo);
				}
				 
				if (ret > 0){
					mv.addObject("status", "SUCCESS");
					mv.addObject("message", "정상적으로 등록 되었습니다.");
					mv.addObject("groupList", groupService.selectBasicGroupInfoLst(vo));
				}else{
					throw new Exception();
				}
			}
			
			
		}catch(Exception e){
			LOGGER.error("updateBasicFileInfo :" + e.toString());
			mv.addObject("status", "FAIL");
			mv.addObject("message", "처리 도중 문제가 발생 하였습니다.");
		}
		return mv;
	}
	@RequestMapping("/backoffice/sub/brodManage/brodBasicTimeFileList.do")
	public ModelAndView detailSelectFileInfo( @ModelAttribute("loginVO") LoginVO loginVO
								             , HttpServletRequest request
								             , BindingResult bindingResult
										     , ModelMap model)throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
		try{
			
			String groupSeq = request.getParameter("groupSeq")!= null ? request.getParameter("groupSeq") : "";  
			String basicCode = request.getParameter("basicCode")!= null ? request.getParameter("basicCode") : "";
			String pageIndex = request.getParameter("pageIndex")!= null ? request.getParameter("pageIndex") : "1";
			
			ContentFileInfoVO fileVO = new ContentFileInfoVO();
			
			fileVO.setBasicCode(basicCode);		
			fileVO.setGroupSeq(groupSeq);
			fileVO.setPageSize(propertiesService.getInt("pageSize"));
			
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo( Integer.parseInt( pageIndex ));
			paginationInfo.setRecordCountPerPage(fileVO.getPageUnit());
			paginationInfo.setPageSize(fileVO.getPageSize());		
			fileVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			fileVO.setLastIndex(paginationInfo.getLastRecordIndex());
			fileVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			List<ContentFileInfoVO> fileList =  conFileService.selectBasicFileDetailPageListByPagination(fileVO);
			
			int totCnt = fileList.size() > 0 ?    Integer.parseInt( fileList.get(0).getTotalRecodCount()) : 0;   
			paginationInfo.setTotalRecordCount(totCnt);
			mv.addObject("paginationInfo", paginationInfo);
			mv.addObject("totalCnt", totCnt);
			mv.addObject("resultList",  fileList );
			//파일 리스트 정보 끝 부분 		
			BasicBrodFileIntervalInfoVO fileIntervalInfo = new BasicBrodFileIntervalInfoVO();
			fileIntervalInfo.setBasicCode(basicCode);
			fileIntervalInfo.setGroupSeq(groupSeq);
			
			mv.addObject("resultListBasic", fileInterval.selectBasicBrodIntervalFileLst( fileIntervalInfo ) );
			mv.addObject("status", "SUCCESS");
			
		}catch(Exception e){
			LOGGER.error("updateBasicFileInfo :" + e.toString());
			mv.addObject("status", "FAIL");
			mv.addObject("message", "처리 도중 문제가 발생 하였습니다.");
		}
		return mv;
	}
	//신규
	@RequestMapping("/backoffice/sub/brodManage/brodBasicFileUpdate_Itv.do")
	public ModelAndView basicUpdateRight_Itv( @ModelAttribute("vo") BasicBrodFileIntervalInfoVO vo			                                                    
								              , HttpServletRequest request
											  , BindingResult bindingResult
										      , ModelMap model)throws Exception{
		
		try{
			ModelAndView model_A = new ModelAndView("jsonView");
			
			int ret = 0;
			
			
			String brodFileseq = request.getParameter("brodFileseq");
			
			LOGGER.debug("vo.getBrodFileseq():" + vo.getBrodFileseq()+":"+ brodFileseq);
			
			if (vo.getFileGubun().equals("R")){
				ret = fileInterval.insertBasciBrodIntervalFile(vo);
			}else{				
				ret = fileInterval.deleteBasciBrodIntervalFile(vo);				
			}
			
			ContentFileInfoVO fileVO = new ContentFileInfoVO();
			fileVO.setBasicCode(vo.getBasicCode());		
			fileVO.setPageUnit( vo.getPageUnit());		
			fileVO.setPageSize( vo.getPageUnit() );
			fileVO.setPageIndex( vo.getPageIndex());		
			fileVO.setGroupSeq(vo.getGroupSeq());
			
			return model_A.addObject("resultMap", basicPagingGroupInfo(fileVO) );
			//return model_A.addObject("resultMap", null );
			
		}catch(Exception e){
			LOGGER.debug("basicUpdateRight Error:" + e.toString());
			return null;
		}
		
	}
	//신규
    private Map basicPagingGroupInfo(ContentFileInfoVO  fileVO)throws Exception{
		
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(fileVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(fileVO.getPageUnit());
		paginationInfo.setPageSize(fileVO.getPageSize());		
		fileVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		fileVO.setLastIndex(paginationInfo.getLastRecordIndex());
		fileVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		
		List<ContentFileInfoVO> fileList =  conFileService.selectBasicFileDetailPageListByPagination(fileVO);
		
		int totCnt = fileList.size() > 0 ?    Integer.parseInt( fileList.get(0).getTotalRecodCount()) : 0;   
		
		
		Map resultMap = new HashMap();
		
		     
		paginationInfo.setTotalRecordCount(totCnt);
		resultMap.put("paging", paginationInfo);
		resultMap.put("schList", fileList);
		
		BasicBrodFileIntervalInfoVO fileIntervalInfo = new BasicBrodFileIntervalInfoVO();
		fileIntervalInfo.setBasicCode(fileVO.getBasicCode());
		fileIntervalInfo.setGroupSeq(fileVO.getGroupSeq());
		resultMap.put("atchFileLst", fileInterval.selectBasicBrodIntervalFileLst( fileIntervalInfo ) );
		
		return resultMap;
	}
	@RequestMapping("/backoffice/sub/brodManage/basicDayInput.do")
	public String selectPopupDatepicker(@ModelAttribute("vo") BasicBrodFileIntervalInfoVO vo
			                            ,HttpServletRequest request
										, BindingResult bindingResult
									    , ModelMap model)throws Exception{
		
		String atchFileId = request.getParameter("atchFileId")!= null ? request.getParameter("atchFileId") : "";  
		String mode = request.getParameter("mode")!= null ? request.getParameter("mode") : "";
		vo.setMode(mode);
		vo.setAtchFileId(atchFileId);
		model.addAttribute("regist", vo);
		return "/backoffice/sub/brodManage/basicDayInput";
	}
	//신규
	@RequestMapping("/backoffice/sub/brodManage/brodGroupFileCheckUpdate.do")
	public ModelAndView groupAllLeftUpdate( HttpServletRequest request)throws Exception{
		
		
		ModelAndView model_A = new ModelAndView("jsonView");
		try{
			
			
			String delBasciSeq = request.getParameter("delBasciSeq") != null ? request.getParameter("delBasciSeq") : "";
			String basicCode = request.getParameter("basicCode") != null ? request.getParameter("basicCode") : "";
			String groupSeq = request.getParameter("groupSeq") != null ? request.getParameter("groupSeq") : "";
			String brodStartday = request.getParameter("brodStartday") != null ? request.getParameter("brodStartday") : "";
			String brodEndday = request.getParameter("brodEndday") != null ? request.getParameter("brodEndday") : "";
			
			String moveGubun = request.getParameter("moveGubun") != null ? request.getParameter("moveGubun") : "";
			/** 페이징 **/
			String pageIndex = request.getParameter("pageIndex") != null ? request.getParameter("pageIndex") : "";
			String pageSize = request.getParameter("pageSize") != null ? request.getParameter("pageSize") : "";
			String searchCondition = request.getParameter("searchCondition") != null ? request.getParameter("searchCondition") : "";
			String searchKeyword = request.getParameter("searchKeyword") != null ? request.getParameter("searchKeyword") : "";
			
			
			
            String[] delSeq = delBasciSeq.split(",");
						
            BasicBrodFileIntervalInfoVO vo = new BasicBrodFileIntervalInfoVO();
			vo.setBasicCode(basicCode);
			vo.setGroupSeq(groupSeq);
			vo.setBrodStartday(brodStartday);
			vo.setBrodEndday(brodEndday);
			
			LOGGER.debug("delBasciSeq:" + delBasciSeq);
			
			
			if (delBasciSeq.contains(",")){
				for (int i =0; i < delSeq.length; i++)
				{
					
					if (moveGubun.equals("L")){
						vo.setAtchFileId(delSeq[i].toString() );
						vo.setBasicOrder("10");	
						fileInterval.insertBasciBrodIntervalFile(vo);
					}else {
						vo.setBrodFileseq(delSeq[i].toString());
						fileInterval.deleteBasciBrodIntervalFile(vo);	
					}
					
				}	
			}else {
				if (!delBasciSeq.equals("")){
					
					if (moveGubun.equals("L")){
						vo.setAtchFileId(delBasciSeq);
						vo.setBasicOrder("10");
						fileInterval.insertBasciBrodIntervalFile(vo);
					}else {
						vo.setBrodFileseq(delBasciSeq);
						fileInterval.deleteBasciBrodIntervalFile(vo);	
					}
				}
			}
			
			
			
			ContentFileInfoVO fileVO = new ContentFileInfoVO();
			fileVO.setBasicCode(vo.getBasicCode());		
			fileVO.setPageUnit( vo.getPageUnit());		
			fileVO.setPageSize( vo.getPageUnit() );
			fileVO.setPageIndex( vo.getPageIndex());		
			fileVO.setGroupSeq(vo.getGroupSeq());
			fileVO.setSearchCondition(searchCondition);
			fileVO.setSearchKeyword(searchKeyword);
						
			
			return model_A.addObject("resultMap", basicPagingGroupInfo(fileVO)  );
		}catch (Exception e){
			return null;
		}
		
	}
	@RequestMapping("/backoffice/sub/brodManage/brodPlayInfo.do")
	public String selectBrodBasicPlayList(@ModelAttribute("loginVO") LoginVO loginVO
										   , @ModelAttribute("searchVO") BasicFileGroupPlayInfoVO searchVO
							               , HttpServletRequest request
										   , BindingResult bindingResult
									       , ModelMap model)throws Exception {
		
	    try{
	    	model.addAttribute("regist", searchVO);
	    	
	    	String searchStartDay = searchVO.getSearchStartDay() != "" ? searchVO.getSearchStartDay() : 
	    		String.valueOf(Integer.parseInt(egovframework.let.utl.fcc.service.EgovDateUtil.getCurrentDate(""))-1);
	    	String searchEndDay = searchVO.getSearchEndDay() != "" ? searchVO.getSearchEndDay() : 
	    		String.valueOf(Integer.parseInt(egovframework.let.utl.fcc.service.EgovDateUtil.getCurrentDate(""))-1);
	    	
	    	
	    	searchVO.setSearchStartDay(searchStartDay);
	    	searchVO.setSearchEndDay(searchEndDay);
	    	try{
	    		if (searchVO.getSearchCenterId().isEmpty() ){
					searchVO.setSearchCenterId(null);
				}
	    	}catch(Exception e1){
	    		searchVO.setSearchCenterId(null);
	    	}
	    	
	    	
	    	List<BasicFileGroupPlayInfoVO> playList = groupPlayInfo.selectPlayListInfo(searchVO);
	    	LOGGER.debug("check3");
	    	int totCnt = playList.size() > 0 ? playList.size() : 0;
	    	
	    	CenterInfoVO centerVo = new CenterInfoVO();
	    	centerVo.setPageSize(100);
	    	centerVo.setPageIndex(0);
	    	centerVo.setLastIndex(200);
	    	
	    	
	    	model.addAttribute("centerList", centerInfoManageService.selectCenterBrodCombo("") );
	    	model.addAttribute("resultList", playList );
	    	model.addAttribute("totalCnt",   totCnt );
	    	
	    }catch (Exception e){
	    	
	    }
		return "/backoffice/sub/brodManage/brodPlayInfo";
	}
	@RequestMapping("/backoffice/sub/brodManage/brodPlayInfoExelDown.do")
	public ModelAndView selectPlayBrodExcelList ( @ModelAttribute("searchVO") BasicFileGroupPlayInfoVO searchVO
								               , HttpServletRequest request
											   , BindingResult bindingResult
										       , ModelMap model)throws Exception {
		
		model.addAttribute("regist", searchVO);
		String searchStartDay = searchVO.getSearchStartDay() != "" ? searchVO.getSearchStartDay() : egovframework.let.utl.fcc.service.EgovDateUtil.getCurrentDate("");
    	String searchEndDay = searchVO.getSearchEndDay() != "" ? searchVO.getSearchEndDay() : egovframework.let.utl.fcc.service.EgovDateUtil.getCurrentDate("");
    	
    	searchVO.setSearchStartDay(searchStartDay);
    	searchVO.setSearchEndDay(searchEndDay);
		
    	try{
    		if (searchVO.getSearchCenterId().isEmpty() ){
				searchVO.setSearchCenterId(null);
			}
    	}catch(Exception e1){
    		searchVO.setSearchCenterId(null);
    	}
		
    	List<BasicFileGroupPlayInfoVO> brodPlayReport = groupPlayInfo.selectPlayListInfo(searchVO);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("brodPlayReport", brodPlayReport);
		
		return new ModelAndView("BrodPlayExcelView", map);
		
	}
	@RequestMapping("/backoffice/sub/brodManage/brodPlayInfoNotCenterExelDown.do")
	public ModelAndView selectPlayBrodNotCenterExcelList ( @ModelAttribute("searchVO") BasicFileGroupPlayInfoVO searchVO
								               , HttpServletRequest request
											   , BindingResult bindingResult
										       , ModelMap model)throws Exception {
		
		model.addAttribute("regist", searchVO);
		String searchStartDay = searchVO.getSearchStartDay() != "" ? searchVO.getSearchStartDay() : egovframework.let.utl.fcc.service.EgovDateUtil.getCurrentDate("");
    	String searchEndDay = searchVO.getSearchEndDay() != "" ? searchVO.getSearchEndDay() : egovframework.let.utl.fcc.service.EgovDateUtil.getCurrentDate("");
    	
    	searchVO.setSearchStartDay(searchStartDay);
    	searchVO.setSearchEndDay(searchEndDay);
		
    	try{
    		if (searchVO.getSearchCenterId().isEmpty() ){
				searchVO.setSearchCenterId(null);
			}
    	}catch(Exception e1){
    		searchVO.setSearchCenterId(null);
    	}
		
    	List<BasicFileGroupPlayInfoVO> brodPlayReport = groupPlayInfo.selectPlayListInfoNotCenter(searchVO);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("brodPlayReport", brodPlayReport);
		
		return new ModelAndView("BrodPlayNotCenterExcelView", map);
		
	}
}
