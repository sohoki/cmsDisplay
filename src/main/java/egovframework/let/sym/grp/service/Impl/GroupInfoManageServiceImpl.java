package egovframework.let.sym.grp.service.Impl;


import java.util.List;

import org.springframework.stereotype.Service;

import egovframework.let.sym.grp.service.GroupInfo;
import egovframework.let.sym.grp.service.GroupInfoVO;
import egovframework.let.sym.grp.service.GroupInfoManageService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import egovframework.com.mapper.GroupInfoManagerMapper;

@Service("GroupInfoManageService")
public class GroupInfoManageServiceImpl extends EgovAbstractServiceImpl implements  GroupInfoManageService{

	@Resource(name="GroupInfoManagerMapper")
	private GroupInfoManagerMapper groupInfoManagerMapper;
	
	@Override
	public List<GroupInfoVO> selectGroupInfoManageListByPagination(
			GroupInfoVO searchVO) {
		// TODO Auto-generated method stub
		return groupInfoManagerMapper.selectGroupInfoManageListByPagination(searchVO);
	}

	@Override
	public List<GroupInfoVO> selectGroupInfoManageCombo(GroupInfoVO searchVO) {
		// TODO Auto-generated method stub
		return groupInfoManagerMapper.selectGroupInfoManageCombo(searchVO);
	}

	@Override
	public GroupInfo selectGroupInfoManageDetail(String groupCode) {
		// TODO Auto-generated method stub
		return groupInfoManagerMapper.selectGroupInfoManageDetail(groupCode);
	}

	@Override
	public int selectGroupIDInfoManageListTotCnt_S(String groupCode) {
		// TODO Auto-generated method stub
		return groupInfoManagerMapper.selectGroupIDInfoManageListTotCnt_S(groupCode);
	}

	@Override
	public int selectGroupInfoManageListTotCnt_S(GroupInfoVO searchVO) {
		// TODO Auto-generated method stub
		return groupInfoManagerMapper.selectGroupInfoManageListTotCnt_S(searchVO);
	}

	@Override
	public int insertGroupInfoManage(GroupInfo vo) {
		// TODO Auto-generated method stub
		return groupInfoManagerMapper.insertGroupInfoManage(vo);
	}

	@Override
	public int updateGroupInfoManage(GroupInfo vo) {
		// TODO Auto-generated method stub
		return groupInfoManagerMapper.updateGroupInfoManage(vo);
	}

	@Override
	public int deleteGroupInfoManage(String groupCode) {
		// TODO Auto-generated method stub
		return groupInfoManagerMapper.deleteGroupInfoManage(groupCode);
	}
	
	
	
}
