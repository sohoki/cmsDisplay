package com.cms.sohoki.sym.pro.service;

import java.util.List;

public interface ProgrameInfoService  {

	List<ProgrameInfoVO> selectProgramPageListInfo (ProgrameInfoVO searchVO) throws Exception;
	
	List<ProgrameInfoVO> selectProgramComboInfo (ProgrameInfoVO searchVO) throws Exception;
	
	ProgrameInfoVO selectProgramPageInfoDetail(String prodCode) throws Exception;
	
	int updateProgrameInfo(ProgrameInfo VO) throws Exception;
	
	int deleteProgrameInfo(String prodCode) throws Exception;	
	
	
}