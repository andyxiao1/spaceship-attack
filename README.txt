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
        I store the user's lasers and projectiles on display in a List. In the tick()
        function, I move all the projectiles and update the collection accordingly.
        When a projectile hits the end it is removed from the List. This is
        appropriate because I need to iterate through the projectiles to move
        them and an arraylist has easy access to each element. I also use a queue
        to store the projectiles to be displayed which has easy remove access. I also
        have a List in HighScoreKeeper to maintain the highscores and read and 
        write to that list.
  2. File I/O
        I store the levels in text files and use the LevelReader class to
        read in the file and add projectiles to the projectiles to be displayed
        queue accordingly. I use a novel format in the level text file that and
        read from it using the format in the LevelReader function. I also
        use file I/O to read and write the highscores. I use the HighScoreKeeper
        to read and write the files and also use a novel format with these
        text files. This is an appropriate use because state will be saved
        even when the game is closed.
  3. ​Inheritance/Subtyping for Dynamic Dispatch
        I have a hierarchy with every object on the screen being a FlyingObj.
        I extend of that with the user SpaceShip class and everything else falls
        under the CollisionProjectie class. This class is abstract and has
        a method hitShip() which does something to the ship when it collides
        with it. Four classes extend off CollisionProjectile which are Coin,
        Laser, EnemyShip, and Asteroid. Coin overrides hitShip() to add coins
        to the ship instead of dealing damage. When iterating through the
        List of projectiles on screen (which is a List of CollisionProjectiles),
        if the projectile collides with the ship their hitShip() method will be 
        called the dynamic dispatch will ensure the correct method is called.
        I also have a Damageable interface and the classes Asteroid, EnemyShip,
        and SpaceShip extend off that. These classes have damage and can
        take damage, so when CollisionProjectile hits it they take damage.
        I check if any Laser the user's lasers List hits a Damageable type
        and call the takeDamage() function of that object. This is needed
        to prevent a lot of rewritting code and the need to check what type
        every projectile is and act based of that. Instead, the hitShip()
        or takeDamage() method is called and polymorphism will ensure the right
        method is called.
  4. Testable component
        I tested the game model to ensure that the various methods work.
        These include the hitShip(), takeDamage(), isDead(), etc.
        This is needed to ensure my game functions correctly and follows the
        invariants I dictate.

=========================
=: Your Implementation :=
=========================
    
- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
        - FlyingObj: abstract class representing anything that flies on
          screen. It can detect if it hits another FlyingObj or hits one of
          the walls.
        - Damageable: interface that represents a projectile that can take
          damage.
        - SpaceShip: represents the user ship, implements damageable, controlled
          by user input.
        - CollisionProjectile: abstract class that extends FlyingObj and 
          represents anything that can collide into the ship.
          Has a hitShip() method that by default does damage to the ship.
        - Asteroid: extends CollisionProjectile and implements Damageable and
          represents an asteroid. Drawn by a black circle.
        - EnemyShip: extends CollisionProjectile and implements Damageable and
          represents and enemy ship. Fires lasers which are added to the SpaceGameCourt's
          projectiles on screen. Drawn by a blue oval.
        - Coin: extends CollisionProjectile. Overrides the hitShip() method
          to add coins to the ship instead of dealing damage.
        - Laser: extends CollisionProjectile. Faster than everything else and
          does damage to other CollisionProjectiles. Drawn by a red line.
        - CollisionProjType: enum that represents the type of projectiles
          that can be passed in from the levels text file.
        - LevelReader: has static method that reads in level and returns
          all the projectiles for that level. Reads from novel level format.
        - Game: runs game, starts at menu and allows for the user to move through
          different screens including level select, menu, game screen,
          instructions, and highscores.
        - HighScore: represents a highscore with a name, score, and quote.
        - HighScoreKeeper: reads and writes using novel highscore format to
          a collection of highscores. Allows for adding new highscores and 
          checking if a score is a highscore.
        - SpaceGameCourt: JPanel for the actual game. Uses timers to ensure
          new projectiles are added and move accordingly. Implements game logic.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
        Mostly, it took me a while to think of how to organize my hierarchy
        to ensure abstraction.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
        Overall, I think my design is okay, I would have liked to somehow have
        the SpaceShip and Enemy be linked by a shootable interface, but there
        was no real use for it and the fire() method would still have to be
        implemented for both.

========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
