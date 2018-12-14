package egovframework.let.sts.xml.web;


import java.io.StringReader;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.Globals;
import egovframework.let.cmm.use.service.GroupManagerService;
import egovframework.let.cmm.use.service.GroupVo;
import egovframework.let.sts.xml.service.XmlInfo;
import egovframework.let.sts.xml.service.XmlInfoVO;
import egovframework.let.sts.xml.service.XmlInfoManageService;
import egovframework.let.sym.ccm.cde.service.CmmnDetailCodeVO;
import egovframework.let.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;
import egovframework.let.sym.cnt.service.CenterInfoManageService;
import egovframework.let.sym.cnt.service.CenterInfoVO;
import egovframework.let.sts.brd.service.BrodScheduleManagerService;
import egovframework.let.sts.cnt.service.ContentDetailFileInfoVO;
import egovframework.let.sts.cnt.service.ContentMessageInfo;
import egovframework.let.sts.cnt.service.ContentMessageInfoManageService;
import egovframework.let.sts.cnt.service.ContentMutiInfoVO;
import egovframework.let.sts.cnt.service.ContentMutiInfoManageService;
import egovframework.let.sts.cnt.service.ContentFileInfoVO;
import egovframework.let.sts.cnt.service.ContentFileInfoManageService;


import egovframework.let.sts.cnt.service.ContentDetailInfo;
import egovframework.let.sts.cnt.service.ContentDetailInfoService;




import  egovframework.let.sym.sch.service.ContentSendHistoryInfoManagerService;
import egovframework.let.sym.did.service.DidInfo;
import egovframework.let.sym.did.service.DidInfoManageService;


import egovframework.let.sts.pic.service.DidMoniterPicVO;
import egovframework.let.sts.pic.service.DidMoniterPicService;
import egovframework.let.sts.snd.service.SendMsgInfo;
import egovframework.let.sts.snd.service.SendMsgInfoManageService;
import egovframework.let.sts.snd.service.SendMsgInfoVO;
import net.sf.json.JSONException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;




















import org.w3c.dom.DOMException;
//xml 관련 구문 
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;























import egovframework.let.sts.brd.service.BasciBrodFileInfoManageService;
import egovframework.let.sts.brd.service.BasciBrodFileInfoVO;
import egovframework.let.sts.brd.service.BasicBrodScheduleInfoManageService;
import egovframework.let.sts.brd.service.BasicBrodScheduleInfoVO;
import egovframework.let.sts.brd.service.BrodContentDetail;
import egovframework.let.sts.brd.service.BrodContentDetailManagerService;
import egovframework.let.sts.brd.service.BrodOrganization;
import egovframework.let.sts.brd.service.BrodOrganizationManagerService;
import egovframework.let.sts.brd.service.BrodScheduleInfo;
















