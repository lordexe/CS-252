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
        setTitle("VM252 Debuger GUI");
        setSize(OUR_DEFAULT_WIDTH, OUR_DEFAULT_HEIGHT);
        
        // Create Model Object 
    
        VM252DebuggerModel machine = new VM252DebuggerModel(objProgram);

        // create text FieldView pannel

        textFieldViewAndController DisplayPanel = new textFieldViewAndController(machine);
        machine.attach(DisplayPanel);
    
        // Create button pannel
    
        buttonsPanel buttonsPanel = new buttonsPanel(machine);
        // Create running debuger process pannel
    
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

        getPanel().add(Displays, BorderLayout.CENTER);

        // buttonsPanel.setBounds(0, 0, 800, 100);
        getPanel().add(buttonsPanel, BorderLayout.WEST);

        // DisplayPanel.setBounds(500,100,300,300);
        getPanel().add(DisplayPanel, BorderLayout.EAST);
    

    
    
        add(getPanel());
    
    }
}
    