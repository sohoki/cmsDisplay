package egovframework.let.sts.brd.service;

import java.util.List;

public interface BrodContentDetailManagerService {

	
    List<BrodContentDetailVO> selectBrodContentDetailLst(BrodContentDetailVO searchVO) throws Exception;
	
    List<BrodContentDetail> selectTimeCombo(String endTime)throws Exception;
    
    List<BrodContentDetail> selectTimeHourCombo()throws Exception;
    
    List<BrodContentDetail> selectBrodFileList(BrodContentDetail vo) throws Exception;
    
	BrodContentDetailVO selectBrodContenDetailt(String brodSeq)throws Exception;
	
    int selectContentRegCnt(BrodContentDetail vo) throws Exception;
	
	int selectContentRegTimeOverCheck(BrodContentDetail vo) throws Exception;
	
	int selectContentRegTimeImsiOverTableCheck(BrodContentDetail vo) throws Exception;
	
	int selectContentFileTime (String atchFileId) throws Exception;
	
	int selectBrodContentDetailPageCnt(BrodContentDetailVO searchVO)throws Exception;
	
	int insertBrodContentDetail(BrodContentDetail vo)throws Exception;
	
	int insertBrodContentCopy(BrodContentDetail vo) throws Exception;
	
	int insertBrodContentCenterCopy(BrodContentDetail vo) throws Exception;
	
	int insertBrodContentScheduleOtherCopy(String brodCode) throws Exception;
	
	int updateBrodContentDetail(BrodContentDetail vo)throws Exception;
	
	int deleteBrodContentDetail(String brodSeq)throws Exception;
	
	int deleteBrodContentTimeDel(BrodContentDetail vo)throws Exception;
	
	int deleteBrodContentBrodCode (String brodCode)throws Exception;
	
	int deleteBrodContentBrodCodeALL(String brodCode)throws Exception;
	
	int deleteBrodBasicBrod(String brodCode)throws Exception;
	
	int deleteContentDetailBasciContent(BrodContentDetail vo) throws Exception;
}
