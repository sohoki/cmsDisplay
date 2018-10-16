package egovframework.com.mapper;

import java.util.List;

import egovframework.let.sym.grp.service.GroupDidInfo;
import egovframework.let.sym.grp.service.GroupDidInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper("GroupDidInfoManagerMapper")
public interface GroupDidInfoManagerMapper {


		public List<GroupDidInfoVO> selectGroupInfoManageListByPagination(String groupCode);
		
		public int selectGroupIDidCheckInfoManageListTotCnt_S(String groupId, String didId);
		
		public int selectGroupInfoManageListTotCnt_S(String groupCode);
		
		public int insertGroupInfoManage(GroupDidInfo vo);
		
		public int deleteGroupInfoManage(String didId);


	   public List<GroupDidInfo> selectComboLst();
	   
	   
	   
}
