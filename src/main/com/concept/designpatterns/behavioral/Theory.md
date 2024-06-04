## Behavioral Design Pattern

These design patterns are concerned with algorithms and the assignment of responsibilities within the objects.

# Types:

1. Template
2. Chain of Responsibility
3. Mediator
4. State
5. Strategy
6. Iterator
7. Observer
8. Visitor
9. Memento
10. Interpreter
11. Command

# Strategy Design Pattern

Lets you define a family of algorithms, put each of them into a separate class and make their objects interchangeable.
Usage:<br/>

- java.util.Comparator#compare() called from Collections#sort(). The Collections.sort() method takes a comparator as a
  second argument to sort the elements.
- javax.servlet.http.HttpServlet: service() method, plus all of the doXXX() methods that accept HttpServletRequest and
  HttpServletResponse objects as arguments.
- javax.servlet.Filter#doFilter()

# Template Design Pattern

It defines the skeleton of the algorithm or may contain the default functionality of the algorithm in the superclass but
lets the subclass overrides specific steps without changing its structure.
Usages:<br/>

- All non-abstract methods of java.io.InputStream, java.io.OutputStream, java.io.Reader and java.io.Writer.
- All non-abstract methods of java.util.AbstractList, java.util.AbstractSet and java.util.AbstractMap.

# Mediator Design Pattern

This pattern helps us reduce chaotic dependencies between objects.
This pattern restricts direct communication between the objects and forces them to communicate via a mediator object.
This is different from facade in the way that facade doesn't add any new functionality.
Mediator centralizes communication between components of the system. The components only know about the mediator object
and don’t communicate directly.

Might be useful when we have circular dependency or tight coupling b/w objects.

> The synonym for mediator is the controller part of MVC.

Usages:<br/>

- java.util.Timer (all scheduleXXX() methods)
- java.util.concurrent.Executor#execute()
- java.util.concurrent.ExecutorService (invokeXXX() and submit() methods)
- java.util.concurrent.ScheduledExecutorService (all scheduleXXX() methods)
- java.lang.reflect.Method#invoke()

# Observer

Lets you define a subscription mechanism to notify multiple objects any event that happened to the event they are
observing. Like a weather monitoring system where the weather locations act as subjects and weather viewing app act as
observer and any change in weather in any location is notified to all observers and updated information is displayed.

# Chain of Responsibility

Lets you pass request along a chain of handlers. Upon receiving the request each handler decides whether to pass the
request to next handler or process it.

Spring boot filter chain is example of this.
In the project there was a Filter for authentication and one to save request in audit table.

# Command Pattern

Encapsulates request as an object, thereby allowing parameterization of clients with queues, requests, and operations.
This pattern decouples the sender of a request from its receiver

# Memento

Lets you save and restore the previous state of an object without revealing the details of its implementation.

# Iterator

Iterator is a behavioral design pattern that lets you traverse elements of a collection without exposing its underlying
representation.
Iterator is the example of this type of pattern.

# State

This pattern allows an object to change its behaviour when it's internal state changes. The states are represented by
separate objects.