package egovframework.let.sts.mhs.service;

import java.util.List;

public interface MhsClassInfoManageService {

	List<MhsClassInfoVO> selectMhsClassList (MhsClassInfoVO mhsClassInfo) throws Exception;
		
	int selectMhsClassListCnt(MhsClassInfoVO mhsClassInfo) throws Exception;
	
	List<MhsClassInfo> selectMhsMoniterClassList(MhsClassInfo vo)throws Exception;
	
	MhsClassInfo selectMhsClassInfo(String mhsClasscd) throws Exception;
		
	int updateMhsClassInfo(MhsClassInfo vo) throws Exception;
	
	int deleteMhsClassInfo(String mhsClasscd) throws Exception;
}
