package egovframework.let.sts.brd.service.impl;


import java.util.List;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.mapper.BrodAnniversaryManagerMapper;
import egovframework.let.sts.brd.service.BrodAnniversary;
import egovframework.let.sts.brd.service.BrodAnniversaryVO;
import egovframework.let.sts.brd.service.BrodAnniversaryManagerService;


@Service("BrodAnniversaryManagerService")
public class BrodAnniversaryManagerServiceImpl extends EgovAbstractServiceImpl implements BrodAnniversaryManagerService {
	
	@Resource(name="BrodAnniManagerMapper")
	private BrodAnniversaryManagerMapper anniMapper;
	
	

	@Override
	public List<BrodAnniversaryVO> selectBrodAnniverLst(BrodAnniversary vo) throws Exception {
		// TODO Auto-generated method stub
		return anniMapper.selectBrodAnniverLst(vo);
	}

	@Override
	public BrodAnniversaryVO selectBrodAnniver(String brodAnnSeq)
			throws Exception {
		// TODO Auto-generated method stub
		return anniMapper.selectBrodAnniver(brodAnnSeq);
	}

	@Override
	public int selectBrodAnniverPageCnt(String brodCode)
			throws Exception {
		// TODO Auto-generated method stub
		return anniMapper.selectBrodAnniverPageCnt(brodCode);
	}

	@Override
	public int insertBrodAnniver(BrodAnniversary vo) throws Exception {
		// TODO Auto-generated method stub
		return anniMapper.insertBrodAnniver(vo);
	}
	@Override
	public int insertBrodAnniverCopy(BrodAnniversary vo) throws Exception {
		// TODO Auto-generated method stub
		return anniMapper.insertBrodAnniverCopy(vo);
	}

	@Override
	public int updateBrodAnniver(BrodAnniversary vo) throws Exception {
		// TODO Auto-generated method stub
		return anniMapper.updateBrodAnniver(vo);
	}

	@Override
	public int deleteBrodAnniver(String brodAnnSeq) throws Exception {
		// TODO Auto-generated method stub
		return anniMapper.deleteBrodAnniver(brodAnnSeq);
	}

	@Override
	public int deleteBrodAnniverBrod(String brodCode) throws Exception {
		// TODO Auto-generated method stub
		return anniMapper.deleteBrodAnniverBrod(brodCode);
	}

	@Override
	public int insertBrodAnniverCenterCopy(BrodAnniversary vo) throws Exception {
		// TODO Auto-generated method stub
		return anniMapper.insertBrodAnniverCenterCopy(vo);
	}

	@Override
	public String selectBrodAnnMaxSeq() throws Exception {
		// TODO Auto-generated method stub
		return anniMapper.selectBrodAnnMaxSeq();
	}

	@Override
	public int deleteBrodAnnBasicBrod(String brodCode) throws Exception {
		// TODO Auto-generated method stub
		return anniMapper.deleteBrodAnnBasicBrod(brodCode);
	}

	@Override
	public int insertBrodAnniverBasicBrodCodeCopy(String brodCode)
			throws Exception {
		// TODO Auto-generated method stub
		return anniMapper.insertBrodAnniverBasicBrodCodeCopy(brodCode);
	}

	@Override
	public int deleteBrodAnniverBrodAll(String brodCode) throws Exception {
		// TODO Auto-generated method stub
		return anniMapper.deleteBrodAnniverBrodAll(brodCode);
	}

	
	
}
