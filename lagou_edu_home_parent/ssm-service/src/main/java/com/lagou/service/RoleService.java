package com.lagou.service;

import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;

import java.util.List;

public interface RoleService {

    /*
      查询所有角色&条件进行查询
   */
    List<Role> findAllRole(Role role);

    /*
    根据角色id查询改角色关联的菜单信息id
 */
    List<Integer> findMenuByRoleId(Integer roleid);

    /*
        为角色分配菜单
     */
    void roleContextMenu(RoleMenuVo roleMenuVo);

    /*
        删除角色
     */
    void deleteRole(Integer roleid);
}
