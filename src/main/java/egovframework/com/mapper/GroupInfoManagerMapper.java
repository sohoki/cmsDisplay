package egovframework.com.mapper;


import java.util.List;

import egovframework.let.sym.grp.service.GroupInfo;
import egovframework.let.sym.grp.service.GroupInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;



@Mapper("GroupInfoManagerMapper")
public interface GroupInfoManagerMapper {

	public List<GroupInfoVO>  selectGroupInfoManageListByPagination(GroupInfoVO searchVO);
	
	public List<GroupInfoVO> selectGroupInfoManageCombo(GroupInfoVO vo);
	
	public GroupInfo selectGroupInfoManageDetail (String groupCode);
	
	public int  selectGroupIDInfoManageListTotCnt_S(String groupCode);
	
	public int  selectGroupInfoManageListTotCnt_S(GroupInfoVO searchVO);
	
	public String selectLastInsertGroup();
	
	public int  insertGroupInfoManage(GroupInfo vo);
	
	public int  updateGroupInfoManage(GroupInfo vo);
	
	public int  deleteGroupInfoManage (String groupCode);
	
	
}
