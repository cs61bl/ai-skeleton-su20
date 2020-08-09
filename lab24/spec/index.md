---
layout: page
title: "Lab 24: Regex"
tags: [Lab, Regex]
released: true
---

## Before You Begin

As usual, pull the skeleton code. This is your last lab in CS 61BL!!! WOOHOO! This
has been a long and grueling summer and we are so so proud of all of the hard work
that you have been done. Congratulations on making this far you are almost done
with the whole class!

Introduction
-------

In this lab you'll be learning to work with _regular expressions_, often abbreviated as _regex_.
Regular expressions are a special kind of syntax that allow easy searching of long documents
for Strings that match special patterns. Most general purpose programming languages you come
across will allow you to use regex, so rest assured that what you learn today will not only
apply to Java. Regex is an essential tool for any programmer and can often lead to simpler
cleaner solutions for programming tasks related to strings. 

### A Motivating Example

Imagine you have a document, like so:

    Dear Bob,

    The time for revolution is fast approaching. We must act quickly before the government gets wind of our plans. 
    Please forward this message to our trusty allies, eve@shadywebsite.net and mallory@vivalarevolucion.com.
    And don't let anyone else see this message!!

    Sincerely,
    Alice

You'd like to create a program that reads in documents like this and outputs a list of all 
email addresses contained within the document. How would you do it?

If we were to give you this task before today, you might start off like so: First, split the
document into its separate words by looking for spaces. Then for each word, check if it 
contains an `@` sign. But only _one_ `@` sign. And there has to be text before and after the
`@` sign. And that text could include numbers, and letters before (and a couple of other 
things), but only letters after. And for sure it must _start_ with a letter. And there can
be multiple periods, but you couldn't have two periods in a row. 

See the problem? If you were to write this program, it would involve a lot of crazy conditionals
all over the place, and be quite long. But it's such a mundane task. Certainly we can do better,
right?

Luckily, regex steps up to the challenge. You can solve this problem essentially using a single
line of regex. This particular regex is a bit complicated, so we'll start more simply. But we'll
get there.

### Checking if a String Matches a Pattern

The simplest way we can use regex is to check if a `String` matches a _pattern_. The `String` class
has a method for this. Here's an example.

```java
String wugPattern = "wug";
String w = "wug";
System.out.println(w.matches(wugPattern)); // prints true
w = "love";
System.out.println(w.matches(wugPattern)); // prints false
```

If this is all we could do with regex, it would be pretty useless. Luckily, regex patterns can be
more general than just specific sequences of characters.

## Character Classes

### Character Classes

In the previous example, we asked if a `String` matched a sequence of characters. We can add to 
this, and ask if a `String` matches a sequences of characters and _character classes_. The fastest
way to explain a character class is probably just to show it.

```java
String pattern = "w[aeiouy]g";
String w = "wug";
System.out.println(w.matches(pattern)); // prints true
w = "wog";
System.out.println(w.matches(pattern)); // also true!
w = "waeg";
System.out.println(w.matches(pattern)); // false
```

What's going on? The `String` `"wug"` doesn't look anything like the `String` `"w[aeiouy]g"`! 
That's because square brackets are a special symbol in regex. A `String` will match a regex pattern
if it matches any one character inside the brackets. You can think of it as a big OR symbol. This
pattern is saying: first match exactly one `w`, then match exactly one of `a`, `e`, `i`, `o`, `u`,
or `y`, then match exactly one `g`.

What if you wanted to match all the letters of the alphabet, a - z? You could do:

```java
String pattern = "[abcdefghijklmnopqrstuvwxyz]ug";
String w = "hug";
System.out.println(w.matches(pattern)); // prints true
```

This seems a little annoying. Luckily, regex has a shortcut for us:

```java
String pattern = "[a-z]ug";
String w = "hug";
System.out.println(w.matches(pattern)); // prints true
```

Yes, `[a-z]` will match all the characters between a and z. This works for numbers too. `[0-9]`
will match all the digits.

### Not

If you include a `^` at the beginning of a character class, you will match any character that's 
_not_ in the class. For example, the pattern `w[^aeiouy]g`will match `whg`, `wng`, `wvg`, etc.,
but not `wug`, `wig`, `wog`, etc.

