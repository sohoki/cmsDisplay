package egovframework.let.cmm.use.service.impl;


import java.util.List;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.mapper.GroupManagerMapper;
import egovframework.let.cmm.use.service.Group;
import egovframework.let.cmm.use.service.GroupVo;
import egovframework.let.cmm.use.service.GroupManagerService;



@Service("GroupManagerService")
public class GroupManagerServiceImpl   extends EgovAbstractServiceImpl  implements GroupManagerService {

	
	@Resource(name="GroupManagerMapper")
	GroupManagerMapper groupManagerMapper;
	
	@Override
	public int deleteGroupManage(String codeId) throws Exception {
		return groupManagerMapper.deleteGroupManage(codeId);
	}

	@Override
	public int insertGroupManage(Group vo) throws Exception {
		return groupManagerMapper.insertGroupManage(vo);
	}

	@Override
	public int updateGroupManage(Group vo) throws Exception {
		return groupManagerMapper.updateGroupManage(vo);
	}

	@Override
	public Group selectGroupManageDetail(String codeId) throws Exception {
		return groupManagerMapper.selectGroupManageDetail(codeId);
	}

	@Override
	public List<GroupVo> selectUserGroupManageListByPagination(GroupVo searchVO)
			throws Exception {
		return groupManagerMapper.selectUserGroupManageListByPagination(searchVO);
	}

	@Override
	public List<GroupVo> selectGroupManageCombo(GroupVo vo) throws Exception {
		return groupManagerMapper.selectGroupManageCombo(vo);
	}

	@Override
	public int selectGroupManageListTotCnt_S(GroupVo searchVO) throws Exception {
		return groupManagerMapper.selectGroupManageListTotCnt_S(searchVO);
	}
	
	
	
}
