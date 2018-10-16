package egovframework.let.sym.cnt.web;


import javax.annotation.Resource;



import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.Globals;
import egovframework.let.sts.brd.service.BrodContentInfoManageService;
import egovframework.let.sts.brd.service.BrodContentInfo;
import egovframework.let.sym.cnt.service.CenterInfoAnniversary;
import egovframework.let.sym.cnt.service.CenterInfoAnniversaryVO;
import egovframework.let.sym.cnt.service.CenterAnniManagerService;


import egovframework.let.sym.cnt.service.CenterInfoManageService;
import egovframework.let.sym.cnt.service.CenterInfoVO;
import egovframework.let.sym.rnt.service.RoleInfoManageService;

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
public class CenterAnniManagerController {
	
	
    private static final Logger LOGGER = LoggerFactory.getLogger(CenterAnniManagerController.class);
	
	
	

	@Resource(name="CenterAnniManagerService")
	private CenterAnniManagerService centerAnniService;
	
	@Resource(name="BrodContentInfoService")
	private BrodContentInfoManageService brodContentInfo;
	
	@Resource(name="egovMessageSource")
	protected EgovMessageSource egovMessageSource;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;
	
	
	
	@RequestMapping(value="/backoffice/sub/basicManage/centerAnniList.do")
	public String selectCenterAnniLst(@ModelAttribute("loginVO") LoginVO loginVO
									, @ModelAttribute("searchVO") CenterInfoAnniversaryVO searchVO
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
		  //수정
		  searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		  
	      model.addAttribute("resultList",   centerAnniService.selectCenterAnniManageListByPagination(searchVO)  );
	      //등록 시작 
	      searchVO.setMode("Ins");
	      model.addAttribute("regist", searchVO);
	      
	      model.addAttribute("brodLst", brodContentInfo.selectBrodContentComboAnn());
	      
	       
	      int totCnt = centerAnniService.selectCenterAnniManageListTotCnt_S(searchVO) ;       
		  paginationInfo.setTotalRecordCount(totCnt);
	      model.addAttribute("paginationInfo", paginationInfo);
	      model.addAttribute("totalCnt", totCnt);
		
		  return "/backoffice/popup/CenterAnni";			
	}
	
	@RequestMapping(value="/backoffice/sub/basicManage/centerAnniDetailInfo.do")
	@ResponseBody
	public String selectAnnDayInfo(HttpServletRequest request) throws Exception {
		String brodCode = request.getParameter("brodCode") != null ? request.getParameter("brodCode") : "";
		
		BrodContentInfo  brodInfo = new BrodContentInfo();
		brodInfo = brodContentInfo.selectBrodContentInfo(brodCode);
		
		return brodInfo.getBrodStartDay()+"/"+brodInfo.getBrodEndDay();
	}
		
	@RequestMapping(value="/backoffice/sub/basicManage/centerAnniCntCheck.do")
	@ResponseBody
	public String selectAnnCnt(CenterInfoAnniversaryVO searchVO
								, HttpServletRequest request
								, BindingResult bindingResult						
								, ModelMap model) throws Exception {
		
		/*String centerId = request.getParameter("centerId") != null ? request.getParameter("centerId") : "";
		String centerAnniStartDay = request.getParameter("centerAnniStartDay") != null ? request.getParameter("centerAnniStartDay") : "";
		String centerAnniEndDay = request.getParameter("centerAnniEndDay") != null ? request.getParameter("centerAnniEndDay") : "";*/
		
		
		
		
		System.out.println(  searchVO.getCenterId() );
		System.out.println(  searchVO.getCenterAnniStartDay() );
		System.out.println(  searchVO.getCenterAnniEndDay() );
		System.out.println(  searchVO.getCenterAnniday() );
		
		
		return String.valueOf(centerAnniService.selectCenterAnniRetgCheck(searchVO));
		
	}

	@RequestMapping(value="/backoffice/sub/basicManage/centerAnniDetail.do")
	public ModelAndView selectCenterAnni (HttpServletRequest request) throws Exception {
		
		String centerAnniday = request.getParameter("centerAnniday") != null ? request.getParameter("centerAnniday") : "";
		
		ModelAndView model = new ModelAndView("jsonView");
		
		CenterInfoAnniversary centerAnni = new CenterInfoAnniversary();
		centerAnni = centerAnniService.selectCenterAnniManageDetail(centerAnniday);
		model.addObject("anniInfo", centerAnni);
		
		return model;
		
	}
	@RequestMapping(value="/backoffice/sub/basicManage/centerAnniUpdate.do")
	@SuppressWarnings("finally")
	public String updateCenterAnni(@ModelAttribute("loginVO") LoginVO loginVO
									, CenterInfoAnniversaryVO vo
									, HttpServletRequest request
									, BindingResult bindingResult						
									, ModelMap model) throws Exception {
		model.addAttribute("regist", vo);
		String meesage = "";
		String url = "/backoffice/sub/basicManage/centerAnniList.do";
		
		try{
			
		
			int ret  = 0;
			if (vo.getMode().equals("Ins")){
				ret =  centerAnniService.insertCenterAnniManage(vo) ;
				meesage = "sucess.common.insert";
				url = "forward:/backoffice/sub/basicManage/centerAnniList.do";				
			}else {
				 ret = centerAnniService.updateCenterAnniManage(vo);
				 meesage = "sucess.common.update";
				 url = "forward:/backoffice/sub/basicManage/centerAnniList.do";
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
			url = "forward:/backoffice/sub/basicManage/centerAnniList.do";
		}finally {
			return url;	
		}					
		
	}
	@RequestMapping(value="/backoffice/sub/basicManage/centerAnniDel.do")
	@ResponseBody	
	public String deleteCenterAnniversary(HttpServletRequest request) throws Exception{
		String centerAnniday = request.getParameter("centerAnniday") != null ? request.getParameter("centerAnniday") : "";		
		return String.valueOf( centerAnniService.deleteCenterAnniManage(centerAnniday) );		
	}
	
}
