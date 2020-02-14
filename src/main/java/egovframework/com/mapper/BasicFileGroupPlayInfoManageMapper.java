package egovframework.com.mapper;


import java.util.List;

import egovframework.let.sts.brd.service.BasicFileGroupPlayInfo;
import egovframework.let.sts.brd.service.BasicFileGroupPlayInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("BasicFileGroupPlayInfoManageMapper")
public interface BasicFileGroupPlayInfoManageMapper {

	public List<BasicFileGroupPlayInfoVO> selectPlayListInfo (BasicFileGroupPlayInfoVO searchVO );
	
	public List<BasicFileGroupPlayInfoVO> selectPlayListInfoNotCenter (BasicFileGroupPlayInfoVO searchVO );
	
	public int selectFileInserCheck (BasicFileGroupPlayInfoVO searchVO );
	
	public int insertFilePlay (BasicFileGroupPlayInfo vo );
	
	public int updateFilePlay (BasicFileGroupPlayInfo vo );
	
	public int deleteFilePlay (BasicFileGroupPlayInfo vo );
}
