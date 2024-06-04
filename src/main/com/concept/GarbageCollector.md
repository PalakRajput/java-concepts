Garbage Collection is the process of reclaiming the runtime unused memory by destroying the unused objects.<br/>

The garbage collectors traverse whole object graph in memory, starting from the garbage collection roots and following
reference from roots to other objects.<br/>

A standard garbage collection process involve 3 phases:

1. Mark objects as alive
   In this step, the GC identifies all the live objects(which are accessible) in memory by traversing the object graph.
2. Sweep dead objects
   This phase release the memory contained by dead objects.
3. Compact remaining objects in memory
   The dead objects might not be next to each other so in this phase the live objects are moved together and new objects
   can be assigned sequential memory.

# Generational Garbage Collection

Java Garbage Collectors implement a generational garbage collection strategy that categorizes objects by age.

Having to mark and compact all the objects in a JVM is inefficient. As more and more objects are allocated, the list of
objects grows, leading to longer garbage collection times.
The heap memory is divided into three sections:

1. Young generation
2. Old generation
3. PermGen/Metaspace

**Young Generation**
All the new objects starts here. It is further divided into:

*Eden space:* All new objects start here and the initial memory is allocated to them.

*Survivor space(FromSpace & ToSpace):* objects are moved from eden space after they survive 1 garbage collection cycle.

When objects are garbage collected from the Young Generation, it is a minor garbage collection event.

When Eden space is filled with objects, a Minor GC is performed. All the dead objects are deleted, and all the live
objects are moved to one of the survivor spaces. Minor GC also checks the objects in a survivor space, and moves them to
the other survivor space.

Take the following sequence as an example:

1. Eden has all objects (live and dead)
2. Minor GC occurs - all dead objects are removed from Eden. All live objects are moved to S1 (FromSpace). Eden and S2
   are now empty.
3. New objects are created and added to Eden. Some objects in Eden and S1 become dead.
4. Minor GC occurs - all dead objects are removed from Eden and S1. All live objects are moved to S2 (ToSpace). Eden and
   S1 are now empty.
5. So, at any time, one of the survivor spaces is always empty. When the surviving objects reach a certain threshold of
   moving around the survivor spaces, they are moved to the Old Generation.

> You can use the -Xmn flag to set the size of the Young Generation.

**Old Generation**

Objects that are long-lived are eventually moved from the Young Generation to the Old Generation. This is also known as
Tenured Generation, and contains objects that have remained in the survivor spaces for a long time.

There is a threshold defined for the tenure of an object which decides how many garbage collection cycles it can survive
before it is moved to the Old Generation.

When objects are garbage collected from the Old Generation, it is a major garbage collection event.

> You can use the -Xms and -Xmx flags to set the size of the initial and maximum size of the Heap memory.

Since Java uses generational garbage collection, the more garbage collection events an object survives, the further it
gets promoted in the heap. It starts in the young generation and eventually ends up in the tenured generation if it
survives long enough.

Consider the following example to understand the promotion of objects between spaces and generations:

When an object is created, it is first put into the Eden space of the young generation. Once a minor garbage collection
happens, the live objects from Eden are promoted to the FromSpace. When the next minor garbage collection happens, the
live objects from both Eden and FromSpace are moved to the ToSpace.

This cycle continues for a specific number of times. If the object is still used after this point, the next garbage
collection cycle will move it to the old generation space.

**Permanent Generation**
Metadata such as classes and methods are stored in the Permanent Generation. It is populated by the JVM at runtime based
on classes in use by the application. Classes that are no longer in use may be garbage collected from the Permanent
Generation.

> You can use the -XX:PermGen and -XX:MaxPermGen flags to set the initial and maximum size of the Permanent Generation.

**MetaSpace**

Starting with Java 8, the MetaSpace memory space replaces the PermGen space. The implementation differs from the PermGen
and this space of the heap is now automatically resized.

This avoids the problem of applications running out of memory due to the limited size of the PermGen space of the heap.
The Metaspace memory can be garbage collected and the classes that are no longer used can be automatically cleaned when
the Metaspace reaches its maximum size.

# Types of garbage collectors:

1. Serial GC: Stop the world, run in single thread.Since the entire application is frozen during garbage collection, it
   is not recommended in a real world scenario where low latencies are required.

2. Parallel GC: Multiple threads are used for minor garbage collection in the Young Generation. A single thread is used
   for major garbage collection in the Old Generation. Default implementation of GC in JVM for Java8. It also stops the
   world.

3. Parallel Old GC: Same as parallel GC just that the multiple threads are used for old generation also.

4. CMS (Concurrent Mark and Sweep) GC: Multiple threads are used for minor garbage collection using the same algorithm
   as Parallel. Major garbage collection is multi-threaded, like Parallel Old GC, but CMS runs concurrently alongside
   application processes to minimize “stop the world” events.

5. G1 (Garbage First) GC (Default in Java17): It divides the heap into further parts and concurrently collects the
   garbage objects. Marking of free objects is done concurrently for old generation but for young generations' marking
   and compacting is done by stopping all other threads and compacting of old generation is also done by stopping all
   the threads.