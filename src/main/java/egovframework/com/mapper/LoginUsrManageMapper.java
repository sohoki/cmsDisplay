package egovframework.com.mapper;

import java.util.List;

import egovframework.com.cmm.LoginVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("LoginUsrManageMapper")
public interface LoginUsrManageMapper {

	
	public LoginVO selectactionLogin(LoginVO vo);
	
}
