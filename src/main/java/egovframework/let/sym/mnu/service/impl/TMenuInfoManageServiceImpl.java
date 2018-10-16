package egovframework.let.sym.mnu.service.impl;


import java.util.List;
import egovframework.let.sym.mnu.service.TMenuInfo;
import egovframework.let.sym.mnu.service.TMenuInfoVO;
import egovframework.let.sym.mnu.service.TMenuInfoManageService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.mapper.TMenuInfoManagerMapper;
import egovframework.com.cmm.LoginVO;


@Service("TMenuInfoManageService")
public class TMenuInfoManageServiceImpl extends EgovAbstractServiceImpl implements TMenuInfoManageService {

	@Resource(name="TMenuInfoManagerMapper")
	private TMenuInfoManagerMapper tmenuInfoManagerMapper;
	
	@Override
	public List<TMenuInfoVO> selectManuInfoManageListByPagination(
			TMenuInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return tmenuInfoManagerMapper.selectManuInfoManageListByPagination(searchVO);
	}

	@Override
	public List<TMenuInfoVO> selectMenuInfoManageCombo() throws Exception {
		// TODO Auto-generated method stub
		return tmenuInfoManagerMapper.selectMenuInfoManageCombo();
	}

	@Override
	public TMenuInfo selectMenurInfoManageDetail(String menuId) throws Exception {
		// TODO Auto-generated method stub
		return tmenuInfoManagerMapper.selectMenurInfoManageDetail(menuId);
	}

	@Override
	public int selectMenuInfoManageListTotCnt_S(TMenuInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return tmenuInfoManagerMapper.selectMenuInfoManageListTotCnt_S(searchVO);
	}

	@Override
	public int insertMenuInfoManage(TMenuInfo vo)  throws Exception {
		// TODO Auto-generated method stub
		return tmenuInfoManagerMapper.insertMenuInfoManage(vo);
	}

	@Override
	public int updateMenuInfoManage(TMenuInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return tmenuInfoManagerMapper.updateMenuInfoManage(vo);
	}

	@Override
	public int deleteMenuInfoManage(String menuId) throws Exception {
		// TODO Auto-generated method stub
		return tmenuInfoManagerMapper.deleteMenuInfoManage(menuId);
	}

	@Override
	public List<TMenuInfo> selectTMenuComboLst(String didId) throws Exception {
		// TODO Auto-generated method stub
		return tmenuInfoManagerMapper.selectTMenuComboLst(didId);
	}

	@Override
	public List<TMenuInfoVO> selectLeftTMenuLst(LoginVO  loginVO) throws Exception {
		// TODO Auto-generated method stub
		return tmenuInfoManagerMapper.selectLeftTMenuLst(loginVO);
	}

}
