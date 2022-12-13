import java.awt.*;

import viewAndController.importObjfile.importObjfileChooser;
public class Main{
    public static void main(String [] commandLineArguments)
    {
        EventQueue.invokeLater(
            () ->{
                    //
                    // create a new importObjfileChooser to Import Obj file (.vm252obj)
                    //
                    importObjfileChooser File = new importObjfileChooser();
                    File.importObjfileChooser();

                }

            );

    }
}