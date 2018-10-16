package egovframework.let.sym.grp.service.Impl;


import java.util.List;

import org.springframework.stereotype.Service;

import egovframework.let.sym.grp.service.GroupDidInfo;
import egovframework.let.sym.grp.service.GroupDidInfoVO;
import egovframework.let.sym.grp.service.GroupDidInfoManageService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import egovframework.com.mapper.GroupDidInfoManagerMapper;

@Service("GroupDidInfoManageService")
public class GroupDidInfoManageServiceImpl extends EgovAbstractServiceImpl implements GroupDidInfoManageService {

	
	@Resource(name="GroupDidInfoManagerMapper")
	private GroupDidInfoManagerMapper groupDidInfoManagerMapper;
	
	@Override
	public List<GroupDidInfoVO> selectGroupInfoManageListByPagination(
			String groupCode) {
		// TODO Auto-generated method stub
		return groupDidInfoManagerMapper.selectGroupInfoManageListByPagination(groupCode);
	}

	@Override
	public int selectGroupIDidCheckInfoManageListTotCnt_S(String groupId,
			String didId) {
		// TODO Auto-generated method stub
		return groupDidInfoManagerMapper.selectGroupIDidCheckInfoManageListTotCnt_S(groupId, didId);
	}

	@Override
	public int selectGroupInfoManageListTotCnt_S(String groupCode) {
		// TODO Auto-generated method stub
		return groupDidInfoManagerMapper.selectGroupInfoManageListTotCnt_S(groupCode);
	}

	@Override
	public int insertGroupInfoManage(GroupDidInfo vo) {
		// TODO Auto-generated method stub
		return groupDidInfoManagerMapper.insertGroupInfoManage(vo);
	}

	@Override
	public int deleteGroupInfoManage(String didId) {
		// TODO Auto-generated method stub
		return groupDidInfoManagerMapper.deleteGroupInfoManage(didId);
	}

	@Override
	public List<GroupDidInfo> selectComboLst() {
		// TODO Auto-generated method stub
		return groupDidInfoManagerMapper.selectComboLst();
	}
	
	
}
