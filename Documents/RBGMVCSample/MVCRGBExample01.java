import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import javax.swing.*;


public class MVCRGBExample01
{

    public static void main(String [] commandLineArguments)
    {

        EventQueue.invokeLater(
            () ->
                {

                    //
                    // Create model object
                    //

                        ObservableRGBValue color = new ObservableRGBValue(0, 0, 0);

                    //
                    // Create view frames
                    //

                        SwatchView swatch = new SwatchView(color);
                        RGBValuesView rgbValues = new RGBValuesView(color);

                    //
                    // Create controller frames
                    //

                        ButtonsController buttons = new ButtonsController(color);
                        HexadecimalInputController hexadecimalInput
                            = new HexadecimalInputController(color);

                    //
                    // Set frames' visibility and closing behavior
                    //

                        swatch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        swatch.setVisible(true);

                        rgbValues.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        rgbValues.setVisible(true);

                        buttons.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        buttons.setVisible(true);

                        hexadecimalInput.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        hexadecimalInput.setVisible(true);

                    }

            );

        }

    }


class SwatchView extends JFrame implements SimpleObserver
{

    private static final int OUR_DEFAULT_WIDTH = 300;
    private static final int OUR_DEFAULT_HEIGHT = 300;

    private JPanel myPanel;
    private ObservableRGBValue mySubject;

    //
    // Accessors
    //

    private JPanel getPanel()
    {

        return myPanel;

        }

    private ObservableRGBValue getSubject()
    {

        return mySubject;

        }

    //
    // Mutators
    //

    private void setPanel(JPanel other)
    {

        myPanel = other;

        }

    public void setSubject(ObservableRGBValue other)
    {

        if (getSubject() != null)
            getSubject().detach(this);

        mySubject = other;

        if (getSubject() != null)
            getSubject().attach(this);

        }

    //
    // Ctors
    //

    public SwatchView()
    {

        this(null);

        }

    public SwatchView(ObservableRGBValue initialRgb)
    {

        setTitle("Swatch");

        setSize(OUR_DEFAULT_WIDTH, OUR_DEFAULT_HEIGHT);

        setSubject(initialRgb);

        //
        // Create a panel with which to display a swatch of the model's color
        //

            setPanel(new JPanel());

        //
        // Add the panel to the container
        //

            add(getPanel());

        //
        // Initially display the model's color or (if there is no model) black
        //

            if (getSubject() != null)

                getPanel().setBackground(
                    new Color(
                        getSubject().getRedValue(),
                        getSubject().getGreenValue(),
                        getSubject().getBlueValue()
                        )
                    );

            else

                getPanel().setBackground(new Color(0, 0, 0));

        //
        // Change the layout of the container of swatch panel so that swatch is maximally
        // sized (this layout change is unnecessary when the container of the swatch panel
        // is a JFrame but necessary when the container is a JPanel)
        //

            setLayout(new GridLayout(1, 1));

        }

    //
    // Observation methods
    //

    @Override
    public void update()
    {

        //
        // Display the updated color specified by the model
        //

            getPanel().setBackground(
                new Color(
                    getSubject().getRedValue(),
                    getSubject().getGreenValue(),
                    getSubject().getBlueValue()
                    )
                );

        }

    }


class RGBValuesView extends JFrame implements SimpleObserver
{

    private static final int OUR_DEFAULT_WIDTH = 300;
    private static final int OUR_DEFAULT_HEIGHT = 300;

    private JPanel myPanel;
    private ObservableRGBValue mySubject;
    private JLabel myRedValueLabel;
    private JLabel myGreenValueLabel;
    private JLabel myBlueValueLabel;

    //
    // Accessors
    //

    private JPanel getPanel()
    {

        return myPanel;

        }

    private ObservableRGBValue getSubject()
    {

        return mySubject;

        }

    private JLabel getRedValueLabel()
    {

        return myRedValueLabel;

        }

    private JLabel getGreenValueLabel()
    {

        return myGreenValueLabel;

        }

    private JLabel getBlueValueLabel()
    {

        return myBlueValueLabel;

        }

    //
    // Mutators
    //

    private void setPanel(JPanel other)
    {

        myPanel = other;

        }

    public void setSubject(ObservableRGBValue other)
    {

        if (getSubject() != null)
            getSubject().detach(this);

        mySubject = other;

        if (getSubject() != null)
            getSubject().attach(this);

        }

    private void setRedValueLabel(JLabel other)
    {

        myRedValueLabel = other;

        }

    private void setGreenValueLabel(JLabel other)
    {

        myGreenValueLabel = other;

        }

    private void setBlueValueLabel(JLabel other)
    {

        myBlueValueLabel = other;

        }

    //
    // Ctors
    //

    public RGBValuesView()
    {

        this(null);

        }