## Discussion: Character Classes

We would like to construct a regex pattern which matches the strings `Matt`, `Malt`, and `Mart`
but does not match the strings `Mite`, `mutt`, or `Mott`. Which of the following regex patters
meet that criteria? Your answer might include more than one regex pattern.

1. `"M[aye][rlt]t"`
2. `"M[a-z][a-z]t"`
3. `"[Mm]a[rlt][te]"`
4. `"Ma[^t]t"`

Answer below (highlight to reveal):
<div style="color: white; background: white;">
Patterns 1 and 3 will match the criteria from above. 
</div>

## Special Characters 

### Repetition

The true power of computer science, of course, is in its ability to repeat things. Naturally,
regex supports some kind of notion of repetition. Here's an example of one in action:

```java
String laughingPattern = "lo+l";
String w = "lol";
System.out.println(w.matches(laughingPattern)); // true
w = "loooool";
System.out.println(w.matches(laughingPattern)); // true
w = "ll";
System.out.println(w.matches(laughingPattern)); // false
```

There's another special regex character! The `+` sign matches _one or more repetitions_ of the
character that precedes it, as shown above. There are some other special characters that work
similarly to, but not exactly the same as, the `+` sign. They are:

- `?` matches 1 or 0 occurrences of the preceding character
- `*` matches 0 or more occurrences of the preceding character
- `{n}` matches exactly `n` occurrences of the preceding character
- `{n,m}` matches at least `n` but not more than `m` occurrences of the preceding character

You can repeat a group of things using parentheses. For example:

```java
String laughingPattern = "l(ol)+"; // specifies you can repeat ol
String w = "lol";
System.out.println(w.matches(laughingPattern)); // true
w = "lololololol";
System.out.println(w.matches(laughingPattern)); // true
w = "loooooool";
System.out.println(w.matches(laughingPattern)); // false
```

### Special Characters and Escaping

Usually when you include a character in a regex pattern, that character will only match itself.
For example, the regex pattern "wug" will match the `String` "wug" and nothing else. As we saw 
earlier, however, there are certain special characters that have other functions. `[` and `]` were
used to indicate character classes, allowing us to match one of several characters, while `+`,  
`?`, and `*` were used to match repetitions. We'd like to now introduce one more common special
character: 

- `.` matches any character, except a line terminator

Great. So now we know some special characters in regex. What if we actually wanted to match one
of the special characters? What if you wanted to specifically match a String that included a `+`,
for example? In this case, you have to _escape_ the character by putting a `\` in front of it.

- `\+` will match exactly the String "+". This technique works for other special characters as well!

Note that this means `\` is _itself_ a special character. So if you wanted to match a string that
included `\`, you'd need to include `\\`.

Note! The `\` is a bit of crazy character, because it's not just a special character in regex; it's
a special character in any Java string, regardless of if it's intended to be interpreted as regex.
For example, try `System.out.println("\n");`, and you won't see a `\` appear). Therefore, if you
want to match the string `"+"`, you need one preceding `\` to escape `+`'s special regex meaning,
and you need to precede that with another `\` to escape the `\` from its special Java meaning. 
Thus, the correct pattern for this in Java would be `"\\+"`.

### Special Characters and Character Classes

The characters that are considered special inside of `[]` are _different_ than the characters that
are considered special elsewhere. The only special characters inside `[]` are `^`, `-`, and `\`. 
The `\` is still used for escaping, allowing you to escape `]`, `^`, and `-` (and `\` itself) 
inside the character class.

### Convenient Character Classes

Aside from escaping, the `\` in regex has an additional use. Notice that since there's no reason
you'd even want to escape normal characters, such as `w`, you might imagine the sequence `\w` is
useless. It's not. Intead, regex uses sequences like these for special meanings:

- `\d` matches any digit, `\D` matches any non-digit.
- `\n` matches a new line (but be careful on Windows, for which new line characters are preceded 
by the return character, `\r`).
- `\s` matches any whitespace character (space, newline, tab, etc.), `\S` matches any non-whitespace.
- `\w` matches any _word_ character (a-z, A-Z, or 0-9), and `\W` matches any non-word character.

### Discussion: Special Characters

We would like to construct a regex pattern which matches the strings `Zoe`, `Z+e`, and `Zoeeeee`
but does not match the strings `Zo`, `Zeeeee`, or `Z0e`. Which of the following regex patters
meet that criteria? Your answer might include more than one regex pattern.

1. `"Zo+e?"`
2. `"Z[\w+]?e*"`
3. `"Z[o\+]e*"`
4. `"Z[o\+]\w+"`

Answer below (highlight to reveal):
<div style="color: white; background: white;">
Only pattern 4 will match the criteria from above. 
</div>

## Java's Regex Objects

### Using Java's `Pattern` and `Matcher` Objects

Using `String`'s `matches` method is cute and all, but this is Java, so we should expect objects to
get involved somehow. This turns out to be the case. In order to do more complicated things with
regex, we'll need to take advantage of two new classes: `Pattern` and `Matcher`. These need to be
imported from `java.util.regex`.

Before we were representing patterns with just Strings, like so:

```java
String laughingPattern = "lo+l"; 
```

Now we'll transition to using a `Pattern` object instead.

```java
Pattern laughingPattern = Pattern.compile("lo+l");
```

We want to check if various `String`s match this pattern. We have to create a `Matcher` object for
each one out of the pattern.

```java
Matcher matcher1 = laughingPattern.matcher("loool");
System.out.println(matcher1.matches()); // prints true

