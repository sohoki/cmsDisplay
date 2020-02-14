package egovframework.let.sym.did.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.let.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;
import egovframework.let.sym.did.service.DidInfoVO;
import egovframework.let.sym.did.service.ProgramInfoManageService;
import egovframework.let.sym.did.service.ProgramInfoVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
 

@Controller
public class ProgramIngoManageController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProgramIngoManageController.class);
	
	
	@Resource(name="egovMessageSource")
	protected EgovMessageSource egovMessageSource;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Autowired
	private DefaultBeanValidator beanValidator;
    
    
    @Resource(name="CmmnDetailCodeManageService")
    private EgovCcmCmmnDetailCodeManageService cmmnDetailCodeManageService;
    
    
    @Resource(name="ProgramInfoManageService")
    private ProgramInfoManageService programService;
    
	
    @RequestMapping("/backoffice/sub/equiManage/programList.do")
	public String selectProgaramList ( @ModelAttribute("loginVO") LoginVO loginVO
								, @ModelAttribute("searchVO") ProgramInfoVO searchVO
								, HttpServletRequest request
								, BindingResult result
								, ModelMap model) throws Exception {
							
		String url = "/backoffice/sub/equiManage/programList";
		
			
		try{
		   LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");	
           if (user == null ){
        	    LOGGER.debug("로그인 기록 없음");    	
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
			 List<ProgramInfoVO> programList = programService.selectProgramInfoManagerInfoManageListByPagination(searchVO);
	         int totCnt =  programList.size() > 0 ? programList.size() : 0;
	           
	         model.addAttribute("regist", searchVO);
			 model.addAttribute("resultList",   programList );
			 
			 paginationInfo.setTotalRecordCount(totCnt);
			 model.addAttribute("paginationInfo", paginationInfo);
			 model.addAttribute("totalCnt", totCnt);
				 
			
		}catch(Exception e){
		   LOGGER.debug("selectProgaramList error:" + e.toString());	
		}
	    return 	url;	
			
	}
    @RequestMapping("/backoffice/sub/equiManage/programView.do")
    public ModelAndView selectProgaramView (@ModelAttribute("loginVO") LoginVO loginVO
											, @RequestBody ProgramInfoVO vo
											, HttpServletRequest request
											, BindingResult result
											, ModelMap model) throws Exception {
    	
    	
    	ModelAndView mv = new ModelAndView("jsonView");
    	try{
    		
    		LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");	
            if (user == null ){
         	   mv.addObject("status", "LOGIN FAIL");
 		    }	
    		
    		mv.addObject("status", "SUCCESS");
    		mv.addObject("proInfo", programService.selectProgramDetailInfoManagerInfo(vo.getProgCode()) );
    		
    		
    	}catch(Exception e){
    		mv.addObject("status", "FAIL");
    		mv.addObject("message", "작업 도중 애러가  발생 하였습니다." + e.toString());
    	}
    	
    	return mv;
    }
    @RequestMapping("/backoffice/sub/equiManage/programUpdate.do")
    public ModelAndView  updateProgaram (@ModelAttribute("loginVO") LoginVO loginVO
											, @RequestBody ProgramInfoVO vo
											, HttpServletRequest request
											, BindingResult result
											, ModelMap model) throws Exception {
    	
    	ModelAndView mv = new ModelAndView("jsonView");
    	try{
    		LoginVO user = (LoginVO) request.getSession().getAttribute("LoginVO");	
            if (user == null ){
         	   mv.addObject("status", "LOGIN FAIL");
 		    }	
            
            int ret = programService.updateProgramInfo(vo);
            if (ret > 0){
            	mv.addObject("status", "SUCCESS");
            	mv.addObject("proInfo", programService.selectProgramInfoManagerInfoManageListByPagination(vo));
            }else {
            	mv.addObject("status", "FAIL");
            }
    	}catch(Exception e){
    		LOGGER.debug("updateProgaram  ERROR:" + e.toString());
    		mv.addObject("status", "FAIL");
    		mv.addObject("message", "작업 도중 애러가  발생 하였습니다." + e.toString());
    	}
    	
    	return mv;
    }
}
