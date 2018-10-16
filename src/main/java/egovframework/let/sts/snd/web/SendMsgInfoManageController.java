package egovframework.let.sts.snd.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.Globals;
import egovframework.let.sts.snd.service.SendMsgInfo;
import egovframework.let.sts.snd.service.SendMsgInfoVO;
import egovframework.let.sts.snd.service.SendMsgInfoManageService;
import egovframework.let.sts.xml.service.XmlInfoManageService;
import egovframework.let.sym.cnt.service.CenterInfoManageService;




import egovframework.let.sym.cnt.service.CenterInfoVO;

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

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller 
public class SendMsgInfoManageController {

	 private static final Logger LOGGER = LoggerFactory.getLogger(SendMsgInfoManageController.class);

		
	   @Resource(name="SendMsgInfoManageService")
	   private SendMsgInfoManageService sendMsgInfoManageService;
	   
	   
	   @Resource(name="CenterInfoManageService")
	   private CenterInfoManageService centerInfoManageService;
	    		
		@Resource(name="egovMessageSource")
		protected EgovMessageSource egovMessageSource;
		
		@Resource(name="XmlInfoManageService")
		private XmlInfoManageService xmlService;
		
	    /** EgovPropertyService */
	    @Resource(name = "propertiesService")
	    protected EgovPropertyService propertiesService;

		@Autowired
		private DefaultBeanValidator beanValidator;
	
		@RequestMapping (value="/backoffice/sub/popup/pop_sendLst.do")
		public String selectPopSendResult(@ModelAttribute("loginVO") LoginVO loginVO
													, @ModelAttribute("searchVO") SendMsgInfoVO searchVO
													, HttpServletRequest request
													, BindingResult bindingResult
													, ModelMap model) throws Exception {
			
			     String didId = request.getParameter("didId") != null ? request.getParameter("didId") : "";      
			
				 LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");			    
				 if (user != null ){
				    	searchVO.setAuthor_Code(user.getAuthorCode());
				    	searchVO.setGroupCode(user.getGroupId());
				    	searchVO.setParentGroupId(user.getParentGroupId());				    	
				 }else {
				    	System.out.println("로그인 기록 없음");
				    	return "/backoffice/login";
				    	
				 }
			
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
				 searchVO.setLastIndex(paginationInfo.getFirstRecordIndex() + searchVO.getPageSize());
				 searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
				 if (!didId.equals("")){
					  searchVO.setDidId(didId);
				 }
				 if (searchVO.getCenterId() == null) { searchVO.setCenterId("");}
				 if (searchVO.getXmlProcessName() == null) { searchVO.setXmlProcessName("");}
				 if (searchVO.getSchStartDay() == null) { searchVO.setSchStartDay("");}
				  
				  
				  
				  

			       model.addAttribute("resultList",   sendMsgInfoManageService.selectSendMsgInfoManageListByPagination(searchVO)  );
			       
			       int totCnt = sendMsgInfoManageService.selectSendMsgInfoManageListTotCnt_S(searchVO);       
				   paginationInfo.setTotalRecordCount(totCnt);
			       model.addAttribute("paginationInfo", paginationInfo);
			       model.addAttribute("totalCnt", totCnt);
			       model.addAttribute("regist", searchVO);
			       
			       
			      return "/backoffice/popup/pop_sendLst";
			
		}
	    @RequestMapping("/backoffice/sub/operManage/sendResultList.do")
	    public String selectSendResult(@ModelAttribute("loginVO") LoginVO loginVO
				, @ModelAttribute("searchVO") SendMsgInfoVO searchVO
				, HttpServletRequest request
				, BindingResult bindingResult
				, ModelMap model) throws Exception {
	    	
	    	try{
	    	 LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");			    
			 if (user != null ){
			    	searchVO.setAuthor_Code(user.getAuthorCode());
			    	searchVO.setGroupCode(user.getGroupId());
			    	searchVO.setParentGroupId(user.getParentGroupId());	    	
			 }else {
			    	System.out.println("로그인 기록 없음");		   
			    	return "/backoffice/login";
			 }
			
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
			  searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

              CenterInfoVO centerInfoVO = new CenterInfoVO();
              String cenSearchKeyword = request.getParameter("cenSearchKeyword") == null ? "" : request.getParameter("cenSearchKeyword");
              centerInfoVO.setSearchKeyword(cenSearchKeyword);
			  
			  model.addAttribute("selectCenter", centerInfoManageService.selectCenterInfoManageCombo(centerInfoVO));		 
		      model.addAttribute("selectProcess", xmlService.selectXmlProcessCombo());
		       
		      if (searchVO.getCenterId() == null) {searchVO.setCenterId("");}
		      if (searchVO.getXmlProcessName() == null) {searchVO.setXmlProcessName("");}
		      if (searchVO.getSchStartDay() == null) {searchVO.setSchStartDay("");}
		       
		      model.addAttribute("resultList",   sendMsgInfoManageService.selectSendMsgInfoManageListByPagination(searchVO)  );		      
		       
		       
		      int totCnt = sendMsgInfoManageService.selectSendMsgInfoManageListTotCnt_S(searchVO);       
			  paginationInfo.setTotalRecordCount(totCnt);			   
		      model.addAttribute("paginationInfo", paginationInfo);		       
		      model.addAttribute("totalCnt", totCnt);
		       
		      model.addAttribute("regist", searchVO);
		       
	    	}catch(Exception e){
	    		LOGGER.debug("selectSendResult:" + e.toString());
	    	}
	    	
		      return "/backoffice/sub/operManage/sendResultList";
		}
}
