* @RequestMapping
	```
	 * 1、@RequestMapping 除了修饰方法，还可以修饰类
	 * 2、修饰类：提供初步的请求映射信息。相对于WEB应用的根目录
	 * 3、修饰方法：提供进一步的细分映射信息。相对于类定义处的URL。 
	 * 		若类定义处未标注@RequestMapping， 则方法出标记的URL相对于WEB应用的根目录
	```
* REST & HiddenHttpMethodFilter  
	```
	 * Rest 风格的URL
	 * 以CRUD为例：				以前不用rest风格的时候
	 * 新增： /order POST				
	 * 修改： /order/1 PUT			update?id=1
	 * 获取： /order/1 GET			get?id=1
	 * 删除： /order/1 DELETE		delete?id=1
	 * 
	 * 如何发送PUT请求和DELETE请求？
	 * 	1、需要配置HiddenHttpMethodFilter
	 *  2、需要发送POST请求
	 *  3、需要在发送POST请求时携带一个name="_method"的隐藏域，值为DELETE或PUT
	 *  
	 * 在SpringMVC的目标方法中如何得到id呢？
	 * 	--使用@PathVariable 注解
	 ```
* @RequestParam
	```
	 * @RequestParam 来映射请求参数
	 * value 值即请求参数的参数名
	 * required 表示该参数是否必须提供， 默认为true
	 * defaultValue 请求参数的默认值
	 ```
* @RequestHeader
	```
	@RequestMapping("/testRequestHeader")
	public String testRequestHeader(@RequestHeader(value="Accept-Language") String acceptLanguage) {
		System.out.println("[test RequestHeader]: Accept-Language="+acceptLanguage);
		return SUCCESS;
	}
	```
* @CookieValue 
	```
	//映射一个Cookie值，属性同@RequestParam一样，有value、defaultValue、required
	@RequestMapping("/testCookieValue")
	public String testCookieValue(@CookieValue(value="JSESSIONID")String sessionId) {
		System.out.println("[Test CookieValue]: JSESSIONID="+sessionId);
		return SUCCESS;
	}
	```
* 使用POJO对象绑定请求参数
	```
	 * Spring MVC 会按请求参数名和POJO 属性名进行自动匹配，自动为该对象填充属性值。
	 * 支持级联属性。如address.city, address.province 等
	 @RequestMapping("/testPojo")
	 public String testPojo(User user) { 
		System.out.println("[Test Pojo object as paramenter]: "+user);
		return SUCCESS;
	 }
	 ```
* 使用Servlet原生的API作为目标方法的参数
	```
	 * 具体支持以下类型
	 * 	HttpServletRequest, HttpServletResponse
	 * 	HttpSession
	 * 	java.security.Principal
	 * 	Locale
	 * 	InputStream, OutputStream
	 * 	Reader, Writer  
	 @RequestMapping("/testServletAPI")
	 public String testServletApi(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("[Test ServletApi as Parameters]: HttpServletRequest="+req+"; HttpServletResponse="+res);
		return SUCCESS;
	 }
	 ```
* 处理模型数据之 ModelAndView
	```
	 * 目标方法的返回值可以是ModelAndView类型。
	 * 其中可以包含视图和模型信息
	 * SpringMVC 会把ModelAndView的model中的数据放入到request scope object 中
	 @RequestMapping("/testModelAndView")
	 public ModelAndView testModelAndView() { 
		String viewName = SUCCESS;
		ModelAndView model = new ModelAndView(viewName);
		model.addObject("time", new Date());
		return model;
	 }
	 ```
* 处理模型数据之Map
	```
	//目标方法可以添加Map类型（实际上也可以是Model 类型或ModelMap 类型）的参数
	@RequestMapping("/testMap")
	public String testMap(Map<String, Object> map) {
		map.put("user", new User("root", "admin","F",18,null));
		return SUCCESS;
	}
	```
* @SessionAttributes(value={...}, types={...})
	```
	 * 这个注解放在类的上面，而不能放在方法上面，这样这个类中的方法都会共用一个session scope
	* @SessionAttributes 除了可以通过属性名指定需要放到session 中的属性外（实际上使用的是value属性值），
	 * 还可以通过模型属性的对象类型指定那些模型属于需要放到会话中（实际上使用的是types属性值）
	 * @SessionAttributes(value= {"user"}, types= {String.class}) 即表示将map中的key=“user”的key-value pair 加到 sessionScope
	 * 同时也将value的type为String.class 的key-value pair 加到sessionScope中  
	 @SessionAttributes(value= {"user"}, types= {String.class})
	 @RequestMapping("/springmvc")
	 @Controller
	  public class SpringMVCTest {
		private static final String SUCCESS="success";
		@RequestMapping("/testSessionAttributes")
	 	public String testSessionAttributes(Map<String, Object> map) {
			map.put("user", new User("root", "admin","F",18,null));
			map.put("school", "university of Toronto");
			return SUCCESS;
		 }
	  }
	```