package com.viewmodel;

import java.util.Date;
import java.util.List;

import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;

import com.domain.DeptVO;
import com.domain.Emp2VO;
import com.domain.EmpVO;
import com.service.DeptService;
import com.service.EmpService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class AddempVM {
	
	@WireVariable
	private EmpService empService;
	@WireVariable
	private DeptService deptService;
	private Emp2VO empVO;
	private ListModelList<DeptVO> deptList;
	private DeptVO deptVO;
	
	@Init
	public void init(){
		empVO = new Emp2VO();
		empVO.setEname("測試1");
		empVO.setJob("MANAGER");
		empVO.setHiredate(new java.sql.Date(new Date().getTime()));
		empVO.setSal(Double.valueOf("10000"));
		empVO.setComm(Double.valueOf("100"));
		deptList = new ListModelList<DeptVO>();
		List<DeptVO> dList = deptService.getAll();
		deptList.addAll(dList);
		if (null != deptList && deptList.size() > 0) {
			setDeptVO(deptList.get(0));
		}
	}
	
	public Emp2VO getEmpVO() {
		return empVO;
	}
	public void setEmpVO(Emp2VO empVO) {
		this.empVO = empVO;
	}

	public ListModelList<DeptVO> getDeptList() {
		return deptList;
	}

	public void setDeptList(ListModelList<DeptVO> deptList) {
		this.deptList = deptList;
	}

	public DeptVO getDeptVO() {
		return deptVO;
	}

	public void setDeptVO(DeptVO deptVO) {
		this.deptVO = deptVO;
	}
	
	@Command
	public void submit(@BindingParam("deptno") Integer deptno) {
		EmpVO eVO = new EmpVO();
		eVO.setEname(empVO.getEname());
		eVO.setJob(empVO.getJob());
		eVO.setHiredate(empVO.getHiredate());
		eVO.setSal(empVO.getSal());
		eVO.setComm(empVO.getComm());
		eVO.setDeptno(deptno);
		empService.addEmp(eVO);
		Executions.sendRedirect("listAllEmp.zul");
	}
	
	public Validator getFormValidator() {
		return new AbstractValidator() {
			public void validate(ValidationContext ctx) {
				String ename = (String)ctx.getProperties("ename")[0].getValue();
				String job = (String)ctx.getProperties("job")[0].getValue();
				Double sal = (Double)ctx.getProperties("sal")[0].getValue();
				System.out.println(sal);
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
