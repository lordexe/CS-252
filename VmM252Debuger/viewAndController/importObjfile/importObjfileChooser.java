package viewAndController.importObjfile;
// https://mkyong.com/swing/java-swing-jfilechooser-example/

import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.*;
import viewAndController.ProgramFrame;
import vm252utilities.VM252Utilities;



public class importObjfileChooser extends JFileChooser{

    public void importObjfileChooser() {

        // Create file chooser from JFilechooser
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        // set filter for Object file " *.vm252obj "
        FileNameExtensionFilter filter = new FileNameExtensionFilter("vm252Obj Files", "vm252obj");
        fileChooser.setFileFilter(filter);

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION){
            File selectedFile = fileChooser.getSelectedFile();

            // use selected directory for VM252Ulitites
            String filePath = selectedFile.getPath();
            System.out.println(filePath);

            // 

            byte [] objPrograme = VM252Utilities.readObjectCodeFromObjectFile(filePath);


            // send the Prgrame byte [] to 
            ProgramFrame frame = new ProgramFrame();

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
    }
}
