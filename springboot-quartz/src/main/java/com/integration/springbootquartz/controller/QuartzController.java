package com.integration.springbootquartz.controller;

import com.integration.springbootquartz.entity.AppQuartz;
import com.integration.springbootquartz.service.QuartzService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ZhangGang on 2019/12/25
 */
@RestController
@RequestMapping("/quartz")
public class QuartzController {

    @Autowired
    private QuartzService quartzService;

    //测试
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() throws Exception {
        return "success";
    }

    //添加一个job
    @RequestMapping(value = "/addJob", method = RequestMethod.POST)
    public String addjob(@RequestBody AppQuartz appQuartz) throws Exception {
        return quartzService.addJob(appQuartz);
    }

    //暂停job
    @RequestMapping(value = "/pauseJob", method = RequestMethod.POST)
    public String pausejob(@RequestBody Integer[] quartzIds) {
        try {
            return quartzService.pausejob(quartzIds);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    //恢复job
    @RequestMapping(value = "/resumeJob", method = RequestMethod.POST)
    public String resumejob(@RequestBody Integer[] quartzIds) {
        try {
            return quartzService.resumejob(quartzIds);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


    //删除job
    @RequestMapping(value = "/deletJob", method = RequestMethod.POST)
    public String deletjob(@RequestBody Integer[] quartzIds) {
        try {
            return quartzService.deletjob(quartzIds);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    //修改
    @RequestMapping(value = "/updateJob", method = RequestMethod.POST)
    public String modifyJob(@RequestBody AppQuartz appQuartz) {
        try {
            return quartzService.modifyJob(appQuartz);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    //暂停所有
    @RequestMapping(value = "/pauseAll", method = RequestMethod.GET)
    public String pauseAllJob() {
        try {
            return quartzService.pauseAllJob();
        } catch (SchedulerException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    //恢复所有
    @RequestMapping(value = "/repauseAll", method = RequestMethod.GET)
    public String repauseAllJob() throws Exception {
        return quartzService.resumeAllJob();
    }
}
