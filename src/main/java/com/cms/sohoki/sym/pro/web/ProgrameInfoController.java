package com.cms.sohoki.sym.pro.web;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.let.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import com.cms.sohoki.sym.pro.service.ProgrameInfo;
import com.cms.sohoki.sym.pro.service.ProgrameInfoVO;
import com.cms.sohoki.sym.pro.service.ProgrameInfoService;;
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
		
		return "/backoffice/sub/equiManage/programList";
		
	}
}
