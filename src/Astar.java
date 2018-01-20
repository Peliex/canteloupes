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

    static void ReconstructPlan(GOAP.ActionPlanner ap, AstarNode goalnode, String plan, GOAP.WorldState worldstates, int plansize)
    {
        AstarNode curnode = goalnode;
        int idx = plansize - 1;
        int numsteps = 0;
        while(curnode != null && curnode.ActionName != null)
        {

        }
    }

    int AstarPlan(GOAP.ActionPlanner ap, GOAP.WorldState start, GOAP.WorldState goal, String plan, GOAP.WorldState worldstates, int plansize)
    {
        numOpened = 0;
        AstarNode n0 = new AstarNode();
        n0.ws = start;
        n0.parentws = start;
        n0.g = 0;
        n0.h = CalculateHeuristic(start, goal);
        n0.f = n0.g + n0.h;
        n0.ActionName = "";
        opened[numOpened++] = n0;
        numClosed=0;

        do
        {
            if(numOpened == 0) {System.err.println("Didn't find a path"); return -1; }
            int lowestIdx = -1;
            int lowestVal = Integer.MAX_VALUE;
            for(int i=0; i<numOpened; ++i)
            {
                if(opened[i].f < lowestVal)
                {
                    lowestVal = opened[i].f;
                    lowestIdx = i;
                }
            }
            AstarNode cur = opened[lowestIdx];
            if(numOpened != 0) opened[lowestIdx] = opened[numOpened-1];
            numOpened--;
            final long care = (goal.dontcare ^ -1L);
            final boolean match = ((cur.ws.value & care) == (goal.value & care));
            if(match)
            {

            }
        }
    }
}
