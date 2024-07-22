https://medium.com/@sendvjs/difference-between-component-service-controller-and-repository-in-spring-5f9fa05bcb1d

# Spring framework

## IoC Containers:

1. BeanFactory
2. ApplicationContext

### Bean Factory

- Supports bean creation using xml file.
- Loads beans on demand (lazy loading by default).
- Supports only singleton and prototype bean scope.
- Does not register BeanFactoryPostProcessor and BeanPostProcessors automatically.
- If we are using bean factory then features like transaction and AOP will not take effect by default.

```java
public class TempClass {
    public static void main(String[] args) {
        // Create an XML configuration file.
        String xmlPath = "beans.xml";

        // Create a ClassPathXmlApplicationContext object and pass the XML configuration file to the constructor.
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(xmlPath);

        // Get a reference to the "hello" bean by its name.
        Hello hello = (Hello) ctx.getBean("hello");

        // Call the print() method on the bean.
        hello.print();
    }
}
```

### Application Context

- Supports bean creation using xml file or java configuration.
- Beans are loaded at the time of ioc container startup.
- Supports all bean scopes(Singleton, prototype, request, session, application, ...).
- Register BeanFactoryPostProcessor and BeanPostProcessors automatically.
- More flexible than BeanFactory.
- Supports internationalization(i18n)
  Application context is the heart of the application. It encapsulates bean factory(which provides IoC container) and
  allows user to access beans. IoC container is responsible for creation/injection of all beans at startup.

### Spring MVC Components:

#### Dispatcher Servlet(Front Controller)

Intercepts the request from client and determine the appropriate handler using the HandlerMapping component.

#### HandlerMapper

It identifies the resource responsible for handling the request. When using @RequestMapping, or it's specialized
annotations, RequestMappingHandlerMapping implementation is utilized.

#### HandlerExecutionChain

#### HandlerInterceptor

Interceptors are classes that are invoked before hitting the handler. We utilize interceptors when we need to execute
specific logic before or after the handler (endpoint method or controller). For example, we can develop an IPInterceptor
interceptor, which checks if the HTTP request originates from a blacklisted IP address.

```java
public class CustomInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // pre-processing logic
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //  post-processing logic
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // after-completion processing logic
    }

}

@Configuration
@EnableWebMVC // to be used only if you're using an non-spring boot application 
public class AppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CustomInterceptor());
    }
}
```

#### ModelAndView

#### ViewResolver

### Filters

These are part of the Java Servlet API and operate at a lower level compared to handler
interceptors. They intercept requests before they reach the servlet (DispatcherServlet if using Spring MVC) and can also
intercept responses before they are sent back to the client. Filters are typically used for tasks such as Global
logging, encoding/decoding, Authentication, request/response modification, and caching.

```java

@WebFilter(urlPatterns = "/*")
public class MyCustomFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Filter Initialized
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // Filtering Request
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        // Filter Destroyed
    }
}
```

### Dynamic proxies

Achieved by JDK Reflection API, it is used when the target class which will be proxied by any feature that relies on
proxy implements at least one interface. It is always preferred.
Thr proxy class will implement the same interface as the target class.

### CGLib(Code Generation Library) - Not active project any more instead Javassit is used now since spring 3.2

This is a third party library, used when our target class doesn't implement any interfaces.
The proxy is created through inheritance. A new class is created which inherits from the target class.

AOP, Transaction management, caching, spring security, scopes of bean etc are managed through proxies in spring.
