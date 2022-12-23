package com.storage.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/spring")
public class SpringController 
{
	@GetMapping("/set/cookie")
	public ResponseEntity Cookie() 
	{
		 ResponseCookie springCookie = ResponseCookie.from("user-id", "c2FtLnNtaXRoQGV4YW1wbGUuY29t")
				    .httpOnly(true)
				    .secure(false)
				    .path("/")
				    .maxAge(6000000)
				    .domain("example.com")
				    .build();
		 HttpHeaders headers = new HttpHeaders();
		 headers.add(HttpHeaders.SET_COOKIE, springCookie.toString());
		 headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
		 headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:8080");
		 
		return  ResponseEntity
			    .ok()
			    .headers(headers)
			    .body("cookie created from spring application...");
	}
	@GetMapping("/get/session")
	public String getSession(HttpServletRequest request,HttpServletResponse response) 
	{
		return "session received..." + request.getSession().getAttribute("user");
	}
	@GetMapping("/get/cookie")
	public String readCookie(
	    @CookieValue(name = "user-id", defaultValue = "default-user-id") String userId) {
	    return userId;
	}
}