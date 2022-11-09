package viewAndController.buttonViewAndController;

import model.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class buttonsPanel extends JPanel{

    private static final int OUR_DEFAULT_WIDTH = 600;
    private static final int OUR_DEFAULT_HEIGHT = 300;

    private JPanel myPanel;

    //
    // Accessors
    //

    private JPanel getPanel(){
        return myPanel;
    }

    //
    // Mutators
    //

    private void setPanel(JPanel other){
        myPanel = other;
    }

    //
    // Ctors
    //

    public buttonsPanel(){
        this(null);
    }

    public buttonsPanel(VM252DebuggerModel machine){

        //
        // Create button controller from Buttons Controller
        //

        ButtonsController buttons = new ButtonsController(machine);

        setPanel(new JPanel());
        getPanel().setSize(OUR_DEFAULT_WIDTH, OUR_DEFAULT_HEIGHT);
        getPanel().add(buttons);

        add(getPanel());

    }
}