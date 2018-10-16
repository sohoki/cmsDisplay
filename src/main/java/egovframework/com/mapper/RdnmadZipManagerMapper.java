package egovframework.com.mapper;

import java.util.List;
import egovframework.let.sym.ccm.zip.service.RdnmadZip;
import egovframework.let.sym.ccm.zip.service.RdnmadZipVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("RdnmadZipManagerMapper")
public interface RdnmadZipManagerMapper {

	
	public List<RdnmadZipVO> selectRdnmadZipManageListByPagination(RdnmadZipVO searchVO);
	
	public int selectRdnmadZipManageListTotCnt_S(RdnmadZipVO searchVO);
	
}
