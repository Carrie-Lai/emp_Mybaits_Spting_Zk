package com.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.domain.Emp2VO;
import com.domain.EmpVO;
import com.mapper.EmpMapper;

@Repository
public class EmpDAO implements EmpDAO_interface {
	
	@Resource
	private EmpMapper empMapper;
	

	@Override
	public void insert(EmpVO empVO) {
			empMapper.insert(empVO);
	}

	@Override
	public void update(EmpVO empVO) {
			empMapper.update(empVO);
	}

	@Override
	public void delete(Integer empno) {
			empMapper.delete(empno);
	}

	@Override
	public Emp2VO findByPrimaryKey(Integer empno) {
		Emp2VO empVO = empMapper.findByPrimaryKey(empno);
		return empVO;
	}

	@Override
	public List<Emp2VO> getAll() {
		System.out.println(empMapper);
		List<Emp2VO> list = new ArrayList<Emp2VO>();
		list = empMapper.getAll();
		return list;
	}
}