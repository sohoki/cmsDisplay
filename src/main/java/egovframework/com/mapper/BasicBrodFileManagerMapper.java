package egovframework.com.mapper;


import java.util.List;
import egovframework.let.sts.brd.service.BasciBrodFileInfo;
import egovframework.let.sts.brd.service.BasciBrodFileInfoVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("BasicBrodFileManagerMapper")
public interface BasicBrodFileManagerMapper {

	
	public List<BasciBrodFileInfoVO> selectBasicBrodFileLst(String basicCode) throws Exception;
	
	public int insertBasciBrodFile(BasciBrodFileInfo vo)throws Exception;
	
	public int insertBasciBrodFileCopy(BasciBrodFileInfoVO vo)throws Exception;
	
	public int deleteBasciBrodFile(String basicSeq) throws Exception;
	
	public int deleteBasciBrodBasicCode(String basicCode);
}
