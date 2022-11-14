package viewAndController.JtextViewAndController;

import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import javax.swing.*;

import model.VM252DebuggerModel;
import observation.*;

public class textFieldViewAndController extends JPanel implements Observer
{
    private static final int OUR_FRAME_WIDTH = 300;
    private static final int OUR_FRAME_HEIGHT = 300;
    private static final int OUR_COMPONENT_FIELD_AND_AREA_WIDTH = 5;
    private static final int OUR_MEMORY_FIELD_AND_AREA_WIDTH = 50;


    private JPanel myJPanel;
    private VM252DebuggerModel myTextBox;
    private JTextField myACCTextField;
    private JTextField myPCTextField;
    private JTextField myInstructionTextField;
    private JTextField myMemoryTextField;

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
    private JTextField getInstructionTextField()
    {
        return myInstructionTextField;
    }
    private JTextField getMemoryTextField()
    {
        return myMemoryTextField;
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

    private void setMemoryTextFieldValue(JTextField other)
    {
        myMemoryTextField = other;
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
        setSize(OUR_FRAME_WIDTH, OUR_FRAME_HEIGHT);

        setTextBox(initialValues);

        // Creating text fields

        setACCTextFieldValue(new JTextField("" + getTextBox().getAccValue(), OUR_COMPONENT_FIELD_AND_AREA_WIDTH));

        setPCTextFieldValue(new JTextField("" + getTextBox().getPCValue(), OUR_COMPONENT_FIELD_AND_AREA_WIDTH));

        setInstructionTextFieldValue(new JTextField("" + getTextBox().getInstruction(), OUR_COMPONENT_FIELD_AND_AREA_WIDTH));

        setMemoryTextFieldValue(new JTextField("" + getTextBox().getMemoryValue(), OUR_MEMORY_FIELD_AND_AREA_WIDTH));

        //Creating labels

        JLabel ACCLabel = new JLabel("ACC:", JLabel.RIGHT);
        JLabel PCLabel = new JLabel("PC:", JLabel.RIGHT);
        JLabel InstructionLabel = new JLabel("Instruction:", JLabel.RIGHT);
        JLabel MemoryLabel = new JLabel("Memory:", JLabel.RIGHT);

        //Create panels

        setPanel(new JPanel());
        getPanel().setLayout(new GridLayout(4, 2));

        //add labels to text fields

        getPanel().add(ACCLabel);
        getPanel().add(getACCTextField());
        getPanel().add(PCLabel);
        getPanel().add(getPCTextField());
        getPanel().add(InstructionLabel);
        getPanel().add(getInstructionTextField());
        getPanel().add(MemoryLabel);
        getPanel().add(getMemoryTextField());

        //add panel to container

        add(getPanel());

        //Create Action

        commandInputAction inputAction = new commandInputAction();

        //associate with listener

        getACCTextField().addActionListener(inputAction);
        getPCTextField().addActionListener(inputAction);
        getInstructionTextField().addActionListener(inputAction);
        getMemoryTextField().addActionListener(inputAction);
    }

    //Observation Method
    @Override
    public void update()
    {
        //set text fields to display the updated information

        getACCTextField().setText("" + getTextBox().getAccValue());
        getPCTextField().setText("" + getTextBox().getPCValue());
        getInstructionTextField().setText("" + getTextBox().getInstruction());
        getMemoryTextField().setText("" + getTextBox().getMemoryValue());
    }

    private class commandInputAction implements ActionListener
    {
        //Ctors

        public commandInputAction()
        {

        }

        //Event Handlers

        @Override
        public void actionPerformed(ActionEvent event)
        {
            if (getTextBox() != null)
            {
                Scanner ACCValueScanner = new Scanner(getACCTextField().getText());

                Scanner PCValueScanner = new Scanner(getPCTextField().getText());

                Scanner InstructionValueScanner = new Scanner(getInstructionTextField().getText());

                Scanner MemoryValueScanner = new Scanner(getMemoryTextField().getText());

                int ACCValue = ACCValueScanner.hasNextInt()? ACCValueScanner.nextInt():0;

                int PCValue = PCValueScanner.hasNextInt() ? PCValueScanner.nextInt(): 0;

                String InstructionValue = InstructionValueScanner.next();

                int MemoryValue = MemoryValueScanner.hasNextInt() ? MemoryValueScanner.nextInt(): 0;

                getTextBox().setAccValue(ACCValue);
                getTextBox().setPCValue(PCValue);
                getTextBox().setInstruction(InstructionValue);
                getTextBox().setMemoryValue(MemoryValue);


            }
        }
    }
}
