package com.springmvc.handlers;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.entity.User;

//@SessionAttributes(value= {"user"}, types= {String.class})
@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest {
	private static final String SUCCESS="success";
	/**
	 * 有@ModelAttribute  标记的方法，会在每个目标方法执行之前被SpringMVC调用
	 * @param id
	 * @param map
	 */
	@ModelAttribute
	public void getUser(@RequestParam(value="id" ,required=false) Integer id, Map<String, Object> map) {
		if(id != null) {
			User user = new User("admin", "12345", "Female", 18, 1);
			System.out.println("[DATABASE] "+user);
			map.put("abc", user);
		}
	}
	/**
	 * 运行流程：
	 * 1、执行@ModelAttribute 注解修饰的方法：从数据库中取出对象，把对象放入到了Map中。key为user
	 * 2、SpringMVC从Map中取出User对象，并把表单的请求参数赋给该User 对象的对应属性。
	 * 3、SpringMVC把上述对象传入目标方法的参数
	 * 
	 * Spring MVC确定目标方法POJO类型入参的过程
	 * 1、确定一个key：
	 * 		-- 若目标方法的POJO类型的参数木有使用@ModelAttribute作为修饰，则key为POJO类名第一个字母的小写
	 * 		-- 若使用@ModelAttributes来修饰，则key为@ModelAttributes注解的value属性值。
	 * 2、在implicitModel中查找key对应的对象，若存在，则作为入参传入
	 * 3、若implicitModel中不存在key对应的对象，则检查当前的Handler是否使用@SessionAttributes注解修饰，
	 * 若使用了该注解，且@SessionAttributes注解的value属性值中包含了key，则会从HttpSession中来获取key所对应的value值，若存在则直接传入到目标方法的入参中，若不存在则将跑出异常
	 * 4、若Handler没有表示@SessionAttributes注解或@SessionAttributes注解的value值中不包含key，则会通过反射来创建POJO类型的参数，传入为目标方法的参数
	 * 5、SpringMVC会把key和value 保存到implicitModel中，今儿会保存到request中。
	 * 
	 * @param user
	 * @return
	 * 
	 */
	@ResponseBody
	@RequestMapping("/testModelAttribute")
	public String teestModelAttribute(@ModelAttribute(value="abc")User user) {
		System.out.println("[VIEW] "+user);
		return SUCCESS;
	}
	/**
	 * @SessionAttributes 除了可以通过属性名指定需要放到session 中的属性外（实际上使用的是value属性值），
	 * 还可以通过模型属性的对象类型指定那些模型属于需要放到会话中（实际上使用的是types属性值）
	 * @SessionAttributes(value= {"user"}, types= {String.class}) 即表示将map中的key=“user”的key-value pair 加到 sessionScope
	 * 同时也将value的type为String.class 的key-value pair 加到sessionScope中
	 * 这个注解放在类的上面，而不能放在方法上面，这样这个类中的方法都会共用一个session scope
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping("/testSessionAttributes")
	public String testSessionAttributes(Map<String, Object> map) {
		map.put("user", new User("root", "admin","F",18,null));
		map.put("school", "university of Toronto");
		return SUCCESS;
	}
	/**
	 * 目标方法可以添加Map类型（实际上也可以是Model 类型或ModelMap 类型）的参数
	 * @param map
	 * @return
	 */
	@RequestMapping("/testMap")
	public String testMap(Map<String, Object> map) {
		map.put("user", new User("root", "admin","F",18,null));
		return SUCCESS;
	}
	/**
	 * 目标方法的返回值可以是ModelAndView类型。
	 * 其中可以包含视图和模型信息
	 * SpringMVC 会把ModelAndView的model中的数据放入到request scope object 中
	 * @return
	 */
	@RequestMapping("/testModelAndView")
	public ModelAndView testModelAndView() {
		String viewName = SUCCESS;
		ModelAndView model = new ModelAndView(viewName);
		model.addObject("time", new Date());
		return model;
	}
	/**
	 * 可以使用Servlet原生的API作为目标方法的参数
	 * 具体支持以下类型
	 * 		HttpServletRequest, HttpServletResponse
	 * 		HttpSession
	 * 		java.security.Principal
	 * 		Locale
	 * 		InputStream, OutputStream
	 * 		Reader, Writer
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/testServletAPI")
	public String testServletApi(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("[Test ServletApi as Parameters]: HttpServletRequest="+req+"; HttpServletResponse="+res);
		return SUCCESS;
	}
	/**
	 * Spring MVC 会按请求参数名和POJO 属性名进行自动匹配，自动为该对象填充属性值。
	 * 支持级联属性。如address.city, address.province 等
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/testPojo")
	public String testPojo(User user) {
		System.out.println("[Test Pojo object as paramenter]: "+user);
		return SUCCESS;
	}
	/**
	 * @CookieValue 映射一个Cookie值，属性同@RequestParam一样，有value、defaultValue、required
	 * 
	 * @param sessionId
	 * @return
	 */
	@RequestMapping("/testCookieValue")
	public String testCookieValue(@CookieValue(value="JSESSIONID")String sessionId) {
		System.out.println("[Test CookieValue]: JSESSIONID="+sessionId);
		return SUCCESS;
	}
	/**
	 * 了解：映射请求头信息
	 * @param acceptLanguage
	 * @return
	 */
	@RequestMapping("/testRequestHeader")
	public String testRequestHeader(@RequestHeader(value="Accept-Language") String acceptLanguage) {
		System.out.println("[test RequestHeader]: Accept-Language="+acceptLanguage);
		return SUCCESS;
	}
	/**
	 * @RequestParam 来映射请求参数
	 * value 值即请求参数的参数名
	 * required 表示该参数是否必须提供， 默认为true
	 * defaultValue 请求参数的默认值
	 * 
	 * @param id
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/testRequestParam")
	public String testRequestParam(@RequestParam(value="id", required=false, defaultValue="0")int id, @RequestParam(value="username") String username, @RequestParam(value="password")String password) {
		System.out.println("[test RequestParam] username="+username+"; password="+password+"; id="+id);
		return SUCCESS;
	}
	/**
	 * Rest 风格的URL
	 * 以CRUD为例：					以前不用rest风格
	 * 新增： /order POST				
	 * 修改:	 /order/1 PUT			update?id=1
	 * 获取： /order/1 GET			get?id=1
	 * 删除： /order/1 DELETE			delete?id=1
	 * 
	 * 如何发送PUT请求和DELETE请求？
	 * 	1、需要配置HiddenHttpMethodFilter
	 *  2、需要发送POST请求
	 *  3、需要在发送POST请求时携带一个name="_method"的隐藏域，值为DELETE或PUT
	 *  
	 * 在SpringMVC的目标方法中如何得到id呢？
	 * 	--使用@PathVariable 注解
	 * @param id
	 * @return
	 */
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
