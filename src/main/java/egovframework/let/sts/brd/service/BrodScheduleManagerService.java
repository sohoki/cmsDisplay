package egovframework.let.sts.brd.service;

import java.util.List;

public interface BrodScheduleManagerService {
	
	List<BrodScheduleInfoVO> selectBrodRigthLst (BrodScheduleInfoVO searchVO) throws Exception;
	
	List<BrodScheduleInfoVO> selectBrodScheduleStatusLst (BrodScheduleInfoVO searchVO) throws Exception;
	
	List<BrodScheduleInfoVO>  selectBrodScheduleUpdateChanage (String bordCode) throws Exception;
	
	int selectBrodScheduleStatusPageCnt(BrodScheduleInfoVO searchVO)throws Exception;
	
	int selectBrodScheduleCnt(BrodScheduleInfo searchVO) throws Exception;
	
	int selectBrodScheduleCenterCnt(String centerId)throws Exception;
	
	String selectBrodScheduleAnniScheduleSeqDay(BrodScheduleInfo searchVO) throws Exception;
	
	int selectBordScheduleCount(BrodScheduleInfo vo) throws Exception;
	
	BrodScheduleInfo selectBordScheduleCode(BrodScheduleInfo vo) throws Exception;
	
	BrodScheduleInfo selectBordScheduleCodeRedown(BrodScheduleInfo vo) throws Exception;
	//신규 부분 
	BrodScheduleInfo selectBordScheduleCodeNew(BrodScheduleInfo vo) throws Exception;
		
	BrodScheduleInfo selectBordScheduleCodeRedownNew(BrodScheduleInfo vo) throws Exception;
	//신규 부분 	
	List<BrodScheduleInfo> selectBrodScheduleCreateCheckList (BrodScheduleInfo searchVO) throws Exception;
	
	int insertBrodSchedule(BrodScheduleInfo vo) throws Exception;
	
	int updateBrodSchedule(BrodScheduleInfo vo) throws Exception;
		
	int updateCenterSchedule(BrodScheduleInfo vo) throws Exception;
	
	int updateBrodScheduleReset(BrodScheduleInfo vo)throws Exception;
	
	int updateBrodScheduleCenter(BrodScheduleInfo vo) throws Exception;
	
	int updateBrodDidCenterID(BrodScheduleInfo vo) throws Exception;
	
	int updateBrodScheduleCenterBrod(BrodScheduleInfo vo) throws Exception;
	
	int updateBrodScheduleCenterNotUse(String centerId) throws Exception;
	
	int deleteBrodScheduleSeq(String scheduleSeq) throws Exception;;
	
	int deleteBrodSchedule(BrodScheduleInfo vo) throws Exception;
	
	int deleteBrodScheduleOther(BrodScheduleInfo vo) throws Exception;
	
	int deleteBrodScheduleAll(String brodCode)throws Exception;
	
	int selectBrodScheduleStateCnt(BrodScheduleInfo vo) throws Exception;

	int deleteBrodScheduleState(BrodScheduleInfo vo)throws Exception;

}
