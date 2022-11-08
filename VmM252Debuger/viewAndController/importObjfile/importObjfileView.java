package viewAndController.importObjfile;
// https://mkyong.com/swing/java-swing-jfilechooser-example/

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class importObjfileView{
    public static void main(String [] args) {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION){
            File selectedFile = jfc.getSelectedFile();
            // use Selected to traverse code.
        }
    }
}
