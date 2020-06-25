
## Testing

Let's take a quick break to talk about testing.
**Unit testing** is a great way to rigorously test each method of your code and
ultimately ensure that you have a working project.

The 'unit' part of unit testing comes from the idea that you can break your
program down into units, or the smallest testable part of an application.
Therefore, unit testing enforces good code structure (each method should only
do 'One Thing'), and allows you to consider all of the edge cases for each
method and test for them individually.

In this class, you will be using JUnit, a unit testing framework for Java, to
create and run tests on your code to ensure its correctness. And when JUnit
tests fail, you will have an excellent starting point for debugging.

JUnit tests are written in Java. However, the JUnit library implements all the
boring stuff like printing error messages, making test writing much simpler.

`assertEquals` is a common method used in JUnit tests. It tests whether a
variable's actual value is equivalent to its expected value.

When you create JUnit test files, you should precede each test method with a
`@Test` annotation. Each test can have one or more `assertEquals` or
`assertTrue` methods (provided by the JUnit library).

**All tests must be non-static.** This may seem weird since your tests don't
use instance variables and you probably won't instantiate the class. However,
this is how the designers of JUnit decided tests should be written, so we'll go
with it.


## Debugger Basics

Before we finish off with the last `IntList` exercises for the day, let's take some
time to learn about debugging. Learning how to debug effectively is the key to overcoming
bugs methodically and efficiently. If you ever find yourself staring at the same thing for
awhile and not knowing where to go, think about what debugging tools you have available and
use them to verify the values of your program against what you know they should be,
piece by piece until you find a discrepancy.

### Breakpoints and Step Into

We'll start by running the main method in `DebugExercise1`.

Open up this file in IntelliJ and click the **Run** button. You should see
three statements printed to the console, one of which should strike you as
incorrect.  If you're not sure how to run `DebugExercise1`, right click on it
in the list of files and click the `Run DebugExercise1.main` button as shown
below:

![Run Button](img/run-button.png)

Somewhere in our code there is a bug, but don't go carefully reading the code
for it! While you might be able to spot this particular bug, often bugs are
nearly impossible to see without actually trying to run the code and probe
what's going on as it executes.

Many of you have had lots of experience with using print statements to probe
what a program is thinking as it runs. At times, print statements can be useful,
especially when trying to search for where an error is occurring. However, they
have a few disadvantages:

- They require you to modify your code (to add print statements).
- They require you to explicitly state what you want to know (since you have to
  say precisely what you want to print).
- They provide their results in a format that can be hard to read, since it's
  usually just a big blob of text in the execution window.

Often (but not always) it takes less time and mental effort to find a bug if
you use a debugger. The IntelliJ debugger allows you to pause the code in the
middle of execution, step the code line by line, and even visualize the
organization of complex data structures like linked lists with the same
diagrams that would be drawn by the Online Java Visualizer.

While they are powerful, debuggers have to be used properly to gain any
advantage. We encourage you to do what one might call "scientific debugging",
debugging by using something quite similar to the scientific method!

Generally speaking, you should formulate hypotheses about how segments of your
code should behave, and then use the debugger to resolve whether those
hypotheses are true. With each new piece of evidence, you will refine your
hypotheses, until finally, you cannot help but stumble right into the bug.

Our first exercise introduces us to two of our core tools, the **Breakpoint**
and the **Step Over** button. In the left-hand **Project** view, right click
(or two finger click) on the `DebugExercise1` file and this time select the
**Debug** option rather than the **Run** option. If the Debug option doesn't
appear, it's because you didn't properly import your `lab05` project. If this
is the case, repeat the lab [setup][] instructions.

![Debug Button](img/debug-button.png)

You'll see that the program simply runs again, with no apparent difference!
That's because we haven't given the debugger anything interesting to do. Let's
fix that by setting a breakpoint.

To set a **breakpoint**, scroll to the line that says `int t3 = 3;`, then left
click just to the right of the line number. You should see a red dot appear
that vaguely resembles a stop sign, which means we have now set a breakpoint.

If we run the program in debug mode again it'll stop at that line. If you'd
prefer to avoid right-clicking to run your program again, you can click the bug
icon in the top right of the screen instead.

<div style='position:relative;padding-bottom:57%'><iframe src='https://gfycat.com/ifr/ThickBarrenFrogmouth' frameborder='0' scrolling='no' width='100%' height='100%' style='position:absolute;top:0;left:0;' allowfullscreen></iframe></div>

If the text console (that says things like `round(10/2)`) does not appear when
you click the debug button, you may need to perform one additional step before
proceeding. At the top left of the information window in the bottom panel, you
should see two tabs labeled **Debugger** and **Console**. Click and drag the
**Console** window to the far right of the bottom panel. This will allow you to
show both the debugger and the console at the same time.

<div style='position:relative;padding-bottom:57%'><iframe src='https://gfycat.com/ifr/SmugAbleAustraliankelpie' frameborder='0' scrolling='no' width='100%' height='100%' style='position:absolute;top:0;left:0;' allowfullscreen></iframe></div>

Once you've clicked the debug button (and made your console window visible if
necessary), you should see that the program has paused at the line at which you
set a breakpoint, and you should also see a list of all the variables at the
bottom, including `t`, `b`, `result`, `t2`, `b2`, and `result2`. We can advance
the program one step by clicking on the "step into" button, which is an arrow
that points straight down as shown below:

