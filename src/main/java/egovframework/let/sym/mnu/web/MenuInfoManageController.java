package egovframework.let.sym.mnu.web;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.Globals;


import egovframework.let.sym.rnt.service.RoleInfoManageService;
import egovframework.let.sym.mnu.service.MenuInfoVO;
import egovframework.let.sym.mnu.service.MenuInfoManageService;
import egovframework.let.sym.cnt.service.CenterInfoManageService;
import egovframework.let.sym.cnt.service.CenterInfoVO;
import egovframework.let.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller 
public class MenuInfoManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MenuInfoManageController.class);

	@Resource(name = "MenuInfoManageService")
	private MenuInfoManageService menuInfoManageService;
	
	@Resource(name = "RoleInfoManageService")
	private RoleInfoManageService roleInfoManageService;
	
	@Resource(name="CenterInfoManageService")
	private CenterInfoManageService centerInfoService;
	
	@Resource(name="CmmnDetailCodeManageService")
	private EgovCcmCmmnDetailCodeManageService cmmnDetailCodeService;
	
	@Resource(name="egovMessageSource")
	protected EgovMessageSource egovMessageSource;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/*
	@RequestMapping (value="/backoffice/sub/basicManage/menuType.do")
	public ModelAndView selectComboView( HttpServletRequest request)throws Exception{
		
		ModelAndView model = new 	ModelAndView("jsonView");
		String menuType = request.getParameter("menuType") != null ? request.getParameter("menuType") : "";
		String mode = request.getParameter("mode") != null ? request.getParameter("mode") : "";
		String didId = request.getParameter("didId") != null ? request.getParameter("didId") : "";
		if (menuType.equals("MENU_TYPE01")){
			model.addObject("didLst", centerInfoService.selectCenterInfoManageCombo());
		}else {
			if (mode.equals("Ins")){
				model.addObject("didLst", menuInfoManageService.selectMenuComboLst(""));	
			}else {
				model.addObject("didLst", menuInfoManageService.selectMenuComboLst(didId));
			}			
		}		
		return model;
	}
	*/
	
	@RequestMapping (value="/backoffice/sub/basicManage/menuList.do")
	public String selectManuInfoManageListByPagination(@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("searchVO") MenuInfoVO searchVO
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
			  //searchVO.setLastIndex(paginationInfo.getFirstRecordIndex() + searchVO.getPageSize());
			  
			  searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		       model.addAttribute("resultList",   menuInfoManageService.selectManuInfoManageListByPagination(searchVO)  );
		       
		       model.addAttribute("regist",   searchVO  );

		       int totCnt = menuInfoManageService.selectMenuInfoManageListTotCnt_S(searchVO) ;       
			   paginationInfo.setTotalRecordCount(totCnt);
		       model.addAttribute("paginationInfo", paginationInfo);
		       model.addAttribute("totalCnt", totCnt);
			
			   return "/backoffice/sub/basicManage/menuList";	
	
	}
	//json
	@RequestMapping(value="/backoffice/sub/basicManage/menuCombo.do")
	public ModelAndView menuCombo( HttpServletRequest request) throws Exception{
		
		String menuType = request.getParameter("menuType") != null ? request.getParameter("menuType") : "";
		String didId = request.getParameter("didId") != null ? request.getParameter("didId") : "";
		MenuInfoVO  vo = new MenuInfoVO();
		vo.setMenuType(menuType);
		ModelAndView model = new ModelAndView("jsonView");
		
		model.addObject("selectMenuGroup", menuInfoManageService.selectMenuInfoManageCombo());
		
		if (  menuType.equals("MENU_TYPE01") ){			
	        CenterInfoVO centerInfoVO = new CenterInfoVO();
	        String cenSearchKeyword = request.getParameter("cenSearchKeyword") == null ? "" : request.getParameter("cenSearchKeyword");
	        centerInfoVO.setSearchKeyword(cenSearchKeyword);
	        
	        model.addObject("centerCombo", centerInfoService.selectCenterInfoManageCombo(centerInfoVO));	
		}else {						
			model.addObject("selectDidGroup", menuInfoManageService.selectMenuComboLst(didId));
		}
		return model;
		
	}
	@RequestMapping(value="/backoffice/sub/basicManage/menuDetail.do")
	public String selectMenurInfoManageDetail(@ModelAttribute("loginVO") LoginVO loginVO
			,  MenuInfoVO vo
			, HttpServletRequest request
			, BindingResult bindingResult
			, ModelMap model)throws Exception {
		
				
		// DID 장비 부분 생략
		model.addAttribute("selectRoleGroup", roleInfoManageService.selectRoleIInfoManageCombo());				
		model.addAttribute("selectMenuType", cmmnDetailCodeService.selectCmmnDetailCombo("EMT007"));	
        			
		model.addAttribute("regist", vo);
		
		if (!vo.getMode().equals("Ins")){
			
					
			
			vo = (MenuInfoVO)menuInfoManageService.selectMenurInfoManageDetail(  vo.getMenuId());
			
			
			model.addAttribute("selectMenuGroup", menuInfoManageService.selectMenuInfoManageCombo());
			
			model.addAttribute("regist", vo);
			if (vo.getMenuType().equals("MENU_TYPE01")  ){
		        CenterInfoVO centerInfoVO = new CenterInfoVO();
		        String cenSearchKeyword = request.getParameter("cenSearchKeyword") == null ? "" : request.getParameter("cenSearchKeyword");
		        centerInfoVO.setSearchKeyword(cenSearchKeyword);
				
				model.addAttribute("centerLst", centerInfoService.selectCenterInfoManageCombo(centerInfoVO));
			}else {
				model.addAttribute("selectDidGroup", menuInfoManageService.selectMenuComboLst(vo.getDidId()));
			}
		}else {
		
		}
		return "/backoffice/sub/basicManage/menuDetail";
	}
	@SuppressWarnings("finally")
	@RequestMapping(value="/backoffice/sub/basicManage/menuDeletel.do")
	public String  deleteCenterInfoManage(@ModelAttribute("loginVO") LoginVO loginVO,
			 MenuInfoVO vo
			, HttpServletRequest request
			, BindingResult bindingResult
			, ModelMap model)throws Exception{
		
		model.addAttribute("regist",vo );
		
		if (StringUtils.equals(vo.getMenuId(), Globals.REGITER_SYSTEM)){
			model.addAttribute("status", Globals.STATUS_FAIL);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.delete.system"));			
			return "redirect:/backoffice/sub/basicManage/menuList.do";
		}
				
		try{
		      int ret = 	menuInfoManageService.deleteMenuInfoManage(vo.getMenuId());		      
		      if (ret > 0 ) {		    	  
		    	  model.addAttribute("status", Globals.STATUS_SUCCESS);
		    	  model.addAttribute("message", egovMessageSource.getMessage("success.common.delete") );		    	  
		      }else {
		    	  throw new Exception();		    	  
		      }
		}catch (Exception e){
			model.addAttribute("status", Globals.STATUS_FAIL);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.delete"));			
		}		
		finally{
			return "redirect:/backoffice/sub/basicManage/menuList.do";				
		}
		
	}
	

	@RequestMapping (value="/backoffice/sub/basicManage/menuUpdate.do")	                                           
	public String updateCenterInfoManage(
			@ModelAttribute("loginVO") LoginVO loginVO,
			@ModelAttribute("MenuInfoVO") MenuInfoVO vo,		
			 HttpServletRequest request
			, BindingResult bindingResult
			, ModelMap model) throws Exception{
		
		model.addAttribute("regist", vo);
		String meesage = "";
		String url = "/backoffice/sub/basicManage/menuList";
		
     	
		try{
			
		
			int ret  = 0;
			if (vo.getMode().equals("Ins")){
				ret = menuInfoManageService.insertMenuInfoManage(vo);
				meesage = "sucess.common.insert";
				url = "/backoffice/sub/basicManage/menuDetail";				
			}else {
				 ret = menuInfoManageService.updateMenuInfoManage(vo);;
				 meesage = "sucess.common.update";
				 url = "/backoffice/sub/basicManage/menuDetail";
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
			url = "/backoffice/sub/basicManage/menuDetail";
		}finally {
			return url;	
		}					
		
	}
	
	
}
