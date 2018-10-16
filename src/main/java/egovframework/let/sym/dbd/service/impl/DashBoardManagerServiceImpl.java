package egovframework.let.sym.dbd.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import egovframework.let.sym.dbd.service.DashBoardInfo;
import egovframework.let.sym.dbd.service.DashBoardManagerService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import egovframework.com.mapper.DashBoardManagerMapper;

@Service("DashBoardManagerService")
public class DashBoardManagerServiceImpl extends EgovAbstractServiceImpl implements DashBoardManagerService {

	
	@Resource(name="DashManagerMapper")
	private DashBoardManagerMapper dashboard;
	
	@Override
	public DashBoardInfo selectDidStatus() throws Exception {
		// TODO Auto-generated method stub
		return dashboard.selectDidStatus();
	}

	@Override
	public DashBoardInfo selectBrodStatus() throws Exception {
		// TODO Auto-generated method stub
		return dashboard.selectBrodStatus();
	}

	@Override
	public int dashStateUpdateStep01() throws Exception {
		// TODO Auto-generated method stub
		return dashboard.dashStateUpdateStep01();
	}

	@Override
	public int dashStateUpdateStep02() throws Exception {
		// TODO Auto-generated method stub
		return dashboard.dashStateUpdateStep02();
	}

	@Override
	public List<DashBoardInfo> selectBrodStatusPage01(DashBoardInfo searchVo) throws Exception {
		// TODO Auto-generated method stub
		return dashboard.selectBrodStatusPage01(searchVo);
	}

	@Override
	public List<DashBoardInfo> selectBrodStatusPage02(DashBoardInfo searchVo) throws Exception {
		// TODO Auto-generated method stub
		return dashboard.selectBrodStatusPage02(searchVo);
	}

	@Override
	public int selectBrodStatusPage01Cnt() throws Exception {
		// TODO Auto-generated method stub
		return dashboard.selectBrodStatusPage01Cnt();
	}

	@Override
	public int selectBrodStatusPage02Cnt() throws Exception {
		// TODO Auto-generated method stub
		return dashboard.selectBrodStatusPage02Cnt();
	}

	

}
