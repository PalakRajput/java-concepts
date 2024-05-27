### Single Responsibility

A class should have only 1 reason or responsibility to change.

```java
// single responsibility principle - bad example

interface IEmail {
    public void setSender(String sender);

    public void setReceiver(String receiver);

    public void setContent(String content);
}

class Email implements IEmail {

    public void setSender(String sender) {
    }

    public void setReceiver(String receiver) {
    }

    public void setContent(String content) {
    }
}

// single responsibility principle - good example

interface IEmail {
    public void setSender(String sender);

    public void setReceiver(String receiver);

    public void setContent(IContent content);
}

interface IContent {
    public String getAsString(); // used for serialization
}

class Email implements IEmail {
    public void setSender(String sender) {
        // set sender; 
    }

    public void setReceiver(String receiver) {
        // set receiver;
    }

    public void setContent(IContent content) {
        // set content;
    }
}
```

### Open-Closed

The classes should be open for extension and closed for modification.

```java
// Open-Close Principle - Bad example
class GraphicEditor {

    public void drawShape(Shape s) {
        if (s.m_type == 1)
            drawRectangle(s);
        else if (s.m_type == 2)
            drawCircle(s);
    }

    public void drawCircle(Circle r) {
    }

    public void drawRectangle(Rectangle r) {
    }
}

class Shape {
    int m_type;
}

class Rectangle extends Shape {
    Rectangle() {
        super.m_type = 1;
    }
}

class Circle extends Shape {
    Circle() {
        super.m_type = 2;
    }
}

// Open-Close Principle - Good example
class GraphicEditor {
    public void drawShape(Shape s) {
        s.draw();
    }
}

class Shape {
    abstract void draw();
}

class Rectangle extends Shape {
    public void draw() {
        // draw the rectangle
    }
} 
```

### Liskov Substitution

Child classes should be substitutable for their parent classes.

```java
// Violation of Likov's Substitution Principle
class Rectangle {
    protected int m_width;
    protected int m_height;

    public void setWidth(int width) {
        m_width = width;
    }

    public void setHeight(int height) {
        m_height = height;
    }

    public int getWidth() {
        return m_width;
    }

    public int getHeight() {
        return m_height;
    }

    public int getArea() {
        return m_width * m_height;
    }
}

class Square extends Rectangle {
    public void setWidth(int width) {
        m_width = width;
        m_height = width; //Changed the behaviour of parent class
    }

    public void setHeight(int height) {
        m_width = height; //Changed the behaviour of parent class
        m_height = height;
    }

}

class LspTest {
    private static Rectangle getNewRectangle() {
        // it can be an object returned by some factory ... 
        return new Square();
    }

    public static void main(String args[]) {
        Rectangle r = LspTest.getNewRectangle();

        r.setWidth(5);
        r.setHeight(10);
        // user knows that r it's a rectangle. 
        // It assumes that he's able to set the width and height as for the base class

        System.out.println(r.getArea());
        // now he's surprised to see that the area is 100 instead of 50.
    }
}

class Shape {
    double area() {
    }
}

class Square extends Shape {
    int edge;

    void setEdge(int edge) {
        this.edge = edge;
    }

    int getEdge() {
        return this.edge;
    }

    @Override
    double area() {
        return edge * edge;
    }
}

class Rectangle extends Shape {
    protected int m_width;
    protected int m_height;

    public void setWidth(int width) {
        m_width = width;
    }

    public void setHeight(int height) {
        m_height = height;
    }

    public int getWidth() {
        return m_width;
    }

    public int getHeight() {
        return m_height;
    }

    @Override
    double area() {
        return m_width * m_height;
    }
}
```

### Interface Segregation

Classes should not be forced to implement methods they don't use. Their should not be fat interfaces, rather they should
be split into smaller interfaces.

```java
// interface segregation principle - bad example

interface IWorker {
    public void work();

    public void eat();
}

class Worker implements IWorker {

    public void work() {
        // ....working
    }

    public void eat() {
        // ...... eating in launch break
    }
}

class SuperWorker implements IWorker {
    public void work() {
        //.... working much more
    }

    public void eat() {
        //.... having the lunch break
    }
}

class Manager {

    IWorker worker;

    public void setWorker(IWorker w) {
        worker = w;
    }

    public void manage() {
        worker.work();
    }
}


// interface segregation principle - good example
interface IWorker extends IFeedable, IWorkable {
}

interface IWorkable {
    public void work();
}

interface IFeedable {
    public void eat();
}

class Worker implements IWorkable, IFeedable {
    public void work() {
        // ....working
    }

    public void eat() {
        //.... eating in launch break
    }
}

class Robot implements IWorkable {
    public void work() {
        // ....working
    }
}

class Manager {
    IWorkable worker;

    public void setWorker(IWorkable w) {
        worker = w;
    }

    public void manage() {
        worker.work();
    }
}
```

### Dependency Inversion

High level modules should not depend on low level modules. Both should depend on abstractions.
Abstractions should not depend on details, details should depend on abstractions.

```java
// Dependency Inversion Principle - Bad example
class Worker {

    public void work() {
        // ....working
    }
}

class Manager {

    Worker worker;

    public void setWorker(Worker w) {
        worker = w;
    }

    public void manage() {
        worker.work();
    }
}

class SuperWorker {
    public void work() {
        //.... working much more
    }
}

// Dependency Inversion Principle - Good example
interface IWorker {
    public void work();
}

class Worker implements IWorker {
    public void work() {
        // ....working
    }
}

class SuperWorker implements IWorker {
    public void work() {
        //.... working much more
    }
}

class Manager {
    IWorker worker;

    public void setWorker(IWorker w) {
        worker = w;
    }

    public void manage() {
        worker.work();
    }
}
```