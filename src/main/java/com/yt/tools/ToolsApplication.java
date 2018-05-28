package com.yt.tools;

import com.yt.tools.rabbitMQ.UserInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping(value = "/api")
public class ToolsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToolsApplication.class, args);
	}

	@RequestMapping(value="/login",method = RequestMethod.POST)
	public Object login(@RequestBody UserInfo userInfo){

		System.out.println(userInfo.getUsername()+"-----------"+userInfo.getPassword());

		return "1";
	}
}
