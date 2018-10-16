package egovframework.let.sym.rnt.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.let.sym.rnt.service.RoleInfo;
import egovframework.let.sym.rnt.service.RoleInfoVO;
import egovframework.let.sym.rnt.service.RoleInfoManageService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


import egovframework.com.mapper.RoleInfoManagerMapper;


@Service("RoleInfoManageService")
public class RoleInfoManageServiceImpl extends EgovAbstractServiceImpl implements RoleInfoManageService {

	
	@Resource (name="RoleInfoManagerMapper")
	private RoleInfoManagerMapper roleInfoManageMapper;
	
	@Override
	public List<RoleInfoVO> selectRoleInfoManageListByPagination(
			RoleInfoVO searchVO) {
		return roleInfoManageMapper.selectRoleInfoManageListByPagination(searchVO);
	}

	@Override
	public List<RoleInfoVO> selectRoleIInfoManageCombo() {
		// TODO Auto-generated method stub
		return roleInfoManageMapper.selectRoleIInfoManageCombo();
	}

	@Override
	public RoleInfoVO selectRoleIrInfoManageDetail(String roleId) {
		// TODO Auto-generated method stub
		return roleInfoManageMapper.selectRoleIrInfoManageDetail(roleId);
	}

	@Override
	public int selectRoleIInfoManageListTotCnt_S(RoleInfoVO searchVO) {
		// TODO Auto-generated method stub
		return roleInfoManageMapper.selectRoleIInfoManageListTotCnt_S(searchVO);
	}

	@Override
	public int insertRoleIInfoManage(RoleInfo vo) {
		// TODO Auto-generated method stub
		return roleInfoManageMapper.insertRoleIInfoManage(vo);
	}

	@Override
	public int updateRoleIInfoManage(RoleInfo vo) {
		// TODO Auto-generated method stub
		return roleInfoManageMapper.updateRoleIInfoManage(vo);
	}

	@Override
	public int deleteRoleIInfoManage(String roleId) {
		// TODO Auto-generated method stub
		return roleInfoManageMapper.deleteRoleIInfoManage(roleId);
	}

	@Override
	public List<RoleInfoVO> selectRoleAuthInfoManageCombo() {
		// TODO Auto-generated method stub
		return roleInfoManageMapper.selectRoleAuthInfoManageCombo();
	}

	@Override
	public List<RoleInfoVO> selectRoleIInfoAuthorManageCombo(String authorCode) {
		// TODO Auto-generated method stub
		return roleInfoManageMapper.selectRoleIInfoAuthorManageCombo(authorCode);
	}

	@Override
	public List<RoleInfoVO> selectNotAdminRoleIInfoAuthorManageCombo() {
		// TODO Auto-generated method stub
		return roleInfoManageMapper.selectNotAdminRoleIInfoAuthorManageCombo();
	}

	
	
	
	
}
