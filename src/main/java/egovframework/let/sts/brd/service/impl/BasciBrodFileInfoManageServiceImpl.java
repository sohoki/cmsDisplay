package egovframework.let.sts.brd.service.impl;


import java.util.List;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.mapper.BasicBrodManagerMapper;
import egovframework.let.sts.brd.service.BasciBrodFileInfo;
import egovframework.let.sts.brd.service.BasciBrodFileInfoVO;
import egovframework.let.sts.brd.service.BasciBrodFileInfoManageService;
import egovframework.let.sts.brd.service.BasciBrodInfo;
import egovframework.com.mapper.BasicBrodFileManagerMapper;

@Service("BasciBrodFileInfoManageService")
public class BasciBrodFileInfoManageServiceImpl extends EgovAbstractServiceImpl implements BasciBrodFileInfoManageService {
	
	
	@Resource(name="BasicBrodFileManagerMapper")
	private BasicBrodFileManagerMapper basicMapper;

	@Override
	public List<BasciBrodFileInfoVO> selectBasicBrodFileLst(String basicCode)
			throws Exception {
		// TODO Auto-generated method stub
		return basicMapper.selectBasicBrodFileLst(basicCode);
	}

	@Override
	public int insertBasciBrodFile(BasciBrodFileInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return basicMapper.insertBasciBrodFile(vo);
	}

	@Override
	public int insertBasciBrodFileCopy(BasciBrodFileInfoVO vo) throws Exception {
		// TODO Auto-generated method stub
		return basicMapper.insertBasciBrodFileCopy(vo);
	}

	@Override
	public int deleteBasciBrodFile(String basicSeq) throws Exception {
		// TODO Auto-generated method stub
		return basicMapper.deleteBasciBrodFile(basicSeq);
	}

	@Override
	public int deleteBasciBrodBasicCode(String basicCode) throws Exception {
		// TODO Auto-generated method stub
		return basicMapper.deleteBasciBrodBasicCode(basicCode);
	}


	
	
	

}
