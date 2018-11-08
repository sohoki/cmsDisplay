package egovframework.let.uat.uia.web;

import java.util.Map;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.let.sym.did.web.DidInfoManageController;
import egovframework.let.sym.log.clg.service.EgovLoginLogService;
import egovframework.let.sym.log.clg.service.LoginLog;
import egovframework.let.uat.uia.service.EgovLoginService;
import egovframework.let.utl.sim.service.EgovClntInfo;
import egovframework.rte.fdl.cmmn.trace.LeaveaTrace;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 일반 로그인, 인증서 로그인을 처리하는 컨트롤러 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.06
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.03.06  박지욱          최초 생성
 *  2011.08.31  JJY            경량환경 템플릿 커스터마이징버전 생성
 *
 *  </pre>
 */
@Controller
public class EgovLoginController {

	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovLoginController.class);
	
	
    /** EgovLoginService */
	@Resource(name = "loginService")
    private EgovLoginService loginService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;


	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** TRACE */
    @Resource(name="leaveaTrace")
    LeaveaTrace leaveaTrace;
    
	@Resource(name="EgovLoginLogService")
	private EgovLoginLogService loginLogService;    

	/**
	 * 로그인 화면으로 들어간다
	 * @param vo - 로그인후 이동할 URL이 담긴 LoginVO
	 * @return 로그인 페이지
	 * @exception Exception
	 */
    @RequestMapping(value="/backoffice/login.do")
	public String loginUsrView(@ModelAttribute("loginVO") LoginVO loginVO,
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model)
			throws Exception {
    	
    	return "/backoffice/login";
	}

    /**
	 * 일반(스프링 시큐리티) 로그인을 처리한다
	 * @param vo - 아이디, 비밀번호가 담긴 LoginVO
	 * @param request - 세션처리를 위한 HttpServletRequest
	 * @return result - 로그인결과(세션정보)
	 * @exception Exception
	 */    
    @RequestMapping(value="/backoffice/sub/SecurityLogin.do")
    public String actionSecurityLogin(@ModelAttribute("loginVO") LoginVO loginVO, 
    		                                           HttpServletResponse response,
						    		                   HttpServletRequest request,
						    		                   ModelMap model)
                                                      throws Exception {

    	// 접속IP
    	String userIp = EgovClntInfo.getClntIP(request);

        

    	// 1. 일반 로그인 처리
        LoginVO resultVO = loginService.actionLogin(loginVO);        
        if (resultVO != null && resultVO.getMberId() != null && !resultVO.getMberId().equals("") ) {
        	
            // 2. spring security 연동
        	
        	request.getSession().setAttribute("LoginVO", resultVO);
        	
        	UsernamePasswordAuthenticationFilter springSecurity = null;

        	ApplicationContext act = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
        	
        	@SuppressWarnings("rawtypes")
        	Map beans = act.getBeansOfType(UsernamePasswordAuthenticationFilter.class);
        	
        	
        	if (beans.size() > 0) {
        		springSecurity = (UsernamePasswordAuthenticationFilter)beans.values().toArray()[0];
        	} else {
        		throw new IllegalStateException("No AuthenticationProcessingFilter");
        	}
        	

 


        	springSecurity.setContinueChainBeforeSuccessfulAuthentication(false);	// false 이면 chain 처리 되지 않음.. (filter가 아닌 경우 false로...)

        	springSecurity.doFilter(new RequestWrapperForSecurity(request, resultVO.getMberNm() + resultVO.getMberId() , resultVO.getMberId()), response, null);
        	
        	Authentication authentication = new UsernamePasswordAuthenticationToken(resultVO.getMberId(), "USER_PASSWORD");        	
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
            HttpSession session = request.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);	
            
        	        	
        	model.addAttribute("message", "login_ok");
        	return "forward:/backoffice/sub/actionMain.do";	// 성공 시 페이지.. (redirect 불가)

        } else {
        	model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "backoffice/login";
        }
    }

    /**
	 * 로그인 후 메인화면으로 들어간다
	 * @param
	 * @return 로그인 페이지
	 * @exception Exception
	 */
    @RequestMapping(value="/backoffice/sub/actionMain.do")
	public String actionMain(ModelMap model)  {
    	try{
    	        // 1. Spring Security 사용자권한 처리
		    	
		    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		    	
		    	if(!isAuthenticated) {
		    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
		        	return "/backoffice/login";
		    	}
    	
		// 2. 메인 페이지 이동
    	} catch(Exception e){
    		LOGGER.debug("login Error:" + e.toString());
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "/backoffice/login";
    	}
    	LOGGER.info("login OK:");
    	return "forward:/backoffice/sub/equiManage/integrate.do"; // didList에서 변경
	}

    /**
	 * 로그아웃한다.
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/backoffice/actionLogout.do")
	public String actionLogout(HttpServletRequest request, ModelMap model) throws Exception {
    	request.getSession().setAttribute("LoginVO", null);
    	
    	return "redirect:/backoffice/login.do";
    }
}

class RequestWrapperForSecurity extends HttpServletRequestWrapper {
	private String username = null;
	private String password = null;

	public RequestWrapperForSecurity(HttpServletRequest request, String username, String password) {
		super(request);

		//System.out.println("username:" + username);
		//System.out.println("password:" + password);		
		this.username = username;
		this.password = password;
		
		
		
	}

	@Override
	public String getRequestURI() {
		return ((HttpServletRequest)super.getRequest()).getContextPath() + "/j_spring_security_check";
	}

	@Override
	public String getParameter(String name) {
        if (name.equals("j_username")) {
        	return username;
        }

        if (name.equals("j_password")) {
        	return password;
        }

        return super.getParameter(name);
    }
}