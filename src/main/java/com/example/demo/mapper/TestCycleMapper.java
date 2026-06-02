package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.TestCycle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TestCycleMapper extends BaseMapper<TestCycle> {

    List<TestCycle> selectByCycleCode(@Param("cycleCode") String cycleCode);

}
