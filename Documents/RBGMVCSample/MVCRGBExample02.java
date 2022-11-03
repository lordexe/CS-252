import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import javax.swing.*;


public class MVCRGBExample02
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
                    // Create view frame
                    //

                        SwatchView swatch = new SwatchView(color);

                    //
                    // Create hybrid view-controller frame
                    //

                        RGBValuesViewAndController rgbValues =
                            new RGBValuesViewAndController(color);

                    //
                    // Create controller frame
                    //

                        ButtonsController buttons = new ButtonsController(color);

                    //
                    // Set frames' visibility and closing behavior
                    //

                        swatch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        swatch.setVisible(true);

                        rgbValues.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        rgbValues.setVisible(true);

                        buttons.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        buttons.setVisible(true);

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


class RGBValuesViewAndController extends JFrame implements SimpleObserver
{

    private static final int OUR_DEFAULT_FRAME_WIDTH = 300;
    private static final int OUR_DEFAULT_FRAME_HEIGHT = 300;
    private static final int OUR_DEFAULT_COMPONENT_FIELD_AND_AREA_WIDTH = 5;

    private JPanel myPanel;
    private ObservableRGBValue mySubjectModel;
    private JTextField myRedValueTextField;
    private JTextField myGreenValueTextField;
    private JTextField myBlueValueTextField;

    //
    // Accessors
    //

    private JPanel getPanel()
    {

        return myPanel;

        }

    private ObservableRGBValue getSubjectModel()
    {

        return mySubjectModel;

        }

    private JTextField getRedValueTextField()
    {

        return myRedValueTextField;

        }

    private JTextField getGreenValueTextField()
    {

        return myGreenValueTextField;

        }

    private JTextField getBlueValueTextField()
    {

        return myBlueValueTextField;

        }

    //
    // Mutators
    //

    private void setPanel(JPanel other)
    {

        myPanel = other;

        }

    private void setSubjectModel(ObservableRGBValue other)
    {

        if (getSubjectModel() != null)
            getSubjectModel().detach(this);

        mySubjectModel = other;

        if (getSubjectModel() != null)
            getSubjectModel().attach(this);

        }

    private void setRedValueTextField(JTextField other)
    {

        myRedValueTextField = other;

        }

    private void setGreenValueTextField(JTextField other)
    {

        myGreenValueTextField = other;

        }

    private void setBlueValueTextField(JTextField other)
    {

        myBlueValueTextField = other;

        }

    //
    // Ctors
    //

    public RGBValuesViewAndController()
    {

        this(null);

        }

    public RGBValuesViewAndController(ObservableRGBValue initialRgb)
    {

        setTitle("RGB Values/Input");

        setSize(OUR_DEFAULT_FRAME_WIDTH, OUR_DEFAULT_FRAME_HEIGHT);

        setSubjectModel(initialRgb);

        //
        // Create text fields for displaying and altering the color levels of the model
        //

            setRedValueTextField(
                new JTextField(
                    "" + getSubjectModel().getRedValue(),
                    OUR_DEFAULT_COMPONENT_FIELD_AND_AREA_WIDTH
                    )
                );
            setGreenValueTextField(
                new JTextField(
                    "" + getSubjectModel().getGreenValue(),
                    OUR_DEFAULT_COMPONENT_FIELD_AND_AREA_WIDTH
                    )
                );
            setBlueValueTextField(
                new JTextField(
                    "" + getSubjectModel().getBlueValue(),
                    OUR_DEFAULT_COMPONENT_FIELD_AND_AREA_WIDTH
                    )
                );

        //
        // Create labels for the text fields
        //

            JLabel redLabel = new JLabel("Red:", JLabel.RIGHT);
            JLabel greenLabel = new JLabel("Green:", JLabel.RIGHT);
            JLabel blueLabel = new JLabel("Blue:", JLabel.RIGHT);

        //
        // Create a panel to hold the labels and the text fields
        //

            setPanel(new JPanel());
            getPanel().setLayout(new GridLayout(3, 2));

        //
        // Add the labels and the text fields, in the order they should appear, to the
        // panel
        //

            getPanel().add(redLabel);
            getPanel().add(getRedValueTextField());
            getPanel().add(greenLabel);
            getPanel().add(getGreenValueTextField());
            getPanel().add(blueLabel);
            getPanel().add(getBlueValueTextField());

        //
        // Add the panel to the container
        //

            add(getPanel());

        //
        // Create an action to alter the model's color when input is entered
        //

            RGBInputAction inputAction = new RGBInputAction();

        //
        // Associate the action with the text fields
        //

            getRedValueTextField().addActionListener(inputAction);
            getGreenValueTextField().addActionListener(inputAction);
            getBlueValueTextField().addActionListener(inputAction);

        }

    //
    // Observation methods
    //

    @Override
    public void update()
    {

        //
        // Set the text in the text fields to display the color levels of the updated
        // color specified by the model
        //

            getRedValueTextField().setText("" + getSubjectModel().getRedValue());
            getGreenValueTextField().setText("" + getSubjectModel().getGreenValue());
            getBlueValueTextField().setText("" + getSubjectModel().getBlueValue());

        }


    private class RGBInputAction implements ActionListener
    {

        //
        // Ctors
        //

        public RGBInputAction()
        {

            }

        //
        // Event handlers
        //

        @Override
        public void actionPerformed(ActionEvent event)
        {

            if (getSubjectModel() != null) {

                //
                // Let redValue, greenValue, and blueValue be the integers typed in the
                // red, green, and blue value text fields, respectively (using 0 as the
                // value for a text field that does not contain an integer or contains
                // a negative integer and using 255 as the value for a text field that
                // contains an integer greater than 255)
                //

                    Scanner redValueScanner =
                        new Scanner(getRedValueTextField().getText());
                    Scanner greenValueScanner =
                        new Scanner(getGreenValueTextField().getText());
                    Scanner blueValueScanner =
                        new Scanner(getBlueValueTextField().getText());

                    int redValue =
                        redValueScanner.hasNextInt()
                            ? Math.max(0, Math.min(255, redValueScanner.nextInt()))
                            : 0;
                    int greenValue =
                        greenValueScanner.hasNextInt()
                            ? Math.max(0, Math.min(255, greenValueScanner.nextInt()))
                            : 0;
                    int blueValue =
                        blueValueScanner.hasNextInt()
                            ? Math.max(0, Math.min(255, blueValueScanner.nextInt()))
                            : 0;

                //
                // Alter the red, green, and blue levels of the model to the redValue,
                // greenValue, and blueValue, respectively
                //

                    getSubjectModel().setRedValue(redValue);
                    getSubjectModel().setGreenValue(greenValue);
                    getSubjectModel().setBlueValue(blueValue);

                }

            }

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
