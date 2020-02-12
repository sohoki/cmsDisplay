package egovframework.let.sts.brd.service.impl;
import java.util.List;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.let.sts.brd.service.BasicBrodScheduleInfo;
import egovframework.let.sts.brd.service.BasicBrodScheduleInfoVO;
import egovframework.let.sts.brd.service.BasicBrodScheduleInfoManageService;
import egovframework.com.mapper.BasicBrodScheduleManagerMapper;

@Service("BasicBrodScheduleInfoManageService")
public class BasicBrodScheduleInfoManageServiceImpl extends EgovAbstractServiceImpl implements  BasicBrodScheduleInfoManageService{
	
	
	
	@Resource(name="BasicBrodScheduleManagerMapper")
	private BasicBrodScheduleManagerMapper scheduleMapper;

	@Override
	public List<BasicBrodScheduleInfoVO> selectBasciBrodScheduleLst(
			BasicBrodScheduleInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return scheduleMapper.selectBasciBrodScheduleLst(searchVO);
	}

	@Override
	public int insertBasciBrodSchedule(BasicBrodScheduleInfo vo)
			throws Exception {
		// TODO Auto-generated method stub
		return scheduleMapper.insertBasciBrodSchedule(vo);
	}

	@Override
	public int updateBasicBrodScheduleCenter(BasicBrodScheduleInfo vo)
			throws Exception {
		// TODO Auto-generated method stub
		return scheduleMapper.updateBasicBrodScheduleCenter(vo);
	}

	@Override
	public int updateBasicBrodSchedule(BasicBrodScheduleInfo vo)
			throws Exception {
		// TODO Auto-generated method stub
		return scheduleMapper.updateBasicBrodSchedule(vo);
	}

	@Override
	public int deleteBasicBrodScheduleCenter(BasicBrodScheduleInfo vo)
			throws Exception {
		// TODO Auto-generated method stub
		return scheduleMapper.deleteBasicBrodScheduleCenter(vo);
	}

	@Override
	public int deleteBasicBrodSchedule(BasicBrodScheduleInfoVO vo)
			throws Exception {
		// TODO Auto-generated method stub
		return scheduleMapper.deleteBasicBrodSchedule(vo);
	}
	
	@Override
	public int deleteBasicBrodScheduleOther(BasicBrodScheduleInfoVO vo)
			throws Exception {
		// TODO Auto-generated method stub
		return scheduleMapper.deleteBasicBrodScheduleOther(vo);
	}
	
	@Override
	public List<BasicBrodScheduleInfoVO> selectBasicBrodScheduleCheckList(String centerGubun)
			throws Exception {
		// TODO Auto-generated method stub
		return scheduleMapper.selectBasicBrodScheduleCheckList(centerGubun);
	}

	@Override
	public int updateBasicCodeCenterUpdate(BasicBrodScheduleInfoVO vo)
			throws Exception {
		// TODO Auto-generated method stub
		return scheduleMapper.updateBasicCodeCenterUpdate(vo);
	}

	@Override
	public int updateBasicBrodScheduleCenterE(BasicBrodScheduleInfoVO vo)
			throws Exception {
		// TODO Auto-generated method stub
		return scheduleMapper.updateBasicBrodScheduleCenterE(vo);
	}

	@Override
	public int updateBasicBrodScheduleCenterStateChange(
			BasicBrodScheduleInfoVO vo) throws Exception {
		// TODO Auto-generated method stub
		return scheduleMapper.updateBasicBrodScheduleCenterStateChange(vo);
	}
	
	@Override
	public int updateBasicBrodScheduleState(String basicCode) throws Exception {
		// TODO Auto-generated method stub
		return scheduleMapper.updateBasicBrodScheduleState(basicCode);
	}

	@Override
	public int selectBasciBrodScheduleLstCnt(BasicBrodScheduleInfoVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return scheduleMapper.selectBasciBrodScheduleLstCnt(searchVO);
	}

	@Override
	public int insertBasciBrodScheduleDistribute(String basicCode)
			throws Exception {
		// TODO Auto-generated method stub
		return scheduleMapper.insertBasciBrodScheduleDistribute(basicCode);
	}

	@Override
	public int updateBasicCodeCenterReset(String basicCode) throws Exception {
		// TODO Auto-generated method stub
		return scheduleMapper.updateBasicCodeCenterReset(basicCode);
	}

	@Override
	public String selectBasicBrodContentlDownCheck(BasicBrodScheduleInfoVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return scheduleMapper.selectBasicBrodContentlDownCheck(searchVO);
	}

	@Override
	public int updateBasicBrodScheduleCenterDownCheck(BasicBrodScheduleInfoVO vo) {
		// TODO Auto-generated method stub
		return scheduleMapper.updateBasicBrodScheduleCenterDownCheck(vo);
	}

	
}
