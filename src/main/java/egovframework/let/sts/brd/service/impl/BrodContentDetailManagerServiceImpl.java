package egovframework.let.sts.brd.service.impl;


import java.util.List;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.let.sts.brd.service.BrodContentDetail;
import egovframework.let.sts.brd.service.BrodContentDetailVO;
import egovframework.let.sts.brd.service.BrodContentDetailManagerService;
import egovframework.com.mapper.BrodContentDetailManagerMapper;

@Service("BrodContentDetailService")
public class BrodContentDetailManagerServiceImpl extends EgovAbstractServiceImpl implements BrodContentDetailManagerService {

	
	@Resource(name="BrodContentDetailManageMapper")
	private BrodContentDetailManagerMapper contentDetail;
	
	@Override
	public List<BrodContentDetailVO> selectBrodContentDetailLst(
			BrodContentDetailVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.selectBrodContentDetailLst(searchVO);
	}

	@Override
	public BrodContentDetailVO selectBrodContenDetailt(String brodSeq)
			throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.selectBrodContenDetailt(brodSeq);
	}

	@Override
	public int selectBrodContentDetailPageCnt(BrodContentDetailVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.selectBrodContentDetailPageCnt(searchVO);
	}

	@Override
	public int insertBrodContentDetail(BrodContentDetail vo) throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.insertBrodContentDetail(vo);
	}

	@Override
	public int updateBrodContentDetail(BrodContentDetail vo) throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.updateBrodContentDetail(vo);
	}

	@Override
	public int deleteBrodContentDetail(String brodSeq) throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.deleteBrodContentDetail(brodSeq);
	}

	@Override
	public List<BrodContentDetail> selectTimeCombo(String endTime)
			throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.selectTimeCombo(endTime);
	}

	@Override
	public int selectContentRegCnt(BrodContentDetail vo) throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.selectContentRegCnt(vo);
	}

	@Override
	public int selectContentRegTimeOverCheck(BrodContentDetail vo)
			throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.selectContentRegTimeOverCheck(vo);
	}

	@Override
	public int deleteBrodContentTimeDel(BrodContentDetail vo) throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.deleteBrodContentTimeDel(vo);
	}

	@Override
	public List<BrodContentDetail> selectTimeHourCombo() throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.selectTimeHourCombo();
	}

	@Override
	public int insertBrodContentCopy(BrodContentDetail vo) throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.insertBrodContentCopy(vo);
	}

	@Override
	public int deleteBrodContentBrodCode(String brodCode) throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.deleteBrodContentBrodCode(brodCode);
	}

	@Override
	public int selectContentFileTime(String atchFileId) throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.selectContentFileTime(atchFileId);
	}

	@Override
	public int selectContentRegTimeImsiOverTableCheck(BrodContentDetail vo)
			throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.selectContentRegTimeImsiOverTableCheck(vo);
	}

	@Override
	public int insertBrodContentCenterCopy(BrodContentDetail vo)
			throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.insertBrodContentCenterCopy(vo);
	}

	@Override
	public int insertBrodContentScheduleOtherCopy(String brodCode)
			throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.insertBrodContentScheduleOtherCopy(brodCode);
	}

	@Override
	public int deleteBrodBasicBrod(String brodCode) throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.deleteBrodBasicBrod(brodCode);
	}

	@Override
	public int deleteContentDetailBasciContent(BrodContentDetail vo)
			throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.deleteContentDetailBasciContent(vo);
	}

	@Override
	public List<BrodContentDetail> selectBrodFileList(BrodContentDetail vo) throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.selectBrodFileList(vo);
	}

	@Override
	public int deleteBrodContentBrodCodeALL(String brodCode) throws Exception {
		// TODO Auto-generated method stub
		return contentDetail.deleteBrodContentBrodCodeALL(brodCode);
	}
	
	
}
