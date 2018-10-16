package egovframework.let.sts.snd.service;


import java.util.List;

public interface SendMsgInfoManageService {

	
	
    List<SendMsgInfoVO> selectSendMsgInfoManageListByPagination(SendMsgInfoVO searchVO) throws Exception;
	
    List<SendMsgInfoVO>  selectDIDOrderLst (SendMsgInfoVO searchVO)throws Exception;
    
	int selectSendMsgInfoManageListTotCnt_S(SendMsgInfoVO searchVO) throws Exception;
	
	int selectDIDScheduleCount(SendMsgInfo vo) throws Exception;
	
	int selectDIDOrderCount (SendMsgInfo vo) throws Exception;
	
	int selectDIDMessageCount ( SendMsgInfo vo) throws Exception;
	
	int insertSendMsgInfoManage(SendMsgInfo vo) throws Exception;
	
	int insertSendMsgInfoManageState (SendMsgInfo vo) throws Exception;
	
	int insertSendMessageInsertManage(SendMsgInfo vo)throws Exception;
	
	int updateSendMsgInfoManage (SendMsgInfo vo) throws Exception;
	
	
	int selectMaxSeq() throws Exception;
	
	int selectSendDidIDCheckCnt(String sendDidId) throws Exception;
	
	String selectSendDidIDMsgSeq(String sendDidId)throws Exception;
	
}
