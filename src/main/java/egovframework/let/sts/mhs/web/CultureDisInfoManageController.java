package egovframework.let.sts.mhs.web;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.annotation.Resource;



import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.Globals;
import egovframework.let.cmm.use.service.Group;
import egovframework.let.cmm.use.service.GroupManagerService;
import egovframework.let.cmm.use.service.GroupVo;
import egovframework.let.sym.did.service.DidInfo;
import egovframework.let.sym.did.service.DidInfoVO;
import egovframework.let.sym.did.service.DidInfoManageService;
import egovframework.let.sym.grp.service.GroupDidInfo;
import egovframework.let.sym.grp.service.GroupDidInfoManageService;
import egovframework.let.sym.cnt.service.CenterInfoManageService;
import egovframework.let.sym.cnt.service.CenterInfoVO;
import egovframework.let.sym.sch.service.ScheduleInfoManageService;
import egovframework.let.sts.cnt.service.ContentFileInfo;
import egovframework.let.sts.met.service.MeetViewInfo;
import egovframework.let.sts.mhs.service.MhsCenterInfo;
import egovframework.let.sts.mhs.service.MhsCenterInfoManageService;
import egovframework.let.sts.mhs.service.MhsCenterInfoVO;
import egovframework.let.sts.mhs.service.MhsClassInfo;
import egovframework.let.sts.mhs.service.MhsClassInfoManageService;
import egovframework.let.sts.mhs.service.MhsClassInfoVO;
import egovframework.let.sts.mhs.service.MhsMonitorInfo;
import egovframework.let.sts.mhs.service.MhsMonitorInfoManageService;
import egovframework.let.sts.mhs.service.MhsMonitorInfoVO;
import egovframework.let.sts.mhs.service.MhsViewConnInfo;
import egovframework.let.sts.mhs.service.MhsViewConnInfoManageService;
import egovframework.let.sts.mhs.service.MhsViewConnInfoVO;
import egovframework.let.sts.snd.service.SendMsgInfo;
import egovframework.let.sts.snd.service.SendMsgInfoManageService;
import egovframework.let.sts.xml.service.XmlInfoManageService;
import egovframework.let.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;
import egovframework.let.sym.mnu.service.MenuInfoVO;
import egovframework.let.sym.rnt.service.RoleInfoManageService;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.SystemOutLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.google.gson.Gson;

import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import java.io.*;
import java.net.*;

