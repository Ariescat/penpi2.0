package org.penpi.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.penpi.demo.search.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class DemoController {

	@Autowired
	DemoService demoService;

	@RequestMapping("/")
	@ResponseBody
	public String index() throws Exception {
		return "index";
	}

	@RequestMapping("/info")
	public String info() {
		return "info";
	}

	@RequestMapping("/findall")
	@ResponseBody
	public Map<String, Object> getUser() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", "success");
		map.put("data", demoService.findAll());
		return map;
	}

	@RequestMapping("/findbyid")
	@ResponseBody
	public Map<String, Object> findById(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", "success");
		map.put("data", demoService.findById(id));
		return map;
	}

	@RequestMapping("/add")
	@ResponseBody
	public Map<String, Object> save(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", "success");
		map.put("data", demoService.save(name));
		return map;
	}
}