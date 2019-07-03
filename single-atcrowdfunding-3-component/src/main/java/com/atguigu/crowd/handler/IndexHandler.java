package com.atguigu.crowd.handler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexHandler {

	@RequestMapping("/index")
	public String showPotalData(Model model) {
		
		
		return "index";
	}
}
