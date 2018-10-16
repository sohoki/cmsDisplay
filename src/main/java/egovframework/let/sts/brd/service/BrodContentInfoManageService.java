package egovframework.let.sts.brd.service;

import java.util.List;

public interface BrodContentInfoManageService {

    List<BrodContentInfoVO> selectBrodContentLst(BrodContentInfoVO searchVO) throws Exception;
	
    List<BrodContentInfo>  selectBrodContentCombo() throws Exception;
    
    List<BrodContentInfo> selectBrodContentComboAnn() throws Exception;
    
    List<BrodContentInfo> selectBrodContentBasciContent(String brodCode) throws Exception;
    
	BrodContentInfoVO selectBrodContentInfo(String brodCode)throws Exception;
	
	String selectBrodContentCenterNm (String brodCode)throws Exception;
	
	String selectBrodContentTimeInfo(String brodCode)throws Exception;
	
	String selectBrodContentTimeInfoChar (String brodCode)throws Exception;
	
	String selectBrodContentCenterCheckBrodCode(String centerId) throws Exception;
	
	String selectBrodContentBasciBrodCode(String brodCode) throws Exception;
	
	String selectBrodContentCenterPreBrodCode(String centerId)throws Exception;
	
	List<BrodContentInfo> selectBrodContentCopy(String brodCode) throws Exception;
	
	List<BrodContentInfoVO> selectBrodRight(BrodContentInfo brodContentInfo) throws Exception;
	
	
	String selectBrodContentBasciBrodCodePreBrodCode(String brodCode) throws Exception;
	
	int selectBrodContentPageCnt(BrodContentInfoVO searchVO)throws Exception;
	
	int insertBrodContent(BrodContentInfo vo)throws Exception;
	
	int insertBrodContentCopy(BrodContentInfo vo)throws Exception;
	
	int insertBrodContentCenterBrodCodeCopy(BrodContentInfo vo)throws Exception;
	
	int updateBrodContent(BrodContentInfo vo)throws Exception;
	
	int updateBrodContentSchChange(BrodContentInfo vo)throws Exception;
	
	int updateBrodContentCenterCntPlus(String brodCode)throws Exception;
	
	int updateBrodContentCenterCntMin(String brodCode)throws Exception;
	
	int updateBrodContentCenter(BrodContentInfo vo) throws Exception;
	
	int updateBrodContentTimeInterval(BrodContentInfo vo)throws Exception;
	
	int updateBrodContentBasicInfo(BrodContentInfo vo) throws Exception;
	
	int updateBrodContentBasicInfoName(BrodContentInfo vo) throws Exception;
	
	int updateBrodContentBasicFileInfo(BrodContentInfo vo) throws Exception;
	
	int updateBrodBasicCodeCntMin(String brodCode)throws Exception;
	
	int deleteBrodContent(String brodCode)throws Exception;
	
	int deleteBrodContentAll(String brodCode) throws Exception;
}
