package egovframework.let.sym.rnt.service;


import java.util.List;



public interface RoleInfoManageService {

	public List<RoleInfoVO>  selectRoleInfoManageListByPagination(RoleInfoVO searchVO);
	
	public List<RoleInfoVO>  selectRoleIInfoManageCombo();
	
	public List<RoleInfoVO>  selectRoleIInfoAuthorManageCombo(String authorCode);
	
	public List<RoleInfoVO>  selectNotAdminRoleIInfoAuthorManageCombo();
	
	public List<RoleInfoVO> selectRoleAuthInfoManageCombo();
	
	public RoleInfoVO   selectRoleIrInfoManageDetail(String roleId);
	
	public int selectRoleIInfoManageListTotCnt_S(RoleInfoVO searchVO);

	public int insertRoleIInfoManage(RoleInfo vo);
	
	public int updateRoleIInfoManage(RoleInfo vo);
	
	public int deleteRoleIInfoManage(String roleId);
	
}
