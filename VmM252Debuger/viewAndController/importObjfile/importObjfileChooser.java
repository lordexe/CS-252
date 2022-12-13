package viewAndController.importObjfile;

// sample of code using JFChooser
// https://mkyong.com/swing/java-swing-jfilechooser-example/

import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.*;
import viewAndController.ProgramFrame;
import vm252utilities.VM252Utilities;



public class importObjfileChooser extends JFileChooser{

    public void importObjfileChooser() {

        // Create a new File chooser from JFileChooser()
        JFileChooser fileChooser = new JFileChooser();

        // Adding filter to the file chooser
        // to only allow choose files type ".vm252obj"
        FileNameExtensionFilter filter = new FileNameExtensionFilter("ObjectFilesFOrTesting", "vm252obj");
        fileChooser.setFileFilter(filter);

        // Specify the location to be the current directory for the file chooser search
        // Purpose: easier to access exist .vm252obj file with out to through directory from root
        fileChooser.setCurrentDirectory(new File("./ObjectFilesFOrTesting"));

        // check variable after choose file 
        // 0 if a file was chosen || 1 if no file was choosen, || user clicked x or cancel
        // purpose make sure file already access
        int response = fileChooser.showOpenDialog(null);

        // if there was a file chosen to open,
        // get the file name from file chooser
        // get the object code from the choosen file by using VM252Utilities to read file
        // then feed that into the program by byte [] of converted .vm252obj 
        if(response == JFileChooser.APPROVE_OPTION) {
            String file = fileChooser.getSelectedFile().getPath();
            byte [] program = VM252Utilities.readObjectCodeFromObjectFile(file);
            ProgramFrame frame = new ProgramFrame(program);

            // Frame status
            frame.setSize(900,550);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
        // if no file was choosen
        // exit the program
        else{
            System.exit(0);
        }
    }
}
