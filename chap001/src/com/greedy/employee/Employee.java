package com.greedy.employee;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name="employee")
@Table(name="EMPLOYEE")
//@SequenceGenerator(
//			name="seqEmp"
//			,sequenceName="SEQ_EMP_ID"
//			,initialValue = 250
//			,allocationSize = 1
//			)
public class Employee {
		
		@Id
		@Column(name="EMP_ID") 
//		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqEmp")
		private String empId;
		
		@Column(name="EMP_NO") 
		private String empNo;	// 주민번호
		
		@Column(name="EMP_NAME") 
		private String empName;
		
		@Column(name="PHONE") 
		private String phone;
		
		@Column(name="EMAIL") 
		private String email;
		
		@JoinColumn(name="DEPT_CODE")
		@ManyToOne(cascade = CascadeType.PERSIST)
		private Department dept;
		
//		JOB_CODE	CHAR(2 BYTE)
		@Column(name="JOB_CODE")
		private String jobCode;
		
//		SAL_LEVEL	CHAR(2 BYTE)
		@Column(name="SAL_LEVEL")
		private String salLevel;

//		SALARY	NUMBER
//		BONUS	NUMBER
//		MANAGER_ID	VARCHAR2(3 BYTE)
//		HIRE_DATE	DATE
//		ENT_DATE	DATE
//		ENT_YN	CHAR(1 BYTE)
		 
		public Employee() {}

		public Employee(String empId, String empNo, String empName, String phone, String email, Department dept,
				String jobCode, String salLevel) {
			super();
			this.empId = empId;
			this.empNo = empNo;
			this.empName = empName;
			this.phone = phone;
			this.email = email;
			this.dept = dept;
			this.jobCode = jobCode;
			this.salLevel = salLevel;
		}

		public String getEmpId() {
			return empId;
		}

		public void setEmpId(String empId) {
			this.empId = empId;
		}

		public String getEmpNo() {
			return empNo;
		}

		public void setEmpNo(String empNo) {
			this.empNo = empNo;
		}

		public String getEmpName() {
			return empName;
		}

		public void setEmpName(String empName) {
			this.empName = empName;
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

		public Department getDept() {
			return dept;
		}

		public void setDept(Department dept) {
			this.dept = dept;
		}

		public String getJobCode() {
			return jobCode;
		}

		public void setJobCode(String jobCode) {
			this.jobCode = jobCode;
		}

		public String getSalLevel() {
			return salLevel;
		}

		public void setSalLevel(String salLevel) {
			this.salLevel = salLevel;
		}

		@Override
		public String toString() {
			return "Employee [empId=" + empId + ", empNo=" + empNo + ", empName=" + empName + ", phone=" + phone
					+ ", email=" + email + ", dept=" + dept + ", jobCode=" + jobCode + ", salLevel=" + salLevel + "]";
		}
		
		
}
