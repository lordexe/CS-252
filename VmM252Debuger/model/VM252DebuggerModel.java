package model;
import observation.*;
import java.lang.Integer;
import vm252architecturespecifications.VM252ArchitectureSpecifications;
import vm252architecturespecifications.VM252ArchitectureSpecifications.Instruction;
public class VM252DebuggerModel extends SimpleObservable
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
    private boolean suppressPcIncrement;
    private VM252ArchitectureSpecifications.Instruction currentInstruction;


    private String instruction;
    private String [] displayContents 
        = new String [ VM252ArchitectureSpecifications.MEMORY_SIZE_IN_BYTES ];
    private short breakPoint;
    public int myInput;
    private boolean inputReady = false;
    private int myExecutingSpeed;
    private String nextInst;
    private String objFileString = "";
    // public static class tableRowInfo {
    //     public int counter;
    //     public String strOpcode;
    //     public int intOprand;
    //     public String hexStringOpcode;
    //     public String hexStringOprand;
    //     public byte [] memoryByteByAddress;

    //     public tableRowInfo(
    //         int counter;
    //         String strOpcode;
    //         int intOprand;
    //         String hexStringOpcode;
    //         String hexStringOprand;
    //         byte [] memoryByteByAddress;
    //     ){
    //         counter = otherCounter;
    //         strOpcode = otherStrOpcode;
    //         intOprand = otherIntOprand;
    //         hexStringOpcode = otherHexStringOpcode;
    //         hexStringOprand = otherHexStringOprand;
    //         memoryByteByAddress = otherMemoryByteAddress;
    //     }
    // };

    // private tableRowInfo [] rowsInfo = new tableRowInfo [vm252architecturespecifications.MEMORY_SIZE_IN_BYTES];

    // public tableRowInfo getTa

    

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
                "Attempt to fetch memory byte from illegal memory address " + address
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

    public int getInputValue()
    {
        return myInput;
    }

    public boolean getInputReady()
    {
        return inputReady;
    }

    public void setInputValue(int other)
    {
        myInput = other;
        announceChange();
    }

    public void setInputReady(boolean other)
    {
        inputReady = other;
    }

    public int getExecutingSpeed()
    {
        return myExecutingSpeed;
    }

    public void setExecutingSpeed(int other)
    {
        myExecutingSpeed = other;
    }

    public void setPauseStatus(boolean other)
    {
        myPauseStatus = other;
    }

    public void setAccumulator(int other){
        myAccumulator = (other);
    }

    public void setStoppedStatus(StoppedCategory other){
        myStoppedStatus = other;
        }
    
    public void setInstruction(String other) {
        instruction = other;
    }

    public void setDisplayContents(String[] other)
    {
        displayContents = other;
    }

    public void forceSetDisplayContents(String[] other)
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


    private void setSuppressPcStatus(boolean other)
    {
        suppressPcIncrement = other;
    }

    private boolean getSuppressPcStatus()
    {
        return suppressPcIncrement;
    }

    private Instruction fectchByPair(int address)
    {
        try {

            currentInstruction
                = new VM252ArchitectureSpecifications.Instruction(
                   fetchMemoryBytes(address, 2)
                   );
            return currentInstruction;
            }
        catch (IllegalArgumentException exception) {

            currentInstruction
                = new VM252ArchitectureSpecifications.Instruction(
                   fetchMemoryBytes(address, 1)
                   );
            return currentInstruction;

            }
    }

    private void setCurrentInstruction(){
        currentInstruction = fectchByPair(programCounter());
    }

    public Instruction getCurrentInstruction()
    {
        return currentInstruction;
    }

    public void setNextInst(String other)
    {
        nextInst = other;
        announceChange();
    }

    public String getNextInst()
    {
        return nextInst;
    }

    public String convertToString()
    {
        Instruction currInstruction;
        String all_string = "";
        boolean endCodeChecker = false;
        int currentPC= 0 ;
        

        while(!endCodeChecker){
            currInstruction = fectchByPair(currentPC);
            setSuppressPcStatus(false);

            switch (currentInstruction.numericOpcode()) {

                case VM252ArchitectureSpecifications.LOAD_OPCODE :
                    all_string = all_string + "[Count " + currentPC + "] " + "LOAD" + currInstruction.numericOperand() + 
                    "\n" + "Opcode hex" + Integer.toHexString( (int) currInstruction.numericOpcode() & 0xff) +
                    "\n" + "Oprand hex" + Integer.toHexString( (int) currInstruction.numericOperand() & 0xff) + "\n";
                    break;

                case VM252ArchitectureSpecifications.SET_OPCODE :
                    all_string = all_string + "[Count " + currentPC + "] " + "SET" + currInstruction.numericOperand() + 
                    "\n" + "Opcode hex" + Integer.toHexString( (int) currInstruction.numericOpcode() & 0xff) +
                    "\n" + "Oprand hex" + Integer.toHexString( (int) currInstruction.numericOperand() & 0xff) + "\n";
                    break;

                case VM252ArchitectureSpecifications.STORE_OPCODE :
                    all_string = all_string + "[Count " + currentPC + "] " + "STORE" + currInstruction.numericOperand() + 
                    "\n" + "Opcode hex" + Integer.toHexString( (int) currInstruction.numericOpcode() & 0xff) +
                    "\n" + "Oprand hex" + Integer.toHexString( (int) currInstruction.numericOperand() & 0xff) + "\n";
                    break;

                case VM252ArchitectureSpecifications.ADD_OPCODE :
                    all_string = all_string + "[Count " + currentPC + "] " + "Add" + currInstruction.numericOperand() + 
                    "\n" + "Opcode hex" + Integer.toHexString( (int) currInstruction.numericOpcode() & 0xff) +
                    "\n" + "Oprand hex" + Integer.toHexString( (int) currInstruction.numericOperand() & 0xff) + "\n";
                    break;

                case VM252ArchitectureSpecifications.SUBTRACT_OPCODE :
                    all_string = all_string + "[Count " + currentPC + "] " + "Subtract" + currInstruction.numericOperand() + 
                    "\n" + "Opcode hex" + Integer.toHexString( (int) currInstruction.numericOpcode() & 0xff) +
                    "\n" + "Oprand hex" + Integer.toHexString( (int) currInstruction.numericOperand() & 0xff) + "\n";
                    break;

                case VM252ArchitectureSpecifications.JUMP_OPCODE :
                    all_string = all_string + "[Count " + currentPC + "] " + "Jump" + currInstruction.numericOperand() + 
                    "\n" + "Opcode hex" + Integer.toHexString( (int) currInstruction.numericOpcode() & 0xff) +
                    "\n" + "Oprand hex" + Integer.toHexString( (int) currInstruction.numericOperand() & 0xff) + "\n";
                    break;

                case VM252ArchitectureSpecifications.JUMP_ON_ZERO_OPCODE :
                    all_string = all_string + "[Count " + currentPC + "] " + "Jumpz" + currInstruction.numericOperand() + 
                    "\n" + "Opcode hex" + Integer.toHexString( (int) currInstruction.numericOpcode() & 0xff) +
                    "\n" + "Oprand hex" + Integer.toHexString( (int) currInstruction.numericOperand() & 0xff) + "\n";
                    break;

                case VM252ArchitectureSpecifications.JUMP_ON_POSITIVE_OPCODE :
                    all_string = all_string + "[Count " + currentPC + "] " + "Jumpp" + currInstruction.numericOperand() + 
                    "\n" + "Opcode hex" + Integer.toHexString( (int) currInstruction.numericOpcode() & 0xff)  +
                    "\n" + "Oprand hex" + Integer.toHexString( (int) currInstruction.numericOperand() & 0xff) + "\n";
                    break;

                case VM252ArchitectureSpecifications.INPUT_OPCODE: {
                    resetDisplayContents();
                    all_string = all_string + "[Count " + currentPC + "] " + " Input" + 
                    "\n" + "Opcode hex" + Integer.toHexString( (int) currInstruction.numericOpcode() & 0xff) + "\n";
                    break;
            }

                case VM252ArchitectureSpecifications.OUTPUT_OPCODE :
                    all_string = all_string + "[Count " + currentPC + "] " + "Output" + 
                    "\n" + "Opcode hex" + Integer.toHexString((int) currInstruction.numericOpcode()) + "\n";
                    break;

                case VM252ArchitectureSpecifications.NO_OP_OPCODE :
                    all_string = all_string + "[Count " + currentPC + "] " + "Noop" +
                    "\n" + "Opcode hex" + Integer.toHexString( (int) currInstruction.numericOpcode()) + "\n";
                    break;

                case VM252ArchitectureSpecifications.STOP_OPCODE :
                    all_string = all_string + "[Count " + currentPC + "] " + "Stop" + 
                    "\n" + "Opcode hex" + Integer.toHexString( (int) currInstruction.numericOpcode()) + "\n";
                    endCodeChecker = true;
                    break;

                }

        if (! getSuppressPcStatus())
            currentPC = 
                VM252ArchitectureSpecifications.nextMemoryAddress(
                    currentPC,
                    currentInstruction.instructionBytes().length
                    ); 

        }

        return all_string;
        }  
                  
    public void  setParsedInstructions(String other){
        objFileString = other;
    }

    public String getParsedInstructions()
    {
        return objFileString;
    }




    //Murators

    public void setProgramCounter(int other) throws IllegalArgumentException{
        if (other < 0 || VM252ArchitectureSpecifications.MEMORY_SIZE_IN_BYTES < other)
            throw
                new IllegalArgumentException(
                    "Attempt to set program counter to illegal memory address " + other
                );

        else {
            myProgramCounter = other;
            announceChange();
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
            announceChange();
            }
        }



    // Ctors

    VM252DebuggerModel()
    {
        super();

        String [] welcomeContents = {"Welcome to VM252 debugger"};

        setAccumulator((short)0);
        setProgramCounter((short)0);
        setDisplayContents(welcomeContents);
        setExecutingSpeed(500);
        setPauseStatus(false);
        setBreakPoint((short)8192);
        setCurrentInstruction();
        setNextInst(getCurrentInstruction().symbolicOpcode());
        setParsedInstructions(convertToString());
    }

    public VM252DebuggerModel(byte [] programEncoded){

        super();

        for (int address = 0; address< programEncoded.length;++ address){
            setMemoryByte(address, programEncoded[address]);
        }

        String [] welcomeContents = {""};

        setSuppressPcStatus(false);
        setStoppedStatus(StoppedCategory.notStopped);
        setAccumulator(0);
        setProgramCounter(0);
        setDisplayContents(welcomeContents);
        setExecutingSpeed(500);
        setPauseStatus(false);
        setBreakPoint((short)8192);
        setCurrentInstruction();
        setNextInst(getCurrentInstruction().symbolicOpcode());
        setParsedInstructions(convertToString());


    }


    //
    // Private Mutators
    //

    private byte [ ] fetchMemoryBytes(int memoryAddress, int numberOfBytes)
        {

            byte [ ] memoryBytes = new byte [ Math.max(0, numberOfBytes) ];

            for (int byteNumber = 0;
                    numberOfBytes > 0;
                    ++ byteNumber, -- numberOfBytes,
                    memoryAddress
                        = VM252ArchitectureSpecifications.nextMemoryAddress(memoryAddress)
                    )

                memoryBytes[ byteNumber ] = memoryByte(memoryAddress);

            return memoryBytes;

            }

    private int fetchMemoryData(int memoryAddress)
    {

        byte [ ] dataBytes = fetchMemoryBytes(memoryAddress, 2);

        return ((short) (dataBytes[ 0 ] << 8 | dataBytes[ 1 ] & 0xff));

        }

    
    private void storeMemoryData(int memoryAddress, int data)
    {


        setMemoryByte(memoryAddress, ((byte) (data >> 8 & 0xff)));
        setMemoryByte(
            VM252ArchitectureSpecifications.nextMemoryAddress(memoryAddress),
            ((byte) (data & 0xff))
            );

        }


    public void runProgram()
    {
        if (stoppedStatus() == StoppedCategory.notStopped ){
            Instruction currentInstruction;
            int data;

            try{
                currentInstruction = new Instruction(
                    fetchMemoryBytes(programCounter(), 2));
            }

            catch (IllegalArgumentException exception) {

                currentInstruction
                    = new VM252ArchitectureSpecifications.Instruction(
                       fetchMemoryBytes(programCounter(), 1)
                       );

                }

        //
        // Simulate the execution of currentInstruction
        //

            setSuppressPcStatus(false);

            switch (currentInstruction.numericOpcode()) {

                case VM252ArchitectureSpecifications.LOAD_OPCODE :
                    resetDisplayContents();
                    setAccumulator(fetchMemoryData(currentInstruction.numericOperand()));
                    setDisplayContents(new String [] {"Count " + programCounter() +
                         "| " + "LOAD " + currentInstruction.numericOperand()});
                    setNextInst(currentInstruction.symbolicOpcode());
                    break;

                case VM252ArchitectureSpecifications.SET_OPCODE :
                    resetDisplayContents();
                    setDisplayContents(new String [] {"Count " + programCounter() +
                         "| " + "SET " + currentInstruction.numericOperand()});
                    setAccumulator(currentInstruction.numericOperand());
                    setNextInst(currentInstruction.symbolicOpcode());
                    break;

                case VM252ArchitectureSpecifications.STORE_OPCODE :
                    storeMemoryData(
                        currentInstruction.numericOperand(),
                        accumulator()
                        );
                        setDisplayContents(new String [] {"Count " + programCounter() +
                         "| " + "STORE " + currentInstruction.numericOperand()});
                         setNextInst(currentInstruction.symbolicOpcode());
                    break;

                case VM252ArchitectureSpecifications.ADD_OPCODE :
                    data = fetchMemoryData(currentInstruction.numericOperand());
                    setAccumulator(
                        accumulator() + data
                        );
                        setDisplayContents(new String [] {"Count " + programCounter() + 
                        "| " + "ADD " + currentInstruction.numericOperand()});
                        setNextInst(currentInstruction.symbolicOpcode());

                    break;

                case VM252ArchitectureSpecifications.SUBTRACT_OPCODE :
                    data = fetchMemoryData(currentInstruction.numericOperand());
                    setAccumulator(
                        accumulator() - data
                        );
                    setDisplayContents(new String [] {"Count " + programCounter() +
                     "| " + "SUBTRACT " + currentInstruction.numericOperand()});
                    setNextInst(currentInstruction.symbolicOpcode());
                    break;

                case VM252ArchitectureSpecifications.JUMP_OPCODE :
                    setProgramCounter(currentInstruction.numericOperand());
                    setDisplayContents(new String [] {"Count " + programCounter() + 
                    "| " + "JUMP " + currentInstruction.numericOperand()});
                    resetDisplayContents();
                    setSuppressPcStatus(true);
                    setNextInst(currentInstruction.symbolicOpcode());
                    break;

                case VM252ArchitectureSpecifications.JUMP_ON_ZERO_OPCODE :
                    resetDisplayContents();
                    setDisplayContents(new String [] {"Count " + programCounter() +
                     "| " + "JUMPZ " + currentInstruction.numericOperand()});
                    //System.out.println("Zero Op");
                    if (accumulator() == 0) {
                        setProgramCounter(currentInstruction.numericOperand());
                        resetDisplayContents();
                        setSuppressPcStatus(true);
                        }
                    setNextInst(currentInstruction.symbolicOpcode());
                    break;

                case VM252ArchitectureSpecifications.JUMP_ON_POSITIVE_OPCODE :
                    
                    //System.out.println("Positive Op");
                    setDisplayContents(new String [] {"Count " + programCounter() +
                     "| " + "JUMPP " + currentInstruction.numericOperand()});
                    if (accumulator() > 0) {
                        //System.out.println("Before" + programCounter());
                        setProgramCounter(currentInstruction.numericOperand());
                        //System.out.println("After" + programCounter());
                        setSuppressPcStatus(true);
                        }
                    resetDisplayContents();
                    setNextInst(currentInstruction.symbolicOpcode());
                    break;

                case VM252ArchitectureSpecifications.INPUT_OPCODE: {
                    resetDisplayContents();

                    forceSetDisplayContents(new String [] {"Count " + programCounter() + "| " + "Please provide some Input"});
                    while (!getInputReady())
                        resetDisplayContents();

                    setAccumulator(getInputValue());
                    setDisplayContents(new String[] {"Count " + programCounter() + "| " + "Set Input value to " + getInputValue()});
                    setInputReady(false);
                    setNextInst(currentInstruction.symbolicOpcode());
                    break;
               }

                case VM252ArchitectureSpecifications.OUTPUT_OPCODE :
                    setDisplayContents(new String [] {"Count " + programCounter() +
                     "| " + "The Output is " + accumulator()});
                     setNextInst(currentInstruction.symbolicOpcode());
                    break;

                case VM252ArchitectureSpecifications.NO_OP_OPCODE :
                    break;

                case VM252ArchitectureSpecifications.STOP_OPCODE :
                    setStoppedStatus(
                    VM252DebuggerModel.StoppedCategory.stopped
                    );
                    forceSetDisplayContents(new String [] {"Count " + programCounter() + "| " + "Program Stops"});
                    resetDisplayContents();
                    break;

                }

            if (stoppedStatus() == StoppedCategory.notStopped && !getSuppressPcStatus()){
                resetDisplayContents();
                setProgramCounter(
                    VM252ArchitectureSpecifications.nextMemoryAddress(
                        programCounter(),
                        currentInstruction.instructionBytes().length
                        )
                    );
            }

            if (stoppedStatus() == StoppedCategory.notStopped)
            setNextInst(currentInstruction.symbolicOpcode());
            }

        }

    @Override
    public void announceChange() {

        for (Observer currentObserver : observers())
            currentObserver.update();
        }

    
    }