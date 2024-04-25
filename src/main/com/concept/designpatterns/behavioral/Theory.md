## Behavioral Design Pattern

These design patterns are concerned with algorithms and the assignment of responsibilities withing the objects.

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


