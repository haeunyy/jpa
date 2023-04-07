package com.greedy.section02.column;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/* 해당 클래스를 엔티티로 설정하기 위한 어노테이션 
 * 프로젝트 내에 다른 패키지에도 동일한 엔티티가 존재하는 경우 해당 엔티티를 식별하기 위한 중복되지 않는 name을 지정해주어야 한다. 
 * 1. 기본 생성자는 필수로 작성
 * 2. final 클래스, enum, interfacem inner class 에서는 사용할 수 없다.
 * 3. 저장할 필드에 final을 사용하면 안된다.  */
@Entity(name="section02_member")
/* 매핑 될 테이블의 이름을 작성한다. 생략하면 자동으로 클래스의 이름을 테이블의 이름으로 사용한다. */
@Table(name="TBL_MEMBER_SECTION02") // 동일한 테이블이 없다면 테이블도 CREATE 할 수 있다.
public class Member {

	
	@Id
	@Column(name="MEMBER_NO")
	private int memberNo;
	
	@Column(name="MEMBER_ID")
	private String memberId;
	
	@Column(name="MEMBER_PWD")
	private String memberPwd;
	
	@Column(name="NICKNAME")
	@Transient 								/* 이 필드는 제외 -> 테이블 생성시 무시 된다. */
	private String nickname;
	
	// @Column(name="PHONE", nullable=false)	/* not null 설정 : 기본 설정은 nullable */
	// @Column(name="PHONE", unique=true)		
	@Column(name="PHONE", columnDefinition = "varchar2(200) default '010-0000-0000'")
	private String phone;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="ENROLL_DATE")
	private java.util.Date enrollDate;
	
	@Column(name="MEMBER_ROLE")
	private String memberRole;
	
	@Column(name="STATUS")
	private String status;
	
	
	
	
	
	
	
	
	
	
	
	public Member() {}











	public Member(int memberNo, String memberId, String memberPwd, String nickname, String phone, String email,
			String address, Date enrollDate, String memberRole, String status) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.nickname = nickname;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.enrollDate = enrollDate;
		this.memberRole = memberRole;
		this.status = status;
	}











	public int getMemberNo() {
		return memberNo;
	}











	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}











	public String getMemberId() {
		return memberId;
	}











	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}











	public String getMemberPwd() {
		return memberPwd;
	}











	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}











	public String getNickname() {
		return nickname;
	}











	public void setNickname(String nickname) {
		this.nickname = nickname;
	}











	public String getPhone() {
		return phone;
	}











	public void setPhone(String phone) {
		this.phone = phone;
	}











	public String getEmail() {
		return email;
	}











	public void setEmail(String email) {
		this.email = email;
	}











	public String getAddress() {
		return address;
	}











	public void setAddress(String address) {
		this.address = address;
	}











	public java.util.Date getEnrollDate() {
		return enrollDate;
	}











	public void setEnrollDate(java.util.Date enrollDate) {
		this.enrollDate = enrollDate;
	}











	public String getMemberRole() {
		return memberRole;
	}











	public void setMemberRole(String memberRole) {
		this.memberRole = memberRole;
	}











	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPwd=" + memberPwd + ", nickname="
				+ nickname + ", phone=" + phone + ", email=" + email + ", address=" + address + ", enrollDate="
				+ enrollDate + ", memberRole=" + memberRole + ", status=" + status + "]";
	}











	public String getStatus() {
		return status;
	}











	public void setStatus(String status) {
		this.status = status;
	}

	
	
}
