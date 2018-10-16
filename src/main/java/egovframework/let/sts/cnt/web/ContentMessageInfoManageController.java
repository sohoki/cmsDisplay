package egovframework.let.sts.cnt.web;




import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.Globals;

import egovframework.let.sym.grp.service.GroupDidInfoManageService;
import egovframework.let.sym.grp.service.GroupInfoManageService;
import egovframework.let.sym.grp.service.GroupInfoVO;

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

import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.let.sts.cnt.service.ContentMessageInfo;
import egovframework.let.sts.cnt.service.ContentMessageInfoVO;
import egovframework.let.sts.cnt.service.ContentMessageInfoManageService;

@Controller
public class ContentMessageInfoManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContentMessageInfoManageController.class);
	
	
	@Resource(name="egovMessageSource")
	protected EgovMessageSource egovMessageSource;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;
	
	@Resource(name="GroupInfoManageService")        
    private GroupInfoManageService didgroupInfoManageService;
     
    @Resource(name="GroupDidInfoManageService")
    private GroupDidInfoManageService groupDidInfoManageService;
    
    
    @Resource(name="MessageInfoManageService")
    private ContentMessageInfoManageService messageService;
    
    @Resource(name="egovMsgIdGnrService")
	private EgovIdGnrService egovMsgIdGnrService;
    
    
    @RequestMapping ("/backoffice/sub/equiManage/didSendMessage.do")
	public String selectGroupDidInfoManageListByPagination(@ModelAttribute("loginVO") LoginVO loginVO
															, @ModelAttribute("searchVO") GroupInfoVO searchVO
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
		  searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

	       model.addAttribute("resultList",   didgroupInfoManageService.selectGroupInfoManageListByPagination(searchVO) );

	       int totCnt = didgroupInfoManageService.selectGroupInfoManageListTotCnt_S(searchVO) ;       
		   paginationInfo.setTotalRecordCount(totCnt);
	       model.addAttribute("paginationInfo", paginationInfo);
	       model.addAttribute("totalCnt", totCnt);
	       
	       model.addAttribute("regist", searchVO);	       
	       return "/backoffice/sub/equiManage/didSendMessage";
	}
    @RequestMapping ("/backoffice/sub/equiManage/didSendMessageLst.do")
	public String selectMessageDidInfoManageListByPagination(@ModelAttribute("loginVO") LoginVO loginVO
															, @ModelAttribute("searchVO") ContentMessageInfoVO searchVO
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
		  searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

	       model.addAttribute("resultList",   messageService.selectContentMessageInfoListByPagination(searchVO));

	       int totCnt = messageService.selectContentMessageInfoListTotCnt_S(searchVO) ;       
		   paginationInfo.setTotalRecordCount(totCnt);
	       model.addAttribute("paginationInfo", paginationInfo);
	       model.addAttribute("totalCnt", totCnt);
	       
	       model.addAttribute("regist", searchVO);	       
	       return "/backoffice/sub/equiManage/didSendMessagelst";
    }
    @RequestMapping("/backoffice/sub/equiManage/didDeleteMessageReg.do")    
    @ResponseBody
    public String deleteDIDMessageInfo(HttpServletRequest request) throws Exception {
    	
    	String delMessage = request.getParameter("delMessage") != null ? request.getParameter("delMessage") : "";
    	int ret = 0;
    	
    	String[] didIdArray =  delMessage.substring(1).split(",");	        	
    	for ( int i = 0; i < didIdArray.length; i++ ){
    		
    		try{
    			ret = 	messageService.deleteContentMessageInfo(didIdArray[i].trim());    	    		
    		}catch(Exception e){
    			LOGGER.debug("error:"+ e.toString());
    		}
    	}

    	if (ret>0){
    		return "O";
    	}else {
    		return "F";
    	}
    }
    @RequestMapping("/backoffice/sub/equiManage/didSendMessageReg.do")
    public String sendMessage (@ModelAttribute("ContentMessageInfo") ContentMessageInfo vo 
    		                   , HttpServletRequest request
    		                   , BindingResult bindingResult
    		                   , ModelMap model) throws Exception{
    	    	
    	String message_atch = request.getParameter("message_atch") != null ? request.getParameter("message_atch") : "";
    	String groupCode = request.getParameter("groupCode") != null ? request.getParameter("groupCode") : "";
    	String mode = request.getParameter("mode") != null ? request.getParameter("mode") : "";
    	
    	String sendMsgId = request.getParameter("sendMsgId") != null ? request.getParameter("sendMsgId") : "";
    	
    	
    	
    	try{
    		
    		
        	vo.setMessage_atch(message_atch);
        	vo.setGroupCode(groupCode);        	
        	vo.setMode(mode);
        	model.addAttribute("regist", vo);
        	
	    	if (mode.equals("Edt")){
	    		
	    		vo =  messageService.selectContentMessageInfoDetail(sendMsgId);
	    		
	    		if (!vo.getSendMessageStartTime().equals("") && vo.getSendMessageStartTime().contains(":") == true){
	    			String[] startArray = vo.getSendMessageStartTime().toString().split(":");
	    			vo.setSendMessageStartHour(startArray[0]);
	    			vo.setSendMessageStartMin(startArray[1]);
	    		}
	    		if (!vo.getSendMessageEndTime().equals("") && vo.getSendMessageEndTime().contains(":") == true){
	    			String[] endArray = vo.getSendMessageEndTime().toString().split(":");
	    			vo.setSendMessageEndHour(endArray[0]);
	    			vo.setSendMessageEndMin(endArray[1]);
	    		}
	    		vo.setMode(mode);
	    			
	    		model.addAttribute("regist", vo);
	    		
	    	}
	    	
	    	
    	}catch(Exception e){
    		LOGGER.debug("error:"+ e.toString());
    	}
    	
    	
    	
    	return "/backoffice/popup/SendMessageReg";
    	
    }
    @RequestMapping("/backoffice/sub/equiManage/didSendMessageUpdate.do")
    public String insertSendMessage (@ModelAttribute("loginVO") LoginVO loginVO
									, @ModelAttribute("ContentMessageInfo") ContentMessageInfo vo
									, ModelMap model ) throws Exception{
    	
    	
    	
        
    	int ret = 0;
        String url = "forward:/backoffice/sub/equiManage/didSendMessageReg.do";
        String meesage  = "";
        
        vo.setSendMessageStartTime(vo.getSendMessageStartHour()+":"+vo.getSendMessageStartMin());
		vo.setSendMessageEndTime(vo.getSendMessageEndHour()+":"+vo.getSendMessageEndMin());
        
        if (vo.getMode().equals("Ins")) {
        	vo.setSendMsgId(egovMsgIdGnrService.getNextStringId());
        	
        	String[] didIdArray =  vo.getMessage_atch().substring(1).split(",");	        	
        	for ( int i = 0; i < didIdArray.length; i++ ){
        		vo.setDidId(didIdArray[i]);
        		
        		try{
        			ret = 	messageService.insertContentMessageInfo(vo);    	    		
        		}catch(Exception e){
        			LOGGER.debug("error:"+ e.toString());
        		}
        	}
        }else {
        	try{    			
	    			ret = messageService.updateContentMessageInfoMsgId(vo);	    		
    		}catch(Exception e){
    			LOGGER.debug("error:"+ e.toString());
    		}
        	
        }
        
    	if (ret > 0){
    		  meesage = vo.getMode().equals("Ins") ?   "sucess.common.insert" : "sucess.common.update" ;
    		  model.addAttribute("status", Globals.STATUS_SUCCESS);
			  model.addAttribute("message", egovMessageSource.getMessage(meesage));			
    	}else {
    		meesage = vo.getMode().equals("Ins") ?   "fail.common.insert" : "fail.common.update" ;    		
  		    model.addAttribute("status", Globals.STATUS_SUCCESS);
			model.addAttribute("message", egovMessageSource.getMessage(meesage));
    	}
    	LOGGER.debug("url:"+ url);
    	return url;
    }
}