Matcher matcher2 = laughingPattern.matcher("loooooooool");
System.out.println(matchter2.matches()); // prints true
```
    
Seems like a lot of work just to do the same thing, right? But it's worth it, because these objects
come with some extra functionality. 

### Flags

For example, you can pass special _flags_ into the constructor for `Pattern` to subtly change its 
behavior. One of the most useful ones is one that allows it to ignore case.

```java
Pattern laughingPattern = Pattern.compile("lo+l", Pattern.CASE_INSENSTIVE);

Matcher matcher1 = laughingPattern.matcher("loool");
System.out.println(matcher1.matches()); // prints true

Matcher matcher2 = laughingPattern.matcher("LOoOoOOL");
System.out.println(matchter2.matches()); // prints true
```

### Finding

Notably, `Matcher` has a `find` method that will check if any _substring_ matches the pattern.

```java
Matcher matcher3 = laughingPattern.matcher("loooooolz");
System.out.println(matcher3.matches()); // prints false, because the pattern doesn't have a z

Matcher matcher4 = laughingPattern.matcher("looooooolz");
System.out.println(matcher4.find()); // prints true, because some substring does match the pattern
```

Why did I create a `matcher4` instead of just reusing `matcher3` and calling its `find` method? It
turns out the `Matcher` objects _cannot be reused normally_. If you do want to reuse one, you would
have to call its `.reset()` method.

### Finding Multiple Things

What if we want to check if _multiple_ substrings match the pattern? We can try to use the `find`
method multiple times to see if there are additional matches. Suppose we have the following method:

```java
public static int countLaughs(String text) {
    Pattern lolPattern = Pattern.compile("lo+l");
    Matcher lolMatcher = lolPattern.matcher(text);
    int count = 0;
    while (lolMatcher.find()) {
        count++;
    }
    return count;
}
```
    
We could try the following code:

```java
System.out.println(countLaughs("looooolz you are the funniest person I have ever met lolol"));
```

This prints 2. We say that find _consumes_ characters left-to-right. Once a character has been
included in a match, it cannot be reused. Hence, we only match twice, not three times.

## Capturing 

### Capturing

One of the most useful things we can do with regex is _capturing_. Capturing allows us to extract
the part of a String that matches a pattern. For example, suppose I have a document that includes
dates formatted like so: `2015-03-14` (e.g `YYYY-MM-DD`). Then I just want to return a list of all
the years mentioned in the document. I will use the following pattern:

```java
Pattern yearCapturer = Pattern.compile("([0-9]{4})-[0-9]{2}-[0-9]{2}");
```

> Noter here we make the simplifying assumption that a valid date could have any digit in any spot.
> This is not the most precise regex we could have written! We know that months are only 01-12 and
> days are only 01-31, for example.

Notice that the pattern contains `(` and `)`. Parentheses are special characters in regex that 
indicate a _capturing group_, which mean they are a part of the pattern we wish to extract. Here is
an example of it in use:

```java
String document = "Please meet me on 2016-03-14. That's right. Let's wait a year. I didn't mean 2015-03-14.";
Matcher yearMatcher = yearCapturer.matcher(document);
while (yearMatcher.find()) {
    System.out.println(yearMatcher.group(1)); // prints out the thing we captured from this find. First 2016, then 2015
}
```

Why is there a `1` in `.group(1)`? It turns out we can include multiple capturing groups in an
expression and reference them by number. 

```
Pattern dateCapturer = Pattern.compile("([0-9]{4})-([0-9]{2})-([0-9]{2})");
Matcher dateMatcher = dateCapturer.matcher(document);
while (dateMatcher.find()) {
    System.out.println(dateMatcher.group(1)); // first 2016, then 2015
    System.out.println(dateMatcher.group(2)); // first 03, then 03
    System.out.println(dateMatcher.group(3)); // first 14, then 14
}
```

Why do I start counting at 1, and not 0? It turns out 0 contains the whole matching expression, 
ignoring the parentheses. Above, first `dateMatcher.group(0)` would contain `2016-03-14`, then
next it would contain `2015-03-04`.

### Greedy vs. Reluctant

What would you expect the following code to do? Recall that `group(0)` is the whole substring that
`find` matches.

```java
Pattern weirdLaughingPattern = Pattern.compile("l.+l");
Matcher weirdMatcher = weirdLaughingPattern.matcher("looool you are the funniest person I have ever met lolol");
while (weirdMatcher.find()) {
    System.out.println(weirdMatcher.group(0));
}
```

Perhaps you expect it to print out `looool` and `lol`. This is not the case. It actually prints out
`looool you are the funniest person I have ever met lolol`. Why's that? It turns out the `+` 
operator is _greedy_, which means it will try to match as many characters as possible. Notice the 
`String` we matched starts with `l`, ends with `l`, and has one or more other characters in the 
middle. And it has as many characters in the middle as possible. `?` and `*` are also greedy. To 
make them _reluctant_, meaning they will match as few characters as possible, include a `?` after
them. To get the code to print `looool` and `lol`, the correct pattern is `l.+?l`.

### Find and Replace

A natural extension of finding is find-and-replace. The `Matcher` class has two methods, 
`replaceAll` and `replaceFirst` that might be helpful. For example, the following makes a famous
quote gender-neutral:

```java
Pattern malePattern = Pattern.compile("men");
Matcher neutralizer = malePattern.matcher("We hold these truths to be self-evident, that all men are created equal...");
System.out.println(neutralizer.replaceAll("people"));
```
    
However, this regex doesn't do what we want in the following case:

```java
Matcher neutralizer2 = malePattern.matcher("We hold these truth to be self-evident, that all men are equal mentally...");
System.out.println(neutralizer2.replaceAll("people"));
```

It prints:

```
We hold these truth to be self-evident, that all people are equal peopletally...
```

That's right, it replaced the `men` in `mentally`, too! That's not what we wanted. How do we fix 
this problem?

## Boundaries

### Boundaries

We introduce the concept of _boundaries_. Boundaries help us specify that we only want to match 
patterns within certain boundary conditions. Here are some useful boundaries we can use in regex:

- `^` matches the beginning of a line
- `$` matches the end of a line
- `\b` matches a _word boundary_. Word boundaries occur at the ends of sequence of word characters.
Recall the word character from earlier.
- `\B` matches a non-word boundary.

In this case, we probably only want to match the _word_ `men`, so we should demarcate it with word
boundaries. Here's the regex we want:

```java
Pattern malePattern = Pattern.compile("\\bmen\\b");
```

Again, we want to treat the `\` next to `b` as a normal character, not a special character, so we
must escape it by preceding it with another `\`.

## Exercises

Complete two of the following three exercises in `RegexSolution.java`. You may find IntelliJ's 
RegExp checker useful! Just click your cursor on any Regex you are working on, press "Alt"+"Enter",
and select "Check RegExp" to test out your expressions! Similarly, 
[regex101](https://regex101.com/) and [regexr.com](http://regexr.com/) may also be useful tools in
helping you debug your Regex.

### URLs
For this  exercise, you must create a list containing only the target input Strings. Each url we
are looking for has a very particular pattern and is surrounded by parentheses `()`. The 
following are examples of valid urls: `(randomstuff1234https://www.eecs.berkeley.edu/blah.htmlyoullneverfindyourextracredit)`,
`(https://en.wikipedia.org/greed.htmltry23andfindmenow)`, `(http://www.cs61bl.github.io/yolo.html)`.

Here's the exact pattern specification:

- Starts with a `(`.
- Followed by 0 or more characters--any characters whatsoever.
- Followed by either `http://` or `https://`.
- Followed by one or more domain names (see below for the domain criteria).
- Followed by a 2 to 3 lower-case letter top-level domain (not the same as domain name; just 3 lower-case letters), e.g., `com` or `me`.
- Followed by `/`.
- Followed by file name, which is 1 or more word characters.
- Followed by `.html`.
- Followed by 0 or more characters--any characters whatsoever.
- Ends with a `)`.

A valid domain name is a sequence of any length of word characters greater than 0 followed by a 
`.`. For example `eecs.berkeley.` is a valid domain or simply `www.berkeley.`, or just 
`facebook.`.

> The text you'll be parsing is inundated with text preceding `https` and urls ending in `.html`,
> so if you use a greedy pattern you will match thousands of urls--you only want one. Use a 
> reluctant regex any time you match on 0 or more things.

### Finding Startup Names 
You wake up one day and find yourself as some strange amalgam of Marc Andreessen, Peter Thiel,
Sam Altman, Ben Horowitz as well as the CEO of Y Combinator. As a well-respected VC, your job is to
discover and incubate innovative, game-changing, data-driven, cutting edge startups that will 
disrupt and change the world. For this exercise, you must create a list containing only the target
input Strings. First, these startups must have a worthy name and because you received thousands of
applications per day, you decide to write a regex parser for your first round of filtering. The 
naming criteria are listed as follows:
- Names must start with any of the words `Data`, `App`, `my`, `on`, `un`.
- Names must contain 1 or more alphanumeric characters except none of these can be `i` (because `y`
would you use `i` when you can use `y`?).
- Names must end with any of the suffixes `ly`, `sy`, `ify`, `.io`, `.fm`, `.tv`.

> Hint: Although we didn't mention it above, you should look up regex alternation constructs, which
> allow you to use a vertical bar to select either the pattern before or the pattern after the bar
> (e.g. `h(ac|ec)k` matches both "heck" and "hack").

### Recover the Image!
For this exercise, you are given a text file named `mystery.txt` where each line contains a list of
RGB pixel values for a particular `(x, y)` coordinate pair. The RGB values are collection of a 
three integer values between square brackets, separated by commas (e.g. `[240, 100, 76]`). The 
corresponding coordinate pair is represented using a length-2 tuple of form
`([x-value], [y-value])` and can appear before or after the RBG pixel values. The integer values
for both the RBG values and the coordinate pairs should be between 0 and 999. However, each line
may contain other arrays and tuples which do not fit our criteria. Write a regex pattern to match
and extract each list of `(x, y)`-RGB values and reveal the mystery image (link to a piece of the
text file [here](./img/mystery.txt)).

## Conclusion

### Additional Resources
Don't worry if all of these symbols seem like complete gibberish. With repeated practice, you'll
eventually find that the necessary characters come to you more quickly and intuitively. Until 
then, feel free to consult Regex cheatsheets like the ones listed below:

* [Omi Centra](http://www.omicentral.com/cheatsheets/JavaRegularExpressionsCheatSheet.pdf)
* [Regex Lib](http://regexlib.com/)

If you enjoyed the exercises in this lab and would like some additional practice, also consider 
playing some [Regex Golf](http://regex.alf.nu/).

## Deliverables

- Complete `RegexPuzzles.java`. Our tests will check the correctness of your solution using 
randomized tests, so keep that in mind as you're writing your solutions. And with that, 
congratulations on completing your last lab of 61BL!