    public RGBValuesView(ObservableRGBValue initialRgb)
    {

        setTitle("RGB Values");

        setSize(OUR_DEFAULT_WIDTH, OUR_DEFAULT_HEIGHT);

        setSubject(initialRgb);

        //
        // Create labels to display the color levels of the model
        //

            setRedValueLabel(new JLabel(""));
            setGreenValueLabel(new JLabel(""));
            setBlueValueLabel(new JLabel(""));

        //
        // Set the text of the labels to display the color levels of the updated color
        // specified by the model (or "????" if ther eis no current model)
        //

            if (getSubject() == null) {

                getRedValueLabel().setText("Red: ????");
                getGreenValueLabel().setText("Green: ????");
                getBlueValueLabel().setText("Blue: ????");

                }

            else {

                getRedValueLabel().setText("Red: " + getSubject().getRedValue());
                getGreenValueLabel().setText("Green: " + getSubject().getGreenValue());
                getBlueValueLabel().setText("Blue: " + getSubject().getBlueValue());

                }

        //
        // Create a panel to hold all the labels
        //

            setPanel(new JPanel());
            getPanel().setLayout(new BoxLayout(getPanel(), BoxLayout.Y_AXIS));

        //
        // Add the labels to the panel
        //

            getPanel().add(getRedValueLabel());
            getPanel().add(getGreenValueLabel());
            getPanel().add(getBlueValueLabel());

        //
        // Add the panel holding the labels to the container
        //

            add(getPanel());

        }

    //
    // Observation methods
    //

    @Override
    public void update()
    {

        //
        // Set the text of the labels to display the color levels of the updated color
        // specified by the model
        //

            getRedValueLabel().setText("Red: " + getSubject().getRedValue());
            getGreenValueLabel().setText("Green: " + getSubject().getGreenValue());
            getBlueValueLabel().setText("Blue: " + getSubject().getBlueValue());

        repaint();

        }

    }


class ButtonsController extends JFrame
{

    private static final int OUR_DEFAULT_WIDTH = 300;
    private static final int OUR_DEFAULT_HEIGHT = 300;

    private JPanel myPanel;
    private ObservableRGBValue myModel;

    //
    // Accessors
    //

    private JPanel getPanel()
    {

        return myPanel;

        }

    private ObservableRGBValue getModel()
    {

        return myModel;

        }

    //
    // Mutators
    //

    private void setPanel(JPanel other)
    {

        myPanel = other;

        }

    public void setModel(ObservableRGBValue other)
    {

        myModel = other;

        }

    //
    // Ctors
    //

    public ButtonsController()
    {

        this(null);

        }

    public ButtonsController(ObservableRGBValue initialModel)
    {

        setTitle("Buttons");

        setSize(OUR_DEFAULT_WIDTH, OUR_DEFAULT_HEIGHT);

        setModel(initialModel);

        //
        // Create buttons to alter the model's color
        //

            JButton redButton = new JButton("Red");
            JButton orangeButton = new JButton("Orange");
            JButton yellowButton = new JButton("Yellow");
            JButton greenButton = new JButton("Green");
            JButton blueButton = new JButton("Blue");
            JButton indigoButton = new JButton("Indigo");
            JButton violetButton = new JButton("Violet");

        //
        // Create a panel to hold all the buttons
        //

            setPanel(new JPanel());
            getPanel().setLayout(new GridLayout(3, 3));

        //
        // Add the buttons to the panel
        //

            getPanel().add(redButton);
            getPanel().add(orangeButton);
            getPanel().add(yellowButton);
            getPanel().add(greenButton);
            getPanel().add(blueButton);
            getPanel().add(indigoButton);
            getPanel().add(violetButton);

        //
        // Add the panel to the container
        //

            add(getPanel());

        //
        // Create actions to alter the model's color when the buttons are pressed
        //

            ButtonAction redButtonAction = new ButtonAction(255, 0, 0);
            ButtonAction orangeButtonAction = new ButtonAction(255, 165, 0);
            ButtonAction yellowButtonAction = new ButtonAction(255, 255, 0);
            ButtonAction greenButtonAction = new ButtonAction(0, 255, 0);
            ButtonAction blueButtonAction = new ButtonAction(0, 0, 255);
            ButtonAction indigoButtonAction = new ButtonAction(75, 0, 130);
            ButtonAction violetButtonAction = new ButtonAction(238, 130, 238);

        //
        // Associate the actions with corresponding buttons
        //

            redButton.addActionListener(redButtonAction);
            orangeButton.addActionListener(orangeButtonAction);
            yellowButton.addActionListener(yellowButtonAction);
            greenButton.addActionListener(greenButtonAction);
            blueButton.addActionListener(blueButtonAction);
            indigoButton.addActionListener(indigoButtonAction);
            violetButton.addActionListener(violetButtonAction);

        }


