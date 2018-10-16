package egovframework.com.mapper;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.let.utl.fcc.service.UniSelectInfo;
@Mapper("UniSelectInfoMapper")
public interface UniSelectInfoManageMapper {
	public String selectUniColumn(UniSelectInfo vo);
	
}
