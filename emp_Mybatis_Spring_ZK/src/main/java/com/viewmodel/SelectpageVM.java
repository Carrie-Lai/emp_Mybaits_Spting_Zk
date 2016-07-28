package com.viewmodel;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;

import com.domain.Emp2VO;
import com.service.EmpService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class SelectpageVM {
	@WireVariable
	private EmpService empService;
	private ListModelList<Emp2VO> empList1;
	private ListModelList<Emp2VO> empList2;
	private Emp2VO select_emp;
	private Emp2VO select_emp_name;
	private Integer empno;

	@Init
	public void init(){
		empList1 = new ListModelList<Emp2VO>();
		empList2 = new ListModelList<Emp2VO>();
		List<Emp2VO> eList = empService.getAll();
		empList1.addAll(eList);
		if(null != empList1 && empList1.size() > 0) {
			setSelect_emp(empList1.get(0));
		}
		empList2.addAll(eList);
		if(null != empList2 && empList2.size() > 0) {
			setSelect_emp_name(empList2.get(0));
		}
	}
	
	public ListModelList<Emp2VO> getEmpList1() {
		return empList1;
	}
	
	public ListModelList<Emp2VO> getEmpList2() {
		return empList2;
	}

	public Emp2VO getSelect_emp() {
		return select_emp;
	}

	public void setSelect_emp(Emp2VO select_emp) {
		this.select_emp = select_emp;
	}
	
	public Integer getEmpno() {
		return empno;
	}

	public void setEmpno(Integer empno) {
		this.empno = empno;
	}
	
	public Emp2VO getSelect_emp_name() {
		return select_emp_name;
	}

	public void setSelect_emp_name(Emp2VO select_emp_name) {
		this.select_emp_name = select_emp_name;
	}

	@Command
	public void showoneemp(@BindingParam("empno") Integer empno,@BindingParam("type") String type){
		if("no".equals(type)) {
			select_emp = empService.getOneEmp(empno);
			Sessions.getCurrent().setAttribute("empVO", select_emp);
		} else if("name".equals(type)) {
			select_emp_name = empService.getOneEmp(empno);
			Sessions.getCurrent().setAttribute("empVO", select_emp_name);
		}
		Executions.sendRedirect("listOneEmp.zul");
	}
	
	@Command
	public void showoneempbyinput(){
			select_emp = empService.getOneEmp(empno);
			Sessions.getCurrent().setAttribute("empVO", select_emp);
			Executions.sendRedirect("listOneEmp.zul");
	}
	
	public Validator getEmpnoValidator() {
		return new AbstractValidator() {
			public void validate(ValidationContext ctx) {
				Integer empno = (Integer)ctx.getProperties("empno")[0].getValue();
				if(null == empno) {
					addInvalidMessage(ctx, "empnoEmptyError", "請輸入員工編號");
				} else if(empno < 1000 || empno > 9999) {
					addInvalidMessage(ctx, "empnoFormatError", "員工編號格式錯誤");
				}
			}
		};
	}
}
