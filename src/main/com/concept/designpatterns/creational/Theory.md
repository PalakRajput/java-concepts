# Design patterns
Solution to some known problems.

# Creational DP
How the objects are created.
# Types:
1. Singleton
2. Prototype
3. Builder
4. Factory
5. Abstract Factory

# Singleton
Singleton pattern restricts the instantiation of a class and ensures that only one instance of the class exists in the Java Virtual Machine.
JVM usages:
java.lang.Runtime#getRuntime()
java.awt.Desktop#getDesktop()
java.lang.System#getSecurityManager()

# Factory
Instead of directly creating the object in client class. A factory class is created which returns the instance of the required class
Have switch statement to determine which factory should be called to create object.
```The Factory Method pattern suggests that you replace direct object construction calls (using the new operator) with calls to a special factory method. Don’t worry: the objects are still created via the new operator, but it’s being called from within the factory method. Objects returned by a factory method are often referred to as products.```
JVM usages:
java.util.Calendar#getInstance()

# Abstract Factory
Factory of factories. Can return multiple types of object.
