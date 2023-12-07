package com.in28minutes.springboot.myfirstwebapp.login;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	//private Logger logger = LoggerFactory.getLogger(getClass());
	
	//Model
	//localhost:8080/login?name=이름
	@RequestMapping("login")
	public String gotoLoginPage(@RequestParam String name, ModelMap model) {
		model.put("name", name); //jsp페이지에 보내기
//		logger.debug("Request param is {}",name);
//		logger.info("I want this printed at info level");
//		logger.warn("I want this printed at warn level");
		System.out.println("Request param is "+ name); 
		
		return "login";
	}

}
