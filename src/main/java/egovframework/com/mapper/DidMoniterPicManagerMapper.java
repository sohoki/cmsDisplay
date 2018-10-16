package egovframework.com.mapper;

import egovframework.let.sts.pic.service.DidMoniterPic;
import egovframework.let.sts.pic.service.DidMoniterPicVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import java.util.List;



@Mapper("DidMoniterPicManagerMappe")
public interface DidMoniterPicManagerMapper {

	public List<DidMoniterPicVO> selectDidMoniterPicManageListByPagination (DidMoniterPicVO SearchVO);
	
	public int selectDidMoniterPicManageListTotCnt_S  (DidMoniterPicVO SearchVO);
	
	public int insertDidMoniterPicManage(DidMoniterPic vo);
	
}
