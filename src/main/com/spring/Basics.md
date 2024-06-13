# Spring framework

### Application Context

Application context is the heart of the application. It encapsulates bean factory(which provides IoC container) and
allows user to
access beans. IoC container is responsible for creation/injection of all beans at startup.

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