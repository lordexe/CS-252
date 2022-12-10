import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import viewAndController.*;

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