### Inheritance

Derived class extends the functionality of base class. Used for avoiding code duplication.

### Abstraction

Showing important details and hiding unnecessary information. Achieved using abstract classes and interfaces.
Providing interfaces for service class and implementing them to give implementation details in impl classes.

### Polymorphism

Many forms.<br/>
Overloading/static/compile-time polymorphism: Same method name but type and number of arguments can defer. <br/>
Overriding/dynamic/run-time polymorphism: Override the method in child class to provide other implementation or extend
functionality.

### Encapsulation

Binding of data and code together in a class.

### Coupling

Degree of direct knowledge one class has of another.
How the classes are dependent on each other. If classes are highly dependent on each other than we have tight coupling
otherwise if classes are not highly dependent on each other than we have loose coupling.
Loose coupling is desired.

### Cohesion

How closely related the responsibilities of a class or module are. High cohesion is desired.

### Association

It defines the relation b/w two classes. It can be one-to-many, many-to-one, one-to-one, many-to-many.

### Composition(Strong association)

The lifetime of the owned object is owned by the owner.

### Aggregation(Weak association)

HAS-A relation where the lifetime of the owned object doesn't depend on the owner.