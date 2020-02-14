package egovframework.let.sts.mhs.service;

import egovframework.let.sts.mhs.service.MhsCenterInfo;

import java.io.Serializable;

public class MhsCenterInfoVO extends MhsCenterInfo implements Serializable {
	
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
    
    // 데이터 추가 조회 (조직/점포 조회간)
    private String mhsBrandlv;
    private String mhsBrandnm;
    private String mhsParentbrandcd;
    
    private String mhsParentcenterNm;
    private String mhsCenterstatusTxt;
    
    
    
    
    
    public String getMhsCenterstatusTxt() {
		return mhsCenterstatusTxt;
	}
	public void setMhsCenterstatusTxt(String mhsCenterstatusTxt) {
		this.mhsCenterstatusTxt = mhsCenterstatusTxt;
	}
	public String getMhsParentcenterNm() {
		return mhsParentcenterNm;
	}
	public void setMhsParentcenterNm(String mhsParentcenterNm) {
		this.mhsParentcenterNm = mhsParentcenterNm;
	}




	// 권한에 따른 리스트 조회 분류
    private String authorCode;
    private String groupId;

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
	
	
	
	public String getMhsBrandlv() {
		return mhsBrandlv;
	}
	public void setMhsBrandlv(String mhsBrandlv) {
		this.mhsBrandlv = mhsBrandlv;
	}
	public String getMhsBrandnm() {
		return mhsBrandnm;
	}
	public void setMhsBrandnm(String mhsBrandnm) {
		this.mhsBrandnm = mhsBrandnm;
	}
	public String getMhsParentbrandcd() {
		return mhsParentbrandcd;
	}
	public void setMhsParentbrandcd(String mhsParentbrandcd) {
		this.mhsParentbrandcd = mhsParentbrandcd;
	}
	
	
	public String getAuthorCode() {
		return authorCode;
	}
	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	
	
	
	@Override
	public String toString() {
		return "MhsCenterInfoVO [searchCondition=" + searchCondition
				+ ", searchKeyword=" + searchKeyword + ", searchUseYn="
				+ searchUseYn + ", pageIndex=" + pageIndex + ", pageUnit="
				+ pageUnit + ", pageSize=" + pageSize + ", firstIndex="
				+ firstIndex + ", lastIndex=" + lastIndex
				+ ", recordCountPerPage=" + recordCountPerPage
				+ ", mhsBrandlv=" + mhsBrandlv + ", mhsBrandnm=" + mhsBrandnm
				+ ", mhsParentbrandcd=" + mhsParentbrandcd + ", authorCode="
				+ authorCode + ", groupId=" + groupId + "]";
	}
	
	

}
