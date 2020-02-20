package com.cms.sohoki.sym.pro.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cms.sohoki.mapper.ProgrameInfoManageMapper;
import com.cms.sohoki.sym.pro.service.ProgrameInfoVO;
import com.cms.sohoki.sym.pro.service.ProgrameInfo;
import com.cms.sohoki.sym.pro.service.ProgrameInfoService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("ProgrameInfoService")
public class ProgrameInfoServiceImpl extends EgovAbstractServiceImpl implements ProgrameInfoService{

	@Resource(name="ProgrameInfoManageMapper")
	private ProgrameInfoManageMapper progMapper;

	@Resource(name="egovProgIdGnrService")
	private EgovIdGnrService egovProgIdGnrService;
	
	@Override
	public List<ProgrameInfoVO> selectProgramPageListInfo(
			ProgrameInfoVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return progMapper.selectProgramPageListInfo(searchVO);
	}

	@Override
	public ProgrameInfoVO selectProgramPageInfoDetail(String prodCode)
			throws Exception {
		// TODO Auto-generated method stub
		return progMapper.selectProgramPageInfoDetail(prodCode);
	}

	@Override
	public int updateProgrameInfo(ProgrameInfo VO) throws Exception {
		// TODO Auto-generated method stub
		int ret = 0;
		if (VO.getMode().equals("Ins")){
			VO.setProgCode(egovProgIdGnrService.getNextStringId());
			ret = progMapper.insertProgrameInfo(VO);
		}else {
			ret = progMapper.updateProgrameInfo(VO);
		}
		return ret;
	}

	@Override
	public int deleteProgrameInfo(String prodCode) throws Exception {
		// TODO Auto-generated method stub
		return progMapper.deleteProgrameInfo(prodCode);
	}
	
	
}
