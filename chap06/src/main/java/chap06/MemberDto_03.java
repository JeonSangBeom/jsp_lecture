package chap06;

public class MemberDto_03 {
	private String name;
	private String id;
			
	// 기본적으로 생성	
	// 이 DTO에 값을 실어서 보낸다(DB값)
	
	// CONSTRUCTOR USING FIELDS -> 기본 빈 생성자도 생성(체크 없이 만들면 된다)
	public MemberDto_03() { 
		super();
	}	
	public MemberDto_03(String name, String id) {
		super();
		this.name = name;
		this.id = id;
	}
	// GETTERS AND SETTERS
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	// TOSTRING
	@Override
	public String toString() {
		return "MemberDto [name=" + name + ", id=" + id + "]";
	}
	
	
}
