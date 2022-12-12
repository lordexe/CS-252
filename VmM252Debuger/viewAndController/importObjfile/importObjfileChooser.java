package viewAndController.importObjfile;
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
        // to only allow choices of files of type vm252 obj
        FileNameExtensionFilter filter = new FileNameExtensionFilter("ObjectFilesFOrTesting", "vm252obj");
        fileChooser.setFileFilter(filter);

        // Specify the location to be the current directory for the file chooser search
        fileChooser.setCurrentDirectory(new File("./ObjectFilesFOrTesting"));

        // assign the result from file choices to response
        // it is int because it will be 0 if a file was chosen
        // it will be 1 if no file was choosen, and user clicked x or cancel
        int response = fileChooser.showOpenDialog(null);

        // if there was a file chosen to open,
        // get the file name from file chooser
        // get the object code from the choosen file
        // then feed that into the program
        if(response == JFileChooser.APPROVE_OPTION) {
            String file = fileChooser.getSelectedFile().getPath();
            byte [] program = VM252Utilities.readObjectCodeFromObjectFile(file);
            ProgramFrame frame = new ProgramFrame(program);
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
