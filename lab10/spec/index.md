---
layout: page
title: "Lab 10: Higher-Order Functions & Inheritance"
tags: [Lab, Java]
released: true
---

## Before You Begin

As usual, pull the skeleton code.

## Learning Goals

In this lab and tomorrows lab, we'll wrap up the Java-focused portion of the class. Today we will take a look at two fundamental parts of comparing objects, the `.equals()` method for equality, and the `Comparator` and `.compareTo()` method for ordering (you should already be familiar with `.equals()` but `Comparator` and `.compareTo()` will be new). Both techniques will be widely used in the remainder of the class. We will then discuss inheritance and its rules, followed by higher-order functions.

## Comparison

> Read Chapter 4.3 from **[Max Function][]** through **Comparables** to help
> motivate the problem we're solving and the tools we'll use along the way.
>
> Remember **casting** is a bit of special syntax where you can tell the compiler that a
> specific expression has a specific compile-time type. If the `maxDog` method
> below returns an object of static type `Dog`, the code normally wouldn't compile
> as `Poodle` is a subtype of `Dog`. *Casting* tells Java to treat the `Dog` as
> if it were a `Poodle` for the purposes of compilation because it's possible
> that the `Dog` returned from `maxDog` *could be* a `Poodle`.
>
> ```java
> Poodle largerPoodle = (Poodle) maxDog(frank, frankJr);
> ```

[Max Function]: https://joshhug.gitbooks.io/hug61b/content/chap4/chap43.html#max-function

While we haven't explicitly learned about sorting yet, the idea of sorting
should be intuitive enough. You have a list of things, and you want to put it
in sorted order. While this makes sense immediately for things like `int`s,
which we can compare with primitive operations like `<` and `==`, this becomes
less clear for general objects.

Take a look at the Java API for the [`Comparable<T>` interface][comparable
interface]. A lot of things implement `Comparable` in the Java standard
library, as comparing two of the same type of object is a very common idea.

[comparable interface]: https://docs.oracle.com/javase/10/docs/api/java/lang/Comparable.html

The only method required by `Comparable` is `compareTo(T o)` which takes
another object of the (same) type `T` and returns an `int` whose value
represents whether `this` or `o` should come first. In order to sort a list in
Java, most sorting algorithms will call `compareTo` and make pairwise
comparisons to determine which object should come first, repeatedly, and swap
elements until the entire list is sorted. (The hard part of sorting, then, is
to determine which `compareTo` 'questions' are necessary to ask!)

Here are a few key details from `compareTo`, slightly adapted:

> Compares this object with the specified object for order. Returns a negative
> integer, zero, or a positive integer if this object is less than, equal to,
> or greater than the specified object, respectively.

There are other requirements that usually just happen naturally with a
reasonable implementation, but are still important to specify:

> The implementor must also ensure that the relation is transitive:
> `(x.compareTo(y) > 0 && y.compareTo(z) > 0)` implies `x.compareTo(z) > 0`.
>
> It is strongly recommended, but not strictly required that `x.compareTo(y) ==
> 0` is equivalent to `x.equals(y)`. Generally speaking, any class that
> implements the `Comparable` interface and violates this condition should
> clearly indicate this fact. The recommended language is "Note: this class has
> a natural ordering that is inconsistent with equals."

Typically, a class will compare to objects of the same type as itself (although
it does not strictly have to). Doing so means data structures that require
ordering (like sorted lists, and in the future, search trees) can contain the
class.

## Exercise: `User`

In `User.java`, we've provided an example of how a website might model a user.

Make `User` implement `Comparable`. Use the generic parameter to `Comparable`
to ensure that `User` can only be used to compare against other `User`s.

The natural ordering for `User` is to compare by ID number. If their ID numbers
are the same, then compare them on their username.

After implementing this, you should be able to sort `User`s:

```java
public static void main(String[] args) {
    User[] users = {
        new User(2, "Matt", ""),
        new User(4, "Zoe", ""),
        new User(5, "Alex", ""),
        new User(1, "Shreya", ""),
        new User(1, "Connor", "")
    };
    Arrays.sort(users);
    for (User user : users) {
        System.out.println(user);
    }
}
```

```java
User{id=1, name=Connor, email=}
User{id=1, name=Shreya, email=}
User{id=2, name=Matt, email=}
User{id=4, name=Zoe, email=}
User{id=5, name=Alex, email=}
```

Note that here we use `Arrays.sort` because `users` is an array; if it was a
Java `Collection` like `ArrayList`, we would use `Collections.sort`.

## Discussion: Inheritance and Overriding

