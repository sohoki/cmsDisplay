package egovframework.let.sts.pic.web;



import java.io.File;
import java.io.StringReader;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.Globals;




import egovframework.let.sts.pic.service.DidMoniterPic;
import egovframework.let.sts.pic.service.DidMoniterPicVO;
import egovframework.let.sts.pic.service.DidMoniterPicService;
import egovframework.let.sts.snd.service.SendMsgInfo;
import egovframework.let.sts.snd.service.SendMsgInfoManageService;
import egovframework.let.utl.fcc.service.FileUpladController;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;



@Controller
public class DidMoniterPicController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DidMoniterPicController.class);
	
	
	FileUpladController uploadFile = new FileUpladController();
	
	@Resource(name="DidMoniterPicService")
	private DidMoniterPicService didMoniterService;
	
	@Resource(name="egovMessageSource")
	protected EgovMessageSource egovMessageSource;
	
	
	@Resource(name="SendMsgInfoManageService")
	private SendMsgInfoManageService sendService;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;


	@SuppressWarnings("finally")
	@RequestMapping(value="/backoffice/sub/equiManage/Capture.do")
	@ResponseBody	
	public String insertDidPictureXml( HttpServletRequest request
									  , MultipartRequest mRequest
														 ){
		
		    DidMoniterPicVO vo  = new DidMoniterPicVO();
		
			String meesage = "";
			String url = "";			
			String fileExt = null;
			int index = 0;
			String realFolder = propertiesService.getString("Globals.fileStorePath") +"/didpic/"  ;
			
			LOGGER.debug("realFolder:"+realFolder);

			try{
				
				
				String didId = request.getParameter("DID_ID") != null ? request.getParameter("DID_ID") : "";
				String didMac = request.getParameter("DID_MAC") != null ? request.getParameter("DID_MAC") : "";
				String msgSeq = request.getParameter("MSG_SEQ") != null ? request.getParameter("MSG_SEQ") : "";
				
				vo.setDidId(didId);
				vo.setDidMac(didMac);
				vo.setMsgSeq(msgSeq);
				
				String fileNm = uploadFile.uploadFileNm(mRequest.getFiles("DID_PICTURE"), realFolder) ;		
				
				LOGGER.debug("fileNm:"+fileNm);
				vo.setDidFileNm( fileNm );				
				int ret = didMoniterService.insertDidMoniterPicManage(vo);		
				if (ret > 0){
				     
					SendMsgInfo sendInfo =  new SendMsgInfo();
					sendInfo.setMsgSeq(vo.getMsgSeq());
					
					sendInfo.setMsgSeq(msgSeq);
					sendInfo.setDidId(didId);
					sendInfo.setDidMacAddress(didMac);
					
					if (ret > 0 && !vo.getDidFileNm().equals("")){
						sendInfo.setSendResult("Y");	
						sendInfo.setErrorMessage("");
					}else {
						sendInfo.setSendResult("N");
						if (vo.getDidFileNm().equals("")){
							sendInfo.setErrorMessage("File Update Error");	
						}else if (vo.getDidFileNm().equals("")){
							sendInfo.setErrorMessage("DataBase Update Error");
						}					
					}										
					int ret_update   =   sendService.updateSendMsgInfoManage(sendInfo);					
					meesage = "O";
				     //xml 전문 보내기
				}else {
					// 나중에 확인 하기 
					meesage = "0";	
				}
			}catch(Exception e){
				System.out.println(e.toString());
				meesage = "O";
			}finally {
				return meesage;
			}
		   
	}
	
	@RequestMapping(value="/backoffice/sub/equiManage/didFileLst.do")
	public String listDidPicture( HttpServletRequest request
			, MultipartRequest mRequest
			, @ModelAttribute("loginVO") LoginVO loginVO
            , @ModelAttribute("DidMoniterPicVO") DidMoniterPicVO vo 
			, BindingResult result
			, ModelMap model  )throws Exception{
			    
		return "/backoffice/sub/equiManage/didCapture";
	}
	@RequestMapping(value="/backoffice/sub/equiManage/didFileUpload.do")
	public String insertDidPicture( HttpServletRequest request
												, MultipartRequest mRequest
												, @ModelAttribute("loginVO") LoginVO loginVO
									            , @ModelAttribute("DidMoniterPicVO") DidMoniterPicVO vo 
												, BindingResult result
												, ModelMap model  )throws Exception{
		
		model.addAttribute("regist", vo);
		String meesage = "";
		String url = "";
		String originalFileName = null;
		String savedFileName = null;
		String fileExt = null;
		int index = 0;
		String realFolder = propertiesService.getString("Globals.fileStorePath") ;
		 
    	List<MultipartFile> files = mRequest.getFiles("didFile");
		for (MultipartFile mFile : files) {
			originalFileName = mFile.getOriginalFilename();
			
			index = originalFileName.lastIndexOf(".");
			fileExt = originalFileName.substring(index + 1);
			
			if (!originalFileName.equals("")){				
				savedFileName = UUID.randomUUID().toString();
				vo.setDidFileNm(savedFileName+ "."+fileExt);
				
				File f = new File(realFolder + savedFileName + "."+fileExt);
				mFile.transferTo(f);
			}
		}
		try{
			int ret = didMoniterService.insertDidMoniterPicManage(vo);		
			if (ret > 0){
			meesage = "sucess.common.insert";
			url = "redirect:/backoffice/sub/equiManage/didFileResult.do";
			}else {
				throw new Exception();	
			}
		}catch(Exception e){
			model.addAttribute("status", Globals.STATUS_FAIL);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.insert"));			
			url = "redirect:/backoffice/sub/equiManage/didFileResult.do";
		}finally {
			return url;
		}
		
		
		
		
	}
	
}
