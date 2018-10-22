package egovframework.com.mapper;

import java.util.List;

import egovframework.let.cmm.use.service.Group;
import egovframework.let.cmm.use.service.GroupVo;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper("GroupManagerMapper")
public interface GroupManagerMapper {

	public List<GroupVo> selectUserGroupManageListByPagination(GroupVo vo);
	
	public Group selectGroupManageDetail(String codeId);
	
	public int insertGroupManage(Group vo);
	               
	public int updateGroupManage(Group vo);
	
	public int deleteGroupManage(String codeId);
		
	public int selectGroupManageListTotCnt_S(GroupVo vo);
	
	public List<GroupVo> selectGroupManageCombo(GroupVo vo);
	
	
}