> Read from the beginning of **[Chapter 4.2][]** through (and including)
> **Casting**.
>
> There are lots of tricky concepts in this chapter; the best way to learn them
> is by applying them through practice and remember that Java follows a very
> strict and precise set of rules.
>
> Note much of this will be a review from material that we covered in lecture 2. If you feel confident on the material you should be able to skip this reading, but we recommend at least skimming again as a refresher. 

[Chapter 4.2]: https://joshhug.gitbooks.io/hug61b/content/chap4/chap42.html

Between you and your partner, come up with a definition and procedure in your
own words for each of the following terms.

- Compile-time type (also known as *static type*)
- Runtime type (also known as *dynamic type*)
- Superclass
- Subclass
- Extends
- Overriding and dynamic method selection

## Discussion: Cats and Dogs

Discuss the first problems from the **[Spring 2019 Exam Prep 4
worksheet][]** and check your answers against the [solution][Spring 2019 Exam
Prep 4 solutions].

Make sure that both partners understand why each answer is what it is. A good
question to really check your understanding is to ask, "What if we removed this
method?" or, "What if we added another overloaded method?"

[Spring 2019 Exam Prep 4 worksheet]: https://sp19.datastructur.es/materials/discussion/examprep04.pdf
[Spring 2019 Exam Prep 4 solutions]: https://sp19.datastructur.es/materials/discussion/examprep04sol.pdf

Refer to the *Inheritance Rules* below if you're not sure how to handle a
particular problem. These formal procedures are helpful to reference when
working through some of the trickier situations that can happen when designing
with inheritance.

## Recap: Inheritance Rules

All fields (class *and* instance variables) and static methods (class methods)
are looked up against the static type of the variable or expression.

Overloading
: Overloading occurs when there are multiple methods within a class with the
same name, but different parameters. Note that, "In a subclass, you can
overload the methods inherited from the superclass. Such overloaded methods
neither hide nor override the superclass instance methods---they are new
methods, unique to the subclass."

Dynamic Method Selection
: "An instance method in a subclass with the same signature (name, plus the
number and the type of its parameters) and return type as an instance method in
the superclass overrides the superclass's method."

    Note that selecting methods by the dynamic type of the object only applies
to **non-static, instance methods**! All other members, including *static
methods, and both static and non-static variables*, are looked up against the
*static type* of the object only: no overriding occurs for these members!

    At **compile-time**, the compiler, using the *static type*, creates a link
to the exact matching **method signature**, which includes the *name, number
and type of parameters (as they appear exactly), and return type* of the
method. This information is saved in the `.class` file generated by `javac`.

    Then, at **runtime**, if the method is an instance method, then override it
with the method with a matching signature that is closest to the dynamic type
of the object. An overriding method can also return a subtype of the type
returned by the overridden method.

Casting
: A type cast *temporarily* changes the *static type* of a variable, and tells
the compiler to trust us, the programmer, that it will work.

  So upcasting is always okay.

  ```java
  Dog d = new Dog();
  Animal a1 = (Animal) d; // this is okay
  Animal a2 = d; // same thing
  ```

  A cast fails at compile-time if the compiler knows, based on the static
type of the variable being casted, that it cannot possibly succeed at runtime:

  ```java
  Tree tree = new Tree();
  Animal a3 = (Animal) tree; // compile-time error
  ```

  Since no `Tree` is ever an `Animal`, the compiler knows this cast cannot
possibly succeed. So we get a compile-time error: `incompatible types: Tree
cannot be converted to Animal`.

  Here's a downcast:

  ```java
  Animal a4 = new Dog();
  Dog dd = (Dog) a4; // no error
  ```

  `a4` has dynamic type `Dog`, and `dd` is a reference to a `Dog`, so it's
okay for `dd` to refer to `a4`.

  But now consider:

  ```java
  Animal a5 = new Animal();
  Dog ddd = (Dog) a5; // runtime error
  ```

  At compile-time, the reasoning is, "`a5` has static type `Animal`.  So `a5`
could have dynamic type `Dog`, in which case, the cast will succeed at runtime.
Since it's **possible** that the cast will succeed, we will allow this to
compile." (Notice that the compiler does not know that `a5` has dynamic type
`Animal`.)

  But then here's what happens at runtime: `a5` has dynamic type `Animal`,
and `ddd` is a reference to a `Dog`. It's not okay for a reference to a `Dog`
to refer to an `Animal`. (Why not?) So we get a runtime error:
`ClassCastException: Animal cannot be cast to Dog`.

  *This example is thanks to Allen Guo.*

