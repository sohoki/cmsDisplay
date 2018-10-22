package egovframework.let.cmm.use.service;

import java.util.List;



public interface GroupManagerService {
	
	List<GroupVo> selectUserGroupManageListByPagination(GroupVo searchVO) throws Exception;
	
	Group selectGroupManageDetail(String codeId) throws Exception;
	
	int deleteGroupManage(String codeId) throws Exception;
	
	int insertGroupManage(Group vo) throws Exception;
	
	int updateGroupManage(Group vo) throws Exception;
	
	int selectGroupManageListTotCnt_S(GroupVo searchVO) throws Exception;
		
	List<GroupVo> selectGroupManageCombo(GroupVo vo) throws Exception;

    
	
}
