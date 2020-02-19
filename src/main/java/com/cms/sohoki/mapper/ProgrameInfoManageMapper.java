package com.cms.sohoki.mapper;

import java.util.List;

import com.cms.sohoki.sym.pro.service.ProgrameInfoVO;
import com.cms.sohoki.sym.pro.service.ProgrameInfo;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("ProgrameInfoManageMapper")
public interface ProgrameInfoManageMapper {

	public List<ProgrameInfoVO> selectProgramPageListInfo (ProgrameInfoVO searchVO);
	
	public ProgrameInfoVO selectProgramPageInfoDetail(String prodCode);
	
	public int insertProgrameInfo(ProgrameInfo VO);
	
	public int updateProgrameInfo(ProgrameInfo VO);
	
	public int deleteProgrameInfo(String prodCode);
	
}
