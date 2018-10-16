package egovframework.let.sts.pic.service;

import java.util.List;

public interface DidMoniterPicService {
	
	List<DidMoniterPicVO> selectDidMoniterPicManageListByPagination(DidMoniterPicVO searchVO) throws Exception;
	
	int selectDidMoniterPicManageListTotCnt_S( DidMoniterPicVO searchVO) throws Exception;
	
	int insertDidMoniterPicManage(DidMoniterPic vo) throws Exception;
	
}
