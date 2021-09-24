import java.io.*;

import javax.swing.SwingUtilities;

public class principal {

	public static void main(String[] args) throws IOException{
	
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI();
            }
        });
	
	}

}