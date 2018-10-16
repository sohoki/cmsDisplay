package egovframework.com.mapper;


import java.util.List;

import egovframework.let.sym.log.clg.service.LoginLog;
import egovframework.let.sym.log.clg.service.LoginLogVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("LoginLogManagerMapper")
public interface LoginLogManagerMapper {

	//public List<LoginLogVO> selectLoginLogManageListByPagination(LoginLogVO  searchVO);
	
	//public int selectLoginLogManageListTotCnt_S (LoginLogVO  searchVO);
	
	public LoginLogVO selectLogDetail(String logId);
	
	public int InsertLoginLog(LoginLog vo);
}
