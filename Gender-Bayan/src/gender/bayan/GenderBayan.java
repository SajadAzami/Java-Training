package gender.bayan;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sajad Azami
 */
public class GenderBayan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<String> lines = null;
        ArrayList<Integer> resault = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get("130.in"), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            Logger.getLogger(GenderBayan.class.getName()).log(Level.SEVERE, null, ex);
        }
        int rows = Integer.parseInt(lines.get(0));
        int pesar = 0;
        int dokhtar = 0;
        char[] charArray = null;
        for (int i = 1; i <= rows; i++) {
            charArray = lines.get(i).toCharArray();
            int count = 0;
            for (int j = 0; j < charArray.length; j++) {
                switch (charArray[j]) {
                    case 'A':
                        count++;
                        break;
                    case 'a':
                        count++;
                        break;
                    case 'E':
                        count++;
                        break;
                    case 'e':
                        count++;
                        break;
                    case 'I':
                        count++;
                        break;
                    case 'i':
                        count++;
                        break;
                    case 'O':
                        count++;
                        break;
                    case 'o':
                        count++;
                        break;
                    case 'U':
                        count++;
                        break;
                    case 'u':
                        count++;
                        break;
                }
            }

            if (count % 2 == 0) {
                resault.add(1);
                System.out.println("Girl");
            }
            if (count % 2 == 1) {
                resault.add(0);
                System.out.println("Boy");
            }
        }
        try {
            PrintWriter out = new PrintWriter("130.txt");
            for (int counter = 0; counter < resault.size(); counter++) {
                if (resault.get(counter) == 1) {
                    out.println("Girl");
                } else if (resault.get(counter) == 0) {
                    out.println("Boy");
                }
            }
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GenderBayan.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
