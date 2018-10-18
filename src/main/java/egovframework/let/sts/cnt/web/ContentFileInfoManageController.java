package egovframework.let.sts.cnt.web;



import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.Globals;
import egovframework.let.sts.cnt.service.ContentFileInfo;
import egovframework.let.sts.cnt.service.ContentFileInfoVO;
import egovframework.let.sts.cnt.service.ContentFileInfoManageService;

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
import egovframework.let.utl.fcc.service.FileUpladController;
import egovframework.let.utl.fcc.service.FileUpladController;


@Controller
public class ContentFileInfoManageController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContentFileInfoManageController.class);
	
	@Resource(name="ContentFileInfo")	
	private ContentFileInfoManageService conFileService;
	
	
	
	
	@Resource(name="egovMessageSource")
	protected EgovMessageSource egovMessageSource;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;
	
	
	@RequestMapping(value="/backoffice/sub/conManage/imageTb.do")
	public ModelAndView selectImageTable(@ModelAttribute("loginVO") LoginVO loginVO,
                                                               HttpServletRequest request)throws Exception{
		
		ModelAndView model = new 	ModelAndView("jsonView");		
		String detailSeq = request.getParameter("detailSeq") != null ? request.getParameter("detailSeq") : "";
		 
		
	    List<ContentFileInfoVO> fileLst = (List<ContentFileInfoVO>) conFileService.selectFileContentPageList( detailSeq);        
	    model.addObject("conFileLst", fileLst);        		
    	return model;
		
	}
	@RequestMapping(value="/backoffice/sub/brodManage/playContentUpdate.do")
	@ResponseBody
	public String mediaUpadateUseYn(HttpServletRequest request
									 , ModelMap model ) throws Exception{
		
		String atchFileId =  request.getParameter("atchFileId") != null ? request.getParameter("atchFileId") : "";
		String useYn =  request.getParameter("useYn") != null ? request.getParameter("useYn") : "";
		String orignlFileNm =  request.getParameter("orignlFileNm") != null ? request.getParameter("orignlFileNm") : "";
		//추후 볼륨 관련 컨트롤 삽입 
		ContentFileInfo fileInfo = new ContentFileInfo();
		fileInfo.setAtchFileId(atchFileId);
		fileInfo.setOrignlFileNm(orignlFileNm);
		fileInfo.setUseYn(useYn);
		
		int ret = conFileService.updateFileManageUseYn(fileInfo);
		if (ret>0){
			return "O";
		}else {
			return "F";
		}
	}
	
	@RequestMapping(value="/backoffice/sub/brodManage/brodContentPlayList.do")
	public String selectPlayContentLst(@ModelAttribute("loginVO") LoginVO loginVO
										, @ModelAttribute("searchVO") ContentFileInfoVO searchVO
										, HttpServletRequest request
										, BindingResult bindingResult			
										, ModelMap model ) throws Exception{
							
			model.addAttribute("regist", searchVO);
			
			if (searchVO.getNotConType() == null) { searchVO.setNotConType("");}
			//신규 추가 
			if (searchVO.getFileGubun() == null) { searchVO.setFileGubun("");}
			
			searchVO.setMediaType("MUSIC"); 
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
			try{
			model.addAttribute("resultList",  conFileService.selectFilePageListByPagination(searchVO) );
			}catch (Exception e){
			System.out.println("error:"+e.toString());
			}
			int totCnt = conFileService.selectFilePageListByPaginationTotCnt_S(searchVO);       
			paginationInfo.setTotalRecordCount(totCnt);
			model.addAttribute("paginationInfo", paginationInfo);
			model.addAttribute("totalCnt", totCnt);		       
			model.addAttribute("regist", searchVO);		    
			
			return "/backoffice/sub/brodManage/brodContentPlayList";
							
	}
	
	@RequestMapping(value="/backoffice/sub/brodManage/playContentDel.do")
	@ResponseBody 
	public String selectbrodMediaLst(HttpServletRequest request
									 , ModelMap model ) throws Exception{
		
		String atchFileId =  request.getParameter("atchFileId") != null ? request.getParameter("atchFileId") : "";
		
		String[] atchFileIdA = atchFileId.substring(1).split(",");
		
		ContentFileInfo fileInfo = new ContentFileInfo();
		FileUpladController fileController = new FileUpladController();
		int ret = 0;
		for (int i =0; i < atchFileIdA.length; i++){
			//파일 삭제 후
			fileInfo = conFileService.selectFileDetail(atchFileIdA[i].toString());	
			fileController.deleteFile(  fileInfo.getFileStreCours() +""+  fileInfo.getStreFileNm());
			//데이타 삭제
			ret = conFileService.deleteFileManage(atchFileIdA[i].toString());
		}
		fileController = null;
		if (ret > 0){
			return "0";
		}else {
			return "F";
		}
		
	}
	@RequestMapping(value="/backoffice/sub/brodManage/playContentStateChange.do")
	@ResponseBody 
	public String selectFileStateChange(HttpServletRequest request
									 , ModelMap model ) throws Exception{
		
		String atchFileId =  request.getParameter("atchFileId") != null ? request.getParameter("atchFileId") : "";
		String useYn =  request.getParameter("useYn") != null ? request.getParameter("useYn") : "";
		
		String[] atchFileIdA = atchFileId.substring(1).split(",");
		
		ContentFileInfo fileInfo = new ContentFileInfo();		
		int ret = 0;
		for (int i =0; i < atchFileIdA.length; i++){
			//파일 삭제 후
			fileInfo.setUseYn(useYn);
			fileInfo.setAtchFileId(atchFileIdA[i].toString());
			ret = conFileService.updateFileManageUseYn(fileInfo);
		}		
		if (ret > 0){
			return "0";
		}else {
			return "F";
		}
		
	}
	
	@RequestMapping(value="/backoffice/sub/brodManage/playContentList.do")
	public String selectbrodMediaLst(@ModelAttribute("loginVO") LoginVO loginVO
											, @ModelAttribute("searchVO") ContentFileInfoVO searchVO
											, HttpServletRequest request
											, BindingResult bindingResult			
											, ModelMap model ) throws Exception{
		
		  model.addAttribute("regist", searchVO);
		  if (searchVO.getNotConType() == null ) { searchVO.setNotConType("");}
		  if (searchVO.getFileGubun() == null ) { searchVO.setFileGubun("");}
		  
		  searchVO.setMediaType("MUSIC"); 
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
		  try{
	       model.addAttribute("resultList",  conFileService.selectFilePageListByPagination(searchVO) );
		  }catch (Exception e){
			  System.out.println("error:"+e.toString());
		  }
	       int totCnt = conFileService.selectFilePageListByPaginationTotCnt_S(searchVO);       
		   paginationInfo.setTotalRecordCount(totCnt);
	       model.addAttribute("paginationInfo", paginationInfo);
	       model.addAttribute("totalCnt", totCnt);		       
	       model.addAttribute("regist", searchVO);		    
	       
	       
	       return "/backoffice/sub/brodManage/playContentList";
		
	}
	
	
	//미디어 리스트 
	@RequestMapping(value="/backoffice/sub/conManage/mediaLst.do")
	public String  selectMediaLst(@ModelAttribute("loginVO") LoginVO loginVO
													, @ModelAttribute("searchVO") ContentFileInfoVO searchVO
													, HttpServletRequest request
													, BindingResult bindingResult			
													, ModelMap model ) throws Exception{
															
					
			  model.addAttribute("regist", searchVO);
			  
		      if(  searchVO.getPageUnit() > 0  ){    	   
		    	      if (searchVO.getPageUnit() == 10) { 
		    	    	  searchVO.setPageUnit(12);
		    	      }else {
		    	    	  searchVO.setPageUnit(searchVO.getPageUnit());
		    	      }				      
			   }else {
				   searchVO.setPageUnit(propertiesService.getInt("pageUnitImage"));   
			   }
			   searchVO.setPageSize(propertiesService.getInt("pageSizeImage"));
		       
		      /** pageing */       			  
				// 아래부분에 대해서 getConType을 getMediaType으로 변경해도 되는지는 확인 필요
			  if (searchVO.getMediaType() == null){ searchVO.setMediaType("");}
			  if (searchVO.getNotConType() == null){ searchVO.setNotConType("MUSIC");}
			  if (searchVO.getFileGubun() == null){ searchVO.setFileGubun("");}
			  
		   	  PaginationInfo paginationInfo = new PaginationInfo();
			  paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			  paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
			  paginationInfo.setPageSize(searchVO.getPageSize());

			  searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			  searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			  searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			  
			  try{
				  LOGGER.debug("getFileGubun : " + searchVO.getFileGubun());
				  LOGGER.debug("getMediaType : " + searchVO.getMediaType());
				  LOGGER.debug("getNotConType : " + searchVO.getNotConType());
				  LOGGER.debug("getFirstIndex : " + searchVO.getFirstIndex());
				  LOGGER.debug("getLastIndex : " + searchVO.getLastIndex());
				  LOGGER.debug("getRecordCountPerPage : " + searchVO.getRecordCountPerPage());
				  model.addAttribute("resultList",  conFileService.selectFilePageListByPagination(searchVO) );
			  }catch (Exception e){
				  System.out.println("error:"+e.toString());
			  }
		       int totCnt = conFileService.selectFilePageListByPaginationTotCnt_S(searchVO);       
			   paginationInfo.setTotalRecordCount(totCnt);
		       model.addAttribute("paginationInfo", paginationInfo);
		       model.addAttribute("totalCnt", totCnt);		       
		       model.addAttribute("regist", searchVO);		       
		      return "/backoffice/sub/conManage/mediaLst";
	}
	
	//파일 상세 정보 ajax 변경 
	@RequestMapping(value="/backoffice/sub/conManage/fileDetail.do")
	public ModelAndView selectFileDetail (ContentFileInfoVO vo,
	                                                          HttpServletRequest request
															 , BindingResult bindingResult
															 , ModelMap model) throws  Exception{
		
		String atchFileId = request.getParameter("atchFileId") != null ? request.getParameter("atchFileId") : "";
		
		ModelAndView modelview = new ModelAndView("jsonView");		
		modelview.addObject("fileResult", conFileService.selectFileDetail(atchFileId));		
		return modelview;		 		
	}
	
	//파일 확인 
	@RequestMapping(value="/backoffice/sub/conManage/fileView.do")
	public String selectFileView (@ModelAttribute("loginVO") LoginVO loginVO
			                                   , ContentFileInfoVO vo
								               , HttpServletRequest request
											   , BindingResult bindingResult
											   , ModelMap model) throws  Exception{		
		model.addAttribute("regist",  conFileService.selectFileDetail(vo.getAtchFileId()) );				
		return "/backoffice/sub/conManage/FileView";
	}
	
	
	
	@RequestMapping(value="/backoffice/sub/conManage/conFileUpdate.do")
	@ResponseBody
	public String conMutiDetail ( @ModelAttribute("loginVO") LoginVO loginVO
												 , ContentFileInfoVO vo
									             , HttpServletRequest request
												   , BindingResult bindingResult
												   , ModelMap model)throws Exception{
		String atchFileId = request.getParameter("atchFileId") != null ? request.getParameter("atchFileId") : "";
		String playTime = request.getParameter("playTime") != null ? request.getParameter("playTime") : "";
		String fileWidth = request.getParameter("fileWidth") != null ? request.getParameter("fileWidth") : "";
		String fileHeight = request.getParameter("fileHeight") != null ? request.getParameter("fileHeight") : "";
		
		
		vo.setAtchFileId(atchFileId);
		if (!playTime.equals("") && playTime != null){
		    vo.setPlayTime(Integer.toString((int)( Double.parseDouble( playTime))));
		}
		vo.setFileWidth(fileWidth);
		vo.setFileHeight(fileHeight);
		String delResult = "";
		
		
		System.out.println("consize ? " + fileWidth + " * " + fileHeight);
		
		int ret = conFileService.updateFileDetailInfo(vo) ;
		if (ret > 0){
			delResult="O";   	
		}else {
			delResult="F";
		}		
		return delResult; 
		
		
	}
	
	//페이지 리스트 
	@RequestMapping(value="/backoffice/sub/conManage/contentTotalCnt.do")
	@ResponseBody
	public String selectContentTotal(@ModelAttribute("loginVO") LoginVO loginVO
													, @ModelAttribute("searchVO") ContentFileInfoVO searchVO
													, HttpServletRequest request
													, BindingResult bindingResult			
													, ModelMap model)throws Exception{
		searchVO.setNotConType("MUSIC");
		searchVO.setMediaType("");
		searchVO.setFileGubun(""); // 180514
		
		
		return  String.valueOf(conFileService.selectFilePageListByPaginationTotCnt_S(searchVO));
	}
	//이미지 리스트 
	@RequestMapping(value="/backoffice/sub/conManage/jsonContentLst.do")
	public ModelAndView jsonContentLst(@ModelAttribute("ContentFileInfoVO")   ContentFileInfoVO searchVO
			                                                   , HttpServletRequest request
			                                                   , BindingResult result
														 	   , ModelMap model)throws Exception {
		
		ModelAndView jsonModel = new ModelAndView("jsonView");
		try{
			
			String strPage = request.getParameter("strPage") != null ? request.getParameter("strPage") : "";
		    String pageSize = request.getParameter("conPageSize") != null ? request.getParameter("conPageSize") : "";
		    
		    searchVO.setFirstIndex( Integer.parseInt( strPage));
		    searchVO.setRecordCountPerPage(Integer.parseInt( pageSize));
		    
		    //여기 부분 나중에 확인 
		    searchVO.setNotConType("MUSIC");
		    
		    String searchMediaType = request.getParameter("mediaType") != null ? request.getParameter("mediaType") : "";
		    searchVO.setMediaType(searchMediaType);
		    
		    searchVO.setFileGubun("");
		    
			List<ContentFileInfoVO> fileInfoVO = conFileService.selectFilePageListByPagination(searchVO);		
			
			System.out.println("fileCnt:"+ conFileService.selectFilePageListByPaginationTotCnt_S(searchVO));
			jsonModel.addObject("jsonCon", fileInfoVO);	
		}catch (Exception e){
			System.out.println("error:" + e.toString());			
		}
	    
		
		return jsonModel;
	}
	
	@RequestMapping(value="/backoffice/sub/conManage/jsonDetailContentLst.do")
	public ModelAndView jsonDetailContentLst(@ModelAttribute("ContentFileInfoVO")   ContentFileInfoVO searchVO
			                                                   , HttpServletRequest request
			                                                   , BindingResult result
														 	   , ModelMap model)throws Exception {
		
		ModelAndView jsonModel = new ModelAndView("jsonView");
		try{
			
			String strPage = request.getParameter("strPage") != null ? request.getParameter("strPage") : "";
		    String pageSize = request.getParameter("conPageSize") != null ? request.getParameter("conPageSize") : "";
		    
		    searchVO.setFirstIndex( Integer.parseInt( strPage));
		    searchVO.setRecordCountPerPage(Integer.parseInt( pageSize));
		    
		    // 조회하지 않을 MediaType
		    searchVO.setNotConType("MUSIC");
		    
		    // 파일 이름 검색
		    String orgFileNm =  request.getParameter("originFileNm") != null ? request.getParameter("originFileNm") : "";
		    searchVO.setSearchCondition("orignlFileNm");
			searchVO.setSearchKeyword(orgFileNm);
		    
		    // 조회를 원하는 특정 MediaType
		    String searchMediaType = request.getParameter("mediaType") != null ? request.getParameter("mediaType") : "";
		    searchVO.setMediaType(searchMediaType);
		    
		    // filegubun값 필요
			searchVO.setFileGubun("");
		    
		    
			List<ContentFileInfoVO> fileInfoVO = conFileService.selectFilePageListByPagination(searchVO);		
			
			// System.out.println("fileCnt:"+ conFileService.selectFilePageListByPaginationTotCnt_S(searchVO));
			jsonModel.addObject("jsonCon", fileInfoVO);	
		}catch (Exception e){
			System.out.println("error:" + e.toString());			
		}
	    
		
		return jsonModel;
	}
	
	@RequestMapping(value="/backoffice/sub/conManage/imageFileDel.do")
	@ResponseBody
	public String selectImageTableDel(@ModelAttribute("loginVO") LoginVO loginVO,
                                                                      HttpServletRequest request)throws Exception{		
		
				String atchFileId = request.getParameter("atchFileId") != null ? request.getParameter("atchFileId") : "";
				String delResult = "";
				ContentFileInfo fileInfo = new ContentFileInfo();
				FileUpladController filecontroller = new FileUpladController();
				fileInfo = conFileService.selectFileDetail(atchFileId);	
				int ret = conFileService.deleteFileManage(atchFileId);
				if (ret > 0){
					delResult="O";   
					// 파일 삭제 구문 수정
					try{					
					   LOGGER.info("file path:"+ fileInfo.getFileStreCours() +""+  fileInfo.getStreFileNm());					
					   filecontroller.deleteFile(  fileInfo.getFileStreCours() +""+  fileInfo.getStreFileNm());
					    //썸네일 지우기
						if (!fileInfo.getFileThumnail().equals("")){												
							filecontroller.deleteFile( fileInfo.getFileStreCours() +""+  fileInfo.getFileThumnail());
						}				
					}catch (Exception e){
						LOGGER.debug("file error:"+ e.toString());						
					}							
				}else {
					delResult="F";
				}		
		  return delResult;		
	}
	
	
	@RequestMapping(value="/backoffice/sub/conManage/mediaFileConnChk.do")
	@ResponseBody
	public ModelAndView selectMediaFileConnCheck(@ModelAttribute("loginVO") LoginVO loginVO,
                                                                      HttpServletRequest request)throws Exception{		
		ModelAndView jsonModel = new ModelAndView("jsonView");
		
		try{
			
			String atchFileId = request.getParameter("atchFileId") != null ? request.getParameter("atchFileId") : "";
			
			List<ContentFileInfoVO> fileInfoVO = conFileService.selectMediaConnList(atchFileId);	
			jsonModel.addObject("jsonCon", fileInfoVO);	
		}catch (Exception e){
			System.out.println("error:" + e.toString());			
		}
		
		return jsonModel;
	}
	
	
}
