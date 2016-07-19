package com.dao;

import java.util.List;

import com.domain.Emp2VO;
import com.domain.EmpVO;

public interface EmpDAO_interface {
          public void insert(EmpVO empVO);
          public void update(EmpVO empVO);
          public void delete(Integer empno);
          public Emp2VO findByPrimaryKey(Integer empno);
          public List<Emp2VO> getAll();
}
