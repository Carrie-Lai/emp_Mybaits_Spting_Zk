package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.domain.Emp2VO;
import com.domain.EmpVO;

//@Repository
public interface EmpMapper {
    public void insert(EmpVO empVO);
    public void update(EmpVO empVO);
    public void delete(Integer empno);
    public Emp2VO findByPrimaryKey(Integer empno);
    public List<Emp2VO> getAll();
    public List<Emp2VO> getAllByPage(@Param("offset") Integer offset,@Param("limit") Integer limit);
}
