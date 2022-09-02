package com.lagou.controller;


import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.nio.cs.US_ASCII;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    /*
          用户分页&多条件组合查询
     */
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo) {

        PageInfo pageInfo = userService.findAllUserByPage(userVo);

        ResponseResult responseResult = new ResponseResult(true, 200, "分页多条件查询成功", pageInfo);
        return responseResult;

    }

    /**
     * 修改用户状态
     * ENABLE能登录，
     * DISABLE不能登录
     */
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(@RequestParam int id, @RequestParam String status) {

        if ("ENABLE".equalsIgnoreCase(status)) {
            userService.updateUserStatus(id, status);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改用户状态成功", status);
            return responseResult;
        } else {
            userService.updateUserStatus(id, status);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改用户状态成功", status);
            return responseResult;
        }

    }

    /*
        用户登录

     */
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {

        User user1 = userService.login(user);
        if (user1 != null) {
            //保存用户id及access_token 到session中
            HttpSession session = request.getSession();
            String access_token = UUID.randomUUID().toString();
            session.setAttribute("access_token", access_token);
            session.setAttribute("user_id", user1.getId());

            //将查询出来的信息响应给前台
            HashMap<String, Object> map = new HashMap<>();
            map.put("access_token", access_token);
            map.put("user_id", user1.getId());
            return new ResponseResult(true, 200, "登录成功", map);


        } else {
            return new ResponseResult(true, 400, "用户名密码错误", null);
        }

    }

    /*
        分配角色回显
     */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRelationRoleById(Integer id) {

        List<Role> roleList = userService.findUserRelationRoleById(id);

        return new ResponseResult(true, 200, "分配角色回显成功", roleList);
    }

    /*
        分配角色
     */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo) {

        userService.userContextRole(userVo);
        return new ResponseResult(true, 200, "分配角色成功", null);
    }

    /*
        获取用户权限获取菜单动态展示
     */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request) {

        //1.获取请求头中的token
        String header_token = request.getHeader("Authorization");

        //2.获取Session中的token
        String session_token = (String) request.getSession().getAttribute("access_token");

        //3.判断token是否一直
        if (header_token.equals(session_token)) {
            //1.获取用户id
            Integer user_id = (Integer) request.getSession().getAttribute("user_id");

            //2.调用service
            ResponseResult responseResult = userService.getUserPermissions(user_id);
            return responseResult;
        } else {

            ResponseResult responseResult = new ResponseResult(false, 400, "获取菜单信息失败", null);
            return responseResult;
        }

    }
}
