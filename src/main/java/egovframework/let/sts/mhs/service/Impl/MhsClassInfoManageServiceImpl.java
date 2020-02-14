package egovframework.let.sts.mhs.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.mapper.MhsClassManageMapper;
import egovframework.let.sts.mhs.service.MhsClassInfo;
import egovframework.let.sts.mhs.service.MhsClassInfoManageService;
import egovframework.let.sts.mhs.service.MhsClassInfoVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("MhsClassInfoManageService")
public class MhsClassInfoManageServiceImpl extends EgovAbstractServiceImpl implements MhsClassInfoManageService {
	
	@Resource(name="MhsClassManageMapper")
	private MhsClassManageMapper mhsClassMapper;
	
	@Override
	public List<MhsClassInfoVO> selectMhsClassList(MhsClassInfoVO mhsClassInfo) throws Exception {
		// TODO Auto-generated method stub
		return mhsClassMapper.selectMhsClassList(mhsClassInfo);
	}

	@Override
	public int selectMhsClassListCnt(MhsClassInfoVO mhsClassInfo)
			throws Exception {
		// TODO Auto-generated method stub
		return mhsClassMapper.selectMhsClassListCnt(mhsClassInfo);
	}

	@Override
	public MhsClassInfo selectMhsClassInfo(String mhsClasscd) throws Exception {
		// TODO Auto-generated method stub
		return mhsClassMapper.selectMhsClassInfo(mhsClasscd);
	}
	
	@Override
	public int updateMhsClassInfo(MhsClassInfo vo) throws Exception {
		// TODO Auto-generated method stub
		int ret = 0;		
		if (vo.getMode().equals("Ins")){
			System.out.println(vo.getMhsBrandcd() + "," + vo.getMhsCentercd() +"," + vo.getMhsClasscd());
			ret = mhsClassMapper.insertMhsClassInfo(vo);
		}else {
			ret = mhsClassMapper.updateMhsClassInfo(vo);
		}
		return ret;
	}

	@Override
	public int deleteMhsClassInfo(String mhsClasscd) throws Exception {
		// TODO Auto-generated method stub
		return mhsClassMapper.deleteMhsClassInfo(mhsClasscd);
	}

	@Override
	public List<MhsClassInfo> selectMhsMoniterClassList(MhsClassInfo vo)
			throws Exception {
		// TODO Auto-generated method stub
		return mhsClassMapper.selectMhsMoniterClassList(vo);
	}
	

}
