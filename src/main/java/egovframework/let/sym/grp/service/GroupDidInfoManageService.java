package egovframework.let.sym.grp.service;

import java.util.List;

public interface GroupDidInfoManageService {

	
	public List<GroupDidInfoVO> selectGroupInfoManageListByPagination(String groupCode);
	
	public int selectGroupIDidCheckInfoManageListTotCnt_S(String groupId, String groupCode);
	
	public int selectGroupInfoManageListTotCnt_S(String groupId);
	
	public int insertGroupInfoManage(GroupDidInfo vo);
	
	public int deleteGroupInfoManage(String didId);
	
	
	public List<GroupDidInfo> selectComboLst();
	
	
}