    private class ButtonAction implements ActionListener
    {

        private int myRedValue;
        private int myGreenValue;
        private int myBlueValue;

        //
        // Accessors
        //

        private int getRedValue()
        {

            return myRedValue;

            }

        private int getGreenValue()
        {

            return myGreenValue;

            }

        private int getBlueValue()
        {

            return myBlueValue;

            }

        //
        // Mutators
        //

        private void setRedValue(int other)
        {

            myRedValue = other;

            }

        private void setGreenValue(int other)
        {

            myGreenValue = other;

            }

        private void setBlueValue(int other)
        {

            myBlueValue = other;

            }

        //
        // Ctors
        //

        public ButtonAction(
            int initialRedValue,
            int initialGreenValue,
            int initialBlueValue
            )
        {

            setRedValue(initialRedValue);
            setGreenValue(initialGreenValue);
            setBlueValue(initialBlueValue);

            }

        //
        // Event handlers
        //

        @Override
        public void actionPerformed(ActionEvent event)
        {

            if (getModel() != null) {

                //
                // Alter the color levels of the model to the levels specified for this
                // action
                //

                    getModel().setRedValue(getRedValue());
                    getModel().setGreenValue(getGreenValue());
                    getModel().setBlueValue(getBlueValue());

                }

            }

        }


    }


class HexadecimalInputController extends JFrame
{

    private static final int OUR_DEFAULT_WIDTH = 300;
    private static final int OUR_DEFAULT_HEIGHT = 300;
    private static final int OUR_DEFAULT_COMPONENT_FIELD_AND_AREA_WIDTH = 6;

    private JPanel myPanel;
    private ObservableRGBValue myModel;
    private JTextField myHexadecimalInputField;

    //
    // Accessors
    //

    private JPanel getPanel()
    {

        return myPanel;

        }

    private ObservableRGBValue getModel()
    {

        return myModel;

        }

    private JTextField getHexadecimalInputField()
    {

        return myHexadecimalInputField;

        }

    //
    // Mutators
    //

    private void setPanel(JPanel other)
    {

        myPanel = other;

        }

    private void setModel(ObservableRGBValue other)
    {

        myModel = other;

        }

    private void setHexadecimalInputField(JTextField other)
    {

        myHexadecimalInputField = other;

        }

    //
    // Ctors
    //

    public HexadecimalInputController()
    {

        this(null);

        }

    public HexadecimalInputController(ObservableRGBValue initialModel)
    {

        setTitle("Hexadecimal Input");

        setSize(OUR_DEFAULT_WIDTH, OUR_DEFAULT_HEIGHT);

        setModel(initialModel);

        //
        // Create a text field for entering an RGB value as a hexadecimal integer
        //

            setHexadecimalInputField(
                new JTextField("", OUR_DEFAULT_COMPONENT_FIELD_AND_AREA_WIDTH)
                );

        //
        // Create a label for the text field
        //

            JLabel hexadecimalInputLabel = new JLabel("Hexadecimal Value:", JLabel.RIGHT);

        //
        // Create a panel to hold the label and the text field
        //

            setPanel(new JPanel());
            getPanel().setLayout(new FlowLayout());

        //
        // Add the label and the text field, in that order, to the panel
        //

            getPanel().add(hexadecimalInputLabel);
            getPanel().add(getHexadecimalInputField());

        //
        // Add the panel to the container
        //

            add(getPanel());

        //
        // Create an action to alter the model's color when input is entered
        //

            HexadecimalInputAction inputAction = new HexadecimalInputAction();

        //
        // Associate the action with the text field
        //

            getHexadecimalInputField().addActionListener(inputAction);

        }


    private class HexadecimalInputAction implements ActionListener
    {

        //
        // Ctors
        //

        public HexadecimalInputAction()
        {

            }

        //
        // Event handlers
        //

        @Override
        public void actionPerformed(ActionEvent event)
        {

            if (getModel() != null) {

                //
                // Let rgbValue be the integer (entered in hexadecimal) typed in the text
                // field (or 0, if the text does not constitute a hexadecimal integer)

                    Scanner hexadecimalInputScanner =
                        new Scanner(getHexadecimalInputField().getText());

                    int rgbValue =
                        hexadecimalInputScanner.hasNextInt(16)
                            ? hexadecimalInputScanner.nextInt(16)
                            : 0;

                //
                // Alter the blue, green, and red levels of the model to the least
                // significant 8 bits of rgbValue, the next least significant bits of
                // rgbValue, and the next least significant bits of rgbValue,
                // respectively
                //

                    getModel().setBlueValue(rgbValue & 0xff);
                    getModel().setGreenValue((rgbValue & 0xff00) >> 8);
                    getModel().setRedValue((rgbValue & 0xff0000) >> 16);

                }

            }

        }


    }
