package egovframework.let.sym.sch.service;

import java.util.List;

public interface ContentSendHistoryInfoManagerService {

	
	List<ScheduleInfoVO> selectContentSendHistoryInfoManage (ContentSendHistoryInfoVO searchVO) throws Exception;

	int insertContentSendHistoryInfoManage(ContentSendHistoryInfo sendHistory) throws Exception;
	
	int  updateContentSendHistoryInfoManage (ContentSendHistoryInfo vo) throws Exception;
	
	int updateContentSendHistoryDidInfoManage(String hisSeq) throws Exception;
	
	int updateContentSendHistoryConInfoManage(String hisSeq) throws Exception;
	
	int updateContentSendHistoryConPageInfoManage(String hisSeq) throws Exception;
	
	int updateContentSendHistoryConFileInfoManage(String hisSeq) throws Exception;
	
	int updateContentSchUpdateCheckReset(String hisSeq) throws Exception;
	
	int deleteContentSendHistoryInfoManage(String schCode) throws Exception;
	
}
