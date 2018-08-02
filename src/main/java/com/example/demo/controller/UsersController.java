package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Msg;

@Controller
public class UsersController {
	
	@RequestMapping("/")
    public String index(Model model){
        Msg msg =  new Msg("测试标题","测试内容","额外信息，只对管理员显示");
        model.addAttribute("msg", msg);
        return "home";
    }
	
	@RequestMapping("/admin")
    @ResponseBody
    public String hello(){
        return "hello admin";
    }
	@RequestMapping("/haha")
    @ResponseBody
    public String hello2(String id){
        return "hello haha" + id;
    }
}
