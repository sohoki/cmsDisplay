package egovframework.let.uat.uia.service.impl;

import egovframework.com.cmm.LoginVO;
import egovframework.let.uat.uia.service.EgovLoginService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import egovframework.com.mapper.LoginUsrManageMapper;





@Service("loginService")
public class EgovLoginServiceImpl extends EgovAbstractServiceImpl implements EgovLoginService {

	@Resource(name="LoginUsrManageMapper")
    private LoginUsrManageMapper loginMapper;

    ///** EgovSndngMailRegistService */
	//@Resource(name = "sndngMailRegistService")
    //private EgovSndngMailRegistService sndngMailRegistService;

    /**
	 * 일반 로그인을 처리한다
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
    @Override
	public LoginVO actionLogin(LoginVO vo) throws Exception {

    	// 1. 입력한 비밀번호를 암호화한다.
    	//String enpassword = EgovFileScrty.encryptPassword(vo.getPassword(), vo.getMberId());
    	//vo.setPassword(enpassword);

    	// 2. 아이디와 암호화된 비밀번호가 DB와 일치하는지 확인한다.
    	LoginVO loginVO = loginMapper.selectactionLogin(vo); 
    			
    	// 3. 결과를 리턴한다.
    	if (loginVO != null && !loginVO.getMberId().equals("") && !loginVO.getPassword().equals("")) {    		
    		return loginVO;
    	} else {    		
    		loginVO = new LoginVO();
    	}

    	return loginVO;
    }
   
}
