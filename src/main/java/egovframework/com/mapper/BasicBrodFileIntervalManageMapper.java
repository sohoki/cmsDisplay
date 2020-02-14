package egovframework.com.mapper;

import java.util.List;

import egovframework.let.sts.brd.service.BasicBrodFileIntervalInfo;
import egovframework.let.sts.brd.service.BasicBrodFileIntervalInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("BasicBrodFileIntervalManageMapper")
public interface BasicBrodFileIntervalManageMapper {

	public List<BasicBrodFileIntervalInfoVO> selectBasicBrodIntervalFileLst(BasicBrodFileIntervalInfoVO  searchVO);
	
	public BasicBrodFileIntervalInfoVO selectBasicBrodIntervalFileDetail(String brodFileseq);
	
	public List<BasicBrodFileIntervalInfoVO> selectAgentFileList (String basicCode);
	
	public List<BasicBrodFileIntervalInfoVO> selectAgentFileDownList (String basicCode);
	
    public int insertBasciBrodIntervalFile(BasicBrodFileIntervalInfoVO vo);
    
    public int updateBasciBrodIntervalFile(BasicBrodFileIntervalInfoVO vo);
    
    public int insertBasciBrodIntervalFileCopy(BasicBrodFileIntervalInfoVO vo);
    
    public int deleteBasciBrodIntervalFile (String brodFileseq);
    
    public int deleteBasciBrodIntervalGroupFile (String groupSeq);
    
    public int deleteBasciBrodBasicCodeInterval(String basicCode);
	
}
