# WSI Coding Challenge

## Problem:
Given a collection of 5-digit ZIP code ranges (each range includes both their upper and lower bounds), provide an algorithm that produces
the minimum number of ranges required to represent the same restrictions as the input.

## Approach:
Merge the ranges by the following approach:
1. Sort the given ranges by the ZipCode from(lower bound)
2. Loop over the ranges and using Stack to merge the overlap ZipCode ranges  

## Technologies:
1. Java 8: Streams, Lambdas
2. JUnit 4.12
3. SonarQube

## Examples
1. No overlap   
Input: [94133,94133] [94200,94299] [94600,94699]   
Output: [94133,94133] [94200,94299] [94600,94699]

2. Overlap  
Input: [94133,94133] [94200,94299] [94226,94399]    
Output: [94133,94133] [94200,94399]