package egovframework.let.sym.ccm.zip.web;


import javax.annotation.Resource;


import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.Globals;
import egovframework.let.sym.ccm.zip.service.RdnmadZipVO;
import egovframework.let.sym.ccm.zip.service.RdnmadZipManageService;


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
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.let.uat.uia.service.EgovUserManagerService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class RdnmadZipManageController {

private static final Logger LOGGER = LoggerFactory.getLogger(RdnmadZipManageController.class);

	
	
    
    @Resource(name="RdnmadZipManageService")
    private RdnmadZipManageService rdnmadZipManageService;

	@Resource(name="egovMessageSource")
	protected EgovMessageSource egovMessageSource;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;
	

	@RequestMapping ("/backoffice/popup/zipSearch.do")
	public String selectDidInfoManageListByPagination(@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("searchVO") RdnmadZipVO searchVO
			, ModelMap model) throws Exception {
		
	      searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
	      searchVO.setPageSize(100);
	       
	      /** pageing */       
	   	  PaginationInfo paginationInfo = new PaginationInfo();
		  paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		  paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		  paginationInfo.setPageSize(searchVO.getPageSize());

		  searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		  searchVO.setLastIndex(100);
		  searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

	       model.addAttribute("resultList",   rdnmadZipManageService.selectRdnmadZipManageListByPagination(searchVO) );

	       int totCnt = rdnmadZipManageService.selectRdnmadZipManageListTotCnt_S(searchVO);       
		   paginationInfo.setTotalRecordCount(totCnt);
	       
	       model.addAttribute("totalCnt", totCnt);
	       
	      return "/backoffice/popup/zipSearch";
	}
	//최초 팝업 창만 나오게 하기 
	@RequestMapping ("/backoffice/popup/zipPop.do")
	public String viewZipPop( @ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("searchVO") RdnmadZipVO searchVO
			, ModelMap model) throws Exception {
		
		return "/backoffice/popup/zipSearch";
	}
	
}
