package egovframework.let.sts.brd.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.com.mapper.BasicBrodFileIntervalManageMapper;
import egovframework.com.mapper.BasicFileGroupInfoManageMapper;
import egovframework.let.sts.brd.service.BasicBrodFileIntervalInfo;
import egovframework.let.sts.brd.service.BasicBrodFileIntervalInfoVO;
import egovframework.let.sts.brd.service.BasicBrodFileIntervalInfoManageService;
import egovframework.let.sts.brd.service.BasicFileGroupInfoManageService;
import egovframework.let.sts.brd.service.BasicFileGroupInfoVO;


@Service("BasicBrodFileIntervalInfoManageService")
public class BasicBrodFileIntervalInfoManageServiceImpl  extends EgovAbstractServiceImpl  implements BasicBrodFileIntervalInfoManageService {
	
	@Resource(name= "BasicBrodFileIntervalManageMapper")
	private BasicBrodFileIntervalManageMapper fileIntervalinfo;
	
	@Resource(name= "BasicFileGroupInfoManageMapper")
	private BasicFileGroupInfoManageMapper groupInfo;
	

	@Override
	public List<BasicBrodFileIntervalInfoVO> selectBasicBrodIntervalFileLst(
			BasicBrodFileIntervalInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return fileIntervalinfo.selectBasicBrodIntervalFileLst(searchVO);
	}

	@Override
	public BasicBrodFileIntervalInfoVO selectBasicBrodIntervalFileDetail(
			String brodFileseq) throws Exception {
		// TODO Auto-generated method stub
		return fileIntervalinfo.selectBasicBrodIntervalFileDetail(brodFileseq);
	}

	@Override
	public int insertBasciBrodIntervalFile(BasicBrodFileIntervalInfoVO vo)
			throws Exception {
		// TODO Auto-generated method stub
		
		BasicFileGroupInfoVO group = groupInfo.selectBasicGroupInfoDetail(vo.getGroupSeq());
		
		group.setFilePls("pls");
		
		vo.setBrodStarttime(group.getGroupStarttime());
		vo.setBrodEndtime(group.getGroupEndtime());
		int ret = fileIntervalinfo.insertBasciBrodIntervalFile(vo);
		if (ret > 0){
			groupInfo.updateGroupFileCnt(group);  
		}
		return ret;
	}

	@Override
	public int updateBasciBrodIntervalFile(BasicBrodFileIntervalInfoVO vo)
			throws Exception {
		// TODO Auto-generated method stub
		return fileIntervalinfo.updateBasciBrodIntervalFile(vo);
	}

	@Override
	public int insertBasciBrodIntervalFileCopy(BasicBrodFileIntervalInfoVO vo)
			throws Exception {
		// TODO Auto-generated method stub
		return fileIntervalinfo.insertBasciBrodIntervalFileCopy(vo);
	}

	@Override
	public int deleteBasciBrodIntervalFile(BasicBrodFileIntervalInfoVO vo) throws Exception {
		// TODO Auto-generated method stub
		
		//파일 수량 확인 하기 
		BasicFileGroupInfoVO group  = new BasicFileGroupInfoVO();
        group.setFileMin("Min");
        group.setGroupSeq(vo.getGroupSeq());
		
		int ret = fileIntervalinfo.deleteBasciBrodIntervalFile(vo.getBrodFileseq());
		if (ret > 0){
			groupInfo.updateGroupFileCnt(group);  
		}
		return ret;
		
	}

	@Override
	public int deleteBasciBrodBasicCodeInterval(String basicCode)
			throws Exception {
		// TODO Auto-generated method stub
		return fileIntervalinfo.deleteBasciBrodBasicCodeInterval(basicCode);
	}

	@Override
	public List<BasicBrodFileIntervalInfoVO> selectAgentFileList(String basicCode) throws Exception {
		// TODO Auto-generated method stub
		return fileIntervalinfo.selectAgentFileList(basicCode);
	}

	@Override
	public List<BasicBrodFileIntervalInfoVO> selectAgentFileDownList(String basicCode) throws Exception {
		// TODO Auto-generated method stub
		return fileIntervalinfo.selectAgentFileDownList(basicCode);
	}

}
