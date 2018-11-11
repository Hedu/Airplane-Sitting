AIRLINE SITTING

This program tries to maximize customer satisfaction when assigning seats on the plane.

There are 2 main groups of entities:
- Passenger entities:
    - Passenger
    - Group of passengers
    - Waiting list of groups

- Airplane entitites:
    - Airplane
    - Rows of seats
    - Seats

All the entities were created using interfaces and classes in order to decouple implementations.
I used factory classes in order to "inject dependencies"

There are 2 big classes responsible of the logic of the application:
- File processor: this class reads the file and turn that plain data into program entities.
    I've used builder objects with this class because:
    - I wanted this class to create the airplane and the waiting list.
    - I wanted those entities to be inmutable.
    - I didn't want this class to be responsible of choosing the implementation

- SeatOrchestator: this class contains the algorithm that tries to maximize customer satisfaction.
    - I've focused on keep groups together more than assigning windows.
    - I try always to fit the best place, this is why I created SeatsMatch Object, which count the extra seats and
    windows in order to fullfill the customers demands, but also to avoid wasting resources.

It's a gradle project, so it can be easily be run or imported on an IDE. It also contains the gradle wrapper.
