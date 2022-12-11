package viewAndController.JtextViewAndController;

import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import javax.swing.*;

import model.VM252DebuggerModel;
import observation.*;

public class textFieldViewAndController extends JPanel implements SimpleObserver
{
    private static final int OUR_FRAME_WIDTH = 300;
    private static final int OUR_FRAME_HEIGHT = 300;
    private static final int OUR_COMPONENT_FIELD_AND_AREA_WIDTH = 5;
    private static final int OUR_INPUT_FIELD_AND_AREA_WIDTH = 50;


    private JPanel myJPanel;
    private VM252DebuggerModel myTextBox;
    private JTextField myACCTextField;
    private JTextField myPCTextField;
    private JTextField myInstructionTextField;
    private JTextField myInputTextField;


    // Accessors

    private JPanel getPanel()
    {
        return myJPanel;
    }

    private JTextField getACCTextField()
    {
        return myACCTextField;
    }

    private JTextField getPCTextField()
    {
        return myPCTextField;
    }
    private JTextField getInstructionTextFieldValue()
    {
        return myInstructionTextField;
    }
    private JTextField getInputTextField()
    {
        return myInputTextField;
    }
    private VM252DebuggerModel getTextBox()
    {
        return myTextBox;
    }

    //Mutators

    private void setPanel(JPanel other)
    {
        myJPanel = other;
    }

    private void setACCTextFieldValue(JTextField other)
    {
        myACCTextField = other;
    }

    private void setPCTextFieldValue(JTextField other)
    {
        myPCTextField = other;
    }

    private void setInstructionTextFieldValue(JTextField other)
    {
        myInstructionTextField = other;
    }

    private void setInputTextFieldValue(JTextField other)
    {
        myInputTextField = other;
    }

    private void setTextBox(VM252DebuggerModel other)
    {
        if (getTextBox() != null)
        getTextBox().detach(this);

        myTextBox = other;

        if (getTextBox() != null)
        getTextBox().detach(this);
    }

    //Ctors

    public textFieldViewAndController()
    {
        this (null);
    }



    public textFieldViewAndController(VM252DebuggerModel initialValues)
    {
        
        

        setTextBox(initialValues);

        JLabel ACCLabel = new JLabel("ACC:", JLabel.RIGHT);
        setACCTextFieldValue(new JTextField("" + getTextBox().accumulator(), OUR_COMPONENT_FIELD_AND_AREA_WIDTH));
        ActionListener setAccValue = new ActionListener(){
	        public void actionPerformed(ActionEvent accChange){
                try
                {
                    getTextBox().resetDisplayContents();
                    getTextBox().setAccumulator(Integer.parseInt(getACCTextField().getText()));
                    getTextBox().setDisplayContents(new String[] {"Set ACC value to " + getACCTextField().getText()});
                }catch(NumberFormatException err){
                    getTextBox().setDisplayContents(new String [] {"Not a valid input. Input for ACC Value must be a number"});
                    getTextBox().resetDisplayContents();
                }

        }};
        getACCTextField().addActionListener(setAccValue);

        JLabel PCLabel = new JLabel("PC");
        setPCTextFieldValue(new JTextField("" + getTextBox().programCounter()));

        ActionListener setPcValue = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getTextBox().resetDisplayContents();
                try{
                    if (Short.valueOf(getPCTextField().getText()) >= ((short)8192) || Short.valueOf(getPCTextField().getText()) < ((short) 0))
                    {
                        getTextBox().setDisplayContents(new String[] {"No address " + getPCTextField().getText()});
                        getTextBox().resetDisplayContents();
                        getTextBox().setProgramCounter(getTextBox().programCounter());
                    }else
                    {
                        getTextBox().setProgramCounter(Short.valueOf(getPCTextField().getText()));
                        getTextBox().setHalt(false);
                        getTextBox().setDisplayContents(new String[] {"Set PC value to " + getPCTextField().getText()});
                        getTextBox().resetDisplayContents();
                        getTextBox().setNextInst(getTextBox().getCurrentInstruction().symbolicOpcode());
                    }
                }catch(NumberFormatException err){
                        getTextBox().setDisplayContents(new String [] {"Not a valid input. Input for PC Value must be a number"});
                        getTextBox().resetDisplayContents();
                    }
                // pc is set to counter.getText()
            }
        };
        getPCTextField().addActionListener(setPcValue);

        JLabel nextInstructionLabel = new JLabel("Next Instruction");
        setInstructionTextFieldValue(new JTextField(getTextBox().getNextInst()));
        getInstructionTextFieldValue().setEditable(false);


        JLabel inputLabel = new JLabel("Input");
        setInputTextFieldValue(new JTextField("" + getTextBox().getInputValue()));
        ActionListener setInputValue = new ActionListener(){
	        public void actionPerformed(ActionEvent inputChange){
                getTextBox().resetDisplayContents();
                try{
                    getTextBox().setInputValue(Short.valueOf(getInputTextField().getText()));
                    getTextBox().setInputReady(true);
                }catch(NumberFormatException err){
                    getTextBox().setDisplayContents(new String [] {"Not a valid input. Input for value must be a number"});
                    getTextBox().resetDisplayContents();
                }
                //getSubjectModel().getSemaphore().release();
                //notifyAll();
          }};
        getInputTextField().addActionListener(setInputValue);

        //Create panels

        setPanel(new JPanel());
        getPanel().setSize(OUR_FRAME_WIDTH, OUR_FRAME_HEIGHT);
        getPanel().setLayout(new GridLayout(4, 2));

        //add labels to text fields

        getPanel().add(ACCLabel);
        getPanel().add(getACCTextField());
        getPanel().add(PCLabel);
        getPanel().add(getPCTextField());
        getPanel().add(nextInstructionLabel);
        getPanel().add(getInstructionTextFieldValue());
        getInstructionTextFieldValue().setEditable(false);
        getPanel().add(inputLabel);
        getPanel().add(getInputTextField());

        //add panel to container

        add(getPanel());
    }

    //Observation Method
    @Override
    public void update()
    {
        //set text fields to display the updated information

        getACCTextField().setText("" + getTextBox().accumulator());
        getPCTextField().setText("" + getTextBox().programCounter());
        getInstructionTextFieldValue().setText("" + getTextBox().getNextInst());
        System.out.println("Here!!!");
        getInputTextField().setText("" + getTextBox());
    }

    @Override
    public void attach(Observer anotherObserver) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void detach(Observer currentObserver) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void announceChange() {
        // TODO Auto-generated method stub
        
    }
}