package com.mapper;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.domain.DeptVO;
import com.domain.EmpVO;

//@Repository
public interface DeptMapper {
    public void insert(DeptVO deptVO);
    public void update(DeptVO deptVO);
    public void deleteEmpByDept(Integer deptno);
    public void delete(Integer deptno);
    public DeptVO findByPrimaryKey(Integer deptno);
    public List<DeptVO> getAll();
    //查詢某部門的員工(一對多)(回傳 Set)
    public Set<EmpVO> getEmpsByDeptno(Integer deptno);
}
