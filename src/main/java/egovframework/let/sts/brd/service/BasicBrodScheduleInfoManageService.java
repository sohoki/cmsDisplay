package egovframework.let.sts.brd.service;

import java.util.List;

public interface BasicBrodScheduleInfoManageService {

    List<BasicBrodScheduleInfoVO> selectBasciBrodScheduleLst(BasicBrodScheduleInfoVO searchVO) throws Exception;
	
    List<BasicBrodScheduleInfoVO> selectBasicBrodScheduleCheckList(String centerGubun)throws Exception;
    
    String selectBasicBrodContentlDownCheck(BasicBrodScheduleInfoVO searchVO ) throws Exception;
     
    int selectBasciBrodScheduleLstCnt(BasicBrodScheduleInfoVO searchVO)throws Exception;
    
	int insertBasciBrodSchedule(BasicBrodScheduleInfo vo) throws Exception;
	
	int insertBasciBrodScheduleDistribute (String basicCode) throws Exception;
	
	int updateBasicBrodScheduleCenter(BasicBrodScheduleInfo vo) throws Exception;
	
	int updateBasicBrodSchedule(BasicBrodScheduleInfo vo) throws Exception;
	
	int updateBasicCodeCenterUpdate(BasicBrodScheduleInfoVO vo)throws Exception;
	
	int updateBasicCodeCenterReset(String basicCode)throws Exception;
	
	int updateBasicBrodScheduleCenterE(BasicBrodScheduleInfoVO vo) throws Exception;
	
	int updateBasicBrodScheduleCenterStateChange(BasicBrodScheduleInfoVO vo) throws Exception;
	
	int updateBasicBrodScheduleCenterDownCheck(BasicBrodScheduleInfoVO vo);
	
	int deleteBasicBrodSchedule(String basicScheduleSeq) throws Exception;
	
}
