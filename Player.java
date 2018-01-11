//M. Hi everybody. Things weren't working right so Mason (Ubbiout) started over from examplefuncplayer - java
//M. it's ok nothing i wrote even compiled
//M. See https://s3.amazonaws.com/battlecode-2018/api/java/index.html for the javadocs.

//M. i'm just going to try to put "M." at the beginning of all my comments so u know it was me that fucked that line up or whatever....

// import the API.
import bc.*;

public class Player {
    public static void main(String[] args) {
        // MapLocation is a data structure you'll use a lot.
        MapLocation loc = new MapLocation(Planet.Earth, 10, 20);
        System.out.println("loc: "+loc+", one step to the Northwest: "+loc.add(Direction.Northwest));
        System.out.println("loc x: "+loc.getX());

        // One slightly weird thing: some methods are currently static methods on a static class called bc.
        // This will eventually be fixed :/
        System.out.println("Opposite of " + Direction.East + ": " + bc.bcDirectionOpposite(Direction.East));

        // Connect to the manager, starting the game
        GameController gc = new GameController();

        // Direction is a normal java enum.
        Direction[] directions = Direction.values();

        while (true) {
            System.out.println("Current round: "+gc.round());
            // VecUnit is a class that you can think of as similar to ArrayList<Unit>, but immutable.
            VecUnit units = gc.myUnits();
            for (int i = 0; i < units.size(); i++) {
                Unit unit = units.get(i);

                // Most methods on gc take unit IDs, instead of the unit objects themselves.
                if (gc.isMoveReady(unit.id()) && gc.canMove(unit.id(), Direction.West)) {
                    gc.moveRobot(unit.id(), Direction.West);
                }
            }
            // Submit the actions we've done, and wait for our next turn.
            gc.nextTurn();
        }
    }
}


/* looks like the only map right now has no deposit on it LOL
public location locateInitialKarboniteDeposits(){
  //M. loop through bc.startingMap(bc.Planet.Earth) and check each with initialKarboniteAt(x and y) or probably not bc.karboniteAt(MapLocation location)
  //initialize vector of locations here
    VecMapLocation karbLoc;
  for (int i = 0; i < bc.startingMap(bc.Planet.Earth).width(); i++) {
     for (int j = 0; j < bc.startingMap(bc.Planet.Earth).height(); i++) {
       if(initialKarboniteAt(i,j) > 0){
         //save location into vector
       }
  }
  //return vector of locations with karbonite deposits
}

so we could totally build a factory
research rockets
build a rocket
eventually finish building it
load workers into its garrison
fly it to mars
and figure out how to write a mars program cuz wtf mate

*/