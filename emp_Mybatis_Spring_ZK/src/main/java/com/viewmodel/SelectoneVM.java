package com.viewmodel;

import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.domain.Emp2VO;
import com.service.EmpService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class SelectoneVM {
	@WireVariable
	private EmpService empService;
	private Emp2VO select_emp;
	
	public Emp2VO getSelect_emp() {
		return select_emp;
	}



	public void setSelect_emp(Emp2VO select_emp) {
		this.select_emp = select_emp;
	}



	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		select_emp = (Emp2VO) Sessions.getCurrent().getAttribute("empVO");
		System.out.println(select_emp);
	}

}
