package egovframework.let.sts.brd.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.let.sts.brd.service.BasicFileGroupInfoManageService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.let.sts.brd.service.BasicBrodFileIntervalInfoVO;
import egovframework.let.sts.brd.service.BasicFileGroupInfo;
import egovframework.let.sts.brd.service.BasicFileGroupInfoVO;
import egovframework.com.mapper.BasicBrodFileIntervalManageMapper;
import egovframework.com.mapper.BasicFileGroupInfoManageMapper;



@Service("BasicFileGroupInfoManageService")
public class BasicFileGroupInfoManageServiceImpl extends EgovAbstractServiceImpl implements BasicFileGroupInfoManageService {

	
	@Resource(name="BasicFileGroupInfoManageMapper")
	private BasicFileGroupInfoManageMapper basicGroupInfo;

	
	@Resource(name= "BasicBrodFileIntervalManageMapper")
	private BasicBrodFileIntervalManageMapper fileIntervalinfo;
	
	@Override
	public List<BasicFileGroupInfoVO> selectBasicGroupInfoLst(
			BasicFileGroupInfoVO searchVO) {
		// TODO Auto-generated method stub
		
		//System.out.println("searchVO:" + searchVO.getBasicCode() + ":" + searchVO.getBasicCode().length());
		
		return basicGroupInfo.selectBasicGroupInfoLst(searchVO);
	}

	@Override
	public BasicFileGroupInfoVO selectBasicGroupInfoDetail(String groupSeq) {
		// TODO Auto-generated method stub
		return basicGroupInfo.selectBasicGroupInfoDetail(groupSeq);
	}

	@Override
	public int insertBasicGroupInfo(BasicFileGroupInfoVO vo) {
		// TODO Auto-generated method stub
		return basicGroupInfo.insertBasicGroupInfo(vo);
	}

	@Override
	public int updateBasicGroupInfo(BasicFileGroupInfoVO vo) {
		// TODO Auto-generated method stub
		
		int ret = 0;
		if (vo.getMode().equals("Ins")){
			ret = basicGroupInfo.insertBasicGroupInfo(vo);
		}else {
			ret = basicGroupInfo.updateBasicGroupInfo(vo);
		}
		return ret;
	}

	@Override
	public int deleteBasicGroup(String groupSeq) {
		// TODO Auto-generated method stub
		fileIntervalinfo.deleteBasciBrodIntervalGroupFile(groupSeq);
		return basicGroupInfo.deleteBasicGroup(groupSeq);
	}

	@Override
	public int deleteBasicGroupBrodCode(String basciCode) {
		// TODO Auto-generated method stub
		return basicGroupInfo.deleteBasicGroupBrodCode(basciCode);
	}

	@Override
	public BasicFileGroupInfoVO selectBasicGroupPreCheck(
			BasicFileGroupInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return basicGroupInfo.selectBasicGroupPreCheck(searchVO);
	}

	@Override
	public List<BasicFileGroupInfoVO> selectBasicGroupInfoCombo()
			throws Exception {
		// TODO Auto-generated method stub
		return basicGroupInfo.selectBasicGroupInfoCombo();
	}

	@Override
	public int updateBasicGroupInfoCopy(BasicFileGroupInfoVO vo)
			throws Exception {
		// TODO Auto-generated method stub
		int ret = basicGroupInfo.insertBasicGroupCopy(vo);
		if (ret > 0){
			String groupSeq = basicGroupInfo.selectMaxGroupSeq();			
			BasicBrodFileIntervalInfoVO fileVo = new BasicBrodFileIntervalInfoVO();
			fileVo.setBasicCode(vo.getBasicCode());
			fileVo.setBrodStarttime(vo.getGroupStarttime());
			fileVo.setBrodEndtime(vo.getGroupEndtime());
			fileVo.setGroupSeq(groupSeq);
			fileVo.setCp_copyGroupSeq(vo.getCp_copyGroupSeq());
			fileVo.setUserId(vo.getUserId());
			
			fileIntervalinfo.insertBasciBrodIntervalFileCopy(fileVo);
		}
		return 0;
	}
	
}
