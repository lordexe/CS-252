public class buttons extends JPanel
{
    private JPanel myPanel;

    // toolbar

    private JToolBar tool_bar;

    // buttons

    private JButton Help_h, Cmd_n, Cmd_q, Cmd_r, Cmd_ba, stop, resume, instructionIncrease, instructionDecrease ;
    private JLabel toolbarLabel, baLabel;
    private JTextField input_ba;


    private JPanel getPanel()
    {
        return myPanel;
    }

    private ObservableVM252Machine getModel()
    {
        return myModel;
    }


    private void setPanel(JPanel other)
    {
        myPanel = other;
    }

    private void setModel(ObservableVM252Machine other)
    {
        myModel = other;
    }

    //
    // Constructors
    //

    public ButtonController()
    {
        this(null);
    }

    public ButtonController(ObservableVM252Machine initialModel)
    {
        setModel(initialModel);

        // create a new toolbar

        tool_bar = new JToolBar();

        // Create the buttons

        toolbarLabel = new JLabel("Toolbar ");
        Cmd_n = new JButton(" n ");
        Cmd_q = new JButton(" q ");
        Cmd_r = new JButton(" r ");
        baLabel = new JLabel(" ba: ");
        input_ba = new JTextField("enter value for ba", 10);
        stop = new JButton("Stop");
        resume = new JButton("Resume");
        instructionIncrease = new JButton("Increase Speed");
        instructionDecrease = new JButton ("Decrease Speed");
        hHelp = new JButton(" Help ");

        // Add the buttons to the toolbar

        tool_bar.setFloatable(false);
        tool_bar.add(toolbarLabel);
        tool_bar.add(Cmd_n);
        tool_bar.add(Cmd_q);
        tool_bar.add(Cmd_r);
        tool_bar.add(baLabel);
        tool_bar.add(input_ba);
        tool_bar.add(resume);
        tool_bar.add(instructionIncrease);
        tool_bar.add(instructionDecrease);


        setPanel(new JPanel());
        getPanel().add(tool_bar);
        getPanel().add(hHelp);

        //
        // Add the panel to the container
        //

        add(getPanel());
    }
}