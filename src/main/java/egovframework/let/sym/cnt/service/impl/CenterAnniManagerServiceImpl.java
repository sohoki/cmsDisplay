package egovframework.let.sym.cnt.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import egovframework.let.sym.cnt.service.CenterInfoAnniversary;
import egovframework.let.sym.cnt.service.CenterInfoAnniversaryVO;
import egovframework.let.sym.cnt.service.CenterAnniManagerService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;





import javax.annotation.Resource;

import egovframework.com.mapper.CenterAnniManagerMapper;

@Service("CenterAnniManagerService")
public class CenterAnniManagerServiceImpl extends EgovAbstractServiceImpl implements CenterAnniManagerService {
	
	
	@Resource(name="CenterAnniManager")
	private CenterAnniManagerMapper centerAnni;

	@Override
	public List<CenterInfoAnniversaryVO> selectCenterAnniManageListByPagination(
			CenterInfoAnniversaryVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return centerAnni.selectCenterAnniManageListByPagination(searchVO);
	}

	@Override
	public CenterInfoAnniversaryVO selectCenterAnniManageDetail(
			String centerAnniDay) throws Exception {
		// TODO Auto-generated method stub
		return centerAnni.selectCenterAnniManageDetail(centerAnniDay);
	}

	@Override
	public int selectCenterAnniManageListTotCnt_S(
			CenterInfoAnniversaryVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return centerAnni.selectCenterAnniManageListTotCnt_S(searchVO);
	}

	@Override
	public int insertCenterAnniManage(CenterInfoAnniversary vo)
			throws Exception {
		// TODO Auto-generated method stub
		return centerAnni.insertCenterAnniManage(vo);
	}

	@Override
	public int updateCenterAnniManage(CenterInfoAnniversary vo)
			throws Exception {
		// TODO Auto-generated method stub
		return centerAnni.updateCenterAnniManage(vo);
	}

	@Override
	public int deleteCenterAnniManage(String centerAnniDay) throws Exception {
		// TODO Auto-generated method stub
		return centerAnni.deleteCenterAnniManage(centerAnniDay);
	}

	@Override
	public int selectCenterAnniRetgCheck(CenterInfoAnniversaryVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return centerAnni.selectCenterAnniRetgCheck(searchVO);
	}

	@Override
	public int deleteCenterID(String centerId) throws Exception {
		// TODO Auto-generated method stub
		return centerAnni.deleteCenterID(centerId);
	}
	
	

}
