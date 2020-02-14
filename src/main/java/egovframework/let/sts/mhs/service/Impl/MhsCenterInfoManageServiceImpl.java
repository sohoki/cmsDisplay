package egovframework.let.sts.mhs.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.mapper.MhsCenterManageMapper;
import egovframework.let.sts.mhs.service.MhsCenterInfo;
import egovframework.let.sts.mhs.service.MhsCenterInfoManageService;
import egovframework.let.sts.mhs.service.MhsCenterInfoVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("MhsCenterInfoManageService")
public class MhsCenterInfoManageServiceImpl extends EgovAbstractServiceImpl implements MhsCenterInfoManageService {
	
	@Resource(name="MhsCenterManageMapper")
	private MhsCenterManageMapper mhsCenterMapper;
			
	@Override
	public List<MhsCenterInfoVO> selectMhsBrandList(MhsCenterInfoVO mhsCenterInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return mhsCenterMapper.selectMhsBrandList(mhsCenterInfoVO);
	}
	
	@Override
	public List<MhsCenterInfoVO> selectMhsCenterList(MhsCenterInfoVO mhsCenterInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return mhsCenterMapper.selectMhsCenterList(mhsCenterInfoVO);
	}

	@Override
	public List<MhsCenterInfo> selectMhsComboList(String mhsBrandcd)
			throws Exception {
		// TODO Auto-generated method stub
		return mhsCenterMapper.selectMhsComboList(mhsBrandcd);
	}

	

	@Override
	public int updateMhsCenter(MhsCenterInfo vo) throws Exception {
		// TODO Auto-generated method stub
		int ret = 0;
		if (vo.getSubMode().equals("Ins")){
			ret = mhsCenterMapper.insertMhsCenter(vo);
		}else {
			ret = mhsCenterMapper.updateMhsCenter(vo);
		}
		return ret;
	}

	@Override
	public int deleteMhsCenter(String mhsCentercd) throws Exception {
		// TODO Auto-generated method stub
		return mhsCenterMapper.deleteMhsCenter(mhsCentercd);
	}

	@Override
	public MhsCenterInfo selectMhsCenterInfo(String mhsCentercd)
			throws Exception {
		// TODO Auto-generated method stub
		return mhsCenterMapper.selectMhsCenterInfo(mhsCentercd);
	}

	@Override
	public List<MhsCenterInfo> selectMhsComboListMeber(String mhsBrandcd)
			throws Exception {
		// TODO Auto-generated method stub
		return mhsCenterMapper.selectMhsComboListMeber(mhsBrandcd);
	}
	
}
