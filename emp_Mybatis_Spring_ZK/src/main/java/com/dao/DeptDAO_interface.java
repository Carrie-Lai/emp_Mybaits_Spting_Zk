package com.dao;

import java.util.List;
import java.util.Set;

import com.domain.DeptVO;
import com.domain.EmpVO;

public interface DeptDAO_interface {
	      public void insert(DeptVO deptVO);
          public void update(DeptVO deptVO);
          public void delete(Integer deptno);
          public DeptVO findByPrimaryKey(Integer deptno);
	      public List<DeptVO> getAll();
	      public Set<EmpVO> getEmpsByDeptno(Integer deptno);
}
