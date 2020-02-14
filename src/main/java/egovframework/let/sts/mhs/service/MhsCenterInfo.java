package egovframework.let.sts.mhs.service;

public class MhsCenterInfo {
	
	private String mhsBrandcd;
	private String mhsCentercd;
	private String mhsCenternm;
	private String mhsParentcentercd;
	private String mhsCenterregid;
	private String mhsCenterregdate;
	private String mhsCenterupdateid;
	private String mhsCenterupdatedate;
	private String mhsCenterstatus;
	private String mode;
	private String subMode;
	
	private String centerId;
	private String centerNm;
	
	
	
	
	public String getCenterId() {
		return centerId;
	}
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}
	public String getCenterNm() {
		return centerNm;
	}
	public void setCenterNm(String centerNm) {
		this.centerNm = centerNm;
	}
	public String getSubMode() {
		return subMode;
	}
	public void setSubMode(String subMode) {
		this.subMode = subMode;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getMhsCenterstatus() {
		return mhsCenterstatus;
	}
	public void setMhsCenterstatus(String mhsCenterstatus) {
		this.mhsCenterstatus = mhsCenterstatus;
	}
	public String getMhsBrandcd() {
		return mhsBrandcd;
	}
	public void setMhsBrandcd(String mhsBrandcd) {
		this.mhsBrandcd = mhsBrandcd;
	}
	public String getMhsCentercd() {
		return mhsCentercd;
	}
	public void setMhsCentercd(String mhsCentercd) {
		this.mhsCentercd = mhsCentercd;
	}
	public String getMhsCenternm() {
		return mhsCenternm;
	}
	public void setMhsCenternm(String mhsCenternm) {
		this.mhsCenternm = mhsCenternm;
	}
	
	public String getMhsParentcentercd() {
		return mhsParentcentercd;
	}
	public void setMhsParentcentercd(String mhsParentcentercd) {
		this.mhsParentcentercd = mhsParentcentercd;
	}
	public String getMhsCenterregid() {
		return mhsCenterregid;
	}
	public void setMhsCenterregid(String mhsCenterregid) {
		this.mhsCenterregid = mhsCenterregid;
	}
	public String getMhsCenterregdate() {
		return mhsCenterregdate;
	}
	public void setMhsCenterregdate(String mhsCenterregdate) {
		this.mhsCenterregdate = mhsCenterregdate;
	}
	public String getMhsCenterupdateid() {
		return mhsCenterupdateid;
	}
	public void setMhsCenterupdateid(String mhsCenterupdateid) {
		this.mhsCenterupdateid = mhsCenterupdateid;
	}
	public String getMhsCenterupdatedate() {
		return mhsCenterupdatedate;
	}
	public void setMhsCenterupdatedate(String mhsCenterupdatedate) {
		this.mhsCenterupdatedate = mhsCenterupdatedate;
	}
	@Override
	public String toString() {
		return "MhsCenterInfo [mhsBrandcd=" + mhsBrandcd + ", mhsCentercd="
				+ mhsCentercd + ", mhsCenternm=" + mhsCenternm
				+ ", mhsParentcentercd=" + mhsParentcentercd
				+ ", mhsCenterregid=" + mhsCenterregid + ", mhsCenterregdate="
				+ mhsCenterregdate + ", mhsCenterupdateid=" + mhsCenterupdateid
				+ ", mhsCenterupdatedate=" + mhsCenterupdatedate + "]";
	}

	
	

}
