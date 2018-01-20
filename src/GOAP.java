package src;

public class GOAP
{
    public static final int MAXATOMS = 64;
    public static final int MAXACTIONS = 64;

    public static class WorldState
    {
        public long value;
        public long dontcare;
    }

    public static class ActionPlanner
    {
        private String AtomNames[] = new String[MAXATOMS];
        private int NumberOfAtoms;

        private String ActionNames[] = new String[MAXACTIONS];
        private WorldState ActionPre[] = new WorldState[MAXACTIONS];
        private WorldState ActionPst[] = new WorldState[MAXACTIONS];
        private int ActionCost[] = new int[MAXACTIONS];
        private int NumberOfActions;

        public ActionPlanner()
        {
            for(int i = 0; i<MAXACTIONS; ++i)
            {
                ActionPre[i] = new WorldState();
                ActionPst[i] = new WorldState();
            }
        }
    }

    //This function iterates through our AtomNames array to see if there is an atom with the name specified, if there
    //isn't, it writes it to the end of the array.
    public static int IDXForAtomName(ActionPlanner ap, String AtomName)
    {
        int idx;
        for(idx=0; idx < ap.NumberOfAtoms; ++idx)
            if(ap.AtomNames[idx].equals(AtomName))
                return idx;

        if(idx < MAXATOMS)
        {
            ap.AtomNames[idx] = AtomName;
            ap.NumberOfAtoms++;
            return idx;
        }

        return -1;
    }

    public static int IDXForActionName(ActionPlanner ap, String ActionName)
    {
        int idx;
        for(idx=0; idx<ap.NumberOfActions; ++idx)
            if(ap.ActionNames[idx].equals(ActionName))
                return idx;

        if(idx < MAXACTIONS) {
            ap.ActionNames[idx] = ActionName;
            ap.NumberOfActions++;
            return idx;
        }

        return -1;
    }

    public static void ClearActionPlanner(ActionPlanner ap)
    {
        ap.NumberOfAtoms = 0;
        ap.NumberOfActions = 0;

        for(int i=0; i<MAXATOMS; ++i)
        {
            ap.AtomNames[i] = "";
        }
        for(int i=0; i<MAXACTIONS; ++i)
        {
            ap.ActionNames[i] = "";
            ap.ActionCost[i] = 0;
            ClearWorldState(ap.ActionPre[i]);
            ClearWorldState(ap.ActionPst[i]);
        }
    }

    public static void ClearWorldState(WorldState ws)
    {
        if(ws != null)
        {
            ws.value = 1L;
            ws.dontcare = 1L;
        }
    }

    public static boolean SetWorldState(ActionPlanner ap, WorldState ws, String AtomName, boolean Value)
    {
        int idx = IDXForAtomName(ap, AtomName);
        if(idx == -1) return false;
        ws.value = Value ? (ws.value | (1L << idx)) : (ws.value & ~(1L << idx));
        ws.dontcare &= ~(1L << idx);
        return true;
    }

    public static boolean SetPrecondition(ActionPlanner ap, String ActionName, String AtomName, boolean Value)
    {
        final int actidx = IDXForActionName(ap, ActionName);
        final int atmidx = IDXForAtomName(ap, AtomName);
        if(actidx == -1 || atmidx == -1) return false;
        SetWorldState(ap, ap.ActionPre[actidx], AtomName, Value);
        return true;
    }

    public static boolean SetPostcondition(ActionPlanner ap, String ActionName, String AtomName, boolean Value)
    {
        final int actidx = IDXForActionName(ap, ActionName);
        final int atmidx = IDXForAtomName(ap, AtomName);
        if(actidx == -1 || atmidx == -1) return false;
        SetWorldState(ap, ap.ActionPst[actidx], AtomName, Value);
        return true;
    }

    public static boolean SetCost(ActionPlanner ap, String ActionName, int cost)
    {
        final int actidx = IDXForAtomName(ap, ActionName);
        if(actidx == -1) return false;
        ap.ActionCost[actidx] = cost;
        return true;
    }
}