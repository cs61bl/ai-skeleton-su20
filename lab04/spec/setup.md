---
layout: page
title: "Lab 3 Setup: Setting Up IntelliJ"
tags: [Lab, IntelliJ]
released: true
---

## Prerequisites
1. Java 11 - You finished [Lab 1 setup](../lab01/setup).
2. You have successfully created your local repo for the class on your own
   machine. This is the `su19-s***` directory you created and set up in [Lab 1](../lab01).
3. You have pulled from skeleton master, and you have a `lab03setup` directory in
   the same directory as your `lab01` and `library-su19` folders.
4. Be mentally prepared to use a real world software development package. It
   will seem very complicated for a while, but we'll lead you down the narrow
   path to success. **Ask for help if you are stuck!** It can be very hard to
   guess the right thing to do in IntelliJ.


## Installing IntelliJ

If you're working on the lab computers, skip this step.

1. You'll need to install the Community Edition of IntelliJ from the
   [JetBrains](https://www.jetbrains.com/idea/download/) website.

2. After selecting the appropriate version for your OS (Mac, Windows, or
   Linux), click download and wait a few minutes for the file to finish
   downloading.

3. Run the install file and follow the prompts to install IntelliJ onto your
   computer. You can stick to all the default settings. Feel free to install
   IdeaVim if you're a vim user.


## Installing the IntelliJ CS 61B Plugins

Begin the setup process by starting up IntelliJ. If you're on one of the lab
computers, use the command `/share/instsww/bin/idea.sh` in a terminal window to
start IntelliJ. Then follow the steps below.

**Make sure you're running IntelliJ Version 2018.3 or later before continuing.**

1. In the *Welcome* window, click the **Configure &rarr; Plugin** button on the
   bottom right of the window.
   ![Configure Plugin](./img2/plugin_setup1.png)

2. In the window that appears, click "Marketplace" and enter "CS 61B" in the
   search bar at the top. The CS 61B plugin entry should appear.
   If you click the autocomplete suggestion, a slightly different window
   from what is shown below may appear -- this is okay.

3. Click the green **Install** button, and wait for the plugin to download and install.
   ![Search CS 61B](./img2/plugin_setup2.png)

4. Now, search for "Java Visualizer", and click the green **Install** button to
   install the plugin.
   ![Search Java Visualizer](./img2/plugin_setup3.png)

5. Click the green **Restart IDE** button to finalize the installation.
   ![Search Java Visualizer](./img2/plugin_setup4.png)

For more information on using the plugins, read [the plugin
guide](../../guides/plugin.html#using-the-plugins). You don't have to read this
right now.

**Only move on once you have installed *both* plugins.**


## Project Setup
IntelliJ is an IDE (Integrated Development Environment). It includes a text
editor a whole lot of extra features to make writing code easier.
In order to run your files in this special environment where we can work our
IDE magic, we need to import our files into a project, similar to how you might
import images or clips into a project for a program like iMovie or
Windows Movie Maker.  Fortunately, this is a fairly painless process.

These instructions apply for both initial setup and for future assignments. When
you run `git pull skeleton master` to retrieve a new assignment and you notice
that you have a new assignment directory (tomorrow, you'll have `lab04/`),
simply run through these steps again from 1 to 8. This will likely involve
pressing next for all steps and, if IntelliJ asks you to overwrite various
housekeeping files (such as `.iml` files) because they already exist, respond
"Yes" or "Overwite" to those popup windows. This is so IntelliJ can
automatically mark the new directories for your assignment to work with IntelliJ
for you as opposed to you manually marking directories as source folders and/or
modules.

Begin the setup process by starting up IntelliJ. If you're on one of the lab
computers, use the command `/share/instsww/bin/idea.sh` in a terminal window to
start IntelliJ. Then follow the steps below.

1. Upon opening IntelliJ, click on the "Import Project" option. _Do not use the "Open" option in this course,
   IntelliJ will not run your java files!_
	![IntelliJ Start Menu](img3/intellij_start_menu.png)

2. Find and choose your lab03setup directory, then press the OK button. From here
   on out, you should be able to simply select next for every screen but to be
   safe in the face of shenanigans, more screenshots follow. **If you keep
   clicking next without paying attention and get to a point where you see a
   message that says No SDK specified, stop and consult step 8!** If you're on Windows or Linux, you may get a different window corresponding to your operating system (the image below is from macOS).
   ![IntelliJ Select Folder](img3/select_folder.png)

3. Make sure "Create project from existing sources" is selected and press next.
   You shouldn't have to change anything at this step.
    ![Import Project](img3/import_project1.png)

4. Leave these fields untouched and press next.
    ![Project Name](img3/project_name.png)

5. Do nothing here and press next. For context, IntelliJ is automagically detecting what your Java files are and self-configuring itself to edit and run them.
    ![Import Sources](img3/import_sources.png)

6. You may not actually see this next window pop up. If it does, click next. If
   it doesn't, that's fine.
    ![Import Sources](img3/import_lib.png)

7. You may not actually see this next window pop up. If it does, click next. If
   it doesn't, that's fine.
    ![Import Sources](img3/module_config.png)

8. You may not actually see this next window pop up. If it does, and you see 11
   on the left sidebar, you're in the clear and can simply press next then click
   finish on the final screen and voila, your project is set up and you can skip
   steps 9 and 10! If you do NOT see 11 (or whatever version of Java you have installed), continue on to steps 9 and 10.
    ![Select SDK](img3/select_sdk.png)

9. Click the plus in the upper left corner and click JDK from the drop-down menu
    ![Add JDK](img3/add_jdk.png)

10. Find where your JDK is saved, select it, and press the OK button. It's highly likely that IntelliJ already found the folder for you (it'll be called `Home`) and set that as the default selection. If that is the case, just press 'OK'.

    Otherwise, on my Mac, it was at `/Library/Java/JavaVirtualMachines/jdk-11.0.1.jdk/Contents/Home`.

    You can run `/usr/libexec/java_home` on your terminal to find out:

    ```sh
    $ /usr/libexec/java_home
    /Library/Java/JavaVirtualMachines/jdk-11.0.1.jdk/Contents/Home
    ```

    If you're on Windows, your path may look like `C:\Program
    Files\Java\jdk11.0.1`. If you're on a lab computer, it should be at
    `/usr/lib/jvm/java-10-oracle/`. Once this window closes and your screen looks
    like the image at step 8, press next, then finish, and you're done!

    ![Select JDK](img3/select_jdk.png)

Once you've pressed finish, you should see something really similar to the
following image. You may need to click the little triangle next to `lab03setup`
in the top left to get the source files (`Dog.java` and `DogTest.java`) for
`lab03setup` to show up. If you don't see the sidebar, go to **View &rarr; Tool
Windows &rarr; Project**, or select **"Project"** on the left toolbar.
![Select JDK](img3/main_screen_when_done.png)

> The first time you start up IntelliJ it's going to spend some time indexing
files. This may take a few minutes. There should be a little progress bar in the
bottom right. Once it's done, a sidebar will appear.


## Getting Java Libraries
Remember the empty `library-su19` folder? We are going to start by populating
that folder with the Java libraries we need for this class.

1. First, open up a terminal window and `cd` into your `s**` repository.
2. Run
```sh
git submodule update --init
```
You should get output like this:
```sh
Submodule 'library-su19' (https://github.com/Berkeley-CS61B/library-su19) registered for path './'
Cloning into '/Users/eli/Downloads/su19-s1/library-su19'...
Submodule path './': checked out '1fbc26d69f044b48346849989aeeff9761b00abf'
```
3. Ta-da! You now have libraries!
```sh
$ ls library-su19/
data/
javalib/
```
Below is shown the directory structure of `library-su19`. Look inside the folder and make sure you see the seven .jar files listed below. If you're using your operating system's file explorer the 'jar' part might not show up in the filenames, and that's OK.
```
library-su19/
└── javalib
    ├── algs4.jar
    ├── hamcrest-core-1.3.jar
    ├── jh61b.jar
    ├── junit-4.12.jar
    ├── stdlib-package.jar
    ├── stdlib.jar
    └── xchart-3.5.1.jar
```

## Importing Libraries and Running Code
Double check on `DogTest.java`. You should see that some of the words in the
file are red, specifically `Test` and `assertEquals`. If you mouse over them,
you'll see a message along the lines of "cannot resolve symbol". The trouble is
that we haven't told IntelliJ where to find the CS61B libraries we just pulled.

Click **File &rarr; Project Structure** in the top left of IntelliJ. A window should
pop up, and you should click **SDKs** on the left panel of this window. When
you've done so, it'll look as shown below:
![add libraries step 1](img3/add_libs1.png)

Click the "plus" button in the bottom-middle of this window, and navigate to the
`javalib` folder (which is in `library-su19`). Select all of the `*.jar` files
in this folder (use shift-click to select multiple files) and press 'Open' or similar.
![add libraries step 2](img3/add_libs2.png)

Press OK a few times, and you'll find yourself looking at DogTest.java again.
This time, the red text should be gone.

Try running the code by clicking **Run &rarr; Run**, as shown below.
![run -> run](img3/run_run.png)

This will probably pop up a very small dialog window like the one shown.
Basically IntelliJ is saying that it isn't quite sure what you mean by running
the program and is giving you two choices: 0. is to edit the configuration
before running the program (which we won't do). 2. is to run the DogTest class,
which is what we want.
![run -> run dialog](img3/run_dialog.png)

Click on DogTest and a green bar should appear with the message "Tests passed: 2 of 2 tests" as shown below.
	![tests passed](img3/tests_passed.png)

You'll notice after running your code that the green play and green bug icons in
the upper right are now green; this is because when you clicked "2.", IntelliJ
memorized what you meant by Run for this project and you can now click this
button to run your program. You'll learn more about this over time as we use
more advanced features of IntelliJ.

## Embedded Terminal (Optional)

IntelliJ has the cool feature that you can have a working terminal in the
workspace so you don't have to constantly switch between having IntelliJ and
your terminal, if that becomes necessary for whatever reason.

For Mac users, you should be able to skip this setup section. Windows users will
likely have to put in a little leg work. This setup assumes you are a Windows
user and you have Git Bash installed.

First, go to File &rarr; Settings (Or use Ctrl + Alt + S)
![Settings](img4/intellij_settings.png)

Type in "terminal" in the search bar. Once there, type in:

`"C:\Program Files\Git\bin\sh.exe" --login -i`

into the "Shell path" field. Click ok.

![Terminal](img4/terminal_settings_window.png)

To test if you've properly set this up, click on the button that says "Terminal"
on the bottom toolbar of the main IntelliJ window. The bottom third of your
screen should now be a terminal, the equivalent of having Git Bash right there.

![Terminal Test](img4/select_terminal.png)

Try typing something in! If you're able to run basic commands like `ls` or `cd`
or `echo 'Hello world'` you've done it!
