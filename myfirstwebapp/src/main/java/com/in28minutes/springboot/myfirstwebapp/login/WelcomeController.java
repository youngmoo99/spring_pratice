package com.in28minutes.springboot.myfirstwebapp.login;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name") //세션에 값 저장
public class WelcomeController {
	
	//Model
	//localhost:8080/login?name=이름
	@RequestMapping(value="/", method= RequestMethod.GET)
	public String gotoWelcomePage(ModelMap model) {
		model.put("name",getLoggedinUsername());
		return "welcome";
	}
	
	
	private String getLoggedinUsername() {
		 org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 
		return authentication.getName();
	}
	


}
