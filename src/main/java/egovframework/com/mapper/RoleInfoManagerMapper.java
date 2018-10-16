package egovframework.com.mapper;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.let.sym.rnt.service.RoleInfo;
import egovframework.let.sym.rnt.service.RoleInfoVO;
import java.util.List;


@Mapper("RoleInfoManagerMapper")
public interface  RoleInfoManagerMapper {
	
	public List<RoleInfoVO>  selectRoleInfoManageListByPagination(RoleInfoVO searchVO);
	
	public List<RoleInfoVO>  selectRoleIInfoManageCombo();
	
	public List<RoleInfoVO>  selectRoleIInfoAuthorManageCombo(String authorCode);
	
	public List<RoleInfoVO>  selectNotAdminRoleIInfoAuthorManageCombo();
	
	
	public RoleInfoVO   selectRoleIrInfoManageDetail(String roleId);
	
	public int selectRoleIInfoManageListTotCnt_S(RoleInfoVO searchVO);

	public int insertRoleIInfoManage(RoleInfo vo);
	
	public int updateRoleIInfoManage(RoleInfo vo);
	
	public int deleteRoleIInfoManage(String roleId);
	
    public List<RoleInfoVO>   selectRoleAuthInfoManageCombo();
    
    
    
}
