// import java.awt.*;
// import java.awt.event.*;
// import javax.swing.*;
// import javax.swing.border.LineBorder;

// public class viewMachinePanel extends JPanel {
//     private static final int OUR_DEFAULT_WIDTH = 600;
//     private static final int OUR_DEFAULT_HEIGHT = 300;


//     private JPanel myPanel;
//         //
//     // Accessors
//     //

//     private JPanel getPanel()
//     {
//         return myPanel;
//     }

//     //
//     // Mutators
//     //

//     private void setPanel(JPanel other)
//     {
//         myPanel = other;
//     }

//     //
//     // Ctor
//     //
//     public viewMachinePanel() {
//         this(null);
//     }

//     public viewMachinePanel(ObservableVM252Debugger machine) {

//         MachineViewAndController displayState = new MachineViewAndController(machine);

//         setPanel(new JPanel());
//         getPanel().setBackground(new Color(25,255,255));

//         setLayout(null);

//         displayState.setBounds(0,0,300,300);
//         add(displayState);
//     }
// }
