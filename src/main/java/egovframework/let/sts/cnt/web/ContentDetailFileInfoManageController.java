package egovframework.let.sts.cnt.web;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.Globals;
import egovframework.let.sts.cnt.service.ContentDetailFileInfo;
import egovframework.let.sts.cnt.service.ContentDetailFileInfoManageService;
import egovframework.let.sts.cnt.service.ContentDetailFileInfoVO;
import egovframework.let.sts.cnt.service.ContentDetailInfoService;
import egovframework.let.sts.cnt.service.ContentFileInfoVO;

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



@Controller
public class ContentDetailFileInfoManageController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContentFileInfoManageController.class);
	
	@Resource(name="ContentDetailFileInfo")
	private  ContentDetailFileInfoManageService conFileinfo;
	
	
	@Resource(name="ContentDetailInfoService")
	private ContentDetailInfoService contentDetail;
	
	@Resource(name="egovMessageSource")
	protected EgovMessageSource egovMessageSource;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;
	
	
	@RequestMapping(value="/backoffice/sub/conManage/conDetailFileInfoTable.do")
	public ModelAndView contentFileDetailInfo( HttpServletRequest request ) throws Exception {
		
		String atchFileId = request.getParameter("atchFileId") != null ? request.getParameter("atchFileId") : "";
		ModelAndView model = new ModelAndView("jsonView");
		
		ContentDetailFileInfoVO fileInfo =  conFileinfo.selectContentDetailFileInfo(atchFileId);
		model.addObject("detailFileInfo", fileInfo);
		return model;
	}
	@RequestMapping(value="/backoffice/sub/conManage/conDetailFileInfoTableSeq.do")
	public ModelAndView contentFileDetailInfofileSeq( HttpServletRequest request ) throws Exception {
		
		String fileSeq = request.getParameter("fileSeq") != null ? request.getParameter("fileSeq") : "";
		ModelAndView model = new ModelAndView("jsonView");
		
		ContentDetailFileInfoVO fileInfo =  conFileinfo.selectContentDetailFileInfoFileSeq(fileSeq);
		model.addObject("detailFileInfo", fileInfo);
		return model;
	}	
	//시간값 
	@RequestMapping(value="/backoffice/sub/conManage/preViewCheck.do")
	@ResponseBody
	public String contentTimeCheck(HttpServletRequest request ) throws Exception {
		
		String conSeq = request.getParameter("conSeq") != null ? request.getParameter("conSeq") : "";
		int ret = conFileinfo.selectTimeIntevalNullCheck(conSeq);
		if (ret > 0) { return "F";} else { return "0";}		
	}
	
	
	
	
	
	
	@RequestMapping(value="/backoffice/sub/conManage/conDetailInfoTable.do")
	public ModelAndView conDetailInfoTable(HttpServletRequest request ) throws Exception {
		
		String conSeq = request.getParameter("conSeq") != null ? request.getParameter("conSeq") : "";
		String detailSeq = request.getParameter("detailSeq") != null ? request.getParameter("detailSeq") : "";
		
		ModelAndView model = new ModelAndView("jsonView");
		
		ContentDetailFileInfoVO searchVO = new ContentDetailFileInfoVO();
		searchVO.setConSeq(conSeq);
		searchVO.setDetailSeq(detailSeq);
		
		List<ContentDetailFileInfoVO> fileInfo =  conFileinfo.selectContentDetailFileLst(searchVO);
		model.addObject("detailContentInfo", fileInfo);
		return model;
	}
	
	@RequestMapping(value="/backoffice/sub/conManage/ContentReg.do")
	@ResponseBody
	public String insertContentReg(@ModelAttribute("loginVO") LoginVO loginVO
												 , ContentDetailFileInfo vo
									             , HttpServletRequest request
												 , BindingResult bindingResult
											     , ModelMap model) throws Exception {
		String resultMessage = "";
		try{
			String conSeq = request.getParameter("conSeq") != null ? request.getParameter("conSeq") : "";		
			String detailSeq = request.getParameter("detailSeq") != null ? request.getParameter("detailSeq") : "";		
			String atchFileId = request.getParameter("atchFileId") != null ? request.getParameter("atchFileId") : "";		
			String fileOrder = request.getParameter("fileOrder") != null ? request.getParameter("fileOrder") : "";
			
			System.out.println("conseq ?? " + conSeq);
			
			vo.setConSeq(conSeq);
			vo.setDetailSeq(detailSeq);
			vo.setAtchFileId(atchFileId);
			vo.setFileOrder(fileOrder);
			
			
			
			
			int result = conFileinfo.insertContentDetailFileManage(vo);
			
			if (result > 0){
				
				
				//동영상 및 음원파일이면 시간 업데이트 이후 시간 정리 하기 				
				int fileSeq = conFileinfo.selectMaxfileSeq(vo);
				
				
				ContentDetailFileInfoVO fileInfo =  conFileinfo.selectContentDetailFileInfoFileSeq( Integer.toString( fileSeq));				
				if (!fileInfo.getMediaType().toString().equals("IMAGE")){
					System.out.println(fileInfo.getPlayTime().toString());
					if (fileInfo.getPlayTime().toString().length() > 10) {
						fileInfo.setPlayTime(fileInfo.getPlayTime().toString().substring(0, 8));
						System.out.println(fileInfo.getPlayTime().toString());
					}
					/* 20180513 merge 확인 필요 부분
					if (fileInfo.getPlayTime().length() > 8){
						fileInfo.setPlayTime(fileInfo.getPlayTime().substring(0, 8));
					}
					*/
					vo.setTimeInterval( fileInfo.getPlayTime().toString());
					vo.setFileSeq( Integer.toString(fileSeq));
					int resultUpdate = conFileinfo.updateContentDetailFileTimeIntervalManage(vo);				
				}
				
				int result_update = contentDetail.updateContentDetailTimeManage(detailSeq);
				
				resultMessage = "O|"+fileSeq+"|"+result_update;				
			}else {
				resultMessage = "F";
			}
		}catch (Exception e){
			System.out.println("e:" + e.toString());
			resultMessage = "F";
		}
		System.out.println("resultMessage:" + resultMessage);
		return resultMessage;
	}
	@RequestMapping(value="/backoffice/sub/conManage/ContentTotalTime.do")
	@ResponseBody
	public String contentTotalTimeInterval( HttpServletRequest request) throws Exception{
		String detailSeq = request.getParameter("detailSeq") != null ? request.getParameter("detailSeq") : "";
		
		return conFileinfo.selectDetailContentSumTime(detailSeq);
		
	}
	
	@RequestMapping(value="/backoffice/sub/conManage/contentFileTimeIntervalUpdate.do")	
	@ResponseBody
	public String jsonFileTimeIntervalUpdate( HttpServletRequest request)throws Exception {
		
		String timeInterval = request.getParameter("timeInterval") != null ? request.getParameter("timeInterval") : "";
		String fileSeq = request.getParameter("fileSeq") != null ? request.getParameter("fileSeq") : "";
		
		ContentDetailFileInfo vo = new ContentDetailFileInfo();
		vo.setFileSeq(fileSeq);
		vo.setTimeInterval(timeInterval);
		
		int result = conFileinfo.updateContentDetailFileTimeIntervalManage(vo);
		String resultMessage = "";
		if (result > 0){			
			resultMessage =  "O";
		}else {
			resultMessage = "F";
		}
		return resultMessage;
	}
	
	@RequestMapping(value="/backoffice/sub/conManage/ContentUpdateOrder.do")
	@ResponseBody
	public String contentTimeInterval( HttpServletRequest request) throws Exception{
		
		String timeInterval = request.getParameter("timeInterval") != null ? request.getParameter("timeInterval") : "";		
		String fileSeq = request.getParameter("fileSeq") != null ? request.getParameter("fileSeq") : "";
		String detailSeq = request.getParameter("detailSeq") != null ? request.getParameter("detailSeq") : "";
		
		ContentDetailFileInfo vo = new ContentDetailFileInfo();
		vo.setFileSeq(fileSeq);
		if (timeInterval.length() > 10) {
			timeInterval = timeInterval.substring(0, 8);
		}
		vo.setTimeInterval(timeInterval);
		
		int result = conFileinfo.updateContentDetailFileTimeIntervalManage(vo);
		String resultMessage = "";
		if (result > 0){			
			resultMessage =  conFileinfo.selectDetailContentSumTime(detailSeq);
		}else {
			resultMessage = "F";
		}
		return resultMessage;
		
	}
	
	@RequestMapping(value="/backoffice/sub/conManage/ContentUpdateOrderFile.do")
	@ResponseBody
	public String updateOrder( HttpServletRequest request) throws Exception{		
		String fileOrder = request.getParameter("fileOrder") != null ? request.getParameter("fileOrder") : "";		
		String fileSeq = request.getParameter("fileSeq") != null ? request.getParameter("fileSeq") : "";
		
		ContentDetailFileInfo vo = new ContentDetailFileInfo();
		vo.setFileSeq(fileSeq);
		vo.setFileOrder(fileOrder);
		
		int result = conFileinfo.updateContentOrderDetailFileManage(vo);
		String resultMessage = "";
		if (result > 0){
			resultMessage = "O";
		}else {
			resultMessage = "F";
		}
		return resultMessage;
		
	}
	
	@RequestMapping(value="/backoffice/sub/conManage/ContentUpdate.do")
	@ResponseBody
	public String updateContentReg(@ModelAttribute("loginVO") LoginVO loginVO
												 , ContentDetailFileInfo vo
									             , HttpServletRequest request
												 , BindingResult bindingResult
											     , ModelMap model) throws Exception {
		
		String conSeq = request.getParameter("conSeq") != null ? request.getParameter("conSeq") : "";		
		String detailSeq = request.getParameter("detailSeq") != null ? request.getParameter("detailSeq") : "";		
		String atchFileId = request.getParameter("atchFileId") != null ? request.getParameter("atchFileId") : "";		
		String fileOrder = request.getParameter("fileOrder") != null ? request.getParameter("fileOrder") : "";
		
		vo.setConSeq(conSeq);
		vo.setDetailSeq(detailSeq);
		vo.setAtchFileId(atchFileId);
		vo.setFileOrder(fileOrder);
		
		
		int result = conFileinfo.updateContentDetailFileManage(vo);
		String resultMessage = "";
		if (result > 0){
			
			int fileSeq = conFileinfo.selectMaxfileSeq(vo);			
			resultMessage = "O|"+ Integer.toString(fileSeq) ;			
		}else {
			resultMessage = "F";
		}
		return resultMessage;
	}
	@RequestMapping(value="/backoffice/sub/conManage/ContentDel.do")
	@ResponseBody
	public String deleteContentReg( HttpServletRequest request) throws Exception {
		
		String fileSeq = request.getParameter("fileSeq") != null ? request.getParameter("fileSeq") : "";
		String detailSeq = request.getParameter("detailSeq") != null ? request.getParameter("detailSeq") : "";
		int result = conFileinfo.deleteContentDetailFileManage(fileSeq);
		
		//시간 변경 작업 		
		String resultMessage = "";
		if (result > 0){
			//시간 업데이트
			int result_update = contentDetail.updateContentDetailTimeManage(detailSeq);			
			resultMessage = conFileinfo.selectDetailContentSumTime(detailSeq);
		}else {
			resultMessage = "F";
		}
		return resultMessage;
	}
	
}
