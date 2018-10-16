package egovframework.com.mapper;



import egovframework.let.sts.cnt.service.ContentDetailFileInfo;
import egovframework.let.sts.cnt.service.ContentDetailFileInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import java.util.List;

@Mapper("ContentDetailFileInfoManagerMapper")
public interface ContentDetailFileInfoManagerMapper {

	public List<ContentDetailFileInfoVO> selectContentDetailFileLst(ContentDetailFileInfoVO searchVO);
	
	public ContentDetailFileInfoVO selectContentDetailFileInfo(String atchFileId);
	
	public ContentDetailFileInfoVO selectContentDetailFileInfoFileSeq (String fileSeq);
	
	
	public String selectDetailContentSumTime (String detailSeq);
	
	public int selectPageSeqCheckFilePageCnt(ContentDetailFileInfoVO searchVO);
	
	public int selectTimeIntevalNullCheck(String conSeq);
	
	public int selectMaxfileSeq(ContentDetailFileInfo vo);
	
	public int insertContentDetailFileManage(ContentDetailFileInfo vo);
	
	public int updateContentDetailFileManage(ContentDetailFileInfo vo);
	
	public int updateContentOrderDetailFileManage(ContentDetailFileInfo vo);
	
	public int updateContentDetailFileTimeIntervalManage(ContentDetailFileInfo vo);
	
	public int deleteContentDetailFileManage(String fileSeq);
	
	
}
