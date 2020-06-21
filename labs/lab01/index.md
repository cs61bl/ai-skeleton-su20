---
layout: page
title: "Lab 1: Java & Git"
tags: [Lab, Java, Git]
released: false
---

[ed]: https://us.edstem.org/courses/630/discussion/

## Before You Begin

**Welcome to CS 61BL!** We have a wonderful summer planned for y'all, and we're so excited that you'll be joining us!

First thing's first: setup! In this class, you'll be using real-world tools, and that means that you'll likely run into real-world problems with configuration and setup these first few days. **Don't be discouraged**, and make sure to ask for help if you're stuck! The best place to ask for help is through Zoom during your actual lab time. If you attempt to do this outside of that time and run into any problems, please come ask then.

> If ever something isn't working, or a screen that should show up isn't showing up, make sure you ask for help -- **do not** keep going because this might make it more difficult for us to identify the problem later on if you do hit a dead-end.

### Partner up!

Your lab TA will assign you a partner to work with today -- but don't worry, you'll only *have* to work with them today. In fact, everyday this week, we'll assign you a new partner to work with so that you have a chance to meet different people in your lab section! For more information, take a look at our [Collaboration Guide](../../guides/collaboration-guide).

## Terminal Basics

### Text Editor ###
> If you already have a text editor you like from a previous class, feel free to skip this step. Word processors like Microsoft Word do not count.

