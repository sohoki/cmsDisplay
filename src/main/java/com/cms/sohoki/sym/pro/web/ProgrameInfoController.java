package com.cms.sohoki.sym.pro.web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.let.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import com.cms.sohoki.sym.pro.service.ProgrameInfo;
import com.cms.sohoki.sym.pro.service.ProgrameInfoVO;
import com.cms.sohoki.sym.pro.service.ProgrameInfoService;

import egovframework.com.cmm.service.Globals;

@Controller
public class ProgrameInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProgrameInfoController.class);
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    
    @Resource(name="CmmnDetailCodeManageService")
    private EgovCcmCmmnDetailCodeManageService cmmnDetailCodeManageService;
    
    @Resource(name="egovMessageSource")
	protected EgovMessageSource egovMessageSource;
    
    @Autowired
	private DefaultBeanValidator beanValidator;
    
    @Resource(name="ProgrameInfoService")
    private ProgrameInfoService programeService;
    
	
    @RequestMapping("/backoffice/sub/equiManage/progList.do")
	public String selectProgrameList(@ModelAttribute("loginVO") LoginVO loginVO
										   , @ModelAttribute("searchVO") ProgrameInfoVO searchVO
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
		
		List<ProgrameInfoVO> progList = programeService.selectProgramPageListInfo(searchVO);
		int totCnt = progList.size() > 0 ? progList.get(0).getTotalRecordCount() : 0;;    
		model.addAttribute("resultList",  progList );
		   
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("totalCnt", totCnt);		       
		model.addAttribute("regist", searchVO);		    
		model.addAttribute("progOstype", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT011"));
		
		return "/backoffice/sub/equiManage/programList";
		
	}
    @RequestMapping("/backoffice/sub/equiManage/progInfo.do")
    public ModelAndView selectProgrameInfo (@ModelAttribute("loginVO") LoginVO loginVO
										   , @RequestBody ProgrameInfoVO VO
							               , HttpServletRequest request
										   , BindingResult bindingResult)throws Exception {
    	
    	ModelAndView mv = new ModelAndView("jsonView");
    	
		
    	try{
    		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
            if(!isAuthenticated) {
            	mv.addObject("message", egovMessageSource.getMessage("fail.common.login"));
            	mv.addObject("status", "LOGIN FAIL");
        		return mv;
            }
            mv.addObject("status", "SUCCESS");
            mv.addObject("result", programeService.selectProgramPageInfoDetail(VO.getProgCode()));
            
    	}catch(Exception e){
    		LOGGER.debug("selectProgrameInfo error: " + e.toString());
			mv.addObject("status", Globals.STATUS_FAIL);
			mv.addObject("message", "프로그램 조회 실패");	
    	}
    	return mv;
    }
    @RequestMapping("/backoffice/sub/equiManage/progUpdate.do")
    public ModelAndView selectProgrameUpdateInfo (@ModelAttribute("loginVO") LoginVO loginVO
											      , @RequestBody ProgrameInfoVO VO
								                  , HttpServletRequest request
											      , BindingResult bindingResult)throws Exception {
    	ModelAndView model = new ModelAndView("jsonView");
    	try{
    		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
            if(!isAuthenticated) {
            	model.addObject("message", egovMessageSource.getMessage("fail.common.login"));
            	model.addObject("status", "LOGIN FAIL");
        		return model;
            }
            //프로그램 파일 업로드 확인 하기 
            int ret = programeService.updateProgrameInfo(VO);
            if (ret > 0){
            	model.addObject("status", "SUCCESS");
            	model.addObject("message", "정상적으로 등록 되었습니다.");
            }else {
            	throw new  Exception();
            }
    	}catch(Exception e){
    		LOGGER.debug("selectProgrameUpdateInfo error: " + e.toString());
    		model.addObject("status", Globals.STATUS_FAIL);
    		model.addObject("message", "프로그램 등록 실패");	
    	}
    	return model;
    }
    @RequestMapping("/backoffice/sub/equiManage/progCombo.do")
    public ModelAndView selectProgrameComboInfo ( @RequestBody ProgrameInfoVO VO )throws Exception {
    	ModelAndView model = new ModelAndView("jsonView");
    	try{
    		model.addObject("status", "SUCCESS");
    		model.addObject("progList", programeService.selectProgramComboInfo(VO));
    	}catch(Exception e){
    		model.addObject("status", "FAIL");
    		model.addObject("message", "조회 실패:" + e.toString());
    	}
    	return model;
    }
    @RequestMapping("/backoffice/sub/equiManage/progDelete.do")
    public ModelAndView selectProgrameDeleteInfo ( HttpServletRequest request  )throws Exception {
    	ModelAndView model = new ModelAndView("jsonView");
    	try{
    		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
            if(!isAuthenticated) {
            	model.addObject("message", egovMessageSource.getMessage("fail.common.login"));
            	model.addObject("status", "LOGIN FAIL");
        		return model;
            }
            
            String progCode = request.getParameter("prodCode") != null ? request.getParameter("prodCode") : "";
            //프로그램 파일 업로드 확인 하기 
            int ret = programeService.deleteProgrameInfo(progCode);
            if (ret > 0){
            	model.addObject("status", "SUCCESS");
            	model.addObject("message", "정상적으로 삭제 되었습니다.");
            }else {
            	throw new  Exception();
            }
    	}catch(Exception e){
    		LOGGER.debug("selectProgrameDeleteInfo error: " + e.toString());
    		model.addObject("status", Globals.STATUS_FAIL);
    		model.addObject("message", "프로그램 삭제 실패");	
    	}
    	return model;
    }
    @RequestMapping("/backoffice/sub/equiManage/progFileUpload.do")
    public ModelAndView requestupload (@RequestParam("files")List<MultipartFile> images)throws Exception{
    	
    	 ModelAndView model = new ModelAndView("jsonView");
    	 
    	 try{
    		 long sizeSum = 0;
        	 
        	 String filePath = propertiesService.getString("Globals.fileStorePath");
        	 
        	 //디렉토리 생성
        	 String inDate   = new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date());            	
         	 
         	 String fileFullPath = "";
         	 String fileList = "";
             for(MultipartFile image : images) {
            	 
            	 File filedir = new File(filePath+"software\\"+inDate);
             	 if (!filedir.isDirectory()){
             		filedir.mkdir();
             	 }
             	 
                 String originalName = image.getOriginalFilename();
                 //확장자 검사
                 System.out.println(image.getSize());
                 //용량 검사
                 //리눅스 관련시 path 변경
                 fileFullPath = filePath+"software\\"+inDate+"\\" + originalName.substring(0,originalName.lastIndexOf(".") - 1)+ ".jpg"; 
                 
                 LOGGER.debug("fileFullPath: " + fileFullPath);
                 File file = new File(fileFullPath);
                 image.transferTo(file);
                 fileList += ","+originalName;
             }
             LOGGER.debug("fileList: " + fileList);
             model.addObject("status", "SUCCESS");
         	 model.addObject("fileInfo", fileList);
    	 }catch(Exception e){
    		 System.out.println("requestupload error:" + e.toString());
    		 LOGGER.debug("requestupload error:" + e.toString());
    		 model.addObject("status", "Fail");
    		 model.addObject("message", "error:" + e.toString());
    	 }
        return model;
    }
}
