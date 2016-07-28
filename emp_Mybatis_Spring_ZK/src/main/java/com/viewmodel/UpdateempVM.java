package com.viewmodel;

import java.util.List;

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

import com.domain.DeptVO;
import com.domain.Emp2VO;
import com.domain.EmpVO;
import com.service.DeptService;
import com.service.EmpService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class UpdateempVM {
	@WireVariable
	private EmpService empService;
	@WireVariable
	private DeptService deptService;
	private EmpVO select_emp;
	private ListModelList<DeptVO> deptList;
	private DeptVO deptVO;
	
	@Init
	public void init() {
		select_emp = (EmpVO) Sessions.getCurrent().getAttribute("empVO");
		deptList = new ListModelList<DeptVO>();
		List<DeptVO> dList = deptService.getAll();
		deptList.addAll(dList);
		setDeptVO(deptService.getOneDept(select_emp.getDeptno()));
	}

	public EmpVO getSelect_emp() {
		return select_emp;
	}

	public void setSelect_emp(EmpVO select_emp) {
		this.select_emp = select_emp;
	}

	public ListModelList<DeptVO> getDeptList() {
		return deptList;
	}

	public DeptVO getDeptVO() {
		return deptVO;
	}

	public void setDeptVO(DeptVO deptVO) {
		this.deptVO = deptVO;
	}
	
	@Command
	public void submit(@BindingParam("deptno") Integer deptno) {
		select_emp.setDeptno(deptno);
		empService.updateEmp(select_emp);
		Emp2VO empVO = empService.getOneEmp(select_emp.getEmpno());
		Sessions.getCurrent().setAttribute("empVO", empVO);
		Executions.sendRedirect("listOneEmp.zul");
	}
	
	public Validator getFormValidator() {
		return new AbstractValidator() {
			public void validate(ValidationContext ctx) {
				String ename = (String)ctx.getProperties("ename")[0].getValue();
				String job = (String)ctx.getProperties("job")[0].getValue();
				Double sal = (Double)ctx.getProperties("sal")[0].getValue();
				Double comm = (Double)ctx.getProperties("comm")[0].getValue();
				String regex = "^[\u4e00-\u9fa5_a-zA-Z0-9]+$";
				if(null == ename || ename.trim().isEmpty()) {
					addInvalidMessage(ctx, "enameEmptyError", "員工姓名：請勿空白");
				} else if(ename.trim().length() < 2 || ename.trim().length() > 10 || !ename.matches(regex)) {
					addInvalidMessage(ctx, "enameFormatError", "員工姓名：只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				if(null == job || job.trim().isEmpty()) {
					addInvalidMessage(ctx, "jobEmptyError", "職位：請勿空白");
				} else if(job.trim().length() > 9) {
					addInvalidMessage(ctx, "jobLengthError", "職位：長度超過");
				}
				if(null == sal || sal.isNaN()) {
					addInvalidMessage(ctx, "salEmptyError", "薪水：請填數字");
				} else if(sal < 0 || sal > 9999999) {
					addInvalidMessage(ctx, "salFormatError", "薪水：需介於0~999999之間");
				}
				if(null == comm || comm.isNaN()) {
					addInvalidMessage(ctx, "commEmptyError", "獎金：請填數字");
				} else if(comm < 0 || comm > 9999999) {
					addInvalidMessage(ctx, "commFormatError", "獎金：需介於0~999999之間");
				}
			}
		};
	}
	
	
	

}
