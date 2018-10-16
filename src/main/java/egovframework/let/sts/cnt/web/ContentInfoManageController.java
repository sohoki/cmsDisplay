package egovframework.let.sts.cnt.web;


import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;

import egovframework.com.cmm.service.Globals;
import egovframework.let.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;



import egovframework.let.sts.cnt.service.ContentInfo;
import egovframework.let.sts.cnt.service.ContentInfoVO;
import egovframework.let.sts.cnt.service.ContentInfoManageService;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;



@Controller
public class ContentInfoManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContentInfoManageController.class);
	
	
	@Resource(name="ContentInfoManageService")
	private ContentInfoManageService contentService;
	
	@Resource(name="CmmnDetailCodeManageService")
    private EgovCcmCmmnDetailCodeManageService cmmnDetailCodeManageService;
	
	@Resource(name="egovMessageSource")
	protected EgovMessageSource egovMessageSource;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;
	
	@RequestMapping(value="/backoffice/sub/conManage/conNextSeq.do")
	public ModelAndView selectNextCont( @ModelAttribute("loginVO") LoginVO loginVO ,
			                                                   HttpServletRequest request  )throws Exception {
		
		ModelAndView model = new ModelAndView("jsonView");
		String conNextSeq  = request.getParameter("conNextSeq") != null ? request.getParameter("conNextSeq") : "";
        List<ContentInfo> contentNextCombo = contentService.selectNextCombo(conNextSeq);
        
        model.addObject("conNextSeqCombo", contentNextCombo);
        
		return model;
	}
	
	@RequestMapping("/backoffice/sub/conManage/conList.do")
	public String selectContentlLst(@ModelAttribute("loginVO") LoginVO loginVO
				, @ModelAttribute("searchVO") ContentInfoVO searchVO
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

		       model.addAttribute("resultList",   contentService.selectContentInfoManageListByPagination(searchVO) );
		       
		       int totCnt = contentService.selectContentInfoManageListTotCnt_S(searchVO);       
			   paginationInfo.setTotalRecordCount(totCnt);
		       model.addAttribute("paginationInfo", paginationInfo);
		       model.addAttribute("totalCnt", totCnt);		       
		       model.addAttribute("regist", searchVO);		       
		      return "/backoffice/sub/conManage/conList";
	}
	@RequestMapping("/backoffice/sub/conManage/conDetail.do")
	public String selectConDetail(@ModelAttribute("loginVO") LoginVO loginVO
			, ContentInfoVO vo
			, HttpServletRequest request			
			, BindingResult bindingResult			
			, ModelMap model) throws Exception{
		
           model.addAttribute("selectConType", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT008"));
           model.addAttribute("selectPlayType", cmmnDetailCodeManageService.selectCmmnDetailCombo("EMT009"));
		   
		   model.addAttribute("regist", vo);
		   
		   if (!vo.getMode().equals("Ins")){
			    vo = contentService.selectContentInfoManageDetail(vo.getConSeq());
			    if (vo.getConPlayType().equals("PLAY02") ){
			    	model.addAttribute("selectNextCon", contentService.selectNextCombo("") );    				    	
			    }
   		        model.addAttribute("regist",  vo );
   		        
   		     
		   }
			return "/backoffice/sub/conManage/conDetail";					
	}
	@RequestMapping (value="/backoffice/sub/conManage/conDel.do")
	public String selectConDelete (@ModelAttribute("loginVO") LoginVO loginVO
			, ContentInfoVO vo
			, HttpServletRequest request
			, BindingResult bindingResult			
			, ModelMap model) throws Exception{
		
        model.addAttribute("regist",vo );
		
		if (StringUtils.equals(vo.getConSeq(), Globals.REGITER_SYSTEM)){
			model.addAttribute("status", Globals.STATUS_FAIL);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.delete.system"));			
			return "forward:/backoffice/sub/conManage/conList.do";
		}
				
		try{
		      int ret = 	contentService.deleteContentInfoManage(vo.getConSeq());		      
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
		return "forward:/backoffice/sub/conManage/conList.do";
	}
	@RequestMapping(value="/backoffice/sub/conManage/conUpdate.do")
	public String selectConUpdate( HttpServletRequest request
													, MultipartRequest mRequest
													, @ModelAttribute("loginVO") LoginVO loginVO
				                                    , @ModelAttribute("ContentInfoVO") ContentInfoVO vo 
													, BindingResult result
													, ModelMap model)throws Exception{
		
		model.addAttribute("regist", vo);
		String meesage = "";
		String url = "redirect:/backoffice/sub/conManage/conList.do";
		
		String originalFileName = null;
		String savedFileName = null;
		String fileExt = null;
		int index = 0;
		String realFolder = propertiesService.getString("Globals.fileStorePath") ;
		 
    	List<MultipartFile> files = mRequest.getFiles("conFile");
		for (MultipartFile mFile : files) {
			originalFileName = mFile.getOriginalFilename();
			
			index = originalFileName.lastIndexOf(".");
			fileExt = originalFileName.substring(index + 1);
			
			if (!originalFileName.equals("")){				
				savedFileName = UUID.randomUUID().toString();
				vo.setConFile(savedFileName+ "."+fileExt);
				
				File f = new File(realFolder + savedFileName + "."+fileExt);
				mFile.transferTo(f);
			}
		}
		List<MultipartFile> Mapfiles = mRequest.getFiles("conThumbnail");
		for (MultipartFile mFile : Mapfiles) {
			originalFileName = mFile.getOriginalFilename();
			
			index = originalFileName.lastIndexOf(".");
			fileExt = originalFileName.substring(index + 1);
			
			if (!originalFileName.equals("")){				
				savedFileName = UUID.randomUUID().toString();
				vo.setConThumbnail(savedFileName+ "."+fileExt);  
				File f = new File(realFolder + savedFileName +"."+ fileExt);
				mFile.transferTo(f);
			}
		}    			
		     
		
		try{			
			
			int ret  = 0;
			if (vo.getMode().equals("Ins")){
				ret = contentService.insertContentInfoManage(vo);
				meesage = "sucess.common.insert";
				url = "redirect:/backoffice/sub/conManage/conDetail.do";				
			}else {
				 ret = contentService.updateContentInfoManage(vo);
				 meesage = "sucess.common.update";
				 url = "redirect:/backoffice/sub/conManage/conDetail.do";
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
			url = "redirect:/backoffice/sub/conManage/conList.do";
		}finally {
			
		}					
		return url;
	
	}
	
}
