package com.dao;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.domain.DeptVO;
import com.domain.EmpVO;
import com.mapper.DeptMapper;

@Repository
@Qualifier("deptDAO")
public class DeptDAO implements DeptDAO_interface {
	
	@Autowired
	private DeptMapper deptMapper;
	
	@Override
	public void insert(DeptVO deptVO) {
			deptMapper.insert(deptVO);
	}

	@Override
	public void update(DeptVO deptVO) {
			deptMapper.update(deptVO);
	}

	@Override
	public void delete(Integer deptno) {
			deptMapper.deleteEmpByDept(deptno);
			deptMapper.delete(deptno);
	}

	@Override
	public DeptVO findByPrimaryKey(Integer deptno) {
		return deptMapper.findByPrimaryKey(deptno);
	}

	@Override
	public List<DeptVO> getAll() {
		List<DeptVO> list = new ArrayList<DeptVO>();
		list = deptMapper.getAll();
		return list;
	}

	@Override
	public Set<EmpVO> getEmpsByDeptno(Integer deptno) {
		Set<EmpVO> set = new LinkedHashSet<EmpVO>();
		set = deptMapper.getEmpsByDeptno(deptno);
		return set;
	}
}