@Controller
public class CultureDisInfoManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CultureDisInfoManageController.class);


	@Resource(name="CmmnDetailCodeManageService")
    private EgovCcmCmmnDetailCodeManageService cmmnDetailCodeManageService;
	
	@Resource(name="egovMessageSource")
	protected EgovMessageSource egovMessageSource;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;
	
	@Resource(name="egovUsrCnfrmMhsClassService")
	private EgovIdGnrService mhsClassService;    
	
	/** MAKE SERVICE */
	@Resource(name="MhsMonitorInfoManageService")
	private MhsMonitorInfoManageService mhsMonitorInfoManageService;
	
	@Resource(name="MhsClassInfoManageService")
	private MhsClassInfoManageService mhsClassInfoManageService;
	
	@Resource(name="MhsCenterInfoManageService")
	private MhsCenterInfoManageService mhsCenterInfoManageService;
	
	@Resource(name="MhsViewConnInfoManageService")
	private MhsViewConnInfoManageService viewConn;
	
	//조직 저보 정리
	@Resource(name = "GroupManagerService")
    private GroupManagerService groupManagerService;
	
	@RequestMapping ("/backoffice/sub/roomManage/mointerUpdate.do")
	public ModelAndView mointerUpdate (@RequestBody MhsMonitorInfo vo
                                       , HttpServletRequest request) throws Exception {
	
		ModelAndView mv = new ModelAndView("jsonView");		
		try{
			LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");	
			
			if (user != null ){
			String meesage = "";
			if (vo.getMode().equals("Ins")){
				vo.setMhsMregid(user.getMberId());
				meesage = "sucess.common.insert";			    	
			}else {
				vo.setMhsMupdateid(user.getMberId());
				meesage = "sucess.common.update";
			}
			int ret = mhsMonitorInfoManageService.updateMhsMonitorInfo(vo);			
			if (ret > 0){
				mv.addObject("status", "SUCCESS");
				mv.addObject("meesage", meesage);
			}else {
				mv.addObject("status", "FAIL");
				mv.addObject("message", egovMessageSource.getMessage("fail.common.insert"));
			}			    
			}else {
			    mv.addObject("status", "LOGIN FAIL");	
			}		
		}catch(Exception e){
			LOGGER.debug("updateMhsCenterInfo error:" + e.toString());
			mv.addObject("status", "FAIL");
			mv.addObject("message", egovMessageSource.getMessage("fail.common.insert"));	
		}
		return mv;
	}	
	@RequestMapping ("/backoffice/sub/roomManage/mointerDelete.do")
	@ResponseBody
	public String deleteMoniter(HttpServletRequest request) throws Exception {
		
		String returnTxt = "";
		try{
			LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");	
			
			if (user != null ){
				String mhsMonitorcd = !request.getParameter("mhsMonitorcd").equals("") ? request.getParameter("mhsMonitorcd") : ""; 
				int ret = mhsMonitorInfoManageService.deleteMhsMonitorInfo(mhsMonitorcd);
				if (ret > 0){
					returnTxt = "SUCCESS";	
				}else {
					returnTxt = "FAIL";
				}
			}else {
				returnTxt = "LOGIN FAIL";			
			}			
		}catch(Exception e){
			LOGGER.debug("deleteMhsCenterInfo error:" + e.toString());
			returnTxt = "FAIL";
		}
		return returnTxt;	
	}
	@RequestMapping ("/backoffice/sub/roomManage/monitorInfo.do")
	public ModelAndView selectMhsMonitorInfo(@ModelAttribute("loginVO") LoginVO loginVO
											 , @ModelAttribute("searchVO") MhsMonitorInfo searchVO
											 , HttpServletRequest request
											 , BindingResult result
											 , ModelMap model) throws Exception {

			LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");		
			ModelAndView mv = new ModelAndView("jsonView");
			String mhsMonitorcd =  request.getParameter("mhsMonitorcd") != null ? request.getParameter("mhsMonitorcd") : "";
			
			try{
			if (user != null ){
				    MhsMonitorInfo moniterInfo = new MhsMonitorInfo();
				    if (!mhsMonitorcd.equals("") && mhsMonitorcd != null){
				    	moniterInfo = mhsMonitorInfoManageService.selectMhsMonitorInfo(mhsMonitorcd);
						mv.addObject("status", "SUCCESS");
						mv.addObject("result", moniterInfo);	
				    }
					MhsCenterInfoVO mhsCenterInfoVO = new MhsCenterInfoVO();
					mhsCenterInfoVO.setMhsParentbrandcd(user.getParentGroupId());
					mhsCenterInfoVO.setMhsBrandcd(user.getGroupId());
					mhsCenterInfoVO.setMhsBrandcd(moniterInfo.getMhsBrandcd());					
					mv.addObject("centerList", mhsCenterInfoManageService.selectMhsCenterList(mhsCenterInfoVO));
			
			}else {
			       mv.addObject("status", "LOGIN FAIL");		    	
			
			}		
			}catch (Exception e){
			LOGGER.error("selectMhsClassInfo error:" + e.toString());
			}				  			    
			return mv;
		
	}
	@RequestMapping ("/backoffice/sub/roomManage/monitorList.do")
	public String selectMhsMonitorListByPagination(@ModelAttribute("loginVO") LoginVO loginVO
														, @ModelAttribute("searchVO") MhsMonitorInfoVO searchVO
														, HttpServletRequest request
														, BindingResult result
														, ModelMap model) throws Exception {

		LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");			    
		if (user != null ){
			searchVO.setAuthorCode(user.getAuthorCode());
			searchVO.setGroupId(user.getGroupId());
			searchVO.setParentGroupId(user.getParentGroupId());
			searchVO.setMhsBrandcd(user.getGroupId());
			searchVO.setMhsCentercd(user.getCenterId());
		}else {
			System.out.println("로그인 기록 없음");		    	
			return "/backoffice/login";
		}
		
		
		if(searchVO.getPageUnit() > 0){    	   
			searchVO.setPageUnit(searchVO.getPageUnit());
		} else {
			searchVO.setPageUnit(propertiesService.getInt("pageUnit"));   
		}
		searchVO.setPageSize(propertiesService.getInt("pageSize"));
		
		if(searchVO.getMhsBrandcd()	 == null) {searchVO.setMhsBrandcd("");}
		if(searchVO.getMhsCentercd() == null) {searchVO.setMhsCentercd("");}
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		
	    
		try{
			
			MhsCenterInfoVO searchCenter  = new MhsCenterInfoVO();
			searchCenter.setAuthorCode(user.getAuthorCode());
			searchCenter.setGroupId(user.getGroupId());
			searchCenter.setMhsParentbrandcd(user.getParentGroupId());
			model.addAttribute("groupInfo",  mhsCenterInfoManageService.selectMhsBrandList(searchCenter) );
			
			model.addAttribute("list", mhsMonitorInfoManageService.selectMhsMonitorList(searchVO));
			int totCnt = mhsMonitorInfoManageService.selectMhsMonitorListCnt(searchVO);       
			paginationInfo.setTotalRecordCount(totCnt);
		    model.addAttribute("paginationInfo", paginationInfo);
		    model.addAttribute("totalCnt", totCnt);
			
		} catch (Exception e){
			LOGGER.info("selectMhsMonitorListByPagination ERROR : " + e.toString());
		}
		
		model.addAttribute("regist", searchVO);
		
		return "/backoffice/sub/roomManage/monitorList";
	}
	
	/*@RequestMapping ("/backoffice/sub/roomManage/monitorInfo.do")
	public String selectMhsMonitorInfo(@ModelAttribute("loginVO") LoginVO loginVO
														, @ModelAttribute("searchVO") DidInfoVO searchVO
														, HttpServletRequest request
														, BindingResult result
														, ModelMap model) throws Exception {

		LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");			    
		if (user != null ){
			searchVO.setAuthor_Code(user.getAuthorCode());
			searchVO.setGroupCode(user.getGroupId());
			searchVO.setParentGroupId(user.getParentGroupId());
		}else {
			System.out.println("로그인 기록 없음");		    	
			return "/backoffice/login";
		}			  			    
		
		return "/backoffice/sub/roomManage/monitorInfo";
	}*/
	
	@RequestMapping ("/backoffice/sub/roomManage/monitorDetail.do")
	public String selectMhsMonitorDetail(@ModelAttribute("loginVO") LoginVO loginVO
										 , @ModelAttribute("searchVO") MhsMonitorInfoVO searchVO
										 , HttpServletRequest request
										 , BindingResult result
										 , ModelMap model) throws Exception {

		LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");			    
		if (user != null ){
			MhsCenterInfoVO searchCenter  = new MhsCenterInfoVO();
			searchCenter.setAuthorCode(user.getAuthorCode());
			searchCenter.setGroupId(user.getGroupId());
			searchCenter.setMhsParentbrandcd(user.getParentGroupId());
			model.addAttribute("groupInfo",  mhsCenterInfoManageService.selectMhsBrandList(searchCenter) );
			model.addAttribute("registInfo", mhsMonitorInfoManageService.selectMhsMonitorInfo(searchVO.getMhsMonitorcd()) );
			model.addAttribute("regist", searchVO);
			
			
			
		}else {
			System.out.println("로그인 기록 없음");		    	
			return "/backoffice/login";
		}			  			    
		
		return "/backoffice/sub/roomManage/monitorDetail";
	}
	@RequestMapping(value="/backoffice/sub/roomManage/preView.do")
	public String preView( @ModelAttribute("loginVO") LoginVO loginVO
							, HttpServletRequest request
							, BindingResult result
							, ModelMap model) throws Exception {
				
			String errorStr = "ERR_RETURN";		
			
			SimpleDateFormat format1 = new SimpleDateFormat ("yyyyMMdd");
			Date time = new Date();
			String today = format1.format(time);
			String mhsMonitorcd = request.getParameter("mhsMonitorcd") == null ? errorStr : request.getParameter("mhsMonitorcd");
			String searchDay 		= request.getParameter("searchDay") == null ? today : request.getParameter("searchDay");
			
			MhsViewConnInfoVO conn = new MhsViewConnInfoVO();
			conn.setSearchDay(searchDay);
			conn.setMhsMonitorcd(mhsMonitorcd);
			
			LOGGER.debug("getSearchDay:" + conn.getSearchDay());
			LOGGER.debug("getMhsMonitorcd:" + conn.getMhsMonitorcd());
			
			try{
				model.addAttribute("status", "SUCCESS");
				model.addAttribute("regist", mhsMonitorInfoManageService.selectMhsMonitorInfo(mhsMonitorcd));
				model.addAttribute("searchDay",searchDay);
				model.addAttribute("resultList", new Gson().toJson(viewConn.selectViewMoniterClassInfo(conn)));
				model.addAttribute("pageInfo", new Gson().toJson(viewConn.selectViewMoniterClassUninPageInfo(conn)));
				
			}catch(Exception e){
				LOGGER.debug("preView error:" + e.toString());
				model.addAttribute("status", "FAIL");
			}
			return "/backoffice/sub/roomManage/preView";			
	}
	@RequestMapping(value="/backoffice/sub/roomManage/preViewJson.do")
	public ModelAndView preViewJson( HttpServletRequest request) throws Exception {
		
		ModelAndView model = new ModelAndView("jsonView");
		String errorStr = "ERR_RETURN";		
		SimpleDateFormat format1 = new SimpleDateFormat ("yyyyMMdd");
		Date time = new Date();
		String today = format1.format(time);		
		
		String mhsMonitorcd 		= request.getParameter("mhsMonitorcd") == null ? errorStr : request.getParameter("mhsMonitorcd");
		String searchDay 		= request.getParameter("searchDay") == "" ? today : request.getParameter("searchDay");		
		MhsViewConnInfoVO conn = new MhsViewConnInfoVO();
		conn.setSearchDay(searchDay);
		conn.setMhsMonitorcd(mhsMonitorcd);
		
		LOGGER.debug("getSearchDay:" + conn.getSearchDay());
		LOGGER.debug("getMhsMonitorcd:" + conn.getMhsMonitorcd());
		
		
		try{
			model.addObject("status", "SUCCESS");
			model.addObject("moniterInfo", mhsMonitorInfoManageService.selectMhsMonitorInfo(mhsMonitorcd));
			model.addObject("resultList", viewConn.selectViewMoniterClassInfo(conn));
			model.addObject("pageInfo", viewConn.selectViewMoniterClassUninPageInfo(conn));
		}catch(Exception e){
			LOGGER.debug("selectViewConnInfo error:" + e.toString());
			model.addObject("status", "FAIL");
		}
		return model;			
	}
	@RequestMapping ("/backoffice/sub/roomManage/viewConnInfo.do")
	public ModelAndView selectViewConnInfo(@RequestBody MhsViewConnInfoVO searchVO
			                               , HttpServletRequest request
											, BindingResult result
											, ModelMap model) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
		LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");
		try{
			
			mv.addObject("status", "SUCCESS");
			mv.addObject("resultList", viewConn.selectViewMoniterClassInfo(searchVO));
			
		}catch(Exception e){
			LOGGER.debug("selectViewConnInfo error:" + e.toString());
			mv.addObject("status", "FAIL");
			mv.addObject("message", egovMessageSource.getMessage("fail.common.insert"));
		}
 		return mv;
	}
	@RequestMapping ("/backoffice/sub/roomManage/viewConnInsert.do")
	public ModelAndView insertViewConnInfo(@RequestBody MhsViewConnInfo vo
			                               , HttpServletRequest request
										   , BindingResult result
										   , ModelMap model) throws Exception {
		
		
		ModelAndView mv = new ModelAndView("jsonView");
		try{
			LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");	
			
			if (user != null ){
			    String meesage = "";
				vo.setMhsDataregid(user.getMberId());
				meesage = "sucess.common.insert";			    	
			
			int ret = viewConn.insertMoniterClassInfo(vo);			
			if (ret > 0){
				mv.addObject("status", "SUCCESS");
				mv.addObject("meesage", meesage);
			}else {
				mv.addObject("status", "FAIL");
				mv.addObject("message", egovMessageSource.getMessage("fail.common.insert"));
			}			    
			}else {
			    mv.addObject("status", "LOGIN FAIL");	
			}		
		}catch(Exception e){
			LOGGER.debug("updateMhsCenterInfo error:" + e.toString());
			mv.addObject("status", "FAIL");
			mv.addObject("message", egovMessageSource.getMessage("fail.common.insert"));	
		}
		return mv;
	}
	@RequestMapping ("/backoffice/sub/roomManage/viewConnDelete.do")
	@ResponseBody
	public String viewConnDelete(HttpServletRequest request) throws Exception {
		
		String returnTxt = "";
		try{
			LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");	
			
			if (user != null ){
				String mhsConnSeq = !request.getParameter("mhsConnSeq").equals("") ? request.getParameter("mhsConnSeq") : ""; 
				int ret = viewConn.deleteMoniterClassInfo(mhsConnSeq);
				if (ret > 0){
					returnTxt = "SUCCESS";	
				}else {
					returnTxt = "FAIL";
				}
			}else {
				returnTxt = "LOGIN FAIL";			
			}			
		}catch(Exception e){
			LOGGER.debug("deleteMhsCenterInfo error:" + e.toString());
			returnTxt = "FAIL";
		}
		return returnTxt;	
	}
	
	
	@RequestMapping ("/backoffice/sub/roomManage/classList.do")
	public String selectMhsClassListByPagination(@ModelAttribute("loginVO") LoginVO loginVO
														, @ModelAttribute("searchVO") MhsClassInfoVO searchVO
														, HttpServletRequest request
														, BindingResult result
														, ModelMap model) throws Exception {

		LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");			    
		try{
			if (user != null ){
				
				
				model.addAttribute("regist", searchVO);
				System.out.println(searchVO.getMhsClasscd());
				MhsCenterInfoVO searchCenter  = new MhsCenterInfoVO();
				searchCenter.setAuthorCode(user.getAuthorCode());
				searchCenter.setGroupId(user.getGroupId());
				searchCenter.setMhsParentbrandcd(user.getParentGroupId());
				
				//20191024 JDH
				searchVO.setMhsCentercd(user.getCenterId());
			     System.out.println(searchVO.getMhsCentercd());
			    if(  searchCenter.getPageUnit() > 0  ){    	   
					  searchVO.setPageUnit(searchVO.getPageUnit());
				}else {
					   searchVO.setPageUnit(propertiesService.getInt("pageUnit"));   
				}
				searchVO.setPageSize(propertiesService.getInt("pageSize"));
				  
				PaginationInfo paginationInfo = new PaginationInfo();
				paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
				paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
				paginationInfo.setPageSize(searchVO.getPageSize());
				
				searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
				searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
				searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
				
				LOGGER.debug("searchKeyword:" + searchVO.getSearchKeyword());
				LOGGER.debug("searchCondition:" + searchVO.getSearchCondition());
				
				int totCnt = mhsClassInfoManageService.selectMhsClassListCnt(searchVO);       
				
				paginationInfo.setTotalRecordCount(totCnt);
			    model.addAttribute("paginationInfo", paginationInfo);
			    model.addAttribute("totalCnt", totCnt);	
				model.addAttribute("groupInfo",  mhsCenterInfoManageService.selectMhsBrandList(searchCenter) );
				
				model.addAttribute("moniterInfo",  mhsMonitorInfoManageService.selectMhsMonitorNm(user.getMberId()));
				model.addAttribute("resultList",   mhsClassInfoManageService.selectMhsClassList(searchVO));
				
			}else {	    	
				return "redirect:/backoffice/login.do";
			}	
		}catch (Exception e){
			LOGGER.error("selectMhsClassInfo error:" + e.toString());
		}					  			    
		return "/backoffice/sub/roomManage/classList";
	}
	//
	@RequestMapping ("/backoffice/sub/roomManage/classInfo.do")
	public ModelAndView selectMhsClassInfo(@ModelAttribute("loginVO") LoginVO loginVO
														, @ModelAttribute("searchVO") MhsClassInfoVO searchVO
														, HttpServletRequest request
														, BindingResult result
														, ModelMap model) throws Exception {

		LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");		
		ModelAndView mv = new ModelAndView("jsonView");
		String mhsClasscd =  request.getParameter("mhsClasscd") != null ? request.getParameter("mhsClasscd") : "";
		
		try{
			if (user != null ){
				    MhsClassInfo classInfo = new MhsClassInfo();
				    classInfo = mhsClassInfoManageService.selectMhsClassInfo(mhsClasscd);
					mv.addObject("status", "SUCCESS");
					mv.addObject("result", classInfo);
					
					MhsCenterInfoVO mhsCenterInfoVO = new MhsCenterInfoVO();
					mhsCenterInfoVO.setMhsParentbrandcd(user.getParentGroupId());
					mhsCenterInfoVO.setMhsBrandcd(user.getGroupId());
					mhsCenterInfoVO.setMhsBrandcd(classInfo.getMhsBrandcd());					
					mv.addObject("centerList", mhsCenterInfoManageService.selectMhsCenterList(mhsCenterInfoVO));
					
				
			}else {
				mv.addObject("status", "LOGIN FAIL");		    	
				
			}		
		}catch (Exception e){
			LOGGER.error("selectMhsClassInfo error:" + e.toString());
		}				  			    
		return mv;
	}
	@RequestMapping ("/backoffice/sub/roomManage/mhsGroupInfo.do")
	public ModelAndView selectMhsGroupInfo(HttpServletRequest request) throws Exception {
		
		   String groupId = !request.getParameter("groupId").equals("") ? request.getParameter("groupId") : "";
		   ModelAndView mv = new ModelAndView("jsonView");
		   
		   try{
			   LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");	
				
				if (user != null ){
					
					mv.addObject("status", "SUCCESS");
					mv.addObject("groupInfo", groupManagerService.selectGroupManageDetail(groupId));
				}else {
					mv.addObject("status", "LOGIN FAIL");	
				}
		   }catch(Exception e) {
			   LOGGER.debug("updateMhsCenterInfo error:" + e.toString());
			   mv.addObject("status", "FAIL");
			   mv.addObject("message", egovMessageSource.getMessage("fail.common.insert"));
		   }
		return mv;
	}
	@RequestMapping ("/backoffice/sub/roomManage/classCombo.do")
	public ModelAndView selectClassCombo(@RequestBody MhsClassInfo vo
                                     , HttpServletRequest request) throws Exception {
		
		   ModelAndView mv = new ModelAndView("jsonView");
		   LOGGER.debug("vo:" + vo.getMhsBrandcd());
		   LOGGER.debug("vo:" + vo.getMhsBrandcd());
		   try{
				LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");	
				
				if (user != null ){
				
					mv.addObject("status", "SUCCESS");
					mv.addObject("result", mhsClassInfoManageService.selectMhsMoniterClassList(vo));
				}else {
					mv.addObject("status", "LOGIN FAIL");	
				}
			}catch(Exception e){
				LOGGER.debug("updateMhsCenterInfo error:" + e.toString());
				mv.addObject("status", "FAIL");
				mv.addObject("message", egovMessageSource.getMessage("fail.common.insert"));	
			}
			return mv;
		   
	}
	@RequestMapping ("/backoffice/sub/roomManage/classDelete.do")
	@ResponseBody 
    public String deleteMhsClass(HttpServletRequest request) throws Exception {
		
		String returnTxt = "";
		try{
			LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");	
			
			if (user != null ){
				String mhsClasscd = !request.getParameter("mhsClasscd").equals("") ? request.getParameter("mhsClasscd") : ""; 
				int ret = mhsClassInfoManageService.deleteMhsClassInfo(mhsClasscd);
				if (ret > 0){
					returnTxt = "SUCCESS";	
				}else {
					returnTxt = "FAIL";
				}
			}else {
				returnTxt = "LOGIN FAIL";			
			}			
		}catch(Exception e){
			LOGGER.debug("deleteMhsCenterInfo error:" + e.toString());
			returnTxt = "FAIL";
		}
		return returnTxt;		
	}
	@RequestMapping ("/backoffice/sub/roomManage/classUpdate.do")
	public ModelAndView classUpdate (@RequestBody MhsClassInfo vo
                                     , HttpServletRequest request) throws Exception {
    	
			ModelAndView mv = new ModelAndView("jsonView");
			
			
			try{
				LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");	
				
				if (user != null ){
				String meesage = "";
				if (vo.getMode().equals("Ins")){
					String mhsClasscd =   mhsClassService.getNextStringId(); //테이블 1행 샘성  ID제너레이션
					vo.setMhsClasscd(mhsClasscd);
					vo.setMhsRegid(user.getMberId());
					meesage = "sucess.common.insert";			    	
				}else {
					vo.setMhsUpdateid(user.getMberId());
					meesage = "sucess.common.update";
				}		
			    LOGGER.debug("MhsClassInfo:" + vo.getMhsBrandcd() +","+vo.getMhsCentercd()+","+vo.getMhsClassstartday());
				
				int ret = mhsClassInfoManageService.updateMhsClassInfo(vo);
				LOGGER.debug("ret:" + ret);
				if (ret > 0){
					mv.addObject("classInfo",vo); //2019.10.21 JDH
					mv.addObject("status", "SUCCESS");
					mv.addObject("meesage", meesage);
				}else {
					mv.addObject("status", "FAIL");
					mv.addObject("message", egovMessageSource.getMessage("fail.common.insert"));
				}			    
				}else {
				    mv.addObject("status", "LOGIN FAIL");	
				}
			
			}catch(Exception e){
				LOGGER.debug("updateMhsCenterInfo error:" + e.toString());
				mv.addObject("status", "FAIL");
				mv.addObject("message", egovMessageSource.getMessage("fail.common.insert"));	
			}
			return mv;
	}
	
	@RequestMapping ("/backoffice/sub/roomManage/parentCenterInfo.do")
	public ModelAndView selectMhsCenterInfoCombo( HttpServletRequest request) throws Exception {
		
		LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");
		String mhsBrandcd =  request.getParameter("mhsBrandcd") != null ? request.getParameter("mhsBrandcd") : "";
		String subMode =  request.getParameter("subMode") != null ? request.getParameter("subMode") : "";
		String mhsCentercd =  request.getParameter("mhsCentercd") != null ? request.getParameter("mhsCentercd") : "";
		
		
		
		ModelAndView mv = new ModelAndView("jsonView");
		try{
			if (user != null ){
					mv.addObject("status", "SUCCESS");
					mv.addObject("result", mhsCenterInfoManageService.selectMhsComboList(mhsBrandcd));
				if (subMode.equals("Edt")){
					LOGGER.debug("mhsBrandcd:" + mhsBrandcd);
					LOGGER.debug("subMode:" + subMode);
					LOGGER.debug("mhsCentercd:" + mhsCentercd);
					
					mv.addObject("centerInfo", mhsCenterInfoManageService.selectMhsCenterInfo(mhsCentercd) );	
				}
				
				
			}else {
				mv.addObject("status", "LOGIN FAIL");		    	
				
			}	
		}catch(Exception e){
			LOGGER.debug("selectMhsCenterInfoCombo Error:" + e.toString());
			mv.addObject("status", "FAIL");		    	
		}
		
		return mv; 
	}
	@RequestMapping ("/backoffice/sub/roomManage/centerDelete.do")
	@ResponseBody
	public String deleteMhsCenterInfo(HttpServletRequest request) throws Exception {
		
		
		String returnTxt = "";
		try{
			LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");	
			
			if (user != null ){
				String mhsCentercd = !request.getParameter("mhsCentercd").equals("") ? request.getParameter("mhsCentercd") : ""; 
				int ret = mhsCenterInfoManageService.deleteMhsCenter(mhsCentercd);
				if (ret > 0){
					returnTxt = "SUCCESS";	
				}else {
					returnTxt = "FAIL";
				}
			}else {
				returnTxt = "LOGIN FAIL";			
			}			
		}catch(Exception e){
			LOGGER.debug("deleteMhsCenterInfo error:" + e.toString());
			returnTxt = "FAIL";
		}
		return returnTxt;		
	}
	
	@RequestMapping ("/backoffice/sub/roomManage/mhsGroupDelete.do")
	@ResponseBody
    public String deleteMhsGroupInfo(HttpServletRequest request) throws Exception {
		
		String returnTxt = "";
		try{
			LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");	
			
			if (user != null ){
				String groupId = !request.getParameter("groupId").equals("") ? request.getParameter("groupId") : ""; 
				int ret = groupManagerService.deleteGroupManage(groupId);
				if (ret > 0){
					returnTxt = "SUCCESS";	
				}else {
					returnTxt = "FAIL";
				}
			}else {
				returnTxt = "LOGIN FAIL";			
			}			
		}catch(Exception e){
			LOGGER.debug("deleteMhsCenterInfo error:" + e.toString());
			returnTxt = "FAIL";
		}
		return returnTxt;		
	}
	
    @RequestMapping ("/backoffice/sub/roomManage/mhsGroupUpdate.do")
    public ModelAndView updateMhsGroupInfo(@RequestBody Group vo
	                                       , HttpServletRequest request) throws Exception {
    	
            ModelAndView mv = new ModelAndView("jsonView");
		
			try{
				LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");	
				
				if (user != null ){
					String meesage = "";
					int ret = 0;
				    if (vo.getMode().equals("Ins")){
				    	meesage = "sucess.common.insert";
				    	ret = groupManagerService.insertGroupManageMhs(vo);
				    	//상위 부서 점포 생성 
				    	String branchCD = groupManagerService.selectMaxGroupId();
				    	MhsCenterInfo centerInfo = new MhsCenterInfo();
				    	centerInfo.setMhsCenterregid(user.getMberId());
				    	centerInfo.setSubMode("Ins");
				    	centerInfo.setMhsCenternm(vo.getGroupNm()+" 총괄점");
				    	centerInfo.setMhsParentcentercd("0");
				    	centerInfo.setMhsBrandcd(branchCD);
				    	centerInfo.setMhsCenterstatus("Y");
				    	
				    	ret = mhsCenterInfoManageService.updateMhsCenter(centerInfo);
				    	
				    	centerInfo = null;
				    	
				    }else {
				    	
				    	meesage = "sucess.common.update";
				    	ret = groupManagerService.updateGroupManage(vo);
				    }				
				    	    
				    if (ret > 0){
				    	mv.addObject("status", "SUCCESS");
				    	mv.addObject("meesage", meesage);
				    }else {
				    	mv.addObject("status", "FAIL");
						mv.addObject("message", egovMessageSource.getMessage("fail.common.insert"));
				    }			    
				}else {
					mv.addObject("status", "LOGIN FAIL");	
				}
				
			}catch(Exception e){
				LOGGER.debug("updateMhsCenterInfo error:" + e.toString());
				mv.addObject("status", "FAIL");
				mv.addObject("message", egovMessageSource.getMessage("fail.common.insert"));	
			}
			return mv;
    	
    }
	@RequestMapping ("/backoffice/sub/roomManage/centerUpdate.do")
	public ModelAndView updateMhsCenterInfo(@RequestBody MhsCenterInfo vo
									        , HttpServletRequest request) throws Exception {
	
		ModelAndView mv = new ModelAndView("jsonView");
		
		LOGGER.debug("0:");
		try{
			LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");	
			LOGGER.debug("vo:" + vo.getMhsCenternm());
			LOGGER.debug("vo:" + vo.getMhsBrandcd());
			if (user != null ){
				String meesage = "";
			    if (vo.getSubMode().equals("Ins")){
			    	vo.setMhsCenterregid(user.getMberId());
			    	meesage = "sucess.common.insert";			    	
			    }else {
			    	vo.setMhsCenterupdateid(user.getMberId());
			    	meesage = "sucess.common.update";
			    }				
			    
			    int ret = mhsCenterInfoManageService.updateMhsCenter(vo);
			    
			    if (ret > 0){
			    	mv.addObject("status", "SUCCESS");
			    	mv.addObject("meesage", egovMessageSource.getMessage(meesage));
			    }else {
			    	mv.addObject("status", "FAIL");
					mv.addObject("message", egovMessageSource.getMessage("fail.common.insert"));
			    }			    
			}else {
				LOGGER.debug("user: null" );
				mv.addObject("status", "LOGIN FAIL");	
			}
			
		}catch(Exception e){
			LOGGER.debug("updateMhsCenterInfo error:" + e.toString());
			mv.addObject("status", "FAIL");
			mv.addObject("message", egovMessageSource.getMessage("fail.common.insert"));	
		}
		return mv;
	}
	@RequestMapping ("/backoffice/sub/roomManage/centerList.do")
	public String selectMhsCenterInfoManageListByPagination(@ModelAttribute("loginVO") LoginVO loginVO
														, @ModelAttribute("searchVO") MhsCenterInfoVO searchVO
														, HttpServletRequest request
														, BindingResult result
														, ModelMap model) throws Exception {

			LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");			    
			if (user != null ){
				searchVO.setAuthorCode(user.getAuthorCode());
				searchVO.setGroupId(user.getGroupId());
				searchVO.setMhsParentbrandcd(user.getParentGroupId());
			}else {
				System.out.println("로그인 기록 없음");		    	
				return "/backoffice/login";
			}			  			    
			
			try{
				model.addAttribute("list", mhsCenterInfoManageService.selectMhsBrandList(searchVO));
			} catch (Exception e){
				LOGGER.info("selectMhsCenterInfoManageListByPagination ERROR : " + e.toString());
			}
			
			model.addAttribute("regist", searchVO);
			
			return "/backoffice/sub/roomManage/centerList";
	}
	
	@RequestMapping(value="/backoffice/sub/roomManage/actionMhsCenter.do")
	@ResponseBody
	public ModelAndView actionMhsCenter(HttpServletRequest request) throws Exception{
		
		ModelAndView model = new ModelAndView("jsonView");
		MhsCenterInfoVO mhsCenterInfoVO = new MhsCenterInfoVO();
		GroupVo groupVo = new GroupVo();
		LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");
		
		String errorStr = "ERR_RETURN";
		
		String actionMode	= request.getParameter("mode")	== null ? errorStr : request.getParameter("mode");
		String brandCd 		= request.getParameter("brand") == null ? errorStr : request.getParameter("brand");
		String centerNm 	= request.getParameter("name")	== null ? errorStr : request.getParameter("name");
		String centerCd 	= request.getParameter("code")	== null ? errorStr : request.getParameter("code");
		
		if (user != null ){
			groupVo.setParentGroupId(user.getParentGroupId());	
			groupVo.setGroupId(user.getGroupId());
			mhsCenterInfoVO.setMhsParentbrandcd(user.getParentGroupId());
			mhsCenterInfoVO.setMhsBrandcd(user.getGroupId());
			
			mhsCenterInfoVO.setMhsCentercd(user.getCenterId());
			
		} else {
			// 로그인 정보 없음, 로그아웃 처리
			
		}
		
		if(actionMode.equals(errorStr) || actionMode.equals("")){
			// ERROR, MODE 정보 누락
			
		} else if (actionMode.equals("s")) {
			if(brandCd.equals(errorStr) || brandCd.equals("")){
				// ERROR, BRANDCD 정보 누락 - 조직 선택, 동일 현상 시 시스템 문의 
			} else {
				mhsCenterInfoVO.setMhsBrandcd(brandCd);
				model.addObject("centerList", mhsCenterInfoManageService.selectMhsCenterList(mhsCenterInfoVO));	
			}
		} else if (actionMode.equals("i")) {
			
		} else if (actionMode.equals("u")) {
			
		} else if (actionMode.equals("d")) {
			
		} else {
			// ERROR, mode 정보 오표기, 시스템 문의
		}
		
		
		// model.addObject("selectType", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT001") );
	
		return model;
	}
	
	
	
}
