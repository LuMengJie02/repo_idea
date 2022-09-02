package com.lagou.dao;

import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;
import com.sun.org.apache.bcel.internal.generic.INEG;

import java.util.List;

public interface RoleMapper {

    /*
        查询所有角色&条件进行查询
     */
    List<Role> findAllRole(Role role);

    /*
        根据角色id查询改角色关联的菜单信息id
     */
    List<Integer> findMenuByRoleId(Integer roleid);

    /*
        根据roleId清空中间表的关联关系
     */
    void  deleteRoleContextMenu(Integer rid);

    /*
        为角色分配菜单信息
     */

    void roleContextMenu(Role_menu_relation role_menu_relation);

    /*
        删除角色
     */
    void deleteRole(Integer roleid);
}
