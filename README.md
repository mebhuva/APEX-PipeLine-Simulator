# CSX42: Assignment 1
## Name: Mehul Bhuva

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in coursesRegistration/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile coursesRegistration/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile coursesRegistration/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile coursesRegistration/src/build.xml run -Darg0=student_coursePref.txt -Darg1=courseInfo.txt -Darg2=registration_results.txt

Note: Arguments accept the absolute path of the files.


-----------------------------------------------------------------------
## Description:

Flow of the project

1. Driver code excepts 3 arguments
2. first two arguments should be two input files student_coursePrefs.txt and courseInfo.txt
3. third argument should be the name of output file registration_results.txt
4. Driver code than pass the argument to the fileprocessor which checks if file exists in the current directory or not
5. If file found then function fileexists print file found otherwise not found
6. Driver code then calls two function readstudent data and coursedata which returns hashmap of student data and course list
7. Driver sends this objects to the scheduler and scheduler process it and allocate the courses and return student data
8. Driver calls result using stdoutinterface and filedisplyinterface to print allocated course data on console and output file respectively

Scheduler Algorithm:-

1. Scheduler algorith iterates through hashmap of studentdetails 
2. It checcks every student object in three passes
3. First pass we check if preferences have any E or F courses in it then put it in wait queue
4. Otherwise allocate first course to the students also check if course capacity is not full
5. Second and third pass check for the time between courses and if courses are not allocated more than three for each student

Time complexity :- O(n * n) 

## References : 
1. https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/    (To sort the hashmap using comparator)
2. https://stackoverflow.com/questions/4716503/reading-a-plain-text-file-in-java  (To read File using Scanner)

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: 09/23/2018


