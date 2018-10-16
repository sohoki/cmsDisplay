package egovframework.let.sts.brd.web.download;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import egovframework.let.sts.brd.service.BrodOrganization;

public class BrodOrganitionExcelView  extends AbstractExcelView{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		try{
			response.setHeader("Content-Disposition", "attachment; filename=\"" + getClass().getSimpleName() + ".xls\"");
	
			HSSFCell cell = null;
	
			HSSFSheet sheet = workbook.createSheet("brodOrganization");
			sheet.setDefaultColumnWidth(12);
	
			// put text in first cell
			cell = getCell(sheet, 0, 0);
			setText(cell, "재생 목록 리스트");
	
			// set header information
			setText(getCell(sheet, 2, 0), "No.");
			setText(getCell(sheet, 2, 1), "재생시간");
			setText(getCell(sheet, 2, 2), "재생콘텐츠");
			setText(getCell(sheet, 2, 3), "구분");
	
			List<BrodOrganization> goods = (List<BrodOrganization>) model.get("brodReport");
	
			for (int i = 0; i < goods.size(); i++) {
				BrodOrganization goodsVO = goods.get(i);
	
				cell = getCell(sheet, 3 + i, 0);
				setText(cell, Integer.toString(i + 1));
	
				cell = getCell(sheet, 3 + i, 1);
				setText(cell, goodsVO.getBrodTime());
	
				cell = getCell(sheet, 3 + i, 2);
				setText(cell, goodsVO.getOrignlFileNm()  );
	
				cell = getCell(sheet, 3 + i, 3);
				if (goodsVO.getBrodSeq() == "0"){
					setText(cell, "특정방송");	
				}else {
					setText(cell, "일반방송");
				}
				
	
			}
		}catch(Exception e){
			System.out.println("error:" + e.toString());
		}
	}

}
