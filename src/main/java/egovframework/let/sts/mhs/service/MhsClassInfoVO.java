package egovframework.let.sts.mhs.service;

import java.io.Serializable;

public class MhsClassInfoVO extends MhsClassInfo implements Serializable {

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
    
    
    private String searchMhsBramdCd;
    private String searchMhsCenterCd;
    private String groupNm;
    private String mhsCenternm;
    private String mhsBrandnm;
    private String mhsMonitornm;
    
    
    
    
	public String getMhsMonitornm() {
		return mhsMonitornm;
	}
	public void setMhsMonitornm(String mhsMonitornm) {
		this.mhsMonitornm = mhsMonitornm;
	}
	public String getMhsBrandnm() {
		return mhsBrandnm;
	}
	public void setMhsBrandnm(String mhsBrandnm) {
		this.mhsBrandnm = mhsBrandnm;
	}
	public String getGroupNm() {
		return groupNm;
	}
	public void setGroupNm(String groupNm) {
		this.groupNm = groupNm;
	}
	public String getMhsCenternm() {
		return mhsCenternm;
	}
	public void setMhsCenternm(String mhsCenternm) {
		this.mhsCenternm = mhsCenternm;
	}
	public String getSearchMhsBramdCd() {
		return searchMhsBramdCd;
	}
	public void setSearchMhsBramdCd(String searchMhsBramdCd) {
		this.searchMhsBramdCd = searchMhsBramdCd;
	}
	public String getSearchMhsCenterCd() {
		return searchMhsCenterCd;
	}
	public void setSearchMhsCenterCd(String searchMhsCenterCd) {
		this.searchMhsCenterCd = searchMhsCenterCd;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "MhsClassInfoVO [searchCondition=" + searchCondition
				+ ", searchKeyword=" + searchKeyword + ", searchUseYn="
				+ searchUseYn + ", pageIndex=" + pageIndex + ", pageUnit="
				+ pageUnit + ", pageSize=" + pageSize + ", firstIndex="
				+ firstIndex + ", lastIndex=" + lastIndex
				+ ", recordCountPerPage=" + recordCountPerPage + "]";
	}
    
    
	
}
