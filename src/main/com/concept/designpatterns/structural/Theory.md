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
