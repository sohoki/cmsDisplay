package egovframework.let.sts.brd.service.impl;


import java.util.List;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.let.sts.brd.service.BrodScheduleInfoVO;
import egovframework.let.sts.brd.service.BrodScheduleInfo;
import egovframework.let.sts.brd.service.BrodScheduleManagerService;
import egovframework.com.mapper.BrodScheduleManagerMapper;


@Service("BrodScheduleManagerService")
public class BrodScheduleManagerServiceImpl extends EgovAbstractServiceImpl implements  BrodScheduleManagerService {

	@Resource(name="BrodScheduleManagerMapper")
	private BrodScheduleManagerMapper brodSchedule;
	
	@Override
	public List<BrodScheduleInfoVO> selectBrodRigthLst(
			BrodScheduleInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return brodSchedule.selectBrodRigthLst(searchVO);
	}

	@Override
	public int insertBrodSchedule(BrodScheduleInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return brodSchedule.insertBrodSchedule(vo);
	}

	@Override
	public int updateBrodSchedule(BrodScheduleInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return brodSchedule.updateBrodSchedule(vo);
	}

	@Override
	public int deleteBrodSchedule(BrodScheduleInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return brodSchedule.deleteBrodSchedule(vo);
	}

	@Override
	public int deleteBrodScheduleOther(BrodScheduleInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return brodSchedule.deleteBrodScheduleOther(vo);
	}

	@Override
	public int updateCenterSchedule(BrodScheduleInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return brodSchedule.updateCenterSchedule(vo);
	}

	@Override
	public List<BrodScheduleInfoVO> selectBrodScheduleStatusLst(
			BrodScheduleInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return brodSchedule.selectBrodScheduleStatusLst(searchVO);
	}

	@Override
	public int selectBrodScheduleStatusPageCnt(BrodScheduleInfoVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return brodSchedule.selectBrodScheduleStatusPageCnt(searchVO);
	}

	@Override
	public List<BrodScheduleInfoVO> selectBrodScheduleUpdateChanage(
			String bordCode) throws Exception {
		// TODO Auto-generated method stub
		return brodSchedule.selectBrodScheduleUpdateChanage(bordCode);
	}

	@Override
	public int deleteBrodScheduleAll(String brodCode) throws Exception {
		// TODO Auto-generated method stub
		return brodSchedule.deleteBrodScheduleAll(brodCode);
	}

	@Override
	public int selectBrodScheduleCnt(BrodScheduleInfo searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return brodSchedule.selectBrodScheduleCnt(searchVO);
	}

	@Override
	public int updateBrodScheduleCenter(BrodScheduleInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return brodSchedule.updateBrodScheduleCenter(vo);
	}

	@Override
	public String selectBrodScheduleAnniScheduleSeqDay(BrodScheduleInfo searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return brodSchedule.selectBrodScheduleAnniScheduleSeqDay(searchVO);
	}

	@Override
	public int updateBrodScheduleCenterBrod(BrodScheduleInfo vo)
			throws Exception {
		// TODO Auto-generated method stub
		return brodSchedule.updateBrodScheduleCenterBrod(vo);
	}

	@Override
	public int selectBordScheduleCount(BrodScheduleInfo vo) throws Exception {
		// TODO Auto-generated method stub				
		return brodSchedule.selectBordScheduleCount(vo);
	}

	@Override
	public BrodScheduleInfo selectBordScheduleCode(BrodScheduleInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return brodSchedule.selectBordScheduleCode(vo);
	}

	@Override
	public int updateBrodDidCenterID(BrodScheduleInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return brodSchedule.updateBrodDidCenterID(vo);
	}

	@Override
	public BrodScheduleInfo selectBordScheduleCodeRedown(BrodScheduleInfo vo)
			throws Exception {
		// TODO Auto-generated method stub
		return brodSchedule.selectBordScheduleCodeRedown(vo);
	}

	@Override
	public int updateBrodScheduleReset(BrodScheduleInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return brodSchedule.updateBrodScheduleReset(vo);
	}

	@Override
	public int deleteBrodScheduleSeq(String scheduleSeq) throws Exception {
		// TODO Auto-generated method stub
		return brodSchedule.deleteBrodScheduleSeq(scheduleSeq);
	}

	@Override
	public List<BrodScheduleInfo> selectBrodScheduleCreateCheckList(
			BrodScheduleInfo searchVO) throws Exception {
		// TODO Auto-generated method stub
		return brodSchedule.selectBrodScheduleCreateCheckList(searchVO);
	}

	@Override
	public int updateBrodScheduleCenterNotUse(String centerId) throws Exception {
		// TODO Auto-generated method stub
		return brodSchedule.updateBrodScheduleCenterNotUse(centerId);
	}

	@Override
	public int selectBrodScheduleCenterCnt(String centerId) throws Exception {
		// TODO Auto-generated method stub
		return brodSchedule.selectBrodScheduleCenterCnt(centerId);
	}

	@Override
	public int selectBrodScheduleStateCnt(BrodScheduleInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return brodSchedule.selectBrodScheduleStateCnt(vo);
	}

	@Override
	public int deleteBrodScheduleState(BrodScheduleInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return brodSchedule.deleteBrodScheduleState(vo);
	}

}
