package egovframework.com.mapper;

import java.util.List;
import egovframework.let.sts.cnt.service.ContentMutiInfo;
import egovframework.let.sts.cnt.service.ContentMutiInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper("ContentMutiInfoManagerMapper")
public interface ContentMutiInfoManagerMapper {

	public List<ContentMutiInfoVO> selectContentMutiInfoManageListByPagination(ContentMutiInfoVO searchVO);
	
	//did 콘텐츠 리스트 현황 
	public List<ContentMutiInfoVO>   selectDIDContentLst(ContentMutiInfoVO searchVO);
	
	public  List<ContentMutiInfoVO>  selectNextContentMutiInfo (String conSeq);
	
	public int selectContentMutiInfoManageListTotCnt_S(ContentMutiInfoVO searchVO);
	
	public ContentMutiInfoVO selectContentMutiInfoManageDetail(String conSeq);
	
	public ContentMutiInfoVO selectContentMutiInfoManageView(String conSeq);
	
	//신규 
	public List<ContentMutiInfo> selectNextSeqList (String conSeq);
	
	
	public String selectContentFileInfo (String conSeq);
	
	public String selectContentFileInfoLocal(String conSeq);
	
	public String selectMaxTimeInterval(String conSeq);
	
	public String selectMaxSeqInfo();
	
	public int insertContentMutiInfoManage(ContentMutiInfo vo);
	
	public int updateContentMutiInfoManage(ContentMutiInfo vo);
	
	public int updateContentMutiFile (ContentMutiInfo vo);
	
	public int updateContentMutiFileLocal (ContentMutiInfo vo);
	
	public int deleteContentMutiInfoManage(String conSeq);
	
	
}

