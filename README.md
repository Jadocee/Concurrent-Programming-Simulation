# Concurrent Programming Simulation

This project simulates various scenarios that require concurrent programming and synchronization techniques. It is based
on an assignment given during my university course and has been structured to offer solutions to a set of specific
problems.

## Overview

- **Problem 1**: Simulates the challenge faced by farmers from the North and South islands of New Zealand when using a
  single-lane bridge to transport produce.
- **Problem 2**: Represents an ice-cream parlour at Shortland Student Hub with peculiar seating rules based on the
  current
  customers.
- **Problem 3**: A continuation of Problem 2 with a solution using a monitor.

The Java classes `P1.java`, `P2.java`, and `P3.java` represent the solutions to the respective problems. The relevant
classes for each problem are kept at the root of the `src` directory per the assignment specification.

## Getting Started

### Dependencies

- Java 11 or higher

### Compilation

1. Navigate to the `src` directory of the project.

    ```bash
    cd .../ConcurrentProgrammingSimulator/src
    ```

2. Compile the relevant programs.

    ```bash
    javac P1.java P2.java P3.java
    ```

### Execution

The programs for each problem can be executed using either of the following commands:

```bash
java P1 P1-1.txt
```

```bash
java P2 P2-1.txt
```
    
```bash 
java P3 P2-1.txt
```

The argument passed to each program is the name of the file to which the output will be written. The commands above
use the provided sample input files [`P1-1.txt`](./P1-1.txt) and [`P2-1.txt`](./P2-1.txt), and can be replaced with 
another file that follows the same format.

## Problems Addressed

- **Sharing the Bridge**: Farmers from North and South islands use a bridge to deliver produce. Deadlocks must be
  avoided
  using semaphores and threads. Neon signs indicate the number of crossings.
- **Ice-cream Parlour**: A parlour with limited seating where customers must adhere to specific seating rules. The entry
  and
  exit of customers must be managed using semaphores.
- **Monitoring the Ice-cream Parlour**: A solution to Problem 2 using a monitor.

Refer to the [assignment specification](./assignment.pdf) for more details.

## Project Structure

```
ConcurrentProgrammingSimulator/
├── src/
│   ├── AbstractParlour.java
│   ├── AbstractTmer.java
│   ├── Bridge.java
│   ├── Customerjava
│   ├── Farmer.java
│   ├── FarmerFactory.java
│   ├── MonitorParlour.java
│   ├── MonitorTmer.java
│   ├── P1.java
│   ├── P2.java
│   ├── P3.java
│   ├── ParlourSimulation.java
│   ├── SemaphoreParlour.java
│   └── SemaphoreTmer.java
├── assignment.pdf
├── README.md
├── P1-1.txt
└── P2-1.txt
```

## Acknowledgements

This project was completed as part of the course [COMP2240](https://www.newcastle.edu.au/course/COMP2240).
