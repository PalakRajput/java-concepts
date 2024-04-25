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

# Strategy Design Pattern

Lets you define a family of algorithms, put each of them into a separate class and make their objects interchangeable.
Usage:
java.util.Comparator#compare() called from Collections#sort(). The Collections.sort() method takes a comparator as a
second argument to sort the elements.
javax.servlet.http.HttpServlet: service() method, plus all of the doXXX() methods that accept HttpServletRequest and
HttpServletResponse objects as arguments.
javax.servlet.Filter#doFilter()

# Template Design Pattern

It defines the skeleton of the algorithm or may contain the default functionality of the algorithm in the superclass but
lets the subclass overrides specific steps without changing its structure.
Usages:
All non-abstract methods of java.io.InputStream, java.io.OutputStream, java.io.Reader and java.io.Writer.
All non-abstract methods of java.util.AbstractList, java.util.AbstractSet and java.util.AbstractMap.

# Mediator Design Pattern

This pattern helps us reduce chaotic dependencies between objects.
This pattern restricts direct communication between the objects and forces them to communicate via a mediator object.

> The synonym for mediator is the controller part of MVC.

Usages:
java.util.Timer (all scheduleXXX() methods)
java.util.concurrent.Executor#execute()
java.util.concurrent.ExecutorService (invokeXXX() and submit() methods)
java.util.concurrent.ScheduledExecutorService (all scheduleXXX() methods)
java.lang.reflect.Method#invoke()