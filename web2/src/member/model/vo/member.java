package member.model.vo;

import java.util.Date;

public class member {
	/*
USERID	VARCHAR2(20 BYTE)
USERPWD	VARCHAR2(20 BYTE)
USERNAME	VARCHAR2(20 BYTE)
AGE	NUMBER
EMAIL	VARCHAR2(30 BYTE)
PHONE	VARCHAR2(13 BYTE)
ADDRESS	VARCHAR2(50 BYTE)
GENDER	VARCHAR2(1 BYTE)
HOBBY	VARCHAR2(30 BYTE)
ENROLLDATE	DATE
*/
	private String userId;
	private String userPwd;
	private String name;
	private int age;
	private String email;
	private String phone;
	private String addr;
	private String gender;
	private String hobby;
	private Date enrolldate;
	private String activation;
	private Date last_modified;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
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
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public Date getEnrolldate() {
		return enrolldate;
	}
	public void setEnrolldate(Date enrolldate) {
		this.enrolldate = enrolldate;
	}
	public String getActivation() {
		return activation;
	}
	public void setActivation(String activation) {
		this.activation = activation;
	}
	public Date getLast_modified() {
		return last_modified;
	}
	public void setLast_modified(Date last_modified) {
		this.last_modified = last_modified;
	}
	
}
