package emart_cms;

public class brodDetail {

	private String brod_code;
	private String intervalTime;
	private String atchFileId;
	private String mp3Time;
	private String contentOrder;
	
	
	public brodDetail(String brod_code, String intervalTime, String atchFileId, String mp3Time, String contentOrder){
		this.brod_code =	brod_code;
		this.intervalTime =	intervalTime;
		this.atchFileId =	atchFileId;
		this.mp3Time =	mp3Time;
		this.contentOrder =	contentOrder;
	}


	public String getBrod_code() {
		return brod_code;
	}


	public void setBrod_code(String brod_code) {
		this.brod_code = brod_code;
	}


	public String getIntervalTime() {
		return intervalTime;
	}


	public void setIntervalTime(String intervalTime) {
		this.intervalTime = intervalTime;
	}


	public String getAtchFileId() {
		return atchFileId;
	}


	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}


	public String getMp3Time() {
		return mp3Time;
	}


	public void setMp3Time(String mp3Time) {
		this.mp3Time = mp3Time;
	}


	public String getContentOrder() {
		return contentOrder;
	}


	public void setContentOrder(String contentOrder) {
		this.contentOrder = contentOrder;
	}


	
	
}
