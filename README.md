# Hexagon Architecture

The main concern of a _Hexagonal Architecture_ is that all dependencies between the different components 
**always** point inwards to the domain objects.

A _hexagonal_ application is build up domain objects and use-cases that operate on these domain 
objects. The _ports_ provide an interface towards the outside world.

### Domain Objects
Domain Objects are the main objects of a _Hexagonal_ application. Domain objects can contain both state and behaviour.
To have easy code to understand, maintain (and reason about), the behavior should be as close as possible to the state.

Domain Objects have **no** dependencies to the any other layer (no outward dependency). Due to this, 
changes in other layers won't have any effect to the domain objects.

### Use Cases

Use Cases are a description of what users are doing with the application. A use case is a class 
that handles everything around a certain use case. The Use Cases contain all business logic and 
and business rule validations that are specific for that particular use case and thus cannot be 
implemented in the domain objects. 

Same as for the domain objects, use cases don't have any dependency to outward components. If use cases 
need something from the outside, output port are used.

### Output ports

An output port is a simple interface that can be called by the use cases (ie. database access). The interface is designed 
to fit the needs of the use case. It's implemented by an outside component called an output/driver adapter. 
This is an implementation of the _Dependency Inversion Principle_. 

### Output/Driven Adapters

Adapters interact with the core of the application. _Output/Driven adapters_ are called by the use cases. The adapters can 
for example provide data from a database to the application. The adapter implements one ore more ports.
