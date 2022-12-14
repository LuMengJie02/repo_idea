package com.lagou.controller;


import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseConentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {
    @Autowired
    private CourseConentService courseConentService;

    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonById(Integer courseId) {

        List<CourseSection> list = courseConentService.findSectionAndLessonByCourseId(courseId);
        ResponseResult responseResult = new ResponseResult(true, 200, "章节及课时内容查询成功", list);
        return responseResult;
    }


    /*
        回显章节对应的课程信息

     */

    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId) {

        Course courseByCourseId = courseConentService.findCourseByCourseId(courseId);

        ResponseResult responseResult = new ResponseResult(true, 200, "查询课程信息成功", courseByCourseId);
        return responseResult;
    }


    /*
        新增以及修改章节信息
     */
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saverOrUpdateSection(@RequestBody CourseSection courseSection) {

        //判断是否携带了章节id
        if (courseSection.getId() == null) {
            //新增
            courseConentService.saveSection(courseSection);
            return new ResponseResult(true, 200, "新增章节成功", null);
        } else {
            //更新
            courseConentService.updateSection(courseSection);
            return new ResponseResult(true, 200, "更新章节成功", null);
        }
    }

    /*
        修改章节状态
     */
    @RequestMapping("updateSectionStatus")
    public ResponseResult updateSectionStatus(int id, int status) {

        courseConentService.updateSectionStatus(id, status);

        //数据响应
        Map<Object, Object> map = new HashMap<>();
        map.put("status", status);

        ResponseResult responseResult = new ResponseResult(true, 200, "修改章节状态成功", map);

        return responseResult;
    }

    /*
       保存以及修改课时
     */
    @RequestMapping("/saveOrUpdateLesson")
    public ResponseResult saveOrUpdateLesson(@RequestBody CourseLesson lesson) {
            courseConentService.saveLesson(lesson);
            return new ResponseResult(true,200,"添加课时成功",null);
    }
}
