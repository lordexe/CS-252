package viewAndController.readableViewAndController;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JList;

import model.VM252DebuggerModel;
import observation.*;

public class DisplayRunPanel extends JPanel implements Observer
{
    private static final int OUR_DEFAULT_WIDTH = 500;
    private static final int OUR_DEFAULT_HEIGHT = 300;

    private JPanel myPanel;
    private VM252DebuggerModel mySubject;
    private String [] myContents;
    private JTextArea displayBox;

    //
    // Accessors
    //

    private JPanel getPanel()
    {
        return myPanel;
    }

    private VM252DebuggerModel getSubject()
    {
        return mySubject;
    }

    private String [] getContents()
    {
        return myContents;
    }

    private JTextArea getDisplayBox()
    {
        return displayBox;
    }

    //
    // Mutators
    //

    private void setPanel(JPanel other)
    {
        myPanel = other;
    }

    private void setContents(String [] other)
    {
        myContents = other;
    }

    private void setSubject(VM252DebuggerModel other)
    {
        if (getSubject() != null)
            getSubject().detach(this);

        mySubject = other;

        if (getSubject() != null)
            getSubject().attach(this);

    }

    private void setDisplayBox(JTextArea other)
    {
        displayBox = other;
    }

    //
    // Ctors
    //

    public DisplayRunPanel()
    {
        this(null);
    }

    public DisplayRunPanel(VM252DebuggerModel machine)
    {
        setSubject(machine);
        setContents(getSubject().getDisplayContents());

        setPanel(new JPanel());


        //
        // Initially display the model's display contents
        //

        setDisplayBox(new JTextArea("Welcome to Vm252 Debugger" + "\n", 10, 1));
        getDisplayBox().setBounds(150, 25, OUR_DEFAULT_WIDTH, OUR_DEFAULT_HEIGHT);
        getDisplayBox().setBackground(new Color(32, 32, 32));
        getDisplayBox().setForeground(Color.WHITE);
        getDisplayBox().setLineWrap(true);
        getDisplayBox().setEditable(false);

        

        JScrollPane scroll = new JScrollPane(getDisplayBox());
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(150, 25, OUR_DEFAULT_WIDTH, OUR_DEFAULT_HEIGHT);

        setLayout(null);
        add(scroll);

        // change of Display content import inside 

    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        if(!(getSubject().getDisplayContents().length == 1 && getSubject().getDisplayContents()[0] == ""))
        {
            String displayString = "";

            for (String content : getSubject().getDisplayContents())
            {
                displayString = displayString + content + "\n";
            }

            getDisplayBox().append(displayString);
            getDisplayBox().setCaretPosition(getDisplayBox().getDocument().getLength());


        }
        
    }

    @Override
    public void attach(Observer anotherObserver) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void detach(Observer currentObserver) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void announceChange() {
        // TODO Auto-generated method stub
        
    }
}

