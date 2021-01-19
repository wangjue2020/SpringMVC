* @RequestMapping

	 * 1、@RequestMapping 除了修饰方法，还可以修饰类
	 * 2、修饰类：提供初步的请求映射信息。相对于WEB应用的根目录
	 * 3、修饰方法：提供进一步的细分映射信息。相对于类定义处的URL。 
	 * 		若类定义处未标注@RequestMapping， 则方法出标记的URL相对于WEB应用的根目录
	
* REST & HiddenHttpMethodFilter  

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