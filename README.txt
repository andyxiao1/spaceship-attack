=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: andyxiao
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. Collections
  
  2. File I/O

  3. ​Inheritance/Subtyping for Dynamic Dispatch

  4. Testable component

=========================
=: Your Implementation :=
=========================

TODO:
- make simple game screen
    - user ship on bottom moves L/R
    - projectiles moves from top down
    - game status bar on top w/ score, health, pause?
- hiearchy - current thoughts
    - FlyingObj - abstract class that allows obj to moves and collide
    - Attackable interface/abstract class - extends FlyingObj, has health, does damage on collide //dont neeed???
    - Shootable - shoots lasers 
    - SpaceShip - user ship that shoots, has health
    - Enemy - enemy ships that shoot, have health
    - Asteroid - rock in space that does dmg on collide
    - Coin - doesn't do damage, adds one // dont think about coins for simple game implementation
    - Laser - high velocity, does dmg on collide
- simple game
    - list of all FlyingObj to be deployed
    - list of current FlyingObj on screen
    - user ship
    - currently every 2 seconds 2 obj from deployed to on screen

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?



========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
