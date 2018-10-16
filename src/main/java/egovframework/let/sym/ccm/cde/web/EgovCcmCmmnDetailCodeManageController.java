package egovframework.let.sym.ccm.cde.web;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.Globals;
import egovframework.let.sym.ccm.cde.service.CmmnDetailCode;
import egovframework.let.sym.ccm.cca.service.CmmnCode;
import egovframework.let.sym.ccm.cca.service.CmmnCodeVO;
import egovframework.let.sym.ccm.cca.service.EgovCcmCmmnCodeManageService;
import egovframework.let.sym.ccm.ccc.service.CmmnClCodeVO;
import egovframework.let.sym.ccm.ccc.service.EgovCcmCmmnClCodeManageService;
import egovframework.let.sym.ccm.cde.service.CmmnDetailCodeVO;
import egovframework.let.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

/**
 *
 * 공통상세코드에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를 정의한다
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
public class EgovCcmCmmnDetailCodeManageController {

	@Resource(name = "CmmnDetailCodeManageService")
    private EgovCcmCmmnDetailCodeManageService cmmnDetailCodeManageService;

	@Resource(name = "CmmnClCodeManageService")
    private EgovCcmCmmnClCodeManageService cmmnClCodeManageService;

	@Resource(name = "CmmnCodeManageService")
    private EgovCcmCmmnCodeManageService cmmnCodeManageService;

	@Resource(name="egovMessageSource")
	protected EgovMessageSource egovMessageSource;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 공통상세코드를 삭제한다.
	 * @param loginVO
	 * @param cmmnDetailCode
	 * @param model
	 * @return "forward:/sym/ccm/cde/EgovCcmCmmnDetailCodeList.do"
	 * @throws Exception
	 */
    @RequestMapping(value="/backoffice/sub/basicManage/codeDetailCodeDelete.do")
	public ModelAndView deleteCmmnDetailCode (@ModelAttribute("loginVO") LoginVO loginVO,
			                                                                HttpServletRequest request			                                                                
                                                                  			) throws Exception {
    	
    	
    	ModelAndView model = new 	ModelAndView("jsonView");
		String code = request.getParameter("code") != null ? request.getParameter("code") : "";
		String codeId = request.getParameter("codeId") != null ? request.getParameter("codeId") : "";
		
		int rnt = cmmnDetailCodeManageService.deleteCmmnDetailCode(code);
		if (rnt>0){
	        List<CmmnDetailCode> codeDetail = (List<CmmnDetailCode>) cmmnDetailCodeManageService.selectCmmnDetailCodeList(codeId);        
	        model.addObject("cmDetailLst", codeDetail);        
		}		
        int totCnt = cmmnDetailCodeManageService.selectCmmnDetailCodeListTotCnt(codeId);
    	
    	return model;
	}

		/**
	 * 공통상세코드 상세항목을 조회한다.
	 * @param loginVO
	 * @param cmmnDetailCode
	 * @param model
	 * @return "cmm/sym/ccm/EgovCcmCmmnDetailCodeDetail"
	 * @throws Exception
	 */
	@RequestMapping(value="/backoffice/sub/basicManage/EgovCcmCmmnDetailCodeDetail.do")	
 	public ModelMap selectCmmnDetailCodeDetail (@ModelAttribute("loginVO") LoginVO loginVO
 			, CmmnDetailCode cmmnDetailCode
 			,	ModelMap model
 			)	throws Exception {
    	CmmnDetailCode vo = cmmnDetailCodeManageService.selectCmmnDetailCodeDetail(cmmnDetailCode);
		model.addAttribute("result", vo);
		return model;
	}
	
	
	

    /**
	 * 공통상세코드 목록을 조회한다.
     * @param loginVO
     * @param searchVO
     * @param model 
     * @throws Exception
     */
    @RequestMapping(value="/backoffice/sub/basicManage/CmmnDetailCodeList.do")
	public ModelAndView selectCmmnDetailCodeList (HttpServletRequest request
			) throws Exception {
    	
    	ModelAndView model = new 	ModelAndView("jsonView");
    	String codeId = request.getParameter("codeId") != null ? request.getParameter("codeId") : "";
        List<CmmnDetailCode> codeDetail = (List<CmmnDetailCode>) cmmnDetailCodeManageService.selectCmmnDetailCodeList(codeId);
        
        
        model.addObject("cmDetailLst", codeDetail);
        
        int totCnt = cmmnDetailCodeManageService.selectCmmnDetailCodeListTotCnt(codeId);
	
        return model;
	}
    @RequestMapping (value="/backoffice/sub/basicManage/codeDetailIDCheck.do")
    @ResponseBody
    public String selectDetailIdCheck(HttpServletRequest request)throws Exception{
    	
    	String code = request.getParameter("code") != null ? request.getParameter("code") : "";
    	int idCheck = cmmnDetailCodeManageService.selectCmmnDetailCodeIdCheck(code)    ;	
    	return Integer.toString(idCheck);
    }
    

    @RequestMapping(value="/backoffice/sub/basicManage/CodeDetailUpdate.do")
	public String updateCmmnDetailCode (@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("cmmnDetailCode") CmmnDetailCode vo
			, BindingResult bindingResult			
			, ModelMap model
			) throws Exception {
    	int ret  = 0;
    	if ( vo.getMode().equals("Ins")) {
    		 ret = cmmnDetailCodeManageService.insertCmmnDetailCode(vo);          
    	} else {
    		 ret = cmmnDetailCodeManageService.updateCmmnDetailCode(vo);
    		
    	}
		if (ret > 0){
			model.addAttribute("status", "codeDInsertOK");
	    	model.addAttribute("codeId", vo.getCodeId() );	
		}else {
			model.addAttribute("status", "codeDInsertFase");
	    	model.addAttribute("codeId", vo.getCodeId() );
		}
    	return "forward:/backoffice/sub/basicManage/codeList.do";
    }

}