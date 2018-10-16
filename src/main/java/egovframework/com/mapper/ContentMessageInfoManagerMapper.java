package egovframework.com.mapper;

import java.util.List;
import egovframework.let.sts.cnt.service.ContentMessageInfo;
import egovframework.let.sts.cnt.service.ContentMessageInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("MessageInfoManagerMapper")
public interface ContentMessageInfoManagerMapper {

	public List<ContentMessageInfoVO> selectContentMessageInfoListByPagination(ContentMessageInfoVO searchVO);
	
	public List<ContentMessageInfo> selectContentMessageInfoDidList(String didId);
	
    public int selectContentMessageInfoListTotCnt_S(ContentMessageInfoVO searchVO);
    
    public ContentMessageInfo selectContentMessageInfoDetail(String sendMsgId);
    
    public int insertContentMessageInfo(ContentMessageInfo vo);
    
    public int updateContentMessageInfo(ContentMessageInfo vo);
    
    public int updateContentMessageInfoClientManage(String sendMsgId);
    
    public int updateContentMessageInfoMsgId(ContentMessageInfo vo);
    
    public int deleteContentMessageInfo(String sendDidId);
    
    public int deleteContentMessageInfoMsgId(String sendMsgId);
	
	
	
}
