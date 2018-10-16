package egovframework.com.mapper;

import java.util.List;
import egovframework.let.sts.snd.service.SendMsgInfo;
import egovframework.let.sts.snd.service.SendMsgInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("SendMsgInfoManagerMapper")
public interface SendMsgInfoManagerMapper {

	
	
	public List<SendMsgInfoVO> selectSendMsgInfoManageListByPagination(SendMsgInfoVO searchVO);
	
	public  List<SendMsgInfoVO>  selectDIDOrderLst (SendMsgInfoVO searchVO);
	
	
	public int selectSendMsgInfoManageListTotCnt_S(SendMsgInfoVO searchVO);
	
	
	
	
	public int selectDIDScheduleCount(SendMsgInfo vo);
	
	public int selectDIDOrderCount ( SendMsgInfo vo);
	
	public int selectDIDMessageCount ( SendMsgInfo vo);
	
	public int insertSendMsgInfoManage(SendMsgInfo vo);
	
	public int insertSendMsgInfoManageState(SendMsgInfo vo);
	
	public int insertSendMessageInsertManage(SendMsgInfo vo);
	
	public int updateSendMsgInfoManage(SendMsgInfo vo);
	
	public int selectMaxSeq();
	
	public int selectSendDidIDCheckCnt(String sendDidId);
	
	public String selectSendDidIDMsgSeq(String sendDidId);
}
