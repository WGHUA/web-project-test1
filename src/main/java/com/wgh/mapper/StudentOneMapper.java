package com.wgh.mapper;

import com.wgh.bean.StudentOne;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author wughua
 * @Description StudentMapper
 * @Date 2023/4/8
 */
@Mapper
public interface StudentOneMapper {

    /**
     * 获取学生信息
     *
     * @return
     */
    List<StudentOne> getStudentInfo();


    /**
     * 保存学生信息
     *
     * @param studentOne
     * @return
     */
    int saveStudentInfo(StudentOne studentOne);
}
