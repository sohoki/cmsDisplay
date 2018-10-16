package egovframework.let.sym.dbd.web;


import javax.annotation.Resource;



import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.Globals;

import egovframework.let.sym.dbd.service.DashBoardInfo;
import egovframework.let.sym.dbd.service.DashBoardManagerService;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.let.uat.uia.service.EgovUserManagerService;
import egovframework.let.utl.fcc.service.FileUpladController;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
public class DashBoardManagerController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(DashBoardManagerController.class);
	
	@Resource(name="DashBoardManagerService")
	private DashBoardManagerService dashboard;
	
	  /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;
	
	
	@RequestMapping("/backoffice/sub/equiManage/dashboardState.do")
	public String selectDashBoardList(@ModelAttribute("loginVO") LoginVO loginVO
									, @ModelAttribute("searchVO") DashBoardInfo searchVO
									, HttpServletRequest request
									, BindingResult bindingResult						
									, ModelMap model)throws Exception{
		
		int ret = dashboard.dashStateUpdateStep01();
		
		ret = dashboard.dashStateUpdateStep02();
		
		model.addAttribute("resultListBrod",   dashboard.selectBrodStatus() );
		model.addAttribute("resultListDid",   dashboard.selectDidStatus() );
		
		return "/backoffice/sub/equiManage/dashboardState";
	}
	@RequestMapping("/backoffice/sub/equiManage/dashboardState1.do")
	public String selectDashBoardListDid(@ModelAttribute("loginVO") LoginVO loginVO
									, @ModelAttribute("searchVO") DashBoardInfo searchVO
									, HttpServletRequest request
									, BindingResult bindingResult						
									, ModelMap model)throws Exception{

	
	
	
	
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
	  //수정
	  //searchVO.setLastIndex(paginationInfo.getFirstRecordIndex() + searchVO.getPageSize());
	  searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	  
	  model.addAttribute("resultList",   dashboard.selectBrodStatusPage01(searchVO) );
      model.addAttribute("regist", searchVO);
      
      int totCnt = dashboard.selectBrodStatusPage01Cnt();       
	  paginationInfo.setTotalRecordCount(totCnt);
      model.addAttribute("paginationInfo", paginationInfo);
      model.addAttribute("totalCnt", totCnt);
      
      model.addAttribute("resultListDid",   dashboard.selectDidStatus() );
	
	return "/backoffice/sub/equiManage/dashboardState1";
	}
	@RequestMapping("/backoffice/sub/equiManage/dashboardState2.do")
	public String selectDashBoardListBrod(@ModelAttribute("loginVO") LoginVO loginVO
									, @ModelAttribute("searchVO") DashBoardInfo searchVO
									, HttpServletRequest request
									, BindingResult bindingResult						
									, ModelMap model)throws Exception{

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
		  //수정
		  //searchVO.setLastIndex(paginationInfo.getFirstRecordIndex() + searchVO.getPageSize());
		  int totCnt = dashboard.selectBrodStatusPage02Cnt();
		  searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		  searchVO.setOffCnt( String.valueOf(totCnt));
		  
		  model.addAttribute("resultList",   dashboard.selectBrodStatusPage02(searchVO) );
	      model.addAttribute("regist", searchVO);
	      
	             
		  paginationInfo.setTotalRecordCount(totCnt);
	      model.addAttribute("paginationInfo", paginationInfo);
	      model.addAttribute("totalCnt", totCnt);
	      
	
	
		model.addAttribute("resultListBrod",   dashboard.selectBrodStatus() );
	
	return "/backoffice/sub/equiManage/dashboardState2";
	}
	
}
