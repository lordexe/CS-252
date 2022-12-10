package observation;


public interface Observer
{

    void attach(Observer anotherObserver);
        //
        // Register anotherObserver as an observer of this object
        //

    void detach(Observer currentObserver);
        //
        // Unregister currentObserver as an observer of this object
        //
    
    void update();
        //
        // Tell this object that the subject it's observing has changed state
        //

    void announceChange();
    //
    // Announce to all observers of this object that this object has changed state
    //

    }