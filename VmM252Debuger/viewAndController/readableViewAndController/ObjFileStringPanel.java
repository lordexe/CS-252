package viewAndController.readableViewAndController;

import java.awt.*;
import javax.swing.*;

import model.VM252DebuggerModel;
import observation.*;
public class ObjFileStringPanel extends JPanel implements Observer {
    private static final int OUR_DEFAULT_WIDTH = 500;
    private static final int OUR_DEFAULT_HEIGHT = 300;

    private JPanel myPanel;
    private VM252DebuggerModel mySubject;
    private String content;
    private JTextArea displayBox;
    
    // Accesstors

    private JPanel getPanel(){
        return myPanel;
    }

    private VM252DebuggerModel getSubject(){
        return mySubject;
    }

    private String getContent(){
        return content;
    }

    private JTextArea getDisplayBox(){
        return displayBox;
    }

    // Murators

    private void setPanel(JPanel other)
    {
        myPanel = other;
    }

    private void setContents(String other)
    {
        content = other;
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

    // Ctors 

    public ObjFileStringPanel(){
        this(null);
    }
    public ObjFileStringPanel(VM252DebuggerModel machine){
        setSubject(machine);
        setContents(machine.getParsedInstructions());

        setPanel(new JPanel());

        setDisplayBox(new JTextArea("Obj File Instructions:" + "\n" + "\n" + "Prog Count | OPCODE | Opcode Hex | Operand Hex " + "\n" + getContent()));
        //getDisplayBox().setBounds(150,25, OUR_DEFAULT_WIDTH,OUR_DEFAULT_HEIGHT);
        getDisplayBox().setBackground(new Color(255,255,255));
        getDisplayBox().setForeground(Color.BLACK);
        getDisplayBox().setLineWrap(true);
        getDisplayBox().setEditable(false);

        JScrollPane scroll = new JScrollPane(getDisplayBox());
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(50, 2, 200, 200);

        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);


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

    @Override
    public void update() {
        // TODO Auto-generated method stub
        
    }

}
