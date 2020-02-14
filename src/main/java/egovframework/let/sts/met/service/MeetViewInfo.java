package egovframework.let.sts.met.service;

public class MeetViewInfo {

	/* 필수 정보 */
	private String mViewNo;
	private String mViewNm;
	private String mViewType;
	private String mViewRoom1;
	private String mViewRoom2;
	
	public String getmViewNo() {
		return mViewNo;
	}
	public void setmViewNo(String mViewNo) {
		this.mViewNo = mViewNo;
	}
	
	public String getmViewNm() {
		return mViewNm;
	}
	public void setmViewNm(String mViewNm) {
		this.mViewNm = mViewNm;
	}
	public String getmViewType() {
		return mViewType;
	}
	public void setmViewType(String mViewType) {
		this.mViewType = mViewType;
	}
	public String getmViewRoom1() {
		return mViewRoom1;
	}
	public void setmViewRoom1(String mViewRoom1) {
		this.mViewRoom1 = mViewRoom1;
	}
	public String getmViewRoom2() {
		return mViewRoom2;
	}
	public void setmViewRoom2(String mViewRoom2) {
		this.mViewRoom2 = mViewRoom2;
	}
	
	
	@Override
	public String toString() {
		return "MeetViewInfo [mViewNo=" + mViewNo + ", mViewType=" + mViewType
				+ ", mViewRoom1=" + mViewRoom1 + ", mViewRoom2=" + mViewRoom2
				+ "]";
	}
	
	
}
