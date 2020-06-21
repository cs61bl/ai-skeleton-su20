# Lab 1

This lab is broken up into three parts: familiarizing yourself with how Java
works, understanding git and how it is used in the class, and getting your feet
wet with Java code.

## Goals

> When checking in with students, these are good baselines to determine if they
> understand the content and completed the assignment correctly.

- Understand course policies, most importantly submission of assignments
  protocol and pair-partnerships policies.
- Familiarize yourself with git workflow for version control and local/remote
  file management.
- Work with simple Java code and develop elementary understanding of the
  syntax.

## Exercises

> Pain points, potential bugs, and teaching techniques for how to
> approach the different exercises in the assignment.

### Java Compilation and Development

This section will mostly be a pretty simple walk-through of how Java programs
work. Students may ask what **javac** does. The simple answer, **javac**
compiles Java programs into Java bytecode stored in .class files, which can be
run in the terminal.

### Git

#### Local commands

- **git add**: Adds a file to staging, where the latest changes are tracked.
- **git commit**: Saves all staged files into a commit. A commit is like a
  snapshot of your current repository.
- **git reset**: Unstages files. Basically undoes git add for the file. It does
  not change the contents of the file but rather it removes it from staging.
- **git checkout**: Changes your current repository to the specified commit, or
  screenshot of the repository. In the lab -- is used, which defaults to the
last commit.

#### Remote Commands

- **git clone**: copies the remote repo to your local repo
- **git remote add**: takes in two arguments, a repo and a url, and adds the
  repo at the url as a remote repo that can be pulled from
- **git pull**: takes in two arguments, a repo and a branch, and pulls the repo
  from the remote repo specified at the given branch. In the case of the lab,
we pull from the skeleton repo and at the master branch.
- **git push**: takes commits on the local machine and applies them to the
  remote repo

### Leap Year Java

This exercise is mostly a gentle introduction to some Java code and mostly will
force students to use the above git techniques to push code and submit to
Gradescope. Students may ask you what a leap year is though. Here are the three
criteria that determine a leap year:

1. The year can be evenly divided by 4.
2. If the year can be evenly divided by 100, it is NOT a leap year, unless...
3. The year is also evenly divisible by 400. Then it is a leap year.

## Checkoff: Git Exercise

When you go to checkoff the student, the first thing you should do is to check
that their repository is in the proper state, based from the git exercise.
There are a few things that you should check for:

- Check that their git repository is in a directory called `lab1-checkoff`.
- Check that there are two files in the directory, with the following contents
  within them:

    - `61b.txt`: 61b changed to version 3
    - `61bl.txt`: 61bl version 1

- Run `git status` on their computer to see that it shows `61b.txt` not staged
  for commit and has been modified
- Run `git log` on their computer to see that it shows exactly 2 commits, with
  "Update 61b.txt and add 61bl.txt" as the most recent commit message and "Add
61b.txt" as the least recent commit message.
- Run `git show [first 4 characters of first commit id]` on their computer to
  see whether the first commit only contains `61b.txt`, and that `61bl.txt`
wasn’t added. This is hopefully to try to prevent students from just adding
everything into their commits all the time. You should only see a diff for
`61b.txt` with no mention of `61bl.txt`. For git show, you need to do at least
the first 4 characters of the commit id to get a match. If 4 characters doesn’t
work, trying a few more characters will definitely work. If the student had
trouble setting up their repository for git checkoff, there's no need to have
them do it again if they know how they would fix the problem.
- Run `git show [first 4 characters of most recent commit id]` on their computer
  to see whether both files were tracked and added.

### Questions

After you see that their repository is in a good state, ask them the following
questions.

**What is the difference between `git add` and `git commit`? Why is it useful
to have the `add` command?**

> Adding doesn’t actually save the changes in git, but it tells git which files
> to save changes for. It is useful in that we can make our commits as small
> and refined by telling git exactly what files to add in a commit.

**Suppose `hug.txt` is a file in a directory with a git repository but has not
been tracked by git. You then make a modification to the file. Would git status
show anything different before and after you made the modification? Why?**

> No git status would not show anything different. Because `hug.txt` is
> untracked, anytime you make a modification to it, git doesn’t care. It will
> simply list hug.txt as an untracked file each time. As soon as you add it and
> commit it, then now any modifications you make with respect to the most
> recent commit will be reported by git status.

**What are some benefits of commiting often?**

> As an example, suppose that you decided to not make frequent commits on a big
> project. You've finished part 1 and tested it so it seems to work, but you’re
> struggling on part 2. You write a bunch of code for part 2 that unfortunately
> affects the code you wrote for part 1, and your current approach for part 2
> doesn’t seem to be working. Since you didn't commit often, you may not be
> able to get your code back to what it was when just part 1 was done,
> resulting in more headache of trying to delete the code you wrote for part 2
> without negatively affecting the code for part 1. Commiting often allows you
> to have better control and flexibility of which versions of the code you are
> able to go back to and revert to if needed.

(After looking at their repository to see that it is in the expected state.)
**Please revert `61b.txt` back to the version in the most recent commit using
just git commands. Please revert `61b.txt` back to the first version.**

> For both of these, `git checkout` should do the job.
>
> - *To revert `61b.txt` back to the version in the most recent commit:* `git
>   checkout 61b.txt`
> - *To revert `61b.txt` back to the first version:* `git checkout [at least
>   first 4 chars of first commit id] 61b.txt`

## Submission

Once the student has answered the lab checkoff questions and you've checked that
their repo is in a good state, direct the student to the magic_word.txt file
that should have come from the skeleton. It is currently empty. Your TA will tell
you how to have students fill it out.

