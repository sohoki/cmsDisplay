package egovframework.let.sts.mhs.service;

import java.io.Serializable;

public class MhsMonitorInfoVO extends MhsMonitorInfo implements Serializable {
	  
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
    
    // 권한에 따른 리스트 조회 분류
    private String authorCode;
    private String groupId;
    private String parentGroupId;
    
    // TB_MHSMONITORINFO에 없는 컬럼 Get
    private String mhsBrandnm;
    private String mhsCenternm;
    
    private String searchMhsBramdCd;
    private String searchMhsCenterCd;
    private String groupNm;
    
    
    
    
    
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
	public String getGroupNm() {
		return groupNm;
	}
	public void setGroupNm(String groupNm) {
		this.groupNm = groupNm;
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
	
	
	
	public String getAuthorCode() {
		return authorCode;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getParentGroupId() {
		return parentGroupId;
	}
	public void setParentGroupId(String parentGroupId) {
		this.parentGroupId = parentGroupId;
	}
	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}
	public String getMhsBrandnm() {
		return mhsBrandnm;
	}
	public void setMhsBrandnm(String mhsBrandnm) {
		this.mhsBrandnm = mhsBrandnm;
	}
	public String getMhsCenternm() {
		return mhsCenternm;
	}
	public void setMhsCenternm(String mhsCenternm) {
		this.mhsCenternm = mhsCenternm;
	}
	
	
	@Override
	public String toString() {
		return "MhsMonitorInfoVO [searchCondition=" + searchCondition
				+ ", searchKeyword=" + searchKeyword + ", searchUseYn="
				+ searchUseYn + ", pageIndex=" + pageIndex + ", pageUnit="
				+ pageUnit + ", pageSize=" + pageSize + ", firstIndex="
				+ firstIndex + ", lastIndex=" + lastIndex
				+ ", recordCountPerPage=" + recordCountPerPage
				+ ", authorCode=" + authorCode + ", groupId=" + groupId
				+ ", parentGroupId=" + parentGroupId + ", mhsBrandnm="
				+ mhsBrandnm + ", mhsCenternm=" + mhsCenternm + "]";
	}
	
	
}
