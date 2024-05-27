## Caching

Add spring-boot-starter-cache to pom.xml
This will create an in memory concurrent hashmap for cache.

* @EnableCaching -> Enables caching. Class level annotation(put on Application.java)
* @Cacheable -> method level annotation.
  > @Cacheable(cacheNames = "subjects", key = "#root.target.key", unless="#result == null or #result.size() == 0")<br/>
  <br/>
  > key is to specify using which value we will uniquely identify the elements in subjects cache.(here we have specified
  root.target.key because the method on which this annotation is applied doesn't take any input param).
  <br/>
  > Unless condition specify that the result will not be cached if the condition is met.(result is null or result which
  is a list in this case has size = 0)
* @CachePut("cacheName") -> Used to update cache. This will execute the method and update or add the entry in cache but
  @Cacheable will not execute the method if value is found in the cache. So @Cacheable and @CachePut should not be used
  together as they might cause unexpected behavior in the application.
* @CacheEvict(value="employee", key="#id") -> Used to delete entry from cache.
* @Caching(evict = {
  @CacheEvict(“address”),
  @CacheEvict(value=“employee”, key=”#employee.id”)
  }) -> This annotation is used for using different caching annotations together.
* @CacheConfig -> Class level annotation.

Spring boot cache providers: JCache
1. EhCache
2. Hazelcast
3. Infinispan
4. Couchbase
5. Redis
6. Caffeine
7. Simple(ConcurrentHashMap)

### Redis
Redis(Remote dictionary Server) is an in-memory data store.
<br/>
Add the below dependency in pom.xml
```xml
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-data-redis</artifactId>
   <version>3.1.2</version>
</dependency>
```

Add below properties in application.yml
```yaml
spring:
  cache:
    type: redis
    host: localhost
    port: 6379
    redis:
      time-to-live: 60000 #Set expiration time for cached data. Optional property in ms.
```

