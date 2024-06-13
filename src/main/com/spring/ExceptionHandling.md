### Exception Handling

Before @ControllerAdvice, we can write @ExceptionHandler in controller class but this have to be written in all
controllers.

The@ControllerAdvice annotation allows us to consolidate our multiple, scattered @ExceptionHandlers from before into a
single, global error handling component.

The actual mechanism is extremely simple but also very flexible:

- It gives us full control over the body of the response as well as the status code.
- It provides mapping of several exceptions to the same method, to be handled together.
- It makes good use of the newer RESTful ResponseEntity response.

One thing to keep in mind here is to match the exceptions declared with @ExceptionHandler to the exception used as the
argument of the method.

```java

@ControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleException(IllegalArgumentException ie) {
        return ResponseEntity.status(400).body(ie.getMessage());
    }
}
```

Spring 5 and above has ResponseStatusException

```java

@RestController
class DemoController {
    @GetMapping(value = "/{id}")
    public Foo findById(@PathVariable("id") Long id, HttpServletResponse response) {
        try {
            Foo resourceById = RestPreconditions.checkFound(service.findOne(id));

            eventPublisher.publishEvent(new SingleResourceRetrievedEvent(this, response));
            return resourceById;
        } catch (MyResourceNotFoundException exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Foo Not Found", exc);
        }
    }
}
```