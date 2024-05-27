## Bootstrap classloader

Parent of all class loaders. Loads JDK internal classes present in rt.jar and from java_home/jre/lib. This CL is part of
JVM and is written in native code so different platforms might have different implementation of this CL.

## Extension classloader

Child of bootstrap classloader and is responsible for loading classes that are extensions of the standard core Java
classes. Loads classes from java_home/lib/ext.

## Application/System classloader

Loads all the application level classes from classpath.

> ### Delegation Hierarchy principle:
> Class loaders follow the delegation model, where on request to find a class or resource, a ClassLoader instance will
> delegate the search of the class or resource to the parent class loader.
>
> Let’s say we have a request to load an application class into the JVM. The system class loader first delegates the
> loading of that class to its parent extension class loader, which in turn delegates it to the bootstrap class
> loader.
>
> Only if the bootstrap and then the extension class loader are unsuccessful in loading the class, the system class
> loader
> tries to load the class itself.

### Methods of java.lang.Classloader

1. **loadClass(String name, boolean resolve):** This method is used to load the classes which are referenced by the JVM. It
takes the name of the class as a parameter. This is of type loadClass(String, boolean).
2. **defineClass():** The defineClass() method is a final method and cannot be overridden. This method is used to define a
array of bytes as an instance of class. If the class is invalid then it throws ClassFormatError.
3. **findClass(String name):** This method is used to find a specified class. This method only finds but doesn’t load the
class.
4. **findLoadedClass(String name):** This method is used to verify whether the Class referenced by the JVM was previously
loaded or not.
5. **Class.forName(String name, boolean initialize, ClassLoader loader):** This method is used to load the class as well as
initialize the class. This method also gives the option to choose any one of the ClassLoaders. If the ClassLoader
parameter is NULL then Bootstrap ClassLoader is used.

