package reverser.bayan;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sajad Azami
 */
public class ReverserBayan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get("54.in"), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            Logger.getLogger(ReverserBayan.class.getName()).log(Level.SEVERE, null, ex);
        }
        int rows = Integer.parseInt(lines.get(0));
        for (int i = 1; i <= rows; i++) {
            int number = Integer.parseInt(lines.get(i));
            Integer reverse = 0;
            while (number != 0) {
                reverse = reverse * 10;
                reverse = reverse + number % 10;
                number /= 10;
            }
            lines.set(i, reverse.toString());
            System.out.println(lines.get(i));
        }
        try {
            PrintWriter out = new PrintWriter("54.txt");
            for (int i = 1; i <= rows; i++) {
                out.println(lines.get(i));
            }
            out.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReverserBayan.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
