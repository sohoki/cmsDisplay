package egovframework.com.mapper;


import java.util.List;
import egovframework.let.sts.brd.service.BrodContentDetail;
import egovframework.let.sts.brd.service.BrodContentDetailVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper("BrodContentDetailManageMapper")
public interface BrodContentDetailManagerMapper {
	
	public List<BrodContentDetailVO> selectBrodContentDetailLst(BrodContentDetailVO searchVO);
	
	public List<BrodContentDetail> selectTimeCombo(String endTime);
	
	public List<BrodContentDetail> selectTimeHourCombo();
	
	public List<BrodContentDetail> selectBrodFileList(BrodContentDetail vo);
	
	public BrodContentDetailVO selectBrodContenDetailt(String brodSeq);
	
	public int selectContentRegCnt(BrodContentDetail vo);
	
	public int selectContentRegTimeOverCheck(BrodContentDetail vo);
	
	public int selectContentRegTimeImsiOverTableCheck(BrodContentDetail vo);
	
	public int selectContentFileTime (String atchFileId);
	
	public int selectBrodContentDetailPageCnt(BrodContentDetailVO searchVO);
	
	public int insertBrodContentDetail(BrodContentDetail vo);
	
	public int insertBrodContentCopy(BrodContentDetail vo);
	
	public int insertBrodContentCenterCopy(BrodContentDetail vo);
	
	public int insertBrodContentScheduleOtherCopy(String brodCode);
	
	public int updateBrodContentDetail(BrodContentDetail vo);
	
	public int deleteBrodContentDetail(String brodSeq); 
	
	public int deleteBrodContentTimeDel(BrodContentDetail vo);
	
	public int deleteBrodContentBrodCode(String brodCode);
	
	public int deleteBrodContentBrodCodeALL(String brodCode);
	
	public int deleteBrodBasicBrod(String brodCode);
	           
	public int deleteContentDetailBasciContent(BrodContentDetail vo);
	
}
