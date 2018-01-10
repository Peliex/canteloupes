// import the API.
// See https://s3.amazonaws.com/battlecode-2018/api/java/index.html for the javadocs.
// Mason thinks we'll get SDK soon... like when the client is released
import bc.*;

public class Player {
    public static void main(String[] args) {
        // MapLocation is a data structure you'll use a lot.
        //MapLocation loc = new MapLocation(Planet.Earth, 10, 20);
        //System.out.println("loc: "+loc+", one step to the Northwest: "+loc.add(Direction.Northwest));
        //System.out.println("loc x: "+loc.getX());

        // One slightly weird thing: some methods are currently static methods on a static class called bc.
        // This will eventually be fixed :/
        //System.out.println("Opposite of " + Direction.North + ": " + bc.bcDirectionOpposite(Direction.North));

        // Connect to the manager, starting the game
        GameController gc = new GameController();

        // Direction is a normal java enum.
        Direction[] directions = Direction.values();

        //queue some rocket research on turn 1
        if(gc.round()==1){
            gc.queueResearch(bc.UnitType.Rocket ) //M. idk if this is the right way to call queueResearch(UnitType branch)
        }

        System.out.println("Current round: "+gc.round()); //M. It's it nice to have a computer that will talk to you?

        //M. mine some Karbonite... someime initialKarboniteAt(MapLocation location)
        while (true) {
            // VecUnit is a class that you can think of as similar to ArrayList<Unit>, but immutable.
            VecUnit units = gc.myUnits();
            for (int i = 0; i < units.size(); i++) { //M. for each unit in our immutable units vector
                Unit unit = units.get(i);

                // Most methods on gc take unit IDs, instead of the unit objects themselves.
                if (gc.isMoveReady(unit.id()) && gc.canMove(unit.id(), Direction.Southeast)) {
                    gc.moveRobot(unit.id(), Direction.Southeast);
                }
            }
            // Submit the actions we've done, and wait for our next turn.
            gc.nextTurn();
        }
    }
}