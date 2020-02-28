package com.cms.sohoki.mapper;

import java.util.List;
import com.cms.sohoki.sym.pro.service.ProgDeployInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("ProgDeplyInfoManageMapper")
public interface ProgDeployInfoManageMapper {

	public List<ProgDeployInfoVO> selectProgramDeployListInfo(ProgDeployInfoVO searchVO);
	
	public ProgDeployInfoVO selectProgramDeployDetail(String depSeq);

	public int insertProgramDeploy(ProgDeployInfoVO vo);
	
	public int updateProgramDeploy(ProgDeployInfoVO vo);
	
	public int deleteProgramDeploy(String depSeq);
}
