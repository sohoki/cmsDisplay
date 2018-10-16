package egovframework.let.sym.did.service.Impl;


import java.util.List;

import org.springframework.stereotype.Service;

import egovframework.let.sym.did.service.DidInfo;
import egovframework.let.sym.did.service.DidInfoVO;
import egovframework.let.sym.did.service.DidInfoManageService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;













import javax.annotation.Resource;

import egovframework.com.mapper.DidInfoManagerMapper;

@Service("DidInfoManageService")
public class DidInfoManageServiceImpl extends EgovAbstractServiceImpl implements  DidInfoManageService{

	
	@Resource( name = "DidInfoManagerMapper")
	private DidInfoManagerMapper didInfoManagerMapper;
	
	@Override
	public List<DidInfoVO> selectDidInfoManageListByPagination(
			DidInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return didInfoManagerMapper.selectDidInfoManageListByPagination(searchVO);
	}

	@Override
	public List<DidInfoVO> selectDidInfoManageCombo() throws Exception {
		// TODO Auto-generated method stub
		return didInfoManagerMapper.selectDidInfoManageCombo();
	}

	@Override
	public DidInfoVO selectDidrInfoManageDetail(String didId) throws Exception {
		// TODO Auto-generated method stub
		return didInfoManagerMapper.selectDidrInfoManageDetail(didId);
	}

	@Override
	public int selectDidInfoManageListTotCnt_S(DidInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return didInfoManagerMapper.selectDidInfoManageListTotCnt_S(searchVO);
	}

	@Override
	public int insertDidInfoManage(DidInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return didInfoManagerMapper.insertDidInfoManage(vo);
	}

	@Override
	public int updateDidInfoManage(DidInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return didInfoManagerMapper.updateDidInfoManage(vo);
	}

	@Override
	public int deleteDidInfoManage(String  didId) throws Exception {
		// TODO Auto-generated method stub
		return didInfoManagerMapper.deleteDidInfoManage(didId);
	}

	@Override
	public DidInfoVO selectDidrInfoManageDetailView(String didId) throws Exception {
		// TODO Auto-generated method stub
		return didInfoManagerMapper.selectDidrInfoManageDetailView(didId);
	}

	@Override
	public int updateDidMac(DidInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return didInfoManagerMapper.updateDidMac(vo);
	}

	@Override
	public int updateDidState(DidInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return didInfoManagerMapper.updateDidState(vo);
	}

	@Override
	public int updateDidEndTime(DidInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return didInfoManagerMapper.updateDidEndTime(vo);
	}

	@Override
	public List<DidInfoVO> selectDidManagerInfoManageListByPagination(
			DidInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return didInfoManagerMapper.selectDidManagerInfoManageListByPagination(searchVO);
	}

	@Override
	public int selectDidManagerInfoManageListTotCnt_S(DidInfoVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return didInfoManagerMapper.selectDidManagerInfoManageListTotCnt_S(searchVO);
	}

	@Override
	public String selectDIDMac(String didId) throws Exception {
		// TODO Auto-generated method stub
		return didInfoManagerMapper.selectDIDMac(didId);
	}

	@Override
	public List selectDidDetailContentInfo(String didId) throws Exception {
		// TODO Auto-generated method stub
		return didInfoManagerMapper.selectDidDetailContentInfo(didId);
	}

	@Override
	public String selectLastInsertDid(String centerId) throws Exception {
		// TODO Auto-generated method stub
		return didInfoManagerMapper.selectLastInsertDid(centerId);
	}

	
	
	
	
	@Override
	public List<DidInfoVO> selectIntegrateManageListByPagination(
			DidInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return didInfoManagerMapper.selectIntegrateManageListByPagination(searchVO);
	}

	@Override
	public List<DidInfoVO> selectIntegrateRoleList(DidInfoVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return didInfoManagerMapper.selectIntegrateRoleList(searchVO);
	}

	@Override
	public List<DidInfoVO> selectIntegrateCenterList(DidInfoVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return didInfoManagerMapper.selectIntegrateCenterList(searchVO);
	}

	@Override
	public List<DidInfoVO> selectIntegrateDeviceList(DidInfoVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return didInfoManagerMapper.selectIntegrateDeviceList(searchVO);
	}
	
	
	
	

}
