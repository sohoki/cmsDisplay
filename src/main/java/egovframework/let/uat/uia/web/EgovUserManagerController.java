package egovframework.let.uat.uia.web;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.Globals;
import egovframework.let.uat.uia.service.GnrMber;
import egovframework.let.uat.uia.service.GnrMberVO;
import egovframework.let.sts.mhs.service.MhsCenterInfo;
import egovframework.let.sts.mhs.service.MhsCenterInfoManageService;
import egovframework.let.sts.mhs.service.MhsCenterInfoVO;
import egovframework.let.sym.rnt.service.AuthorInfoManageService;
import egovframework.let.sym.cnt.service.CenterInfoVO;
import egovframework.let.sym.cnt.service.CenterInfoManageService;
import egovframework.let.sym.rnt.service.RoleInfoVO;
import egovframework.let.sym.rnt.service.RoleInfoManageService;
import egovframework.let.sym.mnu.service.MenuInfoVO;
import egovframework.let.sym.mnu.service.TMenuInfoVO;
import egovframework.let.sym.mnu.service.MenuInfoManageService;
import egovframework.let.sym.mnu.service.TMenuInfoManageService;
import egovframework.let.cmm.use.service.Group;
import egovframework.let.cmm.use.service.GroupManagerService;
import egovframework.let.cmm.use.service.GroupVo;
import egovframework.let.utl.fcc.service.EgovStringUtil;


import egovframework.let.sym.ccm.cde.service.CmmnDetailCode;
import egovframework.let.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;
import egovframework.let.uat.uia.service.EgovUserManagerService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;