> Nicole Rasquinha, a TA from spring 2018, created a [video playlist][]
> reviewing and applying this material if you prefer a video presentation of
> the material. Since these videos are on the long side, it's best to watch
> them at home. You might find them helpful when studying later.
>
> - **[Video 1 Notes on Static and Dynamic Types][]**
> - **[Video 2 and 3 Notes on Overloading and Overriding][]**
>
> The summary video provides a good overview of the compiler's role in dynamic
> method selection.
>
> Summary
> : - The compiler is a cautious proofreader
>   - Compiler only knows static type
>
> Method Procedure
> : 1. During compile-time, look in static type for method
>   2. Write down exact method signature including name *and parameters*
>   3. During runtime, only override if method signature is the same *including
>      parameters*

[video playlist]: https://www.youtube.com/playlist?list=PLEdR-EJ6X7NrMIEgC1chCWUUvmU0j5ftZ
[Video 1 Notes on Static and Dynamic Types]: {{ site.baseurl }}/assets/pdf/Nicole-Notes-Static-Dynamic-Types.pdf
[Video 2 and 3 Notes on Overloading and Overriding]: {{ site.baseurl }}/assets/pdf/Nicole-Notes-Overloading-Overriding.pdf

Some of the excerpts above were quoted from the Java Tutorials on [Overriding
and Hiding Methods][]. Note that this tutorial covers material such as static
method hiding which you don't need to know about.

[Overriding and Hiding Methods]: https://docs.oracle.com/javase/tutorial/java/IandI/override.html

## Higher-Order Functions

> Then, read about **[Higher-Order Functions][]**, the topic we'll be exploring
> and building upon for the rest of lab.

[Higher-Order Functions]: https://joshhug.gitbooks.io/hug61b/content/chap4/chap42.html#higher-order-functions

A **higher-order function** is a function that treats other functions as data.
In the textbook, we saw that we could create higher-order functions with the
help of inheritance by defining additional classes to represent each operation.

```java
public interface IntUnaryFunction {
    int apply(int x);
}
```

```java
public class TenX implements IntUnaryFunction {
    /* Returns ten times the argument. */
    public int apply(int x) {
        return 10 * x;
    }
}
```

By defining the `IntUnaryFunction` interface, the Java compiler is able to type
check and ensure that all parts of the program are safe to run.

There are some significant downsides to this design. For every function you
want to use, you have to define a new class, along with writing all
super-interfaces for the whole hierarchy. One shortcut you can take is defining
[anonymous classes][], a common pattern used in programming for the Android OS,
but one we will not explore in this course. Instead, we'll take a look at a few
other recent Java features like lambdas, which reduce the amount of code we
need to write by having the compiler do as much as it can to infer the
relationship between `TenX` and `IntUnaryFunction`.

[anonymous classes]: https://docs.oracle.com/javase/tutorial/java/javaOO/anonymousclasses.html

### Comparators

A `Comparator<T>` is an example of a Java higher-order function for imposing
ordering on objects (if they have no natural ordering), and/or overriding their
natural ordering as specified by `Comparable`.

> Read **[Comparator][]** in Chapter 4.3 of the online textbook.

[Comparator]: https://joshhug.gitbooks.io/hug61b/content/chap4/chap43.html#comparator

`Comparator` only requires one method to be implemented: `compare(T o1, T o2)`,
which "Returns a negative integer, zero, or a positive integer as the first
argument is less than, equal to, or greater than the second." It should behave
transitively & symmetrically, and returning zero should also imply that
`o1.equals(o2)`.

Then, we can pass in the `Comparator` object as the second argument to
`Arrays.sort` (the same works for `Collections.sort`) and sort on whatever we
want. For example, if we wanted to sort integers in reverse order, we can
create a `ReverseComparator` and reverse the result usual `Integer` comparison.

```java
public class ReverseComparator implements Comparator<Integer> {

    public Integer compare(Integer o1, Integer o2) {
        return -(Integer.compare(o1, o2));
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        Arrays.sort(arr, new ReverseComparator());
        // The above line sorts based on ReverseComparator's compare method
        System.out.println(Arrays.toString(arr)); // [3, 2, 1]
    }
}
```

> You may notice this is very similar to the `Comparable<T>` interface from before. There are a couple of notable differences:
> - `Comparable`'s `compareTo()` is called from a `T` object whereas `Comparator`'s `compare` is called from a `Comparator` instance. You can think of a `Comparable` like "I am comparing myself to some other object" and a `Comparator` like "I am some third party comparing two objects".
> - `Comparable<T>` / `compareTo()` define the natural ordering for a particular object. A `Comparator<T>` can compare `T` objects with other orderings that may be different from the natural ordering. 
> - You will only have one `compareTo` defined for a particular object, but could have any number of `Comparators`. 

