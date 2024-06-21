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
@EnableMethodSecurity
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

    /*@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/admin")
    public ResponseEntity<String> adminApi() {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("Authenticated admin !");
    }*/
}
```

```java

@Component
public class CustomAuthProvider implements AuthenticationProvider {
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthProvider.class);
    @Autowired
    SigningKeyResolver signingKeyResolver;
    @Value("${client-id}")
    String appId;
    @Value("${tenant-id}")
    String tenantId;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        try {
            logger.info("Authenticate token");
            retrieveClaimsAndValidateClaims(authentication);
        } catch (ExpiredJwtException | MalformedJwtException | DecodingException e) {
            logger.info(e.getMessage());
            throw new ApplicationException("401", e.getMessage());
        } catch (UnsupportedJwtException | SignatureException | IllegalArgumentException ex) {
            logger.error("Exception occurred while validating token: {}", ex.getMessage(), ex);
            return null;
        }
        return successfulAuthentication(authentication.getPrincipal().toString());

    }

    private void retrieveClaimsAndValidateClaims(Authentication authentication) {
        String issuer = "IssuerURLInJWTToken";
        //signingKeyResolver -> component responsible for calling jwks endpoint and constructing public key
        Jws<Claims> claim = Jwts.parserBuilder().setSigningKeyResolver(signingKeyResolver).build()
                .parseClaimsJws(authentication.getPrincipal().toString());
        Claims claims = claim.getBody();
        validateClaimsOfJWT(issuer, claims);
    }

    private void validateClaimsOfJWT(String issuer, Claims claims) {
        //Validate claims here like userId, nbf, issuer, audience and throw exception.
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private Authentication successfulAuthentication(String accessToken) {
        return new UsernamePasswordAuthenticationToken(accessToken, accessToken, List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }


}
```

```java
class CustomFilter extends GenericFilterBean {
    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;
    private static final Logger log = LoggerFactory.getLogger(CustomFilter.class);


    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.securityContextRepository = new NullSecurityContextRepository();
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain)
            throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        try {
            String headerValue = request.getHeader("Authorization");
            String header = headerValue.substring(7); //header starts with Bearer so remove 'Bearer ' from token
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(header, header);
            Authentication authenticationResult = authenticationManager.authenticate(token);
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authenticationResult);
            SecurityContextHolder.setContext(context);
            this.securityContextRepository.saveContext(context, request, response);
            filterChain.doFilter(request, response);
        } catch (RuntimeException failed) {
            SecurityContextHolder.clearContext();
            log.error("Authentication failed for the request {}: {}", request.getServletPath(), failed.getMessage(), failed);
            checkExceptionClassAndSetHeaderAndStatus(response, failed);
        }
    }


    private void checkExceptionClassAndSetHeaderAndStatus(HttpServletResponse response, RuntimeException failed) {
        //check specific exceptions and set response status code in `response` variable accordingly
    }
}
```

### OAuth

For authorization using access token.

### Open-ID connect

Sits on top of OAuth 2.0 and provides authentication with identity token.

#### RSA

Asymmetric encryption algorithm where a private key is used to encrypt the data and public key is used to decrypt it.

> Generate 2048 bit private key:
> openssl genrsa -out test_key.pem 2048
> Generate it's corresponding public key:
> openssl rsa -in test_key.pem -outform PEM -pubout -out test_key.pem.pub

```java
class GenerateJWT {
    public String generateJWT() {
        String jwt = Jwts.builder()                     // (1)

                .header()                                   // (2) optional
                .keyId("aKeyId")
                .and()

                .subject("Bob")                             // (3) JSON Claims, or
                //.content(aByteArray, "text/plain")        //     any byte[] content, with media type

                .signWith(signingKey)                       // (4) if signing, or
                //.encryptWith(key, keyAlg, encryptionAlg)  //     if encrypting

                .compact();

        //jws = short for signed jwt
        String jws = Jwts.builder()

                .issuer("me")
                .subject("Bob")
                .audience().add("you").and()
                .expiration(expiration) //a java.util.Date
                .notBefore(notBefore) //a java.util.Date
                .issuedAt(new Date()) // for example, now
                .id(UUID.randomUUID().toString()); //just an example id
    }
}
```

#### Reading a JWT
You read (parse) a JWT as follows:

1. Use the Jwts.parser() method to create a JwtParserBuilder instance.
2. Optionally call keyLocator, verifyWith or decryptWith methods if you expect to parse signed or encrypted JWTs.
3. Call the build() method on the JwtParserBuilder to create and return a thread-safe JwtParser.
4. Call one of the various parse* methods with your compact JWT string, depending on the type of JWT you expect.
5. Wrap the parse* call in a try/catch block in case parsing, signature verification, or decryption fails.