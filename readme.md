# COMP2240 Assignment 2

## How To Run This Program

### Compile

```ps
javac P1.java P2.java P3.java
```

### Run

```ps
java P1 P1-1.txt
java P2 P2-1.txt
java P3 P2-1.txt
```

## Problems

Each program addresses the respective problem outlined below. These problems were provided and assessed as part of one of my courses at university; [COMP2240](https://www.newcastle.edu.au/course/COMP2240)

### Problem 1: Sharing The Bridge

A new single lane bridge is constructed to connect the North Island to the South Island of New Zealand.
Farmers from each island use the bridge to deliver produce to the other island, return to their island and
this is repeated indefinitely. It takes a farmer carrying produce 20 steps to cross the bridge. Once a farmer (say
a North Island farmer identified as `N_Farmer1`) crosses the bridge (from North Island to South Island) they
just attempts to cross the bridge in the opposite direction (from South Island to North Island) and so on. Note that the ID of the farmer does NOT change.

The bridge can become deadlocked if a northbound and a southbound farmer are on the bridge at the same time (New Zealand farmers are stubborn and will not back up). The bridge has a large neon sign above it indicating the number of farmers that have crossed it in either direction. The neon sign counts multiple crossing by the same farmer.

Using **semaphores**, design and implement an algorithm that prevents deadlock. Use threads to simulate multiple/**concurrent** farmers and assume that the stream of farmers are constantly attempting to use the bridge from either direction. Your program should input parameters at runtime to initialise the number of farmers from each direction. For example `[N=5, S=5]` would indicate a constant stream of 5 farmers from each direction wanting to use the bridge. You also make sure that the solution is starvation-free (the situation in which northbound farmers prevent southbound farmers from using the bridge, or vice versa should not occur).

Add **20 ms** delay between every 5 steps of the farmers take.

You should **terminate the problem after NEON = 100**.

### Problem 2: Ice-Cream Parlour

A new ice-cream parlour has been opened at Shortland Student Hub. The parlour does not offer take away service but there is only five seats in the parlour to eat in. A customer may arrive to the parlour at any time and may take a certain time to finish his ice-cream. The manager servers the customers in the order they arrive but adds a peculiar rule to it. If a customer arrives when there is an empty seat, then the customer can immediately take a seat. However, if all the five seats are occupied (i.e. there are five customers enjoying their ice-creams at any instant) then all the arriving customers have to wait for the entire party (all current customers) to leave before they can get their seats. 

Using **semaphores**, design and implement an algorithm that manages the customers entering and leaving the ice-cream parlour in line with manager’s rules. Use threads to simulate multiple/concurrent customers. Your solution must be fair - starvation free. Assume no time is wasted in taking seat, serving/starting eating ice-cream and leaving the parlour.

### Problem 3: Monitoring The Ice-Cream Parlour

You will need to implement a solution for '[Problem 2: (Ice-Cream Parlour)](#problem-2-ice-cream-parlour)' using monitors.

Using **monitors**, design and implement an algorithm that manages the customers entering and leaving the ice-cream parlour in line with manager’s rules. Use **threads** to simulate multiple/**concurrent** customers. Your solution must be fair and starvation free, i.e., the customers will be served in the order of their arrival time and no customer should be waiting for indefinite time. Assume no time is wasted in taking seat, serving/starting eating meals and leaving the parlour.
