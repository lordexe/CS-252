package viewAndController.buttonViewAndController;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import model.VM252DebuggerModel;
import model.VM252DebuggerModel.StoppedCategory;


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
        input_ba = new JTextField("", 10);
        stop = new JButton("Hold");
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

        //
        // Add action listener for stop and resume command
        //

            
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
                    getModel().setDisplayContents(new String[] {"set breakpoint at address " + breakPointPosition});
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
                        getModel().setDisplayContents(new String [] {"Hit breakpoint at address " + getModel().getBreakPoint() });
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
