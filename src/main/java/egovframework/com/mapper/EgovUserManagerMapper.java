package egovframework.com.mapper;

import java.util.List;
import egovframework.let.uat.uia.service.GnrMber;
import egovframework.let.uat.uia.service.GnrMberVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper("UserManagerMapper")
public interface EgovUserManagerMapper {

	public List<GnrMberVO> selectUserManageListByPagination(GnrMberVO vo);
	
	public GnrMberVO selectUserManageDetail(String code);
	
	public int insertUserManage(GnrMber vo);
	               
	public int updateUserManage(GnrMber vo);
	
	public int deleteUserManage(String code);
		
	public int selectUserManageListTotCnt_S(GnrMberVO vo);
	
	public int selectUserMangerIDCheck(String code);
	
	
	public int selectPwSearchUserInfo(GnrMberVO vo);
	
	
}
