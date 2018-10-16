package egovframework.com.mapper;

import java.util.List;
import egovframework.let.sym.sch.service.ContentSendHistoryInfo;
import egovframework.let.sym.sch.service.ContentSendHistoryInfoVO;
import  egovframework.let.sym.sch.service.ScheduleInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;



@Mapper("ContentHistoryInfoManagerMapper")
public interface ContentSendHistoryInfoManagerMapper  {

	public List<ScheduleInfoVO> selectContentSendHistoryInfoManage (ContentSendHistoryInfoVO searchVO);

	public int insertContentSendHistoryInfoManage(ContentSendHistoryInfo vo);
	
	public int  updateContentSendHistoryInfoManage (ContentSendHistoryInfo vo);
	
	public int updateContentSendHistoryDidInfoManage(String hisSeq);
	
	public int updateContentSendHistoryConInfoManage(String hisSeq);
	
	public int updateContentSendHistoryConPageInfoManage(String hisSeq);
	
	public int updateContentSendHistoryConFileInfoManage(String hisSeq);
	
	public int updateContentSchUpdateCheckReset(String hisSeq);
		
	public int deleteContentSendHistoryInfoManage(String schCode);
	
	
	
}
