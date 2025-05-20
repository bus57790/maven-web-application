package com.mt.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/employee")
public class EmployeeService {

	
	@RequestMapping(value = "/getEmployeeDetails", method = RequestMethod.GET)
	@ResponseBody
	String uploadImage(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession)
			throws JSONException {

		JSONObject js = new JSONObject();
		js.put("Name", "BETECH Solutions");
		js.put("Calling Name", "BETECH");
		js.put("DOB", "Aug 13, 1989");
		js.put("Hobbies", "Praying, Enginering, Studying, Training, Employing..");
		js.put("Places of Business", "America, Africa, India");
		return js.toString();
}
}
