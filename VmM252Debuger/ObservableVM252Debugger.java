class ObservableVM252Debugger extends SimpleObservable
{

    private int myACC;
    private int myPC;
    private int myMemory;

    public int getmyACCValue()
    {

        return myACC;

        }

    public int getmyPCValue()
    {

        return myPC;

        }

    public int getmyMemoryValue()
    {

        return myMemory;

        }

    public void setmyACCValue(int other)
    {

        myACC = other;

        announceChange();

        }

    public void setmyPCValue(int other)
    {

        myPC = other;

        announceChange();

        }

    public void setmyMemoryValue(int other)
    {

        myMemory = other;

        announceChange();

        }

    ObservableVM252Debugger()
    {

        super();

        setmyACCValue(0);
        setmyPCValue(0);
        setmyMemoryValue(0);

        }

    ObservableVM252Debugger(int initialmyACCValue, int initialmyPCValue, int initialmyMemoryValue)
    {

        super();

        setmyACCValue(initialmyACCValue);
        setmyPCValue(initialmyPCValue);
        setmyMemoryValue(initialmyMemoryValue);

        }

    }