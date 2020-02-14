package egovframework.com.mapper;

import java.util.List;

import egovframework.let.sym.did.service.ProgramInfo;
import egovframework.let.sym.did.service.ProgramInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper("ProgramInfoManageMapper")
public interface ProgramInfoManageMapper {

	public List<ProgramInfoVO> selectProgramInfoManagerInfoManageListByPagination(ProgramInfoVO searchVO);
	
	public ProgramInfoVO selectProgramDetailInfoManagerInfo(String progCode);
	
	public int insertProgramInfo(ProgramInfoVO searchVO);
	
	public int updateProgramInfo(ProgramInfoVO searchVO);
	
	public int deleteProgramInfo(String progCode);
}
