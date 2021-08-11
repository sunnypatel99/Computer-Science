# CSCI 1302 - GenList ADT v2020.sp

![Approved for: Fall 2020](https://img.shields.io/badge/Approved%20for-Fall%202020-blueviolet)

![Trains are like linked lists...](https://i.imgur.com/MdPMTcO.png)

This document contains the description for the Generic List API 
project assigned to the students in the **Fall 2020** CSCI 1302 classes
at the University of Georgia.

## Deadline Options

There are different deadline options for this project. Students who perform their
final submission via the `submit` command before the date/times listed below
automatically receive the associated Submission-Based (SB) extra credit. The late 
penalty does not start applying until after the final date listed. 

* **SUN 2020-10-25 (Oct 25) @ 11:55 PM EST (`+20` SB Extra Credit)**
* **WED 2020-10-28 (Oct 28) @ 11:55 PM EST (`+10` SB Extra Credit)**
* **THU 2020-10-29 (Oct 29) @ 11:55 PM EST (`+0` SB Extra Credit)**

## Table of Contents

* [Course-Specific Learning Outcomes](#course-specific-learning-outcomes)
* [Academic Honesty](#academic-honesty)
* [Updates](#updates)
* [Project Description](#project-description)
* [Project Requirements & Grading](#project-requirements--grading)
  * [Functional Requirements](#functional-requirements)
  * [Non-Functional Requirements](#non-functional-requirements)
  * [Absolute Requirements](#absolute-requirements)
* [How to Download the Project](#how-to-download-the-project)
* [Submission Instructions](#submission-instructions)
* [Appendix - FAQ](#appendix---faq)

## Course-Specific Learning Outcomes

* **LO1.c:** Create and modify text files and source code using a powerful terminal-based text editor such as Emacs or Vi.
* **LO1.d:** Use shell commands to compile new and existing software solutions that are organized into multi-level 
  packages and have external dependencies.
* **LO2.a:** (Optional) Identify redundancy in a set of classes and interfaces, then refactor using inheritance and polymorphism 
  to emphasize code reuse.
* **LO2.b:** Define, throw, and propagate exceptions appropriately in a software solution.
* **LO2.d:** Implement new generic methods, interfaces, and classes in a software solution.
* **LO2.e:** Utilize existing generic methods, interfaces, and classes in a software solution.
* **LO3.a:** Create and update source code that adheres to established style guidelines.
* **LO3.b:** Create class, interface, method, and inline documentation that satisfies a set of requirements.
* **LO4.a:** (Partial) Design, create and use interfaces in a software solution.
* **LO4.b:** Utilize interface-based polymorphism in a software solution.
* **LO7.c:** (Partial) Use common abstract data types and structures, including lists, queues, arrays, and stacks in solving 
  typical problems.
  
## Academic Honesty

You agree to the Academic Honesty policy as outlined in the course syllabus. 
In accordance with this notice, I must caution you **not** to 
fork this repository on GitHub if you have an account. Doing so will more than
likely make your copy of the project publicly visible. Please follow the 
instructions contained in the 
[How to Download the Project](#how-to-download-the-project)
section below in order to do your development on Odin. Furthermore, you must adhere
to the copyright notice and licensing information at the bottom of this document.

## Updates

* **2020-10-12-MON:** Added a project extension; the new due dates are listed
  under [Deadline Options](#deadline-options) near the beginning of this document.
* **2020-10-21-WED:** Added another project extension due to scheduled maintenance; 
  the new due dates are listed under [Deadline Options](#deadline-options) near the 
  beginning of this document.

If there has been an update and you have already cloned the project to Odin, 
then you can update your copy of the project using the <code>$ git pull</code>
command while inside of your project directory.

## Project Description

After implementing the `StringList` interface a couple different ways, your project
manager discussed the finished product with the client. Well, it turns out that the
client was only using strings as "an example" of what they wanted. In reality, the
client wanted a generic list implementation that will work with any type. Without 
breaking a sweat, your project manager assigned this new, updated task to you.

In this project, you are tasked with implementing a generic list interface `GenList<T>`
that also provides some additional functionality. Your implementation must use a linked 
list as the internal storage for the list. Each node of the linked list should contain a 
generic type object along with a pointer to another node of the same type. The provided 
JAR file does not contain the generic node class. However, you are welcome to use any code you 
created in a class exercise as long as you can explain any part of the code, if asked 
to do so.

For this project, you will *NOT* have access to the `.java` files for the
interface. Instead, you will have access to the generated API documentation
for the interface [here](http://csweb.cs.uga.edu/~mec/cs1302-genlistadt-doc/index.html)
as well as a `.jar` file containing the compiled version of the interface..
Implementors should make sure that each method functions or behaves as described
by the interface's API documentation, except in cases where a functional requirement 
changes the behavior of the method. Additionally, a testing script will not be provided. It will
be up to you to write the test code for this project. Feel free to use the `listadt-tester`
from project 2 for example scenarios you might want to test.

**WARNING:** Pay close attention to the API documentation for each and every method. While some 
methods are new for this project, there are methods that existed in the previous list project that
are now _genericized_. Some such method may also have updated API documentation. For example, the 
`add` method that takes another list as a parameter is now required to handle self-reference.
There are also methods that were optional / extra credit in the previous project that
are now mandatory (e.g., the `iterator` method).

Implementors are always free to implement additional methods in addition
to the ones defined by the interface. However, they should not assume that
users (e.g., graders) will use them (even if declared with `public` visibility), 
since they are not defined in the interface. In other words, a driver class should
not be required to call any methods that aren't given in the interface in order to 
fully test the implementation of the interface. Any additional methods you write may 
help avoid redundancy and promote code reuse.

### Suggested Reading

* [API Documentation for `GenList<T>`](http://csweb.cs.uga.edu/~mec/cs1302-genlistadt-doc/index.html)

## Project Requirements & Grading

This assignment is worth 100 points. The lowest possible grade is 0, and the 
highest possible grade is 120 based on the date of your last submission
(see [Deadline Options](#deadline-options)).

### Functional Requirements

A functional requirement is *added* to your point total if satisfied.
There will be no partial credit for any of the requirements that simply 
require the presence of a method related to a particular functionality. 
The actual functionality is tested using test cases.

While the specific details are listed below, the following diagram 
illustrates the _potential_ general relationship between your classes 
and the interface (with instance variables, constructors, and methods omitted 
from the diagram for brevity):

![Sample UML Diagram](img/genlistadt-uml.png)

#### `LinkedGenList<T>`

* **`LinkedGenList<T>`:** Create the `cs1302.genlist.LinkedGenList<T>` 
  class such that it either directly implements the `cs1302.genlistadt.GenList<T>` interface 
  or indirectly by extending an [optional abstract parent class](#basegenlistt-optional).
  Additional requirements are listed below. 

  * The FQN for this generic class is `cs1302.genlist.LinkedGenList`. 
  
  * You must explicitly define and document all constructors required by
    the interface API documentation.
    
  * The `iterator` method is requried for this project.
    You may implement it in this class `LinkedGenList<T>` class or 
    in the [optional abstract parent class](#basegenlistt-optional).
	
  * There is a requirement related to this class's storage included
    in the [Absolute Requirements](#absolute-requirements) section.
	
  * The bulk of this functional requirement will be graded
    based on unit tests. This is the same as someone using 
    the classes you wrote based purely on the interface definitions. 
    If you implement the interface correctly, then you should
    pass the associated test cases. 
    <!--
    **Examples of small unit tests can be found in the included `test` directory**, 
    with some very basic instructions provided in [`test/README.md`](test/README.md).
    //-->
    
  * **You are NOT permitted to share test code for this project.** However, if you wrote
    your own test code for the `StringList` project, then you are free to personally
    adapt that code to test your generic implementation. Remember,
    if you decide to introduce additional `.java` files into your project,
    then they are expected to fulfill all non-functional and absolute requirements, 
    even if the main parts of the project do not use them. 
    
    <!-- Furthermore. if you adapt
    test code originally authored by someone else, then please include the following
    snippet in any relevant Javadoc comments:
    
    ```java
     * <p>
     * This is based on test code originally authored by FALL_2020_STUDENT_NAME, 
     * which was provided by the official Piazza post
     * <a href="https://piazza.com/class/k4x23al2zg1266?cid=474">here</a>.
     ```
     -->
     
#### `BaseGenList<T>` (optional)

* **`BaseGenList<T>`:** You may optionally create an abstract parent class called
  `cs1302.genlist.BaseGenList<T>` class such that it properly implements the 
  `cs1302.genlistadt.GenList<T>` interface. If you choose to create and use this
  class, then the expectation is that it will be extended by your `LinkedGenList<T>`
  class. Students who created an abstract parent class for the `StringList` project
  may find this class useful. Additional requirements are listed below. 
  
  * The FQN for this generic abstract class is `cs1302.genlist.BaseGenList`. 
    
  * The `iterator` method is requried for this project. You may implement it in
    this class `BaseGenList<T>` class or in derived child classes.
	
  * There is a requirement related to this class's storage included
    in the [Absolute Requirements](#absolute-requirements) section.
  
#### `LinkedGenListTest`

* **(13 points) `LinkedGenListTest`:** Create the `cs1302.genlist.LinkedGenListTest` class
  to demo proper use of some of the more interesting methods provided by the
  interface using your `LinkedGenList<T>` class. In this class, you are required to
  create a set of static methods that each demo some calls to a particular `GenList<T>` method
  in a meaningful way **using multiple lambda expressions**. Each demo method needs to
  involve at least two different parameterizations of `GenList<T>` (i.e., at least two
  different replacements for `T`). For each parameterization of `GenList<T>` within a
  demo method, you should use a unique lambda expression applicable to the type
  parameterization and interface method that you are testing. You need one of these 
  static demo methods for each of the following `GenList<T>` methods:
  
  | Points | Static Method | List Method | Example of Non-Trivial |
  |--------|---------------|-------------|------------------------|
  | **3** | `demoMap` | [`<R> GenList<R> map(Function<T,R> f)`](http://csweb.cs.uga.edu/~mec/cs1302-genlistadt-doc/cs1302/genlistadt/GenList.html#map-java.util.function.Function-) | Transform an element's value and change its type. |
  | **3** | `demoReduce` | [`T reduce(T start, BinaryOperator<T> f)`](http://csweb.cs.uga.edu/~mec/cs1302-genlistadt-doc/cs1302/genlistadt/GenList.html#reduce-T-java.util.function.BinaryOperator-) | Concatenation, addition, squaring, multiplying, etc. is okay. |
  | **3** | `demoFilter` | [`GenList<T> filter(Predicate<T> p)`](http://csweb.cs.uga.edu/~mec/cs1302-genlistadt-doc/cs1302/genlistadt/GenList.html#filter-java.util.function.Predicate-) | Make use of a compound boolean expression.
  | **2** | `demoMin` | [`T min(Comparator<T> c)`](http://csweb.cs.uga.edu/~mec/cs1302-genlistadt-doc/cs1302/genlistadt/GenList.html#min-java.util.Comparator-) | Use an ordering based on parts/digits of the elements instead of the entire elements. |
  | **2** | `demoAllMatch` | [`boolean allMatch(Predicate<T> p)`](http://csweb.cs.uga.edu/~mec/cs1302-genlistadt-doc/cs1302/genlistadt/GenList.html#allMatch-java.util.function.Predicate-) | Make use of a compound boolean expression. |
  
  * **What is meaningful?** You need to make the code, documentation, and the printout clear such 
    that anyone who is reading it can understand what is going on. The scenarios
    that you demo should not be trivial -- see examples above. In most cases, this will involve using 
    some of your other list methods in conjunction with the ones that are requred 
    above.
    
  * **Do I need a `main` method?** You should create a `main` method that calls each of the
    demo functions. Please make sure that your program output is legible and easily understood
    by someone who is not familiar with your code.
    
  * **Helper Methods?** You may find it useful to create a separate method for each parameterization 
    and have the main demo method call these helper methods. 
  
### Non-Functional Requirements

A non-functional requirement is *subtracted* from your point total if
not satisfied. In order to emphasize the importance of these requirements,
non-compliance results in the full point amount being subtracted from your
point total. That is, they are all or nothing. 
  
* **(0 points) [RECOMMENDED] No Static Variables:** Use of static variables
  is not appropriate for this assignment. However, static constants 
  and static utility and/or test methods are perfectly fine.
  
* **(20 points) Code Style Guidelines:** You should be consistent with the style 
  aspect of your code in order to promote readability. Every `.java` file that
  you include as part of your submission for this project must be in valid style 
  as defined in the [CS1302 Code Style Guide](https://github.com/cs1302uga/cs1302-styleguide).
  All of the individual code style guidelines listed in that document are part 
  of this single non-functional requirement. Like the other non-functional
  requirements, this requirement is all or nothing. Please use the `checkstyle` 
  program on a regular basis to check your code for compliance.  

* **In-line Documentation (10 points):** Code blocks should be adequately documented
  using in-line comments. This is especially necessary when a block of code
  is not immediately understood by a reader (e.g., yourself or the grader).

### Absolute Requirements

An absolute requirement is similar to a non-functional requirement, except that violating
it will result in an immediate zero for the assignment. In many cases, a violation
will prevent the graders from evaluating your functional requirements. No attempts will be
made to modify your submission to evaluate other requirements.

* **Project Directory Structure:** The location of the default
  package for the source code should be a direct subdirectory of 
  `cs1302-genlistadt` called `src`. When the project is compiled, 
  the `-d` option should be used with `javac` to make the default package 
  for compiled code a direct subdirectory of `cs1302-genlistadt` 
  called `bin`. **Any additional classes that you create should
  be located in the `cs1302.genlist` package.**
  
  If you follow this structure, then you might type the following to compile 
  your code, assuming you are in the top-level project 
  directory `cs1302-genlistadt`:
  
  ```
  $ javac -Xlint -d bin src/cs1302/genlist/YourNodeClassName.java
  $ javac -Xlint -cp lib/genlistadt.jar -d bin src/cs1302/genlist/YourIteratorClassName.java
  $ javac -Xlint -cp bin:lib/genlistadt.jar -d bin src/cs1302/genlist/BaseGenList.java
  $ javac -Xlint -cp bin:lib/genlistadt.jar -d bin src/cs1302/genlist/LinkedGenList.java
  $ javac -Xlint -cp bin:lib/genlistadt.jar -d bin src/cs1302/genlist/LinkedGenListTest.java
  ```
  
  The `-Xlint` option is used to display more information about warnings; the 
  [Development Environment](#dev-env) absolute requirement states that your code should
  compile with no errors or warnings. If your iterator class relies on types other 
  than the interface, then you may need to also include `bin` in the class path 
  when compiling that class.
  
  Remember, when you compile `.java` files individually, there might be 
  dependencies between the files. In such cases, the order in which you
  compile the code matters. Also, if more than one default package is needed
  (e.g., `genlistadt.jar` and some other directory like `bin`), then a colon `:` 
  can be used to separate each path in a list of multiple paths supplied
  to `-cp`. For an example, see 
  ["Setting the Classpath"](https://github.com/cs1302uga/cs1302-tutorials/blob/master/packages.md#setting-the-class-path) 
  in the package tutorial.

* __Development Environment:__ <a id="dev-env"/>This project must be implemented 
  in Java 11, and it *must compile and run* correctly on Odin using the specific
  version of Java 11 that is setup according to the instructions provided
  by your instructor. For this requirement, the term *compile* should be interpreted as 
  *compile with no errors or warnings*.
  
  If you decide to introduce additional `.java` files into your project,
  then they are expected to fulfill all non-functional and absolute requirements, 
  even if the main parts of the project do not use them. You may assume
  graders will compile your source code in an order that satisfies
  compilation dependencies. **You should remove any `.java` files that you
  do not need before submission.** 
  
* **`cs1302.genlist.LinkedGenList<T>` Storage Requirement:**
  You must use a sequence of generic node (or container) objects
  for this class's storage. The `Node` class is not provided for you.
  You will need to make your own as a separate generic class. 
  If you use Java's `java.util.LinkedList` class or something similar, then 
  that will result in an immediate violation of this absolute requirement, 
  regardless of any use of any `Node` objects elsewhere in the class.
  This requirement also prohibits any use of third-party implementations 
  of list or list-like interfaces. You may not use arrays in the
  `LinkedGenList<T>` class anywhere except in the `toArray` method --
  this also prohibits the use of `toArray` elsewhere in the class.
  
* **`cs1302.genlist.BaseGenList<T>` Storage Requirement:**
  You may not use arrays in the `BaseGenList<T>` class anywhere 
  except in the `toArray` method -- this also prohibits the use 
  of `toArray` elsewhere in the class.
  
* **No Stream API:**
  You are not allowed to use built-in Java streams to implement any
  part of this project. Use of any class that implements
  `java.util.stream.Stream<T>` is prohibited. If you use Java's stream
  interface or something similar, then that will result in an immediate
  violation of this absolute requirement.

### Grading

This project will be graded using unit tests, none of which will be made 
available before the project deadline. Graders will also inspect your implementation
of `LinkedGenListTest` to ensure proper use of lambda expressions.

## How to Download the Project

On Odin, execute the following terminal command in order to download the project
files into sub-directory within your present working directory:

```
$ git clone --depth 1 https://github.com/cs1302uga/cs1302-genlistadt.git
```

This should create a directory called `cs1302-genlistadt` in
your present working directory that contains a clone of the 
project's respository. Take a look around.

If any updates to the project files are announced by your instructor, you can
merge those changes into your copy by changing into your project's directory
on Odin and issuing the following terminal command:

```
$ git pull
```

If you have any problems with these download procedures, then please contact
your instructor.

## Submission Instructions

You will be submitting your project via Odin before the deadline indicated
near the top of this document. Make sure your project files
are on `odin.cs.uga.edu`. Change into the parent directory of your
project directory. If you've followed the instructions provided in this document, 
then the name of your project directory is likely `cs1302-listadt`. 
While in your project's parent directory, execute the following command: 

```
$ submit cs1302-genlistadt csci-1302
```

If you have any problems submitting your project then please send a private
post to your instructor via the course Piazza as soon as possible. However, 
creating a post about something like this the day or night the project is due 
is probably not the best idea.

# Appendix - FAQ

Below are some frequently asked questions related to this project.

1. **Name Clash? Two methods have the same erasure, yet neither overrides the other?**

   _Background:_ When the Java compiler is tasked with compiling a generic class or 
   interface, it performs a series of internal steps, known as 
   [**_type erasure_**](https://docs.oracle.com/javase/tutorial/java/generics/erasure.html) 
   to ensure that a single compiled class or interface is produced. Contrast this with other 
   languages, like C++, where a generic-like templated class may result in multiple compiled 
   classes. 
   
   _Likely Culprit:_ Your generic class is likely declared as implementing the raw type
   of generic interface. Implementing the raw type leads to one set of abstract methods 
   coming from the interface that don't quite match up with the methods that you may have
   declared in your class. Since it's two different sets of methods (with the same names), 
   the compiler thinks you're tying to write method overloads, however, 
   [this won't work because those overloads erase to the same type](https://docs.oracle.com/javase/tutorial/java/generics/restrictions.html#cannotOverload).
   
   To fix the issue, simply parameterize the interface in your class
   declaration. While it is not always the case, the type parameter that you declared for
   your class is often used in the interface parameterization. 
   
   
   Here is a general, project-independent example:
   
   ```java
   public class MyClass<T> implements GenericInterface {         // BAD; implements raw type!
   ```
   
   ```java
   public class MyClass<T> implements GenericInterface<String> { // OK; implements GenericInterface<String>
   ```
   
   ```java
   public class MyClass<T> implements GenericInterface<T> {      // OK implements GenericInterface<T>
   ```
   
   In the last example, when `T` gets replaced when the class is used, it's replaced everywhere, including
   in the the `implements` clause.
   
1. **What do I need to write for my Javadoc comments?** <a id="faq-javadoc-what"/>

   Obviously, all methods that you create should be documented with Javadoc comments that
   satisfy the Code Style Guidelines for this project. For helper methods that you create,
   this means writing documentation that would be helpful for someone who is using the method, but
   who does not necessarily have access to its internals.

   For methods that are being overridden from a parent class or interface, 
   **you should NOT manually copy the entire comment and parameter details from the API website.**
   Instead, include a summary sentence and `{@inheritDoc}` to make it clear to readers of the source
   code that your intent is to inherit the documentation. An example of this can be found in the
   style guide, [here](https://github.com/cs1302uga/cs1302-styleguide#missingjavadocmethod).
   If you think that users of your method need more information than what is provided in the
   parent documentation, then you should add more.
   
1. **Why doesn't `{@inheritDoc}` seem to work (and other Javadoc-related questions)?** 

   It doesn't work because the `javadoc` tool requires the source code in order to automatically
   pull the text of comments from supertypes when applicable. **We did not provide you with the
   source code for the interface,** so this is working as intended. **However,** you can use the
   `javadoc1302` command (only available on Odin) along with the `--GenList.java` option
   instead of the usual `javadoc` command to give the Javadoc tool access to the source code 
   it needs to inherit the documentation.
   
   An example of the `javadoc1302` command is provided below; see the note below the example if you 
   have problems running the command. The `USUAL_JAVADOC_OPTIONS_HERE` part might be replaced with 
   options like `-d doc`, `-sourcepath src`, `-subpackages cs1302`, etc., as usual. Here is the
   example:
   
   ```
   $ javadoc1302 --GenList.java \
     USUAL_JAVADOC_OPTIONS_HERE \
     -classpath lib/genlistadt.jar \
     -link https://docs.oracle.com/en/java/javase/11/docs/api/
   ```
   
   **NOTE:** The command presented above is a mult-line command since it's so long. There is a single
   space before the `\` at the end of the first two lines. When typing this out, you should type a
   single space followed by `\`, then immediately press your `RET` key to continue to the next line.
   If typed correctly, you will see a `>` on the next line and you can continue typing the command.
   The `\` and `>` characters will NOT be part of the command when you do your final press of the 
   `RET` key. 
   
   **SUGGESTION:** **Do NOT manually copy the entire comment and parameter details from the API website.**
   Instead, include a summary sentence and `{@inheritDoc}` to make it clear to readers of the source
   code that your intent is to inherit the documentation. An example of this can be found in the
   style guide, [here](https://github.com/cs1302uga/cs1302-styleguide#missingjavadocmethod).
   
Have a question? Please post it on the course Piazza.

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
