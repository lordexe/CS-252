package model;
import observation.*;
import vm252architecturespecifications.VM252ArchitectureSpecifications;
import Packages.vm252simulation.ObservableVM252;
import Packages.vm252simulation.VM252Observer;
public class VM252DebuggerModel extends SimpleObservable implements ObservableVM252
{
    public enum StoppedCategory {
        notStopped,
        stopped
        };
    
    private int myAccumulator;
    private int myProgramCounter;
    private final byte [ ] myMemory
        = new byte [ VM252ArchitectureSpecifications.MEMORY_SIZE_IN_BYTES ];
    private StoppedCategory myStoppedStatus;
    private boolean myPauseStatus;


    private String instruction;
    private String [] displayContents 
        = new String [ VM252ArchitectureSpecifications.MEMORY_SIZE_IN_BYTES ];
    private short breakPoint;
    private boolean HaltingInstruction;

    //Accessors

    public int accumulator(){
        return myAccumulator;
    }

    public int programCounter(){
        return myProgramCounter;
    }

    public byte[] getMemoryValue(){
        return myMemory;
    }

    public byte memoryByte(int address) throws IllegalArgumentException{
        if (address < 0
            || VM252ArchitectureSpecifications.MEMORY_SIZE_IN_BYTES <= address)
            throw
            new IllegalArgumentException(
                "Attempt to getch memory byte from illegal memory address " + address
            );
        else
            return myMemory[ address ];
        }

    public StoppedCategory stoppedStatus(){
        return myStoppedStatus;
    }

    public boolean getPauseStatus()
    {
        return myPauseStatus;
    }
    
    public String getInstruction(){
        return instruction;
    }

    public String [] getDisplayContents()
    {
        return displayContents;
    }

    //Murators

    public void setAccumulator(int other){
        myAccumulator = ((short) other);
        announceAccumulatorChange();
    }

    public void setProgramCounter(int other) throws IllegalArgumentException{
        if (other < 0 || VM252ArchitectureSpecifications.MEMORY_SIZE_IN_BYTES <= other)
            throw
                new IllegalArgumentException(
                    "Attempt to set program counter to illegal memory address " + other
                );

        else {
            myProgramCounter = other;
            announceProgramCounterChange();
            };

        }

    public void setMemoryByte(int address, byte other) throws IllegalArgumentException{
        if (address < 0
                || VM252ArchitectureSpecifications.MEMORY_SIZE_IN_BYTES <= address)
            throw
                new IllegalArgumentException(
                    "Attempt to set memory byte at illegal memory address " + address
                    );
        else {
            myMemory[ address ] = other;
            announceMemoryChange(address);
            }
        }

    public void setStoppedStatus(StoppedCategory other){
        myStoppedStatus = other;
        announceStoppedStatusChange();
        }
    
    public void setInstruction(String other) {
        instruction = other;
        announceChange();
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
    public VM252DebuggerModel(byte [] programEncoded){

        super();
        String [] welcomeContents = {""};

        setAccumulator(0);
        setProgramCounter(0);
        setStoppedStatus(StoppedCategory.notStopped);
        // setMemoryValue();
        // setInstruction();
        // setDisplayContents();

    }

    @Override
    public void announceAccumulatorChange() {
        // TODO Auto-generated method stub
        for (Observer currentObserver : observers())

                if (currentObserver instanceof VM252Observer)
                    ((VM252Observer) currentObserver).updateAccumulator();

        }

    @Override
    public void announceProgramCounterChange() {
        // TODO Auto-generated method stub
        for (Observer currentObserver : observers())
            if (currentObserver instanceof VM252Observer)
                ((VM252Observer) currentObserver).updateProgramCounter();
            }

    @Override
    public void announceMemoryChange(int addressOfChangedByte) {
        // TODO Auto-generated method stub
        for (Observer currentObserver : observers())
            if (currentObserver instanceof VM252Observer)
                ((VM252Observer) currentObserver).updateMemory(addressOfChangedByte);
            }

    @Override
    public void announceStoppedStatusChange() {
        // TODO Auto-generated method stub
        for (Observer currentObserver : observers())
            if (currentObserver instanceof VM252Observer)
                ((VM252Observer) currentObserver).updateStoppedStatus();
            }

    @Override
    public void announceChange() {

        for (Observer currentObserver : observers())
            currentObserver.update();

        }
    }