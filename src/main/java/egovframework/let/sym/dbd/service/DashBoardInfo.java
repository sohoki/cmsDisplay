package egovframework.let.sym.dbd.service;

public class DashBoardInfo {

	private String didCnt;
	private String onCnt;
	private String real_per;
	private String offdid;
	private String didNm;
	private String centerNm;
	private String didStatus;
	private String offCnt;
	private String didEndContime;
	private String didId;
	
	
	
	
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
	

    
    public String getDidId() {
		return didId;
	}
	public void setDidId(String didId) {
		this.didId = didId;
	}
	public String getDidEndContime() {
		return didEndContime;
	}
	public void setDidEndContime(String didEndContime) {
		this.didEndContime = didEndContime;
	}
	public String getOffCnt() {
		return offCnt;
	}
	public void setOffCnt(String offCnt) {
		this.offCnt = offCnt;
	}
	
	public String getDidNm() {
		return didNm;
	}
	public void setDidNm(String didNm) {
		this.didNm = didNm;
	}
	public String getCenterNm() {
		return centerNm;
	}
	public void setCenterNm(String centerNm) {
		this.centerNm = centerNm;
	}
	public String getDidStatus() {
		return didStatus;
	}
	public void setDidStatus(String didStatus) {
		this.didStatus = didStatus;
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
	public String getDidCnt() {
		return didCnt;
	}
	public void setDidCnt(String didCnt) {
		this.didCnt = didCnt;
	}
	public String getOnCnt() {
		return onCnt;
	}
	public void setOnCnt(String onCnt) {
		this.onCnt = onCnt;
	}
	public String getReal_per() {
		return real_per;
	}
	public void setReal_per(String real_per) {
		this.real_per = real_per;
	}
	public String getOffdid() {
		return offdid;
	}
	public void setOffdid(String offdid) {
		this.offdid = offdid;
	}
	
	
	
	
	
}
