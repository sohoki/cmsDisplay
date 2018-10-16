package egovframework.let.sym.rnt.service.Impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.let.sym.rnt.service.AuthorInfoVO;
import egovframework.let.sym.rnt.service.AuthorInfoManageService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import egovframework.com.mapper.AuthorInfoManagerMapper;

@Service("AuthorInfoManageService")
public class AuthorInfoServiceImpl extends EgovAbstractServiceImpl implements AuthorInfoManageService {

	
	@Resource (name="AuthorInfoManagerMapper")
	private AuthorInfoManagerMapper authorInfoManagerMapper;
	
	@Override
	public List<AuthorInfoVO> selectAuthorIInfoManageCombo() {
		// TODO Auto-generated method stub
		return authorInfoManagerMapper.selectAuthorIInfoManageCombo();
	}

	
	
}