Throughout the summer you will find it helpful to have a text editor and one will be required to complete this lab. In a following lab we will be switching to using IntelliJ, a powerful IDE (Integrated Development Environment), to develop all of your Java code. Some popular options are [Atom](https://atom.io/) and [Sublime Text](https://www.sublimetext.com/). You should be able to use the default installation recommendations, but if you run into problems do not hesitate to ask for help!

### Terminal Installation (Windows) ###
> If you either do not have a Windows computer or do have a Windows computer but installed Git Bash in a previous class feel free to skip this step.

In this class we will be using the terminal to run programs, submit assignments, etc. All commands we will be supplying will be for a bash shell. Unfortunately the default Windows command prompt is not a bash shell, so we will need to install one. **Please install the [Git Bash terminal](https://git-scm.com/downloads)** by clicking the "Download 2.27.0 for Windows" button. You should be able to select the default configurations with the exception of the following option.

You can choose the **default editor used by git** to be any of the options in the list if you have Atom, Sublime Text or another editor installed. Students often find these easier to use than Nano or Vim (which can be a bit tricky to figure out if you have never used them).

![git-bash-editor](img2/git-bash-editor.png "git-bash-editor")

> Note that some of the versions will be ever so slightly different than the versions in the screenshots. This should not make any difference, don't worry if the photos do not match exactly.

After installing you should now have a Bash terminal on your computer. Whenever we refer to a terminal throughout the class you should use Git Bash instead of any other terminal on your Windows computer (e.g. do not use CMD or Powershell).

### Essential Terminal Commands ###
In CS 61BL we will be using the terminal extensively, even more than you likely did in previous classes. Bash commands can be pretty powerful and will allow you to create folders or files, navigate through your file system, etc. To jump start your knowledge we have included a short guide of the most essential commands that you will be using in this class. Please carefully read this and try to familiarize yourself with the commands. We will help you as you get started, but by the end of the class we hope that you will have become a proficient user of the bash terminal!

`cd`
: Change your working directory

    ```sh
    cd hw
    ```

    This command will change your directory to `hw`.

`pwd`
: Present working directory

    ```sh
    pwd
    ```

    This command will tell you the full absolute path for the current directory you are in if you are not sure where you are.

`.`
: Means your current directory

    ```sh
    cd .
    ```

    This command will change your directory to the current directory (aka do nothing).

`..`
: Means one parent directory above your current directory

    ```sh
    cd ..
    ```

    This command will change your directory to its parent. If you are in `/workspace/day1/`, the command will place you in `/workspace/`.

`ls`
: List files/folders in directory

    ```sh
    ls
    ```

    This command will list all the files and folders in your current directory.

    ```sh
    ls -a
    ```
    This command will list all the files and folders in your current directory, including hidden files.

    ```sh
    ls -l
    ```
    This command will list all the files and folders in your current directory with timestamps and file permissions. This can help you double-check if your file updated correctly or change the read-write-execute permissions for your files.

`mkdir`
: Make a directory

    ```sh
    mkdir dirname
    ```

    This command will make a directory within the current directory called
    `dirname`.

`rm`
: Remove a file

    ```sh
    rm file
    ```

    This command will remove file from the current directory. It will not
    work if `file` does not exist.

    ```sh
    rm -r dir
    ```

    This command will remove the `dir` directory recursively. In other
    words, it will delete all the files and directories in `dir` in addition
    to `dir` itself. **Be careful with this command!**

`cp`
: Copy a file

    ```sh
    cp lab01/original lab02/duplicate
    ```

    This command will copy the `original` file in the `lab01` directory and
    and create a `duplicate` copy in the `lab02` directory.

`mv`
: Move or rename a file

    ```sh
    mv lab01/original lab02/original
    ```

    This command moves `original` from `lab01` to `lab02`. Unlike `cp`, mv
    does not leave original in the `lab01` directory.

    ```sh
    mv lab01/original lab01/newname
    ```

    This command does not move the file but rather renames it from `original`
    to `newname`.


There are some other useful tricks when navigating on command line:

* Most terminals can autocomplete file and directory names for you with *tab completion*. When you have an incomplete name (for something that already exists), try pressing the <kbd>Tab</kbd> key for autocomplete or a list of possible names.

* If you want to retype the same instruction used recently, press the <kbd>Up</kbd> key on your keyboard until you see the correct instruction. This saves typing time if you are doing repetitive instructions (like running Java programs on command line while testing).

## Personal Computer Setup

Since all students will be working remotely, you will need to install Java and Git on your personal computer. Please follow the setup procedures below to install Java, Git, and any other operating system-specific requirements.

- [Windows Setup](#windows-setup)
- [MacOS Setup](#macos-setup)
- [Linux Setup](#linux-setup)

> If you are wondering why we are telling you to install Java 11, instead of the current / most recent version, Java 14, here is why. Java 11 is a LTS or Long Term Support release. This means that Java 11 will be officially supported through 2024, while Java 14 will only be supported until September 2020. The difference between the versions will be negligible for you in this class, so we opted for you to install the version that should last longer.

### Windows Setup ###

> You should only complete this section if you have a Windows computer, otherwise skip it.

#### Install Java ####
You will need to install the Java 11 JDK to compile and run code for this class. First head to the [AdoptOpenJDK Website](https://adoptopenjdk.net/?variant=openjdk11&jvmVariant=hotspot). You should select "OpenJDK 11 (LTS)" as the version and "HotSpot" as the JVM like below.

![java-windows](img2/java-windows.png "java-windows")

Once downloaded, run the installation file and follow the prompts to install Java onto your computer. **It is critically important to select the "Set JAVA_HOME variable" option in the custom setup screen** (this sets environment variables correctly so that you can run Java commands from Git Bash). Once selected it should look like the following image.

![open-jdk-features](img2/open-jdk-features.png "open-jdk-features")

After you make sure this has been selected go ahead and continue with the installation.

#### Install Python 3 ####
_Note: If you used python 3 for a previous class and were able to call python commands from Git Bash you can skip this step._

We will use python for some of the later more complicated projects later on. [Install python3](https://www.python.org/downloads/) at the following link. **In the "Advanced Options" section make sure that you select the "Add Python to environment variables" option.** Once selected the Window should look like this.

![python-path](img2/python-path.png "python-path")

For all other options, it is fine to go with the defaults and finish the installation. Once you have completed this jump down to the [test run section](#test-run).

### macOS Setup ###
> You should only complete this section if you have a Mac computer, otherwise skip it.

We will be using terminal to install the necessary software for this class using a package manager called Homebrew. First you need to run the following command to install Homebrew if you have do not already have it installed.

```sh
ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```

> During the process, you may be prompted to enter a password. When you enter your password, *nothing* will display on the terminal, but the computer is actually recording your password. This is a security measure. Just type your password and hit enter.

Once installed run the following three commands to update packages, install cask, and allow cask to look up versions

```sh
brew update
brew tap homebrew/cask
brew tap homebrew/cask-versions
```

Next by running the following three commands you should be able to install Java, Git, and Python 3.

```sh
brew cask install java11
brew install git
brew install python
```

Once you have completed this jump down to the [test run section](#test-run).

### Linux Setup ###
> You should only complete this section if you have a Linux computer, otherwise skip it.

There are many different distributions of Unix / Linux with different package manages (apt-get, yum, etc.), so these instructions might differ slightly than what you must run. These instructions should match exactly to what you would need to run on Ubuntu.

You should be able to install Java, Python 3, and Git. By running the following commands (all of these should be available from the default repositories).

```sh
sudo apt-get install openjdk-11-jdk python3 git
```

Once you have completed this jump down to the [test run section](#test-run).

### Test Run ###

Let's try running a Java program to try out your new setup. Again don't worry too much about what we are doing here, all of this will be explained in more detail later on. This is just meant as a sanity check before continuing. First we will make a temporary directory and navigate to it with the following command.

    mkdir -p ~/temp-cs61bl && cd ~/temp-cs61bl

Next running the following command will create a file `HelloWorld.java` on your computer which will print out the string "Hello World!".

    echo 'public class HelloWorld { public static void main(String[] args) { System.out.println("Hello world!"); } }' > HelloWorld.java

Now the real test can be completed as follows.

1. In your terminal enter `ls` (list files / folders in this directory). You should see `HelloWorld.java` listed.
1. Run the command `javac HelloWorld.java`. This should not generate any output, but when you run the command `ls` again you should now see both `HelloWorld.java` and `HelloWorld.class`. If this produced any output, there is likely something wrong with your setup and the rest of the test probably will not work.
1. Run the command `java HelloWorld`. This should print out "Hello world!" for you. If it did not, again there is likely something incorrect about your setup.
1. You can now delete the temp-cs61bl folder and its contents by running the following command. Be careful with this as the files will be permanently deleted (should not be a problem since this is a temporary directory we just made)

        cd .. && rm -rf ~/temp-cs61bl

If at any point during the test run you ran into problems you should revisit the corresponding instructions to make sure you followed them correctly. If you cannot resolve your problem try asking other students or your TA!

## GitHub and Beacon<sup>TM</sup>
We have a wonderful in-house system for centralizing your grades and student information called Beacon<sup>TM</sup> (copyright 2019, all rights reserved and patent pending).

We will also setup here our CS 61BL GitHub repository ("repo"), which you will need to submit all coding assignments.

1. Create an account at [GitHub.com](https://github.com/). If you already have an account, you do not need to create a new one.
1. Go to [the Beacon website](https://beacon.datastructur.es/register/) and you'll be guided through a few steps to complete your GitHub repository registration. Please follow them carefully! You'll need to be logged in to your Berkeley account to complete the Google Form quiz. If any errors occur while you're working through the steps, please let your TA know immediately.
1. After completing all of the steps, you should have received an email inviting you to collaborate on your course GitHub repository, accept the email invitation and you should be good to go. Hooray! **Don't follow the instructions that GitHub says you might want to do** -- instead, follow the instructions given later in this lab.

### More details about your repository
Your repository will have a name containing a number that is unique to you! For instance, if your repo is called "su20-s42",  you'll be able to visit your private repository at https://github.com/Berkeley-CS61B-Student/su20-s42 (when logged into GitHub).

Additionally, the instructors, TAs, and tutors will be able to view your repository. This means you can (and should!) link to your code when asking private debugging questions on Ed. No other students will be able to view your repository. As a reminder, you may not post code from this course publicly, even after completing the course. Doing so is a violation of our course policies and you might be subject to disciplinary action.

## Git

In this course, you'll be required to use the git version control system, which is wildly popular out in the real world. Unfortunately, the abstractions behind it are fairly tricky to understand, so don't be frazzled when you encounter significant frustration as you learn to use git.

Towards the middle of the semester, we'll be learning the inner workings of Git in much greater detail but, for now, let's just get a working knowledge of how to use git.

Before you proceed, **read sections up to the Remote Repositories section of the [Using Git Guide](../../guides/using-git)**.

> Do not proceed until you have read sections up to the **Remote Repositories** section of the **[Using Git Guide](../../guides/using-git)**. You do not need to read past that.

### Git Exercise

Now that you've read the first 3 sections of the [Using Git Guide](../../guides/using-git), you're ready to start using git! As part of your lab checkoff, you will be working through a small git workflow by setting up a git repository and making a couple commits to the repository. An academic intern or staff member will look at your git repository during checkoff to ensure that it is in a good state.

If you need help with creating directories, creating files, changing directories, etc., refer back to *[Learn to Use the Terminal](#essential-terminal-commands)*.

> If at some point during this exercise Git prompts you to update your username and email, now is a good time to [change your git name and email][].

[Change your Git name and email]: #change-your-git-name-and-email

1. Create a directory called `lab01-checkoff`. You can put this directory anywhere on your computer (unless you have already cloned your su20-\*\*\* repository, in which case, you **should not put this directory inside of your su20-\*\*\* repo)**.
1. Move into the `lab01-checkoff` directory, and initialize a git repository in this directory.
1. Create a file called `61b.txt` in any way you'd like. In this text file, add the text "61b version 1" into it.
1. Create another file called `61bl.txt` in any way you'd like. In this text file, add the text "61bl version 1" into it.
1. Begin tracking **only** `61b.txt`, and create a new commit containing just this file, with the following commit message: "Add 61b.txt".
1. Make a modification in `61b.txt` by changing the text in the file to: "61b changed to version 2".
1. Make another commit, this time containing both `61b.txt` and `61bl.txt`. The commit message should be: "Update 61b.txt and add 61bl.txt".
1. Finally, make one more modification to `61b.txt` by changing the text in the file to: "61b changed to version 3". Don’t commit this version.

At this point, if you were to type in `git status`, something like this should show:

![Git Exercise Status](img/exercise-status.png)

Also, if you were to run `git log`, something like this should show:

![Git Exercise Log](img/exercise-log.png)

Be sure to save this repository and directory until you get checked-off by a lab assistant.

Along with other short conceptual questions involving git, you will be asked to revert `61b.txt` back to the version in the most recent commit, as well as back to the earliest version of the file, so make sure you know how to do this!

> **Hint**: Look into the `checkout` command. But be careful when using the `checkout` command, as your repo might end up in an unexpected state. **For this exercise make sure to always specify a file (or directory) when you use checkout.**
>
> Specifically, if you see something about your repository being in a detached HEAD state as a result of a checkout command, that is something we don't want. Read the [git-WTFS][] guide for more on what it is and how to fix it.

[git-WTFS]: {{ site.baseurl }}/guides/git-wtfs#head-detached-at-

### Git & Remote Repos

First, read the **Remote Repositories** section of the **[Using Git Guide](../../guides/using-git#remote-repositories)**.

In this course, you'll be required to submit your code using Git to your course GitHub repository that you should have created above. This is for several reasons:

* To spare you the incredible agony of losing your files.
* To submit your work for grading and to get results back from the autograder.
* To save you from the tremendous anguish of making unknown changes to your files that break everything.
* To ensure that we have easy access to your code so that we can help if you're stuck.
* **To dissuade you from posting your solutions on the web in a public GitHub repository**. This is a major violation of course policy!
* To expose you to a realistic workflow that is common on every major project you'll ever work on in the future.
* To enable safer, more equitable partner collaborations.

### Setting up your Git Repository

#### Clone your `su20-***` git repository.

Navigate to the spot in your folders on your computer that you'd like to start your repository. In the example below, we're assuming you want all your stuff in a folder named `cs61bl`, but you can pick a different name if you'd like.

> Your terminal might use a different prompt than `$`. **Type out or paste only the part after the prompt.**

```sh
$ cd cs61bl
```

Enter the following command to clone your GitHub repo. Make sure to replace the
`***` with your class repository number.

```sh
$ git clone https://github.com/Berkeley-CS61B-Student/su20-***.git
```

> After cloning your terminal will report "warning: You appear to have cloned an empty repository." This is not an issue, it is just Git letting you know that there are no files there which in our case is what we should expect.

If you'd like to setup SSH access so you don't need to type in your GitHub
password every time you use your repository, feel free to follow the relevant
instructions on GitHub instead. If you don't know what that means, just
use the command above.

> If you use macOS and you've previously logged into GitHub, your computer may have remembered your username and password from before. If it's not the same login information as you're using now, you may need to [update keychain credentials][].

[update keychain credentials]: https://help.github.com/articles/updating-credentials-from-the-osx-keychain/

Move into your newly created repo!

```sh
$ cd su20-***
```

Now we will add the `skeleton` remote repository. You will pull from this remote repository to get starter code for assignments. (Make sure that you are within the newly created repository folder when the continue with these commands.) Enter the following command to add the `skeleton` remote.

```sh
$ git remote add skeleton https://github.com/cs61bl/skeleton-su20.git
```

Listing the remotes should now show both the `origin` and `skeleton` remotes.

```sh
$ git remote -v
```

> If you see an error like `fatal: not a git repository` make sure you have properly moved into the `cs61bl` directory.

#### Change your Git name and email

Every time a commit is made, your name and email address is recorded as part of the commit. Change your Git name and email by running the following commands, substituting your own name and email address where appropriate.

```sh
$ git config --global user.name "<your name>"
$ git config --global user.email "<your email>"
```

#### Change your Git text editor

We'll also change the text editor associated with Git. Sometimes, Git needs your help when inputting things like commit messages, so it will open a text editor for you. We recommend using Atom or Sublime Text, though you're welcome to use your preferred text editor. Follow the instructions [here](https://help.github.com/articles/associating-text-editors-with-git) and make sure that you follow the correct instructions for your operating system.

### Working on the Skeleton

You must now pull from the `skeleton` remote in order to get the starter code for this lab. You will also do this when new projects and assignments are released (or if there is ever an update to an assignment). To do this, use the spookiest command in the whole git toolbox.

```sh
$ git pull skeleton master
```

What this does is fetch all remote files from the repo named `skeleton` (which is located at `https://github.com/cs61bl/skeleton-su20.git`) and copy them into your current folder.

> If you get an error similar to `fatal: refusing to merge unrelated histories`, you can fix this by, for this time only, running:
> ```sh
> $ git pull --rebase --allow-unrelated-histories skeleton master
> ```

If you list the files in your current directory, you'll see that there is now one folder: `lab01`.  Look in the `lab01` folder and you'll see files called `LeapYear.java` and `magic_word.txt` that you'll work with in later parts of this lab.

Now we will show you what submitting files will be like using Git! Open the file `magic_word.txt` in whatever your favorite text editor is (both you and your partner should do this). Discuss with your partner what your favorite flavor of ice cream is, then edit your `magic_word.txt` file to contain your partner's favorite flavor (this is not the magic word you need to get credit).

Now stage and commit `magic_word.txt`.

```sh
$ git add lab01/magic_word.txt
$ git commit -m "My partner's favorite ice cream!"
```

Finally push these changes to the `master` branch on the `origin` remote repo.

```sh
$ git push origin master
```

You can verify that this was successful by checking your repository online on Github's website.

### Checkoff: Git Exercise

You will need to get your git exercise checked off by a staff member or academic intern. We will be using our online office hours queue for this, and the online queue will also be what will be used in all labs going forward to get help from staff members. To add yourself to the queue navigate to (oh.datastructur.es)[https://oh.datastructur.es]. The instructions should be given there, but if you are unsure how to do this feel free to ask in your lab's zoom call.

A staff member or academic intern, upon completion of the lab checkoff, will tell you what to put into the `magic_word.txt` file in order to pass the autograder. If there's a wait, feel free to move on until your name is called.

## Java

Java is sometimes called a "compiled language" while Python is called an "interpreted language". Neither of these terms, however, make any sense yet. What is true is that in most Java implementations, programs are compiled (translated into a form that is more easily executed by computers) in a separate, user-visible step from being executed, while most Python implementations give users the impression that the actual programs that they write are executed directly. These are not properties of the languages, however; Java can be interpreted and Python can be compiled.  But as often happens, even in CS, people get sloppy in their terminology and fail to distinguish between programming languages and their implementations.

The Java implementations we use compile Java _source code_ (what is written by the programmer, typically in `*.java` files) into Java class files containing _virtual byte code_, which may then be executed by a separate program.  (Often, this separate program, called `java`, does a mix of interpreting the class file and compiling it into machine code and then having the bare hardware execute it.)

Let's see an example. Here is the "Hello World" program again in Java. Don't worry about understanding it for now - we'll deconstruct it later in this lab.

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
```

Here is what the corresponding compiled Java code (called _bytecode_) looks like. The Java virtual machine can interpret this to run the program.

![Java bytecode](img/bytecode.png)

### Why Compilation?

At this point, you may be wondering why Java is (usually) compiled. Compilers are quite helpful for several reasons:

1. They can check for errors prior to _runtime_ (program execution). The Java compiler will catch and report errors like:

    - type errors, which can be produced by giving functions the wrong objects as parameters (like a `String` instead of a `int`)
    - syntax errors, which can be caused by forgetting syntactical elements like parentheses or braces

    Catching these and many other types of errors prior to runtime helps to prevent many of the possible bugs caused by programmer error, making Java programs more stable before they are run.

2. Compilers can help speed up programs. Programs run by interpreters can be slow because interpreters must parse text that is understandable to humans and translate it into an executable form. Furthermore, for various engineering reasons, this executable form is generally not actual machine code (directly executable by the hardware), but some other intermediate form that another program (the interpreter) then executes. A compiler does this translation work once and saves the instructions to a file variously called a _binary_, _object file_, or (in the case of Java) a _class file_. As such, Java programs do not have to translate the code at runtime, decreasing the overall runtime of the code.

There are many other reasons some languages have compilers, some of which you will learn by taking CS 61C. But for now, you will just need to know how to compile and run your Java program.

### Compiling Java Programs

There are many different [Java compilers](http://en.wikipedia.org/wiki/Java_compiler), but we'll be using `javac` for command line in this class. `javac` is included in the Java Development Kit (JDK) and should be accessible to you now if you setup your computer correctly.

Let's pretend you have a Java file called `BaconEggs.java`. To compile `BaconEggs.java`, you would type the following command into your terminal:

```sh
$ javac BaconEggs.java
```

> Every time you make changes to your Java source code, you will need to save and recompile it in order for the changes to have effect at runtime (think about why that is so).

### Running Java Programs

Compiling your program using the command above should generate `.class` files. For example, let's pretend that you've compiled `BaconEggs.java`. This would generate a new file called `BaconEggs.class`.

If you were to open this `.class` file in a text editor, you'd see something like the bytecode in the image earlier in this lab. Instead, you'll typically use the Java bytecode interpreter to run the class file. We could invoke the Java bytecode interpreter on our new class file by typing the following command in a terminal:

    $ java BaconEggs

This would begin execution of the program, but you do not type `BaconEggs.class`. If you ever try to run a `java <class name>` command without a `<class name>.class` file it will cause an error message like this:

    Error: Could not find or load main class

### Writing Java Programs

#### Java Is Object-Oriented

Java is an _object-oriented_ programming (OOP) language. What this means is that you'll organize your programs around the _types of data_ that it manipulates. Each of these data types describes a class of _objects_ and how these objects will interact with each other. Those of you who took 61A may recognize that term as having been applied to Python, but Java takes OOP a step further. In Java, all functions (or _methods_, as the OOP inventors renamed them in order to make-believe they were inventing an entirely new concept) and all variables reside in some class definition.

#### Format of a Java Program

Every Java source file contains a class, interface, or "enum" (a special kind of class). For now, let's just discuss _class definitions_. A class definition provides the name of the class and serves as a template for objects. In addition, a class definition contains _variables_ and _methods_ that define the behavior of that class.

Here is a deconstruction of the aforementioned "Hello World" program:

![HelloWorld](img/HelloWorld.png "HelloWorld")

A Java program consists of a collection of one of more of these Java files. At least one of the classes in a complete Java program must contain a method called `main` having the header shown in the `HelloWorld` code above. This _main method_ is where execution of your program begins.

This is why running the `HelloWorld` program prints out `Hello world!`. The `main` method in the `HelloWorld` class is being run when you type `java HelloWorld` into the terminal.

## Exercise: Leap Year

In the `lab01` folder, you should see a file called `LeapYear.java`. This program is supposed to test whether or not a given year is a leap year. The user will give a year as a command line parameter (examples given below), and then print out whether or not that year is a leap year, e.g.

```sh
$ java LeapYear 2000
2000 is a leap year.
$ java LeapYear 1999
1999 is not a leap year.
$ java LeapYear 2004
2004 is a leap year.
$ java LeapYear 2100
2100 is not a leap year.
```

A leap year is either:

- divisible by 400 **or**
- divisible by 4 and not by 100.

For example, 2000 and 2004 are leap years. 1900, 2003, and 2100 are not leap
years.

To implement this you will have to implement the `isLeapYear` function in the file `LeapYear.java`. You can open your code in a text editor of your choice. You should be replacing the `// TODO` with your solution. Some additional guidelines for style and tips for Java can be found below.

### Style

In this course, we'll work hard to try to keep our code readable. Some of the
most important features of good coding style are:

Consistent style
: Spacing, variable naming, brace style, etc.

Size
: Lines that are not too wide, source files that are not too long.

Descriptive naming
: As applied to variables, functions, classes. We prefer meaningful names like `year` or `getUserName` instead of `x` or `f`.

Avoidance of repetitive code
: Strive to never have two significant blocks of code that are nearly identical except for a few changes. We'll teach you a number of large-scale design and small-scale style techniques for working around these situations.

Comments where appropriate
: Line comments in Java use the `//` delimiter. Block (or multi-line comments)comments use `/*` and `*/`. Make sure to update the Javadoc comment ("Update this comment...") and provide a short description of the method's behavior.

In addition, **if you used any external resources, please cite it in the Javadoc comment using the `@source` tag like in the example above.** It's not necessary to cite anything from the course staff, but you should cite any other resources like StackOverflow by pasting in a link.

We have a more formal [style guide][] available for you to read at your own leisure, but the golden rule is this: **Write your code so that it is easy for a stranger to understand.**

[style guide]: {{ site.baseurl }}/guides/style-guide

### Java Tips

- The `&&` operator computes the boolean `and` of two expressions, while `||` computes boolean `or`.
- The `%` operator implements remainder. Thus, the value of `year % 4` will be `0`, `1`, `2`, or `3`.
- The `!=` operator compares two values for inequality. The code fragment `if (year % 4 != 0)` reads as "if the remainder when dividing year by 4 is not equal to 0."
- When one of the arguments of the `+` operator is a String, the arguments are concatenated as Strings. For example, `2 + "horse"` + `"battery"` would return `"2horsebattery"`. This is different from Python which doesn't perform this automatic conversion.

### General Git Workflow: Saving Your Work ###

As you are making changes to your code, it is good practice to save your work often. We have briefly discussed the commands, but now we will explain how they should be used in practice. In the case that you ever want to go back to another version of your code, it is better to have more options to roll back to. The next set of instructions will talk you through Git's version of saving work through snapshots of your file system called commits.

1. After you have made some changes to the code within our local repository, Git will take notice of these changes. To get a report of the current state of your local repository use the command `git status`. Run this and try to interpret the results. Do they make sense to you or do they match your intuition? It is helpful to run this before running other git commands so you know what the state of things are.

1. If we want to save work that we have completed on a file, we must first stage the file and then we can commit it. Staging a file is achieved with the command `git add`; this does not save the file but it marks it to be saved the next time you commit. The two below commands show what saving work looks like in a git repository. For `git add` depending on what directory you are in, the path to the file you are adding might differ (hint: use `git status` if it's not clear). Additionally the `-m "Completed Year.java"` part of the commit command specifies a message to be attached to this snapshot. It is useful to have descriptive messages in case you ever do need to revert to a previous version.

    ```sh
    $ git add lab01/LeapYear.java
    $ git commit -m "lab01: Completed LeapYear.java"
    ```

1. Once again, we want to push these changes to the Github repository so that your changes can be seen by us and graded. Your changes will also be available to `pull` if you had your repo initialized in other places or other computers.

    ```sh
    $ git push origin master
    ```

Get into the habit of saving your files and doing the `git commit` step _often_ (i.e. every 15 minutes). It can save your skin when you mess things up, since it allows you to back out of changes and to see what you have changed recently.

Basically, right when you sit down to work in your repository, first `git pull` to make sure you are starting with the most recent code. While you are working, frequently commit. When you are finished, `git push` so all your changes are uploaded and ready for you to pull again next time. 

## Discussion: Compilation Errors

Throughout this lab, you may have run into some errors like the one below after running `javac LeapYear.java`.

```
LeapYear.java:3: error: incompatible types: int cannot be converted to boolean
        if (year) {

1 error
```

This is a **compilation error** because the compiler, `javac`, is able to identify ('catch') the problem before we even run the code with `java`!

Discuss these questions with your partner:

- What kinds of bugs is the compiler able to catch? What do you suspect is going on behind the scenes?
- What kinds of bugs is it unable to catch?
- And how is this similar or different to other languages you may have used before, like Python, Scheme, MATLAB, etc?

## Submission

The last step is to submit your work with [Gradescope][]. Gradescope is the site that you'll use to submit lab and project assignments.

> We added everyone's CalCentral email to Gradescope on the first day of labs. Make sure to login using the email address listed on CalCentral.
>
> If you're having trouble accessing your [Gradescope][] account or would like to use a different email address, ask your TA!

[Gradescope]: https://gradescope.com

Select the Lab 1: Java & Git assignment on Gradescope. Choose your `su20-***` repository and use the `master` branch.

![Github Repo and Branch Selection](img/github-repo-and-branch-selection.png)

Please report any issues you may have to [Ed][]. Entire error messages and/or screenshots are welcome.

Today and even when partnerships are locked down, everybody must submit to Gradescope to receive credit (both you and your partner). In the case of a partnership, you may have the same code and you can even submit from the same repository, but you both are responsible for making your own submission before the deadline.

> As above, **we strongly encourage you to make frequent commits!** Lack of proper version control will not be considered an excuse for lost work, particularly after the first week.

## Recap

Java is a **compiled** language. You can use `javac` and `java` to compile and run your code. Our first programs reveal several important syntax features of Java:

- All code lives inside a class.
- The code that is executed is inside a function, a.k.a. method, called `main`.
- Curly braces are used to denote the beginning and end of a section of code, e.g. a class or method declaration.
- Statements end with semi-colons.
- Variables have declared types, also called their “static type”.
- Variables must be declared before use.
- Functions must have a return type. If a function does not return anything, we use void,
- The compiler ensures type consistency. If types are inconsistent, the program will not compile.

> Java is also statically-typed. **Static typing** gives us a number of important advantages over languages without static typing:
>
> - Types are checked before the program is even run, allowing developers to catch type errors with ease.
> - If you write a program and distribute the compiled version, it is (mostly) guaranteed to be free of any type errors. This makes your code more reliable.
> - Every variable, parameter, and function has a declared type, making it easier for a programmer to understand and reason about code.
>
> There are downside of static typing, to be discussed later.

We also discussed briefly how to comment code and coding style. Coding style is very important in this course and in the real world. Code should be appropriately commented as described in the textbook and lectures. We haven't
learned what good style is yet, nor when to use comments.

**Git** is a version control system that tracks the history of a set of files in the form of commits. Commit often and use informative commit messages so it's easy for the future-you to find what the change you're looking for. Pull from the `skeleton` remote repository to get or update starter code for assignments and use Gradescope to submit labs and projects.

If you haven't had a chance to work on the [optional Java introduction][] yet,
now's a good time! This will help the transition to programming in Java smoother over the next couple of labs.

[optional Java introduction]: {{ site.baseurl }}/java

### Deliverables

There are two required file:

`LeapYear.java`
: You should have completed the `isLeapYear` method in this file.

`magic_word.txt`
: You should have received the correct magic word from completing the git checkoff.

Congratulations on finishing your first CS 61BL lab!
