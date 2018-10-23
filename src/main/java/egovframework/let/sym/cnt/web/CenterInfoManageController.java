package egovframework.let.sym.cnt.web;


import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;



import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.Globals;
import egovframework.let.cmm.use.service.GroupManagerService;
import egovframework.let.cmm.use.service.GroupVo;
import egovframework.let.sts.brd.service.BrodAnniversaryManagerService;
import egovframework.let.sts.brd.service.BrodContentDetailManagerService;
import egovframework.let.sts.brd.service.BrodContentInfoManageService;
import egovframework.let.sts.brd.service.BrodScheduleManagerService;
import egovframework.let.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;
import egovframework.let.sym.cnt.service.CenterAnniManagerService;
import egovframework.let.sym.cnt.service.CenterInfo;
import egovframework.let.sym.cnt.service.CenterInfoVO;
import egovframework.let.sym.cnt.service.CenterInfoManageService;
import egovframework.let.sym.grp.service.GroupInfoVO;
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
public class CenterInfoManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CenterInfoManageController.class);
	
	//파일 업로드
	FileUpladController uploadFile = new FileUpladController();
	
	
	@Resource(name = "GroupManagerService")
    private GroupManagerService groupManagerService;

	@Resource(name="CenterInfoManageService")
	private CenterInfoManageService centerInfoManageService;
	
	
	@Resource(name = "RoleInfoManageService")
	private RoleInfoManageService roleInfoManageService;
	
	
	@Resource(name="CmmnDetailCodeManageService")
    private EgovCcmCmmnDetailCodeManageService cmmnDetailCodeManageService;
	
	
	@Resource(name="egovMessageSource")
	protected EgovMessageSource egovMessageSource;
	
	
	@Resource(name="BrodContentInfoService")
	private BrodContentInfoManageService brodContent;
	
	/** 음원 관련 자료 추가 **/
	@Resource(name="CenterAnniManagerService")
	private CenterAnniManagerService centerAnniService;
	
	@Resource(name="BrodContentInfoService")
	private BrodContentInfoManageService brodContentInfo;
	
	
	@Resource(name="BrodScheduleManagerService")
	 private BrodScheduleManagerService brodSchedue;
	
	//나중에 삭제 할수 있는 방법 찾기
	 @Resource(name="BrodContentDetailService")
	 private BrodContentDetailManagerService brodDetail;
	 
	 @Resource(name="BrodAnniversaryManagerService")
     private BrodAnniversaryManagerService anniverInfo;
	
	/** 음원 관련 자료 추가 끝 부분 **/
	
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;
	
	@RequestMapping(value="/backoffice/sub/basicManage/centerList.do")
	public String  selectCenterInfoManageListByPagination(@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("searchVO") CenterInfoVO searchVO
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
		  //searchVO.setLastIndex(paginationInfo.getFirstRecordIndex() + searchVO.getPageSize());
		  searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

	       model.addAttribute("resultList",   centerInfoManageService.selectCenterInfoManageListByPagination(searchVO)  );
	       model.addAttribute("regist", searchVO);
	       
	       int totCnt = centerInfoManageService.selectCenterInfoManageListTotCnt_S(searchVO) ;       
		   paginationInfo.setTotalRecordCount(totCnt);
	       model.addAttribute("paginationInfo", paginationInfo);
	       model.addAttribute("totalCnt", totCnt);
		
		   return "/backoffice/sub/basicManage/centerList";	
	}
	
	//센터 정보 상세
	@RequestMapping (value="/backoffice/sub/basicManage/centerDetail.do")
	public String selectCenterInfoManageDetail(@ModelAttribute("loginVO") LoginVO loginVO
			                                                           , @ModelAttribute("CenterInfoVO")  CenterInfoVO vo
			                                                            , HttpServletRequest request
			                                            				, BindingResult bindingResult
																	  , ModelMap model ) throws Exception{	
		
		GroupVo groupVo = new GroupVo();
		LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");			    
		if (user != null ){
			groupVo.setParentGroupId(user.getParentGroupId());	
			groupVo.setGroupId(user.getGroupId());
		} else {
			groupVo.setParentGroupId("EMART_00000000000001");
			groupVo.setGroupId("EMART_00000000000002");
		}
		model.addAttribute("selectRoleGroup", groupManagerService.selectGroupManageCombo(groupVo));
		
		
		
		//지점 구분 
		model.addAttribute("selectBranchGubun", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT022"));
		
		System.out.println("menuGubun:" + vo.getMenuGubun());
		model.addAttribute("regist", vo);
		if (!vo.getMode().equals("Ins")){			
	     	model.addAttribute("regist", centerInfoManageService.selectCenterInfoManageDetail(vo.getCenterId()));	     	
		}		
		return "/backoffice/sub/basicManage/centerDetail";
	}
	
	@RequestMapping (value="/backoffice/sub/basicManage/centerDeletel.do")
	public String deleteCenterInfoManage(@ModelAttribute("loginVO") LoginVO loginVO,
			CenterInfoVO vo,						
			ModelMap model)throws Exception{
		
		model.addAttribute("regist",vo );
		
		if (StringUtils.equals(vo.getCenterId(), Globals.REGITER_SYSTEM)){
			model.addAttribute("status", Globals.STATUS_FAIL);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.delete.system"));			
			return "redirect:/backoffice/sub/basicManage/centerList.do";
		}
				
		try{
		      int ret = 	centerInfoManageService.deleteCenterInfoManage(vo.getCenterId());		      
		      if (ret > 0 ) {	
		    	  //음원 방송 관련 모든 자료 삭제
		    	  //TB_CENTERANNIVERSARY 관련 컬럼 삭제 추후 기록 필요하면 이쪽 부분 변경		    	  
		    	  centerAnniService.deleteCenterID(vo.getCenterId());		    	  
		    	  //TB_BRODCENTERSCH 관련 작업 Y로 돌려 놓구 배치 안되게 설정
		    	  
		    	  if(brodSchedue.selectBrodScheduleCenterCnt(vo.getCenterId()) > 0)
		    	  {
		    		  brodSchedue.updateBrodScheduleCenterNotUse(vo.getCenterId());  
		    	  }
		    	  //TB_BRODCONTENTDETAIL //추후 자료 필요 없을시 이쪽 부분 이용 삭제 할것 
		    	  //TB_BRODANNIVERSARY
		    	  //TB_BRODSCHEDULE
		    	  
		    	  
		    	  //음원 관련 자료 삭제 끝 
		    	  model.addAttribute("status", Globals.STATUS_SUCCESS);
		    	  model.addAttribute("message", egovMessageSource.getMessage("success.common.delete") );		    	  
		      }else {
		    	  throw new Exception();		    	  
		      }
		}catch (Exception e){
			model.addAttribute("status", Globals.STATUS_FAIL);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.delete"));			
		}		
		
		return "redirect:/backoffice/sub/basicManage/centerList.do";
	}
	
    
	
	@RequestMapping (value="/backoffice/sub/basicManage/centerUpdate.do")
	@SuppressWarnings("finally")
	public String updateCenterInfoManage(
			HttpServletRequest request, MultipartRequest mRequest,
			@ModelAttribute("loginVO") LoginVO loginVO,
			@ModelAttribute("CenterInfoVO") CenterInfoVO vo					
			, BindingResult result,
			ModelMap model) throws Exception{
		
		model.addAttribute("regist", vo);
		String meesage = "";
		String url = "/backoffice/sub/basicManage/centerList";
		
		
		String originalFileName = "";
		String savedFileName = "";
			
    	String realFolder = propertiesService.getString("Globals.fileStorePath") ;
     	 int index = 0 ;
     	 String fileExt = null;		  
     	 
     	 
     	vo.setCenterZipcode(  vo.getCenterZipcode1() +   vo.getCenterZipcode2()  );     	     			     	
    	vo.setCenterImg( uploadFile.uploadFileNm(mRequest.getFiles("centerImg"), realFolder));
		vo.setCenterImgMap( uploadFile.uploadFileNm(mRequest.getFiles("centerImgMap"), realFolder));
		
		try{
			
		
			int ret  = 0;
			if (vo.getMode().equals("Ins")){
				ret = centerInfoManageService.insertCenterInfoManage(vo);
				meesage = "sucess.common.insert";
				url = "redirect:/backoffice/sub/basicManage/centerList.do";				
			}else {
				 ret = centerInfoManageService.updateCenterInfoManage(vo);
				 meesage = "sucess.common.update";
				 url = "redirect:/backoffice/sub/basicManage/centerList.do";
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
			url = "redirect:/backoffice/sub/basicManage/centerList.do";
		}finally {
			return url;	
		}					
		
	}
	//
	@RequestMapping("/backoffice/sub/basicManage/selectCenterTimeInfo.do")
	@ResponseBody  
	public String selectCenterTimeInfo (HttpServletRequest request) throws Exception {
		String centerId = request.getParameter("centerId") != null ? request.getParameter("centerId") : "";
		String centerSearchDay = request.getParameter("centerSearchDay") != null ? request.getParameter("centerSearchDay") : "";
		
		CenterInfo vo = new CenterInfo();
		vo.setCenterId(centerId);
		vo.setCenterSearchDay(centerSearchDay);		
		return (centerInfoManageService.selectCenterTimeInfo(vo));
		
	}
	
	
	
	@RequestMapping(value="/backoffice/sub/equiManage/centerSearch.do")
	public ModelAndView selectCenterSearchList( HttpServletRequest request) throws Exception{
        ModelAndView model = new ModelAndView("jsonView");
        CenterInfoVO centerInfoVO = new CenterInfoVO();
        String cenSearchKeyword = request.getParameter("cenSearchKeyword") == null ? "" : request.getParameter("cenSearchKeyword");
        centerInfoVO.setSearchKeyword(cenSearchKeyword);
        return model.addObject("resultMap", centerInfoManageService.selectCenterInfoManageCombo(centerInfoVO));
		
	}
	
	
}
