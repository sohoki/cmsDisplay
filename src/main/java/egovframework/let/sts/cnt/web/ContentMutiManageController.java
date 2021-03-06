package egovframework.let.sts.cnt.web;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.Globals;
import egovframework.let.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;
import egovframework.let.sts.cnt.service.ContentDetailFileInfoManageService;
import egovframework.let.sts.cnt.service.ContentDetailFileInfoVO;
import egovframework.let.sts.cnt.service.ContentFileInfoManageService;
import egovframework.let.sts.cnt.service.ContentFileInfoVO;
import egovframework.let.sts.cnt.service.ContentMutiInfo;
import egovframework.let.sts.cnt.service.ContentMutiInfoVO;
import egovframework.let.sts.cnt.service.ContentMutiInfoManageService;
import egovframework.let.sts.cnt.service.ContentDetailInfo;
import egovframework.let.sts.cnt.service.ContentDetailInfoService;


import egovframework.let.sym.ccm.cde.service.CmmnDetailCode;




import egovframework.let.sym.grp.service.GroupDidInfoManageService;
import egovframework.let.sym.grp.service.GroupDidInfoVO;
import egovframework.let.sym.sch.service.ContentSendHistoryInfo;
import egovframework.let.sym.sch.service.ContentSendHistoryInfoManagerService;
import egovframework.let.sym.sch.service.ScheduleInfoManageService;
import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
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
import egovframework.let.sym.sch.service.ScheduleInfo;



