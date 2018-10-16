package egovframework.let.sts.brd.service.impl;



import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.let.sts.brd.service.BrodContentDetailTime;
import egovframework.let.sts.brd.service.BrodContentDetailTimeManagerService;
import egovframework.com.mapper.BrodContentDetailTimeManagerMapper;

@Service("BrodContentTimeManagerService")
public class BrodContentDetailTimeManagerServiceImpl extends EgovAbstractServiceImpl implements BrodContentDetailTimeManagerService {

	
	@Resource(name="BrodContentDetailTimeManageMapper")
	private BrodContentDetailTimeManagerMapper brodTime;
	
	
	@Override
	public int insertBrodContentDetailTime(BrodContentDetailTime vo)
			throws Exception {
		// TODO Auto-generated method stub
		return brodTime.insertBrodContentDetailTime(vo);
	}

	@Override
	public int deleteBrodContentDetailTime(String imsiSeq) throws Exception {
		// TODO Auto-generated method stub
		return brodTime.deleteBrodContentDetailTime(imsiSeq);
	}

	@Override
	public int deleteBrodContentDetailTimeBrodCode(String brodCode)
			throws Exception {
		// TODO Auto-generated method stub
		return brodTime.deleteBrodContentDetailTimeBrodCode(brodCode);
	}

}
