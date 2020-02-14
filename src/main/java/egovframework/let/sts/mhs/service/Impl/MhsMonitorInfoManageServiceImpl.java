package egovframework.let.sts.mhs.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.mapper.MhsCenterManageMapper;
import egovframework.com.mapper.MhsMonitorManageMapper;
import egovframework.let.sts.mhs.service.MhsCenterInfo;
import egovframework.let.sts.mhs.service.MhsCenterInfoManageService;
import egovframework.let.sts.mhs.service.MhsCenterInfoVO;
import egovframework.let.sts.mhs.service.MhsMonitorInfo;
import egovframework.let.sts.mhs.service.MhsMonitorInfoManageService;
import egovframework.let.sts.mhs.service.MhsMonitorInfoVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("MhsMonitorInfoManageService")
public class MhsMonitorInfoManageServiceImpl extends EgovAbstractServiceImpl implements MhsMonitorInfoManageService {
	
	@Resource(name="MhsMonitorManageMapper")
	private MhsMonitorManageMapper mhsMonitorMapper;
	
	@Override
	public List<MhsMonitorInfoVO> selectMhsMonitorList(MhsMonitorInfoVO mhsMonitorInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return mhsMonitorMapper.selectMhsMonitorList(mhsMonitorInfoVO);
	}

	@Override
	public int selectMhsMonitorListCnt(MhsMonitorInfoVO mhsMonitorInfoVO)
			throws Exception {
		// TODO Auto-generated method stub
		return mhsMonitorMapper.selectMhsMonitorListCnt(mhsMonitorInfoVO);
	}

	@Override
	public MhsMonitorInfoVO selectMhsMonitorInfo(String mhsMonitorcd)
			throws Exception {
		// TODO Auto-generated method stub
		return mhsMonitorMapper.selectMhsMonitorInfo(mhsMonitorcd);
	}
	
	@Override
	public List<MhsMonitorInfoVO> selectMhsMonitorNm(String mberId) throws Exception {
		// TODO Auto-generated method stub
		return mhsMonitorMapper.selectMhsMonitorNm(mberId);
	}

	

	@Override
	public int updateMhsMonitorInfo(MhsMonitorInfo vo) throws Exception {
		// TODO Auto-generated method stub
		int ret =  0;
		if (vo.getMode().equals("Ins")){
			ret = mhsMonitorMapper.insertMhsMonitorInfo(vo);
		}else {
			ret = mhsMonitorMapper.updateMhsMonitorInfo(vo);
		}
		return ret;
	}

	@Override
	public int updateMhsMonitorInfoIpMac(MhsMonitorInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return mhsMonitorMapper.updateMhsMonitorInfoIpMac(vo);
	}

	@Override
	public int deleteMhsMonitorInfo(String mhsMonitorcd) throws Exception {
		// TODO Auto-generated method stub
		return mhsMonitorMapper.deleteMhsMonitorInfo(mhsMonitorcd);
	}

	@Override
	public int updateMhsMonitorInfoStatus(String mhsMonitorcd) throws Exception {
		// TODO Auto-generated method stub
		return mhsMonitorMapper.updateMhsMonitorInfoStatus(mhsMonitorcd);
	}

	@Override
	public int updateMoniterDidUpdateDayChange(String mhsMonitorcd)
			throws Exception {
		// TODO Auto-generated method stub
		return mhsMonitorMapper.updateMhsMonitorInfoStatus(mhsMonitorcd);
	}

	
	

}
