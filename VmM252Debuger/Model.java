// class ObservableVM252Machine extends Observe from other file.java
class ObservableVM252Machine{
    private short myACC;
    private short myPC;
    private byte [] myMemory;
    //
    // Accessors
    //
    public short getACCValue(){
        return myACC;
    }

    public short getPCValue(){
        return myPC;
    }

    public byte [] getMemoryValue(){
        return myMemory;
    }

    //
    // Mutators
    //

    public void setACCValue(short other){
        myACC = other;
    }

    public void setPCValue(short other){
        myPC = other;
        // announceChange();
    }

    public void setMemoryValue(byte [] other){
        myMemory = other;
        // announceChange();
    }

    //
    // Ctors
    //

    ObservableVM252Machine(){
        super();

        // String [] welcomeContents = {"Welcome to VM252 debuger"};

        setACCValue((short)0);
        setPCValue((short)0);
        setMemoryValue(new byte [8192]);
    }

    ObservableVM252Machine(short initialACCValue, short initialPCValue, byte [] initialMemoryValue, String initialNextInstruction, String []initialDisplayContents){
        super();

        setACCValue(initialACCValue);
        setPCValue(initialPCValue);
        setMemoryValue(initialMemoryValue);
    }
}