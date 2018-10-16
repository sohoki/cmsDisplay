package egovframework.let.utl.fcc.service.impl;


import egovframework.let.utl.fcc.service.UniSelectInfo;
import egovframework.let.utl.fcc.service.UniSelectInfoManageService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;
import egovframework.com.mapper.UniSelectInfoManageMapper;

import org.springframework.stereotype.Service;

@Service("UniSelectInfoManageService")
public class UniSelectInfoManageServiceImpl extends EgovAbstractServiceImpl implements UniSelectInfoManageService  {

	
	
	
	@Resource(name="UniSelectInfoMapper")
	private UniSelectInfoManageMapper uniSelectMapper;

	//공용 함수 정리 하기 
	@Override
	public String selectUniColumn(UniSelectInfo vo) {
		// TODO Auto-generated method stub
		return uniSelectMapper.selectUniColumn(vo);
	}
	
	
	

	
}