@Controller
public class ContentMutiManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContentMutiManageController.class);
	
	
	
	@Resource(name="ContentMuti")
	private ContentMutiInfoManageService contentMuti;
	
	@Resource(name="ContentFileInfo")
	private ContentFileInfoManageService conFileService;
	
	@Resource(name="CmmnDetailCodeManageService")
    private EgovCcmCmmnDetailCodeManageService cmmnDetailCodeManageService;
	
	@Resource(name="ContentDetailInfoService")
	private ContentDetailInfoService contentDetail;
	
	
	@Resource(name="ContentDetailFileInfo")
	private  ContentDetailFileInfoManageService conFileinfo;
	
	
	@Resource(name="egovMessageSource")
	protected EgovMessageSource egovMessageSource;
	
	
	@Resource(name="ScheduleInfoManageService")
	private ScheduleInfoManageService scheduleInfoManageService;
	
	@Resource(name="ContentSendHistoryInfoManagerService")
	private ContentSendHistoryInfoManagerService SendHistory;
	
	
	@Resource(name="GroupDidInfoManageService")
	private GroupDidInfoManageService didInfo;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;
	
	
	
	@RequestMapping(value="/backoffice/sub/conManage/conMutiList.do")
	public String selectContentMutilLst(@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("searchVO") ContentMutiInfoVO searchVO
			, HttpServletRequest request
			, BindingResult bindingResult			
			, ModelMap model) throws Exception {
		    
		
		   model.addAttribute("regist", searchVO);
		     
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

		       model.addAttribute("resultList",   contentMuti.selectContentMutiInfoManageListByPagination(searchVO) );
		       
		       int totCnt = contentMuti.selectContentMutiInfoManageListTotCnt_S(searchVO);       
			   paginationInfo.setTotalRecordCount(totCnt);
		       model.addAttribute("paginationInfo", paginationInfo);
		       model.addAttribute("totalCnt", totCnt);		       
		       model.addAttribute("regist", searchVO);		       
		      return "/backoffice/sub/conManage/conMutiList";
		
	}
	@RequestMapping(value="/backoffice/sub/conManage/conMutiView.do")
	public String selectConmultiView(@ModelAttribute("loginVO") LoginVO loginVO
			, ContentMutiInfoVO vo
			, HttpServletRequest request			
			, BindingResult bindingResult			
			, ModelMap model) throws Exception{		
		model.addAttribute("regist",  contentMuti.selectContentMutiInfoManageView(vo.getConSeq()) );
		
		
		return "/backoffice/sub/conManage/conMutiView";
	}
	@RequestMapping(value="/backoffice/sub/conManage/conMutiView_back.do")
	public String selectConmultiViewBack(@ModelAttribute("loginVO") LoginVO loginVO
			, ContentMutiInfoVO vo
			, HttpServletRequest request			
			, BindingResult bindingResult			
			, ModelMap model) throws Exception{		
		
        String conSeq = request.getParameter("conSeq") == null ? "" : request.getParameter("conSeq");
        if(conSeq.equals("")){
            model.addAttribute("regist", contentMuti.selectContentMutiInfoManageView(vo.getConSeq()));
        } else {
            model.addAttribute("regist", contentMuti.selectContentMutiInfoManageView(conSeq));
        }
        return "/backoffice/sub/conManage/conMutiView_back";
	}
	
	//콤보 박스 리스트 가지고 오기 
	@RequestMapping(value="/backoffice/sub/conManage/conMutiSelect.do")
	public ModelAndView selectComboView(HttpServletRequest request) throws Exception {
		
		String code = request.getParameter("code") != null ? request.getParameter("code") : "";
		String conSeq = request.getParameter("conSeq") != null ? request.getParameter("conSeq") : "";
		
		
		CmmnDetailCode cmDetail = new CmmnDetailCode();
		//이쪽 구문 정리 하기 
		cmDetail = cmmnDetailCodeManageService.selectCmmnDetail(code);		
		//페이지 카운터 
		String PageCnt = "";
		if(cmDetail.getCodeDc() == null || cmDetail.getCodeDc().equals("")){
			PageCnt = "1";
		} else {
			PageCnt = cmDetail.getCodeDc();
		}
		
		int pageConDetailCnt =  contentDetail.selectConDetailCnt(conSeq);
		
		System.out.println("PageCnt:" + PageCnt +"   | pageConDetailCnt:" + pageConDetailCnt);
		
		if ( Integer.parseInt(PageCnt) !=  pageConDetailCnt){
			createDetailTable(conSeq, PageCnt);			
		}
		ModelAndView model = new ModelAndView("jsonView");
		
		List<ContentDetailInfo> detailComboLst = contentDetail.selectConDetailCombo(conSeq);
		
		model.addObject("jobCombo", detailComboLst);
		
		
		return model;
	}
	
	private Boolean createDetailTable (String conSeq, String PageCnt) throws Exception {
		
		
		int result = contentDetail.deleteContentDetailConSeq(conSeq);
		//페이지 카운터 등록 
		int pageConDetailCnt =  contentDetail.selectConDetailCnt(conSeq);
		
		System.out.println("pageConDetailCnt:" + pageConDetailCnt);
		//테이블 생성 
		ContentDetailInfo vo = new ContentDetailInfo();
		
		for (int i =0; i <  Integer.parseInt( PageCnt); i++ ){
			System.out.println("setDetailOrder:" + i);
			vo.setConSeq(conSeq);
			vo.setDetailOrder( Integer.toString( i));
			contentDetail.insertContentDetailManage(vo);
		}
		
		return true;
	}
	@RequestMapping(value="/backoffice/sub/conManage/conMutiDetail.do")
	public String selectConmultiDetail(@ModelAttribute("loginVO") LoginVO loginVO
			, ContentMutiInfoVO vo
			, HttpServletRequest request			
			, BindingResult bindingResult			
			, ModelMap model) throws Exception{
	
		   model.addAttribute("selectConType", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT001"));
           model.addAttribute("selectScreenType", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT010"));
           
           model.addAttribute("selectPlayType", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT015"));
           model.addAttribute("selectUrlType", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT017"));
           //신규
           
           try{
           model.addAttribute("selectNextSeq", contentMuti.selectNextSeqList(vo.getConSeq()));
           }catch(Exception e1){
		    	System.out.println("error:" + e1.toString());
		    }
		   		   
		   if (!vo.getMode().equals("Ins")){
			    try{
			    vo = contentMuti.selectContentMutiInfoManageDetail(vo.getConSeq());
			    vo.setMode(vo.getMode());
			    vo.setConSeq(vo.getConSeq());			    
			    }catch(Exception e){
			    	System.out.println("error:" + e.toString());
			    }
		   }else {			
			   vo.setConWidth("1080");
			   vo.setConHeight("1980");
			   vo.setConMid("540");		
			   vo.setConTime("0");
		   }		   
		   model.addAttribute("regist",  vo );		   
		   return "backoffice/sub/conManage/conMutiDetail";
		
	}
	//파일 업로드 파일 올리기
	@RequestMapping (value="/backoffice/sub/conManage/fileIupload.do")
	public String fileUplad(@ModelAttribute("loginVO") LoginVO loginVO			
			, HttpServletRequest request
			, BindingResult bindingResult			
			, ModelMap model) throws Exception{		
		return "/backoffice/sub/conManage/FileUpload";		
	}
	
	@RequestMapping (value="/backoffice/sub/conManage/conPage01.do")
	public String filePage01(@ModelAttribute("loginVO") LoginVO loginVO
			, ContentDetailInfo vo
			, HttpServletRequest request
			, BindingResult bindingResult			
			, ModelMap model) throws Exception{
	   
	   
	   model.addAttribute("selectConType", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT008"));	   
	   model.addAttribute("regist", vo);
	   
	   
	   
	   
	   int cnt =  contentDetail.selectPageSeqCheckPage01Cnt(vo.getConSeq());
	   
	   
	    if (  cnt == 0 ){	    	 		    		    		    	
	    	try{
		    	int ret = contentDetail.insertContentDetailManage(vo);
		    	if (ret > 0){
		    	vo.setDetailSeq( Integer.toString(contentDetail.selectMaxDetail() ) );
		    	vo = contentDetail.selectContentDetail(vo.getDetailSeq()) ;		 
		    	vo.setMode("Edt");
		    	}else {
		    		System.out.println("에러");
		    	}
	    	}catch (Exception e){
	    		System.out.println("에러:" + e.toString());
	    	}
	    }else {
	    	vo.setDetailSeq(  Integer.toString(  contentDetail.selectPageSeqCheckPage01(vo.getConSeq())    ));	
	    }
	    
	    vo = contentDetail.selectContentDetail(vo.getDetailSeq()) ;	    
	    vo.setPageGubun("Page01");
    	model.addAttribute("regist",vo);    
		return "/backoffice/sub/conManage/conPage01";		
	}
	@RequestMapping (value="/backoffice/sub/conManage/conPage02.do")
	public String filePage02(@ModelAttribute("loginVO") LoginVO loginVO
			, ContentDetailInfo vo
			, HttpServletRequest request
			, BindingResult bindingResult			
			, ModelMap model) throws Exception{
		
		   model.addAttribute("selectConType", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT008"));	   
		   model.addAttribute("regist", vo);
		   
		   int cnt =  contentDetail.selectPageSeqCheckPage02Cnt(vo.getConSeq());
		   		   
		   if (  cnt == 0 ){	    	 		    		    		    	
		    	try{
			    	int ret = contentDetail.insertContentDetailManage(vo);
			    	if (ret > 0){
			    	vo.setDetailSeq( Integer.toString(contentDetail.selectMaxDetail() ) );
			    	vo = contentDetail.selectContentDetail(vo.getDetailSeq()) ;		 
			    	vo.setMode("Edt");
			    	}else {
			    		System.out.println("에러");
			    	}
		    	}catch (Exception e){
		    		System.out.println("에러:" + e.toString());
		    	}
		    }else {
		    	vo.setDetailSeq(  Integer.toString(  contentDetail.selectPageSeqCheckPage02(vo.getConSeq())    ));	
		    }		    
		    vo = contentDetail.selectContentDetail(vo.getDetailSeq()) ;	    
		    vo.setPageGubun("Page02");
	        model.addAttribute("regist",vo);
	    
	    
		   return "/backoffice/sub/conManage/conPage02";
		
	}	
	
	
	
	@RequestMapping (value="/backoffice/sub/conManage/conPageUpdate.do")
	public String filePageUpdate (@ModelAttribute("loginVO") LoginVO loginVO
			, ContentDetailInfo vo
			, HttpServletRequest request
			, BindingResult bindingResult			
			, ModelMap model) throws Exception{
		
		model.addAttribute("regist", vo);
		String meesage = "";
		
		String url = "";
		
	  try{					  
		    //페이지 순서
		    if ( vo.getPageGubun().equals("Page01")){              
		    	 vo.setDetailOrder("1");
		    }else {
		    	vo.setDetailOrder("2");
		    }
		 
		    int ret  = contentDetail.updateContentDetailManage(vo);
		    
		    
					
			if (ret >0){
				
				    int deCheck = conFileService.selectFileContentTotCnt_S(vo.getDetailSeq());				    
				    if (deCheck > 0){
					   vo.setConRemark(conTentPage(vo.getDetailSeq(), vo.getConSeq())   );
				    }
				    				 
					ret  = contentDetail.updateContentDetailManage(vo);
					meesage = "sucess.common.update";
					
					if ( vo.getPageGubun().equals("Page01")  ){
					     url = "forward:/backoffice/sub/conManage/conPage01.do";
					}else {
						 url = "forward:/backoffice/sub/conManage/conPage02.do";
				   }
				
				   model.addAttribute("status", Globals.STATUS_SUCCESS);
				   model.addAttribute("message", egovMessageSource.getMessage(meesage));						
			}else {
				throw new Exception();
			}
			
		}catch (Exception e){
			model.addAttribute("status", Globals.STATUS_FAIL);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.insert"));			
			if ( vo.getPageGubun().equals("Page01")  ){
			     url = "forward:/backoffice/sub/conManage/conPage01.do";
			}else {
				 url = "forward:/backoffice/sub/conManage/conPage02.do";
			}
		}finally {
			
		}					
		return url;
		
	}
	
	
	@RequestMapping (value="/backoffice/sub/conManage/conMutiDel.do")
	public String selectConDelete (@ModelAttribute("loginVO") LoginVO loginVO
			, ContentMutiInfoVO vo
			, HttpServletRequest request
			, BindingResult bindingResult			
			, ModelMap model) throws Exception{
		
            model.addAttribute("regist",vo );
		
			if (StringUtils.equals(vo.getConSeq(), Globals.REGITER_SYSTEM)){
				model.addAttribute("status", Globals.STATUS_FAIL);
				model.addAttribute("message", egovMessageSource.getMessage("fail.common.delete.system"));			
				return "forward:/backoffice/sub/conManage/conMutiList.do";
			}
					
			try{
			      int ret = 	contentMuti.deleteContentMutiInfoManage(vo.getConSeq());		      
			      if (ret > 0 ) {		    	  
			    	  model.addAttribute("status", Globals.STATUS_SUCCESS);
			    	  model.addAttribute("message", egovMessageSource.getMessage("success.common.delete") );
			    	  
			    	   // 파일도 삭제			    				    	  
			    	   ret  = conFileService.deleteFileConSeq(vo.getConSeq());			    	   
			    	   if (ret > 0){
			    		   LOGGER.info("정상 파일 삭제 ") ;
			    	   }else {
			    		   LOGGER.debug("정상 파일 애러 ") ;
			    	   }			    	   
			    	   ret = contentDetail.deleteContentDetailConSeq(vo.getConSeq());
			    	   if (ret > 0){
			    		   LOGGER.info("정상 페이지 삭제 ") ;
			    	   }else {
			    		   LOGGER.debug("정상 페이지 애러 ") ;
			    	   }			    	    
			    	  
			      }else {
			    	  throw new Exception();		    	  
			      }
			}catch (Exception e){
				model.addAttribute("status", Globals.STATUS_FAIL);
				model.addAttribute("message", egovMessageSource.getMessage("fail.common.delete"));			
			}				
			return "forward:/backoffice/sub/conManage/conMutiList.do";
	}
	
	
	
	@RequestMapping(value="/backoffice/sub/conManage/conMutiUpdate.do")
	public String selectConMutiUpdate( HttpServletRequest request
															, @ModelAttribute("loginVO") LoginVO loginVO
						                                    , @ModelAttribute("ContentMutiInfoVO") ContentMutiInfoVO vo 
															, BindingResult result
															, ModelMap model)throws Exception{
		model.addAttribute("regist", vo);
		String meesage = "";
		String url = "redirect:/backoffice/sub/conManage/conMutiList.do";
			     
		
		try{			
			
			int ret  = 0;
			
			if (vo.getConNextSeq().equals("")){ vo.setConNextSeq("0");}
			
			
			if (vo.getMode().equals("Ins")){
				
				ret = contentMuti.insertContentMutiInfoManage(vo);
                meesage = "sucess.common.insert";
                url = "redirect:/backoffice/sub/conManage/conMutiList.do";
                if(ret > 0){
                    String maxSeq = contentMuti.selectMaxSeqInfo();
                    if(!maxSeq.equals("-1") && !maxSeq.equals("-2")){
                    	url = "redirect:/backoffice/sub/conManage/conMutiView_back.do?conSeq="+maxSeq;
                    }
                }
							
			}else {
				 ret = contentMuti.updateContentMutiInfoManage(vo);
				 meesage = "sucess.common.update";
				 url = "forward:/backoffice/sub/conManage/conMutiView_back.do";
			}			
			if (ret >0){
				model.addAttribute("status", Globals.STATUS_SUCCESS);
				model.addAttribute("message", egovMessageSource.getMessage(meesage));
						
			}else {
				throw new Exception();
			}
			
		}catch (Exception e){
			LOGGER.debug(e.toString());
			model.addAttribute("status", Globals.STATUS_FAIL);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.insert"));			
			url = "redirect:/backoffice/sub/conManage/conMutiList.do";
		}finally {
			
		}							
		return url;
	}
	//테스트 문장 
	/*@RequestMapping(value="/backoffice/sub/conManage/contentJsonTest.do")
	@ResponseBody 
	public String selectContentTestPreview(HttpServletRequest request) throws Exception{
		
		String jsonTest = "";
		String detailSeq = request.getParameter("detailSeq") != null ? request.getParameter("detailSeq") : "";
		String conSeq = request.getParameter("conSeq") != null ? request.getParameter("conSeq") : "";		
		try{			
			jsonTest = conPageDetail(conSeq);
			boolean fileCheck = ContentFileCreate(jsonTest, conSeq);
			
		}catch(Exception e){
			
		}		
		return jsonTest;
	}*/
	
	@RequestMapping(value="/backoffice/sub/conManage/contentPreview.do")
	@ResponseBody 
	public String selectContentPreview(HttpServletRequest request) throws Exception{
		
		
		String jsonTest = "";
		String jsonTestLocal = "";		
		String conSeq = request.getParameter("conSeq") != null ? request.getParameter("conSeq") : "";		
		try{			
			jsonTest = conPageDetail(conSeq , "S");
			boolean fileCheck = ContentFileCreate(jsonTest, conSeq);
			if (fileCheck == true){
				LOGGER.debug("fileCheck:true" );
				jsonTestLocal = conPageDetail(conSeq , "L");
				
				
				ContentFileCreateLocal(jsonTestLocal, conSeq);
			}			
		}catch(Exception e){
			LOGGER.debug("selectContentPreview error" + e.toString());
		}		
		return jsonTest;
	}
	@RequestMapping(value="/backoffice/sub/conManage/contentScheduleSend.do")
	@ResponseBody 
	public String selectContentScheduleSend(HttpServletRequest request) throws Exception{
		
		
		String conSeq = request.getParameter("conSeq") != null ? request.getParameter("conSeq") : "";
		
		try{
			List<ScheduleInfo> schedule = scheduleInfoManageService.selectScheduleConSeqList(conSeq);
			
			for (int i = 0; i < schedule.size(); i++){


                 int ret_result =0;			   	
				 ret_result = SendHistory.deleteContentSendHistoryInfoManage(schedule.get(i).getSchCode());
				 ContentSendHistoryInfo sendHistory = new ContentSendHistoryInfo();
				 List<GroupDidInfoVO> resultLst = didInfo.selectGroupInfoManageListByPagination(schedule.get(i).getGroupCode());
					
					//단말기 전송할 구문 넣기 
				 for (int a = 0 ; a < resultLst.size() ; a++    )
				 {
						sendHistory.setDidId(resultLst.get(a).getDidId().toString() );
						sendHistory.setHisSeq("");
						sendHistory.setSchCode(schedule.get(i).getSchCode());
						ret_result = SendHistory.insertContentSendHistoryInfoManage(sendHistory);						
						if (  ret_result == 0 ){
							LOGGER.debug("단말단 개별 인서트 애러");
						}					
				 }				
			}
			return "T";
		}catch(Exception e){
			LOGGER.debug("selectContentScheduleSend error:" + e.toString());
			return "F";
		}
		
	}
	
	// 전체 페이지 구성, 웹내페이지
	public String MainPageView( String conSeq ){
		StringBuilder htmlPage = new StringBuilder();
		StringBuilder htmlPageFile = new StringBuilder();
		
		ContentMutiInfoVO vo = new ContentMutiInfoVO();
		
		try {
			vo = contentMuti.selectContentMutiInfoManageView(conSeq);
			
			String width  = vo.getConWidth();
			String height = vo.getConHeight();
			String mid = vo.getConMid();
		    
			List<ContentDetailInfo> voLst = contentDetail.selectContentDetailLst(conSeq);
			
			
			htmlPage.append("<!DOCTYPE HTML>   \r\n ");
			htmlPage.append("<html>   \r\n");  
			htmlPage.append("<head>  \r\n ");    
			htmlPage.append("<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>\r\n");
			htmlPage.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>\r\n"); 
			htmlPage.append("<title>" + vo.getConNm() + "</title>   \r\n");    
			htmlPage.append("<script type='text/javascript' src='/js/jquery.min.js'></script>   \r\n");
			if (!vo.getConNextSeq().equals("0")  && !vo.getConFile().equals("N") ){
				htmlPage.append("<script type='text/javascript'>");
				htmlPage.append(" setTimeout function { location.href='"+  contentMuti.selectContentFileInfo(vo.getConNextSeq()) +"'; }, " +  vo.getConTime() + "*1000 );");
				htmlPage.append("</script>");
			}			
			htmlPage.append("<style>");
			htmlPage.append("html,body{ min-height:100%; margin:0; padding:0; background: #fff;}   \r\n");
			htmlPage.append("</style>   \r\n");
			htmlPage.append("</head>    \r\n");						
			htmlPage.append("<body>   \r\n");
			
			if (vo.getConScreen().equals("H21") ){
				for (int i=0; i < voLst.size(); i++){
					if (i==0){
						htmlPage.append("<div id='page"+  Integer.toString(i) +"' style='width:"+width+";height:"+ mid +"'>   \r\n");
						htmlPage.append(" " + conTentPage(voLst.get(i).getDetailSeq(), conSeq  ) + " \r\n");						
					}else {
						htmlPage.append("<div id='page"+Integer.toString(i)+"' style='width:"+width+";height:"+ Integer.toString(Integer.parseInt(height) - Integer.parseInt(mid) + 1)  +"'>   \r\n");
						htmlPage.append(" " + conTentPage(voLst.get(i).getDetailSeq(), conSeq  ) + " \r\n");					
					}
					htmlPage.append("</div>      \r\n");
				}				
			}else if (vo.getConScreen().equals("W21") ) {
				for (int i=0; i < voLst.size(); i++){
					if (i==0){
						htmlPage.append("<div id='page"+  Integer.toString(i) +"' style='width:"+mid+";height:"+ height +"'>   \r\n");
						htmlPage.append(" " + conTentPage(voLst.get(i).getDetailSeq(), conSeq  ) + " \r\n");						
					}else {
						htmlPage.append("<div id='page"+Integer.toString(i)+"' style='width:"+Integer.toString(Integer.parseInt(width) - Integer.parseInt(mid) + 1)+";height:"+height+"'>   \r\n");
						htmlPage.append(" " + conTentPage(voLst.get(i).getDetailSeq(), conSeq  ) + " \r\n");					
					}
					htmlPage.append("</div>      \r\n");
				}							
			}else {
				htmlPage.append("<div style='width:"+width+";height:"+height+"'>   \r\n");
				htmlPage.append(" " + conTentPage(voLst.get(0).getDetailSeq(), conSeq  ) + " \r\n");		
				htmlPage.append("/div>      \r\n");
			}
			htmlPage.append("</body>     \r\n");
			htmlPage.append("</html>      \r\n");
			
			//----------------------------------------------------------------- 파일 생성 구문 
			
			htmlPageFile.append("<!DOCTYPE HTML>\r\n ");
			htmlPageFile.append("<html>\r\n");  
			htmlPageFile.append("<head>\r\n ");    
			htmlPageFile.append("<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>\r\n");
			htmlPage.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>\r\n");  
			htmlPageFile.append("<title>" + vo.getConNm() + "</title>\r\n");    
			htmlPageFile.append("<script type='text/javascript' src='/js/jquery.min.js'></script>\r\n");
			if (!vo.getConNextSeq().equals("0")  && !vo.getConFile().equals("N") ){
				htmlPageFile.append("<script type='text/javascript'>\r\n");
				htmlPageFile.append(" setTimeout function { location.href='./"+  contentMuti.selectContentFileInfo(vo.getConNextSeq()) +"'; }, " +  vo.getConTime() + "*1000 );\r\n");
				htmlPageFile.append("</script>\r\n");
			}			
			htmlPageFile.append("<style>");
			htmlPageFile.append("html,body{ min-height:100%; margin:0; padding:0; background: #fff;}\r\n");
			htmlPageFile.append("</style>\r\n");
			htmlPageFile.append("</head>\r\n");						
			htmlPageFile.append("<body>\r\n");
			
			if (vo.getConScreen().equals("H21") ){
				for (int i=0; i < voLst.size(); i++){
					if (i==0){
						htmlPageFile.append("<div id='page"+  Integer.toString(i) +"' style='width:"+width+";height:"+ mid +"'>   \r\n");
						htmlPageFile.append(" " + conTentPageFile(voLst.get(i).getDetailSeq(), conSeq  ) + " \r\n");						
					}else {
						htmlPageFile.append("<div id='page"+Integer.toString(i)+"' style='width:"+width+";height:"+ Integer.toString(Integer.parseInt(height) - Integer.parseInt(mid) + 1)  +"'>   \r\n");
						htmlPageFile.append(" " + conTentPageFile(voLst.get(i).getDetailSeq(), conSeq  ) + " \r\n");					
					}
					htmlPageFile.append("</div>      \r\n");
				}				
			}else if (vo.getConScreen().equals("W21") ) {
				for (int i=0; i < voLst.size(); i++){
					if (i==0){
						htmlPageFile.append("<div id='page"+  Integer.toString(i) +"' style='width:"+mid+";height:"+ height +"'>   \r\n");
						htmlPageFile.append(" " + conTentPageFile(voLst.get(i).getDetailSeq(), conSeq  ) + " \r\n");						
					}else {
						htmlPageFile.append("<div id='page"+Integer.toString(i)+"' style='width:"+Integer.toString(Integer.parseInt(width) - Integer.parseInt(mid) + 1)+";height:"+height+"'>   \r\n");
						htmlPageFile.append(" " + conTentPageFile(voLst.get(i).getDetailSeq(), conSeq  ) + " \r\n");					
					}
					htmlPageFile.append("</div>      \r\n");
				}							
			}else {
				htmlPageFile.append("<div style='width:"+width+";height:"+height+"'>   \r\n");
				htmlPageFile.append(" " + conTentPageFile(voLst.get(0).getDetailSeq(), conSeq  ) + " \r\n");		
				htmlPageFile.append("/div>      \r\n");
			}
			htmlPageFile.append("</body>     \r\n");
			htmlPageFile.append("</html>      \r\n");

			boolean fileCheck = ContentFileCreate(htmlPageFile.toString(), conSeq);
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			htmlPage.append(" Error Page:"+ e.toString()+"");
			e.printStackTrace();
		} 
		
		
		
		return htmlPage.toString();
	}
	
	// 로컬페이지
	public boolean ContentFileCreateLocal (String htmlFile, String conSeq){
		boolean htmlCreate = false;
		try{
			
			
			LOGGER.debug("htmlFile:"+htmlFile );
						
			String fileName = "";
			fileName =	 contentMuti.selectContentFileInfoLocal(conSeq);
			String filePath = propertiesService.getString("Globals.fileStorePath") ;
			File  htm_file = null;
			 	 
					 
			if (  !fileName.equals("N") ){							
					htm_file = new File (filePath +"/" + fileName);
					if (htm_file.isFile()){
						htm_file.delete();
					}							
			}else {
				
				Date date = new Date();
				LOGGER.debug("conSeq pre:"+conSeq);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				LOGGER.debug("sdf:"+sdf.toString());
				fileName = sdf.format(date)+"_"+conSeq+"_local.html";
				LOGGER.debug("fileName Pre:"+fileName);
			}
						 
			  BufferedWriter fw = new BufferedWriter(new FileWriter(filePath +"/"+fileName, true));
			  fw.write(htmlFile);
	          fw.flush();
	          fw.close(); 
	          ContentMutiInfo vo = new ContentMutiInfo();
	          vo.setConSeq(conSeq);
	          LOGGER.debug("fileName:"+fileName);
	          vo.setConLocalfile(fileName);
			  int ret =      contentMuti.updateContentMutiFileLocal(vo);
			 if (ret > 0){
				 htmlCreate = true;
			 }else {
				 htmlCreate = false;
			 }
		}catch(Exception e){
			LOGGER.debug("CREATE HTML FILE ERROR:" + e.toString()  );
			htmlCreate = false;					
		}
		return htmlCreate;
	}
	
	public boolean ContentFileCreate (String htmlFile, String conSeq){
		boolean htmlCreate = false;
		try{
			 String fileName = "";
			 fileName =	 contentMuti.selectContentFileInfo(conSeq);
			 String filePath = propertiesService.getString("Globals.fileStorePath") ;
			 File  htm_file = null;
			 	 
					 
			  if (  !fileName.equals("N") ){							
					htm_file = new File (filePath +"/" + fileName);
					if (htm_file.isFile()){
						htm_file.delete();
					}							
			  }else {
				
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				fileName = sdf.format(date)+"_"+conSeq+".html";
				
			  }
						 
			  BufferedWriter fw = new BufferedWriter(new FileWriter(filePath +"/"+fileName, true));
			  fw.write(htmlFile);
	          fw.flush();
	          fw.close(); 
	          ContentMutiInfo vo = new ContentMutiInfo();
	          vo.setConSeq(conSeq);
	          vo.setConFile(fileName);
			  int ret =      contentMuti.updateContentMutiFile(vo);
			 if (ret > 0){
				 htmlCreate = true;
			 }else {
				 htmlCreate = false;
			 }
		}catch(Exception e){
			LOGGER.debug("CREATE HTML FILE ERROR:" + e.toString()  );
			htmlCreate = false;					
		}
		return htmlCreate;
	}
	
	//신규 webView 
	public String conPageDetail(String conSeq , String playGubun)
	{
		
		String pageInfo = null;
		
		ContentMutiInfoVO vo_info = new ContentMutiInfoVO();	
		StringBuilder htmlPage = new StringBuilder();
		
		try{
		
			vo_info = contentMuti.selectContentMutiInfoManageView(conSeq);
			//기초 정보 			
			String width  = vo_info.getConWidth();
			String height = vo_info.getConHeight();
			String mid = vo_info.getConMid();
			String conDc = vo_info.getConDc();
			/**
			 * viewType ? 화면구성 방식
			 * 0 : 전체화면
			 * 1 : 가로 분할 화면
			 * 2 : 세로 분할 화면
			 * */
			int viewType = 0;
			
			
			if(conDc == null || conDc.equals("") || conDc.equals("0")){
				conDc = "1";
			}
			
			String top0 = "";
			String top1 = "";
			
			String height0 = "";
			String height1 = "";
			
			String width0 = "";
			String width1 = "";
			
			String left0 = "";
			String left1 = "";
			LOGGER.info("width : " + width + ", height : " + height + ", conDc : " + conDc);

			//변수값 설정 			
			if ( Integer.parseInt( width ) >  Integer.parseInt( height )  && Integer.parseInt( conDc ) > 1   ){
				viewType = 1;
				width0 = mid;
				width1 = Integer.toString((Integer.parseInt(width) -  Integer.parseInt( mid)));
				height0= height;
				height1= height;
				top0 = "0";
				top1 = "0";
				left0 = "0";
				left1 =  Integer.toString(Integer.parseInt(mid) + 1);				
			}else if ( Integer.parseInt( width ) <  Integer.parseInt( height )  && Integer.parseInt( conDc ) > 1   ){
				viewType = 2;
				width0= width;
				width1= width;
				height0 = mid;
				height1 = Integer.toString((Integer.parseInt(height) -  Integer.parseInt( mid)));				
				top0 = "0";
				top1 = Integer.toString(Integer.parseInt(mid) + 1);	
				left0 = "0";
				left1 = "0";
			}else {
				viewType = 0;
				width0= width;
				height0= height;
				width1= width;
				height1= height;
				top0 = "0";
				top1 = "0";
				left0 = "0";
				left1 = "0";
			}
			
			/**
			 * 콘텐츠 화면 생성 부 !
			 * 2019-01-11 전체소스 수정
			 * */
		    htmlPage.append("<!DOCTYPE HTML>\r\n");
			htmlPage.append("<html>\r\n");  
			htmlPage.append("<head>\r\n");    
			htmlPage.append("<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>\r\n");   
			htmlPage.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>\r\n");
			htmlPage.append("<title>" + vo_info.getConNm() + "</title>\r\n");    
			if (playGubun.equals("L")){
				htmlPage.append("<script type='text/javascript' src='./jquery-2.2.4.min.js'></script>\r\n");	
			}else {
				htmlPage.append("<script type='text/javascript' src='/js/jquery-2.2.4.min.js'></script>\r\n");
			}
			
			
			List<ContentDetailInfo> detailInfo =  contentDetail.selectContentDetailLst(conSeq);
			
			htmlPage.append("<script type='text/javascript'>\r\n");
			boolean musicPOPChk = false;
			String mediaType = "";
			for (int i = 0 ; i < detailInfo.size() ; i++ ){
				
				ContentDetailFileInfoVO searchVO = new ContentDetailFileInfoVO();
				searchVO.setConSeq(conSeq);
				searchVO.setDetailSeq(detailInfo.get(i).getDetailSeq());				
				List<ContentDetailFileInfoVO>  detailFileContent = conFileinfo.selectContentDetailFileLst(searchVO);
				JSONArray jsonA = JSONArray.fromObject(detailFileContent);			
				
				if(i == 0){
					System.out.println(detailFileContent.get(0).getAtchFileId());
					System.out.println(detailFileContent.get(0).getMediaType());
					mediaType= detailFileContent.get(0).getMediaType();
					if(mediaType == null){
						musicPOPChk = true;
					} else {
						if(!mediaType.equals("IMAGE") && !mediaType.equals("MEDIA")){
							musicPOPChk = true;
						}else{
							musicPOPChk = false;
						}
					}
				}
				htmlPage.append("    var albumLst"+i+" = '"+ jsonA  +"';\r\n");				
				htmlPage.append("    var contentCount"+i+" = 0;\r\n");
				htmlPage.append("    var jsonData"+i+";\r\n");				
				htmlPage.append("    var firstPlay"+i+", errorFlag"+i+";\r\n");
				htmlPage.append("    var prepareFileNm"+i+", prepareFileType"+i+", prepareFileTime"+i+", prepareFileStreCours"+i+", prepareMakeType"+i+";\r\n");
			}
			// onRead 
			htmlPage.append("    $(document).ready(function(){\r\n");
			htmlPage.append("        console.log('contents play start');\r\n");
			htmlPage.append("        function startContentSch(){\r\n");
			for (int i = 0 ; i < detailInfo.size() ; i++ ){
				htmlPage.append("            jsonData"+i+" = JSON.parse(albumLst"+i+");\r\n");
				htmlPage.append("            if(jsonData"+i+".length > 0){\r\n ");
				htmlPage.append("                firstPlay"+i+" = true;\r\n ");
				htmlPage.append("                tagMaking"+i+"(jsonData"+i+"[contentCount"+i+"].streFileNm, jsonData"+i+"[contentCount"+i+"].mediaType, jsonData"+i+"[contentCount"+i+"].timeInterval, jsonData"+i+"[contentCount"+i+"].fileStreCours, 'A');\r\n");
				htmlPage.append("            }\r\n");
			}
			htmlPage.append("        }\r\n");
			htmlPage.append("        startContentSch();\r\n");
			htmlPage.append("    });\r\n");
			for (int i = 0 ; i < detailInfo.size() ; i++ ){
				htmlPage.append("    function tagMaking"+i+"(fileNm, fileType, fileInterval, fileStreCours, makeType){\r\n");
				htmlPage.append("        var result, mediaTag;\r\n");
				
				if (playGubun.equals("L")){ // local file myHandler 생성부
					htmlPage.append("        if (makeType == 'A'){\r\n");
					htmlPage.append("            $('#content_show"+i+"a').html('');\r\n");
					htmlPage.append("            switch(fileType){\r\n");
					if (i==0){
						htmlPage.append("                case 'IMAGE' : result = '<img id=\"content"+i+"a\" src=\"./'+fileNm+'\" width=\""+width0+"\" height=\""+height0+"\" />'; break;\r\n");	
					} else {
						htmlPage.append("                case 'IMAGE' : result = '<img id=\"content"+i+"a\" src=\"./'+fileNm+'\" width=\""+width1+"\" height=\""+height1+"\" />'; break;\r\n");
					}
					htmlPage.append("                case 'MEDIA' : result = '<video id=\"content"+i+"a\" src=\"./'+fileNm+'\"><source type=\"video/mp4\"/></video>'; break;\r\n");
					htmlPage.append("                default      : result = '<audio id=\"content"+i+"a\" controls src=\"./'+fileNm+'\"><source type=\"audio/mpeg\"/></audio>'; break;\r\n");
					htmlPage.append("            }\r\n");
					htmlPage.append("            mediaTag = $('#content"+i+"a');\r\n");
					htmlPage.append("            $('#content_show"+i+"a').html(result);\r\n");
				    htmlPage.append("        } else {\r\n");
					htmlPage.append("            $('#content_show"+i+"b').html('');\r\n");
					htmlPage.append("            switch(fileType){\r\n");
					if (i==0){
						htmlPage.append("                case 'IMAGE' : result = '<img id=\"content"+i+"b\" src=\"./'+fileNm+'\" width=\""+width0+"\" height=\""+height0+"\" />'; break;\r\n");	
					} else {
						htmlPage.append("                case 'IMAGE' : result = '<img id=\"content"+i+"b\" src=\"./'+fileNm+'\" width=\""+width1+"\" height=\""+height1+"\" />'; break;\r\n");
					}
					htmlPage.append("                case 'MEDIA' : result = '<video id=\"content"+i+"b\" src=\"./'+fileNm+'\"><source type=\"video/mp4\"/></video>'; break;\r\n");
					htmlPage.append("                default      : result = '<audio id=\"content"+i+"b\" controls src=\"./'+fileNm+'\"><source type=\"audio/mpeg\"/></audio>'; break;\r\n");
					htmlPage.append("            }\r\n");
					htmlPage.append("            mediaTag = $('#content"+i+"b');\r\n");
					htmlPage.append("            $('#content_show"+i+"b').html(result);\r\n");
				    htmlPage.append("        }\r\n");
				}else {
					// 화면생성, 미리보기 생성부
					htmlPage.append("        if (makeType == 'A'){\r\n");
					htmlPage.append("            $('#content_show"+i+"a').html('');\r\n");
					htmlPage.append("            switch(fileType){\r\n");
					if (i==0){
						htmlPage.append("                case 'IMAGE' : result = '<img id=\"content"+i+"a\" src=\"'+fileStreCours+fileNm+'\" width=\""+width0+"\" height=\""+height0+"\" />'; break;\r\n");	
					} else {
						htmlPage.append("                case 'IMAGE' : result = '<img id=\"content"+i+"a\" src=\"'+fileStreCours+fileNm+'\" width=\""+width1+"\" height=\""+height1+"\" />'; break;\r\n");
					}
					htmlPage.append("                case 'MEDIA' : result = '<video id=\"content"+i+"a\" src=\"'+fileStreCours+fileNm+'\"><source type=\"video/mp4\"/></video>'; break;\r\n");
					htmlPage.append("                default      : result = '<audio id=\"content"+i+"a\" controls src=\"'+fileStreCours+fileNm+'\"><source type=\"audio/mpeg\"/></audio>'; break;\r\n");
					htmlPage.append("            }\r\n");
					htmlPage.append("            mediaTag = $('#content"+i+"a');\r\n");
					htmlPage.append("            $('#content_show"+i+"a').html(result);\r\n");
				    htmlPage.append("        } else {\r\n");
					htmlPage.append("            $('#content_show"+i+"b').html('');\r\n");
					htmlPage.append("            switch(fileType){\r\n");
					if (i==0){
						htmlPage.append("                case 'IMAGE' : result = '<img id=\"content"+i+"b\" src=\"'+fileStreCours+fileNm+'\" width=\""+width0+"\" height=\""+height0+"\" />'; break;\r\n");	
					} else {
						htmlPage.append("                case 'IMAGE' : result = '<img id=\"content"+i+"b\" src=\"'+fileStreCours+fileNm+'\" width=\""+width1+"\" height=\""+height1+"\" />'; break;\r\n");
					}
					htmlPage.append("                case 'MEDIA' : result = '<video id=\"content"+i+"b\" src=\"'+fileStreCours+fileNm+'\"><source type=\"video/mp4\"/></video>'; break;\r\n");
					htmlPage.append("                default      : result = '<audio id=\"content"+i+"b\" controls src=\"'+fileStreCours+fileNm+'\"><source type=\"audio/mpeg\"/></audio>'; break;\r\n");
					htmlPage.append("            }\r\n");
					htmlPage.append("            mediaTag = $('#content"+i+"b');\r\n");
					htmlPage.append("            $('#content_show"+i+"b').html(result);\r\n");
				    htmlPage.append("        }\r\n");
				}
				htmlPage.append("        if(firstPlay"+i+"){\r\n");
			    htmlPage.append("            firstPlay"+i+" = false;\r\n");
			    htmlPage.append("            mediaPlaySetting"+i+"(fileNm, fileType, fileInterval, fileStreCours, makeType);\r\n");
			    htmlPage.append("        } else if (errorFlag"+i+"){\r\n");
			    htmlPage.append("            errorFlag"+i+" = false;\r\n");
			    htmlPage.append("            mediaPlaySetting"+i+"(fileNm, fileType, fileInterval, fileStreCours, makeType);\r\n");
			    htmlPage.append("        } else {\r\n");
			    htmlPage.append("            prepareFileNm"+i+" = fileNm;\r\n");
			    htmlPage.append("            prepareFileType"+i+" = fileType;\r\n");
			    htmlPage.append("            prepareFileTime"+i+" = fileInterval;\r\n");
			    htmlPage.append("            prepareFileStreCours"+i+" = fileStreCours;\r\n");
			    htmlPage.append("            prepareMakeType"+i+" = makeType;\r\n");
			    htmlPage.append("        }\r\n");
				htmlPage.append("    }\r\n");			
			}
			for (int i = 0 ; i < detailInfo.size() ; i++ ){
				htmlPage.append("    function mediaPlaySetting"+i+"(fileNm, fileType, fileInterval, fileStreCours, makeType){\r\n");
				htmlPage.append("        var mediaTag;\r\n");
				htmlPage.append("        var contentLoadCheck = setTimeout(function(){\r\n");
				htmlPage.append("            console.log('CONTENTS LOADING ISSUE, NEXT !');\r\n");
				htmlPage.append("            nextSeqConChk"+i+"('1', makeTypeRevers(makeType));\r\n");
				htmlPage.append("        }, 3000);\r\n");
				htmlPage.append("        var contentPauseChk = setTimeout(function(){\r\n");
				htmlPage.append("            console.log('content Pause ? Play Time : ' + ((fileInterval*1000)+3000));\r\n");
				htmlPage.append("            nextSeqConChk"+i+"('2', prepareMakeType"+i+");\r\n");
				htmlPage.append("        }, ((fileInterval*1000)+3000));\r\n");
				htmlPage.append("        switch(makeType){\r\n");
				htmlPage.append("            case 'A' : mediaTag = $('#content"+i+"a'); $('#content_show"+i+"b').css('z-index', '150'); break;\r\n");
				htmlPage.append("            case 'B' : mediaTag = $('#content"+i+"b'); $('#content_show"+i+"b').css('z-index', '300'); break;\r\n");
				htmlPage.append("        }\r\n");
				htmlPage.append("        if(fileType == 'IMAGE'){\r\n");
				htmlPage.append("            mediaTag.each(function(){\r\n");
				htmlPage.append("                clearTimeout(contentLoadCheck);\r\n");
				htmlPage.append("                setTimeout(function(){\r\n");
				htmlPage.append("                    clearTimeout(contentPauseChk);\r\n");
				htmlPage.append("                    nextSeqConChk"+i+"('2', prepareMakeType"+i+");\r\n");
				htmlPage.append("                }, fileInterval*1000);\r\n");
				htmlPage.append("                nextSeqConChk"+i+"('1', makeTypeRevers(makeType));\r\n");
				htmlPage.append("            });\r\n");
				htmlPage.append("        } else {\r\n");
				htmlPage.append("            mediaTag[0].autoplay = true;\r\n");
				if (playGubun.equals("L")){
					htmlPage.append("            mediaTag[0].src = './' + fileNm + '?autoplay=1';\r\n");	
				} else {
					htmlPage.append("            mediaTag[0].src = fileStreCours+fileNm + '?autoplay=1';\r\n");
				}
				htmlPage.append("            mediaTag.on('loadeddata', function(){\r\n");
				htmlPage.append("                clearTimeout(contentLoadCheck);\r\n");
				htmlPage.append("                nextSeqConChk"+i+"('1', makeTypeRevers(makeType));\r\n");
				htmlPage.append("            });\r\n");
				htmlPage.append("            mediaTag.on('ended', function(){\r\n");
				htmlPage.append("                clearTimeout(contentPauseChk);\r\n");
				htmlPage.append("                nextSeqConChk"+i+"('2', prepareMakeType"+i+");\r\n");
				htmlPage.append("            });\r\n");
				htmlPage.append("        }\r\n");
				htmlPage.append("        mediaTag.on('error', function(err){\r\n");
				htmlPage.append("            clearTimeout(contentLoadCheck);\r\n");
				htmlPage.append("            clearTimeout(contentPauseChk);\r\n");
				htmlPage.append("            console.log('CONTENT NAME : ' + fileNm + ', ERROR !');\r\n");
				htmlPage.append("            errorFlag"+i+" = true;\r\n");
				htmlPage.append("            nextSeqConChk"+i+"('1', makeType);\r\n");
				htmlPage.append("        });\r\n");
			    htmlPage.append("    }\r\n");
			    
			    /** 
			     * 분할부분과 관련하여 최종 콘텐츠 재생 후 관련 내용 시작 부
			     * 2019-01-14 페이지를 새로고치는 부분에서 기준값이 필요로 하다,
			     * 1. 메인되는 모니터를 정할지
			     * 2. 완전히 따로노는 무한 플레이 방식을 고수할지 (안정화 상당량 필요)
			     * 3. 내용정리 필요
			     * */
			    htmlPage.append("    function nextSeqConChk"+i+"(checkType, makeType){\r\n");
			    htmlPage.append("        if(contentCount"+i+" == jsonData"+i+".length){\r\n");
			    htmlPage.append("            location.href = document.location.href;\r\n");
			    htmlPage.append("        } else {\r\n");
			    htmlPage.append("            if(checkType == '1'){\r\n");
			    htmlPage.append("                contentCount"+i+"++;\r\n");
			    htmlPage.append("                if(contentCount"+i+" != jsonData"+i+".length){\r\n");
			    htmlPage.append("                    tagMaking"+i+"(jsonData"+i+"[contentCount"+i+"].streFileNm, jsonData"+i+"[contentCount"+i+"].mediaType, jsonData"+i+"[contentCount"+i+"].timeInterval, jsonData"+i+"[contentCount"+i+"].fileStreCours, makeType);\r\n");
			    htmlPage.append("                }\r\n");
			    htmlPage.append("            } else {\r\n");
			    htmlPage.append("                mediaPlaySetting"+i+"(prepareFileNm"+i+", prepareFileType"+i+", prepareFileTime"+i+", prepareFileStreCours"+i+", prepareMakeType"+i+");\r\n");
			    htmlPage.append("            }\r\n");
			    htmlPage.append("        }\r\n");
			    htmlPage.append("    }\r\n");
			}
			htmlPage.append("    function makeTypeRevers(makeType){\r\n");
		    htmlPage.append("        if(makeType == 'A'){\r\n");
		    htmlPage.append("            return 'B';\r\n");
		    htmlPage.append("        } else {\r\n");
		    htmlPage.append("            return 'A';\r\n");
		    htmlPage.append("        }\r\n");
		    htmlPage.append("    }\r\n");
			htmlPage.append("</script>\r\n");			
			//다음 시퀀스가 있는지 없는지 확인 후 setTime값으로 하기			
			if (!vo_info.getConNextSeq().equals("0") && !vo_info.getConNextSeq().equals("")){
				
				htmlPage.append("<script type='text/javascript'> \r\n");
				String page_url = "";
				if (playGubun.equals("L")){
				    page_url = contentMuti.selectContentFileInfoLocal(vo_info.getConNextSeq());
				}else {
					page_url = contentMuti.selectContentFileInfo(vo_info.getConNextSeq());
				}
				String pageTime = contentMuti.selectMaxTimeInterval(vo_info.getConNextSeq());				
				htmlPage.append("setTimeout( 'nextPage()', "+ Integer.parseInt(pageTime)*1000  +");	\r\n");	
				htmlPage.append(" function nextPage(){ \r\n");
				
				htmlPage.append(" location.href='"+page_url+"';\r\n");
				htmlPage.append(" } \r\n");
				htmlPage.append("</script>	\r\n");	
			}
			
			htmlPage.append("<style>	\r\n");
			htmlPage.append("    body{\r\n");
			htmlPage.append("        margin : 0;\r\n");
			htmlPage.append("        background-color : #000;\r\n");
			htmlPage.append("    }\r\n");
			
			for (int i = 0 ; i < detailInfo.size() ; i++ ){
				htmlPage.append("    .contentBox"+i+"{\r\n");
				htmlPage.append("        position : fixed;\r\n");
				htmlPage.append("        top : 0;\r\n");
				if (i ==0){
					htmlPage.append("        width: "+width0+"px; height: "+height0+"px;\r\n");	
				} else {
					htmlPage.append("        width: "+width1+"px; height: "+height1+"px;\r\n");
					if(viewType == 1){
						htmlPage.append("        left: "+mid+"px;\r\n");
					} else if(viewType == 2){
						htmlPage.append("        top: "+mid+"px;\r\n");
					}
				}
				htmlPage.append("        float : left;\r\n");
				htmlPage.append("        background-color : #000;\r\n");
				htmlPage.append("    }\r\n");
				
				
				htmlPage.append("    .contentBox"+i+" video{\r\n");
				if(viewType == 0){
					htmlPage.append("        width: "+width0+"px; height: "+height0+"px;\r\n");
					htmlPage.append("        min-width: "+width0+"px; min-height: "+height0+"px;\r\n");	
				} else {
					if (i == 0){
						htmlPage.append("        width: "+width0+"px; height: "+height0+"px;\r\n");
						htmlPage.append("        min-width: "+width0+"px; min-height: "+height0+"px;\r\n");	
					} else {
						htmlPage.append("        width: "+width1+"px; height: "+height1+"px;\r\n");
						htmlPage.append("        min-width: "+width1+"px; min-height: "+height1+"px;\r\n");
					}
					htmlPage.append("        object-fit: fill;\r\n");	
				}				
				htmlPage.append("    }\r\n");
				
				
			}
			
			htmlPage.append("</style>	\r\n");
			htmlPage.append("</head>	\r\n");
			htmlPage.append("<body>\r\n");
			if (playGubun.equals("L")){
				htmlPage.append("    <iframe src='./silence.mp3' allow='autoplay' id='setAudio' style='display:none'></iframe>  \r\n");
			} else {
				htmlPage.append("    <iframe src='/upload/silence.mp3' allow='autoplay' id='setAudio' style='display:none'></iframe>  \r\n");	
			}
		    for (int i = 0 ; i < detailInfo.size() ; i++ ){
		    		htmlPage.append("    <div id='content_show"+i+"a' class='contentBox"+i+"' style='z-index:200;'></div>\r\n");
		    		htmlPage.append("    <div id='content_show"+i+"b' class='contentBox"+i+"' style='z-index:150;'></div>\r\n");
		    }			
			htmlPage.append("</body>	\r\n");
			htmlPage.append("</html>	\r\n");
		}catch(Exception e){
			htmlPage.append(" 페이지 애러:"+ e.toString());
			e.printStackTrace();
		}		
		
		//System.out.println(htmlPage.toString());
		
		return htmlPage.toString();
	}
	
	
	//파일 보여 주기 
	public String conTentPageFile(String detailSeq, String conSeq ) {
		
		
		
		
		ContentMutiInfoVO vo_info = new ContentMutiInfoVO();		
		ContentDetailInfo vo = new ContentDetailInfo();
		StringBuilder htmlPage = new StringBuilder();
		List<ContentFileInfoVO> fileLst = null;
		
		
		try{
			
		vo_info = contentMuti.selectContentMutiInfoManageView(conSeq);
		vo =	contentDetail.selectContentDetail(detailSeq);
		
		String timeInterval = vo.getTimeInterval();
		String slideGubun = vo.getImageSlidtype();
		String detaillOrder = vo.getDetailOrder();
		String width  = vo_info.getConWidth();
		String height = vo_info.getConHeight();
		String mid = vo_info.getConMid();
		String top = "";
		String left = "";
		
		
		
		//가로 세로 관련 값 정하기 
		if (vo_info.getConScreen().equals("H21") && detaillOrder.equals("1")){
			height = mid;  			
			top = "0";
			left = "0";			
		}else if  (vo_info.getConScreen().equals("H21") && detaillOrder.equals("2")){
			height = Integer.toString(Integer.parseInt(height) -Integer.parseInt(mid));
			top = Integer.toString(Integer.parseInt(mid)  + 1);
			left = "0";			
		}else if  (vo_info.getConScreen().equals("W21") && detaillOrder.equals("1")){			
			width = mid;
			top = "0";
			left = "0";
		}else if  (vo_info.getConScreen().equals("W21") && detaillOrder.equals("2")){
			width =Integer.toString(Integer.parseInt(width) -Integer.parseInt(mid));
			top = "0";
			left = Integer.toString(Integer.parseInt(mid)  + 1);
			
		}		
		
		
		//파일 리스트 받기 
		fileLst = (List<ContentFileInfoVO>) conFileService.selectFileContentPageList( detailSeq);
		
		
		if (vo.getConType().toString().equals("CONTYPE01")){
			
				String movieLst = "";
				String movieFirst = "";
				String movieFirstExt = "";
				for (int i =0; i < fileLst.size(); i++){
					if (i ==0){  
						movieFirst = fileLst.get(i).getStreFileNm();
						movieFirstExt =fileLst.get(i).getFileExtsn(); 
					}
					movieLst += "'/upload/" + fileLst.get(i).getStreFileNm() + "',";
				}
				htmlPage.append("<script type='text/javascript'>  \r\n" );
				htmlPage.append("$(document).ready(function() {          \r\n" );
				htmlPage.append("         var movie_title = [" +movieLst.substring(0, movieLst.length() -1) +"];  \r\n");		
				htmlPage.append("       var Video"+detaillOrder+" = document.getElementById('Video"+detaillOrder+"');  \r\n");
				htmlPage.append("        Video"+detaillOrder+".setAttribute('src', './"+ movieFirst + "');  \r\n");
				htmlPage.append("	   var movie_length = movie_title.length;  \r\n" );					         	  
				htmlPage.append("    Video"+detaillOrder+".onended = function(e){	   		 \r\n" );
				htmlPage.append("	               for (i =0; i < movie_length ; i++){     \r\n" );
				htmlPage.append("				                      if (Video"+detaillOrder+".getAttribute('src') == movie_title[i] && i != (movie_length-1) ){   \r\n" );
				htmlPage.append("				        				   Video"+detaillOrder+".src= movie_title[i+1];	    \r\n" );
				htmlPage.append("					   					  break;     \r\n" );
				htmlPage.append("				                      }else if (Video"+detaillOrder+".getAttribute('src') == movie_title[i] && i == (movie_length-1)){   \r\n" );
				htmlPage.append("				        				   Video"+detaillOrder+".src= movie_title[0];	    \r\n" );
				htmlPage.append("					   					  break;    \r\n" );
				htmlPage.append("				                      }       \r\n" );
				htmlPage.append("					               }          \r\n" );
				htmlPage.append("								   Video"+detaillOrder+".type='video/mp4';   \r\n" );
				htmlPage.append("								   Video"+detaillOrder+".play();    \r\n" );
				htmlPage.append("					   }      \r\n" );
				htmlPage.append("					});       \r\n" );			
			    htmlPage.append("		</script>   \r\n");
				
			    
			    htmlPage.append("<style>     \r\n");
			    htmlPage.append("#Video"+detaillOrder+"    \r\n");
			    htmlPage.append("    {               \r\n");
			    htmlPage.append("     position:absolute;    \r\n");
			    htmlPage.append("     top: "+ top +"px;              \r\n");
			    htmlPage.append("     left:"+ left +"px;                    \r\n");
			    htmlPage.append("     width:"+ width +"px;           \r\n");
			    htmlPage.append("	 height:"+ height +";                  \r\n");
			    htmlPage.append("     border:0px solid blue;    \r\n");
			    htmlPage.append("     display:block;                   \r\n");
			    htmlPage.append("     z-index:99;                   \r\n");
			    htmlPage.append("     }                           \r\n");
			    htmlPage.append("</style>         \r\n");
			    
			    if (Integer.parseInt(width)> Integer.parseInt(height) ){
				    htmlPage.append("    <video id='Video"+detaillOrder+"' autoplay width='"+width+"'>      \r\n" );
			    }else {
			    	htmlPage.append("    <video id='Video"+detaillOrder+"' autoplay height='"+height+"'>      \r\n" );
			    }
				htmlPage.append("       <source src='./"+ movieFirst + "' type='video/mp4' />  \r\n");           
				htmlPage.append("   </video> \r\n");
			
		}else if ( vo.getConType().toString().equals("CONTYPE02")   ) {
			if (slideGubun.equals("1")){
				
				
				try {
					
					String imageLst = "";
					if (fileLst !=  null)
					{
						for (int i =0; i < fileLst.size(); i++){
							imageLst += "'./" + fileLst.get(i).getStreFileNm() + "',";
						}

						htmlPage.append("<script type='text/javascript'>  \r\n" );
						htmlPage.append("var imagePaths = [" +imageLst.substring(0, imageLst.length() -1) +"];  \r\n");
						htmlPage.append("var showCanvas = null;  \r\n");				
						htmlPage.append("var showCanvasCtx = null;  \r\n");
						htmlPage.append("var img = document.createElement('img');  \r\n");
						htmlPage.append("var currentImage = 0;  \r\n");
						htmlPage.append("var revealTimer;  \r\n");
						htmlPage.append("window.onload = function () {  \r\n");
						htmlPage.append("showCanvas = document.getElementById('GoSlideShow');  \r\n");
						htmlPage.append("showCanvasCtx = showCanvas.getContext('2d');  \r\n");
						htmlPage.append(" img.setAttribute('width','"+width+ "');    \r\n");
						htmlPage.append(" img.setAttribute('height','"+height+ "');    \r\n");
						htmlPage.append(" switchImage();   \r\n");
						htmlPage.append(" setInterval(switchImage,"+timeInterval+"*1000 );   \r\n");
						htmlPage.append(" }  \r\n");
						
						htmlPage.append(" function switchImage() {  \r\n");
						htmlPage.append(" img.setAttribute('src',imagePaths[currentImage++]);  \r\n");
						htmlPage.append(" if (currentImage >= imagePaths.length) {    \r\n");
						htmlPage.append(" currentImage = 0;              \r\n");
						htmlPage.append(" }             \r\n");
						
						htmlPage.append(" showCanvasCtx.globalAlpha = 0.1;             \r\n");
						htmlPage.append(" revealTimer = setInterval(revealImage,100);             \r\n");
						htmlPage.append(" }             \r\n");
						
						
						htmlPage.append(" function revealImage() {              \r\n");
						htmlPage.append("  showCanvasCtx.save();              \r\n");
						//htmlPage.append("  showCanvasCtx.drawImage(img,0,0,"+ width + ","+height+" );             \r\n");
						htmlPage.append("  showCanvasCtx.drawImage(img,0,0 );             \r\n");
						htmlPage.append("  showCanvasCtx.globalAlpha += 0.1;             \r\n");
						htmlPage.append("  if (showCanvasCtx.globalAlpha >= 1.0) {             \r\n");
						htmlPage.append("     clearInterval(revealTimer);             \r\n");
						htmlPage.append("  }             \r\n");
						htmlPage.append("  showCanvasCtx.restore();             \r\n");
						htmlPage.append(" }        \r\n");
						htmlPage.append("</script>      \r\n");			
						
						
						htmlPage.append("<canvas id='GoSlideShow' width='"+ width + "' height='"+height+"' style='border:solid 1px'>Canvas Not Supported </canvas>       \r\n");
						
						
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("S1 Page Error:" + e.toString() );
					htmlPage.append("Page Error:" + e.toString() );
				}		   	
			}else if (slideGubun.equals("2")){
				
				
				try {
														
					if (fileLst !=  null)
					{
						htmlPage.append("<div id='wowslider-container1'>   \r\n " );
						htmlPage.append(" <div class='ws_images' >   \r\n " );
						htmlPage.append("   <ul>   \r\n " );		
						
						for (int i =0; i < fileLst.size(); i++){
							htmlPage.append("  <li><img src='./"+ fileLst.get(i).getStreFileNm() +"' alt='' title='' id='"+  fileLst.get(i).getFileOrder()+"' width='"+ width + "' height='"+height+"' /></li>   \r\n " );
						}
						
						htmlPage.append("   </ul>   \r\n " );
						htmlPage.append("</div>   \r\n " );
						htmlPage.append("<script type='text/javascript' src='./wowslider.js'></script>   \r\n " );
						htmlPage.append("<script type='text/javascript' src='./script.js'></script>  \r\n " );
						
					}
				} catch (Exception e) {
					// 
					e.printStackTrace();
					System.out.println("S2 Page Error:" + e.toString() );
					htmlPage.append("Page Error:" + e.toString() );
				}								
			}
			
		}else {
			//텍스트 타입인 경우 
			htmlPage.append("Text page not support");
		}
		
		}catch (Exception e1){
			System.out.println(e1.toString());
			htmlPage.append("PAGE ERROR:" + e1.toString());
		}
		
				
		return htmlPage.toString();
		
	}
	// html 페이지  보여주기 
	public String conTentPage(String detailSeq, String conSeq ) {
		
		
		
		
		ContentMutiInfoVO vo_info = new ContentMutiInfoVO();		
		ContentDetailInfo vo = new ContentDetailInfo();
		StringBuilder htmlPage = new StringBuilder();
		List<ContentFileInfoVO> fileLst = null;
		
		
		try{
			
		vo_info = contentMuti.selectContentMutiInfoManageView(conSeq);
		vo =	contentDetail.selectContentDetail(detailSeq);
		
		String timeInterval = vo.getTimeInterval();
		String slideGubun = vo.getImageSlidtype();
		String detaillOrder = vo.getDetailOrder();
		String width  = vo_info.getConWidth();
		String height = vo_info.getConHeight();
		String mid = vo_info.getConMid();
		String top = "";
		String left = "";
		
		
		
		//가로 세로 관련 값 정하기 
		if (vo_info.getConScreen().equals("H21") && detaillOrder.equals("1")){
			height = mid;  			
			top = "0";
			left = "0";			
		}else if  (vo_info.getConScreen().equals("H21") && detaillOrder.equals("2")){
			height = Integer.toString(Integer.parseInt(height) -Integer.parseInt(mid));
			top = Integer.toString(Integer.parseInt(mid)  + 1);
			left = "0";			
		}else if  (vo_info.getConScreen().equals("W21") && detaillOrder.equals("1")){			
			width = mid;
			top = "0";
			left = "0";
		}else if  (vo_info.getConScreen().equals("W21") && detaillOrder.equals("2")){
			width =Integer.toString(Integer.parseInt(width) -Integer.parseInt(mid));
			top = "0";
			left = Integer.toString(Integer.parseInt(mid)  + 1);
			
		}		
		
		
		//파일 리스트 받기 
		fileLst = (List<ContentFileInfoVO>) conFileService.selectFileContentPageList( detailSeq);
		
		
		if (vo.getConType().toString().equals("CONTYPE01")){
			
				String movieLst = "";
				String movieFirst = "";
				String movieFirstExt = "";
				for (int i =0; i < fileLst.size(); i++){
					if (i ==0){  
						movieFirst = fileLst.get(i).getStreFileNm();
						movieFirstExt =fileLst.get(i).getFileExtsn(); 
					}
					movieLst += "'/upload/" + fileLst.get(i).getStreFileNm() + "',";
				}
				htmlPage.append("<script type='text/javascript'>  \r\n" );
				htmlPage.append("$(document).ready(function() {          \r\n" );
				htmlPage.append("         var movie_title = [" +movieLst.substring(0, movieLst.length() -1) +"];  \r\n");		
				htmlPage.append("       var Video"+detaillOrder+" = document.getElementById('Video"+detaillOrder+"');  \r\n");
				htmlPage.append("        Video"+detaillOrder+".setAttribute('src', '/upload/"+ movieFirst + "');  \r\n");
				htmlPage.append("	   var movie_length = movie_title.length;  \r\n" );					         	  
				htmlPage.append("    Video"+detaillOrder+".onended = function(e){	   		 \r\n" );
				htmlPage.append("	               for (i =0; i < movie_length ; i++){     \r\n" );
				htmlPage.append("				                      if (Video"+detaillOrder+".getAttribute('src') == movie_title[i] && i != (movie_length-1) ){   \r\n" );
				htmlPage.append("				        				   Video"+detaillOrder+".src= movie_title[i+1];	    \r\n" );
				htmlPage.append("					   					  break;     \r\n" );
				htmlPage.append("				                      }else if (Video"+detaillOrder+".getAttribute('src') == movie_title[i] && i == (movie_length-1)){   \r\n" );
				htmlPage.append("				        				   Video"+detaillOrder+".src= movie_title[0];	    \r\n" );
				htmlPage.append("					   					  break;    \r\n" );
				htmlPage.append("				                      }       \r\n" );
				htmlPage.append("					               }          \r\n" );
				htmlPage.append("								   Video"+detaillOrder+".type='video/mp4';   \r\n" );
				htmlPage.append("								   Video"+detaillOrder+".play();    \r\n" );
				htmlPage.append("					   }      \r\n" );
				htmlPage.append("					});       \r\n" );			
			    htmlPage.append("		</script>   \r\n");
				
			    
			    htmlPage.append("<style>     \r\n");
			    htmlPage.append("#Video"+detaillOrder+"    \r\n");
			    htmlPage.append("    {               \r\n");
			    htmlPage.append("     position:absolute;    \r\n");
			    htmlPage.append("     top: "+ top +"px;              \r\n");
			    htmlPage.append("     left:"+ left +"px;                    \r\n");
			    htmlPage.append("     width:"+ width +"px;           \r\n");
			    htmlPage.append("	 height:"+ height +";                  \r\n");
			    htmlPage.append("     border:0px solid blue;    \r\n");
			    htmlPage.append("     display:block;                   \r\n");
			    htmlPage.append("     z-index:99;                   \r\n");
			    htmlPage.append("     }                           \r\n");
			    htmlPage.append("</style>         \r\n");
			    
			    if (Integer.parseInt(width)> Integer.parseInt(height) ){
				    htmlPage.append("    <video id='Video"+detaillOrder+"' autoplay width='"+width+"'>      \r\n" );
			    }else {
			    	htmlPage.append("    <video id='Video"+detaillOrder+"' autoplay height='"+height+"'>      \r\n" );
			    }
				htmlPage.append("       <source src='/upload/"+ movieFirst + "' type='video/mp4' />  \r\n");           
				htmlPage.append("   </video> \r\n");
			
		}else if ( vo.getConType().toString().equals("CONTYPE02")   ) {
			if (slideGubun.equals("1")){
				
				
				try {
					
					String imageLst = "";
					if (fileLst !=  null)
					{
						for (int i =0; i < fileLst.size(); i++){
							imageLst += "'/upload/" + fileLst.get(i).getStreFileNm() + "',";
						}

						htmlPage.append("<script type='text/javascript'>  \r\n" );
						htmlPage.append("var imagePaths = [" +imageLst.substring(0, imageLst.length() -1) +"];  \r\n");
						htmlPage.append("var showCanvas = null;  \r\n");				
						htmlPage.append("var showCanvasCtx = null;  \r\n");
						htmlPage.append("var img = document.createElement('img');  \r\n");
						htmlPage.append("var currentImage = 0;  \r\n");
						htmlPage.append("var revealTimer;  \r\n");
						htmlPage.append("window.onload = function () {  \r\n");
						htmlPage.append("showCanvas = document.getElementById('GoSlideShow');  \r\n");
						htmlPage.append("showCanvasCtx = showCanvas.getContext('2d');  \r\n");
						htmlPage.append(" img.setAttribute('width','"+width+ "');    \r\n");
						htmlPage.append(" img.setAttribute('height','"+height+ "');    \r\n");
						htmlPage.append(" switchImage();   \r\n");
						htmlPage.append(" setInterval(switchImage,"+timeInterval+"*1000 );   \r\n");
						htmlPage.append(" }  \r\n");
						
						htmlPage.append(" function switchImage() {  \r\n");
						htmlPage.append(" img.setAttribute('src',imagePaths[currentImage++]);  \r\n");
						htmlPage.append(" if (currentImage >= imagePaths.length) {    \r\n");
						htmlPage.append(" currentImage = 0;              \r\n");
						htmlPage.append(" }             \r\n");
						
						htmlPage.append(" showCanvasCtx.globalAlpha = 0.1;             \r\n");
						htmlPage.append(" revealTimer = setInterval(revealImage,100);             \r\n");
						htmlPage.append(" }             \r\n");
						
						
						htmlPage.append(" function revealImage() {              \r\n");
						htmlPage.append("  showCanvasCtx.save();              \r\n");
						//htmlPage.append("  showCanvasCtx.drawImage(img,0,0,"+ width + ","+height+" );             \r\n");
						htmlPage.append("  showCanvasCtx.drawImage(img,0,0 );             \r\n");
						htmlPage.append("  showCanvasCtx.globalAlpha += 0.1;             \r\n");
						htmlPage.append("  if (showCanvasCtx.globalAlpha >= 1.0) {             \r\n");
						htmlPage.append("     clearInterval(revealTimer);             \r\n");
						htmlPage.append("  }             \r\n");
						htmlPage.append("  showCanvasCtx.restore();             \r\n");
						htmlPage.append(" }        \r\n");
						htmlPage.append("</script>      \r\n");			
						
						
						htmlPage.append("<canvas id='GoSlideShow' width='"+ width + "' height='"+height+"' style='border:solid 1px'>Canvas Not Supported </canvas>       \r\n");
						
						
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("S1 Page Error:" + e.toString() );
					htmlPage.append("Page Error:" + e.toString() );
				}		   	
			}else if (slideGubun.equals("2")){
				
				
				try {
														
					if (fileLst !=  null)
					{
						htmlPage.append("<div id='wowslider-container1'>   \r\n " );
						htmlPage.append(" <div class='ws_images' >   \r\n " );
						htmlPage.append("   <ul>   \r\n " );		
						
						for (int i =0; i < fileLst.size(); i++){
							htmlPage.append("  <li><img src='/upload/"+ fileLst.get(i).getStreFileNm() +"' alt='' title='' id='"+  fileLst.get(i).getFileOrder()+"' width='"+ width + "' height='"+height+"' /></li>   \r\n " );
						}
						
						htmlPage.append("   </ul>   \r\n " );
						htmlPage.append("</div>   \r\n " );
						htmlPage.append("<script type='text/javascript' src='/upload/wowslider.js'></script>   \r\n " );
						htmlPage.append("<script type='text/javascript' src='/upload/script.js'></script>  \r\n " );
						
					}
				} catch (Exception e) {
					// 
					e.printStackTrace();
					System.out.println("S2 Page Error:" + e.toString() );
					htmlPage.append("Page Error:" + e.toString() );
				}								
			}
			
		}else {
			//텍스트 타입인 경우 
			htmlPage.append("Text page not support");
		}
		
		}catch (Exception e1){
			System.out.println(e1.toString());
			htmlPage.append("PAGE ERROR:" + e1.toString());
		}
		
				
		return htmlPage.toString();
		
	}
	
}