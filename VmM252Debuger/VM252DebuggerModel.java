class VM252DebuggerModel extends SimpleObservable
{

    private int myACC;
    private int myPC;
    private int myMemory;
    private String instruction;
    private String [] displayContents;

    //Accessors

    public int getAccValue()
    {

        return myACC;

        }

    public int getPCValue()
    {

        return myPC;

        }

    public int getMemoryValue()
    {

        return myMemory;

        }
    
    public String getInstruction(){
        return instruction;
    }

    public String [] getDisplayContents()
    {
        return displayContents;
    }

    //Murators

    public void setAccValue(int other)
    {

        myACC = other;

        announceChange();

        }

    public void setPCValue(int other)
    {

        myPC = other;

        announceChange();

        }

    public void setMemoryValue(int other)
    {

        myMemory = other;

        announceChange();

        }
    
    public void setInstruction(String other) {
        instruction = other;
    }

    public void setDisplayContents(String [] other)
    {
        displayContents = other;
        announceChange();
    }

    // Ctors

    VM252DebuggerModel()
    {

        super();
        String [] welcomeContents = {"Welcome to VM252 debugger GUI"};

        setAccValue(0);
        setPCValue(0);
        setMemoryValue(0);
        setInstruction("Null");
        setDisplayContents(welcomeContents);

        }

        VM252DebuggerModel(int initialAccValue, int initialPCValue, int initialMemoryValue, String initialInstruction, String [] initialDisplayContents)
    {

        super();

        setAccValue(initialAccValue);
        setPCValue(initialPCValue);
        setMemoryValue(initialMemoryValue);
        setInstruction(initialInstruction);
        setDisplayContents(initialDisplayContents);

        }
}