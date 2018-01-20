import bc.GameController;
import src.*;
import src.GOAP;

public class Player {
    public static void main(String[] args) {
        //Ai ai = new Ai(new GameController());
        //noinspection InfiniteLoopStatement

        GOAP.ActionPlanner ap = new GOAP.ActionPlanner();
        GOAP.ClearActionPlanner(ap);

        GOAP.SetPrecondition(ap, "scout", "armedwithgun", true);
        GOAP.SetPostcondition(ap, "scout", "enemyvisible", true);

        GOAP.SetPrecondition(ap, "approach", "enemyvisible", true);
        GOAP.SetPostcondition(ap, "approach", "nearenemy", true);

        GOAP.SetPrecondition(ap, "aim", "enemyvisible", true);
        GOAP.SetPrecondition(ap, "aim", "weaponloaded", true);
        GOAP.SetPostcondition(ap, "aim", "enemylinedup", true);

        GOAP.SetPrecondition(ap, "shoot", "enemylinedup", true);
        GOAP.SetPostcondition(ap, "shoot", "enemyalive", false);

        GOAP.WorldState fr = new GOAP.WorldState();
        GOAP.ClearWorldState(fr);
        GOAP.SetWorldState(ap, fr, "enemyvisible", false);
        GOAP.SetWorldState(ap, fr, "armedwithgun", true);
        GOAP.SetWorldState(ap, fr, "weaponloaded", false);
        GOAP.SetWorldState(ap, fr, "enemylinedup", false);
        GOAP.SetWorldState(ap, fr, "nearenemy", false);
        GOAP.SetWorldState(ap, fr, "enemyalive", true);

        GOAP.WorldState goal = new GOAP.WorldState();
        GOAP.ClearWorldState(goal);
        GOAP.SetWorldState(ap, goal, "enemyalive", false);

        while (true) {
            /*try {
                //ai.run();
            } catch (Exception e) {
                System.err.println("Exception caught: " + e.getMessage());
                e.printStackTrace();
            }*/
        }
    }
}