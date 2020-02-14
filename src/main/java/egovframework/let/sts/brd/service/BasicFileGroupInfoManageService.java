package egovframework.let.sts.brd.service;

import java.util.List;

public interface BasicFileGroupInfoManageService {

    List<BasicFileGroupInfoVO> selectBasicGroupInfoLst(BasicFileGroupInfoVO searchVO) throws Exception;
	
    List<BasicFileGroupInfoVO> selectBasicGroupInfoCombo() throws Exception;
    
	BasicFileGroupInfoVO selectBasicGroupInfoDetail(String groupSeq) throws Exception;
	
	BasicFileGroupInfoVO selectBasicGroupPreCheck(BasicFileGroupInfoVO searchVO) throws Exception;
	
	int insertBasicGroupInfo(BasicFileGroupInfoVO vo) throws Exception;
	
	int updateBasicGroupInfo(BasicFileGroupInfoVO vo) throws Exception;
	
	int updateBasicGroupInfoCopy(BasicFileGroupInfoVO vo) throws Exception;
	
	int deleteBasicGroup(String groupSeq) throws Exception;
	
	int deleteBasicGroupBrodCode(String basciCode) throws Exception;
}
