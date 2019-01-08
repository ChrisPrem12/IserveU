package in.iserveu.app.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
	
	@Value("${base.URL}")
	private String baseURL;

	@GetMapping("/")
	public String testAPI() {
		System.out.println(baseURL);
		return "index";
	}
}
