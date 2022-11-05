import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main{
    public static void main(String [] commandLineArguments)
    {
        EventQueue.invokeLater(
            () ->{
                    ProgramFrame frame = new ProgramFrame();

                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);

                }

            );

    }
}

class ProgramFrame extends JFrame{

    private static final int OUR_DEFAULT_WIDTH = 800;
    private static final int OUR_DEFAULT_HEIGHT = 1000;

    private JPanel myPanel;

    private JPanel getPanel()
    {
        return myPanel;
    }

    private void setPanel(JPanel other){
        myPanel = other;
    }

    public ProgramFrame(){
        setTitle("VM252 Debuger GUI");
        setSize(OUR_DEFAULT_WIDTH, OUR_DEFAULT_HEIGHT);

        ObservableVM252Debugger machine = new ObservableVM252Debugger();

        FunctionButtonsPanel buttonsPanel = new FunctionButtonsPanel(machine);

        buttonsViewAndController DisplayPanel = new buttonsViewAndController(machine);
        
        DisplayRunPanel runningPanel = new DisplayRunPanel(machine);
        setPanel(new JPanel());
        getPanel().setLayout(null);

        buttonsPanel.setBounds(0, 0, 800, 100);
        getPanel().add(buttonsPanel);

        DisplayPanel.setBounds(50,0,800,200);
        getPanel().add(DisplayPanel);

        runningPanel.setBounds(0, 500, 800, 300);
        getPanel().add(runningPanel);


        add(getPanel());

    }
}