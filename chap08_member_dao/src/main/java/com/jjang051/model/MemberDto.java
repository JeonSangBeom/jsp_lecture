package com.jjang051.model;

// 값 세팅 하는 곳
//VO, DTO, BEAN ( 다 같은 말)
//DTO 데이터베이스 트랜스 오브젝트 데이터베이스로 전달하기 위한 오브젝트 
public class MemberDto {
	// SQL에 만들어둔 테이블대로 만들어 두기
	private int no;
	private String id;
	private String password;
	private String name;
	private String email;
	private String phone;
	private int zipCode;
	private String address;
	private String regDate;
	//생성자 2개 게터 세터
	public MemberDto() { // 기본 생성자
		//super(); // 없어도 된다
	}
	public MemberDto(int no, String id, String password, String name, String email, String phone, int zipCode,
			String address, String regDate) {// 필드 전체
		super();
		this.no = no;
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.zipCode = zipCode;
		this.address = address;
		this.regDate = regDate;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() { // 제대로 넘어오는지 알기 위해 생성 -> join_process에서 system.out.print(memberDto)해주면 됨
		return "MemberDto [no=" + no + ", id=" + id + ", password=" + password + ", name=" + name + ", email=" + email
				+ ", phone=" + phone + ", zipCode=" + zipCode + ", address=" + address + ", regDate=" + regDate + "]";
	}
	
	
	
}