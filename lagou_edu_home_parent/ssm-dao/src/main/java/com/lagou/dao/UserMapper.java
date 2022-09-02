package com.lagou.dao;

import com.lagou.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    /*
        用户分页&多条件查询
     */
    List<User> findAllUserByPage(UserVo userVo);

    /*
        修改用户状态
     */
    void updateUserStatus(@Param("id") int id, @Param("status") String status);

    /*
        用户登陆（根据用户名查询具体用户信息）
     */
    User login(User user);



    /*
        根据用户id清空中间表
     */
    void  deleteUserContextRole(Integer userId);

    /*
        分配角色
     */
    void userContextRole(User_Role_relation user_role_relation);




    /*
      根据用户id查询关联的角色信息 多个角色
   */
    List<Role> findUserRelationRoleById(Integer id);

    /*
        根据角色id，查询角色所拥有的父级菜单
     */
    List<Menu> findParentMenuByRoleId(List<Integer> ids);

    /*
        根据PId查询子菜单信息
     */
    List<Menu> findSubMenuByPid(Integer pid);

    /*
        获取用户拥有的资源信息
     */
    List<Resource> findResourceByRoleId(List<Integer> ids);
    
    List<Resource> findResourceByRoleId2(List<Integer> ids);


    void test11();
    void test21();
    void test31();
    void test41();
    void test51();
    void test61();
    void test71();

    void test1();
    void test2();
    void test3();
    void test4();
    void test5();
    void test6();
    void test7();




}
