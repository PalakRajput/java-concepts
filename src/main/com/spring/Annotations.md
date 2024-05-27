* @Bean -> defines a bean, applied on a method level.
* @Configuration -> Annotated on a class which has methods with @Bean annotation.
* @Component -> Mark a class as Java bean.
* @Service, @Repository, @Controller -> under the hoos uses @Component but used for specific classes(no difference in
  how the bean is created.)
* @Controller -> returns view
* @RestController -> @Controller + @ResponseBody, writes data in response body in specific format like json, xml
* @ComponentScan -> looks for java bean in same package and all subpackages.
* @Autowired -> for injecting dependencies either through setter, constructor or field
* @SpringBootConfiguration -> @EnableAutoConfiguration + @ComponentScan + @Configuration
* @Value -> to read properties from environment/properties file.
* @Scope("") -> to define the scope of the bean
* @Primary -> to mark the bean as primary bean
* @Qualifier("nameOfTheBean") -> to inject specific bean if there are multiple beans present in our project.
* 