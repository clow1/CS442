# CS442: Assignment 3
## Name: Crystal Low

-----------------------------------------------------------------------
-----------------------------------------------------------------------

####Command: 

To build:
ant -buildfile loadbalancer/src/build.xml 
To run:
ant -buildfile loadbalancer/src/build.xml run -Darg0="$(path)/input1.txt" -Darg1="$(path)/outpu1.txt" 

where $(path) is equal to the absolute path of where this assignment is located.
**********OR*************
Use the Makefile included in the directory this README is.

To build:
make build

To run:
make run

To clean:
make clean

:::IMPORTANT::: 
Change the constant named path to the destination of all the text files.
Everytime the text files are moved, change the path variable within the Makefile
to match its new location. Change the text file names inside the Makefile too.

-----------------------------------------------------------------------
## Description:

Implemented the observer design. FileProcessor parses through the file
which is then passed to the subject each time. There is a switch statement in
FileProcessor process(.) method.

I think there is something off with the removeService method. Contrary the
sample output, the last command is parsed and something is causing it
to state that that the service is Inactive as opposed to being
Invalid.

-----------------------------------------------------------------------
##Note:

Used 2 slackdays.

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Crystal Low
Date: [11/4/2019]


