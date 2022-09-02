package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;


import java.util.List;

public interface CourseMapper {

    /*
    多条件列表查询
     */

     List<Course> findCourseByConditioin(CourseVO courseVo);

     /*
          新增课程信息
      */
      void saveCourse(Course course);

     /*
          新增将是信息
      */
      void saveTeacher(Teacher teacher);

      /*
            回显课程信息（根据id查询对应的课程信息及讲师信息）
       */
       CourseVO findCourseById(Integer id);

       /*
             修改看课程信息

        */

       void updateCourse(Course course);

        /*
        更新讲师信息
         */
        void updateTeacher(Teacher teacher);

        /*
            课程状态管理
         */
        void updateCourseStatus(Course course);

        /*

         */

}


