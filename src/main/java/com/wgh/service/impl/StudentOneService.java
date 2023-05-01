package com.wgh.service.impl;

import com.wgh.bean.StudentOne;

import java.util.List;

/**
 * @Author wughua
 * @Description StudentOneService
 * @Date 2023/4/8
 */
public interface StudentOneService {

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
    Boolean saveStudentInfo(StudentOne studentOne);
}
