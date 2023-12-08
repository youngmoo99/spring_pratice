package com.in28minutes.springboot.myfirstwebapp.login;

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
public class LoginController {
	//private Logger logger = LoggerFactory.getLogger(getClass());
	private AuthenticationService authenticationService;
	
	public LoginController(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}

	//Model
	//localhost:8080/login?name=이름
	@RequestMapping(value="login", method= RequestMethod.GET)
	public String gotoLoginPage() {
		
		
		
//		logger.debug("Request param is {}",name);
//		logger.info("I want this printed at info level");
//		logger.warn("I want this printed at warn level");
		 
		return "login";
	}
	
	@RequestMapping(value="login", method= RequestMethod.POST)
	public String gotoWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap model) {
		//인증 로직 
		// name -in28minutes
		// pwd - dummy 
		if(authenticationService.authenticate(name, password)) {
			model.put("name",name);
			
			
	
			return "welcome";
		}
		model.put("ErrorMessage", "name or pwd is not true ! try again");
		
		return "login";
	}

}
