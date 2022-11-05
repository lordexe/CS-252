import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class textFields extends JPanel implements SimpleObserver
{
    private static final int OUR_FRAME_WIDTH = 300;
    private static final int OUR_FRAME_HEIGHT = 300;
    private static final int OUR_COMPONENT_FIELD_AND_AREA_WIDTH = 5;


    private JPanel myJPanel;
    private ObserveButtons myButtons;
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
    private ObserveButtons getButtons()
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

    private void setButtons(ObserveButtons)
    {
        if (getButtons() != null)
            getButtons().detatch(this);

        myButtons = other;

        if (getButtons() != null)
            getButtons().detatch(this);
    }
    //Ctors

    public buttonsViewAndController()
    {
        this (null)
    }



    public buttonsViewAndController(ObserveButtons initialValues)
    {
        setSize(OUR_FRAME_WIDTH, OUR_FRAME_HEIGHT);

        setButtons(initialValues);

        // Creating text fields

        setACCTextFieldValue(new JTextField("" + getButtons().getACC(), OUR_COMPONENT_FIELD_AND_AREA_WIDTH));

        setPCTextFieldValue(new JTextField("" + getButtons().getPC(), OUR_COMPONENT_FIELD_AND_AREA_WIDTH));

        setInstructionTextFieldValue(new JTextField("" + getButtons().getInstruction(), OUR_COMPONENT_FIELD_AND_AREA_WIDTH));

        setMemoryTextFieldValue(new JTextField("" + getButtons().getMemory(), OUR_COMPONENT_FIELD_AND_AREA_WIDTH));

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

        getACCTextField().setText("" + getButtons().getACC());
        getPCTextField().setText("" + getButtons().getPC());
        getInstructionTextField().setText("" + getButtons().getInstruction());
        getMemoryTextField().setText("" + getButtons().getMemory());
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

                Scanner InstructionValueScanner = new Scanner(getInctructionTextField().getText());

                Scanner MemoryValueScanner = new Scanner(getMemoryTextField().getText());

                int ACCValue = ACCValueScanner.hasNextInt();

                int PCValue = PCValueScanner.hasNextInt();

                String InstructionValue = InstructionValueScanner.next();

                int MemoryValue = MemoryValueScanner.hasNextInt();
            }
        }
    }
}