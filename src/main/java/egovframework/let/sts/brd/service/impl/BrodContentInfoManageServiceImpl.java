package egovframework.let.sts.brd.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.mapper.BrodContentInfoManagerMapper;
import egovframework.let.sts.brd.service.BrodContentInfo;
import egovframework.let.sts.brd.service.BrodContentInfoManageService;
import egovframework.let.sts.brd.service.BrodContentInfoVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("BrodContentInfoService")
public class BrodContentInfoManageServiceImpl extends EgovAbstractServiceImpl implements BrodContentInfoManageService {

	
	@Resource(name="BrodContentManageMapper")
	private BrodContentInfoManagerMapper brodContentMapper;
	
	@Override
	public List<BrodContentInfoVO> selectBrodContentLst(
			BrodContentInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return brodContentMapper.selectBrodContentLst(searchVO);
	}

	@Override
	public BrodContentInfoVO selectBrodContentInfo(String brodCode)
			throws Exception {
		// TODO Auto-generated method stub
		return brodContentMapper.selectBrodContentInfo(brodCode);
	}

	@Override
	public int selectBrodContentPageCnt(BrodContentInfoVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return brodContentMapper.selectBrodContentPageCnt(searchVO);
	}

	@Override
	public int insertBrodContent(BrodContentInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return brodContentMapper.insertBrodContent(vo);
	}

	@Override
	public int updateBrodContent(BrodContentInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return brodContentMapper.updateBrodContent(vo);
	}

	@Override
	public int updateBrodContentTimeInterval(BrodContentInfo vo)
			throws Exception {
		// TODO Auto-generated method stub
		return brodContentMapper.updateBrodContentTimeInterval(vo);
	}

	@Override
	public int deleteBrodContent(String brodCode) throws Exception {
		// TODO Auto-generated method stub
		return brodContentMapper.deleteBrodContent(brodCode);
	}

	@Override
	public String selectBrodContentTimeInfo(String brodCode) throws Exception {
		// TODO Auto-generated method stub
		return brodContentMapper.selectBrodContentTimeInfo(brodCode);
	}

	@Override
	public List<BrodContentInfo> selectBrodContentCopy(String brodCode) throws Exception {
		// TODO Auto-generated method stub
		return brodContentMapper.selectBrodContentCopy(brodCode);
	}

	@Override
	public int insertBrodContentCopy(BrodContentInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return brodContentMapper.insertBrodContentCopy(vo);
	}

	@Override
	public List<BrodContentInfo> selectBrodContentCombo() throws Exception {
		// TODO Auto-generated method stub
		return brodContentMapper.selectBrodContentCombo();
	}

	@Override
	public int updateBrodContentSchChange(BrodContentInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return brodContentMapper.updateBrodContentSchChange(vo);
	}

	@Override
	public List<BrodContentInfo> selectBrodContentComboAnn() throws Exception {
		// TODO Auto-generated method stub
		return brodContentMapper.selectBrodContentComboAnn();
	}

	@Override
	public String selectBrodContentTimeInfoChar(String brodCode)
			throws Exception {
		// TODO Auto-generated method stub
		return brodContentMapper.selectBrodContentTimeInfoChar(brodCode);
	}

	@Override
	public int updateBrodContentBasicInfo(BrodContentInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return brodContentMapper.updateBrodContentBasicInfo(vo);
	}

	@Override
	public String selectBrodContentCenterCheckBrodCode(String centerId)
			throws Exception {
		// TODO Auto-generated method stub
		return brodContentMapper.selectBrodContentCenterCheckBrodCode(centerId);
	}

	@Override
	public int insertBrodContentCenterBrodCodeCopy(BrodContentInfo vo)
			throws Exception {
		// TODO Auto-generated method stub
		return brodContentMapper.insertBrodContentCenterBrodCodeCopy(vo);
	}

	@Override
	public int updateBrodContentCenterCntPlus(String brodCode) throws Exception {
		// TODO Auto-generated method stub
		return brodContentMapper.updateBrodContentCenterCntPlus(brodCode);
	}

	@Override
	public int updateBrodContentCenterCntMin(String brodCode) throws Exception {
		// TODO Auto-generated method stub
		return brodContentMapper.updateBrodContentCenterCntMin(brodCode);
	}

	@Override
	public int updateBrodContentCenter(BrodContentInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return brodContentMapper.updateBrodContentCenter(vo);
	}

	@Override
	public String selectBrodContentCenterPreBrodCode(String centerId)
			throws Exception {
		// TODO Auto-generated method stub
		return brodContentMapper.selectBrodContentCenterPreBrodCode(centerId);
	}

	@Override
	public List<BrodContentInfoVO> selectBrodRight(
			BrodContentInfo brodContentInfo) throws Exception {
		// TODO Auto-generated method stub
		return brodContentMapper.selectBrodRight(brodContentInfo);
	}

	@Override
	public List<BrodContentInfo> selectBrodContentBasciContent(String brodCode)
			throws Exception {
		// TODO Auto-generated method stub
		return brodContentMapper.selectBrodContentBasciContent(brodCode);
	}

	@Override
	public int updateBrodBasicCodeCntMin(String brodCode) throws Exception {
		// TODO Auto-generated method stub
		return brodContentMapper.updateBrodBasicCodeCntMin(brodCode);
	}

	@Override
	public String selectBrodContentBasciBrodCodePreBrodCode(String brodCode)
			throws Exception {
		// TODO Auto-generated method stub
		return brodContentMapper.selectBrodContentBasciBrodCodePreBrodCode(brodCode);
	}

	@Override
	public String selectBrodContentBasciBrodCode(String brodCode)
			throws Exception {
		// TODO Auto-generated method stub
		return brodContentMapper.selectBrodContentBasciBrodCode(brodCode);
	}

	@Override
	public int updateBrodContentBasicInfoName(BrodContentInfo vo)
			throws Exception {
		// TODO Auto-generated method stub
		return brodContentMapper.updateBrodContentBasicInfoName(vo);
	}

	@Override
	public int deleteBrodContentAll(String brodCode) throws Exception {
		// TODO Auto-generated method stub
		return brodContentMapper.deleteBrodContentAll(brodCode);
	}

	@Override
	public String selectBrodContentCenterNm(String brodCode) throws Exception {
		// TODO Auto-generated method stub
		return brodContentMapper.selectBrodContentCenterNm(brodCode);
	}

	@Override
	public int updateBrodContentBasicFileInfo(BrodContentInfo vo)
			throws Exception {
		// TODO Auto-generated method stub
		return brodContentMapper.updateBrodContentBasicFileInfo(vo);
	}

	
}
