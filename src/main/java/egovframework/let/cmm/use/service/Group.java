package egovframework.let.cmm.use.service;

public class Group {

	
	private  String groupId ;
	private  String groupNm;
	private  String groupCreatDe;
	private  String groupDc;
	private String parentGroupId ;
	private String useYn ;
	private String lv;
	private String mode;
	private String parentGroupNm;
	private String menuGubun;	
	
		
	public String getParentGroupNm() {
		return parentGroupNm;
	}
	public void setParentGroupNm(String parentGroupNm) {
		this.parentGroupNm = parentGroupNm;
	}
	public String getMenuGubun() {
		return menuGubun;
	}
	public void setMenuGubun(String menuGubun) {
		this.menuGubun = menuGubun;
	}
	public String getLv() {
		return lv;
	}
	public void setLv(String lv) {
		this.lv = lv;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getParentGroupId() {
		return parentGroupId;
	}
	public void setParentGroupId(String parentGroupId) {
		this.parentGroupId = parentGroupId;
	}
	
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupNm() {
		return groupNm;
	}
	public void setGroupNm(String groupNm) {
		this.groupNm = groupNm;
	}
	public String getGroupCreatDe() {
		return groupCreatDe;
	}
	public void setGroupCreatDe(String groupCreatDe) {
		this.groupCreatDe = groupCreatDe;
	}
	public String getGroupDc() {
		return groupDc;
	}
	public void setGroupDc(String groupDc) {
		this.groupDc = groupDc;
	}
	
	
	
	
	
}
