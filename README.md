# AquaHood
Simulation des déplacements d'un ban de poissons
Matheus & Arthur


# Java Project: Fish School Movement Simulation 

The goal of this project is to create a software model to simulate the movements of a school of fish. The model will follow these rules:

- Fish move in a torus (donut-shaped) space.
- Each fish has a limited vision of its surroundings in terms of distance (diameter) and angle. These parameters are the same for all fish.
- A fish's movement depends on its initial speed and the other fish in its vision range:
  - A fish doesn't want to collide with its peers. This is represented by a repulsion force exerted by each fish in its field of view. This force is arbitrarily defined as inversely proportional to the distance between the two fish.
  - Despite this, a fish seeks to stay close to others. Cohesion is maintained by a force towards the average position of the visible fish. This force's intensity is proportional to the distance between the fish and this average position.
  - The fish matches its speed with that of the surrounding fish. In effect, this means applying a force proportional to the difference between the speed vector and the average speed vector of the visible fish.

The initial part of the project will be dedicated to system modelling, developing a non-graphical version of the simulation (which will simply display the list of fish and their status for a given time step), and testing. 

Later on, a more or less sophisticated graphical interface will be developed.
