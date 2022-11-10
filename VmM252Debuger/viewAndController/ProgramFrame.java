package viewAndController;

import javax.swing.*;

import model.VM252DebuggerModel;
import viewAndController.JtextViewAndController.buttonsViewAndController;
import viewAndController.buttonViewAndController.buttonsPanel;
import viewAndController.readableViewAndController.DisplayRunPanel;
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
    
        VM252DebuggerModel machine = new VM252DebuggerModel();
    
        // Create button pannel
    
        buttonsPanel buttonsPanel = new buttonsPanel(machine);
    
        // Create hybrid view-controller pannel
        
        buttonsViewAndController DisplayPanel = new buttonsViewAndController(machine);
    
        // Create running debuger process pannel
    
        DisplayRunPanel runningPanel = new DisplayRunPanel(machine);
    
        // Create panel to hold all views and controllers
    
        setPanel(new JPanel());
        getPanel().setLayout(null);
    
        // Add and setBounds, views and controllers to panel
        
        DisplayPanel.setBounds(500,100,300,200);
        getPanel().add(DisplayPanel);
    
        buttonsPanel.setBounds(0, 0, 800, 100);
        getPanel().add(buttonsPanel);
    
        runningPanel.setBounds(0, 100, 500, 300);
        getPanel().add(runningPanel);
    
    
        add(getPanel());
    
    }
}
    