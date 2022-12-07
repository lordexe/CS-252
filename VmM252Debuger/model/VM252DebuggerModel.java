package model;
import observation.*;
import Packages.vm252architecturespecifications.*;
public class VM252DebuggerModel extends SimpleObservable
{

    private int myACC;
    private int myPC;
    private byte [] myMemory;
    private String instruction;
    private String [] displayContents;
    private short breakPoint;
    private boolean HaltingInstruction;

    //Accessors

    public int getAccValue()
    {

        return myACC;

        }

    public int getPCValue()
    {

        return myPC;

        }

    public byte[] getMemoryValue()
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

    public void setACCValue(int other)
    {

        myACC = other;

        announceChange();

        }

    public void setPCValue(int other)
    {

        myPC = other;

        announceChange();

        }

    public void setMemoryValue(byte [] other)
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

    public void resetDisplayContents()
    {
        String [] contents = {""};
        displayContents = contents;
    }

    public short getBreakPoint()
    {
        return breakPoint;
    }

    public void setBreakPoint(short other)
    {
        breakPoint = other;
    }

    public boolean getHaltStatus()
    {
        return HaltingInstruction;
    }

    public void setHalt(boolean other)
    {
       HaltingInstruction = other;
    }

    // Ctors

    public VM252DebuggerModel()
    {

        super();
        String [] welcomeContents = {"Welcome to VM252 debugger GUI"};


        setACCValue((short) 0);
        setPCValue((short) 0);
        setMemoryValue(new byte [8192]);
        setInstruction("Null");
        setDisplayContents(welcomeContents);

    }

        VM252DebuggerModel(byte [] programEncoded)
    {

        super();
        byte [] memory = new byte[ 8192 ];
        String [] welcomeContents = {""};

        setACCValue((short)0);
        setPCValue((short) 0);
        setMemoryValue(initialMemoryValue);
        setInstruction(initialInstruction);
        setDisplayContents(initialDisplayContents);

    }
    
    public void runPrograme ()

    


}