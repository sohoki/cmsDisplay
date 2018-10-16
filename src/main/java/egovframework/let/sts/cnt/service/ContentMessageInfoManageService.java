package egovframework.let.sts.cnt.service;

import java.util.List;

public interface ContentMessageInfoManageService {

	
    List<ContentMessageInfoVO> selectContentMessageInfoListByPagination(ContentMessageInfoVO searchVO) throws Exception;
    
    List<ContentMessageInfo> selectContentMessageInfoDidList(String didId)throws Exception;
	
    int selectContentMessageInfoListTotCnt_S(ContentMessageInfoVO searchVO)throws Exception;
    
    ContentMessageInfo selectContentMessageInfoDetail(String sendMsgId)throws Exception;
    
    int insertContentMessageInfo(ContentMessageInfo vo)throws Exception;
    
    int updateContentMessageInfo(ContentMessageInfo vo)throws Exception;
    
    int updateContentMessageInfoClientManage(String sendMsgId)throws Exception;
    
    int deleteContentMessageInfo(String sendDidId)throws Exception;
    
    int updateContentMessageInfoMsgId(ContentMessageInfo vo)throws Exception;
    
    int deleteContentMessageInfoMsgId(String sendMsgId)throws Exception;
}