![step into](img/step-into.png)

> Make sure you're pressing **Step Into** rather than **Step Over**. Step into
> points straight down, whereas step over points diagonally up and then diagonally down.

Each time you click this button, the program will advance one step.

> **Before you click each time, formulate a hypothesis about how the variables
> should change.**

Repeat this process until you find a line where the result does not match your
expectations. Then, try and figure out why the line doesn't do what you expect.
If you miss the bug the first time, click the stop button (red square), and then
the debug button to start back over. Optionally, you may fix the bug once you've
found it.

### Step Over and Step Out

Just as we rely on layering abstractions to construct and compose programs, we
should also rely on abstraction to debug our programs. The "step over" button
in IntelliJ makes this possible. Whereas the "step into" from the previous
exercise shows the literal next step of the program, the "step over" button
allows us to complete a function call without showing the function executing.

The main method in `DebugExercise2` is supposed to take two arrays, compute
the element-wise max of those two arrays, and then sum the resulting maxes. For
example, suppose the two arrays are `{2, 0, 10, 14}` and `{-5, 5, 20, 30}`. The
element-wise max is `{2, 5, 20, 30}`, and the sum of this element-wise max is
2 + 5 + 20 + 30 = 57.

There are two different bugs in the provided code. Your job for this exercise
is to fix the two bugs, with one special rule: **You should NOT step into the
`max` or `add` functions or even try to understand them.**

These are very strange functions that use poor syntax (and bad style) to do easy
tasks in an incredibly obtuse way. If you find yourself accidentally stepping
into one of these two functions, use the "step out" button (an upwards pointing
arrow) to escape.

Even without stepping INTO these functions, you should be able to tell whether
they have a bug or not. That's the glory of abstraction! Even if I don't know
how a fish works at a molecular level, there are some cases where I can clearly
tell that a fish is dead.

Now that we've told you what "step over" does, try exploring how it works
exactly and try to find the two bugs. If you find that one of these functions
has a bug, you should completely rewrite the function rather than trying to fix
it.

> If you're having the issue that the using run (or debug) button in the top
> right keeps running `DebugExercise1`, right click on `DebugExercise2` to run
> it instead.

If you get stuck or just want more guidance, read the directions below.

#### Further Guidance (for those who want it)

To start, try running the program. The `main` method will compute and print an
answer to the console. Try manually computing the answer, and you'll see that
the printed answer is incorrect. If you don't know how to manually compute the
answer, reread the description of what the function is supposed to do above, or
read the comments in the provided code.

Next, set a breakpoint to the line in `main` that calls
`sumOfElementwiseMaxes`. Then use the debug button, followed by the step-into
function to reach the first line of `sumOfElementWiseMaxes`. Then use the "step
over" button on the line that calls `arrayMax`. What is wrong with the output
(if anything)? How does it fail to match your expectations?

> Note that to see the contents of an array, you may need to click the
> rightward pointing triangle next to the variable name in the variables tab of
> the debugger window in the bottom panel.

If you feel that there is a bug, step into `arrayMax` (instead of over it) and
try to find the bug.

> Reminder: do not step into `max`. You should be able to tell if `max` has a
> bug using step over. If `max` has a bug, replace it completely.

Repeat the same process with `arraySum` and `add`. Once you've fixed both bugs,
double check that the `sumOfElementwiseMaxes` method works correctly for the
provided inputs.

### Conditional Breakpoints and Resume

Sometimes it's handy to be able to set a breakpoint and return to it over and
over. In this final debugging exercise, we'll see how to do this and why it is
useful.

Try running `DebugPractice3`, which attempts to count the number of turnips
available from all grocery stores nearby. It does this by reading in
`foods.csv`, which provides information about foods available, where each line
of the file corresponds to a single product available at a single store. Feel
free to open the file to see what it looks like. Strangely, the number of
turnips seems to be negative.

Set a breakpoint on the line where `totalTurnips = newTotal` occurs, and you'll
see that if you "step over", the total number of turnips is incremented as
you'd expect. One approach to debugging would be to keep clicking "step over"
repeatedly until finally something goes wrong. However, this is too slow. One
way we can speed things up is to click on the "resume" button (just down and to
the left from the step-over button), which looks like a green triangle pointing
to the right. Repeat this and you'll see the turnip count incrementing
repeatedly until something finally goes wrong.

An even faster approach is to make our breakpoint conditional. To do this,
right (or two-finger) click on the red breakpoint dot. Here, you can set a
condition for when you want to stop. In the condition box, enter "newTotal <
0", stop your program, and try clicking "debug" again. You'll see that you land
right where you want to be.

See if you can figure out the problem. If you can't figure it out, talk to your
partners, another partnership, or a lab assistant.

### Recap: Debugging

By this point you should understand the following tools:

- Breakpoints
- Stepping over
- Stepping into
- Stepping out (though you might not have actually used it in this lab)
- Conditional breakpoints
- Resuming

However, this is simply scratching the surface of the features of the debugger!
Feel free to experiment and search around online for more help.

Remember that **Watches** tab? Why not read into what that does?

Or try out the incredibly handy **Evaluate Expressions** calculator button (the
last button on the row of step into/over/out buttons)?

Or perhaps look deeper into breakpoints, and **Exception Breakpoints** which
can pause the debugger right *before* your program is about to crash.

We won't always use all of these tools, but knowing that they exist and making
the debugger part of your toolkit is incredibly useful.

