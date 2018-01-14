package src;

public class Astar
{
    public static final int MAXOPEN = 1024;
    public static final int MAXCLOS = 1024;

    public class AstarNode
    {
        GOAP.WorldState ws;
        int g;
        int h;
        int f;
        String ActionName;
        GOAP.WorldState parentws;
    }

    static AstarNode opened[] = new AstarNode[MAXOPEN];
    static AstarNode closed[] = new AstarNode[MAXCLOS];

    private static int numOpened = 0;
    private static int numClosed = 0;

    static int CalculateHeuristic(GOAP.WorldState fr, GOAP.WorldState to)
    {
        final long care = (to.dontcare ^ -1L);
        final long diff = ((fr.value & care) ^ (to.value & care));
        int dist = 0;
        for(int i=0; i<GOAP.MAXATOMS; ++i)
        {
            if((diff & (1L << i)) != 0) dist++;
        }
        return dist;
    }

    static int IDXInOpened(GOAP.WorldState ws)
    {
        for(int i=0; i<numOpened; ++i)
        {
            if(opened[i].ws.value == ws.value) return i;
        }
        return -1;
    }

    static int IDXInClosed(GOAP.WorldState ws)
    {
        for(int i=0; i<numClosed; ++i)
        {
            if (closed[i].ws.value == ws.value) return i;
        }
        return -1;
    }

    /*int AstarPlan(GOAP.ActionPlanner ap, GOAP.WorldState start, GOAP.WorldState goal, String plan, GOAP.WorldState worldstates, int plansize)
    {

    }*/
}
