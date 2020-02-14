package egovframework.com.mapper;

import java.util.List;

import egovframework.let.sts.brd.service.BasicFileGroupInfoVO;
import egovframework.let.sts.brd.service.BasicFileGroupInfo;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("BasicFileGroupInfoManageMapper")
public interface BasicFileGroupInfoManageMapper {
	
	public List<BasicFileGroupInfoVO> selectBasicGroupInfoLst(BasicFileGroupInfoVO searchVO);
	
	public BasicFileGroupInfoVO selectBasicGroupInfoDetail(String groupSeq);
	
	public String selectMaxGroupSeq();
	
	public BasicFileGroupInfoVO selectBasicGroupPreCheck(BasicFileGroupInfoVO searchVO);
	
	public List<BasicFileGroupInfoVO> selectBasicGroupInfoCombo();
	
	public int insertBasicGroupInfo(BasicFileGroupInfoVO vo);
	
	public int updateBasicGroupInfo(BasicFileGroupInfoVO vo);
	
	public int insertBasicGroupCopy(BasicFileGroupInfoVO vo);
	
	public int updateGroupFileCnt(BasicFileGroupInfoVO vo);
	
	public int deleteBasicGroup(String groupSeq);
	
	public int deleteBasicGroupBrodCode(String basciCode);

}
