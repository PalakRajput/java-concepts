## Structural Design Pattern

Provides a way to combine different objects and classes to create a bigger structure.

# Types:

1. Adapter Pattern
2. Bridge Pattern
3. Composite Pattern
4. Decorator Pattern
5. Facade Pattern
6. Flyweight Pattern
7. Proxy Pattern

# Adapter Design Pattern

Allow objects with incompatible interfaces to collaborate.
JVM Usages:
java.util.Arrays#asList()
java.util.Collections#list()
java.util.Collections#enumeration()

# Bridge Design Pattern

Bridge is a structural design pattern that lets you split a large class or a set of closely related classes into two
separate hierarchies—abstraction and implementation—which can be developed independently of each other.
The implementation of bridge design pattern follows the notion to prefer Composition to inheritance.

Suppose we have shape class and it has two implementation Circle and Rectangle and we want to extend this class
hierarchy to add colours
so there will now be 4 classes RedCircle, BlueCircle, RedRectangle, BlueRectangle.
Adding new shapes or colors to this hierarchy will grow the classes exponentially.

Bridge pattern allows to choose composition over inheritance.

# Facade Design Pattern

Provides a simpler interface to a complex library, framework or classes.
The subsystem(complex library, framework) is not aware of facade object.
The facade doesn't change the functionality of the subsystem.

Use case -> JDBC

Ex: We have video conversion library which provides classes/methods to determine video type, read it and then based on
the type process it.
Instead of calling all the methods of the library in client code, create a facade class which calls all the required
classes/methods of the library and return the desired output
Now this facade can be used at all places where we want to convert the video file.

# Decorator Design Pattern

This pattern is used to add additional features/behaviors to an instance of a class without affecting other instances of
the same class.

Ex: We have a Notification system where we send notification either using WhatsApp, Facebook, Slack
Now there can be a requirement to send two or three types of Notification, so we can't create classes for each
combination.

JVM Usages:
All subclasses of java.io.InputStream, OutputStream, Reader and Writer have constructors that accept objects of their
own type.
java.util.Collections, methods checkedXXX(), synchronizedXXX() and unmodifiableXXX().
javax.servlet.http.HttpServletRequestWrapper and HttpServletResponseWrapper

# Flyweight Design Pattern

Let the object share memory.
String pool implementation in java is an example of flyweight pattern.
This pattern suggests to store the intrinsic state(constant) into a separate object and extrinsic state(changeable) into
a separate object.
The intrinsic state should be initialized using constructor, and it should be immutable.
Saves RAM by sharing state between multiple objects.
JVM Usages:
java.lang.Integer#valueOf(int) (also Boolean, Byte, Character, Short, Long and BigDecimal)

# Proxy Design Pattern

Lets us provide the substitute or placeholder for another object. It controls access to the original object.
Proxy design pattern common uses are to control access or to provide a wrapper implementation for better performance.
Usages:
java.lang.reflect.Proxy
java.rmi.*
javax.ejb.EJB
javax.inject.Inject
javax.persistence.PersistenceContext
A real-world example is: Internet access is guarded behind a network proxy. All network requests go through a proxy
which first checks the requests for allowed websites and posted data to the network. If the request looks suspicious,
the proxy blocks the request — otherwise request passes through. (consider it as protection proxy)