### `Function` Interface

The Java standard library now includes a `Function` interface, a standard
interface for any programmer to pass around function objects. Instead of having
to use our own made-up interface, we can implement interfaces from Java's
universal `Function` hierarchy.

Now, when it comes to writing things like Comparators, it means we no longer
have to write a custom class. This is because a `Comparator` is a [*functional
interface*](https://www.geeksforgeeks.org/functional-interfaces-java/) (that is, it only has one abstract method that must be implemented)
and can be replaced by any appropriate lambda function or method reference.

Now we can use `Arrays.sort` like a true higher order function. Let's revisit
the example of sorting integers in reverse order.

Here are two ways to do it.

Using a [method reference][]
:   ```java
    public class IntSorter {
        public static int reverseCompare(Integer i1, Integer i2) {
            return -(Integer.compare(i1, i2));
        }

        public static void main(String[] args) {
            int[] arr = {1, 2, 3};
            Arrays.sort(arr, IntSorter::reverseCompare);
            System.out.println(Arrays.toString(arr)); // [3, 2, 1]
        }
    }
    ```

    The `::` notation denotes the `reverseCompare` method of the `IntSorter`
class. You can also do this for instance methods (without associating it with
an object) or with the instance method of a particular object (like
`instance::method`).  This is the equivalent of passing the method around by
reference.

    Note some of the differences between this and the previous `Comparator`
example. Here, we pass in a method reference. `IntSorter` does not implement
the `Comparator` interface, and we never make a new instance of `IntSorter`

[method reference]: https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html

Using a [lambda expression][]
:   ```java
    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3};
        Arrays.sort(arr, (o1, o2) -> -(Integer.compare(o1, o2)));
        System.out.println(Arrays.toString(arr)); // [3, 2, 1]
    }
    ```

    Notice the syntax here. First, the arguments to the method are declared,
then an arrow, followed by the return value. The typed parameters are not
provided, and the compiler will infer them automatically by their use. Of
course, longer and more specific lambda statements are possible, using multiple
statements and return statements, and using typed parameters, like so:

    ```java
    Arrays.sort(arr, (Integer o1, Integer o2) -> {
                System.out.println("Do more stuff here");
                return -(Integer.compare(o1, o2));
            });
    ```

[lambda expression]: https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html

## Exercise: `getOrderedBy`

Take a look at `DBTable.java`. It is a simple thin wrapper around a `List<T>`.
You will be adding functionality for a few common operations on a list of
things like what we find in databases. In our example, `T` will always be a
User, but your code should generalize to any type.

Implement `getOrderedBy`.

```java
public <R extends Comparable<R>> List<T> getOrderedBy(Function<T, R> getter)
```

> The `<R extends Comparable<R>>` is a *method generic*, which declares a
> generic type local to a single method (rather than the entire class), and can
> be used throughout the method similar to generics in classes. The method
> generic goes after the visibility keyword, `public`, and before the return
> type declaration, `List<T>`. Additionally, there is a *bound* on the generic
> type `R`, stating that only types that extend `Comparable<R>` will be
> accepted by the compiler.

`getOrderedBy` should take in a method reference to a *getter* method of the
`T` class, and return a new `List<T>`, ordered on the return value of that
getter method. Example usage:

```java
public static void main(String[] args) {
    List<User> users = Arrays.asList(
            new User(2, "Christine", ""),
            new User(4, "Kevin", ""),
            new User(5, "Alex", ""),
            new User(1, "Lauren", ""),
            new User(1, "Catherine", "")
            );
    DBTable<User> t = new DBTable<>(users);
    List<User> l = t.getOrderedBy(User::getName);
    l.forEach(System.out::println);
}
```

```
User{id=5, name=Alex, email=}
User{id=1, name=Catherine, email=}
User{id=2, name=Christine, email=}
User{id=4, name=Kevin, email=}
User{id=1, name=Lauren, email=}
```

Like before, the `Function` interface only requires a single method be
implemented, `R apply(T t)`, which represents the behavior of the Function
itself. Functions also have other methods, which are marked `default`, that can
do other cool features, like composing functions with the `compose` method,
similar to currying in CS 61A, and chaining functions through `andThen`.

Implement `getOrderedBy` according to specification. You will need to make
usage of a custom comparator, without writing `Comparator` once.

*Hint*: Use a lambda statement. Do not mutate DBTable's `entries`.

## Recap

A `max` Function
: Suppose we want to write a function `max()` that returns the max of any array
regardless of type. If we write a method `max(Object[] items)`, where we use
the '>' operator to compare each element in the array, this will not work! Why
is this the case?

  Well, this makes the assumption that all objects can be compared. But some
objects cannot! Alternatively, we could write a `max()` function inside the Dog
class, but now we have to write a `max()` function for each class that we want
to compare! Remember, our goal is to write a "one true max method" that works
for all comparable objects.

`OurComparable` Interface
: The solution is to create an interface that contains a `compareTo(Object)`
method; let's call this interface `OurComparable`. Now, our `max()` method can
take a `OurComparable[]` parameter, and since we guarantee that any object
which extends the interface has all the methods inside the interface, we
guarantee that we will always be able to call a `compareTo` method, and that
this method will correctly return some ordering of the objects.

  Now, we can specify a "one true max method". Of course, any object that needs
to be compared must implement the `compareTo` method. However, instead of
re-implementing the `max` logic in every class, we only need to implement the
logic for picking the ordering of the objects, given two objects.

`Comparable` Interface
: Java has an in-built `Comparable` interface that uses generics to avoid any
weird casting issues. Plus, `Comparable` already works for things like
`Integer`, `Character`, and `String`; moreover, these objects have already
implemented a `max`, `min`, etc. method for you. Thus you do not need to re-do
work that's already been done!

Overloading
: In Java, methods in a class can have the same name, but different parameters.
For example, a `Math` class can have an `add(int a, int b)` method and an
`add(float a, float b)` method as well. The Java compiler is smart enough to
choose the correct method depending on the parameters that you pass in.
Methods with the same name but different parameters are said to be overloaded.

Overriding
: For each method in `AList` that we also defined in `List`, we will add an
@Override right above the method signature. As an example:

  ```java
  @Override
  public Item get(int i) { ... }
  ```

  This is not necessary, but is good style and thus we will require it. Also,
it allows us to check against typos. If we mistype our method name, the
compiler will prevent our compilation if we have the @Override tag.

Static vs. Dynamic Type
: Every variable in Java has a static type. This is the type specified when the
variable is declared, and is checked at compile-time. Every variable also has a
dynamic type; this type is specified when the variable is instantiated, and is
checked at runtime. As an example:

  ```java
  Thing a;
  a = new Fox();
  ```

  Here, `Thing` is the static type, and `Fox` is the dynamic type. This is
fine because all foxes are things. We can also do:

  ```java
  Animal b = a;
  ```

  This is fine, because all foxes are animals too. We can do:

  ```java
  Fox c = b;
  ```

  This is fine, because `b` points to a `Fox`. Finally, we can do:

  ```java
  a = new Squid()
  ```

  This is fine, because the static type of `a` is a `Thing`, and `Squid` is a
thing.

Dynamic Method Selection
: The rule is, if we have a static type `X`, and a dynamic type `Y`, then if
`Y` overrides the method from `X`, then on runtime, we use the method in `Y`
instead. Student often confuse overloading and overriding.

Overloading and Dynamic Method Selection
: Dynamic method selection plays no role when it comes to overloaded methods.
Consider the following piece of code, where `Fox extends Animal`.

  ```java
  Fox f = new Fox();
  Animal a = f;
  define(f);
  define(a);
  ```

  Let's assume we have the following overloaded methods in the same class:

  ```java
  public static void define(Fox f) { ... }
  public static void define(Animal a) { ... }
  ```

  Line 3 will execute `define(Fox f)`, while line 4 will execute `define(Animal
a)`. Dynamic method selection only applies when we have overridden methods.
There is no overriding here, and therefore dynamic method selection does not
apply.

`Comparator` Interface
: The term "Natural Order" is used to refer to the ordering implied by a
`Comparable`'s `compareTo` method. However, what if we want to order our `Dog`
objects by something other than `size`? We will instead pass in a
`Comparator<T>` interface, which demands a `compare()` method. We can then
implement the `compare()` method anyway we want to achieve our ordering.

### Deliverables

Here's a quick recap of what you need to do to complete this lab!

- Understand how `compareTo` relates to higher order functions.
- Understand how method overloading and overriding work with inheritance
  relationships.
- Make the `User` class implement `Comparable`.
- Implement `getOrderedBy` in `DBTable.java`.

You will need to complete the two exercises:

- In `User.java` you should have modified the file to implement the `Comparable` interface.
- In `DBTable.java` you should have implemented the `getOrderedBy` function using lambda statement.

If you have any time remaining in your lab, we recommend that you stick around and work on Gitlet with your partner! If your TA is available this is a great time to ask questions.