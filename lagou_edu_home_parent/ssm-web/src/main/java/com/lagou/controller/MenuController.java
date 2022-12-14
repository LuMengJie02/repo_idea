package com.lagou.controller;

import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;


    /*
        查询所有菜单
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){

        List<Menu> allMenu = menuService.findAllMenu();
        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有菜单成功", allMenu);

        return responseResult ;

    }

    /*
        回显菜单信息
     */
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfOById(Integer id){

        //判断根据id的值 当前是更新还是添加 判断是否为-1
        if (id == -1){
            //添加信息 不需要回显menu信息
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);

            //封装信息
            HashMap<String, Object> map = new HashMap<>();
            map.put("muneInfo",null);
            map.put("parentMenuList",subMenuListByPid);

            return new ResponseResult(true,200,"添加回显成功",map);
        }else {

            //修改信息 回显菜单信息
           Menu menu =  menuService.findMenuById(id);
            //添加信息 不需要回显menu信息
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);

            //封装信息
            HashMap<String, Object> map = new HashMap<>();
            map.put("muneInfo",menu);
            map.put("parentMenuList",subMenuListByPid);

            return new ResponseResult(true,200,"修改回显成功",map);

        }
    }

}
