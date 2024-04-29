- SecurityContextHolder:
  The SecurityContextHolder is where Spring Security stores the details of who is authenticated.

- SecurityContext:
  is obtained from the SecurityContextHolder and contains the Authentication of the currently authenticated user.

- Authentication:
  Can be the input to AuthenticationManager to provide the credentials a user has provided to authenticate or the
  current user from the SecurityContext.

- GrantedAuthority:
  An authority that is granted to the principal on the Authentication (i.e. roles, scopes, etc.)

- AuthenticationManager:
  the API that defines how Spring Security’s Filters perform authentication.

- ProviderManager:
  the most common implementation of AuthenticationManager.

- AuthenticationProvider:
  used by ProviderManager to perform a specific type of authentication.

# SecurityContextHolder

The SecurityContextHolder is where Spring Security stores the details of who is authenticated. Spring Security does not
care how the SecurityContextHolder is populated. If it contains a value, then it is used as the currently authenticated
user.

The simplest way to indicate a user is authenticated is to set the SecurityContextHolder directly.

Setting SecurityContextHolder
JavaKotlin

```java
class Security {
    public void setSecurityContextHolder() {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication =
                new UsernamePasswordAuthenticationToken("username", "password", "ROLE_USER");
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
    }
}
```

We start by creating an empty SecurityContext.

1. It is important to create a new SecurityContext instance instead of using SecurityContextHolder.getContext()
   .setAuthentication(authentication) to avoid race conditions across multiple threads.
   Next we create a new Authentication object.
2. Spring Security does not care what type of Authentication implementation is set on the SecurityContext.
   A more common production scenario is UsernamePasswordAuthenticationToken(userDetails, password, authorities).
3. Finally, we set the SecurityContext on the SecurityContextHolder.
   Spring Security will use this information for authorization.
   <br/>
   Access Currently Authenticated User:

```java
class SecurityConfig {
    public void accessCurrentAuthenticatedUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String username = authentication.getName();
        Object principal = authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    }
}
```

# Authentication

The Authentication serves two main purposes within Spring Security:

An input to AuthenticationManager to provide the credentials a user has provided to authenticate. When used in this
scenario, isAuthenticated() returns false.

Represents the currently authenticated user. The current Authentication can be obtained from the SecurityContext.

# AuthenticationManager

(interface)
AuthenticationManager is the API that defines how Spring Security’s Filters perform authentication. The Authentication
that is returned is then set on the SecurityContextHolder by the controller (i.e. Spring Security’s Filterss) that
invoked the AuthenticationManager.

While the implementation of AuthenticationManager could be anything, the most common implementation is ProviderManager.

# ProviderManager

(class)
ProviderManager is the most commonly used implementation of AuthenticationManager. ProviderManager delegates to a List
of AuthenticationProviders. Each AuthenticationProvider has an opportunity to indicate that authentication should be
successful, fail, or indicate it cannot make a decision and allow a downstream AuthenticationProvider to decide. If none
of the configured AuthenticationProviders can authenticate, then authentication will fail with a
ProviderNotFoundException which is a special AuthenticationException that indicates the ProviderManager was not
configured to support the type of Authentication that was passed into it.

> In 6.0, @Configuration is removed from @EnableWebSecurity, @EnableMethodSecurity, @EnableGlobalMethodSecurity, and
> @EnableGlobalAuthentication.
>
> To prepare for this, wherever you are using one of these annotations, you may need to add @Configuration.

```java

@Configuration
@EanbleWebSecurity
class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(corsConfigurer()))
                //disable csrf
                .csrf(AbstractHttpConfigurer::disable)
                // stateless session
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // security filter is applied to endpoints starting with /api
                .securityMatcher("/api/**")
                .authorizeHttpRequests(authz -> authz
                        // all OPTIONS, swagger-ui or actuator endpoints are accessible without authentication.
                        //earlier there were mvcMatchers, antMatchers but now we have requestMatchers
//                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
//                        .requestMatchers("/swagger-ui/**").permitAll()
//                        .requestMatchers("/actuator/**").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(customFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    private AuthenticationFilter customFilter() {
        return new AuthenticationFilter(authenticationManager(), tracer);
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(customAuthProvider));
    }

    public CorsConfigurationSource corsConfigurer() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("originUrls"));
        corsConfiguration.setAllowedMethods(List.of("GET", "HEAD", "OPTIONS", "PUT", "POST", "DELETE"));
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return configurationSource;
    }
}
```