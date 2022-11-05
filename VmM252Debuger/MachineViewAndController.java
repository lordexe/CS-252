// import java.awt.*;
// import java.awt.event.*;
// import javax.swing.*;

// class MachineViewAndController extends JPanel implements SimpleObserver
// {

//     private static final int OUR_DEFAULT_FRAME_WIDTH = 300;
//     private static final int OUR_DEFAULT_FRAME_HEIGHT = 300;
//     private static final int OUR_DEFAULT_COMPONENT_FIELD_AND_AREA_WIDTH = 5;

//     private JPanel myPanel;
//     private JTextField myACCTextField;
//     private JTextField myPcTextField;
//     private JTextField myInstructionTextField;
//     private JTextField myMemoryTextField;
//     //
//     // Accessors
//     //

//     private JPanel getPanel()
//     {

//         return myPanel;

//         }

//     private ObservableVM252Debugger mySubjectModel;

//     private ObservableVM252Debugger getSubjectModel()
//     {
//         return mySubjectModel;
//     }


//     private JTextField getACCTextField()
//     {

//         return myACCTextField;

//         }

//     private JTextField getPcTextField()
//     {

//         return myPcTextField;

//         }

//     private JTextField getInstructionTextField()
//     {

//         return myInstructionTextField;

//         }
//     private JTextField getMemoryTextField(){
//         return myMemoryTextField;
//     }
//     //
//     // Mutators
//     //

//     private void setPanel(JPanel other)
//     {

//         myPanel = other;

//         }

//     private void setSubjectModel(ObservableVM252Debugger initialMachine)
//     {

//         if (getSubjectModel() != null)
//             getSubjectModel().detach(this);

//         mySubjectModel = initialMachine;

//         if (getSubjectModel() != null)
//             getSubjectModel().attach(this);

//         }

//     private void setACCTextField(JTextField other)
//     {

//         myACCTextField = other;

//         }

//     private void setPcTextField(JTextField other)
//     {

//         myPcTextField = other;

//         }

//     private void setInstructionTextField(JTextField other)
//     {

//         myInstructionTextField = other;

//         }
    
//     private void setMemoryValueTextField(JTextField other){
//         myMemoryTextField = other;
//     }

//     //
//     // Ctors
//     //

//     public MachineViewAndController()
//     {

//         this(null);

//         }

//     public MachineViewAndController(ObservableVM252Debugger initialMachine)
//     {

//         setSize(OUR_DEFAULT_FRAME_WIDTH, OUR_DEFAULT_FRAME_HEIGHT);

//         setSubjectModel(initialMachine);

//         //
//         // Create text fields for displaying and altering the color levels of the model
//         //

//             setACCTextField(
//                 new JTextField(
//                     "" + getSubjectModel().getAccValue(),
//                     OUR_DEFAULT_COMPONENT_FIELD_AND_AREA_WIDTH
//                     )
//                 );
//             setPcTextField(
//                 new JTextField(
//                     "" + getSubjectModel().getPCValue(),
//                     OUR_DEFAULT_COMPONENT_FIELD_AND_AREA_WIDTH
//                     )
//                 );
//             setInstructionTextField(
//                 new JTextField(
//                     "" + getSubjectModel().getInstruction(),
//                     OUR_DEFAULT_COMPONENT_FIELD_AND_AREA_WIDTH
//                     )
//                 );
//             setMemoryValueTextField(
//                 new JTextField(
//                     "" + getSubjectModel().getMemoryValue(),
//                     OUR_DEFAULT_COMPONENT_FIELD_AND_AREA_WIDTH
//                     )
//                 );

//         //
//         // Create labels for the text fields
//         //

//             JLabel AccLabel = new JLabel("Acc:", JLabel.RIGHT);
//             JLabel PcLabel = new JLabel("Pc:", JLabel.RIGHT);
//             JLabel instructionLabel = new JLabel("Instruction:", JLabel.RIGHT);
//             JLabel memoryLabel = new JLabel("Memory:", JLabel.RIGHT);

//         //
//         // Create a panel to hold the labels and the text fields
//         //

//             setPanel(new JPanel());
//             getPanel().setLayout(new GridLayout(3, 2));

//         //
//         // Add the labels and the text fields, in the order they should appear, to the
//         // panel
//         //

//             getPanel().add(AccLabel);
//             getPanel().add(getACCTextField());
//             getPanel().add(PcLabel);
//             getPanel().add(getPcTextField());
//             getPanel().add(instructionLabel);
//             getPanel().add(getInstructionTextField());
//             getPanel().add(memoryLabel);
//             getPanel().add(getInstructionTextField());

//         //
//         // Add the panel to the container
//         //

//             add(getPanel());
//         }

//     //
//     // Observation methods
//     //

//     @Override
//     public void update()
//     {

//         //
//         // Set the text in the text fields to display the color levels of the updated
//         // color specified by the model
//         //

//             getACCTextField().setText("" + getSubjectModel().getAccValue());
//             getPcTextField().setText("" + getSubjectModel().getPCValue());
//             getInstructionTextField().setText("" + getSubjectModel().getInstruction());
//             getMemoryTextField().setText("" + getSubjectModel().getMemoryValue());

//     }
// }