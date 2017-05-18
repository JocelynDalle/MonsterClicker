package hb.com.energyclicker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Jocelyn on 16/05/2017.
 */

public class SaveManager {


    public void loadSaveFile() {
        try {
            FileInputStream fis = new FileInputStream("energyclicker.xml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
