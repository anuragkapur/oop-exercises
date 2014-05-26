Problem Statement
=================
Write a small API that can be used to look up information about movie actors in a simple directory that is stored as a file of comma-separated values. The separated values are:

    <name>,<actor ID>,<height>,<date of birth>
 

An example directory file:

     Sandra Bullock,1001,1.71,26 July 1964
     Jeff Bridges,1002,1.85,4 December 1949
     Mia Wasikowska,1003,1.62,14 October 1989
     Johnny Depp,1004,1.78,9 June 1963
     Jeff Bridges,1005,1.73,4 October 1974
 

The last line is NOT a mistake; Jeff Bridges and Jeff Bridges are two different people.

Please write a library that will let me do the following:

* Look up all of an actor's information by name.
* Look up all of an actor's information by ID.
* Add the information of an actor
* Update the information of an actor.