package egovframework.let.sts.brd.web.download;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import egovframework.let.sts.brd.service.BasicFileGroupPlayInfoVO;

public class BrodPlayNotCenterExcelView extends AbstractExcelView {
	
	
	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		try{
			
			System.out.println("----------------------------------------------------------------");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + getClass().getSimpleName() + ".xls\"");
			
			HSSFCell cell = null;
	
			HSSFSheet sheet = workbook.createSheet("기본 음원 재생 목록 리스트");
			sheet.setDefaultColumnWidth(12);
	
			// put text in first cell
			cell = getCell(sheet, 0, 0);
			setText(cell, "기본 음원 재생 목록 리스트");
	
			// set header information
			setText(getCell(sheet, 2, 0), "No.");
			setText(getCell(sheet, 2, 1), "재생일자");
			setText(getCell(sheet, 2, 2), "파일명");
			setText(getCell(sheet, 2, 3), "앨범");
			setText(getCell(sheet, 2, 4), "가수");
			setText(getCell(sheet, 2, 5), "음원재생카운트");
	
			List<BasicFileGroupPlayInfoVO> goods = (List<BasicFileGroupPlayInfoVO>) model.get("brodPlayReport");
	
			for (int i = 0; i < goods.size(); i++) {
				BasicFileGroupPlayInfoVO goodsVO = goods.get(i);
	
				cell = getCell(sheet, 3 + i, 0);
				setText(cell, Integer.toString(i + 1));
	
				cell = getCell(sheet, 3 + i, 1);
				setText(cell, goodsVO.getPlayDay());
				
				cell = getCell(sheet, 3 + i, 2);
				setText(cell, goodsVO.getOrignlFileNm());
				
				cell = getCell(sheet, 3 + i, 3);
				setText(cell, goodsVO.getFileAlbum());
				
				cell = getCell(sheet, 3 + i, 4);
				setText(cell, goodsVO.getSingerNm());
				
				cell = getCell(sheet, 3 + i, 5);
				setText(cell, goodsVO.getPlayCnt());
	
	
			}
		}catch(Exception e){
			System.out.println("error:" + e.toString());
		}
	}

}
