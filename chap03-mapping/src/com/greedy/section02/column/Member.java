package com.greedy.section02.column;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
	
	 /* 컬럼 매핑 시 @Column 어노테이션에 사용 가능한 속성들
	    * 1. name : 필드와 매핑할 테이블의 컬럼 이름
	    * 2. insertable : 엔티티 저장 시 이 필드도 같이 저장(true 기본값)
	    * 3. updatable : 엔티티 수정 시 이 필드도 같이 수정(true 기본값)
	    * 4. table : 하나의 엔티티를 두 개 이상의 테이블에 매핑할 때 사용(현재 클래스가 매핑된 테이블)
	    * 5. nullable : null값 허용 여부 설정. not null 제약조건에 해당함 (true 기본값)
	    * 6. unique : 한 컬럼에 unique 제약조건을 설정.
	    *    (기본값은 없으며, 두 개 이상 컬럼에 unique 제약조건을 설정하기 위해서는 클래스 레벨에서 @Table의 uniqueConstraints 속성에 설정한다.)
	    * 7. columnDefinition : 데이터베이스 컬럼 정보를 직접 기입 (필드의 자바 타입과 방언 정보를 사용해서 적절한 컬럼 타입을 생성함)
	    * 8. length : 문자 길이 제약조건, String 타입에서만 사용(255 기본값)
	    *
	    * ** 주로 name과 nullable을 사용하며, insertable과 updateable은 정보를 읽기만 하고 실수로 변경하게 될 것을 미연에 방지하고자 설정한다.
	    *    다만 애플리케이션 레벨에서 DDL 구분을 직접 사용하지 않는 것이 더 바람직하다.
	    *  */
	@Column(name="NICKNAME")
	@Transient 	 /* 이 필드는 제외 -> 테이블 생성시 무시 된다. */
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
	@Temporal(TemporalType.TIMESTAMP)	/* DATE + TIME 으로 날짜 및 시간이 나온다.*/
//	@Temporal(TemporalType.DATE)		/* 2023/04/07 10:04:12 오라클에서는 TIMESTAMP와 차이가 없다. */
//	@Temporal(TemporalType.TIME)		/* 1970/01/01 10:01:52 날짜는 틀리고 시간만 맞게 나온다. */
	private java.util.Date enrollDate;	/* @Temporal을 사용하려면 sqlDate가 아닌 utilDate를 사용해야한다.*/
	
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
