import model.SimpleObservable;
import model.SimpleObserver;

public class ObserverExample
{

    public static void main(String [] commandLineArguments)
    {

        ObservableInt I = new ObservableInt(0);

        PrintingIntObserver PIO = new PrintingIntObserver(I);
        UniformIntObserver UIO = new UniformIntObserver(I);

        I.setState(1);
        I.setState(2);

        I.detach(UIO);

        I.setState(3);
        I.setState(4);

        I.attach(UIO);

        I.setState(5);
        I.setState(6);

        }

    }


class ObservableInt extends SimpleObservable
{

    private int myState;

    ObservableInt(int initialState)
    {

        super();

        myState = initialState;

        }

    public int getState()
    {

        return myState;

        }

    public void setState(int other)
    {

        myState = other;

        announceChange();

        }

    }


class IntObserver implements SimpleObserver
{

    private ObservableInt mySubject;
    private int myObservedState;

    public int getObservedState()
    {

        return myObservedState;

        }

    public IntObserver(ObservableInt initialSubject)
    {

        mySubject = initialSubject;
        myObservedState = mySubject.getState();
        mySubject.attach(this);

        }


    public void setSubject(ObservableInt subject)
    {

        mySubject.detach(this);
        mySubject = subject;
        myObservedState = mySubject.getState();
        mySubject.attach(this);

        }

    @Override
    public void update()
    {

        myObservedState = mySubject.getState();

        }

    }


class PrintingIntObserver extends IntObserver
{

    public PrintingIntObserver(ObservableInt initialSubject)
    {

        super(initialSubject);

        System.out.println("Observed initial " + getObservedState());

        }

    @Override
    public void update()
    {

        super.update();

        System.out.println("Observed change " + getObservedState());

        }

    }


class UniformIntObserver extends IntObserver
{

    public UniformIntObserver(ObservableInt initialSubject)
    {

        super(initialSubject);

        System.out.println("Observed");

        }

    @Override
    public void update()
    {

        super.update();

        System.out.println("Observed");

        }

    }