import egovframework.let.uat.uia.service.EgovUserManagerService;
import egovframework.let.uat.uia.service.GnrMberVO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Controller
public class XmlInfoManageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(XmlInfoManageController.class);

    @Resource(name="ContentDetailInfoService")
    private ContentDetailInfoService detailService;
    
    
    @Resource(name="ContentFileInfo")
    private ContentFileInfoManageService fileService;
	
    @Resource(name="XmlInfoManageService")
    protected XmlInfoManageService xmlInfoManageService;
    
    @Resource(name="DidMoniterPicService")
    private DidMoniterPicService picService;
    
    @Resource(name="DidInfoManageService")
    private DidInfoManageService didInfoManageService;
    
    @Resource(name="SendMsgInfoManageService")
    private SendMsgInfoManageService sendMsgInfoManageService;
    
	@Resource(name="CmmnDetailCodeManageService")
    private EgovCcmCmmnDetailCodeManageService cmmnDetailCodeManageService;
	
	@Resource(name="BrodOrganizationService")
	private BrodOrganizationManagerService brodOrgService;

	@Resource(name="ContentMuti")
	private ContentMutiInfoManageService mutiInfoService;
	
	@Resource(name="ContentSendHistoryInfoManagerService")
	private ContentSendHistoryInfoManagerService sendHistory;
	
	@Resource(name="egovMessageSource")
	protected EgovMessageSource egovMessageSource;
	
    @Resource(name="MessageInfoManageService")
    private ContentMessageInfoManageService messageService;
	
    
    @Resource(name="BrodScheduleManagerService")
	 private BrodScheduleManagerService brodSchedue;
    
    @Resource(name="BrodContentDetailService")
	private BrodContentDetailManagerService brodDetail;
    
    
    @Resource(name="BasicBrodScheduleInfoManageService")
    private BasicBrodScheduleInfoManageService schService;
    
    @Resource(name="BasciBrodFileInfoManageService")
    private BasciBrodFileInfoManageService basicFileService;
    
    
    
    /**
     * JSON DATA 통신 관련
     * */
    
    // 부서관련
    @Resource(name = "GroupManagerService")
    private GroupManagerService groupManagerService;

    // 점포관련
	@Resource(name="CenterInfoManageService")
	private CenterInfoManageService centerInfoManageService;
	
	// 계정관련
	@Resource(name="UserManagerService")
	private EgovUserManagerService userManagerService;
	
    
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;
	
	@RequestMapping ("/backoffice/sub/operManage/xmlList.do")
	public String selectXmlLst(@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("searchVO") XmlInfoVO searchVO
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
		  searchVO.setLastIndex(paginationInfo.getFirstRecordIndex() + searchVO.getPageSize());
		  searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

	       model.addAttribute("resultList",   xmlInfoManageService.selectXmlInfoManageListByPagination(searchVO) );
	       
	       model.addAttribute("selectWorkgubun", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT006"));

	       
	       
	       int totCnt = xmlInfoManageService.selectXmlInfoManageListTotCnt_S(searchVO);       
		   paginationInfo.setTotalRecordCount(totCnt);
	       model.addAttribute("paginationInfo", paginationInfo);
	       model.addAttribute("totalCnt", totCnt);
	       
	       model.addAttribute("regist", searchVO);
	       
	       
	      return "/backoffice/sub/operManage/xmlList";
	}
	@RequestMapping (value="/backoffice/sub/operManage/xmlDel.do")
	public String deleteXmlInfoManage(@ModelAttribute("loginVO") LoginVO loginVO
										, XmlInfoVO vo
										, HttpServletRequest request
										, BindingResult bindingResult			
										, ModelMap model)throws Exception{
		
		model.addAttribute("detail",vo );
		
		if (StringUtils.equals(vo.getXmlSeq(), Globals.REGITER_SYSTEM)){
			model.addAttribute("status", Globals.STATUS_FAIL);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.delete.system"));			
			return "forward:/backoffice/sub/operManage/xmlList.do";
		}
				
		try{
		      int ret = 	xmlInfoManageService.deleteXmlInfoManage(vo.getXmlSeq());		      
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
		return "forward:/backoffice/sub/operManage/xmlList.do";
	}
	
	@RequestMapping("/backoffice/sub/operManage/xmlView.do")
	public String selectViewXml(@ModelAttribute("loginVO") LoginVO loginVO
			,  XmlInfoVO vo
			, HttpServletRequest request
			, BindingResult bindingResult			
			, ModelMap model) throws Exception {
		
		   model.addAttribute("regist", xmlInfoManageService.selectXmlrInfoManageDetail(vo.getXmlSeq()) );	     		     				
			return "/backoffice/sub/operManage/xmlView";		
		
	}
	
	@RequestMapping("/backoffice/sub/operManage/xmlDetail.do")
	public String selectDetailXml(@ModelAttribute("loginVO") LoginVO loginVO
									,  XmlInfoVO vo
									, HttpServletRequest request
									, BindingResult bindingResult			
									, BindingResult result
									, ModelMap model) throws Exception {
		
		   model.addAttribute("selectWorkgubun", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT006"));
		   
		   model.addAttribute("regist", vo);
		   LOGGER.debug("mode:"+vo.getMode());
		   if (!vo.getMode().equals("Ins")){
   		        model.addAttribute("regist", xmlInfoManageService.selectXmlrInfoManageDetail(vo.getXmlSeq()) );
   		        LOGGER.debug("mode:"+vo.getMode());
		   }
			return "/backoffice/sub/operManage/xmlDetail";			
	}
	
	// groupCode 체크 
	@RequestMapping("/backoffice/sub/operManage/processCheck.do")
	@ResponseBody 
	public String selectIdCheck(HttpServletRequest request) throws Exception{			
			String xmlProcessName = request.getParameter("xmlProcessName") != null ? request.getParameter("xmlProcessName") : "";			
			int processCheck = xmlInfoManageService.selectXmlProcessCount(xmlProcessName)		;
			return Integer.toString(processCheck);
	}
	
	
	@RequestMapping("/backoffice/sub/operManage/xmlUpdate.do")
	public String updateXml(@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("XmlInfoVO")  XmlInfoVO vo
			, HttpServletRequest request
			, BindingResult bindingResult			
			, BindingResult result
			, ModelMap model)throws Exception{
		
		
		model.addAttribute("regist", vo);
		String meesage = "";
		String url = "redirect:/backoffice/sub/operManage/xmlList.do";
		     
		
		try{
			
			
			
			int ret  = 0;
			if (vo.getMode().equals("Ins")){
				ret = xmlInfoManageService.insertXmlInfoManage(vo);
				meesage = "sucess.common.insert";
				url = "forward:/backoffice/sub/operManage/xmlList.do";				
			}else {
				 ret = xmlInfoManageService.updateXmlInfoManage(vo);
				 meesage = "sucess.common.update";
				 url = "forward:/backoffice/sub/operManage/xmlView.do";
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
			url = "forward:/backoffice/sub/operManage/xmlList.do";
		}finally {
			
		}					
		return url;
		
	}
	
	// groupCodeJson 체크 
	@RequestMapping("/backoffice/sub/operManage/jsonAuthView.do")
	@ResponseBody 
	public String selectJsonPage(HttpServletRequest request) throws Exception{			
			String xmlSeq = request.getParameter("xmlSeq") != null ? request.getParameter("xmlSeq") : "";							
			return jsonDoc(xmlInfoManageService.selectXmlrInfoManageDetail(xmlSeq));
	}
	// jsonDoc 만들기
	public String jsonDoc(XmlInfo vo)
	{	
		String[] inputParamArrays ;
		inputParamArrays = vo.getXmlInputParam().split(",");
		String[] inputParamSampleArrays ;
		inputParamSampleArrays = vo.getXmlInputParamSample().split(",");
		
		
		
		JSONObject obj = new JSONObject();
		obj.put("command_type", vo.getXmlProcessName());
		
		
		
		try {			
			 JSONArray dataArray = new JSONArray();
			 
			 JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
			 
			for (int i = 0; i < inputParamArrays.length; i++){			
				sObject.put(inputParamArrays[i].toString().trim(), inputParamSampleArrays[i].toString().trim());
				
			}
			
			dataArray.add(sObject);
			obj.put("command_data", dataArray);
			
		}catch (JSONException e) {
			e.printStackTrace();
			LOGGER.debug("jsonDoc:"+ e.toString());	
		}		
	   return obj.toJSONString(); 	
	}
	// groupCode 체크 
	@RequestMapping("/backoffice/sub/operManage/xmlAuthView.do")
	@ResponseBody 
	public String selectXmlPage(HttpServletRequest request) throws Exception{			
			String xmlSeq = request.getParameter("xmlSeq") != null ? request.getParameter("xmlSeq") : "";							
			return xmlDocument(xmlInfoManageService.selectXmlrInfoManageDetail(xmlSeq));
	}
	//xml Doc 만들기
	public String xmlDocument (XmlInfo vo)
	{
		String[] inputParamArrays ;
		inputParamArrays = vo.getXmlInputParam().split(",");
		String[] inputParamSampleArrays ;
		inputParamSampleArrays = vo.getXmlInputParamSample().split(",");
		
		
		StringBuilder xmlDoc = new StringBuilder();
		xmlDoc.append("<?xml version='1.0' encoding='UTF-8'?>\r\n");
		xmlDoc.append("<CM_Document protocol = 'CMXML' version = '1.0'>\r\n");
		xmlDoc.append("   <command>\r\n ");
		xmlDoc.append("       <command_type>" + vo.getXmlProcessName() + "</command_type>\r\n" );
		xmlDoc.append("       <command_data>\r\n");
		for (int i = 0; i < inputParamArrays .length; i++) 
		{
		    xmlDoc.append("            <" + inputParamArrays[i].toString().trim() + ">"+  inputParamSampleArrays[i].toString().trim() + "</" + inputParamArrays[i].toString().trim() + ">\r\n");			
		}
		xmlDoc.append("       </command_data>\r\n");
		xmlDoc.append("   </command>\r\n ");
		xmlDoc.append("</CM_Document>\r\n ");
		return xmlDoc.toString();
	}
	public String xmlDocumentNoSpace (XmlInfo vo)
	{
		String[] inputParamArrays ;
		inputParamArrays = vo.getXmlInputParam().split(",");
		String[] inputParamSampleArrays ;
		inputParamSampleArrays = vo.getXmlInputParamSample().split(",");
		
		
		StringBuilder xmlDoc = new StringBuilder();
		xmlDoc.append("<?xml version='1.0' encoding='UTF-8'?>");
		xmlDoc.append("<CM_Document protocol = 'CMXML' version = '1.0'>");
		xmlDoc.append("<command>");
		xmlDoc.append("<command_type>" + vo.getXmlProcessName() + "</command_type>" );
		xmlDoc.append("<command_data>");
		for (int i = 0; i < inputParamArrays .length; i++) 
		{
		    xmlDoc.append("<" + inputParamArrays[i].toString().trim() + ">"+  inputParamSampleArrays[i].toString().trim() + "</" + inputParamArrays[i].toString().trim() + ">");			
		}
		xmlDoc.append("</command_data>");
		xmlDoc.append("</command>");
		xmlDoc.append("</CM_Document>");
		return xmlDoc.toString();
	}
	//결과 관련 함수 만들기 
	@RequestMapping("/backoffice/sub/operManage/xmlAuthReq.do")
	public String selectXmlSendPage(HttpServletRequest request, ModelMap model ) throws Exception{
		
		String xmlSeq =   request.getParameter("xmlSeq") != null ? request.getParameter("xmlSeq") : "";
		String xml =  xmlDocumentNoSpace(xmlInfoManageService.selectXmlrInfoManageDetail(xmlSeq));
		
		XmlInfo vo = new XmlInfo();		
		vo.setXmlSeq(xmlSeq);
		model.addAttribute("regist", vo  );
		model.addAttribute("xml", xml  );
		
		return "/backoffice/sub/operManage/xmlAuthReq";
	}
	//결과 함수 json
	@RequestMapping("/backoffice/sub/operManage/jsonAuthReq.do")
	public String selectJsonSendPage(HttpServletRequest request, ModelMap model ) throws Exception{
		
		
		
		
		String xmlSeq =   request.getParameter("xmlSeq") != null ? request.getParameter("xmlSeq") : "";
		
		
		
		String json =  jsonDoc(xmlInfoManageService.selectXmlrInfoManageDetail(xmlSeq));
	
		
		
		
		XmlInfo vo = new XmlInfo();		
		vo.setXmlSeq(xmlSeq);
		model.addAttribute("regist", vo  );
		model.addAttribute("json", json.replaceAll("\"", "'")  );
		
		return "/backoffice/sub/operManage/jsonAuthReq";
	}
	@RequestMapping("/backoffice/sub/operManage/jsonAuth.do")
	@ResponseBody
	public String selectJsonResultPage(HttpServletRequest request) throws Exception{
		
		String json =   request.getParameter("json") != null ? request.getParameter("json") : "";
	    String jsonResult = "";
	    
	    json = json.replace("'", "\"");
	    
	    JSONParser  jsonparse = new JSONParser(); 		
	    JSONObject jsonObject = (JSONObject) jsonparse.parse(json);						 
		String commandType = jsonObject.get("command_type").toString();
		
			
		
		int ProcessCk = xmlInfoManageService.selectXmlProcessCount(commandType);
		if (ProcessCk > 0){			
			System.out.println("json:" + json);	
			jsonResult = jsonDocResult(json);
		}else {
			jsonResult = "NO_JSON";				
		}			
		return jsonResult;
		
		
		
	}
	@RequestMapping("/backoffice/sub/operManage/serverDate.do")
	@ResponseBody
	public String serverDate()throws Exception{
		try{
		GregorianCalendar gc = new GregorianCalendar();
        long milis = gc.getTimeInMillis();// 밀리초로 바꿔준다        
        SimpleDateFormat sf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");; // 기본 데이타베이스 저장 타입
        Date d = gc.getTime(); // Date -> util 패키지
        String str = sf.format(d);        
        return  str;
		}catch (Exception e){
			LOGGER.debug("serverDate error:" + e.toString());
			return "";			
		}
	}
	// json 결과 파이지 
	public String jsonDocResult(String json) throws Exception{
		
		JSONParser  jsonparse = new JSONParser();
		String resultTxt = null; 
		try {
              JSONObject jsonObject = (JSONObject) jsonparse.parse(json);						 
			  String commandType = jsonObject.get("command_type").toString();
			  
			 
			  JSONArray dataInfoArray = (JSONArray) jsonObject.get("command_data");			 
			  JSONObject dataObject = (JSONObject) dataInfoArray.get(0);	
			  
			  
			  DidInfo didinfo = new DidInfo();				
			  ContentMutiInfoVO conMutiInfo = new ContentMutiInfoVO();
			  SendMsgInfo sminfo = new SendMsgInfo();
			  
			  
			  if (commandType.equals("SP_DIDAUTH")){
				  
				  
				    didinfo.setDidId( dataObject.get("DID_ID").toString() );
				 	didinfo.setDidMac(  dataObject.get("DID_MAC").toString() );
					didinfo.setDidIpaddr(  dataObject.get("DID_IP").toString());
					didinfo.setDidSendInterval( dataObject.get("DID_INTERVAL").toString() );
					didinfo.setDidType(dataObject.get("DID_TYPE").toString());
					
					int ret = didInfoManageService.updateDidMac(didinfo);		
					
					resultTxt = "{'command_type':'"+commandType+"','result':'"+  sendRed( dataObject.get("DID_ID").toString(), commandType,  ret , "", dataObject.get("DID_MAC").toString()) +"'}";
					
			  }else if (commandType.equals("SP_BRODSTATE")){
				    
				    didinfo.setDidId( dataObject.get("DID_ID").toString().trim() );
				 	didinfo.setDidMac(  dataObject.get("DID_MAC").toString().trim() );				 	
					didinfo.setDidSttus("Y");							
					didInfoManageService.updateDidState(didinfo);					    
					resultTxt = "{'command_type':'"+commandType+"','result':{'BROD_CNT':'"+brodCnt( didinfo.getDidId(),  didinfo.getDidMac()) +"','ORD_CNT':'"+ orderCnt( didinfo.getDidId(),  didinfo.getDidMac()) +"','MSG_CNT':'"+ messageCnt( didinfo.getDidId(),  didinfo.getDidMac()) +"'} }";
					
			  }else if (commandType.equals("SP_BRODSTATE_NEW")){
				    
				    didinfo.setDidId( dataObject.get("DID_ID").toString().trim() );
				 	didinfo.setDidMac(  dataObject.get("DID_MAC").toString().trim() );				 	
					didinfo.setDidSttus("Y");							
					didInfoManageService.updateDidState(didinfo);					    
					resultTxt = "{'command_type':'"+commandType+"','result':{'BROD_CNT':'"+brodCnt( didinfo.getDidId(),  didinfo.getDidMac()) +"','ORD_CNT':'"+ orderCnt( didinfo.getDidId(),  didinfo.getDidMac()) +"','MSG_CNT':'"+ messageCnt( didinfo.getDidId(),  didinfo.getDidMac()) +"','BASIC_CNT':'"+ basicCnt( didinfo.getDidId(),  didinfo.getDidMac()) +"'} }";
					
			  }else if (commandType.equals("SP_NEXTCONTENTFILELST")){	
				  
				  
				  List<ContentFileInfoVO> resultContent =  fileService.selectFileContentList(dataObject.get("CON_SEQ").toString());
				  
				  JSONObject obj = new JSONObject();
				  obj.put("command_type", commandType);
				  JSONArray dataArray = new JSONArray();
				  			
				  for (int i = 0; i < resultContent.size(); i++){
					 JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
					sObject.put(  "CON_SEQ", resultContent.get(i).getConSeq());
					sObject.put(  "DETAIL_SEQ", resultContent.get(i).getDetailSeq());
					sObject.put(  "FILE_STRE_COURS", resultContent.get(i).getFileStreCours());
					sObject.put(  "ATCH_FILE_ID", resultContent.get(i).getAtchFileId());
					sObject.put(  "STRE_FILE_NM", resultContent.get(i).getStreFileNm());
					sObject.put(  "FILE_EXTSN", resultContent.get(i).getFileExtsn());
					sObject.put(  "FILE_SIZE", resultContent.get(i).getFileSize());
					sObject.put(  "FILE_ORDER", resultContent.get(i).getFileOrder());
					dataArray.add(sObject);
				  }			 	  
				  obj.put("CONINFO", dataArray);				 
				  resultTxt = obj.toJSONString();
				  obj.clear();
				  
			  }else if (commandType.equals("SP_NEXTCONTENTINFO")){
					List<ContentMutiInfoVO> resultContent =  (List<ContentMutiInfoVO>) mutiInfoService.selectNextContentMutiInfo(dataObject.get("CON_SEQ").toString());
					
					
					  JSONObject obj = new JSONObject();
					  obj.put("command_type", commandType);
					  JSONArray dataArray = new JSONArray();
					  			
					  for (int i = 0; i < resultContent.size(); i++){
						 JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
						sObject.put(  "CON_SEQ", resultContent.get(i).getConSeq());
						sObject.put(  "CON_SCREEN", resultContent.get(i).getConScreen());
						sObject.put(  "CON_TYPE", resultContent.get(i).getConType());
						sObject.put(  "CON_USEYN", resultContent.get(i).getConUseYn());
						sObject.put(  "CON_WIDTH", resultContent.get(i).getConWidth());
						sObject.put(  "CON_HEIGHT", resultContent.get(i).getConHeight());
						sObject.put(  "CON_MID", resultContent.get(i).getConMid());
						sObject.put(  "CON_NM",  URLDecoder.decode(resultContent.get(i).getConNm(), "UTF-8"));
						sObject.put(  "CON_TIME", resultContent.get(i).getConTime());
						sObject.put(  "CON_NEXTSEQ", resultContent.get(i).getConNextSeq());
						sObject.put(  "CON_FILE", resultContent.get(i).getConLocalfile());
						sObject.put(  "DETAIL_SEQ", resultContent.get(i).getDetailSeq());
						sObject.put(  "DETAIL_ORDER", resultContent.get(i).getDetailOrder());
						sObject.put(  "CON_TYPE", resultContent.get(i).getConTypeD());
						sObject.put(  "IMAGE_SLIDTYPE", resultContent.get(i).getImageSlidType());
						sObject.put(  "TIME_INTERVAL", resultContent.get(i).getTimeIntervalD());
						dataArray.add(sObject);
					  }			 	  
					  obj.put("CONINFO", dataArray);				 
					  resultTxt = obj.toJSONString();
					  obj.clear();
					
					
			  }else if (commandType.equals("SP_MESSAGEINFO")){
				  didinfo.setDidId( dataObject.get("DID_ID").toString() );
				  didinfo.setDidMac(  dataObject.get("DID_MAC").toString() );
				  // 메세지 전문 확인 후 메세지 TB_MESSAGEHISTORY 에 등록후 리스트 보여 주기 		
				  List<ContentMessageInfo> messageInfo = (List<ContentMessageInfo>) messageService.selectContentMessageInfoDidList(dataObject.get("DID_ID").toString());
				  
				  
				  sminfo.setDidId(dataObject.get("DID_ID").toString());
				  sminfo.setDidMacAddress(dataObject.get("DID_MAC").toString());
				  
				  JSONObject obj = new JSONObject();
				  obj.put("command_type", commandType);
				  JSONArray dataArray = new JSONArray();
				  for  (int i = 0; i < messageInfo.size(); i++){
					  JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
					  //SEND_MESSAGE, SEND_MESSAGESTARTDAY, SEND_MESSAGEENDDAY, SEND_MESSAGESTARTTIME, SEND_MESSAGEENDTIME, SEND_FONTTYPE
					  sObject.put(  "SEND_DIDID", messageInfo.get(i).getSendDidId());
					  sObject.put(  "SEND_MESSAGE", URLDecoder.decode(messageInfo.get(i).getSendMessage(), "UTF-8") );
					  sObject.put(  "SEND_MESSAGESTARTDAY", messageInfo.get(i).getSendMessageStartDay());
					  sObject.put(  "SEND_MESSAGEENDDAY", messageInfo.get(i).getSendMessageEndDay());
					  sObject.put(  "SEND_MESSAGESTARTTIME", messageInfo.get(i).getSendMessageStartTime());
					  sObject.put(  "SEND_MESSAGEENDTIME", messageInfo.get(i).getSendMessageEndTime());
					  sObject.put(  "SEND_FONTTYPE", messageInfo.get(i).getSendFontType());
					  dataArray.add(sObject);
					  
					  if (sendMsgInfoManageService.selectSendDidIDCheckCnt(messageInfo.get(i).getSendDidId()) < 1 ){
						  sminfo.setSendDidId(messageInfo.get(i).getSendDidId());
						  sendMsgInfoManageService.insertSendMessageInsertManage(sminfo);  
					  }
					  
					  //최종 message에 인서트 하기 
				  }
				  
				  obj.put("MESSAGEINFO", dataArray);				 
				  resultTxt = obj.toJSONString();
				  obj.clear();
				  
			  }else if (commandType.equals("SP_MESSAGEUPDATE")){
				  didinfo.setDidId( dataObject.get("DID_ID").toString() );
				  didinfo.setDidMac(  dataObject.get("DID_MAC").toString() );
				  String sendDidid = dataObject.get("SEND_DIDID").toString();
				  
				  String MsgSeq = sendMsgInfoManageService.selectSendDidIDMsgSeq(sendDidid);
				  int ret = 0;
				  
				  
				  sminfo.setDidId(dataObject.get("DID_ID").toString());
				  sminfo.setDidMacAddress(dataObject.get("DID_MAC").toString());
				  sminfo.setMsgSeq(MsgSeq);
                  if (dataObject.get("ERROR_MESSAGE").toString().equals("OK")){
                	  ret = messageService.updateContentMessageInfoClientManage(sendDidid);
                	  sminfo.setSendResult("OK");
                	  sminfo.setErrorMessage(dataObject.get("ERROR_MESSAGE").toString());
				  }else {
					  sminfo.setSendResult("FALSE");
					  sminfo.setErrorMessage(dataObject.get("ERROR_MESSAGE").toString());
				  }
                  ret = sendMsgInfoManageService.updateSendMsgInfoManage(sminfo);
                  
				  //업데이트 확인 
                  if (ret > 0){
						resultTxt = "{'command_type':'"+commandType+"','result':'O'}";
					}else {
						resultTxt = "{'command_type':'"+commandType+"','result':'F'}";
				  }
				  
			  }else if (commandType.equals("SP_DIDSTATE")){
				    
				    didinfo.setDidId( dataObject.get("DID_ID").toString() );
				 	didinfo.setDidMac(  dataObject.get("DID_MAC").toString() );				 	
					didinfo.setDidSttus("Y");							
					int ret = didInfoManageService.updateDidState(didinfo);					    
					resultTxt = "{'command_type':'"+commandType+"','result':{'SCH_CNT':'"+scheduleDID( didinfo.getDidId(),  didinfo.getDidMac()) +"','ORD_CNT':'"+ orderCnt( didinfo.getDidId(),  didinfo.getDidMac()) +"','MSG_CNT':'"+ messageCnt( didinfo.getDidId(),  didinfo.getDidMac()) +"'} }";
				  
			  }else if (commandType.equals("SP_BRODSCH")){
				  BrodScheduleInfo vo = new BrodScheduleInfo();
				  vo.setDidId(dataObject.get("DID_ID").toString() );
				  vo.setDidMac(dataObject.get("DID_MAC").toString() );
				  
				  JSONObject obj = new JSONObject();
				  obj.put("command_type", commandType);
				  
				  
				  try
				  {
					  vo = brodSchedue.selectBordScheduleCode(vo);
					  JSONArray dataArray = new JSONArray();					  
					  JSONObject sObject = new JSONObject();
					  sObject.put("BROD_CODE", vo.getBrodCode());
					  sObject.put("CENTER_STARTTIME", vo.getCenterStartTime());
					  sObject.put("CENTER_ENDTIME", vo.getCenterEndTime());
					  sObject.put("CENTER_ID", vo.getCenterId());
					  sObject.put("FILE_STRE_COURS", vo.getFileStreCours());
					  sObject.put("STRE_FILE_NM", vo.getStreFileNm());
					  dataArray.add(sObject);
					  obj.put("BRODINFO", dataArray);  					  
				  }
				  catch (Exception e)
				  {
					  
				  }
				  				 
				  resultTxt = obj.toJSONString();
				  obj.clear();
				  vo = null;
				  
			  }else if (commandType.equals("SP_REBRODSCH")){
				  BrodScheduleInfo vo = new BrodScheduleInfo();
				  vo.setDidId(dataObject.get("DID_ID").toString() );
				  vo.setDidMac(dataObject.get("DID_MAC").toString() );
				  
				  JSONObject obj = new JSONObject();
				  obj.put("command_type", commandType);
				  
				  try
				  {
					  vo = brodSchedue.selectBordScheduleCodeRedown(vo);
					  JSONArray dataArray = new JSONArray();					  
					  JSONObject sObject = new JSONObject();
					  sObject.put("BROD_CODE", vo.getBrodCode());
					  sObject.put("CENTER_STARTTIME", vo.getCenterStartTime());
					  sObject.put("CENTER_ENDTIME", vo.getCenterEndTime());			  
					  sObject.put("CENTER_ID", vo.getCenterId());
					  sObject.put("FILE_STRE_COURS", vo.getFileStreCours());
					  sObject.put("STRE_FILE_NM", vo.getStreFileNm());
					  dataArray.add(sObject);
					  obj.put("BRODINFO", dataArray);  					  
				  }
				  catch (Exception e)
				  {
					  
				  }
				  				 
				  resultTxt = obj.toJSONString();
				  obj.clear();
				  vo = null;
				  
			  }else if (commandType.equals("SP_BRODSCHFILELST")){
				  
				  BrodContentDetail vo = new BrodContentDetail();
				  vo.setDidId(dataObject.get("DID_ID").toString() );
				  vo.setDidMac(dataObject.get("DID_MAC").toString() );
				  vo.setBrodCode(dataObject.get("BROD_CODE").toString());
				  
				  
				  List<BrodContentDetail> resultContent =  brodDetail.selectBrodFileList(vo);
					
				  JSONObject obj = new JSONObject();
				  obj.put("command_type", commandType);
				  JSONArray dataArray = new JSONArray();
				  			
				  for (int i = 0; i < resultContent.size(); i++){
					JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
					sObject.put("ATCH_FILE_ID", resultContent.get(i).getAtchFileId());
					sObject.put("FILESTRECOURS", resultContent.get(i).getFileStreCours());
					sObject.put("STREFILENM", resultContent.get(i).getStreFileNm());					
					dataArray.add(sObject);
				  }			 					  
				  obj.put("FILEINFO", dataArray);				 
				  resultTxt = obj.toJSONString();
				  obj.clear();
				  vo = null;
				  
			  }else if (commandType.equals("SP_BASICSCHFILELST")){
				  
				  
				  
				  List<BasciBrodFileInfoVO> resultContent =  basicFileService.selectBasicBrodFileLst(dataObject.get("BASIC_CODE").toString()) ;
					
				  JSONObject obj = new JSONObject();
				  obj.put("command_type", commandType);
				  JSONArray dataArray = new JSONArray();
				  			
				  for (int i = 0; i < resultContent.size(); i++){
					JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
					sObject.put("ATCH_FILE_ID", resultContent.get(i).getAtchFileId());
					sObject.put("FILESTRECOURS", resultContent.get(i).getFileStreCours());
					sObject.put("STREFILENM", resultContent.get(i).getStreFileNm());					
					dataArray.add(sObject);
				  }			 					  
				  obj.put("FILEINFO", dataArray);				 
				  resultTxt = obj.toJSONString();
				  obj.clear();
				  
			  }else if (commandType.equals("SP_BRODSCHLST")){
				  
				  BrodOrganization vo = new BrodOrganization();
				  vo.setDidId(dataObject.get("DID_ID").toString() );
				  vo.setDidMac(dataObject.get("DID_MAC").toString() );
				  vo.setBrodCode(dataObject.get("BROD_CODE").toString());
				  
				  
				  List<BrodOrganization> resultContent =  brodOrgService.selectBrodOrgnizationDid(vo);
					
				  JSONObject obj = new JSONObject();
				  obj.put("command_type", commandType);
				  JSONArray dataArray = new JSONArray();
				  			
				  for (int i = 0; i < resultContent.size(); i++){
					JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
					sObject.put("BROD_TIME", resultContent.get(i).getBrodTime());
					sObject.put("STRE_FILE_NM", resultContent.get(i).getStreFileNm());
					sObject.put("BROD_SEQ", resultContent.get(i).getBrodSeq());					
					dataArray.add(sObject);
				  }			 					  
				  obj.put("REPORTINFO", dataArray);				 
				  resultTxt = obj.toJSONString();
				  obj.clear();
				  vo = null;
				  
			  }else if (commandType.equals("SP_BRODDOWNCHECK")){
				  
				  BrodScheduleInfo vo = new BrodScheduleInfo();
				  vo.setCenterId(dataObject.get("CENTER_ID").toString() );
				  vo.setBrodCode(dataObject.get("BROD_CODE").toString());
				  vo.setDidDownCheck(dataObject.get("DID_DOWNCHECK").toString());
				  //  				  
				  int ret = brodSchedue.updateBrodDidCenterID(vo);               
				  
				  
				  //업데이트 확인 
                  if (ret > 0){
						resultTxt = "{'command_type':'"+commandType+"','result':'O'}";
					}else {
						resultTxt = "{'command_type':'"+commandType+"','result':'F'}";
				  }					
				  vo = null;
				  
			  }else if (commandType.equals("SP_BASICDOWNCHECK")){
				  
				  BasicBrodScheduleInfoVO vo = new BasicBrodScheduleInfoVO();
				  vo.setCenterId(dataObject.get("CENTER_ID").toString() );
				  vo.setBasicCode(dataObject.get("BASIC_CODE").toString());
				  vo.setDidDowncheck(dataObject.get("DID_DOWNCHECK").toString());
				  //  				  
				  int ret = schService.updateBasicBrodScheduleCenterDownCheck(vo);                
				  
				  
				  //업데이트 확인 
                  if (ret > 0){
						resultTxt = "{'command_type':'"+commandType+"','result':'O'}";
					}else {
						resultTxt = "{'command_type':'"+commandType+"','result':'F'}";
				  }					
				  vo = null;
				  
			  }else if (commandType.equals("SP_DIDCONTENTLST")){
				  
				  
				    conMutiInfo.setDidId(dataObject.get("DID_ID").toString());
				    conMutiInfo.setDidMac(dataObject.get("DID_MAC").toString());
				    
				    //콘텐츠 리스트 보여주기 
					
					  List<ContentMutiInfoVO> resultContent =  mutiInfoService.selectDIDContentLst(conMutiInfo);
					
					  JSONObject obj = new JSONObject();
					  obj.put("command_type", commandType);
					  JSONArray dataArray = new JSONArray();
					  			
					  for (int i = 0; i < resultContent.size(); i++){
						JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
						sObject.put("HIS_SEQ", resultContent.get(i).getHisSeq());
						sObject.put("SCH_CODE", resultContent.get(i).getSchCode());
						sObject.put("CON_SEQ", resultContent.get(i).getConSeq());
						sObject.put("CON_NM", URLDecoder.decode(resultContent.get(i).getConNm(), "UTF-8"));
						sObject.put("CON_SCREEN", resultContent.get(i).getConScreen());
						sObject.put("CON_TYPE", resultContent.get(i).getConType());
						sObject.put("CON_USEYN", resultContent.get(i).getConUseYn());
						sObject.put("CON_WIDTH", resultContent.get(i).getConWidth());
						sObject.put("CON_HEIGHT", resultContent.get(i).getConHeight());
						sObject.put("CON_MID", resultContent.get(i).getConMid());						
						sObject.put("CON_TIME", resultContent.get(i).getConTime());
						sObject.put("CON_NEXTSEQ", resultContent.get(i).getConNextSeq());
						sObject.put("CON_FILE", resultContent.get(i).getConFile());
						sObject.put("CON_LOCALFILE", resultContent.get(i).getConLocalfile());
						sObject.put("SCH_EMERGUBUN", resultContent.get(i).getSchEmerGubun());
						
						dataArray.add(sObject);
					  }			 	
					  
					  obj.put("CONINFO", dataArray);				 
					  resultTxt = obj.toJSONString();
					  obj.clear();
				  
			  }else if (commandType.equals("SP_DIDCONTENTLSTUPDATECHECK")){
				    
				    didinfo.setDidId( dataObject.get("DID_ID").toString() );
				 	didinfo.setDidMac(  dataObject.get("DID_MAC").toString() );				 	
					//업데이트값 					
					int resultUpdate = sendHistory.updateContentSendHistoryConInfoManage(dataObject.get("HIS_SEQ").toString());
					
					
                    sminfo.setDidId(dataObject.get("DID_ID").toString());
					sminfo.setDidMacAddress(dataObject.get("DID_MAC").toString());
					sminfo.setXmlProcessName("SP_DIDCONTENTLSTUPDATECHECK");
					sminfo.setErrorMessage("HIS_SEQ:" + dataObject.get("HIS_SEQ").toString());
					
					if (resultUpdate > 0){
						sminfo.setSendResult("Y");					
						resultTxt = "{'command_type':'"+commandType+"','result':'O'}";						
					}else {
						sminfo.setSendResult("N");
						resultTxt = "{'command_type':'"+commandType+"','result':'F'}";
					}
					//전문 정리 
					int ret =  sendMsgInfoManageService.insertSendMsgInfoManage(sminfo);
					
					
			  }else if (commandType.equals("SP_SCHRESET")){
				    
				    didinfo.setDidId(  dataObject.get("DID_ID").toString() );
				 	didinfo.setDidMac(  dataObject.get("DID_MAC").toString() );
					
					/*String[]resultconSeqArrays;
					resultconSeqArrays =  dataObject.get("HIS_SEQ").toString().split("|");*/
				 	
					//업데이트값 					
					int	resultUpdate = sendHistory.updateContentSchUpdateCheckReset(dataObject.get("HIS_SEQ").toString());
					
					if (resultUpdate > 0){
						resultTxt = "{'command_type':'"+commandType+"','result':'O'}";
					}else {
						resultTxt = "{'command_type':'"+commandType+"','result':'F'}";
					}
					
			  }else if (commandType.equals("SP_DIDCONTENTPAGELST")){
					ContentDetailInfo detailPage = new ContentDetailInfo();
					detailPage.setDidId(dataObject.get("DID_ID").toString().trim());
					detailPage.setSchCode(dataObject.get("SCH_CODE").toString().trim());
					
					List<ContentDetailInfo> resultDetailPage = detailService.selectContentDetailDidPage(detailPage);
  
				     
					  JSONObject obj = new JSONObject();
					  obj.put("command_type", commandType);
					  JSONArray dataArray = new JSONArray();
					  			
					  for (int i = 0; i < resultDetailPage.size(); i++){
						JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
						sObject.put(  "HIS_SEQ", resultDetailPage.get(i).getHisSeq());
						sObject.put(  "SCH_CODE", resultDetailPage.get(i).getSchCode());
						sObject.put(  "CON_SEQ", resultDetailPage.get(i).getConSeq());
						sObject.put(  "CON_FILE", resultDetailPage.get(i).getConFile());						
						dataArray.add(sObject);
					  }			 	  
					  obj.put("CONINFO", dataArray);				 
					  resultTxt = obj.toJSONString();
					  obj.clear();
				  
			  }else if (commandType.equals("SP_DIDCONTENTPAGELSTUPDATECHECK")){
				  
				    conMutiInfo.setDidId(dataObject.get("DID_ID").toString());
					conMutiInfo.setDidMac(dataObject.get("DID_MAC").toString());
					
					String[]resultconSeqArrays;
					resultconSeqArrays = dataObject.get("HIS_SEQ").toString().split("|");
					//업데이트값 
					int resultUpdate = 0; 
					for (int a =0; a < resultconSeqArrays.length; a++){
						resultUpdate = sendHistory.updateContentSendHistoryConPageInfoManage(resultconSeqArrays[a].toString()) ;
					}
					if (resultUpdate > 0){
						resultTxt = "{'command_type':'"+commandType+"','result':'O'}";
					}else {
						resultTxt = "{'command_type':'"+commandType+"','result':'F'}";
					}				  
			  }else if (commandType.equals("SP_DIDCONTENTFILELST")){
				  
				  
				  
				    
				    ContentFileInfoVO fileSearch = new ContentFileInfoVO();
					fileSearch.setDidId(dataObject.get("DID_ID").toString());					
					fileSearch.setSchCode(dataObject.get("SCH_CODE").toString());					
					
					
					List<ContentFileInfoVO> resultFileLst = fileService.selectFileContentLstDid(fileSearch);
					
					JSONObject obj = new JSONObject();
				    obj.put("command_type", commandType);
				    JSONArray dataArray = new JSONArray();
				    
				   for (int i = 0; i < resultFileLst.size(); i++){
					    JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
						sObject.put(  "HIS_SEQ", resultFileLst.get(i).getHisSeq());
						sObject.put(  "SCH_CODE", resultFileLst.get(i).getSchCode());
						sObject.put(  "CON_SEQ", resultFileLst.get(i).getConSeq());
						sObject.put(  "DETAIL_SEQ", resultFileLst.get(i).getDetailSeq());
						sObject.put(  "FILE_STRE_COURS", resultFileLst.get(i).getFileStreCours());
						sObject.put(  "ATCH_FILE_ID", resultFileLst.get(i).getAtchFileId());
						sObject.put(  "STRE_FILE_NM", URLDecoder.decode(  resultFileLst.get(i).getStreFileNm() , "UTF-8"));						
						sObject.put(  "FILE_EXTSN", resultFileLst.get(i).getFileExtsn());
						sObject.put(  "FILE_SIZE", resultFileLst.get(i).getFileSize());
						sObject.put(  "FILE_ORDER", resultFileLst.get(i).getFileOrder());
						sObject.put(  "TIME_INTERVAL", resultFileLst.get(i).getTimeInterval());	
						dataArray.add(sObject);
				   }			 	  
				   obj.put("CONINFO", dataArray);				 
				   resultTxt = obj.toJSONString();
				   obj.clear();
				  
				  
			  }else if (commandType.equals("SP_DIDCONTENTFILELSTUPDATECHECK")){
				    conMutiInfo.setDidId(dataObject.get("DID_ID").toString());
					conMutiInfo.setDidMac(dataObject.get("DID_MAC").toString());
					 
					int resultUpdate = 0; 
					resultUpdate = sendHistory.updateContentSendHistoryConFileInfoManage(dataObject.get("HIS_SEQ").toString()) ;
					sminfo.setDidId(dataObject.get("DID_ID").toString());
					sminfo.setDidMacAddress(dataObject.get("DID_MAC").toString());
					sminfo.setXmlProcessName("SP_DIDCONTENTFILELSTUPDATECHECK");					                          
					sminfo.setErrorMessage("HIS_SEQ:" + dataObject.get("HIS_SEQ").toString());
						
					if (resultUpdate > 0){
						sminfo.setSendResult("Y");
						resultTxt = "{'command_type':'"+commandType+"', 'result':'O'}";
					}else {
						sminfo.setSendResult("N");
						resultTxt = "{'command_type':'"+commandType+"','result':'F'}";
					}				
					int ret =  sendMsgInfoManageService.insertSendMsgInfoManage(sminfo);
				  
			  }else if (commandType.equals("SP_DIDMONITERRESULT")){
				    
				    //화면 업데이트 
				    DidMoniterPicVO picVo = new DidMoniterPicVO();
					
					picVo.setDidFileNm(dataObject.get("DID_ID").toString());
					picVo.setDidMac(dataObject.get("DID_MAC").toString());
					picVo.setMsgSeq(dataObject.get("MSG_SEQ").toString());
					picVo.setDidFileNm(dataObject.get("FILENM").toString());
					
					
					//파일 업데이트 확인
					int ret = picService.insertDidMoniterPicManage(picVo);					
					sminfo.setMsgSeq(picVo.getMsgSeq());
					if (ret > 0 ){
						sminfo.setSendResult("Y");	
						sminfo.setErrorMessage("");
					}else {
						sminfo.setSendResult("N");						
						sminfo.setErrorMessage("DataBase Update Error");											
					}
					int retUpdate = sendMsgInfoManageService.updateSendMsgInfoManage(sminfo);
					
					if (retUpdate > 0){
						resultTxt = "{'command_type':'"+commandType+"','result':'O'}";
					}else {
						resultTxt = "{'command_type':'"+commandType+"','result':'F'}";
					}						
			  }else if (commandType.equals("SP_DIDREBOOTRESULT")){
				    //재부팅 관련 
				    
				    sminfo.setDidId(dataObject.get("DID_ID").toString());
				    sminfo.setDidMacAddress(dataObject.get("DID_MAC").toString());
					if (dataObject.get("ERROR_MESSAGE").toString().equals("OK")){
						sminfo.setSendResult("OK");
					}else {
						sminfo.setSendResult("FALSE");
					}
					sminfo.setErrorMessage(dataObject.get("ERROR_MESSAGE").toString());
					sminfo.setMsgSeq(dataObject.get("MSG_SEQ").toString());					
					int ret = sendMsgInfoManageService.updateSendMsgInfoManage(sminfo);
					
					if ( ret > 0){
						resultTxt = "{'command_type':'"+commandType+"','result':'O'}";
					}else {
						resultTxt = "{'command_type':'"+commandType+"','result':'F'}";
					}	
			  }else if  (commandType.equals("SP_DIDENDTIMERESULT")){
				    //종료 시간 변경 
				    
				    sminfo.setDidId(dataObject.get("DID_ID").toString());
				    sminfo.setDidMacAddress(dataObject.get("DID_MAC").toString());
					if (dataObject.get("ERROR_MESSAGE").toString().equals("OK")){
						sminfo.setSendResult("OK");
					}else {
						sminfo.setSendResult("FALSE");
					}
					sminfo.setErrorMessage(dataObject.get("ERROR_MESSAGE").toString());
					sminfo.setMsgSeq(dataObject.get("MSG_SEQ").toString());
					
					int ret = sendMsgInfoManageService.updateSendMsgInfoManage(sminfo);
				   
					if ( ret > 0){
						resultTxt = "{'command_type':'"+commandType+"','result':'O'}";
					}else {
						resultTxt = "{'command_type':'"+commandType+"','result':'F'}";
					}	
			  }else if (commandType.equals("SP_DIDREBOOT")){
   				     					
					sminfo.setDidId(dataObject.get("DID_ID").toString());
					sminfo.setDidMacAddress(dataObject.get("DID_MAC").toString());
					sminfo.setXmlProcessName("SP_DIDREBOOT");
					sminfo.setSendResult("N");
					
					int ret =  sendMsgInfoManageService.insertSendMsgInfoManage(sminfo);
					
					if ( ret > 0){
						int max_seq = sendMsgInfoManageService.selectMaxSeq();
						resultTxt = "{'command_type':'"+commandType+"','result':'"+Integer.toString(max_seq)+"'}";
					}else {
						resultTxt = "{'command_type':'"+commandType+"','result':'F'}";
					}	

			  }else if (commandType.equals("SP_DIDENDTIME")){
				  didinfo.setDidId(dataObject.get("DID_ID").toString());
					didinfo.setDidMac(dataObject.get("DID_MAC").toString());
					didinfo.setDidEndTime(dataObject.get("DID_ENDTIME").toString());					
					int ret = didInfoManageService.updateDidEndTime(didinfo);
					
					
					sminfo.setDidId(dataObject.get("DID_ID").toString());
					sminfo.setDidMacAddress(dataObject.get("DID_MAC").toString());
					sminfo.setErrorMessage(dataObject.get("DID_ENDTIME").toString());
					sminfo.setXmlProcessName("SP_DIDENDTIME");
					sminfo.setSendResult("N");
					
					int ret1 =  sendMsgInfoManageService.insertSendMsgInfoManage(sminfo);
					
					
					if (ret > 0 && ret1 > 0){
						int max_seq = sendMsgInfoManageService.selectMaxSeq();
						resultTxt = "{'command_type':'"+commandType+"','result':[{'MSG_SEQ':'"+Integer.toString(max_seq)+"','END_TIME':'"+dataObject.get("DID_ENDTIME").toString()+"'}]}";						
					}else {
						resultTxt = "{'command_type':'"+commandType+"','result':'F'}";
					}
			  }else if  (commandType.equals("SP_DIDMONITER")){
				    //자동 안됨 					
					sminfo.setDidId(dataObject.get("DID_ID").toString());
					sminfo.setDidMacAddress(dataObject.get("DID_MAC").toString());
					sminfo.setXmlProcessName("SP_DIDMONITER");
					sminfo.setSendResult("N");					
					int ret1 =  sendMsgInfoManageService.insertSendMsgInfoManage(sminfo);
					
					if ( ret1 > 0){
						int max_seq = sendMsgInfoManageService.selectMaxSeq();
						resultTxt = "{'command_type':'"+commandType+"','result':'"+Integer.toString(max_seq)+"'}";
					}else {
						resultTxt = "{'command_type':'"+commandType+"','result':'F'}";
					}
			  }else if (commandType.equals("SP_ORDERLST")){
				  
				    SendMsgInfoVO sminfoVO = new SendMsgInfoVO();
				    sminfoVO.setDidId(dataObject.get("DID_ID").toString());
				    sminfoVO.setDidMacAddress(dataObject.get("DID_MAC").toString());
					
					List<SendMsgInfoVO> resultLst = sendMsgInfoManageService.selectDIDOrderLst(sminfoVO);
					
					JSONObject obj = new JSONObject();
				    obj.put("command_type", commandType);
				    JSONArray dataArray = new JSONArray();
				  			
				   for (int i = 0; i < resultLst.size(); i++){
					    JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
						sObject.put(  "MSG_SEQ", resultLst.get(i).getMsgSeq());
						sObject.put(  "XML_PROCESS_NAME", resultLst.get(i).getXmlProcessName());
						sObject.put(  "SEND_REGDATE", resultLst.get(i).getSendRegDate());						
						dataArray.add(sObject);
				   }			 	  
				   obj.put("CONINFO", dataArray);				 
				   resultTxt = obj.toJSONString();
				   obj.clear();				   
				   sminfoVO = null;
				  
			  }
			  sminfo = null;
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return resultTxt;
	}
	
	
	@RequestMapping("/backoffice/sub/operManage/xmlAuth.do")
	@ResponseBody 
	public String selectXmlResultPage(HttpServletRequest request) throws Exception{			
					
		    String xml =   request.getParameter("xml") != null ? request.getParameter("xml") : "";
		    String xmlResult = "";
			InputSource is = new InputSource(new StringReader(xml)); 		
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			
			XPath  xpath = XPathFactory.newInstance().newXPath();
			
			String expression =  "//*/command_type";
			String processName = xpath.compile(expression).evaluate(doc);
			
			int ProcessCk = xmlInfoManageService.selectXmlProcessCount(processName);
			if (ProcessCk > 0){
				xmlResult = xmlDocumentResult(xml);				
			}else {
				xmlResult = "NO_XMLNM";				
			}			
			//System.out.println("xmlResult:" + xmlResult);
			return xmlResult;
			
			
	}
	public String xmlDocumentResult(String  xml)
	{
		try{
				InputSource is = new InputSource(new StringReader(xml)); 				
				Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
				
				XPath  xpath = XPathFactory.newInstance().newXPath();
				
				String expression =  "//*/command_type";
				String processName = xpath.compile(expression).evaluate(doc);
				
				XmlInfo vo = xmlInfoManageService.selectXmlrInfoManageNameDetail(processName);
				
				String[] inputParamArrays ;
				inputParamArrays = vo.getXmlInputParam().split(",");
				String[] outputParamSampleArrays ;
				outputParamSampleArrays = vo.getXmlOutputParam().split(",");
				int paramLength = inputParamArrays.length;
				String[] result;
				result = vo.getXmlInputParam().split(",");
				
				DidInfo didinfo = new DidInfo();
				
				ContentMutiInfoVO conMutiInfo = new ContentMutiInfoVO();
				
				
				NodeList nodeList = doc.getElementsByTagName("command_data");
				
				for(int i=0; i<nodeList.getLength();i++){					 
		            for(Node node = nodeList.item(i).getFirstChild(); node!=null; node=node.getNextSibling()){ //첫번째 자식을 시작으로 마지막까지 다음 형제를 실행
		            	//System.out.println(node.getNodeName()+":" + node.getFirstChild().getNodeValue() );
		                  for (int a = 0; a < result.length; a++ ){
		                	 
		                	  if ( result[a].trim().equals(node.getNodeName())  ){
		                		  result[a] = node.getFirstChild().getNodeValue();		                		
		                	  }
		                  }	 
		            }
		        }
				
				
				
				
				String resultTxt = "";
				StringBuilder xmlDoc = new StringBuilder();
				xmlDoc.append("<?xml version='1.0' encoding='UTF-8'?>\r\n");
				xmlDoc.append("<CM_Document protocol = 'CMXML' version = '1.0'>\r\n");
				xmlDoc.append("   <command>\r\n");
				xmlDoc.append("       <command_type>" + vo.getXmlProcessName() + "</command_type>\r\n" );
				xmlDoc.append("       <command_result>\r\n");
				//xmlDoc.append("         <results>\r\n");
				
				
				
				if (vo.getXmlProcessName().equals("SP_DIDAUTH")){
										
					didinfo.setDidId(result[0].toString());
					didinfo.setDidMac(result[1].toString());
					didinfo.setDidIpaddr(result[2].toString());
					didinfo.setDidSendInterval(result[3].toString());
					didinfo.setDidType(result[4].toString());
					
					
					int ret = didInfoManageService.updateDidMac(didinfo);					
					resultTxt = sendRed( result[0].toString(),  vo.getXmlProcessName(),  ret , "", result[1].toString());
					
					xmlDoc.append("           <result>"+resultTxt+"</result>\r\n");	
				}
				
				else if (vo.getXmlProcessName().equals("SP_NEXTCONTENTFILELST")) {
					List<ContentFileInfoVO> resultContent =  fileService.selectFileContentList(result[0].toString());
					
					
					xmlDoc.append("           <result>");
					
					
					for(int a =0;  a < resultContent.size(); a++ ) {
						xmlDoc.append("   <CONINFO>\r\n");
						xmlDoc.append("           <CON_SEQ>"+ resultContent.get(a).getConSeq()    +"</CON_SEQ>\r\n");						
						xmlDoc.append("           <DETAIL_SEQ>"+ resultContent.get(a).getDetailSeq()   +"</DETAIL_SEQ,>\r\n");
						xmlDoc.append("           <FILE_STRE_COURS>"+ resultContent.get(a).getFileStreCours()   +"</FILE_STRE_COURS,>\r\n");
						xmlDoc.append("           <ATCH_FILE_ID>"+ resultContent.get(a).getAtchFileId()  +"</ATCH_FILE_ID>\r\n");
						xmlDoc.append("           <STRE_FILE_NM>"+ resultContent.get(a).getStreFileNm() +"</STRE_FILE_NM>\r\n");
						xmlDoc.append("           <FILE_EXTSN>"+ resultContent.get(a).getFileExtsn()   +"</FILE_EXTSN>\r\n");
						xmlDoc.append("           <FILE_SIZE>"+ resultContent.get(a).getFileSize()   +"</FILE_SIZE>\r\n");
						xmlDoc.append("           <FILE_ORDER >"+ resultContent.get(a).getFileOrder()    +"</FILE_ORDER >\r\n");						
						xmlDoc.append("   </CONINFO>\r\n");
					}
					xmlDoc.append("           </result>\r\n");
					
				}
				else if (vo.getXmlProcessName().equals("SP_NEXTCONTENTINFO")) {
					
					
					List<ContentMutiInfoVO> resultContent =  (List<ContentMutiInfoVO>) mutiInfoService.selectNextContentMutiInfo(result[0].toString());
					xmlDoc.append("           <result>");
					
										
					for(int a =0;  a < resultContent.size(); a++ ) {
						xmlDoc.append("   <CONINFO>\r\n");
						xmlDoc.append("           <CON_SEQ>"+ resultContent.get(a).getConSeq()    +"</CON_SEQ>\r\n");
						xmlDoc.append("           <CON_SCREEN>"+ resultContent.get(a).getConScreen()   +"</CON_SCREEN>\r\n");
						//xmlDoc.append("           <CON_TYPE>"+ resultContent.get(a).getConType()   +"</CON_TYPE>\r\n");
						xmlDoc.append("           <CON_USEYN>"+ resultContent.get(a).getConUseYn() +"</CON_USEYN>\r\n");
						xmlDoc.append("           <CON_WIDTH>"+ resultContent.get(a).getConWidth()    +"</CON_WIDTH>\r\n");
						xmlDoc.append("           <CON_HEIGHT>"+ resultContent.get(a).getConHeight()   +"</CON_HEIGHT>\r\n");
						xmlDoc.append("           <CON_MID>"+ resultContent.get(a).getConMid()    +"</CON_MID>\r\n");
						xmlDoc.append("           <CON_NM>"+  URLDecoder.decode(resultContent.get(a).getConNm(), "UTF-8")    +"</CON_NM>\r\n");
						xmlDoc.append("           <CON_TIME>"+ resultContent.get(a).getConTime()    +"</CON_TIME>\r\n");
						xmlDoc.append("           <CON_NEXTSEQ>"+ resultContent.get(a).getConNextSeq()    +"</CON_NEXTSEQ>\r\n");
						xmlDoc.append("           <CON_FILE>"+ resultContent.get(a).getConFile()    +"</CON_FILE>\r\n");
						xmlDoc.append("           <DETAIL_SEQ>"+ resultContent.get(a).getDetailSeq()    +"</DETAIL_SEQ>\r\n");
						xmlDoc.append("           <DETAIL_ORDER>"+ resultContent.get(a).getDetailOrder()    +"</DETAIL_ORDER>\r\n");
						xmlDoc.append("           <CON_TYPE>"+ resultContent.get(a).getConTypeD()    +"</CON_TYPE>\r\n");
						xmlDoc.append("           <IMAGE_SLIDTYPE>"+ resultContent.get(a).getImageSlidType()    +"</IMAGE_SLIDTYPE>\r\n");
						xmlDoc.append("           <TIME_INTERVAL>"+ resultContent.get(a).getTimeIntervalD()    +"</TIME_INTERVAL>\r\n");
						xmlDoc.append("   </CONINFO>\r\n");
					}
					xmlDoc.append("           </result>\r\n");
					
				}
				else if (vo.getXmlProcessName().equals("SP_DIDSTATE")) {
					
					didinfo.setDidId(result[0].toString());
					didinfo.setDidMac(result[1].toString());
					didinfo.setDidSttus("Y");							
					int ret = didInfoManageService.updateDidState(didinfo);					
					
					xmlDoc.append("           <result>\r\n");
					xmlDoc.append("                    <SCH_CNT>"+scheduleDID(didinfo.getDidId(), didinfo.getDidMac())+"</SCH_CNT>\r\n");
					xmlDoc.append("                    <ORD_CNT>"+orderCnt(didinfo.getDidId(), didinfo.getDidMac())+"</ORD_CNT>\r\n");
					xmlDoc.append("                    <MSG_CNT>"+messageCnt(didinfo.getDidId(), didinfo.getDidMac())+"</MSG_CNT>\r\n");
					xmlDoc.append("           </result>\r\n");
				}else if (vo.getXmlProcessName().equals("SP_DIDCONTENTLST")){
					
					conMutiInfo.setDidId(result[0].toString());
					conMutiInfo.setDidMac(result[1].toString());
					//콘텐츠 리스트 보여주기 
					
					List<ContentMutiInfoVO> resultContent =  mutiInfoService.selectDIDContentLst(conMutiInfo);
					xmlDoc.append("           <result>");
					

					
										
					for(int a =0;  a < resultContent.size(); a++ ) {
						xmlDoc.append("   <CONINFO>\r\n");
						xmlDoc.append("           <HIS_SEQ>"+ resultContent.get(a).getHisSeq()    +"</HIS_SEQ>\r\n");
						xmlDoc.append("           <SCH_CODE>"+ resultContent.get(a).getSchCode()    +"</SCH_CODE>\r\n");
						xmlDoc.append("           <CON_SEQ>"+ resultContent.get(a).getConSeq()    +"</CON_SEQ>\r\n");
						xmlDoc.append("           <CON_NM>"+ URLDecoder.decode(resultContent.get(a).getConNm(), "UTF-8")   +"</CON_NM>\r\n");
						xmlDoc.append("           <CON_SCREEN>"+ resultContent.get(a).getConScreen()    +"</CON_SCREEN>\r\n");
						xmlDoc.append("           <CON_TYPE>"+ resultContent.get(a).getConType()   +"</CON_TYPE>\r\n");
						xmlDoc.append("           <CON_USEYN>"+ resultContent.get(a).getConUseYn()    +"</CON_USEYN>\r\n");
						xmlDoc.append("           <CON_WIDTH>"+ resultContent.get(a).getConWidth()   +"</CON_WIDTH>\r\n");
						xmlDoc.append("           <CON_HEIGHT>"+ resultContent.get(a).getConHeight()    +"</CON_HEIGHT>\r\n");
						xmlDoc.append("           <CON_MID>"+ resultContent.get(a).getConMid()    +"</CON_MID>\r\n");
						xmlDoc.append("           <CON_TIME>"+ resultContent.get(a).getConTime()    +"</CON_TIME>\r\n");
						xmlDoc.append("           <CON_NEXTSEQ>"+ resultContent.get(a).getConNextSeq()    +"</CON_NEXTSEQ>\r\n");
						xmlDoc.append("           <CON_FILE>"+ resultContent.get(a).getConFile()    +"</CON_FILE>\r\n");
						xmlDoc.append("   </CONINFO>\r\n");
					}
					xmlDoc.append("           </result>\r\n");
					
				}else if (vo.getXmlProcessName().equals("SP_DIDCONTENTLSTUPDATECHECK")){
				
					conMutiInfo.setDidId(result[0].toString());
					conMutiInfo.setDidMac(result[1].toString());
					
					//업데이트값 
					int resultUpdate = 0; 
					resultUpdate = sendHistory.updateContentSendHistoryConInfoManage(result[2].toString());
					
					if (resultUpdate > 0){
						xmlDoc.append("           <result>O</result>");					
					}else {
						xmlDoc.append("           <result>F</result>");
					}
					
				}else if (vo.getXmlProcessName().equals("SP_DIDCONTENTPAGELST")){
					ContentDetailInfo detailPage = new ContentDetailInfo();
					detailPage.setDidId(result[0].toString().trim());
					detailPage.setSchCode(result[1].toString().trim());
					
					
					
					List<ContentDetailInfo> resultDetailPage = detailService.selectContentDetailDidPage(detailPage);
					
					xmlDoc.append("           <result>\r\n");
					
					for(int a =0;  a < resultDetailPage.size(); a++ ) {
						xmlDoc.append("   <CONINFO>\r\n");
						xmlDoc.append("           <HIS_SEQ>"+ resultDetailPage.get(a).getHisSeq()    +"</HIS_SEQ>\r\n");
						xmlDoc.append("           <SCH_CODE>"+ resultDetailPage.get(a).getSchCode()    +"</SCH_CODE>\r\n");
						xmlDoc.append("           <CON_SEQ>"+ resultDetailPage.get(a).getConSeq()    +"</CON_SEQ>\r\n");
						xmlDoc.append("           <DETAIL_SEQ>"+ resultDetailPage.get(a).getDetailSeq()    +"</DETAIL_SEQ>\r\n");
						xmlDoc.append("           <DETAIL_ORDER>"+ resultDetailPage.get(a).getDetailOrder()    +"</DETAIL_ORDER>\r\n");
						xmlDoc.append("           <CON_TYPE>"+ resultDetailPage.get(a).getConType()   +"</CON_TYPE>\r\n");
						xmlDoc.append("           <IMAGE_SLIDTYPE>"+ resultDetailPage.get(a).getImageSlidtype()   +"</IMAGE_SLIDTYPE>\r\n");
						xmlDoc.append("           <TIME_INTERVAL>"+ resultDetailPage.get(a).getTimeInterval()   +"</TIME_INTERVAL>\r\n");		
						xmlDoc.append("   </CONINFO>\r\n");
					}
					xmlDoc.append("           </result>\r\n");
					
				}else if (vo.getXmlProcessName().equals("SP_DIDCONTENTPAGELSTUPDATECHECK")){
					conMutiInfo.setDidId(result[0].toString());
					conMutiInfo.setDidMac(result[1].toString());
					
					String[]resultconSeqArrays;
					resultconSeqArrays = result[2].toString().split("|");
					//업데이트값 
					int resultUpdate = 0; 
					for (int a =0; a < resultconSeqArrays.length; a++){
						resultUpdate = sendHistory.updateContentSendHistoryConPageInfoManage(resultconSeqArrays[a].toString()) ;
					}
					if (resultUpdate > 0){
						xmlDoc.append("           <result>O</result>");
					}else {
						xmlDoc.append("           <result>F</result>");
					}
					                                                                 
				}else if (vo.getXmlProcessName().equals("SP_DIDCONTENTFILELST")){
					
					ContentFileInfoVO fileSearch = new ContentFileInfoVO();
					fileSearch.setDidId(result[0].toString());
					fileSearch.setSchCode(result[1].toString());					
					
					List<ContentFileInfoVO> resultFileLst = fileService.selectFileContentLstDid(fileSearch);
					
					
                    xmlDoc.append("           <result>\r\n");
					for(int a =0;  a < resultFileLst.size(); a++ ) {
						xmlDoc.append("   <CONINFO>\r\n");
						xmlDoc.append("           <HIS_SEQ>"+ resultFileLst.get(a).getHisSeq()    +"</HIS_SEQ>\r\n");
						xmlDoc.append("           <SCH_CODE>"+ resultFileLst.get(a).getSchCode()    +"</SCH_CODE>\r\n");
						xmlDoc.append("           <CON_SEQ>"+ resultFileLst.get(a).getConSeq()    +"</CON_SEQ>\r\n");
						xmlDoc.append("           <DETAIL_SEQ>"+ resultFileLst.get(a).getDetailSeq()    +"</DETAIL_SEQ>\r\n");
						xmlDoc.append("           <FILE_STRE_COURS>"+ resultFileLst.get(a).getFileStreCours()    +"</FILE_STRE_COURS>\r\n");
						xmlDoc.append("           <ATCH_FILE_ID>"+ resultFileLst.get(a).getAtchFileId()   +"</ATCH_FILE_ID>\r\n");
						xmlDoc.append("           <STRE_FILE_NM>"+ URLDecoder.decode(  resultFileLst.get(a).getStreFileNm() , "UTF-8")     +"</STRE_FILE_NM>\r\n");
						xmlDoc.append("           <FILE_EXTSN>"+ resultFileLst.get(a).getFileExtsn()   +"</FILE_EXTSN>\r\n");
						xmlDoc.append("           <FILE_SIZE>"+ resultFileLst.get(a).getFileSize()   +"</FILE_SIZE>\r\n");
						xmlDoc.append("   </CONINFO>\r\n");
					}
					xmlDoc.append("           </result>\r\n");				
					
				}else if (vo.getXmlProcessName().equals("SP_DIDCONTENTFILELSTUPDATECHECK")){
					conMutiInfo.setDidId(result[0].toString());
					conMutiInfo.setDidMac(result[1].toString());
					
					//String[]resultconSeqArrays;
					//resultconSeqArrays = result[2].toString().split("|");
					//업데이트값 
					int resultUpdate = 0; 
					//for (int a =0; a < resultconSeqArrays.length; a++){
						resultUpdate = sendHistory.updateContentSendHistoryConFileInfoManage(result[2].toString()) ;
					//}
					if (resultUpdate > 0){
						xmlDoc.append("           <result>O</result>");
					}else {
						xmlDoc.append("           <result>F</result>");
					}
				// 화면 캡처 	
				}else if (vo.getXmlProcessName().equals("SP_DIDMONITERRESULT")   ){
					
					//화면 업데이트 하기 
					DidMoniterPicVO picVo = new DidMoniterPicVO();
					
					picVo.setDidFileNm(result[0].toString());
					picVo.setDidMac(result[1].toString());
					picVo.setMsgSeq(result[2].toString());
					picVo.setDidFileNm(result[3].toString());
					
					
					//파일 업데이트 확인
					int ret = picService.insertDidMoniterPicManage(picVo);
					
					SendMsgInfo sendInfo =  new SendMsgInfo();
					
					sendInfo.setMsgSeq(picVo.getMsgSeq());
					if (ret > 0 ){
						sendInfo.setSendResult("Y");	
						sendInfo.setErrorMessage("");
					}else {
						sendInfo.setSendResult("N");						
						sendInfo.setErrorMessage("DataBase Update Error");											
					}
					int retUpdate = sendMsgInfoManageService.updateSendMsgInfoManage(sendInfo);
					
					if (ret > 0 && retUpdate > 0){
						xmlDoc.append("           <result>O</result>");
					}else {
						xmlDoc.append("           <result>F</result>");
					}					
				}else if (vo.getXmlProcessName().equals("SP_DIDREBOOTRESULT")){
					//재부팅 관련 문서 
					SendMsgInfo sendInfo =  new SendMsgInfo();
					sendInfo.setDidId(result[0].toString());
					sendInfo.setDidMacAddress(result[1].toString());
					if (result[2].toString().equals("OK")){
						sendInfo.setSendResult("OK");
					}else {
						sendInfo.setSendResult("FALSE");
					}
					sendInfo.setErrorMessage(result[2].toString());
					sendInfo.setMsgSeq(result[3].toString());
					
					int ret = sendMsgInfoManageService.updateSendMsgInfoManage(sendInfo);
					
					if (ret > 0 ){
						xmlDoc.append("           <result>O</result>");
					}else {
						xmlDoc.append("           <result>F</result>");
					}		
					
				}else if (vo.getXmlProcessName().equals("SP_DIDENDTIMERESULT")){
					//종료 시간 변경 관려 
					SendMsgInfo sendInfo =  new SendMsgInfo();
					sendInfo.setDidId(result[0].toString());
					sendInfo.setDidMacAddress(result[1].toString());
					if (result[2].toString().equals("OK")){
						sendInfo.setSendResult("OK");
					}else {
						sendInfo.setSendResult("FALSE");
					}
					sendInfo.setErrorMessage(result[2].toString());
					sendInfo.setMsgSeq(result[3].toString());
					
					int ret = sendMsgInfoManageService.updateSendMsgInfoManage(sendInfo);
					
					if (ret > 0 ){
						xmlDoc.append("           <result>O</result>");
					}else {
						xmlDoc.append("           <result>F</result>");
					}		
					
				}else if (vo.getXmlProcessName().equals("SP_DIDREBOOT")){
					//재부팅 여부 
					SendMsgInfo sminfo = new SendMsgInfo();
					sminfo.setDidId(result[0].toString());
					sminfo.setDidMacAddress(result[1].toString());
					sminfo.setXmlProcessName("SP_DIDREBOOT");
					sminfo.setSendResult("N");
					
					int ret1 =  sendMsgInfoManageService.insertSendMsgInfoManage(sminfo);
					
					if ( ret1 > 0){
						int max_seq = sendMsgInfoManageService.selectMaxSeq();
						xmlDoc.append("           <RESULT>"+  Integer.toString(max_seq)  +"</RESULT>");
					}else {
						xmlDoc.append("           <RESULT>F</RESULT>");
					}
					
					
					
				}else if (vo.getXmlProcessName().equals("SP_DIDENDTIME")){
					//종료시간 설정 
					
					didinfo.setDidId(result[0].toString());
					didinfo.setDidMac(result[1].toString());
					didinfo.setDidEndTime(result[2].toString());					
					int ret = didInfoManageService.updateDidEndTime(didinfo);
					System.out.println("ret:"+ ret );
					
					SendMsgInfo sminfo = new SendMsgInfo();
					
					sminfo.setDidId(result[0].toString());
					sminfo.setDidMacAddress(result[1].toString());
					sminfo.setErrorMessage(result[2].toString());
					sminfo.setXmlProcessName("SP_DIDENDTIME");
					sminfo.setSendResult("N");
					
					int ret1 =  sendMsgInfoManageService.insertSendMsgInfoManage(sminfo);
					System.out.println("ret:"+ ret1 );
					
					if (ret > 0 && ret1 > 0){
						int max_seq = sendMsgInfoManageService.selectMaxSeq();
						xmlDoc.append("           <RESULT><MSG_SEQ>"+  Integer.toString(max_seq)  +"</MSG_SEQ><END_TIME>"+ result[2].toString() +"</END_TIME></RESULT>");
					}else {
						xmlDoc.append("           <RESULT>F</RESULT>");
					}
					
				}else if (vo.getXmlProcessName().equals("SP_DIDMONITER")){
					//화면 전송 요청
					SendMsgInfo sminfo = new SendMsgInfo();
					sminfo.setDidId(result[0].toString());
					sminfo.setDidMacAddress(result[1].toString());
					sminfo.setXmlProcessName("SP_DIDMONITER");
					sminfo.setSendResult("N");
					
					int ret1 =  sendMsgInfoManageService.insertSendMsgInfoManage(sminfo);
					
					if ( ret1 > 0){
						int max_seq = sendMsgInfoManageService.selectMaxSeq();
						xmlDoc.append("           <RESULT>"+  Integer.toString(max_seq)  +"</RESULT>");
					}else {
						xmlDoc.append("           <RESULT>F</RESULT>");
					}
					
				}else if ( vo.getXmlProcessName().equals("SP_ORDERLST")){
					//단말기 리스트 현황 
					SendMsgInfoVO sminfo = new SendMsgInfoVO();
					sminfo.setDidId(result[0].toString());
					sminfo.setDidMacAddress(result[1].toString());
					
					List<SendMsgInfoVO> resultLst = sendMsgInfoManageService.selectDIDOrderLst(sminfo);
					
					xmlDoc.append("           <result>\r\n");
					for(int a =0;  a < resultLst.size(); a++ ) {
						xmlDoc.append("                <CONINFO>\r\n");
						xmlDoc.append("                       <MSG_SEQ>"+ resultLst.get(a).getMsgSeq()    +"</MSG_SEQ>\r\n");
						xmlDoc.append("                       <XML_PROCESS_NAME>"+ resultLst.get(a).getXmlProcessName()   +"</XML_PROCESS_NAME>\r\n");
						xmlDoc.append("                       <SEND_REGDATE>"+ resultLst.get(a).getSendRegDate()    +"</SEND_REGDATE>\r\n");						
						xmlDoc.append("                </CONINFO>\r\n");
					}
					xmlDoc.append("           </result>\r\n");
					
				}
				
				
				//xmlDoc.append("         </results>\r\n");
				xmlDoc.append("       </command_result>\r\n");
				xmlDoc.append("   </command>\r\n ");
				xmlDoc.append("</CM_Document>\r\n ");
				return xmlDoc.toString();
				
				
		} catch (Exception ex){
			return "ERROR:" + ex.toString();
		}
	}
	
	//전송 결과 넣기
	public String sendRed( String didID, String xmlProcessName, int rnt , String errorMessage, String didMac  )throws Exception
	{
		SendMsgInfo sendinfo = new SendMsgInfo();
		String result = "";
		sendinfo.setDidId(didID);
		sendinfo.setXmlProcessName(xmlProcessName);
		sendinfo.setErrorMessage(errorMessage);
		sendinfo.setDidMacAddress(didMac);
		
		
		if (rnt > 0){
		    sendinfo.setSendResult("OK");
		    result = "OK";
		}else {
			sendinfo.setSendResult("FALSE");
			result = "FALSE";
		}		
		int ret = sendMsgInfoManageService.insertSendMsgInfoManage(sendinfo);		
		return result;
	}
	//음원 방송 
   public String brodCnt (String didId, String didMac) throws Exception{	   
	   BrodScheduleInfo vo = new BrodScheduleInfo();
	   vo.setDidId(didId);
	   vo.setDidMac(didMac);
	   int totalCnt = brodSchedue.selectBordScheduleCount(vo);	   
	   System.out.println("totalCnt:" + totalCnt);
	   
	   return String.valueOf(totalCnt); 
   }
   public String basicCnt (String didId, String didMac) throws Exception{	   
	   BasicBrodScheduleInfoVO vo = new BasicBrodScheduleInfoVO();
	   vo.setDidId(didId.toString().trim());
	   vo.setDidMac(didMac.toString().trim());
	   // String basicCode = schService.selectBasicBrodContentlDownCheck(vo);
	   
	   String basicCode = schService.selectBasicBrodContentlDownCheck(vo);
	   
	   if (basicCode == null){
		   basicCode = "N";
	   }
	   return basicCode; 
   }
   public BrodScheduleInfo brodCode(String didId, String didMac) throws Exception{
	   
	   BrodScheduleInfo vo = new BrodScheduleInfo();
	   vo.setDidId(didId);
	   vo.setDidMac(didMac);	   
	   return brodSchedue.selectBordScheduleCode(vo);
	   
   } 
   //음원 방송 추가 끝 부분 
   public String scheduleDID( String didId, String didMac) throws Exception
   {
	   SendMsgInfo sendinfo = new SendMsgInfo();
	   sendinfo.setDidId(didId);
	   sendinfo.setDidMacAddress(didMac);
	   //int ret1 = sendMsgInfoManageService.insertSendMsgInfoManageState(sendinfo);
	   int totalCnt = sendMsgInfoManageService.selectDIDScheduleCount(sendinfo);	   
	   return Integer.toString(totalCnt);
   }
   public String orderCnt (String didId, String didMac) throws Exception
   {
	   SendMsgInfo sendinfo = new SendMsgInfo();
	   sendinfo.setDidId(didId);
	   sendinfo.setDidMacAddress(didMac);
	   int totalCnt = sendMsgInfoManageService.selectDIDOrderCount(sendinfo);	   
	   return Integer.toString(totalCnt);
	   
   }
   //메세지 카운터 있는지 확인
   public String messageCnt (String didId, String didMac) throws Exception
   {
	   SendMsgInfo sendinfo = new SendMsgInfo();
	   sendinfo.setDidId(didId);
	   sendinfo.setDidMacAddress(didMac);
	   int totalCnt = sendMsgInfoManageService.selectDIDMessageCount(sendinfo);
	   return Integer.toString(totalCnt);
	   
   }
   public String xmlQuitReplace(String xmlResult){	   
	   return xmlResult.replaceAll("'", "^").replaceAll("<", "@").replaceAll(">", "~");
   }
   
   
   /**
    * Json 페이지 작업 간 통신 부분
    * FIRST : 2018-10-30, psm
    * MODIFY : 
    **/

   	
    @RequestMapping(value="/backoffice/sub/operManage/jsonRequest.do")
	@ResponseBody 
	public String jsonTypeWebDataSelect(HttpServletRequest request ) throws Exception {
    	String result = "";
    	String reqeustData = request.getParameter("requestData") != null ? request.getParameter("requestData") : "null";
		
		try{
			if(reqeustData.equals("null")){
				result = "{'result':{'req_type':'REQUEST_DATA_ERR','length':0},'data':[]}";
			} else {
				result = webUseDataProcessResult(reqeustData);
			}
		}catch(Exception e){
			result = "{'result':{'req_type':'SYSTEM_ERROR','length':0},'data':[]}";
			e.printStackTrace();
		}
		
		return result;
	}
   
   public String webUseDataProcessResult(String reqData){
	   
	   // reqData를 JSON형태로 만들어줄 것
	   String result = "";
	   
	   
	   try{
		   /* REQUEST VARIABLE */
		   reqData = reqData.replace("'", "\"");
		   System.out.println(reqData);
		   JSONParser jsonParser = new JSONParser();
		   JSONObject jsonObject = (JSONObject) jsonParser.parse(reqData);	
		   
		   String request_type = jsonObject.get("request_type").toString();
		   
		   JSONObject resultObj = new JSONObject();
		   resultObj =  (JSONObject) jsonObject.get("request_data");
		   
		   /* RESPONSE VARIABLE */
		   JSONObject jsonRes = new JSONObject();
		   JSONObject returnResult = new JSONObject();
		   JSONArray jsonData = new JSONArray();
		   JSONObject nowData = new JSONObject();
		   

		   if(request_type.equals("join-groupData") || request_type.equals("pwSearch-groupData")){ /* 01. 부서정보 호출, 요청 데이터 없음 */	
			   String joinGroupId 		= resultObj.get("groupId").toString() != null 		? resultObj.get("groupId").toString() 		: "";
			   String joinParentGroupId = resultObj.get("parentGroupId").toString() != null ? resultObj.get("parentGroupId").toString() : "";
			   System.out.println(joinGroupId + ", " + joinParentGroupId);
			   GroupVo groupVo = new GroupVo();
			   groupVo.setSearchKeyword("");
			   groupVo.setSearchCondition("");
			   groupVo.setGroupId(joinGroupId);
			   groupVo.setParentGroupId(joinParentGroupId);
			   List<GroupVo> returnData = groupManagerService.selectGroupManageCombo(groupVo);
			   
			   int returnLength = returnData.size();
			   for(int i = 0; i < returnLength; i++){
				   nowData = new JSONObject();
				   nowData.put("GROUP_ID", returnData.get(i).getGroupId());
				   nowData.put("PARENT_GROUP_ID", returnData.get(i).getParentGroupId());
				   nowData.put("GROUP_NM", returnData.get(i).getGroupNm());
				   jsonData.add(nowData);
			   }
			   
			   nowData = new JSONObject();
			   nowData.put("req_type", request_type);
			   nowData.put("length", returnLength);
			   jsonRes.put("result", nowData);
			   jsonRes.put("data", jsonData);
			   
			   
		   } else if (request_type.equals("join-centerData") || request_type.equals("pwSearch-centerData")){ /* 02. 점포정보 호출 (부서 수준에 따른) */ 
			   String centerSearchGroupId = resultObj.get("roleCode").toString() != null ? resultObj.get("roleCode").toString() : "";
			   
			   
			   CenterInfoVO centerInfoVO = new CenterInfoVO();
			   centerInfoVO.setRoleCode(centerSearchGroupId);
			   
			   List<CenterInfoVO> returnData = centerInfoManageService.selectGroupInCenterInfo(centerInfoVO);
			   
			   
			   int returnLength = returnData.size();
			   
			   for(int i = 0; i < returnLength; i++){
				   nowData = new JSONObject();
				   nowData.put("CENTER_ID", returnData.get(i).getCenterId());
				   nowData.put("CENTER_NM", returnData.get(i).getCenterNm());
				   jsonData.add(nowData);
			   }
			   nowData = new JSONObject();
			   nowData.put("req_type", request_type);
			   nowData.put("length", returnLength);
			   jsonRes.put("result", nowData);
			   jsonRes.put("data", jsonData);

			   
		   } else if (request_type.equals("join-confirm")){ // 이용신청 정보 입력
			   String mberId = resultObj.get("mberId").toString() != null ? resultObj.get("mberId").toString() : "";
			   String password = resultObj.get("password").toString() != null ? resultObj.get("password").toString() : "";
			   String mberNm = resultObj.get("mberNm").toString() != null ? resultObj.get("mberNm").toString() : "";
			   String groupId = resultObj.get("groupId").toString() != null ? resultObj.get("groupId").toString() : "";
			   String centerId = resultObj.get("centerId").toString() != null ? resultObj.get("centerId").toString() : null;
			   
			   
			   String mberSttus = "STATE_02";
			   String authorCode = "ROLE_USER_MEMBER";
			   int resultVal = 0;
			   if(password != null && !password.equals("")){
				   // 비밀번호는 문제 없음
				   
				   // 이쪽 작업 시작해야함, 받아온 값을 기준으로 insert 보내는 작업 시작
				   
				   GnrMberVO gnrMberVO = new GnrMberVO();
				   gnrMberVO.setMberId(mberId);
				   gnrMberVO.setPassword(password);
				   gnrMberVO.setMberNm(mberNm);
				   gnrMberVO.setGroupId(groupId);
				   gnrMberVO.setMberSttus(mberSttus);
				   gnrMberVO.setAuthorCode(authorCode);
				   if(centerId != null && centerId.equals("")){
					   gnrMberVO.setCenterId(centerId);
				   }
				   
				   resultVal = userManagerService.insertUserManage(gnrMberVO);
				   
				   if(resultVal > 0){
					   nowData = new JSONObject();
					   nowData.put("mberId", mberId);
					   nowData.put("mberNm", mberNm);
					   nowData.put("groupId", groupId);
					   jsonData.add(nowData);
				   }
				   
			   } else {
				   //비밀번호가 잘못 들어옴
				   
			   }
			   
			   nowData = new JSONObject();
			   nowData.put("req_type", request_type);
			   nowData.put("length", resultVal);
			   jsonRes.put("result", nowData);
			   jsonRes.put("data", jsonData);
			   
		   } else if (request_type.equals("join-idCheck")){
			   // 회원가입간 아이디 중복체크
			   String mberId = resultObj.get("mberId").toString() != null ? resultObj.get("mberId").toString() : "";
			   int ret = 0;
			   if(mberId != null && !mberId.equals("")){
				   int IDCheck = userManagerService.selectUserMangerIDCheck(mberId);
				   
				   nowData = new JSONObject();
				   nowData.put("mberId", mberId);
				   if(IDCheck == 0){
					   nowData.put("exist", "N");
				   } else {
					   nowData.put("exist", "Y");
				   }
				   jsonData.add(nowData);
				   ret = 1;
			   }
			   
			   nowData = new JSONObject();
			   nowData.put("req_type", request_type);
			   nowData.put("length", ret);
			   jsonRes.put("result", nowData);
			   jsonRes.put("data", jsonData);
			
				
		   } else if (request_type.equals("pwSearch-userInfo")){
			   // 유저정보 존재 확인
			   String mberId = resultObj.get("mberId").toString() != null ? resultObj.get("mberId").toString() : "";
			   /*String mberNm = resultObj.get("mberNm").toString() != null ? resultObj.get("mberNm").toString() : "";*/
			   String groupId = resultObj.get("groupId").toString() != null ? resultObj.get("groupId").toString() : "";
			   /*String centerId = resultObj.get("centerId").toString() != null ? resultObj.get("centerId").toString() : null;*/
			   
			   GnrMberVO gnrMberVO = new GnrMberVO();
			   gnrMberVO.setMberId(mberId);
			   /*gnrMberVO.setMberNm(mberNm);*/
			   gnrMberVO.setGroupId(groupId);
			   /*if(centerId != null && centerId.equals("")){
				   gnrMberVO.setCenterId(centerId);
			   }
			   */
			   
			   int exist = 0;
			   if(mberId != null && groupId != null && !mberId.equals("") && !groupId.equals("")){
				   exist = userManagerService.selectPwSearchUserInfo(gnrMberVO);
				   nowData = new JSONObject();
				   nowData.put("mberId", mberId);
				   if(exist == 0){
					   nowData.put("exist", "N");
				   } else {
					   nowData.put("exist", "Y");
				   }
				   jsonData.add(nowData);
			   }
			   
			   nowData = new JSONObject();
			   nowData.put("req_type", request_type);
			   nowData.put("length", exist);
			   jsonRes.put("result", nowData);
			   jsonRes.put("data", jsonData);
		   } else if (request_type.equals("pwSearch-change")){ /* 비밀번호 초기화 & 변경 */ 
			   String mberId = resultObj.get("mberId").toString() != null ? resultObj.get("mberId").toString() : "";
			   String groupId = resultObj.get("groupId").toString() != null ? resultObj.get("groupId").toString() : "";
			   String mPassword = resultObj.get("password").toString() != null ? resultObj.get("password").toString() : "";

			   GnrMberVO gnrMberVO = new GnrMberVO();
			   gnrMberVO.setMberId(mberId);
			   gnrMberVO.setGroupId(groupId);
			   gnrMberVO.setPassword(mPassword);
			   
			   int ret = 0;
			   if(mPassword != null && !mPassword.equals("")){
				   ret = userManagerService.updateUserPassword(gnrMberVO);
				   nowData = new JSONObject();
				   nowData.put("mberId", mberId);
				   if(ret == 0){
					   nowData.put("success", "N");
				   } else {
					   nowData.put("success", "Y");
				   }
				   jsonData.add(nowData);
			   }

			   nowData = new JSONObject();
			   nowData.put("req_type", request_type);
			   nowData.put("length", ret);
			   jsonRes.put("result", nowData);
			   jsonRes.put("data", jsonData);

			   
		   } else {
			   
		   }
		   
		   
		   result = jsonRes.toJSONString();
		   jsonRes.clear();
		   
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	   
	   return result;
	   
   }
   
   
   

	
}
