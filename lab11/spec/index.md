---
layout: page
title: "Lab 11: Exceptions, Iteration, & Streams"
tags: [Lab, Exceptions, Iteration, Streams]
released: true
---

## Before You Begin

Pull the files from GitHub and create a new IntelliJ project out of them.

## Learning Goals

In this lab, we will finish up the last of our Java concepts by exploring the concept of **delegation**
and different methods for how we can delegate work to other classes and methods.
For our purposes, to *delegate* work means to pass it off to someone or something else.

We'll first consider what happens when an error occurs, like an
`ArrayIndexOutOfBoundsException`. Instead of crashing immediately and printing
out a long stack trace, we'll learn how we can hand over control of the program
to the method which called us, allowing the programmer to handle the crash and
take alternate action. The mechanism that enables this ability is called an
**exception**.

An exception can be **thrown**---passed to another part of the program to
handle---or **caught**---handled on its own. We'll consider examples of each
kind of use in the upcoming lab exercises.

Then, we'll see how to build **iterators**, objects that
control iteration through the items in a collection via three methods: a
constructor, `hasNext` ("Are there any more items left?"), and `next` ("Return
the next item."). *Iterators* and *iterables* are a core component of the Java
standard library, and we'll see how iterators, as a form of delegation, can be
used to write better and more performant code.

Finally, we'll see how delegation can be applied to higher-order functions
through Java **streams**. Streams are the most abstract way to represent
ordered sequences in Java (like lists) and allow for the greatest potential
optimizations.

## Error-Handling

So far in this course, we have not dealt much with error-handling. You were
allowed to assume that the arguments given to methods were formatted or
structured appropriately. However, this is not always the case due to program
bugs and incorrect user input. Here are a few examples of this:

1. Bugs in your programs might create inconsistent structures or erroneous
   method calls (e.g. division by zero, indexing out of bounds, dereferencing a
   null pointer).
2. Users cannot be trusted to give valid input (e.g. non-numeric input where a
   number is required or search failures where a command or keyword was
   misspelled).

We assume in the following discussion that we can detect the occurrence of an
error and at least print an error message about it.

A big challenge in dealing with the error is to provide information about it at
the right level of detail. For instance, consider the error of running off the
end of an array or list. If the contents of a list are inconsistent with the
number of elements supposedly contained in the list, a "reference through a
null pointer" or "index out of bounds" error may result. The programmer may
wish to pass information about the error back to the calling method with the
hope that the caller can provide more appropriate and useful information about
the root cause of the error and perhaps be able to deal with the error.

## Discussion: Error Handling

Here are three approaches to error handling:

- Don't try to pass back any information to the caller at all. Just print an
  error message and halt the program.
- Detect the error and set some global error indicator (like a `public static`
variable in Java) to indicate its cause.
- Require the method where the error occurred (and every method that
  directly or indirectly calls it) to pass back its value or to take an extra
argument object that can be set to indicate the error.

Different languages geared towards solving different types of problems take
different approaches to error handling. The increasingly popular [Go
programming language][], for example, chooses a design similar to the third
option.

[Go programming language]: https://golang.org/

Which seems most reasonable? Discuss with your partner, and defend your answer.
If none, justify why all the options are bad.

## Exceptions

There is a fourth option for handling errors, called an *exception*. Provided
by Java and other modern programming languages, an exception signals that an
error of some sort has occurred. Java allows both the *signaling* of an error
and selective *handling* of the error. Methods called between the signaling
method and the handling method need not be aware of the possibility of the
error.

An exception is *thrown* by the code that detects the exceptional situation,
and it is *caught* by the code that handles the problem. These two definitions are
helpful in discussing exceptions.

> Read Chapter **[6.1][]** and **[6.2][]** of the online textbook to learn more
> about exceptions.

[6.1]: https://joshhug.gitbooks.io/hug61b/content/chap6/chap61.html
[6.2]: https://joshhug.gitbooks.io/hug61b/content/chap6/chap62.html

An extension to the `try catch` block construct that often comes in handy is
the `finally` block. A `finally` block comes after the last `catch` block and
is used to do any cleanup that might be necessary, such as releasing resources
the `try` block was using. This is very common when working with input-output
like opening files on your computer.

```java
Scanner scanner = new Scanner(System.in);
int k;
try {
    k = scanner.nextInt();
} catch (NoSuchElementException e) {
    // Ran out of input
} catch (InputMismatchException e) {
    // Token isn't an integer
} finally {
    // finally will be executed as long as JVM does not exit early
    scanner.close();
}
```

This use of the `finally` block so common that the Java language developers
introduced the `try-with-resources` block. It allows you to declare resources
being used as part of the try block, and automatically release those resources
after the block finishes executing. The code below is equivalent to the snippet
above, but it doesn't use the `finally` block.

```java
int k;
try (Scanner scanner = new Scanner(System.in)) {
    k = scanner.nextInt();
} catch (NoSuchElementException e) {
    // ran out of input
} catch (InputMismatchException e) {
    // token isn't an integer
}
```

## Iteration

In CS 61BL, we're going to encounter a variety of different *data structures*,
or ways to organize data. We've implemented linked lists like `DLList` and
resizing array-based lists like `AList`. Starting Thursday,  we'll see more complicated data
structures such as trees, hash tables, heaps, and graphs.

A common operation on a data structure is to process every item. But often, the
code we need to write to setup and iterate through a data structure differs
depending on the data structure's implementation.

```java
for (int i = 0; i < array.length; i += 1) {
    // Do something with array[i]
}
```

For `SLList`, the pattern significantly differs from above.

```java
SLList list = ...
for (IntNode p = list.sentinel.next; p != null; p = p.next) {
    int item = p.item;
}
```

Evidently, we need to write two very different codes in order to do the exactly
same thing. It would be nice if we can write one piece of code that we can
reuse for different data structures. In other words, we wish to *abstract away*
the internal implementation of data structures from the operations on them.

Furthermore, if we use a different data structure, a `for` loop like the one
above may not make sense. For example, what would it mean to access the `k`th
item of a set, where order of items is not defined? We need a more *abstract*
notion of processing every item in a data structure, something that allows us
to check every item regardless of how they're organized. To do that, we're
going to use something called an *iterator*.

### Enhanced `for` Loop

It turns out that we've been using *iterators* all semester long! When Java
executes an enhanced `for` loop, it does a bit of work to convert it into
iterators and iterables. The following code represents the enhanced for loop
you have most likely already seen and then a translated version which reveals
what is happening behind the hood using an iterator.

```java
List<Integer> friends = new ArrayList<>();
friends.add(5);
friends.add(23);
friends.add(42);
for (int x : friends) {
    System.out.println(x);
}
```

```java
List<Integer> friends = new ArrayList<>();
friends.add(5);
friends.add(23);
friends.add(42);

Iterator<Integer> seer = friends.iterator();
while (seer.hasNext()) {
    int x = seer.next();
    System.out.println(x);
}
```

### `Iterable`

How does this work? `List` is an interface which extends the `Iterable`
interface. An **iterable** is something which can be iterated over, so all
`Collection`s implement `Iterable`.

```java
public interface Iterable<T> {
    Iterator<T> iterator();
}
```

The `iterator()` method initializes an **iterator** by returning an object of
type `Iterator`, which can then be used to iterate.

Your next questions is probably what is an `Iterator`? It turns out this is another interface!

### `Iterator`

```java
package java.util;

public interface Iterator<T> {
    boolean hasNext();
    T next();
    default void remove() { // implementation not shown }
    default void forEachRemaining(Consumer<? super E> action) { // implementation not shown}

}
```

`hasNext`
: `hasNext` is a boolean method that says whether there are any more remaining
items in the data structure to return. In other words, returns true if `next()`
would return an element rather than throwing an exception.

`next`
: `next` successively returns items in the data structure one by one. The first
call to `next` returns the first value, the second call to `next` returns the
second value, and so on. If you're iterating over a set---a data structure that
is not supposed to have an order---we might not necessarily guarantee that
`next` returns items in any specific order. However, what we do need to
guarantee is that it returns each item exactly once. If we were iterating over
a data structure that *does* have an ordering, like a list, then we would also
like to guarantee that `next` returns items in the right order.

> It's worth noting that every call to `next()` is typically preceded by a call
> to `hasNext()`, thus ensuring that the `Iterator` does indeed have a next value 
> to return. If there are no more elements to remaining, it is common practice to
> throw a `NoSuchElementException`.

`remove`
: Because this is declared as a `default` method in the interface, it need not
be overridden and as such it is rarely implemented in this class and elsewhere.
The default implementation throws an `UnsupportedOperationException` and performs
no other action, thus this is an optional operation. If implemented the method 
removes from the underlying collection the last element returned by this iterator.
This method can be called only once per call to `next()`. The behavior of an 
`Iterator` is unspecified if the underlying collection is modified while the iteration
is in progress in any way other than by calling this method.

`forEachRemaining`
: Also declared as `default` and thus does not need to be implemented. This will
never be overridden for `Iterator`s you will be asked to make for this class.
Performs the given action for each remaining element until all elements have been
processed or the action throws an exception. Actions are performed in the order
of iteration, if that order is specified. Exceptions thrown by the action are 
relayed to the caller.

*Why design two separate interfaces, one for iterator and one for iterable? Why
not just have the iterable do both?*

The idea is similar to `Comparable` and `Comparator`. By separating the design,
we can provide a 'default' iterator, but also allow for programmers to
implement different iterators with different behaviors. While the `AList` class
might, by default, provide an efficient `AListIterator` implementation, this
design opens up the possibility of defining an `AListReverseIterator` that can
iterate over an `AList` in reverse, all without modifying any of the code in
`AList`!

## `AListIterator`

Here's an example of inserting the iterator methods into the `AList`
class from the online textbook. Don't worry if you don't get it all right away or
the generics.

```java
import java.util.Iterator;

public class AList<Item> implements Iterable<Item> {

    private Item[] values;
    private int size;

    // AList constructors and methods would normally appear here.

    public Iterator<Item> iterator() {
        return new AListIterator();
    }

    private class AListIterator implements Iterator<Item> {

        private int bookmark = 0;

        public Item next() {
            Item toReturn = values[bookmark];
            bookmark += 1;
            return toReturn;
        }

        public boolean hasNext() {
            return bookmark < size;
        }
    }
}
```

> The code maintains an important invariant: prior to any call to `next`,
> `bookmark` contains the index of the next value in the list to return.
>
> Notice also that we don't need a generic type in the `AListIterator` class
> declaration. Since it's a non-static nested class (called an *inner class*),
> the `AList` generic type still applies. The implication of `AListIterator`
> being non-static means that an `AListIterator` cannot be created without an
> existing `AList` instance: the `AListIterator` needs to know its outer
> `AList`'s generic type `Item` in order to function correctly.

We can then use our `AList` class exactly like in the earlier examples, and
even in an enhanced `for` loop.

```java
AList<Integer> friends = new AList<>();
friends.add(5);
friends.add(23);
friends.add(42);

Iterator<Integer> seer = friends.iterator();
while (seer.hasNext()) {
    int x = seer.next();
    System.out.println(x);
}
```

```java
AList<Integer> friends = new AList<>();
friends.add(5);
friends.add(23);
friends.add(42);
for (int x : friends) {
    System.out.println(x);
}
```

## Defining Iterators

Often, when writing our own iterators, we'll follow a similar pattern of doing
most of the work in `next`.

1. We save the next item with `Item toReturn = values[bookmark]`.
2. Move the bookmark to the next item with `bookmark += 1`.
3. Return the item we saved earlier.

An important feature of the code is that `hasNext` **doesn't change any
state**. It only examines existing state by comparing the progress of the
iteration to the number of list elements. `hasNext` can then be called twice in
a row and nothing should change, or it could be called not at all and the
iteration should still work as long as there are elements left to be returned.

## Discussion: Iterator Invariants

Consider the following `AListIterator`, slightly different from those we just
encountered.

```java
private class AListIterator implements Iterator<Item> {

    private int bookmark = -1;

    public Item next() {
        bookmark += 1;
        return values[bookmark];
    }

    public boolean hasNext() {
        return (bookmark + 1) < size;
    }
}
```

Now, discuss the following questions with your partner:
1. What's the invariant relation that's true between calls to `next`?
2. In general, most experienced programmers prefer the organization introduced
   first over this organization. What might explain this preference?

Finally discuss these next two questions and answers with your partner. These 
questions deal with the order in which methods are called and it is important to 
understand this before using an `Iterator`.

What if someone calls `next` when `hasNext` returns false?
: This violates the iterator contract so the behavior for `next` is undefined.
Crashing the program is acceptable. However, a common convention is to throw a
`NoSuchElementException`.

Will `hasNext` always be called before `next`?
: Not necessarily. This is sometimes the case when someone using the iterator
knows exactly how many elements are in the sequence. For this reason, we can't
depend on the user calling `hasNext` when implementing `next`.

## Exercise: `DLListIterator`

As mentioned before, it is standard practice to use a separate iterator object
(and therefore a separate, typically nested class) as the actual `Iterator`.
This separates the `Iterator` from the underlying data structure or *iterable*.

Modify the provided `DLList` class so that it implements `Iterable<Item>`. Then,
add a nested `DLListIterator` class which implements `Iterator<Item>`. Note that if
you submit to the autograder before you implement this, your code likely will say
that there are compilation errors coming from the autograder tests (you will see
errors like "error: cannot find symbol" for calls to `a.iterator` or similar). Once
you have properly completed this, the errors should go away.

> Note that `DLList` itself does not implement `Iterator`. This is why we need
> a separate, nested, private class to be the iterator. Typically, this class
> is nested inside the data structure class itself so that it can access the
> internals of the object that instantiated the instance of the nested class.
>
> Make sure that you've completed the following checklist.
>
> 1. Does your `DLList` object know anything about its `DLListIterator`'s
>    state? Information about iteration (index of the next item to return)
>    should be confined to `Iterator` alone.
> 2. Are multiple `Iterator`s for the same `DLList` object independent of each
>    other? There can be multiple `Iterator`s for a single `DLList` object, and
>    one iterator's operation should not affect the state of another.
> 3. Does `hasNext` alter the state of your `Iterator`? It should not change
>    state.

After you have modified your `DLList` class, write some test code to see if
Java's enhanced `for` loop works as expected on your `DLList`.

## Discussion: Concurrent Modification

For our lab, we regarded our data structure to be "frozen," while the
`Iterator` was at work. In other words, we assumed that while we were operating
on the iterators, the data structure would remain as is. However, this is not
generally true in the real world.

```java
Iterator<BankAccount> it = accounts.iterator();
while (it.hasNext()) {
    // Remove the next account!
    checkValidity(it.next()); // This code breaks since the account was removed.
}
```

If all clients of the data structure were to only read, there would be no
problem. However, if any were to modify the data structure while others are
reading, this could break the fundamental invariant that `next` returns the
next item if `hasNext` returns true!

To handle such situations, many Java iterators throw
`ConcurrentModificationException` if they detect that the data structure has
been externally modified during the iterator's lifetime. This is called a
"fail-fast" behavior.

```java
List<String> friends = new ArrayList<>();
friends.add("Nyan");
friends.add("Meow");
friends.add("Nyaahh");
for (String friend : friends) {
    friends.remove(1);
    System.out.println("friend=" + friend);
}
```

Discuss with your partner about how to implement this "fail-fast" property for
a data structure. How can we propagate the modification to all existing
iterators?

If you want to see the Java standard library solution, run the above code, see
the error message and click on the link in the stack trace `ArrayList.java:1042` 
(note the line number might be slightly different depending on your Java version)
to see where the fail-fast happened. You can even see how Java implements their
nested `Iterator`.

```
Exception in thread "main" java.util.ConcurrentModificationException
    at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1042)
```

## Delegation

The key advantage of using iterators over, say, the old fashioned way of
getting items from a list lies in *programming cost* and *execution cost*.

Programming cost
: How long it takes to develop, modify, and maintain code.

Execution cost
: How long or how much space it takes to execute code on a computer.

Iterators are a powerful abstraction that allow us to reduce both programming
cost and execution cost.

Let's compare and contrast all the different ways to iterate over all the
elements in an `SLList`.

Direct pointer manipulation
: While this has a good execution cost as it runs in $$\Theta(N)$$ time, it
increases programming cost as the developer needs to know exactly how linked
lists work, not to mention it's a violation of abstraction barriers! Forgetting
to include `p = p.next` is a very easy way to get stuck in an infinite loop.
Moreover, the programmer need to know the exact details of the implementation
like the fact that `SLList` uses a single sentinel node and is null-terminated,
while `DLList` uses a single, circular sentinel node structure. As our data
structures and our problems get increasingly complex, it will get harder and
harder to maintain programs written in this way.

  ```java
  SLList list = SLList.of(1, 2, 3, 4, 5);
  IntNode p = list.sentinel.next;
  while (p != null) {
      int item = p.item;
      System.out.println(item);
      p = p.next;
  }
  ```

Abstract data type methods
: Using our provided ADT methods reduces programming costs significantly since
we no longer need to worry about how the linked list works (and the code now
works more generally as well), but our execution cost increases as the `get`
operation isn't optimized for sequential access! The following code runs in
$\Theta(N^2)$ time where $N$ is the size of the list.

  ```java
  SLList list = SLList.of(1, 2, 3, 4, 5);
  int i = 0;
  while (i < list.size()) {
      int item = list.get(i);
      System.out.println(item);
      i += 1;
  }
  ```

Iterators
: Iterators provide the best of both worlds by creating an abstraction for
sequential access. The programming cost is incurred once by the `SLList`
programmer, not the client using `SLList`! The client only has to understand
how the iterator interface works to use the class.

  ```java
  SLList list = SLList.of(1, 2, 3, 4, 5);
  Iterator<Integer> seer = list.iterator();
  while (seer.hasNext()) {
      int item = seer.next();
      System.out.println(item);
  }
  ```

  Java provides the enhanced `for` loop to make this even easier to use.

  ```java
  SLList list = SLList.of(1, 2, 3, 4, 5);
  for (int item : list) {
      System.out.println(item);
  }
  ```

By **delegating** work to the implementation, we can reduce both programming
cost by writing code that adheres to a universally-accepted standard interface
while gaining all the execution cost benefits of specialization thanks to
dynamic method selection.

In this way, delegation is a form of **abstraction**. Just like we saw with the
idea of *encapsulation* earlier in the course, delegation makes it much easier
to compose efficient programs.

But it's possible to take the idea of delegation even farther by offloading
even more work to the implementation through a programming paradigm called
**functional programming**. We'll be introduced to a bit of small-scale
functional *programming* through streams both in today's lab and this week's 
lecture.

## Streams

A **stream** is a sequence of elements, much like the collections we've seen
before in this course. However, where a stream differs is in how the interface
is exposed to programmers.

With abstract data types like `List`, we normally interact with it by calling
methods to `get`, `add`, or `remove` elements from the collection. The state of
the underlying data structure is the focus of the problem, and we often need to
spend time determining the best way to represent the problem we're trying to
solve in terms of these data structures.

Streams take a different approach: instead of focusing on the data and the way
that data is organized, we instead focus on the computation and leave the
optimization to the implementation.

```java
List<String> list = Arrays.asList("a", "bd", "bb", "bc", "cc", "d");
list.stream()
    .filter(s -> s.startsWith("b"))
    .map(s -> s + s)
    .forEach(System.out::println);
```

```
bdbd
bbbb
bcbc
```

Streams are created by either calling the `stream` method of any Collection
class or by calling `Arrays.stream(array)`.

The syntax of the *stream pipeline* relies on *call chaining*.

1. `stream()` returns a streams from the given collection, which then lets us
   call the...
2. `filter(...)` method which saves the function argument for later, and
   returns a stream, which then lets us call the...
3. `map(...)` method which saves the function argument for later, and returns a
   stream, which then lets us call the...
4. `forEach(...)` method which applies each saved computation on each element
   of the stream, applying optimizations along the way, and has a `void` return
type. This *terminates* the call chain.

Because each *intermediate operation* returns a `Stream`, we can chain method
calls arbitrarily until we run into a *terminal operation* like `forEach`
which 'consumes' the stream, applying all the saved operations along the way.

The stream itself is *one-time-use*. After the stream is consumed, it cannot be
used again.

Notice how many of these operations take in function references as parameters.
It is assumed that these functions do not modify the state of the stream (or
the structure the stream originates from) and are typically deterministic
(non-random).

### Working with Streams

One intuitive way to think about streams is as a pipeline of elements and focus
on what happens to one element in that pipeline.

Each of the intermediate operations modifies the stream in-place without
looking at past or future elements, and returns the new stream. An analogy
would be to compare streams to assembly lines in a factory. Each piece moves
down the line through a sequence of transformations and operations, and at each
step the worker can complete their task without having to remember the pieces
that already moved through or look at the pieces about to reach them. Since
each item in the collection can be processed independently of the others,
our problem has become [embarrassingly parallel][].

[embarrassingly parallel]: https://en.wikipedia.org/wiki/Embarrassingly_parallel

If we did not use streams and instead used more traditional forms of computing,
it would be as if we had our factory, but we only have a single worker who now
needs to pick up each part and complete every task on it before picking up the
next part. With streams, there are many workers that do not need to communicate
with each other which allows them to work simultaneously on multiple parts.
Also once a worker is done with their one task on a part, it moves directly to
the next step in the chain and frees up the worker to start again on the next
part. This allows everyone to work at the same time and not have to wait for
the previous step.

For this lab, we will not focus on the parallel processing portion and will
instead look at the way streams create sequences of discrete tasks to be
performed on each element, but the way the Java `Stream` interface has been
designed makes it easy for code written on your laptop to scale up and solve
supercomputer-size problems. In CS 61C, you'll learn more about these parallel
processing techniques.

## Exercise: `DBTable`

Complete `DBTable.java`. Use streams to solve each problem using only one
statement each, though you can split up the one statement over multiple lines.

Each of these exercises represents part of an interface that another user could
potentially use to solve their database query needs. In order to complete these
exercises **we strongly recommend reading through the rest of the section below.**
We describe the basics of using streams plus include examples of how to manipulate
streams. Also if you run into any issues **we have included a hints and suggestions
section below.**

Streams have a lot of operations available, some of which are extremely
powerful, and it can be hard to know what the syntax is to use them. One trick
is to start the stream by typing out `.stream()` after a collection object.
This creates a stream object from that collection object and then you can type
a dot and some prefix to look for which operations you want in the auto-complete
menu. IntelliJ will tell you what it takes as parameters, what it returns, and
you can infer from the name what it does. The [`stream` package][] and
[`Stream` interface][] documentation also contains a lot of examples.

[`stream` package]: https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html
[`Stream` interface]: https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html

**Additionally, you may keep reading to read about the intermediate and terminal
operations for streams. This may help you complete the exercises of `DBTable`.**

> Streams make heavy use of **higher-order functions**, **generics**, and the
> [`Optional`][] type. There is a lot of new and probably overwhelming syntax
> in this section. Ask questions sooner rather than later!
>
> `Optional` is a container that may or may not contain a single element; you
> can then define actions to take `ifPresent` or specify default values using
> `orElse`. See the documentation for more methods.

[`Optional`]: https://docs.oracle.com/javase/10/docs/api/java/util/Optional.html

### Intermediate Operations

Intermediate operations modify a stream in-place, allowing you to continue with
stream operations. They all return a stream of some type.

`map`
: `Stream<R> map(Function<T, R> mapper)` returns a stream consisting of the
results of applying the given function to the elements of this stream. Notice
that this can change the type of the stream. While unassuming by nature, the
`map` concept is probably the most widely used throughout list manipulation.
It's like a `forEach`, but replacing each element instead of terminating the
stream.

  For example, if we just wanted to make a new list of party email users, we
could do something like this:

  ```java
  party_users = users.stream()
          .map(u -> new User(u.getUsername(), u.getUsername() + "@party.com")
          .collect(Collectors.toList());
  ```

  Of course, you don't have to limit yourself to returning the same type---you
can change your stream from one type to another:

  ```java
  int num_short_usernames = users.stream()
          .map(u -> u.getUsername()) // convert from Stream<User> to Stream<String>
          .filter(name -> name.length() < 5)
          .count();
  ```

`filter`
: `Stream<T> filter(Predicate<T> predicate)` returns a stream consisting of the
elements of the stream that match the given predicate. You can think about a predicate
as some function that returns a boolean value. If it returns true for an element 
in the stream, then it will be kept, else it will be removed. You can define a
predicate either through a lambda as we do below or by defining an instance
of the [Predicate interface](https://docs.oracle.com/javase/8/docs/api/java/util/function/Predicate.html).
This helps for removing elements of the stream based on some condition, which is
entirely up to the programmer's discretion. For example:

  ```java
  list.stream()
      .filter(s -> okayThings.contains(s))
      .collect(Collectors.toList());
  ```

  You can make the predicate check, or compute anything you want, and even
interact with other data structures in your program. A common technique is to
construct a `Set` first, and then filter out the elements found in that set.
We can solve the `missingNumber` challenge from a few labs ago this way.

  ```java
  public static int missingNumber(int[] values) {
      Set<Integer> distinctValues = Set.of(values);
      return IntStream.range(0, values + 1)
                      .boxed()
                      .filter(i -> !distinctValues.contains(i))
                      .findFirst().get();
  }
  ```

`sorted`
: `sorted()` and `sorted(Comparator<T> comparator)` sort the stream. As with
`Collections.sort`, if a `Comparator` is not specified, the natural ordering
is used.

### Terminal Operations

Terminal operations convert a stream to something that isn't a stream. Without
terminal operations, you cannot use the result of the stream. Often, this is
either void or `Optional<T>`, a container object that may or may not contain a
null value.

`forEach(Consumer<T> action)`
: Iterates over each element of the stream and executes the action on each
element of the stream. A `Consumer` takes in one argument of type `T` and has a
`void` return type. `forEach` is also a method of `Iterable` and can be used
the exact same way as it is on a stream.

`collect(Collector collector)`
: This method has possibly the most confusing documentation because it is so
general, but the simplest common function is to change a stream into a concrete
data structure. Use [`Collectors`][] to easily turn streams into lists or sets.

  ```java
  list.stream()
      .filter(s -> s.startsWith("b"))
      .collect(Collectors.toList()); // or Collectors.toSet()
  ```

  [`Collectors.toMap`][] is trickier to use, but the documentation provides
helpful examples.

[`Collectors`]: https://docs.oracle.com/javase/10/docs/api/java/util/stream/Collectors.html
[`Collectors.toMap`]: https://docs.oracle.com/javase/10/docs/api/java/util/stream/Collectors.html#toMap(java.util.function.Function,java.util.function.Function)

`reduce`
: Reduction is the process of combining all of the elements of the stream
into a single result. The two simpler `reduce` methods take in a
`BinaryOperator<T>`, which will be called on each pair of elements in the
stream repeatedly until the stream shrinks in half, then half again, and again,
until there is only one element left.

  For example, given a stream of users, we can do something funny like finding
the user with the highest `id`:

  ```java
  t.entries.stream()
           .reduce(((u1, u2) -> {
                  if (u1.getId() > u2.getId()) {
                      return u1;
                  } else {
                      return u2;
                  }
              }))
          .ifPresent(System.out::println);
  ```

  which is the equivalent of calling `max` with the right comparator. We can
also aggregate information, like combining all the fields of users into a
single mega-user:

  ```java
  User superuser = t.entries.stream()
          .reduce(new User(0, "", ""),
                  ((user, user2) -> new User(
                          user.getId() + user2.getId(),
                          user.getUsername() + user2.getUsername(),
                          user.getEmail() + user2.getEmail())
                  )
                 );
  ```

  One thing to note is that the first way returns `Optional<T>`, since the
stream may be empty, but the second returns `T` since an identity is given. The
identity passed in as the first argument behaves like the first element of the
stream.

  Reduction is quite powerful, and isn't limited to aggregation or min/max.
You can be flexible with your reduction operations and combiners, throw away
the result of the reduction, perform some intermediate operations not related
to the list, and so on.

  Also note that here we have potentially the first multi-line lambda expressions
you might have seen in Java. Typically we will use lambda expressions for simple
functions which we do not want to define an entire class for, but it is also
possible to write longer lambdas which might use `if` statements, `for` statements,
etc. 

`allMatch`, `anyMatch`, `noneMatch`
: These methods take in a `Predicate<T>` and do more or less what they sound
like they do. For each element, these methods will call the predicate on the
element and perform the appropriate action based on their name.

### Hints and Suggestions
- The methods accept a `Function<T, R> getter` as a parameter. The two generics
refer to the parameter type and return type of the function. E.g. if we passed 
in the function `User::getEmail`, this function would accept in a `User` and would
return an `String`, so `T` would correspond with `User` and `R` would correspond
with `String`.
- To evaluate a `Function<T, R>` you can use the instance method `public R apply(T t)`.
If our `Function<T, R>` was named `getter` and we wanted to pass in the `User` object `u1`
then we should write `getter.apply(u1)`.
- You should be starting most of these methods with a call to `entries.stream()`.
- For `getSubtableOf` you should have a call to the `DBTable` constructor which should
take in the result of a stream operation.
- The terminal operation for all of the stream operations in this question should most
likely be `.collect(Collectors.toList())`.

## Deliverables

Here's a quick recap of what you need to do to complete this lab.

- Make `DLList.java` iterable.
- Implement the methods `getOrderedBy`, `getWhiteListed`, and `getSubTableOf` in `DBTable.java`.
