package egovframework.com.mapper;


import java.util.List;

import egovframework.let.sts.brd.service.BasicBrodScheduleInfo;
import egovframework.let.sts.brd.service.BasicBrodScheduleInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("BasicBrodScheduleManagerMapper")
public interface BasicBrodScheduleManagerMapper {




	public List<BasicBrodScheduleInfoVO> selectBasciBrodScheduleLst(BasicBrodScheduleInfoVO searchVO);
	
	public int selectBasciBrodScheduleLstCnt(BasicBrodScheduleInfoVO searchVO);
	
	public List<BasicBrodScheduleInfoVO> selectBasicBrodScheduleCheckList(String centerGubun); 
	
	public String selectBasicBrodContentlDownCheck(BasicBrodScheduleInfoVO searchVO );
	
	public int insertBasciBrodSchedule(BasicBrodScheduleInfo vo);
	
	public int insertBasciBrodScheduleDistribute (String basicCode);
	
	public int updateBasicBrodScheduleCenter(BasicBrodScheduleInfo vo);
	
	public int updateBasicBrodSchedule(BasicBrodScheduleInfo vo);
	
	public int updateBasicCodeCenterUpdate(BasicBrodScheduleInfoVO vo);
	
	public int updateBasicBrodScheduleCenterE(BasicBrodScheduleInfoVO vo);
	
	public int updateBasicCodeCenterReset(String basicCode);
	
	public int updateBasicBrodScheduleCenterDownCheck(BasicBrodScheduleInfoVO vo);
	
	public int updateBasicBrodScheduleCenterStateChange(BasicBrodScheduleInfoVO vo);
	
	public int deleteBasicBrodSchedule(String basicScheduleSeq);

	
	
}
