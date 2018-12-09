/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.Graphics;

/** 
 * An object in the game. 
 *
 * Game objects exist in the game court. They have a position, velocity, size and bounds. Their
 * velocity controls how they move; their position should always be within their bounds.
 */
public abstract class FlyingObj {
    
    /* Current position of the object (in terms of graphics coordinates) */
    private int px; 
    private int py;

    /* Size of object, in pixels. */
    private int width;
    private int height;

    /* Velocity: number of pixels to move every time move() is called. */
    private int vx;
    private int vy;

    /* 
     * Upper bounds of the area in which the object can be positioned. Maximum permissible x, y
     * positions for the upper-left hand corner of the object.
     */
    private int maxX;
    private int maxY;
    
    /**
     * Constructor
     */
    public FlyingObj(int vx, int vy, int px, int py, int width, int height, int courtWidth,
        int courtHeight) {
        this.vx = vx;
        this.vy = vy;
        this.px = px;
        this.py = py;
        this.width  = width;
        this.height = height;

        // take the width and height into account when setting the bounds for the upper left corner
        // of the object.
        this.maxX = courtWidth - width;
        this.maxY = courtHeight - height;
    }

    /*** GETTERS **********************************************************************************/
    public int getPx() {
        return this.px;
    }

    public int getPy() {
        return this.py;
    }
    
    public int getVx() {
        return this.vx;
    }
    
    public int getVy() {
        return this.vy;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }

    /*** SETTERS **********************************************************************************/
    public void setPx(int px) {
        this.px = px;
        clip();
    }

    public void setPy(int py) {
        this.py = py;
        clip();
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }

    /*** UPDATES AND OTHER METHODS ****************************************************************/

    /**
     * Prevents the object from going outside of the bounds of the area designated for the object.
     * (i.e. Object cannot go outside of the active area the user defines for it).
     */ 
    private void clip() {
        this.px = Math.min(Math.max(this.px, 0), this.maxX);
        this.py = Math.min(Math.max(this.py, 0), this.maxY);
    }

    /**
     * Moves the object by its velocity.  Ensures that the object does not go outside its bounds by
     * clipping.
     */
    public void move() {
        this.px += this.vx;
        this.py += this.vy;

        clip();
    }

    /**
     * Determine whether this game object is currently colliding with another object.
     * 
     * Collision is determined by comparing bounding boxes. If the bounding boxes overlap, then
     * an collision is considered to occur.
     * 
     * @param that The other object
     * @return Whether this object collides with the other object.
     */
    public boolean collidesWith(FlyingObj that) {
        return (this.px + this.width >= that.px
            && this.py + this.height >= that.py
            && that.px + that.width >= this.px 
            && that.py + that.height >= this.py);
    }

    /**
     * Determine whether the projectile has finished its course.
     *  
     * @return boolean depending on if the projectile's course has finished
     */
    public boolean finishedCourse() {
        return this.py + this.vy > this.maxY || this.py + this.vy < 0;
    }
    
    /**
     * Default draw method that provides how the object should be drawn in the GUI. This method does
     * not draw anything. Subclass should override this method based on how their object should
     * appear.
     * 
     * @param g The <code>Graphics</code> context used for drawing the object. Remember graphics
     * contexts that we used in OCaml, it gives the context in which the object should be drawn (a
     * canvas, a frame, etc.)
     */
    public abstract void draw(Graphics g);
}