package egovframework.let.sym.cnt.web;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.Globals;
import egovframework.let.sts.brd.service.BrodContentInfoManageService;
import egovframework.let.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;
import egovframework.let.sym.cnt.service.CenterInfo;
import egovframework.let.sym.cnt.service.CenterInfoVO;
import egovframework.let.sym.cnt.service.CenterInfoManageService;
import net.sf.jxls.parser.Cell;

import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.let.uat.uia.service.EgovUserManagerService;
import egovframework.let.utl.fcc.service.FileUpladController;
import egovframework.rte.fdl.excel.EgovExcelMapping;
import egovframework.rte.fdl.excel.util.EgovExcelUtil;
import egovframework.rte.fdl.property.EgovPropertyService;



public class CenterInfoExcelMapping extends EgovExcelMapping  {

	private static final Logger LOGGER = LoggerFactory.getLogger(CenterInfoExcelMapping.class);

	
	 /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;
	
	
	
	//엑셀 업로드 할거
	@Override
	public CenterInfo mappingColumn(Row row) throws Exception {
		// TODO Auto-generated method stub
		org.apache.poi.ss.usermodel.Cell cell0 =  row.getCell(0);
		org.apache.poi.ss.usermodel.Cell cell1 =  row.getCell(1);
		org.apache.poi.ss.usermodel.Cell cell2 =  row.getCell(2);
		org.apache.poi.ss.usermodel.Cell cell3 =  row.getCell(3);
		org.apache.poi.ss.usermodel.Cell cell4 =  row.getCell(4);
		org.apache.poi.ss.usermodel.Cell cell5 =  row.getCell(5);
		org.apache.poi.ss.usermodel.Cell cell6 =  row.getCell(6);
		org.apache.poi.ss.usermodel.Cell cell7 =  row.getCell(7);
		org.apache.poi.ss.usermodel.Cell cell8 =  row.getCell(8);
		org.apache.poi.ss.usermodel.Cell cell9 =  row.getCell(9);
		
		
		CenterInfo centerinfo = new CenterInfo();
		
		
		centerinfo.setCenterNm(EgovExcelUtil.getValue(cell0));
		centerinfo.setCenterZipcode(EgovExcelUtil.getValue(cell1));
		centerinfo.setCenterAddr1(EgovExcelUtil.getValue(cell2));
		centerinfo.setCenterAddr2(EgovExcelUtil.getValue(cell3));
		centerinfo.setCenterTel(EgovExcelUtil.getValue(cell4));
		centerinfo.setCenterFax(EgovExcelUtil.getValue(cell5));
		centerinfo.setCenterUserId(EgovExcelUtil.getValue(cell6));
		centerinfo.setCenterStartTime(EgovExcelUtil.getValue(cell7));
		centerinfo.setCenterEndTime(EgovExcelUtil.getValue(cell8));
		centerinfo.setCenterGubun(EgovExcelUtil.getValue(cell9));
		
		
		
		return centerinfo;
		
		
	}
	
	
	
	
}
