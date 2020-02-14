package egovframework.let.sts.brd.service;

import java.util.List;

public interface BasicBrodFileIntervalInfoManageService {

	List<BasicBrodFileIntervalInfoVO> selectBasicBrodIntervalFileLst(BasicBrodFileIntervalInfoVO  searchVO) throws Exception;
	
	BasicBrodFileIntervalInfoVO selectBasicBrodIntervalFileDetail(String brodFileseq) throws Exception;
	
	List<BasicBrodFileIntervalInfoVO>selectAgentFileList (String basicCode) throws Exception;
	
	List<BasicBrodFileIntervalInfoVO> selectAgentFileDownList (String basicCode) throws Exception;
	
    int insertBasciBrodIntervalFile(BasicBrodFileIntervalInfoVO vo) throws Exception;
    
    int updateBasciBrodIntervalFile(BasicBrodFileIntervalInfoVO vo) throws Exception;
    
    int insertBasciBrodIntervalFileCopy(BasicBrodFileIntervalInfoVO vo) throws Exception;
    
    int deleteBasciBrodIntervalFile (BasicBrodFileIntervalInfoVO vo) throws Exception;
    
    int deleteBasciBrodBasicCodeInterval(String basicCode) throws Exception;
}
