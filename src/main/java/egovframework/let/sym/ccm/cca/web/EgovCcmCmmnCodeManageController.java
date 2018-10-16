package egovframework.let.sym.ccm.cca.web;

import egovframework.com.cmm.LoginVO;
import egovframework.let.sym.ccm.cca.service.CmmnCode;
import egovframework.let.sym.ccm.cca.service.CmmnCodeVO;
import egovframework.let.sym.ccm.cca.service.EgovCcmCmmnCodeManageService;
import egovframework.let.sym.ccm.ccc.service.EgovCcmCmmnClCodeManageService;



import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.Globals;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;


/**
 *
 * 공통코드에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를 정의한다
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 *   2011.08.31  JJY            경량환경 템플릿 커스터마이징버전 생성
 *
 * </pre>
 */
@Controller
public class EgovCcmCmmnCodeManageController {

	@Resource(name = "CmmnCodeManageService")
    private EgovCcmCmmnCodeManageService cmmnCodeManageService;

	@Resource(name = "CmmnClCodeManageService")
    private EgovCcmCmmnClCodeManageService cmmnClCodeManageService;

	
	
	@Resource(name="egovMessageSource")
	protected EgovMessageSource egovMessageSource;
	
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 공통코드를 삭제한다.
	 * @param loginVO
	 * @param cmmnCode
	 * @param model
	 * @return "forward:/sym/ccm/cca/EgovCcmCmmnCodeList.do"
	 * @throws Exception
	 */
    @RequestMapping(value="/backoffice/sub/basicManage/codeDelete.do")
	public String deleteCmmnCode (@ModelAttribute("loginVO") LoginVO loginVO
			, CmmnCode cmmnCode
			, HttpServletRequest request
			, BindingResult bindingResult			
			, ModelMap model
			) throws Exception {
    	int ret = cmmnCodeManageService.deleteCmmnCode(cmmnCode.getCodeId());
    	if (ret > 0){
			model.addAttribute("status", Globals.STATUS_SUCCESS);
			model.addAttribute("message",   egovMessageSource.getMessage("success.common.delete"));    		
    	}else {
    		throw new Exception();    		
    	}
    	
        return "forward:/backoffice/sub/basicManage/codeList.do";
	}

	
	
    @RequestMapping(value="/backoffice/sub/basicManage/codeList.do")
	public String selectCmmnCodeList (@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("searchVO") CmmnCodeVO searchVO
			, HttpServletRequest request
			, BindingResult bindingResult			
			, ModelMap model
			) throws Exception {
    	
    	model.addAttribute("regist", searchVO);
    	
    	/** EgovPropertyService.sample */
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        model.addAttribute("resultList", cmmnCodeManageService.selectCmmnCodeList(searchVO));

        int totCnt =cmmnCodeManageService.selectCmmnCodeListTotCnt(searchVO);
        
        model.addAttribute("totalCnt", totCnt);
        
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "/backoffice/sub/basicManage/codeList";
	}
   
    //ID 체크 
    @RequestMapping (value="/backoffice/sub/basicManage/codeIDCheck.do")
    @ResponseBody 
    public String selectIdCheck(HttpServletRequest request)throws Exception{
    	
    	String codeId = request.getParameter("CodeId") != null ? request.getParameter("CodeId") : "";
    	int idCheck = cmmnCodeManageService.selectIDCheck(codeId);    	
    	return Integer.toString(idCheck);
    }
  //ajax 로 값 보내기 
  	@RequestMapping ("/backoffice/sub/basicManage/codeDetail.do")
  	@ResponseBody 
  	public String selectGroupDetail(HttpServletRequest request) throws Exception{
  	    //	    
  		String codeId = request.getParameter("codeId") != null ? request.getParameter("codeId") : "";
  		
  	    
  		CmmnCode  cmmCode  =	(CmmnCode) cmmnCodeManageService.selectCmmnCodeDetail(codeId);
        String grInfo =   cmmCode.getCodeIdNm()+"/"+cmmCode.getUseAt();  	    
  	 	return grInfo;
  	}
  	@RequestMapping (value="/backoffice/sub/basicManage/codeUpdate.do")
  	@SuppressWarnings("finally")	
  	public String  updateCmmCode (@ModelAttribute("loginVO") LoginVO loginVO
  			                                          , @ModelAttribute("cmmnCode") CmmnCode vo
  			                                          , HttpServletRequest request
  			                          			      , BindingResult bindingResult						                          			
  			                          			      , ModelMap model
  			                          			  ) throws Exception {
  		model.addAttribute("regist", vo);
		String meesage = "";
		String url = "redirect:/backoffice/sub/basicManage/codeList.do";  		
		
  	    try{
  	    	int ret  = 0;
  	    	System.out.println("mode:"+ vo.getMode()  );
  	    	
			if (vo.getMode().equals("Ins")){
				
				ret = cmmnCodeManageService.insertCmmnCode(vo);
				meesage = "sucess.common.insert";
				url = "redirect:/backoffice/sub/basicManage/codeList.do";				
			}else {
				 ret = cmmnCodeManageService.updateCmmnCode(vo);
				 meesage = "sucess.common.update";
				 url = "redirect:/backoffice/sub/basicManage/codeList.do";
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
			url = "redirect:/backoffice/sub/basicManage/codeList.do";
  	    }
  	    finally{
  	    	return url;
  	    }
  		
  	}
   

}