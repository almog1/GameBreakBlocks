package games;

import collisions.Collidable;
import collisions.CollisionInfo;
import geometry.Line;
import geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * @author almog
 */
public class GameEnvironment {
    private List<Collidable> listOfObjects;

    /**
     * constructor .
     * Make an Array list and then can add Collidables
     */
    public GameEnvironment() {
        this.listOfObjects = new ArrayList<Collidable>();
    }


    /**
     * Add the collidable to the game environment .
     *
     * @param c . collisions.Collidable need to be added to the environment
     */
    public void addCollidable(Collidable c) {
        this.listOfObjects.add(c);
    }

    /**
     * Remove the collidable from the game environment .
     *
     * @param c . collisions.Collidable need to be removed from this environment
     */
    public void removeCollidable(Collidable c) {
        this.listOfObjects.remove(c);
    }

    /**
     * Check if the object will collide with one of the collidable in the list .
     *
     * @param trajectory . geometry.Line to check if it collidable
     * @return null if not . if collidable retun the info of the closest
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Collidable> listOfObjectsCp = this.listOfObjects; //copy of it so no Exception while iterating
        List<Point> listOfAllPoints = new ArrayList<Point>();
        List<Collidable> ofWhichCollidable = new ArrayList<Collidable>();
        //check every collidable if it collided with the line
        for (int i = 0; i < listOfObjectsCp.size(); i++) {
            Point mostClose = trajectory.closestIntersectionToStartOfLine(listOfObjectsCp.get(i).
                    getCollisionRectangle()); // take the most close point
            //check if the point isn't null
            if (mostClose != null) {
                //add to the list the point
                listOfAllPoints.add(mostClose);
                ofWhichCollidable.add(listOfObjectsCp.get(i));
            }
        }
        //if the list empty - no coliision - return null
        if (listOfAllPoints.isEmpty()) {
            return null;
        } else {
            Point closestPoint = null;
            Collidable mostClosetColidable = null;
            double minDistance = 0; //for the smallest distance between points
            //find the closest point to the line
            closestPoint = listOfAllPoints.get(0);
            mostClosetColidable = ofWhichCollidable.get(0);
            minDistance = listOfAllPoints.get(0).distance(trajectory.start());
            for (int j = 1; j < listOfAllPoints.size(); j++) {
                //if the distance between the points is smaller - change the min and point
                if (listOfAllPoints.get(j).distance(trajectory.start()) < minDistance) {
                    closestPoint = listOfAllPoints.get(j);
                    mostClosetColidable = ofWhichCollidable.get(j);
                    minDistance = listOfAllPoints.get(j).distance(trajectory.start());
                }
            }
            return new CollisionInfo(closestPoint, mostClosetColidable);
        }

    }

}