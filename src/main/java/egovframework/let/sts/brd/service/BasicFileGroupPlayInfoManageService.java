package egovframework.let.sts.brd.service;

import java.util.List;

public interface BasicFileGroupPlayInfoManageService {
	
	List<BasicFileGroupPlayInfoVO> selectPlayListInfo (BasicFileGroupPlayInfoVO searchVO )throws Exception;
	
	List<BasicFileGroupPlayInfoVO> selectPlayListInfoNotCenter (BasicFileGroupPlayInfoVO searchVO )throws Exception;
	
	int updateFilePlay (BasicFileGroupPlayInfoVO vo ) throws Exception;
	
	int deleteFilePlay (BasicFileGroupPlayInfoVO vo ) throws Exception;
}
