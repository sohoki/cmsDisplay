package egovframework.let.sym.did.web;



import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;



import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.Globals;
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
import egovframework.let.sts.snd.service.SendMsgInfo;
import egovframework.let.sts.snd.service.SendMsgInfoManageService;
import egovframework.let.sts.xml.service.XmlInfoManageService;
import egovframework.let.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;
import egovframework.let.sym.mnu.service.MenuInfoVO;
import egovframework.let.sym.rnt.service.RoleInfoManageService;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.cms.sohoki.sym.pro.service.ProgrameInfoService;
import com.cms.sohoki.sym.pro.service.ProgrameInfoVO;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import java.io.*;
import java.net.*;

@Controller
public class DidInfoManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DidInfoManageController.class);

	@Resource(name="DidInfoManageService")
	private DidInfoManageService didInfoManageService;
	
	
	@Resource(name="CmmnDetailCodeManageService")
    private EgovCcmCmmnDetailCodeManageService cmmnDetailCodeManageService;
	
	
	@Resource(name="GroupManagerService")
	private GroupManagerService groupManagerService;	
	
	@Resource(name="SendMsgInfoManageService")
	private SendMsgInfoManageService sendMsgInfo;
	
	@Resource(name="CenterInfoManageService")
	private CenterInfoManageService centerInfoManageService;
	
	
	@Resource(name="ScheduleInfoManageService")
	private ScheduleInfoManageService scheduleManagerService;
	
	@Resource(name="XmlInfoManageService")
	private XmlInfoManageService xmlService;
	
	
	@Resource(name="egovMessageSource")
	protected EgovMessageSource egovMessageSource;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name="GroupDidInfoManageService")
    private GroupDidInfoManageService groupDidInfoManageService;
    
    
    @Resource(name="ProgrameInfoService")
    private ProgrameInfoService progInfo;

	@Autowired
	private DefaultBeanValidator beanValidator;
	
	@RequestMapping("/backoffice/sub/equiManage/didEndTime.do")
	@ResponseBody  
	public String updateDidTime (HttpServletRequest request ) throws Exception {
		
		String didId = request.getParameter("didId") != null ? request.getParameter("didId") : "";
		String didMac = request.getParameter("didMac") != null ? request.getParameter("didMac") : "";
		String didEndTime = request.getParameter("didEndTime") != null ? request.getParameter("didEndTime") : "";
		DidInfo vo = new DidInfo();
		
		vo.setDidId(didId);
		vo.setDidMac(didMac);
		vo.setDidEndTime(didEndTime);		
		int ret = didInfoManageService.updateDidEndTime(vo);
		
		SendMsgInfo sminfo = new SendMsgInfo();
		sminfo.setDidId(didId);
		sminfo.setDidMacAddress(didMac);
		sminfo.setXmlProcessName("SP_DIDENDTIME");
		sminfo.setSendResult("N");
		
		int ret1 =  sendMsgInfo.insertSendMsgInfoManage(sminfo);
		String result = "";
		if (ret > 0 && ret1 > 0){
			result ="O";
		}else {
			result ="F";
		}
		return result;
	}
	
	
	
	@RequestMapping("/backoffice/sub/equiManage/RestartDidInfo.do")
	@ResponseBody
	public String didRestInfo ( HttpServletRequest request ) throws Exception {
		String RestartDidInfo = request.getParameter("RestartDidInfo") != null ? request.getParameter("RestartDidInfo") : "";
		String xmlProceNm = request.getParameter("xmlProceNm") != null ? request.getParameter("xmlProceNm") : "";
		
        int ret = 0;
    	
    	String[] didIdArray =  RestartDidInfo.substring(1).split(",");
    	String[] didInfo = null;
		SendMsgInfo sminfo = new SendMsgInfo();    	
    	for ( int i = 0; i < didIdArray.length; i++ ){
    		didInfo = didIdArray[i].split("|");
    		
    		try{
    			sminfo.setDidId(didInfo[0].toString());
    			sminfo.setDidMacAddress(didInfo[1].toString());
    			if (xmlProceNm.equals("RESTART")){
    				sminfo.setXmlProcessName("SP_DIDREBOOT");	
    			}else {
    				sminfo.setXmlProcessName("SP_REDOWN");
    			}
    			
    			sminfo.setSendResult("N");	    
    			ret =  sendMsgInfo.insertSendMsgInfoManage(sminfo);
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
	//  grup 정보 변경
	@RequestMapping("/backoffice/sub/equiManage/didChange.do")
	@ResponseBody  
	public String xmlUpdateDid ( HttpServletRequest request ) throws Exception {
		
		String sch_code = request.getParameter("sch_code") != null ? request.getParameter("sch_code") : "";
		String update_code = request.getParameter("update_code") != null ? request.getParameter("update_code") : "";
		String proces_gubun = request.getParameter("proces_gubun") != null ? request.getParameter("proces_gubun") : "";
		
		String[] inputParamArrays ;
		String returnValue = "F";
		inputParamArrays = sch_code.split(",");
		
		String didMac = "";
		int ret = 0;
		// 전문 다시 보내기 
	
		
		
		
		SendMsgInfo sminfo = new SendMsgInfo();
		
		try{
			for (int i = 0; i < inputParamArrays.length; i ++ ){
				
				sminfo.setDidId(inputParamArrays[i].toString().trim());
				didMac = didInfoManageService.selectDIDMac(inputParamArrays[i].toString().trim());
				
				
				
					if (!didMac.equals("N") && didMac != null){
						
							sminfo.setDidMacAddress(didMac);			
							
							if (proces_gubun.equals("ON")){
						        sminfo.setXmlProcessName("SP_DIDREBOOT");					
							}else if (proces_gubun.equals("OFF")){
								sminfo.setXmlProcessName("SP_DIDOFF");
							}else if (proces_gubun.equals("SCH")){
								sminfo.setXmlProcessName( xmlService.selectDIDProcessNm(update_code) );
							}else if (proces_gubun.equals("SWF")){
								sminfo.setXmlProcessName( xmlService.selectDIDProcessNm(update_code) );
							}
							sminfo.setSendResult("N");
							ret =  sendMsgInfo.insertSendMsgInfoManage(sminfo);			
						    if(ret == 0){
						    	returnValue = "F";
						    	break;
						    }else {
						    	returnValue = "T";
						    }
					}else {
							
					}
				
			}
		}catch (Exception e){
			System.out.println("Error:"+ e.toString());	
		}
		return returnValue;
		
	}
	
	@RequestMapping("/backoffice/sub/equiManage/didCapture.do")
	@ResponseBody
	public String updateDidCapture ( HttpServletRequest request ) throws Exception {
		
		String didId = request.getParameter("didId") != null ? request.getParameter("didId") : "";
		String didMac = request.getParameter("didMac") != null ? request.getParameter("didMac") : "";
		
		
		SendMsgInfo sminfo = new SendMsgInfo();
		sminfo.setDidId(didId);
		sminfo.setDidMacAddress(didMac);
		sminfo.setXmlProcessName("SP_DIDMONITER");
		sminfo.setSendResult("N");
		
		int ret1 =  sendMsgInfo.insertSendMsgInfoManage(sminfo);
		String result = "";
		if ( ret1 > 0){
			result ="O";
		}else {
			result ="F";
		}
		return result;		
	}
	
	@RequestMapping("/backoffice/sub/equiManage/didReBoot.do")
	@ResponseBody  
	public String updateDidReboot ( HttpServletRequest request ) throws Exception {
		
		String didId = request.getParameter("didId") != null ? request.getParameter("didId") : "";
		String didMac = request.getParameter("didMac") != null ? request.getParameter("didMac") : "";
		
		SendMsgInfo sminfo = new SendMsgInfo();
		sminfo.setDidId(didId);
		sminfo.setDidMacAddress(didMac);
		sminfo.setXmlProcessName("SP_DIDREBOOT");
		sminfo.setSendResult("N");
		
		int ret1 =  sendMsgInfo.insertSendMsgInfoManage(sminfo);
		String result = "";
		if ( ret1 > 0){
			result ="O";
		}else {
			result ="F";
		}
		return result;
	}
	@RequestMapping("/backoffice/sub/equiManage/didReSoot.do")
	@ResponseBody  
	public String updateDidReStart ( HttpServletRequest request ) throws Exception {
		
		String didId = request.getParameter("didId") != null ? request.getParameter("didId") : "";
		String didMac = request.getParameter("didMac") != null ? request.getParameter("didMac") : "";
		
		SendMsgInfo sminfo = new SendMsgInfo();
		sminfo.setDidId(didId);
		sminfo.setDidMacAddress(didMac);
		sminfo.setXmlProcessName("SP_DIDRESTART");
		sminfo.setSendResult("N");
		
		int ret1 =  sendMsgInfo.insertSendMsgInfoManage(sminfo);
		String result = "";
		if ( ret1 > 0){
			result ="O";
		}else {
			result ="F";
		}
		return result;
	}
	@RequestMapping("/backoffice/sub/equiManage/didContentRedown.do")
	@ResponseBody  
	public String updateContentRedown ( HttpServletRequest request ) throws Exception {
		
		String didId = request.getParameter("didId") != null ? request.getParameter("didId") : "";
		String didMac = request.getParameter("didMac") != null ? request.getParameter("didMac") : "";
		
		SendMsgInfo sminfo = new SendMsgInfo();
		sminfo.setDidId(didId);
		sminfo.setDidMacAddress(didMac);
		sminfo.setXmlProcessName("SP_REDOWN");
		sminfo.setSendResult("N");
		
		int ret1 =  sendMsgInfo.insertSendMsgInfoManage(sminfo);
		String result = "";
		if ( ret1 > 0){
			result ="O";
		}else {
			result ="F";
		}
		return result;
	}
	@RequestMapping("/backoffice/sub/equiManage/didWakeOn.do")
	@ResponseBody  
	public String pcWakeOn ( HttpServletRequest request ) throws Exception {
		
		String didId = request.getParameter("didId") != null ? request.getParameter("didId") : "";
		String didMac = request.getParameter("didMac") != null ? request.getParameter("didMac") : "";
		
		String macStrseq = "";
		if (didMac.length() == 12){
            for (int i =0; i < (didMac.length()/2); i ++){
            	
            	macStrseq += didMac.substring((i*2), ((i*2)+2)) +"-";
            }
        }
        
        
        
		
		String result = "F";
		try{
			byte[] macBytes = getMacBytes(macStrseq.substring(0, (macStrseq.length()-1)));
            byte[] bytes = new byte[6 + 16 * macBytes.length];
            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte) 0xff;
            }
            for (int i = 6; i < bytes.length; i += macBytes.length) {
                System.arraycopy(macBytes, 0, bytes, i, macBytes.length);
            }
            DidInfo didInfo = new DidInfo(); 
            didInfo=	didInfoManageService.selectDidrInfoManageDetail(didId);
            
            
            InetAddress address = InetAddress.getByName(didInfo.getDidIpaddr());
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, 9);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
            socket.close();			
            result ="O";
            
		}catch (Exception e){
			LOGGER.debug("Failed to send Wake-on-LAN packet:" + e);
			result ="F";
		}	
		return result;
	}
	private byte[] getMacBytes(String macStr) throws IllegalArgumentException {
        byte[] bytes = new byte[6];
        String[] hex = macStr.split("(\\:|\\-)");
        if (hex.length != 6) {
            throw new IllegalArgumentException("Invalid MAC address.");
        }
        try {
            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte) Integer.parseInt(hex[i], 16);
            }
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid hex digit in MAC address.");
        }
        return bytes;
    }
	@RequestMapping("/backoffice/sub/equiManage/didShutdown.do")
	@ResponseBody  
	public String updateDidShutdown ( HttpServletRequest request ) throws Exception {
		
		String didId = request.getParameter("didId") != null ? request.getParameter("didId") : "";
		String didMac = request.getParameter("didMac") != null ? request.getParameter("didMac") : "";
		
		SendMsgInfo sminfo = new SendMsgInfo();
		sminfo.setDidId(didId);
		sminfo.setDidMacAddress(didMac);
		sminfo.setXmlProcessName("SP_DIDSHUTDOWN");
		sminfo.setSendResult("N");
		
		int ret1 =  sendMsgInfo.insertSendMsgInfoManage(sminfo);
		String result = "";
		if ( ret1 > 0){
			result ="O";
		}else {
			result ="F";
		}
		return result;
	}
	
	@RequestMapping ("/backoffice/sub/equiManage/didManagerList.do")
	public String selectDidManagerInfoManageListByPagination(@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("searchVO") DidInfoVO searchVO
			, HttpServletRequest request
			, BindingResult result
			, ModelMap model) throws Exception {
		
		
		 LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");			    
		 if (user != null ){
		    	
		    	searchVO.setAuthor_Code(user.getAuthorCode());
		    	searchVO.setRole_Code(user.getRoleCode());
		    	searchVO.setCenterId("");
		    	searchVO.setDidModelType("");		    			    	
		 }else {
		    	System.out.println("로그인 기록 없음");		    	
		    	return "/backoffice/login";
		 }			  			    
		
		
		    model.addAttribute("regist", searchVO);
			model.addAttribute("selectschLst", scheduleManagerService.selectScheduleInfoManageCombo());
			model.addAttribute("selectSwver", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT005"));
		
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
			//
			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			// 스케줄
			
			try{
			model.addAttribute("resultList",   didInfoManageService.selectDidManagerInfoManageListByPagination(searchVO) );
			} catch(Exception e){
				LOGGER.debug("error:" + e.toString());
		  }
			
			int totCnt = didInfoManageService.selectDidManagerInfoManageListTotCnt_S(searchVO);      
			paginationInfo.setTotalRecordCount(totCnt);
			model.addAttribute("paginationInfo", paginationInfo);
			model.addAttribute("totalCnt", totCnt);
			
			return "/backoffice/sub/equiManage/didManagerLst";
	
	}
	
	
	
	@RequestMapping ("/backoffice/sub/equiManage/didList.do")
	public String selectDidInfoManageListByPagination(@ModelAttribute("loginVO") LoginVO loginVO
														, @ModelAttribute("searchVO") DidInfoVO searchVO
														, HttpServletRequest request
														, BindingResult result
														, ModelMap model) throws Exception {
		
	        CenterInfoVO centerInfoVO = new CenterInfoVO();
	        String cenSearchKeyword = request.getParameter("cenSearchKeyword") == null ? "" : request.getParameter("cenSearchKeyword");
	        centerInfoVO.setSearchKeyword(cenSearchKeyword);
	        model.addAttribute("selectCenter", centerInfoManageService.selectCenterInfoManageCombo(centerInfoVO));
		    model.addAttribute("selectGroup", groupDidInfoManageService.selectComboLst());
		
		    //System.out.println("centerId:"+searchVO.getCenterId());

		     
			 LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");			    
			 if (user != null ){
				 if(user.getAuthorCode() != null && !user.getAuthorCode().equals("") && (user.getAuthorCode().equals("ROLE_MHS_ADMIN") || user.getAuthorCode().equals("ROLE_MHS_USER"))){
					 return "redirect:/backoffice/sub/roomManage/monitorList.do";
				 }
				 searchVO.setAuthor_Code(user.getAuthorCode());
				 searchVO.setGroupCode(user.getGroupId());
				 searchVO.setParentGroupId(user.getParentGroupId());
			 }else {
			    	System.out.println("로그인 기록 없음");		    	
			    	return "/backoffice/login";
			 }			  			    
			 
			 System.out.println("로그인 계정 ROLE CODE ? " + user.getGroupId());
			 
			
		
		   if(  searchVO.getPageUnit() > 0  ){    	   
				    	   searchVO.setPageUnit(searchVO.getPageUnit());
			}else {
				   searchVO.setPageUnit(propertiesService.getInt("pageUnit"));   
			}
			searchVO.setPageSize(propertiesService.getInt("pageSize"));
	       
	      /** pageing */       
		  if (searchVO.getCenterId() ==  null) { searchVO.setCenterId("");}
		  if (searchVO.getDidModelType() ==  null) { searchVO.setDidModelType("");}
	   	  PaginationInfo paginationInfo = new PaginationInfo();
		  paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		  paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		  paginationInfo.setPageSize(searchVO.getPageSize());

		  searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		  searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		  searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		  
		  try{
			  //model.addAttribute("resultList",   didInfoManageService.selectDidInfoManageListByPagination(searchVO) ); 
			  System.out.println(searchVO.getSearchCondition());
			  System.out.println(searchVO.getSearchKeyword());
			  model.addAttribute("resultList",   didInfoManageService.selectIntegrateManageListByPagination(searchVO) ); 
			  
			  
			  
		  }catch(Exception e){
				LOGGER.debug("error:" + e.toString());
		  }
		   
		   
	       
	       int totCnt = didInfoManageService.selectDidInfoManageListTotCnt_S(searchVO) ;       
		   paginationInfo.setTotalRecordCount(totCnt);
	       model.addAttribute("paginationInfo", paginationInfo);
	       model.addAttribute("totalCnt", totCnt);
	       model.addAttribute("selectCenter", centerInfoManageService.selectCenterInfoManageCombo(centerInfoVO));
		   model.addAttribute("selectGroup", groupDidInfoManageService.selectComboLst());
		   model.addAttribute("regist", searchVO);
		   
	      return "/backoffice/sub/equiManage/didList";
	}
	@RequestMapping ("/backoffice/sub/equiManage/didDetail.do")
	public String selectDidrInfoManageDetail( @ModelAttribute("loginVO") LoginVO loginVO
			,  DidInfoVO vo
			, HttpServletRequest request
			, BindingResult result
			, ModelMap model) throws Exception{
		
		
		
		GroupVo groupVo = new GroupVo();
		LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");			    
		if (user != null ){
			groupVo.setParentGroupId(user.getParentGroupId());	
			groupVo.setGroupId(user.getGroupId());
		} else {
			groupVo.setParentGroupId("EMART_00000000000001");
			groupVo.setGroupId("EMART_00000000000002");
		}

		
		model.addAttribute("selectRole", groupManagerService.selectGroupManageCombo(groupVo));
		
		
        CenterInfoVO centerInfoVO = new CenterInfoVO();
        String cenSearchKeyword = request.getParameter("cenSearchKeyword") == null ? "" : request.getParameter("cenSearchKeyword");
        centerInfoVO.setSearchKeyword(cenSearchKeyword);
        
        
        model.addAttribute("selectCenter", centerInfoManageService.selectCenterInfoManageCombo(centerInfoVO));
        model.addAttribute("selectType", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT001") );
		model.addAttribute("selectResolution", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT002"));
		model.addAttribute("selectIpType", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT003"));
		model.addAttribute("selectModelType", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT004"));
		model.addAttribute("selectSwver", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT005"));
		model.addAttribute("selectOs", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT011"));
		model.addAttribute("selectSerialUse", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT012"));
		model.addAttribute("selectComPort", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT013"));
		model.addAttribute("selectMoniterCnt", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT014"));
		
		
		
		model.addAttribute("regist", vo);
		
		if (!vo.getMode().equals("Ins")){
			//신규 추가 
			ProgrameInfoVO progVo = new ProgrameInfoVO();
			progVo.setProgOstype(vo.getDidOs()); 
			model.addAttribute("selectProgVersion", progInfo.selectProgramComboInfo(progVo));
	     	model.addAttribute("regist", didInfoManageService.selectDidrInfoManageDetail(vo.getDidId()) );	     		     
		}		
		return "/backoffice/sub/equiManage/didDetail";		
	}
	@RequestMapping ("/backoffice/sub/equiManage/didView.do")
	public String selectDidrInfoManageView( @ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("DidInfoVO") DidInfoVO vo			
			,  HttpServletRequest request
			,  BindingResult result
			,  ModelMap model) throws Exception{
		
		model.addAttribute("regist", vo);
	    model.addAttribute("regist", didInfoManageService.selectDidrInfoManageDetailView(vo.getDidId()) );
	    
		return "/backoffice/sub/equiManage/didView";		
		
	}
	

	@RequestMapping(value="/backoffice/sub/equiManage/didConSchList.do")
	public ModelAndView selectDidConSchInfoList( HttpServletRequest request) throws Exception{
		
        ModelAndView model = new ModelAndView("jsonView");
        String didId = request.getParameter("didId") == null ? "" : request.getParameter("didId");
        return model.addObject("resultMap", didInfoManageService.selectDidDetailContentInfo(didId));
		
	}
    

    
	
	@RequestMapping (value="/backoffice/sub/equiManage/didDeletel.do")
	public String deleteCenterInfoManage(@ModelAttribute("loginVO") LoginVO loginVO,
			DidInfoVO vo,
			ModelMap model)throws Exception{
		
		model.addAttribute("detail",vo );
		
		if (StringUtils.equals(vo.getCenterId(), Globals.REGITER_SYSTEM)){
			model.addAttribute("status", Globals.STATUS_FAIL);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.delete.system"));			
			return "forward:/backoffice/sub/equiManage/didList.do";
		}
				
		try{
		      int ret = 	didInfoManageService.deleteDidInfoManage(vo.getDidId()) ;		      
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
		return "forward:/backoffice/sub/equiManage/didList.do";
	}

	@RequestMapping (value="/backoffice/sub/equiManage/didUpdate.do")	                                           
	public String updateCenterInfoManage(
			@ModelAttribute("loginVO") LoginVO loginVO,
			@ModelAttribute("DidInfo") DidInfo vo
			, HttpServletRequest request
			, BindingResult result
			, ModelMap model) throws Exception{
		
		model.addAttribute("regist", vo);
		String meesage = "";
		String url = "redirect:/backoffice/sub/equiManage/didList.do";
		
		try{
			
			int ret  = 0;
			LOGGER.debug("didSwver:" + vo.getDidSwver());
			if (vo.getMode().equals("Ins")){
				
				String insertDid = didInfoManageService.selectLastInsertDid(vo.getCenterId());
				vo.setDidId(insertDid);
				
                ret = didInfoManageService.insertDidInfoManage(vo);
                
                if(ret > 0){
                	 if(vo.getGroupId() != null && !vo.getGroupId().equals("")){
                         LOGGER.info((new StringBuilder("insert try group info : ")).append(vo.getGroupId()).toString());
                         GroupDidInfo groupDidInfo = new GroupDidInfo();
                         groupDidInfo.setDidId(vo.getDidId());
                         groupDidInfo.setGroupCode(vo.getGroupId());
                         if(groupDidInfoManageService.insertGroupInfoManage(groupDidInfo) > 0){
                             LOGGER.info("DID insert with Group info");
                         } else {
                             LOGGER.info("DID insert FAIL");
                         }
                     }
                } else {
                    LOGGER.info("DID insert with Group info");
                }
                
				meesage = "sucess.common.insert";
				url = "redirect:/backoffice/sub/equiManage/didView.do?didId="+insertDid;
				
			}else {
				System.out.println("UPDATE DATA CHECK >>> " + vo.toString());
				
				
	           	 if(vo.getGroupId() != null && !vo.getGroupId().equals("")){
	                 LOGGER.info((new StringBuilder("insert try group info : ")).append(vo.getGroupId()).toString());
	                 GroupDidInfo groupDidInfo = new GroupDidInfo();
	                 groupDidInfo.setDidId(vo.getDidId());
	                 groupDidInfo.setGroupCode(vo.getGroupId());
	                 if(groupDidInfoManageService.insertGroupInfoManage(groupDidInfo) > 0){
	                     LOGGER.info("DID insert with Group info");
	                 } else {
	                     LOGGER.info("DID insert FAIL");
	                 }
	             }
				
				
				 ret = didInfoManageService.updateDidInfoManage(vo);
				 meesage = "sucess.common.update";
				 url = "forward:/backoffice/sub/equiManage/didView.do";
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
			e.printStackTrace();
			url = "redirect:/backoffice/sub/equiManage/didList.do";
		}		
		return url;
	}
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping ("/backoffice/sub/equiManage/integrate.do")
	public String selectIntegrateListByPagination(@ModelAttribute("loginVO") LoginVO loginVO
														, @ModelAttribute("searchVO") DidInfoVO searchVO
														, HttpServletRequest request
														, BindingResult result
														, ModelMap model) throws Exception {
		
	        CenterInfoVO centerInfoVO = new CenterInfoVO();
	        String cenSearchKeyword = request.getParameter("cenSearchKeyword") == null ? "" : request.getParameter("cenSearchKeyword");
	        centerInfoVO.setSearchKeyword(cenSearchKeyword);
	        model.addAttribute("selectCenter", centerInfoManageService.selectCenterInfoManageCombo(centerInfoVO));
		    model.addAttribute("selectGroup", groupDidInfoManageService.selectComboLst());
		
		    //System.out.println("centerId:"+searchVO.getCenterId());

		     
			 LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");			    
			 if (user != null ){
			    	searchVO.setAuthor_Code(user.getAuthorCode());
			    	searchVO.setGroupCode(user.getGroupId());
			    	searchVO.setParentGroupId(user.getParentGroupId());
			 }else {
			    	System.out.println("로그인 기록 없음");		    	
			    	return "/backoffice/login";
			 }			  			    
			 
			 // System.out.println("로그인 계정 ROLE CODE ? " + user.getGroupId());
			 
			
		/*
		   if(  searchVO.getPageUnit() > 0  ){    	   
				    	   searchVO.setPageUnit(searchVO.getPageUnit());
			}else {
				   searchVO.setPageUnit(propertiesService.getInt("pageUnit"));   
			}
			searchVO.setPageSize(propertiesService.getInt("pageSize"));
	       
	      // pageing       
		  if (searchVO.getCenterId() ==  null) { searchVO.setCenterId("");}
		  if (searchVO.getDidModelType() ==  null) { searchVO.setDidModelType("");}
	   	  PaginationInfo paginationInfo = new PaginationInfo();
		  paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		  paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		  paginationInfo.setPageSize(searchVO.getPageSize());

		  searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		  searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		  searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());*/
		  
		  /*try{
			  
			  String requestSystemType = request.getParameter("systemType") == null ? request.getParameter("systemType") : "SIGNAGE";
			  searchVO.setRequestSystemType(requestSystemType);
			  
			  System.out.println(user.getAuthorCode());
			  if(user.getAuthorCode() != null && (user.getAuthorCode().equals("ROLE_ADMIN") || user.getAuthorCode().equals("ROLE_ANONYMOUS") || 
					  user.getAuthorCode().equals("ROLE_USER_MEMBER") || user.getAuthorCode().equals("ROLE_DID_ADMIN") || user.getAuthorCode().equals("ROLE_DID_USER"))){
				  // 통합 및 사이니지 유저
				  requestSystemType = "SIGNAGE";
			  } else {
				  // 음원방송 유저
				  requestSystemType = "MUSIC";
			  }
			  
			  searchVO.setRequestSystemType(requestSystemType);
			  
			  model.addAttribute("roleList",   didInfoManageService.selectIntegrateRoleList(searchVO) ); // selectIntegrateRoleList
			  model.addAttribute("centerList",   didInfoManageService.selectIntegrateCenterList(searchVO) ); // selectIntegrateCenterList 
			  // model.addAttribute("deviceList",   didInfoManageService.selectIntegrateDeviceList(searchVO) );  // selectIntegrateDeviceList
			  
		  }catch(Exception e){
				LOGGER.debug("error:" + e.toString());
		  }*/
		   
		   /*
	       
	       int totCnt = didInfoManageService.selectDidInfoManageListTotCnt_S(searchVO) ;       
		   paginationInfo.setTotalRecordCount(totCnt);
	       model.addAttribute("paginationInfo", paginationInfo);
	       model.addAttribute("totalCnt", totCnt);
	       model.addAttribute("selectCenter", centerInfoManageService.selectCenterInfoManageCombo(centerInfoVO));
		   model.addAttribute("selectGroup", groupDidInfoManageService.selectComboLst());
		   model.addAttribute("regist", searchVO);*/
		   
	      return "/backoffice/sub/equiManage/integrate";
	}
	
	
	@RequestMapping(value="/backoffice/sub/equiManage/selectRoleCenterInfo.do")
	@ResponseBody
	public ModelAndView selectRoleCenterInfo(HttpServletRequest request) throws Exception{
		
		DidInfoVO didInfoVO = new DidInfoVO();
		ModelAndView model = new ModelAndView("jsonView");

		String groupId				= request.getParameter("groupId") != null ? request.getParameter("groupId") : "";
		String firstIndex 			= request.getParameter("firstIdx") != null ? request.getParameter("firstIdx") : "0";
		String lastIndex 			= request.getParameter("lastIdx") != null ? request.getParameter("lastIdx") : "0"; 
		String recordCountPerPage	= request.getParameter("recordCnt") != null ? request.getParameter("recordCnt") : "10"; 
		
		String requestSystemType = request.getParameter("systemType") != null ? request.getParameter("systemType") : "SIGNAGE";
		didInfoVO.setRequestSystemType(requestSystemType);
		System.out.println(didInfoVO.getRequestSystemType());
		didInfoVO.setFirstIndex(Integer.parseInt(firstIndex));
		didInfoVO.setLastIndex(Integer.parseInt(lastIndex));
		didInfoVO.setRecordCountPerPage(Integer.parseInt(recordCountPerPage));
		didInfoVO.setGroupId(groupId);
		
		model.addObject("roleList",   didInfoManageService.selectIntegrateRoleList(didInfoVO) ); // selectIntegrateRoleList

		return model;
	}
	
	
	@RequestMapping(value="/backoffice/sub/equiManage/selectIntegrateCetnerList.do")
	@ResponseBody
	public ModelAndView selectIntegrateCetnerList(HttpServletRequest request) throws Exception{
		
		DidInfoVO didInfoVO = new DidInfoVO();
		ModelAndView model = new ModelAndView("jsonView");

		String groupId				= request.getParameter("groupId") != null ? request.getParameter("groupId") : "";
		String firstIndex 			= request.getParameter("firstIdx") != null ? request.getParameter("firstIdx") : "0";
		String lastIndex 			= request.getParameter("lastIdx") != null ? request.getParameter("lastIdx") : "0"; 
		String recordCountPerPage	= request.getParameter("recordCnt") != null ? request.getParameter("recordCnt") : "10"; 
		
		String requestSystemType = request.getParameter("systemType") == null ? request.getParameter("systemType") : "SIGNAGE";
		didInfoVO.setRequestSystemType(requestSystemType);
		
		didInfoVO.setFirstIndex(Integer.parseInt(firstIndex));
		didInfoVO.setLastIndex(Integer.parseInt(lastIndex));
		didInfoVO.setRecordCountPerPage(Integer.parseInt(recordCountPerPage));
		
		didInfoVO.setGroupId(groupId);
		

		model.addObject("centerList", didInfoManageService.selectIntegrateCenterList(didInfoVO));

		return model;
	}
	
	
	
	@RequestMapping(value="/backoffice/sub/equiManage/selectIntegrateEquipList.do")
	@ResponseBody
	public ModelAndView selectIntegrateEquipList(HttpServletRequest request) throws Exception{
		
		DidInfoVO didInfoVO = new DidInfoVO();
		ModelAndView model = new ModelAndView("jsonView");

		String groupCode			= request.getParameter("groupId") != null ? request.getParameter("groupId") : "";
		String centerId				= request.getParameter("centerId") != null ? request.getParameter("centerId") : "";
		String firstIndex 			= request.getParameter("firstIdx") != null ? request.getParameter("firstIdx") : "0";
		String lastIndex 			= request.getParameter("lastIdx") != null ? request.getParameter("lastIdx") : "0"; 
		String recordCountPerPage	= request.getParameter("recordCnt") != null ? request.getParameter("recordCnt") : "10"; 
		
		didInfoVO.setFirstIndex(Integer.parseInt(firstIndex));
		didInfoVO.setLastIndex(Integer.parseInt(lastIndex));
		didInfoVO.setRecordCountPerPage(Integer.parseInt(recordCountPerPage));
		
		didInfoVO.setGroupCode(groupCode);
		didInfoVO.setCenterId(centerId);

		model.addObject("equipList", didInfoManageService.selectIntegrateDeviceList(didInfoVO));

		return model;
	}
	
	@RequestMapping(value="/backoffice/sub/equiManage/selectIntegrateEquipInfo.do")
	@ResponseBody
	public ModelAndView selectIntegrateEquipInfo(HttpServletRequest request) throws Exception{
		
		DidInfoVO didInfoVO = new DidInfoVO();
		ModelAndView model = new ModelAndView("jsonView");

		String didId = request.getParameter("didId") != null ? request.getParameter("didId") : "";

		
		if(didId != null && !didId.equals("")){
			didInfoVO.setDidId(didId);
			model.addObject("equipInfo", didInfoManageService.selectDidrInfoManageDetailView(didInfoVO.getDidId()));
			model.addObject("equipSchList", didInfoManageService.selectDidDetailContentInfo(didId));
		} else {
			
			// 데이터를 점검 해달라는 내용을 삽입해야함
			
		}
		return model;
	}
	
	
	@RequestMapping(value="/backoffice/sub/equiManage/selectEquipRegistComboData.do")
	@ResponseBody
	public ModelAndView selectEquipRegistComboData(HttpServletRequest request) throws Exception{
		
		DidInfoVO didInfoVO = new DidInfoVO();
		ModelAndView model = new ModelAndView("jsonView");

		GroupVo groupVo = new GroupVo();
		LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");
		if (user != null ){
			groupVo.setParentGroupId(user.getParentGroupId());	
			groupVo.setGroupId(user.getGroupId());
		} else {
			groupVo.setParentGroupId("EMART_00000000000001");
			groupVo.setGroupId("EMART_00000000000002");
		}
		
		String callType = request.getParameter("callType") == null ? "0" : request.getParameter("callType");
		
		if(callType.equals("0")){
			// 요청사항 없음, 에러, 재확인 요청
			
		} else if(callType.equals("basicInfo")) {
			// IP형태  - EMT003
			// OS타입 - EMT011 
			// 송출사항 - EMT004
			// 단말형태 - EMT001
			// 단말해상도 - EMT002
			// 부서정보
			model.addObject("selectType", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT001") ); // 
			model.addObject("selectResolution", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT002"));
			model.addObject("selectIpType", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT003"));
			model.addObject("selectModelType", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT004"));
			model.addObject("selectOs", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT011"));
			model.addObject("selectRole", groupManagerService.selectGroupManageCombo(groupVo));
		} else if(callType.equals("centerInfo")) {
			// 점포정보
			CenterInfoVO centerInfoVO = new CenterInfoVO();
	        String selectRoleCode = request.getParameter("callDetail") == null ? "" : request.getParameter("callDetail");
	        centerInfoVO.setSearchKeyword("");
	        centerInfoVO.setSelectRoleCode(selectRoleCode);
			model.addObject("selectCenter", centerInfoManageService.selectCenterInfoManageCombo(centerInfoVO)); // 점포정보
		} else if(callType.equals("groupInfo")) {
			
		} else if(callType.equals("")) {
			
		}
		
		
		
		
        
			
		
        
	
		return model;
	}
	
	
	
	@RequestMapping(value="/backoffice/sub/equiManage/modifyEquipDetail.do")
	@ResponseBody
	public String modifyEquipDetail(HttpServletRequest request) throws Exception{
		
		DidInfoVO didInfoVO = new DidInfoVO();
		
		String result = "FAIL";
		
		String work = request.getParameter("work") != null ? request.getParameter("work") : "";
		
		
		
		LOGGER.debug("incheck 1 : " + work);
		LOGGER.debug("incheck 1-2 : " + request.getRequestURI());
		LOGGER.debug("incheck 1-3 : " + request.getRequestURL());
		LOGGER.debug("incheck 1-4 : " + request.getParameterMap().size());
		LOGGER.debug("incheck 1-5 : " + request.getParameterMap().toString());
		
		// 요청 작업사항과 단말ID값이 정상적으로 존재시 진행
		try{
			if(work != null && !work.equals("")){
				LOGGER.debug("incheck 2");
				if (work.equals("insert")){
					LOGGER.debug("incheck 3-1");
					String centerId = request.getParameter("equip_regist_center") != null ? request.getParameter("equip_regist_center") : "";
					String didNm = request.getParameter("equip_regist_nm") != null ? request.getParameter("equip_regist_nm") : "";
					String didIptype = request.getParameter("equip_regist_iptype") != null ? request.getParameter("equip_regist_iptype") : "";
					String didOs = request.getParameter("equip_regist_os") != null ? request.getParameter("equip_regist_os") : "";
					String didRoleId = request.getParameter("equip_regist_role") != null ? request.getParameter("equip_regist_role") : "";
					String didGroupId = request.getParameter("equip_regist_group") != null ? request.getParameter("equip_regist_group") : "";
					String didOpenTime = request.getParameter("equip_regist_openTime") != null ? request.getParameter("equip_regist_openTime") : "";
					String didCloseTime = request.getParameter("equip_regist_closeTime") != null ? request.getParameter("equip_regist_closeTime") : "";
					String didSystemType = request.getParameter("equip_regist_systemType") != null ? request.getParameter("equip_regist_systemType") : "";
					String didDeviceType = request.getParameter("equip_regist_deviceType") != null ? request.getParameter("equip_regist_deviceType") : "";
					String didWidth = request.getParameter("equip_regist_resolution_w_width") != null ? request.getParameter("equip_regist_resolution_w_width") : "";
					String didHeight = request.getParameter("equip_regist_resolution_w_height") != null ? request.getParameter("equip_regist_resolution_w_height") : "";
					String didResolution = request.getParameter("equip_regist_resolution") != null ? request.getParameter("equip_regist_resolution") : "";
					String didUseYn = request.getParameter("equip_regist_use") != null ? request.getParameter("equip_regist_use") : "";
					
					// 송출사항 : systemType
					// 단말형태 : deviceType
					
					didInfoVO.setCenterId(centerId);
					didInfoVO.setDidNm(didNm);
					didInfoVO.setDidIptype(didIptype);
					didInfoVO.setDidOs(didOs);
					didInfoVO.setRoleCode(didRoleId);
					didInfoVO.setGroupId(didGroupId);
					didInfoVO.setDidStartTime(didOpenTime);
					didInfoVO.setDidEndTime(didCloseTime);
					didInfoVO.setDidModelType(didSystemType);
					didInfoVO.setDidType(didDeviceType);
					didInfoVO.setDidWidth(didWidth);
					didInfoVO.setDidHeight(didHeight);
					didInfoVO.setDidResolution(didResolution);
					if(didUseYn.equals("on")){
						didInfoVO.setDidUseYn("Y");
					} else {
						didInfoVO.setDidUseYn("N");
					}
					didInfoVO.setDidSwver("DIDSW02");
					
					if(centerId != null && !centerId.equals("")){
						LOGGER.debug("incheck 3-2 : " + didNm + ", " + didUseYn);
						String insertDid = didInfoManageService.selectLastInsertDid(didInfoVO.getCenterId());
						
						
						
						// LOGGER.info((new StringBuilder("insert try get didid : ")).append(insertDid).toString());
		                
						didInfoVO.setDidId(insertDid);
		                int ret = didInfoManageService.insertDidInfoManage(didInfoVO);
		                
		                // LOGGER.info((new StringBuilder("insert try resultValue : ")).append(ret).toString());
		               
		                if(ret > 0){
		                	LOGGER.debug("incheck 3-4");
		                	 if(didInfoVO.getGroupId() != null && !didInfoVO.getGroupId().equals("")){
		                		 LOGGER.debug("incheck 3-5 ? ");
		                         // LOGGER.info((new StringBuilder("insert try group info : ")).append(vo.getGroupId()).toString());
		                         GroupDidInfo groupDidInfo = new GroupDidInfo();
		                         groupDidInfo.setDidId(didInfoVO.getDidId());
		                         groupDidInfo.setGroupCode(didInfoVO.getGroupId());
		                         if(groupDidInfoManageService.insertGroupInfoManage(groupDidInfo) > 0){
		                             LOGGER.info("DID insert with Group info");
		                         } else {
		                             LOGGER.info("DID insert FAIL");
		                         }
		                     } else {
		                    	 result = "SUCCESS";
		                     }
		                } else {
		                	LOGGER.info("DID insert with Group info");
		                	result = "FAIL";
		                }
					} else {
						LOGGER.info("DID insert with CENTER_ID EMPTY !");
						result = "FAIL";
					}
				} else {
					String didId = request.getParameter("didId") != null ? request.getParameter("didId") : "";
					didInfoVO.setDidId(didId);
					if(work.equals("modify")){
						if(didId != null && !didId.equals("")){
							result = "MODIFY";
						}
					} else if (work.equals("delete")){
						if(didId != null && !didId.equals("")){
							int ret = didInfoManageService.deleteDidInfoManage(didInfoVO.getDidId()) ;		      
							if (ret > 0 ) {
								result = "SUCCESS";
							}else {
								result = "FAIL";
							}	
						}
					} else {
						LOGGER.info("Request work non insert & non data EMPTY !");
						result = "FAIL";
					}
				}
			} else {
				LOGGER.info("Request work info EMPTY !");
				result = "FAIL";
			}
		}catch (Exception e){
			result = "FAIL";
			LOGGER.info("modifyEquipDetail - Error, " + e.toString());
			e.printStackTrace();
		}				
		return result;
	}
	
	/*
	 * 
	 * model.addAttribute("selectCenter", centerInfoManageService.selectCenterInfoManageCombo(centerInfoVO));
        model.addAttribute("selectType", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT001") );
		model.addAttribute("selectResolution", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT002"));
		model.addAttribute("selectIpType", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT003"));
		model.addAttribute("selectModelType", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT004"));
		model.addAttribute("selectSwver", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT005"));
		model.addAttribute("selectOs", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT011"));
		model.addAttribute("selectSerialUse", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT012"));
		model.addAttribute("selectComPort", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT013"));
		model.addAttribute("selectMoniterCnt", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT014"));
	 * */
	
	
}
