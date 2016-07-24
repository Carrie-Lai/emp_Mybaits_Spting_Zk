package com.viewmodel;

import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.domain.Emp2VO;
import com.domain.EmpVO;
import com.service.EmpService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ListallempVM {
	
	Integer pageSize = 3;
	Integer activePage = 0;
	Integer totalSize;
	@WireVariable
	private EmpService empService;
	private List<Emp2VO> empList;
	private Integer empno;
	private Emp2VO empVO;
	private EmpVO select_emp;
	
	@Init
	public void init() {
		empList = empService.getAllByPage(activePage*pageSize, pageSize);
		totalSize = empService.getAll().size();
	}
	
	public List<Emp2VO> getEmpList() {
		return empService.getAllByPage(activePage*pageSize, pageSize);
	}
	public void setEmpList(List<Emp2VO> empList) {
		this.empList = empList;
	}
	public Integer getEmpno() {
		return empno;
	}
	public void setEmpno(Integer empno) {
		this.empno = empno;
	}
	public Emp2VO getEmpVO() {
		return empVO;
	}
	public void setEmpVO(Emp2VO empVO) {
		this.empVO = empVO;
	}
	
	public Integer getTotalSize() {
		return empService.getAll().size();
	}

	public Integer getActivePage() {
		return activePage;
	}

	@NotifyChange("empList")
	public void setActivePage(Integer activePage) {
		this.activePage = activePage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	@Command
	@NotifyChange({"empList","totalSize"})
	public void delete(@BindingParam("empno") Integer empno) {
		empService.deleteEmp(empno);
		Executions.getCurrent().sendRedirect("");
	}
	
	@Command
	public void update(@BindingParam("empno") Integer empno) {
		empVO = empService.getOneEmp(empno);
		select_emp = new EmpVO();
		select_emp.setEmpno(empVO.getEmpno());
		select_emp.setEname(empVO.getEname()); 
		select_emp.setJob(empVO.getJob()); 
		select_emp.setHiredate(empVO.getHiredate()); 
		select_emp.setSal(empVO.getSal()); 
		select_emp.setComm(empVO.getComm()); 
		select_emp.setDeptno(empVO.getDeptVO().getDeptno()); 
		Sessions.getCurrent().setAttribute("empVO", select_emp);
		Executions.sendRedirect("update_emp_input.zul");
		
	}
	
	
	
	

}
