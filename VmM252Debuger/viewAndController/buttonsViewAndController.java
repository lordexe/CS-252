package viewAndController;

import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import javax.swing.*;

import model.VM252DebuggerModel;
import observation.*;

public class buttonsViewAndController extends JPanel implements Observer
{
    private static final int OUR_FRAME_WIDTH = 300;
    private static final int OUR_FRAME_HEIGHT = 300;
    private static final int OUR_COMPONENT_FIELD_AND_AREA_WIDTH = 5;


    private JPanel myJPanel;
    private VM252DebuggerModel myButtons;
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
    private VM252DebuggerModel getButtons()
    {
        return myButtons;
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

    private void setButtons(VM252DebuggerModel other)
    {
        if (getButtons() != null)
            getButtons().detach(this);

        myButtons = other;

        if (getButtons() != null)
            getButtons().detach(this);
    }
    //Ctors

    public buttonsViewAndController()
    {
        this (null);
    }



    public buttonsViewAndController(VM252DebuggerModel initialValues)
    {
        setSize(OUR_FRAME_WIDTH, OUR_FRAME_HEIGHT);

        setButtons(initialValues);

        // Creating text fields

        setACCTextFieldValue(new JTextField("" + getButtons().getAccValue(), OUR_COMPONENT_FIELD_AND_AREA_WIDTH));

        setPCTextFieldValue(new JTextField("" + getButtons().getPCValue(), OUR_COMPONENT_FIELD_AND_AREA_WIDTH));

        setInstructionTextFieldValue(new JTextField("" + getButtons().getInstruction(), OUR_COMPONENT_FIELD_AND_AREA_WIDTH));

        setMemoryTextFieldValue(new JTextField("" + getButtons().getMemoryValue(), OUR_COMPONENT_FIELD_AND_AREA_WIDTH));

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

        getACCTextField().setText("" + getButtons().getAccValue());
        getPCTextField().setText("" + getButtons().getPCValue());
        getInstructionTextField().setText("" + getButtons().getInstruction());
        getMemoryTextField().setText("" + getButtons().getMemoryValue());
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
            if (getButtons() != null)
            {
                Scanner ACCValueScanner = new Scanner(getACCTextField().getText());

                Scanner PCValueScanner = new Scanner(getPCTextField().getText());

                Scanner InstructionValueScanner = new Scanner(getInstructionTextField().getText());

                Scanner MemoryValueScanner = new Scanner(getMemoryTextField().getText());

                int ACCValue = ACCValueScanner.hasNextInt()? ACCValueScanner.nextInt():0;

                int PCValue = PCValueScanner.hasNextInt() ? PCValueScanner.nextInt(): 0;

                String InstructionValue = InstructionValueScanner.next();

                int MemoryValue = MemoryValueScanner.hasNextInt() ? MemoryValueScanner.nextInt(): 0;

                getButtons().setAccValue(ACCValue);
                getButtons().setPCValue(PCValue);
                getButtons().setInstruction(InstructionValue);
                getButtons().setMemoryValue(MemoryValue);


            }
        }
    }
}