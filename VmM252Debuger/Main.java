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

    private static final int OUR_DEFAULT_WIDTH = 1000;
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
    }
}