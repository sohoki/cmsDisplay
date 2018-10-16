package egovframework.let.sts.brd.service.impl;

import java.util.List;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.mapper.BasicBrodManagerMapper;
import egovframework.let.sts.brd.service.BasciBrodInfo;
import egovframework.let.sts.brd.service.BasciBrodInfoVO;
import egovframework.let.sts.brd.service.BasciBrodInfoManageService;

@Service("BasciBrodInfoManageService")
public class BasciBrodInfoManageServiceImpl extends EgovAbstractServiceImpl implements BasciBrodInfoManageService {

	
	@Resource(name="BasicBrodManagerMapper")
	private BasicBrodManagerMapper basicMapper;

	@Override
	public List<BasciBrodInfoVO> selectBasciBrodLst(BasciBrodInfoVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return basicMapper.selectBasciBrodLst(searchVO);
	}

	@Override
	public BasciBrodInfo selectBasciBrod(String basicCode) throws Exception {
		// TODO Auto-generated method stub
		return basicMapper.selectBasciBrod(basicCode);
	}

	@Override
	public int selectBasciBrodPageCnt(BasciBrodInfoVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return basicMapper.selectBasciBrodPageCnt(searchVO);
	}

	@Override
	public int insertBasciBrod(BasciBrodInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return basicMapper.insertBasciBrod(vo);
	}

	@Override
	public int insertBasciBrodCopy(BasciBrodInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return basicMapper.insertBasciBrodCopy(vo);
	}

	@Override
	public int updateBasciBrod(BasciBrodInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return basicMapper.updateBasciBrod(vo);
	}

	@Override
	public int updateBasciBrodCnt(String basicCode) throws Exception {
		// TODO Auto-generated method stub
		return basicMapper.updateBasciBrodCnt(basicCode);
	}

	@Override
	public int updateBasciBrodCntPlus(String basicCode) throws Exception {
		// TODO Auto-generated method stub
		return basicMapper.updateBasciBrodCntPlus(basicCode);
	}

	@Override
	public int updateBasciBrodCntMins(String basicCode) throws Exception {
		// TODO Auto-generated method stub
		return basicMapper.updateBasciBrodCntMins(basicCode);
	}

	@Override
	public int deleteBasciBrod(String basicCode) throws Exception {
		// TODO Auto-generated method stub
		return basicMapper.deleteBasciBrod(basicCode);
	}

	@Override
	public String selectMaxBrodCode() throws Exception {
		// TODO Auto-generated method stub
		return basicMapper.selectMaxBrodCode();
	}

	@Override
	public List<BasciBrodInfo> selectBasicBrodCombo() throws Exception {
		// TODO Auto-generated method stub
		return basicMapper.selectBasicBrodCombo();
	}

	@Override
	public String selectBasciCode() throws Exception {
		// TODO Auto-generated method stub
		return basicMapper.selectBasciCode();
	}
	
	
	
	
}
