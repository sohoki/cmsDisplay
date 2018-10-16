package egovframework.let.sym.log.clg.service.impl;


import java.util.List;

import egovframework.let.sym.log.clg.service.EgovLoginLogService;
import egovframework.let.sym.log.clg.service.LoginLog;
import egovframework.let.sym.log.clg.service.LoginLogVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.mapper.LoginLogManagerMapper;

/**
 * 접속로그 관리를 위한 서비스 구현 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.03.11
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.11  이삼섭          최초 생성
 *   2011.07.01  이기하          패키지 분리(stm.log -> sym.log.clg)
 *   2011.08.31  JJY            경량환경 템플릿 커스터마이징버전 생성 
 *
 * </pre>
 */
@Service("EgovLoginLogService")
public class EgovLoginLogServiceImpl extends EgovAbstractServiceImpl implements EgovLoginLogService {

	@Resource(name="LoginLogManagerMapper")
	private LoginLogManagerMapper  loginLog;	
	
    /** ID Generation */    
	@Resource(name="egovLoginLogIdGnrService")
	private EgovIdGnrService egovLoginLogIdGnrService;

	/**
	 * 접속로그를 기록한다.
	 * 
	 * @param LoginLog
	 */
	public int  logInsertLoginLog(LoginLog  vo) throws Exception {
		// TODO Auto-generated method stub
		String logId = egovLoginLogIdGnrService.getNextStringId();
		vo.setLogId(logId);		
		return loginLog.InsertLoginLog(vo);    	

	}



	/**
	 * 접속로그 목록을 조회한다.
	 * 
	 * @param LoginLog
	 */
	public List<LoginLogVO> selectLoginLogInf(LoginLogVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		 	 
		//return loginLog.selectLoginLogManageListByPagination(searchVO) ;
		return null;
	}

	
	@Override
	public int selectLoginLogManageListTotCnt_S(LoginLogVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		//return loginLog.selectLoginLogManageListTotCnt_S(searchVO);
		return 0;
	}

	@Override
	public LoginLog selectLoginLog(String  logId) throws Exception {
		// TODO Auto-generated method stub
		return loginLog.selectLogDetail(logId);
	}

}
