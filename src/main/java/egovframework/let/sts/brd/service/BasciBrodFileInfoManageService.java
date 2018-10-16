package egovframework.let.sts.brd.service;

import java.util.List;

public interface BasciBrodFileInfoManageService {
	
	List<BasciBrodFileInfoVO> selectBasicBrodFileLst(String basicCode) throws Exception;
	
	
	int insertBasciBrodFile(BasciBrodFileInfo vo)throws Exception;
	
	int insertBasciBrodFileCopy(BasciBrodFileInfoVO vo)throws Exception;
	
	int deleteBasciBrodFile(String basicSeq) throws Exception;
	
	int deleteBasciBrodBasicCode(String basicCode) throws Exception;
}
