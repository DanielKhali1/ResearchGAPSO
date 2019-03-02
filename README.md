# PSO Hybridization with GA

## Introduction ##

We are hoping to find a faster method of searching through a solution space by using a hybridization of Particle Swarm Optimization and Genetic Algorithms. In viewing the difference between both the strengths and weaknesses of both algorithms we have hypothesized that combining the two may overcome the other's faults. This application using both algorithms together, could potentially find better solutions faster, and more efficiently then just using Genetic Algorithms, or Particle Swarm Optimization alone.

## Genetic Algorithm ##

### Algorithm ###

A Genetic Algorithm is a process that emulates the steps of natural selection to generate accurate 
solutions.

from [geeks for geeks](https://www.geeksforgeeks.org/genetic-algorithms/)

1. Randomly initialize populations of move sets
2. Determine fitness of each move set
3. Until Solution Reached repeat:
    1. Select parents from population
    2. Crossover and generate new population
    3. Perform mutation on new population
    4. Calculate fitness for new population
    
<img src="pics/GArepresentation1.1.gif" width = "400" height = "350" align="right">
     
### Population ###

- Our Population of individuals will consist of binary strings. these binary strings

### Calculate Fitness ###

Every time a gene attempts to move onto a space occupied by noise a point will be added 
to the fitness.

If  the  chromosome  moves  on  top  of  the  end  tile  in  less  moves  then  the  size  of  the 
chromosome, each move after the index of string that reached the goal will be subtracted 
from the fitness

### Selection of Parents: ###

#### Tournament Process: ####
   - Tournament will select 2 random individuals from the population and pick the one which has the lowest fitness
   - Tournament will happen twice, to find two fit parents for cross over
   
### Crossover: ###
- Create a cross over point by generating a random integer in-between 1 and the length of the chromosome
- Use that number as a crossover point 
- create a new individual using 2 parents taken from Selection of Parents, by splitting the first and second parent from the index of the crossover point

<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/56/OnePointCrossover.svg/462px-OnePointCrossover.svg.png">

  
### Perform Mutation: ###
- Mutation  will  occur  based  on  a  specific  rate,  and  will  change  one  random  gene  in  the 
chromosome 
<img src="https://cdn-images-1.medium.com/max/1600/1*CGt_UhRqCjIDb7dqycmOAg.png">



## Particle Swarm Optimization ##


![gaPSOGif](pics/gaPSO.gif)
