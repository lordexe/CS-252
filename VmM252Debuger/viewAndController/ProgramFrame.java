package viewAndController;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

import model.VM252DebuggerModel;
import viewAndController.JtextViewAndController.textFieldViewAndController;
import viewAndController.buttonViewAndController.*;
import viewAndController.readableViewAndController.*;
public class ProgramFrame extends JFrame{

    private static final int OUR_DEFAULT_WIDTH = 800;
    private static final int OUR_DEFAULT_HEIGHT = 1000;

    private JPanel myPanel;

    //Accessor

    private JPanel getPanel()
    {
        return myPanel;
    }

    //Murator

    private void setPanel(JPanel other){
        myPanel = other;
    }

    // Ctors
    
    public ProgramFrame(byte [] objProgram) {
    
        //Set title
        setTitle("VM252 Debugger GUI");
        setSize(OUR_DEFAULT_WIDTH, OUR_DEFAULT_HEIGHT);
        
        // Create Model Object 
        // input: objPrgram as byte [] from .vm252obj
    
        VM252DebuggerModel machine = new VM252DebuggerModel(objProgram);

        // create text FieldView pannel
        // give access to the Model
        // attach DisplayPanel to link Model to textFieldViewAndController 
        // preference from the Vm252Simulation stepper.java

        textFieldViewAndController DisplayPanel = new textFieldViewAndController(machine);
        machine.attach(DisplayPanel);
    
        // Create button pannel
        // get current instance and make call instance to Model
    
        buttonsPanel buttonsPanel = new buttonsPanel(machine);

        // Create running debuger process pannel
        // get update from the accounce change ( announceChange() ) belong to observation -- observer
        DisplayRunPanel runningPanel = new DisplayRunPanel(machine);

        ObjFileStringPanel ObjStringPanel = new ObjFileStringPanel(machine);
    
        // Create panel to hold all views and controllers
    
        setPanel(new JPanel());
        getPanel().setLayout(new BorderLayout());
    
        // Add and setBounds, views and controllers to panel

        JPanel Displays = new JPanel();
        Displays.setLayout(new GridLayout(2,1));
        Displays.add(runningPanel);
        Displays.add(ObjStringPanel);

        // style single feature to the pannel
        getPanel().add(Displays, BorderLayout.CENTER);
        getPanel().add(buttonsPanel, BorderLayout.WEST);
        getPanel().add(DisplayPanel, BorderLayout.EAST);
    
        add(getPanel());
    
    }
}
    