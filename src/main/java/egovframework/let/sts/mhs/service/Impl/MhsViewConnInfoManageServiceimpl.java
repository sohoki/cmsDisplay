package egovframework.let.sts.mhs.service.Impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.let.sts.mhs.service.MhsViewConnInfoVO;
import egovframework.let.sts.mhs.service.MhsViewConnInfo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.let.sts.mhs.service.MhsViewConnInfoManageService;
import egovframework.com.mapper.MhsViewConnInfoManageMapper;

@Service("MhsViewConnInfoManageService")
public class MhsViewConnInfoManageServiceimpl extends EgovAbstractServiceImpl implements MhsViewConnInfoManageService {

	@Resource(name="MhsViewConnInfoManageMapper")
	private MhsViewConnInfoManageMapper ConnMapper;
	
	@Override
	public List<MhsViewConnInfoVO> selectViewMoniterClassInfo(
			MhsViewConnInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return ConnMapper.selectViewMoniterClassInfo(searchVO);
	}

	@Override
	public int insertMoniterClassInfo(MhsViewConnInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return ConnMapper.insertMoniterClassInfo(vo);
	}

	@Override
	public int updateMoniterClassInfo(MhsViewConnInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return ConnMapper.updateMoniterClassInfo(vo);
	}

	@Override
	public int deleteMoniterClassInfo(String  mhsConnSeq) throws Exception {
		// TODO Auto-generated method stub
		return ConnMapper.deleteMoniterClassInfo(mhsConnSeq);
	}

	@Override
	public int selectViewMoniterClassUpdateInfoChange(String mhsMonitorcd)
			throws Exception {
		// TODO Auto-generated method stub
		return ConnMapper.selectViewMoniterClassUpdateInfoChange(mhsMonitorcd);
	}
    //통신 관련 내용 변경 
	@Override
	public int updateMoniterDidUpdateChange(String mhsMonitorcd)
			throws Exception {
		// TODO Auto-generated method stub
		return ConnMapper.updateMoniterDidUpdateChange(mhsMonitorcd);
	}

	@Override
	public int updateMoniterClassChangeInfo(String mhsClasscd) throws Exception {
		// TODO Auto-generated method stub
		return ConnMapper.updateMoniterClassChangeInfo(mhsClasscd);
	}

	@Override
	public List<MhsViewConnInfoVO> selectViewMoniterClassUninPageInfo(
			MhsViewConnInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return ConnMapper.selectViewMoniterClassUninPageInfo(searchVO);
	}

	
}
