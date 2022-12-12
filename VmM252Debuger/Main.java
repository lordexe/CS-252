import java.awt.*;

import viewAndController.importObjfile.importObjfileChooser;
public class Main{
    public static void main(String [] commandLineArguments)
    {
        EventQueue.invokeLater(
            () ->{
                    // Import Obj file
                    importObjfileChooser File = new importObjfileChooser();
                    File.importObjfileChooser();

                }

            );

    }
}