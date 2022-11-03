class ObservableRGBValue extends SimpleObservable
{

    private int myRedValue;
    private int myGreenValue;
    private int myBlueValue;

    public int getRedValue()
    {

        return myRedValue;

        }

    public int getGreenValue()
    {

        return myGreenValue;

        }

    public int getBlueValue()
    {

        return myBlueValue;

        }

    public void setRedValue(int other)
    {

        myRedValue = other;

        announceChange();

        }

    public void setGreenValue(int other)
    {

        myGreenValue = other;

        announceChange();

        }

    public void setBlueValue(int other)
    {

        myBlueValue = other;

        announceChange();

        }

    ObservableRGBValue()
    {

        super();

        setRedValue(0);
        setGreenValue(0);
        setBlueValue(0);

        }

    ObservableRGBValue(int initialRedValue, int initialGreenValue, int initialBlueValue)
    {

        super();

        setRedValue(initialRedValue);
        setGreenValue(initialGreenValue);
        setBlueValue(initialBlueValue);

        }

    }