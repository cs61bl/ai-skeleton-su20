# Lab 2

## Exercises

### Bank Account Management

In this exercise students are asked to make various changes to a class that
represents a basic Bank Account. The skeleton code represents a bank account
that allows deposits and withdrawals (the state of an account is represented by
its balance). The withdrawal method represented in the skeleton code disallows
withdrawals that exceed the balance amount.

The very first part of this exercise requires students to modify the withdraw
method such that it returns true on success and false otherwise. This might be
the first time the students see a method with a non-void return type so now
might be a good point to elaborate on return types. This part of the exercise
should be pretty simple.

The second part of the exercise requires students to implement a merge method
that takes the balance of the argument account, adds it to the current balance
of this account, and sets the argument's balance to zero. This exercise should
also be pretty straightforward.

The last part of this exercise requires the students to modify the account
class in order to provide an overdraft protection feature (If a withdrawal
exceeds the balance the remaining amount can be drawn from a parent account).
Encourage the students to devise a recursive solution for this feature. Things
to look out for:

- Make sure to check that the parent isn't null. If it is null, return false
  immediately if the amount to be withdrawn is greater than the balance.
- Remind the students that their implementations should work for long chains of
  accounts (parent withdraws from its parent and so on).
- Make sure that account states for overdrafts are updated correctly. Set
  `this` account's balance to 0 (only on successful withdrawal from parent) and
ensuring that withdrawal from the parent account occurs only after `this`
account is exhausted.
- Students often get confused with when to use `this`. Make sure they're not
  trying to refer to the parent with `this`.

### Pursuit Curves

Students will implement a simplified pursuit curve. All of the student's code
will go in `Path.java` This exercise is intended to target designing classes
given a set of goals---hence why we predefine what constructors and instance
methods should be implemented.

Most of the initial steps (i.e. defining the constructors and getter methods)
should be relatively straight forward. The most technically challenging part of
this assignment will be implementing iterate. However, we abstract away the
mathematics for pursuit curves so if students get stuck try referring them back
to the visual in the problem description.

Additionally, students may get stuck on swapping the current point with the
next point, finding that their current point always ends up being the next
point. This will most likely be due to a reference error (hence the hint to
draw the box and pointer diagram). It may additionally be helpful to remind the
student that within `Point.java` there is a constructor that takes in another
Point object.

## Quiz
Students will be able to access a gradescope quiz on this lab and lab01 for the 24 hours after their lab section. 
Students who do not perform well can utilize small group sessions led by the tutors on this specific topic.
Those who attend small group sessions will unlock a new quiz on the same topic with different questions.
This second quiz can clobber the score on the first quiz.
