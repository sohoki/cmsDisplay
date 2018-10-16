package egovframework.com.mapper;

import java.util.List;

import egovframework.let.sts.cnt.service.ContentInfo;
import egovframework.let.sts.cnt.service.ContentInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper("ContentInfoManagerMapper")
public interface ContentInfoManagerMapper {


	        public int   selectContentInfoManageListTotCnt_S(ContentInfoVO SearchVO);
			
			public List<ContentInfoVO>   selectContentInfoManageListByPagination(ContentInfoVO SearchVO);
			
			public List<ContentInfo> selectNextCombo (String conSeq);
			
			public List<ContentInfo> selectSearcHCombo (String searchKeyword);
			
			public ContentInfoVO selectContentInfoManageDetail(String conSeq);
			
			
			
			public int insertContentInfoManage(ContentInfo vo);
			
			public int updateContentInfoManage(ContentInfo vo);
			
			public int deleteContentInfoManage (String conSeq);
	
	
	
	
}
