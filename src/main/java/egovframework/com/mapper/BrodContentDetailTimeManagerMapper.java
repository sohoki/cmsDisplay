package egovframework.com.mapper;

import egovframework.let.sts.brd.service.BrodContentDetailTime;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("BrodContentDetailTimeManageMapper")
public interface BrodContentDetailTimeManagerMapper {

	public int insertBrodContentDetailTime(BrodContentDetailTime vo);
	
	public int deleteBrodContentDetailTime(String imsiSeq);
	
	public int deleteBrodContentDetailTimeBrodCode(String brodCode);
	
	
}
