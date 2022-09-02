package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {

    /*
    多条件课程列表查询
     */

     List<Course> findCourseByConditioin(CourseVO courseVO);

     /*
          添加课程及讲师信息
      */
     public void saveOrUpdateCourse(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;


     /*
          根据id查询课程信息以及讲师信息
      */
     CourseVO findCourseById(Integer id);

     /*
         更新课程及讲师信息
      */
     void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

      /*
          修改课程状态
       */
       void updateCourseStatus(int courseid ,int status);
}
