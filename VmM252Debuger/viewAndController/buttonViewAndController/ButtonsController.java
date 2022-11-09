package viewAndController.buttonViewAndController;

import java.awt.*;
import java.util.Scanner;
import java.awt.event.*;
import javax.swing.*;

import model.VM252DebuggerModel;


public class ButtonsController extends JPanel
{
    private JPanel myPanel;
    private VM252DebuggerModel myModel;

    // toolbar

    private JToolBar tool_bar;

    // buttons

    private JButton Help_h, Cmd_n, Cmd_q, Cmd_r, Cmd_ba, stop, resume, instructionIncrease, instructionDecrease ;
    private JLabel toolbarLabel, baLabel;
    private JTextField input_ba;

    //Accessors

    private JPanel getPanel()
    {
        return myPanel;
    }

    private VM252DebuggerModel getModel()
    {
        return myModel;
    }

    // Murators

    private void setPanel(JPanel other)
    {
        myPanel = other;
    }

    private void setModel(VM252DebuggerModel other)
    {
        myModel = other;
    }

    //
    // Constructors
    //
    
    public ButtonsController()
    {
        this(null);
    }

    public ButtonsController(VM252DebuggerModel initialModel)
    {
        setModel(initialModel);

        // create a new toolbar

        tool_bar = new JToolBar();

        // Create the buttons

        toolbarLabel = new JLabel("Toolbar ");
        Cmd_n = new JButton(" n ");
        Cmd_q = new JButton(" q ");
        Cmd_r = new JButton(" r ");
        Cmd_ba = new JButton(" ba ");

        baLabel = new JLabel(" ba: ");
        input_ba = new JTextField("enter value for ba", 10);
        stop = new JButton("Stop");
        resume = new JButton("Resume");
        instructionIncrease = new JButton("Increase Speed");
        instructionDecrease = new JButton ("Decrease Speed");
        Help_h = new JButton(" Help ");

        // Help button functionality
        // Prints what different commands are used for
        Help_h.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
                String [] helpContents = {"ba MA = Set a breakpoint at address MA",
                "help = Display help messages",
                "n = Execute the next machine instruction",
                "q = Quit",
                "r = Run machine until error occurs or stop instruction is triggered"
                };
		        getModel().setDisplayContents(helpContents);
            }});

        // Quit button functionality
        // Closes the program upon clicking on the button
        ActionListener quitActListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // close the current JFrame
                ((JFrame)myPanel.getTopLevelAncestor()).dispose();
            }
        };

        Cmd_q.addActionListener(quitActListener);

        
            
        // Add the buttons to the toolbar

        tool_bar.setFloatable(false);
        tool_bar.add(toolbarLabel);
        tool_bar.add(Cmd_n);
        tool_bar.add(Cmd_q);
        tool_bar.add(Cmd_r);
        tool_bar.add(Cmd_ba);
        tool_bar.add(baLabel);
        tool_bar.add(input_ba);
        tool_bar.add(stop);
        tool_bar.add(resume);
        tool_bar.add(instructionIncrease);
        tool_bar.add(instructionDecrease);
        tool_bar.add(Help_h);


        setPanel(new JPanel());
        getPanel().add(tool_bar);

        tool_bar.setBackground(new Color(200,200,200));


        //
        // Add the panel to the container
        //

        add(getPanel());
    }
}
