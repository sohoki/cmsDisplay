package egovframework.let.sts.brd.service;

import java.io.Serializable;
import java.util.List;

public class BasicFileGroupPlayInfoVO extends BasicFileGroupPlayInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	/** 검색조건 */
    private String searchCondition = "";    
    /** 검색Keyword */
    private String searchKeyword = "";    
    /** 검색사용여부 */
    private String searchUseYn = "";    
    /** 현재페이지 */
    private int pageIndex = 1;    
    /** 페이지갯수 */
    private int pageUnit = 10;    
    /** 페이지사이즈 */
    private int pageSize = 10;    
    private int firstIndex = 1;
    private int lastIndex = 1;    
    private int recordCountPerPage = 10;
    private int rnum = 0;
    
    private String filePlayList = "";
    
    private String searchStartDay = "";
    private String searchEndDay = "";
    private List<String>  searchCenterId =null ;
    private String centerNm = "";
    
    private String fileAlbum = "";
    private String singerNm = "";
    private String orignlFileNm = "";
    
    
    
    
    
    
	public String getOrignlFileNm() {
		return orignlFileNm;
	}
	public void setOrignlFileNm(String orignlFileNm) {
		this.orignlFileNm = orignlFileNm;
	}
	public String getSearchStartDay() {
		return searchStartDay;
	}
	public void setSearchStartDay(String searchStartDay) {
		this.searchStartDay = searchStartDay;
	}
	public String getSearchEndDay() {
		return searchEndDay;
	}
	public void setSearchEndDay(String searchEndDay) {
		this.searchEndDay = searchEndDay;
	}
	
	public List<String> getSearchCenterId() {
		return searchCenterId;
	}
	public void setSearchCenterId(List<String> searchCenterId) {
		this.searchCenterId = searchCenterId;
	}
	public String getCenterNm() {
		return centerNm;
	}
	public void setCenterNm(String centerNm) {
		this.centerNm = centerNm;
	}
	public String getFileAlbum() {
		return fileAlbum;
	}
	public void setFileAlbum(String fileAlbum) {
		this.fileAlbum = fileAlbum;
	}
	public String getSingerNm() {
		return singerNm;
	}
	public void setSingerNm(String singerNm) {
		this.singerNm = singerNm;
	}
	public String getFilePlayList() {
		return filePlayList;
	}
	public void setFilePlayList(String filePlayList) {
		this.filePlayList = filePlayList;
	}
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public String getSearchUseYn() {
		return searchUseYn;
	}
	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageUnit() {
		return pageUnit;
	}
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getFirstIndex() {
		return firstIndex;
	}
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}
	public int getLastIndex() {
		return lastIndex;
	}
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
    
    

}
