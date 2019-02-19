package egovframework.let.sym.grp.service;

import java.util.List;

public interface GroupInfoManageService {

	
	
	public List<GroupInfoVO>  selectGroupInfoManageListByPagination(GroupInfoVO searchVO);
	
	public List<GroupInfoVO> selectGroupInfoManageCombo(GroupInfoVO searchVO);
	
	public GroupInfo selectGroupInfoManageDetail (String groupCode);
	
	public int  selectGroupIDInfoManageListTotCnt_S(String groupCode);
	
	public int  selectGroupInfoManageListTotCnt_S(GroupInfoVO searchVO);
	
	public String selectLastInsertGroup();
	
	public int  insertGroupInfoManage(GroupInfo vo);
	
	public int  updateGroupInfoManage(GroupInfo vo);
	
	public int  deleteGroupInfoManage (String groupCode);
}
