package egovframework.com.mapper;


import java.util.List;

import egovframework.let.sym.rnt.service.AuthorInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("AuthorInfoManagerMapper")
public interface AuthorInfoManagerMapper {

	public List<AuthorInfoVO> selectAuthorIInfoManageCombo(); 
	
}