import javax.annotation.Resource;

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
public class EgovUserManagerController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovUserManagerController.class);
	
	@Resource(name="UserManagerService")
	private EgovUserManagerService userManagerService;
	
	@Resource(name ="AuthorInfoManageService")
	private AuthorInfoManageService authorInfoManageService;
	
	@Resource(name = "RoleInfoManageService")
	private RoleInfoManageService roleInfoManageService;
	
	@Resource(name="MenuInfoManageService")
	private MenuInfoManageService  menuInfo;
	
	@Resource(name="TMenuInfoManageService")
	private TMenuInfoManageService  tmenuInfo;
	
	@Resource(name="GroupManagerService")
	private GroupManagerService groupManagerService;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name="CmmnDetailCodeManageService")
    private EgovCcmCmmnDetailCodeManageService cmmnDetailCodeManageService;
    
    @Resource(name="CenterInfoManageService")
    private CenterInfoManageService centerInfoManageService;
    
    @Resource(name="MhsCenterInfoManageService")
    private MhsCenterInfoManageService mhsCenterInfoManageService;
    
    
    @Resource(name="egovMessageSource")
	protected EgovMessageSource egovMessageSource;

	@Autowired
	private DefaultBeanValidator beanValidator;
	
	
	@RequestMapping(value="/backoffice/sub/basicManage/manageList.do")
	public String selectUserManagerList(@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("searchVO") GnrMberVO searchVO
			, HttpServletRequest request
			, BindingResult bindingResult
			, ModelMap model
			) throws Exception {  
	
       
	   if(  searchVO.getPageUnit() > 0  ){    	   
	    	   searchVO.setPageUnit(searchVO.getPageUnit());
        }else {
    	   searchVO.setPageUnit(propertiesService.getInt("pageUnit"));   
        }
       searchVO.setPageSize(propertiesService.getInt("pageSize"));   
       
       
       
       
       model.addAttribute("regist", searchVO);
       /** pageing */       
   	  PaginationInfo paginationInfo = new PaginationInfo();
	  paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
	  paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
	  paginationInfo.setPageSize(searchVO.getPageSize());

	  searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
	  searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
	  //수정 	  
	  searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

       model.addAttribute("resultList",   userManagerService.selectUserManageListByPagination(searchVO)  );
       
       model.addAttribute("selectState", cmmnDetailCodeManageService.selectCmmnDetailCombo("COM003"));
       

       int totCnt = userManagerService.selectUserManageListTotCnt_S(searchVO) ;       
	   paginationInfo.setTotalRecordCount(totCnt);
       model.addAttribute("paginationInfo", paginationInfo);
       model.addAttribute("totalCnt", totCnt);
 
	   return "/backoffice/sub/basicManage/manageList";
	}
	@RequestMapping(value="/backoffice/sub/basicManage/managerCheck.do")
	public String userView(@ModelAttribute("loginVO") LoginVO loginVO
			, GnrMberVO MberVO
			, HttpServletRequest request
			, BindingResult bindingResult
			, ModelMap model
			) throws Exception{
		
		GroupVo groupVo = new GroupVo();
		LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");			    
		if (user != null ){
			groupVo.setParentGroupId(user.getParentGroupId());	
			groupVo.setGroupId(user.getGroupId());
		} else {
			groupVo.setParentGroupId("EMART_00000000000001");
			groupVo.setGroupId("EMART_00000000000002");
		}
		
        model.addAttribute("selectGroup", groupManagerService.selectGroupManageCombo(groupVo));
		model.addAttribute("selectAuthor", authorInfoManageService.selectAuthorIInfoManageCombo());		
		model.addAttribute("selectState", cmmnDetailCodeManageService.selectCmmnDetailCombo("COM003"));
		
		
		
		model.addAttribute("regist", MberVO);
				
		if (MberVO.getMode().equals("Edt")){
			
			GnrMber vo = userManagerService.selectUserManageDetail(MberVO);			
			if (!vo.getAuthorCode().equals("ROLE_ADMIN")){ 
                CenterInfoVO centerInfoVO = new CenterInfoVO();
                String cenSearchKeyword = request.getParameter("cenSearchKeyword") == null ? "" : request.getParameter("cenSearchKeyword");
                centerInfoVO.setSearchKeyword(cenSearchKeyword);
				model.addAttribute("selectRole", roleInfoManageService.selectRoleIInfoAuthorManageCombo(vo.getAuthorCode())  );
				
				Group group = new Group();
			    group = groupManagerService.selectGroupManageDetail(vo.getGroupId());
				//여기 부분 수정 
			    if (group.getGroupId().equals("EMART_00000000000023") || group.getParentGroupId().equals("EMART_00000000000023")){
			    	model.addAttribute("selectCenter", mhsCenterInfoManageService.selectMhsComboListMeber(vo.getGroupId()) );
			    }else {
			    	model.addAttribute("selectCenter", centerInfoManageService.selectCenterInfoManageCombo(centerInfoVO) );	
			    }
				
							     
			}
			model.addAttribute("regist", vo);			
		}				
		return "/backoffice/sub/basicManage/managerCheck";
	}
	@RequestMapping(value="/backoffice/sub/basicManage/managerUpdate.do")
	public String mangerUpdate (
			 @ModelAttribute("loginVO") LoginVO loginVO
			,@ModelAttribute("GnrMberVO") GnrMberVO vo
			, HttpServletRequest request
			, BindingResult bindingResult
			, ModelMap model
			) throws Exception{
		
		model.addAttribute("regist", vo);
		String meesage = "";
		String url = "redirect:/backoffice/sub/basicManage/roleList.do";
		
		//vo.setMbtlnum(   vo.getMbtlnum1() +"-"+   vo.getMbtlnum2() +"-"  +vo.getMbtlnum3()   );
     	
		//패스워드 변경 업데이트 
		
		
		try{
			
			int ret  = 0;
			
			
			if (vo.getMode().equals("Ins")){
				ret = userManagerService.insertUserManage(vo);
				meesage = "sucess.common.insert";
				url = "forward:/backoffice/sub/basicManage/managerCheck.do";				
			}else {
				 ret = userManagerService.updateUserManage(vo);
				 meesage = "sucess.common.update";
				 url = "forward:/backoffice/sub/basicManage/managerCheck.do";
			}			
			if (ret >0){
				model.addAttribute("status", Globals.STATUS_SUCCESS);
				model.addAttribute("message", egovMessageSource.getMessage(meesage));
			}else {	
				System.out.println();
				throw new Exception();
			}
			
		}catch (Exception e){
			model.addAttribute("status", Globals.STATUS_FAIL);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.insert"));			
			System.out.println("Exception:" + e.toString());
			url = "forward:/backoffice/sub/basicManage/managerCheck.do";
		}finally {
			
		}					
		return url;
	}
	@RequestMapping(value="/backoffice/sub/basicManage/managerDelete.do")
	@ResponseBody
	public String deleteMber(HttpServletRequest request) throws Exception {
		
		String mberId = request.getParameter("mberId") != null ? request.getParameter("mberId") : "";
		int ret = userManagerService.deleteUserManage(mberId)	;
		if (ret> 0){
			return "O";
		}else {
			return "F";
		}
	}
	@RequestMapping(value="/backoffice/sub/basicManage/IdCheck.do")
	@ResponseBody 
	public String selectUserMangerIDCheck(HttpServletRequest request) throws Exception {
						
		String userID = request.getParameter("userID") != null ? request.getParameter("userID") : "";
		int IDCheck = userManagerService.selectUserMangerIDCheck(userID);		
		return Integer.toString( IDCheck ) ;
	}
	
	@RequestMapping(value="/backoffice/sub/basicManage/mhsBrandCombo.do")
	@ResponseBody
	public ModelAndView selectMhsBrandCombo(HttpServletRequest request) throws Exception {
		ModelAndView model = new ModelAndView("jsonView");		
		List<CmmnDetailCode> brandCombo = cmmnDetailCodeManageService.selectCmmnDetailCombo("MHS001");
		model.addObject("selectBrand", brandCombo);
		return model;
	}
	
	
	@RequestMapping(value="/backoffice/sub/basicManage/mhsCenterCombo.do")
	@ResponseBody
	public ModelAndView selectMhsCenterCombo(HttpServletRequest request) throws Exception {
		String mhsBrandcd  = request.getParameter("mhsBrandcd") != null ? request.getParameter("mhsBrandcd") : "EMART_00000000000023";
		ModelAndView model = new ModelAndView("jsonView");
		MhsCenterInfoVO mhsCenterInfoVO = new MhsCenterInfoVO();
		mhsCenterInfoVO.setMhsBrandcd(mhsBrandcd);
		List<MhsCenterInfoVO> centerCombo = mhsCenterInfoManageService.selectMhsCenterList(mhsCenterInfoVO);
		model.addObject("selectCenter", centerCombo);
		return model;
	}
	
	@RequestMapping(value="/backoffice/sub/basicManage/roleCombo.do")
	public ModelAndView selectRollCombo(HttpServletRequest request) throws Exception {
		
		ModelAndView model = new ModelAndView("jsonView");
		String authorCode  = request.getParameter("authorCode") != null ? request.getParameter("authorCode") : "";
        List<RoleInfoVO>  roleCombo  =	roleInfoManageService.selectRoleIInfoAuthorManageCombo(authorCode);         
        model.addObject("roleCombo", roleCombo);
        
        CenterInfoVO centerInfoVO = new CenterInfoVO();
        String cenSearchKeyword = request.getParameter("cenSearchKeyword") == null ? "" : request.getParameter("cenSearchKeyword");
        centerInfoVO.setSearchKeyword(cenSearchKeyword);
        List<CenterInfoVO>  centerCombo  =	centerInfoManageService.selectCenterInfoManageCombo(centerInfoVO);
        model.addObject("centerCombo", centerCombo);        
		return model;
		
	}
	@RequestMapping(value="/backoffice/sub/basicManage/centerCombo.do")
	public ModelAndView selectCenterCombo(HttpServletRequest request) throws Exception {
		
		ModelAndView model = new ModelAndView("jsonView");
		
        CenterInfoVO centerInfoVO = new CenterInfoVO();
        String cenSearchKeyword = request.getParameter("cenSearchKeyword") == null ? "" : request.getParameter("cenSearchKeyword");
        centerInfoVO.setSearchKeyword(cenSearchKeyword);
		List<CenterInfoVO>  centerCombo  =	centerInfoManageService.selectCenterInfoManageCombo(centerInfoVO);
        model.addObject("centerCombo", centerCombo);
		return model;
		
	}
	
	
	
	// include 파일 정리 
	@RequestMapping(value="/backoffice/inc/emart_header.do")
	public String emartHader() throws Exception{		
		return "/backoffice/inc/Emart_header";
	}
	@RequestMapping(value="/backoffice/inc/emart_tree.do")	
	public String emartTree(     ) throws Exception{				
		return "/backoffice/inc/Emart_Tree";		
	}
	@RequestMapping(value="/backoffice/inc/cms_left.do")
	public String cmsLeft() throws Exception{		
		return "/backoffice/inc/Cms_Left";
	}
	@RequestMapping(value="/backoffice/inc/cms_header.do")
	public String cmsHeader() throws Exception{		
		return "/backoffice/inc/cms_header";
	}
	@RequestMapping(value="/backoffice/inc/mhs_header.do")
	public String mhsHader() throws Exception{		
		return "/backoffice/inc/mhs_header";
	}
	
	@RequestMapping(value="/backoffice/inc/emart_treeMu.do")	
	public ModelAndView emartTreeMu( HttpServletRequest request 
			                                                  ) throws Exception{		
		ModelAndView model = new ModelAndView("jsonView");
				
		String menuGubun = request.getParameter("menuGubun") != null ? request.getParameter("menuGubun") : "";
		
		
		
		 LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");			    
		 if (loginVO == null ){
			 System.out.println("로그인 기록 없음");		    			    	
		 }
		
			
		if (menuGubun.equals("TMnu")){
			List<TMenuInfoVO> menuTree = tmenuInfo.selectLeftTMenuLst(loginVO);
			model.addObject("menuTree", menuTree);
			return model;
		}else {			
			List<MenuInfoVO> menuTree = menuInfo.selectLeftMenuLst(loginVO);
			model.addObject("menuTree", menuTree);
			return model;
		}
		
	}
	
	@RequestMapping(value="/backoffice/inc/emart_footer.do")
	public String emartFooter() throws Exception{		
		return "/backoffice/inc/Emart_Footer";
	}	
	
}
