package egovframework.let.uat.uia.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import egovframework.com.cmm.LoginVO;

import egovframework.rte.fdl.security.userdetails.EgovUserDetails;
import egovframework.rte.fdl.security.userdetails.jdbc.EgovUsersByUsernameMapping;

import javax.sql.DataSource;

/**
 * mapRow 결과를 사용자 EgovUserDetails Object 에 정의한다.
 *
 * @author ByungHun Woo
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    -------------    ----------------------
 *   2009.03.10  ByungHun Woo    최초 생성
 *   2009.03.20  이문준          UPDATE
 *   2011.08.31  JJY            경량환경 템플릿 커스터마이징버전 생성
 *
 * </pre>
 */

public class EgovSessionMapping extends EgovUsersByUsernameMapping  {

	/**
	 * 사용자정보를 테이블에서 조회하여 EgovUsersByUsernameMapping 에 매핑한다.
	 * @param ds DataSource
	 * @param usersByUsernameQuery String
	 */
	public EgovSessionMapping(DataSource ds, String usersByUsernameQuery) {
        super(ds, usersByUsernameQuery);
    }

	/**
	 * mapRow Override
	 * @param rs ResultSet 결과
	 * @param rownum row num
	 * @return Object EgovUserDetails
	 * @exception SQLException
	 */
	@Override
	protected EgovUserDetails mapRow(ResultSet rs, int rownum) throws SQLException {
    	//logger.debug("## EgovUsersByUsernameMapping mapRow ##");
    	
        String strUserId    = rs.getString("MBER_ID");        
        String strPassWord  = rs.getString("PASSWORD");        
        boolean strEnabled  = true;
        String strUserNm    = rs.getString("MBER_NM");        
        String strUserSe    = rs.getString("MBTLNUM");        
        String strUserEmail = rs.getString("MBER_EMAIL_ADRES");        
        String strOrgnztId  = rs.getString("GROUP_ID");        
        String strRoleCode = rs.getString("ROLE_CODE");        
        String strCenterId = rs.getString("CENTER_ID");        
        String strAuthorCode = rs.getString("AUTHOR_CODE");
        String strParentGroupId = rs.getString("PARENT_GROUP_ID");
        // 세션 항목 설정
        LoginVO loginVO = new LoginVO();
        loginVO.setMberId(strUserId);
        loginVO.setPassword(strPassWord);
        loginVO.setMberNm(strUserNm);
        loginVO.setMbtlNum(strUserSe);
        loginVO.setMberEmailAddress(strUserEmail);
        loginVO.setGroupId(strOrgnztId);
        loginVO.setAuthority(strAuthorCode);
        loginVO.setUserid(strUserId);
        
        
        // 추가 설정 항목 
        loginVO.setRoleCode(strRoleCode);
        loginVO.setAuthorCode(strAuthorCode);
        loginVO.setCenterId(strCenterId);
        loginVO.setParentGroupId(strParentGroupId);
        

        return new EgovUserDetails(strUserId, strPassWord, strEnabled, loginVO);
    }
}
