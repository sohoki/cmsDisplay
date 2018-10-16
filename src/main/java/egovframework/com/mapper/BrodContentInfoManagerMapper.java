package egovframework.com.mapper;

import java.util.List;

import egovframework.let.sts.brd.service.BrodContentInfo;
import egovframework.let.sts.brd.service.BrodContentInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("BrodContentManageMapper")
public interface BrodContentInfoManagerMapper {

	public List<BrodContentInfoVO> selectBrodContentLst(BrodContentInfoVO searchVO);
	
	public BrodContentInfoVO selectBrodContentInfo(String brodCode);
	
	public List<BrodContentInfo> selectBrodContentCombo(); 
	
	public List<BrodContentInfo> selectBrodContentComboAnn();
	
	public List<BrodContentInfo> selectBrodContentCopy(String brodCode);
	
	public List<BrodContentInfoVO> selectBrodRight(BrodContentInfo brodContentInfo);
	
	public List<BrodContentInfo> selectBrodContentBasciContent(String brodCode);
	
	public String selectBrodContentCenterNm (String brodCode);
	
	public String selectBrodContentTimeInfo (String brodCode);
	
	public String selectBrodContentTimeInfoChar (String brodCode);
	
	public String selectBrodContentCenterCheckBrodCode(String centerId);
	
	public String selectBrodContentBasciBrodCode(String brodCode);
	
	public String selectBrodContentCenterPreBrodCode(String centerId);
	
	public String selectBrodContentBasciBrodCodePreBrodCode(String brodCode);
	
	public int selectBrodContentPageCnt(BrodContentInfoVO searchVO);
	
	public int insertBrodContent(BrodContentInfo vo);
	
	public int insertBrodContentCopy(BrodContentInfo vo);
	
	public int insertBrodContentCenterBrodCodeCopy(BrodContentInfo vo);
	
	public int updateBrodContent(BrodContentInfo vo);
	
	public int updateBrodContentSchChange(BrodContentInfo vo);
	
	public int updateBrodContentBasicInfo(BrodContentInfo vo);
	
	public int updateBrodContentBasicInfoName(BrodContentInfo vo);
	
	public int updateBrodContentCenterCntPlus(String brodCode);
	
	public int updateBrodContentCenterCntMin(String brodCode);
	
	public int updateBrodContentCenter(BrodContentInfo vo);
	
	public int updateBrodContentTimeInterval(BrodContentInfo vo);
	
	public int updateBrodContentBasicFileInfo(BrodContentInfo vo);
	
	public int updateBrodBasicCodeCntMin(String brodCode);
	
	public int deleteBrodContent(String brodCode);
	
	public int deleteBrodContentAll(String brodCode);
}
