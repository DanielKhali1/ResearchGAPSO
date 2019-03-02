# PSO Hybridization with GA

## Introduction ##

We are hoping to find a faster method of searching through a solution space by using a hybridization of Particle Swarm Optimization and Genetic Algorithms. In viewing the difference between both the strengths and weaknesses of both algorithms we have hypothesized that combining the two may overcome the other's faults. This application using both algorithms together, could potentially find better solutions faster, and more efficiently then just using Genetic Algorithms, or Particle Swarm Optimization alone.

## Test Functions ##
To test the hypothesis that a Particle Swarm Optimization hybridization with Genetic Algorithms would be a viable option, a decision was made to test these two optimization algorithms on a few different 2 dimensional functions. The goal for the algorithm would to be search these 2 dimensional solution spaces and find the global minimum value.

## Genetic Algorithm ##

### The Algorithm ###

A Genetic Algorithm is a process that emulates the steps of natural selection to generate accurate 
solutions.

from [geeks for geeks](https://www.geeksforgeeks.org/genetic-algorithms/)

1. initialize a population of random binary strings
2. Determine fitness of each individual in that population
3. Until Solution Reached repeat:
    1. Select parents from population
    2. Crossover and generate new population
    3. Perform mutation on new population
    4. Calculate fitness for new population
    
<img src="pics/GArepresentation1.1.gif" width = "400" height = "350" align="right">
     
### Population ###

- Our Population of individuals will consist of binary strings. these binary strings will be converted later on into decimal values that will represent x before we plug them into a function to find y.

### Calculate Fitness ###

- To calculate the fitness of an individual that individual will be converted into a decimal value be plugged and be plugged into the selected function.
- the fitness value of each individual is the y value from the function that has been selected.
- During selection the lower the y value (fitness) the more likely that individual will be used in crossover.

### Selection of Parents: ###

   - There are few different ways selection can be implemented, in this project we will be using tournament process, although the selection process should in function just provide a way for selecting new parents for the new generation by prioritizing individuals with a more desired fitness. (in this project's case the lower fitness the better)

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

## Deceptive Cases ##

One of the setbacks that Genetic Algorithms run into, is the ability to be decieved. Deceptive problems can ruin the chance of finding an accurate solution.

For  example,  (using  this  graphic)  if 
we were to use a GA to find the local 
minimum  of  this  line  it  would  go  to 
the first deep valley it see’s and not 
move to the next. The reason for this 
is    because    of    how    the    fitness 
function   works.   Each   generation 
every individual gets tested to see if 
they  have  a  high  fitness.  If  they  do 
they  have  a  greater  chance  of  having  their  genes  passed  on.  Unfortunately  any 
chromosomes  that  try  and  climb  the  giant  curve  to  get  the  global  minimum  will 
have lower fitness values. So they don’t have a high chance of being put into the 
next generation making it very hard for them to pass on their genes. To get to the 
global minimum.


## Particle Swarm Optimization ##


![gaPSOGif](pics/gaPSO.gif)
