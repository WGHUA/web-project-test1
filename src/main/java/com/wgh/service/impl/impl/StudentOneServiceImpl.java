package com.wgh.service.impl.impl;

import com.wgh.bean.StudentOne;
import com.wgh.mapper.StudentOneMapper;
import com.wgh.service.impl.StudentOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author wughua
 * @Description StudentOneServiceImpl
 * @Date 2023/4/8
 */
@Service
public class StudentOneServiceImpl implements StudentOneService {

    @Autowired
    private StudentOneMapper studentOneMapper;

    @Override
    public List<StudentOne> getStudentInfo() {
        return studentOneMapper.getStudentInfo();
    }

    @Override
    public Boolean saveStudentInfo(StudentOne studentOne) {
        int i = studentOneMapper.saveStudentInfo(studentOne);
        if (i > 0) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
