package viewAndController.buttonViewAndController;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import model.VM252DebuggerModel;
import model.VM252DebuggerModel.StoppedCategory;
import viewAndController.importObjfile.importObjfileChooser;


public class ButtonsController extends JPanel
{
    private JPanel myPanel;
    private VM252DebuggerModel myModel;

    // toolbar

    private JToolBar tool_bar;

    // buttons

    private JButton Help_h, Cmd_n, Cmd_q, Cmd_r, Cmd_ba, Cmd_pf, stop, resume, instructionIncrease, instructionDecrease ;
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

        Cmd_r = new JButton(" Run ");
        stop = new JButton(" Hold ");
        Cmd_n = new JButton(" Next ");
        resume = new JButton(" Resume ");
        Cmd_ba = new JButton(" Break Now ");
        baLabel = new JLabel(" Break Set: ");
        input_ba = new JTextField("", 10);
        instructionIncrease = new JButton(" + Speed ");
        instructionDecrease = new JButton (" - Speed ");
        Help_h = new JButton(" Help ");
        Cmd_pf = new JButton(" New File ");
        Cmd_q = new JButton(" Quit ");






        // Help button functionality
        // Prints what different commands are used for

        Help_h.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
                String [] helpContents = {" ", "----- VM252 Debugger Help -----"," ",
                "Break Set = Set a breakpoint at count provided",
                "help = Display help messages",
                "Next = Execute the next instruction",
                "Run = Start executing the instructions",
                "Quit = Quit the program",
                "Hold = Pause the instruction execution display",
                "Resume = Resume the instruction execution display",
                "+/- Speed = Changee the speed at which instructions are executed", " "
                };
		        getModel().forceSetDisplayContents(helpContents);
            }});

        // Quit button functionality
        // Closes the program upon clicking on the button
        ActionListener quitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // close the current JFrame
                ((JFrame)myPanel.getTopLevelAncestor()).dispose();
            }
        };
        Cmd_q.addActionListener(quitListener);  

        // Pick new file button functionality
        ActionListener pickFileListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // close the current JFrame
                ((JFrame)myPanel.getTopLevelAncestor()).dispose();
                // Initiate new file picket
                importObjfileChooser newFile = new importObjfileChooser();
                // Run the FileChooser to create a new JFrame out of the new chosen file
                newFile.importObjfileChooser();
            }
        };
        Cmd_pf.addActionListener(pickFileListener);  



        //
        // Add action listener for r command button
        //

        RunButtonActionListener runListener = new RunButtonActionListener();
        Cmd_r.addActionListener(runListener);

        //
        // Add action listener for n command button
        //

        RunStepListener runStepListener = new RunStepListener();
        Cmd_n.addActionListener(runStepListener);

        //
        // Add action listener for increase speed and decrease speed command
        //

        ChangeSpeedListener changeSpeedListener = new ChangeSpeedListener();
        instructionIncrease.addActionListener(changeSpeedListener);
        instructionDecrease.addActionListener(changeSpeedListener);

        //
        // Add action listener for stop and resume command
        //

        ChangeRunningStatus changeRunningStatus = new ChangeRunningStatus();
        stop.addActionListener(changeRunningStatus);
        resume.addActionListener(changeRunningStatus);

        //
        // Add action Listener for ba text field
        //

        setBreakPointListener baListener = new setBreakPointListener();
        input_ba.addActionListener(baListener);
            
        // Add the buttons to the toolbar

        tool_bar.setFloatable(false);
        tool_bar.add(Cmd_r);
        tool_bar.add(stop);
        tool_bar.add(Cmd_n);
        tool_bar.add(resume);
        tool_bar.add(Cmd_ba);
        tool_bar.add(baLabel);
        tool_bar.add(input_ba);
        tool_bar.add(instructionIncrease);
        tool_bar.add(instructionDecrease);
        tool_bar.add(Help_h);
        tool_bar.add(Cmd_pf);
        tool_bar.add(Cmd_q);










        setPanel(new JPanel());
        getPanel().add(tool_bar);

        tool_bar.setBackground(new Color(200,200,200));


        //
        // Add the panel to the container
        //

        add(getPanel(), BorderLayout.NORTH);
    }

    private class setBreakPointListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            try
            {
                short breakPointPosition = Short.valueOf(input_ba.getText());
                if(breakPointPosition > 8191 || breakPointPosition < 0)
                {
                    getModel().forceSetDisplayContents(new String[] {"No address" + breakPointPosition});
                    getModel().resetDisplayContents();
                }else
                {
                    getModel().setBreakPoint(breakPointPosition);
                    getModel().forceSetDisplayContents(new String[] {"set breakpoint at address " + breakPointPosition});
                }
            }catch(NumberFormatException err)
            {
                getModel().forceSetDisplayContents(new String [] {"Not a valid input. ba value must be a number"});
                getModel().resetDisplayContents();

            }

        }
    }

    private class RunStepListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            StepExecutionThread runStepThread = new StepExecutionThread();
            runStepThread.start();
        }
    }

    private class RunButtonActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            ExecutionThread runThread = new ExecutionThread();
            runThread.start();
        }
    }

    private class ChangeSpeedListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if (event.getSource() == instructionIncrease)
            {
                int currentSpeed = getModel().getExecutingSpeed();
                getModel().setExecutingSpeed(currentSpeed < 0 ? 0 : (currentSpeed - 500));
            }else if (event.getSource() == instructionDecrease)
            {
                int currentSpeed = getModel().getExecutingSpeed();
                getModel().setExecutingSpeed(currentSpeed + 500);
            }else
                ; // do nothing
        }
    }

    private class ChangeRunningStatus implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if (event.getSource() == stop)
                getModel().setPauseStatus(true);
            else if (event.getSource() == resume)
                getModel().setPauseStatus(false);
            else
                ; //do nothing
        }
    }

    
    private class ExecutionThread extends Thread{
        @Override
        public void run()
        {
            //
            // execute obj file in another thread
            //
            boolean hitBreakPoint = false;
            if(getModel().stoppedStatus() == StoppedCategory.stopped)
            {
                getModel().setDisplayContents(new String [] {"Program stopped"});
            }else
            {
                while(getModel().stoppedStatus() == StoppedCategory.notStopped && !hitBreakPoint)
                {
                    if(getModel().getPauseStatus());
                    // Paused
                    //System.out.println("Here!");

                    // do nothing
                    else if (getModel().getBreakPoint() == getModel().programCounter())
                    {   
                        System.out.println("get model!!");
                        getModel().runProgram();
                        getModel().forceSetDisplayContents(new String [] {"Hit breakpoint at address " + getModel().getBreakPoint() });
                        hitBreakPoint = true;
                    }else
                        getModel().runProgram();

                    try{
                        Thread.sleep(getModel().getExecutingSpeed());
                    }catch(Exception e){}
                }
            }
        }
    }

    private class StepExecutionThread extends Thread{
        @Override
        public void run()
        {
            //
            // execute obj file in another thread
            //
            if(getModel().stoppedStatus() == StoppedCategory.stopped)
            {
                getModel().setDisplayContents(new String [] {"Program stopped"});
            }else
            {
                getModel().runProgram();
            }
        }
    }
}
