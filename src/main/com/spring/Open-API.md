### Documenting REST APIs using OPEN-API(Spring boot v3.x.x)

```xml

<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.5.0</version>
</dependency>
```

#### Default URL

- Default URL after adding the dependency: http://localhost:8080/v3/api-docs
- Custom path(change in application.properties): springdoc.api-docs.path=/api-docs
- For interacting with API: http://localhost:8080/swagger-ui/index.html
- Custom swagger path(change in application.properties): springdoc.swagger-ui.path=/swagger-ui-custom.html

#### Documentation

1. When the model class include JS-303 bean validation annotations like @Min, @Max, @NotNull, @NotBlank, the models are
   in swagger ui shows these constraints out of the box.
2. If using @RestControllerAdvice, we can use @ResponseStatus(HttpStatus.BAD_REQUEST) annotation to specify the error
   code on methods annotated with @ExceptionHandler
3. Using open api specific annotations like @Operations and @ApiResponse

```java

@RestController
class Controller {
    @Operation(summary = "Get a book by its id")
    @ApiResponses(value =
            {
                    @ApiResponse(responseCode = "200", description = "Found the book",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Book.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Book not found",
                            content = @Content)
            }
    )
    @GetMapping("/{id}")
    public Book findById(@Parameter(description = "id of book to be searched")
                         @PathVariable long id) {
        return repository.findById(id).orElseThrow(() -> new BookNotFoundException());
    }
}
```