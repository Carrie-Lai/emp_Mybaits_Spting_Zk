package com.viewmodel;

import java.util.List;

import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.domain.Emp2VO;
import com.domain.EmpVO;
import com.service.EmpService;

public class SelectpageVM {
	@WireVariable
	private EmpService empServ;
//	private List<Emp2VO> empList;
	private EmpVO select_emp;

	
	public List<Emp2VO> getEmpList() {
		return empServ.getAll();
	}

	public EmpVO getSelect_emp() {
		return select_emp;
	}

	public void setSelect_emp(EmpVO select_emp) {
		this.select_emp = select_emp;
	}
	
	
	
	

}
