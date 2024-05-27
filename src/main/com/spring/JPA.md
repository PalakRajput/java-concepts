## JPA Relations

### @ManyToOne, @OneToMany, @ManyToMany, @OneToOne

> There is a Student class which has fields like fName, lName, gender, address. The student can take multiple courses.
> A teacher can teach multiple courses but 1 course is taught by 1 teacher only.
> For each course there is a course material

```java

@Entity
class Course {
    String title;
}

/**
 * In this example the teacher is the owning side and course
 * is the referencing side.
 */
@Entity
class Teacher {
    String fName;
    String lName;
    @ManyToOne
    @JoinColumn(name = "TEACHER_ID", referencedColumnName = "ID")
    // Using this annotation will tell JPA that the COURSE table must have a foreign key column TEACHER_ID that references the TEACHER table's ID column.
    List<Course> courses;
}


/**
 * Course can become owning side by mapping Teacher field 
 * with @ManyToOne. With this we don't need List<Course1> in Teacher1.
 */
@Entity
class Course1 {
    String title;
    @ManyToOne
    @JoinColumn(name = "TEACHER_ID", referencedColumnName = "ID", cascade = CascadeType.PERSIST)
    private Teacher1 teacher;
}

//Adding cascade tells JPA to persist teacher while persisting courses.
//Types of cascading: PERSIST, MERGE, REMOVE, REFRESH, DETACH, ALL(combination of all types)

/**
 * To achieve bi-directional relation. 
 * Course1 has @ManyToOne and is the owning side with @JoinColumn
 * Teacher1 is referencing side with @OneToMany(mappedBy = "fieldInOwningSide")
 */
@Entity
public class Teacher1 {
    // ...
    @OneToMany(mappedBy = "teacher", fetch = FetchType.EAGER / LAZY)
    private List<Course1> courses;
}

/**
 * @OneToOne between Course and CourseMaterial.
 */
@Entity
public class CourseMaterial {
    @Id
    private Long id;
    private String url;
    @OneToOne(optional = false)
    @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID")
    private Course2 course;
}

@Entity
class Course2 {
    String title;
    int courseId;
    @ManyToOne
    @JoinColumn(name = "TEACHER_ID", referencedColumnName = "ID", cascade = CascadeType.PERSIST)
    private Teacher1 teacher;
    @OneToOne(mappedBy = "course")
    private CourseMaterial material;
}
```

> Owning side should preferably the entity where we will have the foreign key present.

* A relation may be optional or mandatory but if we define using @OneToMany it is always optional. While with ManyToOne
  we can specify whether it should be mandatory or not.

> `@ManyToOne(optional = false)` now the relation will be mandatory.

```java
//A book can be written by multiple authors and 1 author can have multiple books.
//@JoinTable -> name of the table which holds the relation in DB
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors;

}

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}

```

#### Cascading

When we perform some action on the target entity, the same action will be applied to the associated entity.

1. Cascade Type PERSIST propagates the persist operation from a parent to a child entity. When parent is saved, child
   will also be saved.
2. CascadeType.MERGE propagates the merge operation from a parent to a child entity.
3. CascadeType.REMOVE propagates the remove operation from parent to child entity. Similar to JPA’s CascadeType.REMOVE,
   we have CascadeType.DELETE, which is specific to Hibernate.

#### Spring Data annotations:

1. For named parameter, @Param
   @Query("FROM Person p WHERE p.name = :name")
   Person findByName(@Param("name") String name);
2. Calling stored procedures:

```java

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "count_by_name",
                procedureName = "person.count_by_name",
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "name",
                                type = String.class),
                        @StoredProcedureParameter(
                                mode = ParameterMode.OUT,
                                name = "count",
                                type = Long.class)
                }
        )
})
class Person {
}

//After this, we can refer to it in the repository with the name we declared in the name argument:

@Procedure(name = "count_by_name")
long getCountByName(@Param("name") String name);
```

3. @Modifying -> for update queries
4. @Query

```java

@Query(
        value = "SELECT * FROM USERS u WHERE u.status = 1",
        nativeQuery = true)
    //-> if nativeQuery flag is set to true then we can use table names instead of entity and write queries like how we would write in db tool
Object findAll();
```
5. Sorting in JPA predefined methods
```java
List<User> users = userRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
```

6. Joins
```java
@Query(value = "SELECT new ResultDTO(c.id, o.id, p.id, c.name, c.email, o.orderDate, p.productName, p.price) "
  + " from Customer c, CustomerOrder o ,Product p "
  + " where c.id=o.customer.id "
  + " and o.id=p.customerOrder.id "
  + " and c.id=?1 ")
List<ResultDTO> findResultDTOByCustomer(Long id);
```