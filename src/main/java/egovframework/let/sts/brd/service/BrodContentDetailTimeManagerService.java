package egovframework.let.sts.brd.service;

public interface BrodContentDetailTimeManagerService {

	
    int insertBrodContentDetailTime(BrodContentDetailTime vo) throws Exception;
	
	int deleteBrodContentDetailTime(String imsiSeq) throws Exception;
	
	int deleteBrodContentDetailTimeBrodCode(String brodCode) throws Exception;
}
