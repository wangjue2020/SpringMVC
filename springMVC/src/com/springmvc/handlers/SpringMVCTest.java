package com.springmvc.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest {
	private static final String SUCCESS="success";
	
	@RequestMapping(value="/testRest/{id}", method=RequestMethod.DELETE)
	@ResponseBody()
	public String testRestDelete(@PathVariable("id")Integer id) {
		System.out.println("test Rest [DELETE] id: "+id);
		return SUCCESS;
	}

	@RequestMapping(value="/testRest", method=RequestMethod.POST)
	public String testRestPost() {
		System.out.println("test Rest [POST]");
		return SUCCESS;
	}
	
	@ResponseBody()
	@RequestMapping(value="/testRest/{id}",method=RequestMethod.PUT)
	public String testRestPut(@PathVariable("id") Integer id) {
		System.out.println("test Rest [PUT] id: "+id);
		return SUCCESS;
	}
	
	@RequestMapping(value="/testRest/{id}", method=RequestMethod.GET)
	public String testRestGet(@PathVariable("id") Integer id) {
		System.out.println("test Rest [GET] id:"+id);
		return SUCCESS;
	}
	
	@RequestMapping("/testPathVariable/{id}&{username}")
	public String testPathVariable(@PathVariable("id") Integer id, @PathVariable("username") String username) {
		System.out.println("test PathVariable id: "+id);
		System.out.println("test PathVariable username: "+username);
		return SUCCESS;
	}
	/**
	 * 了解：可以使用params和headers来更加精确地设置映射要求。params 和headers 支持简单的表达式。
	 * @return
	 */
	@RequestMapping(value="/testParamsAndHeaders", params= {"username", "age!=10"},
			headers= {"Accept-Language=en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7"})
	public String testParamsAndHeaders() {
		System.out.println("testParamsAndHeaders");
		return SUCCESS;
	}
	/**
	 * 常用：使用method 属性指定请求方式
	 * @return
	 */
	@RequestMapping(value="/testMethod", method=RequestMethod.POST)
	public String testMethod() {
		System.out.println("testMethod");
		return SUCCESS;
	}
	/**
	 * 1、@RequestMapping 除了修饰方法，还可以修饰类
	 * 2、修饰类：提供初步的请求映射信息。相对于WEB应用的根目录
	 * 3、修饰方法：提供进一步的细分映射信息。相对于类定义处的URL。 
	 * 		若类定义处未标注@RequestMapping， 则方法出标记的URL相对于WEB应用的根目录
	 * @return
	 */
	@RequestMapping("/testRequestMapping")
	public String testRequestMapping() {
		System.out.println("testRequestMapping....");
		return SUCCESS;
	}
}
