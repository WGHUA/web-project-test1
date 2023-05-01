package com.wgh.controller;

import com.alibaba.fastjson.JSON;
import com.wgh.bean.StudentOne;
import com.wgh.bean.dto.ResponsibilityChainContextDTO;
import com.wgh.service.impl.StudentOneService;
import com.wgh.service.impl.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author wughua
 * @Description TestController
 * @Date 2023/4/5
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    private StudentOneService studentOneService;
    @Autowired
    private TestService testService;

    @RequestMapping("/index")
    public String index() {
        log.info("你好，我是日志...");
        return "<div style='height:100%;line-height:100%;background-image:linear-gradient(45deg, #9F025E, #F9C929);" +
                "color:white;border-radius:2px;'>吴广华</div>";
    }

    @RequestMapping("/getStudentInfo")
    public List<StudentOne> getStudentInfo() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("查询学生信息");
        log.info(JSON.toJSONString(studentOneService.getStudentInfo()));
        stopWatch.stop();
        String s = stopWatch.toString();
        log.info(s);
        return studentOneService.getStudentInfo();
    }

    @RequestMapping("/saveStudentInfo")
    public Boolean saveStudentInfo(@RequestBody StudentOne studentOne) {
        return studentOneService.saveStudentInfo(studentOne);
    }

    @RequestMapping("/testResponsibilityChain")
    public ResponsibilityChainContextDTO testResponsibilityChain() {
        ResponsibilityChainContextDTO dto = ResponsibilityChainContextDTO.builder()
                .id("1")
                .name("zhangsan")
                .build();
        return testService.testResponsibilityChain(dto);
    }

}
