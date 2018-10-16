package egovframework.let.sym.mnu.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import egovframework.let.sym.mnu.service.MenuInfo;
import egovframework.let.sym.mnu.service.MenuInfoVO;
import egovframework.let.sym.mnu.service.MenuInfoManageService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import egovframework.com.mapper.MenuInfoManagerMapper;
import egovframework.com.cmm.LoginVO;

@Service("MenuInfoManageService")
public class MenuInfoManageServiceImpl extends EgovAbstractServiceImpl implements MenuInfoManageService {

	
	@Resource(name="MenuInfoManagerMapper")
	private MenuInfoManagerMapper menuManagerMapper;
	
	
	@Override
	public List<MenuInfoVO> selectManuInfoManageListByPagination(
			MenuInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return menuManagerMapper.selectManuInfoManageListByPagination(searchVO);
	}

	@Override
	public List<MenuInfoVO> selectMenuInfoManageCombo( )throws Exception {
		// TODO Auto-generated method stub
		return menuManagerMapper.selectMenuInfoManageCombo();
	}

	@Override
	public MenuInfoVO selectMenurInfoManageDetail(String menuId) throws Exception{
		// TODO Auto-generated method stub
		return menuManagerMapper.selectMenurInfoManageDetail(menuId);
	}

	@Override
	public int selectMenuInfoManageListTotCnt_S(MenuInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return menuManagerMapper.selectMenuInfoManageListTotCnt_S(searchVO);
	}

	@Override
	public int insertMenuInfoManage(MenuInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return menuManagerMapper.insertMenuInfoManage(vo);
	}

	@Override
	public int updateMenuInfoManage(MenuInfo vo) throws Exception {
		// TODO Auto-generated method stub
		return menuManagerMapper.updateMenuInfoManage(vo);
	}

	@Override
	public int deleteMenuInfoManage(String menuId) throws Exception {
		// TODO Auto-generated method stub
		return menuManagerMapper.deleteMenuInfoManage(menuId);
	}

	@Override
	public List<MenuInfo> selectMenuComboLst(String  didId) throws Exception {
		// TODO Auto-generated method stub
		return menuManagerMapper.selectMenuComboLst(didId);
	}

	@Override
	public List<MenuInfoVO> selectLeftMenuLst( LoginVO loginVO ) throws Exception {
		// TODO Auto-generated method stub
		return menuManagerMapper.selectLeftMenuLst(loginVO);
	}

	
	
	
	
	
}
