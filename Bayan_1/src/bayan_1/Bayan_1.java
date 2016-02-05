/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bayan_1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sajad Azami
 */
public class Bayan_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get("A.txt"), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            Logger.getLogger(Bayan_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        int rows = Integer.parseInt(lines.get(0));
        ArrayList<Integer> winner = new ArrayList<>();
        ArrayList<Integer> tempWinner = new ArrayList<>();
        ArrayList<Integer> counter = new ArrayList<>();
        int index = 0;
        for (int i = 1; i <= rows; i++) {
            int players = Integer.parseInt(lines.get(2 + index));
            char[] charArray = null;
            charArray = lines.get(index + 3).toCharArray();
            winner.clear();
            for (int j = 0; j < players; j++) {
                for (int k = 0; k < players; k++) {
                    if (charArray[j] == 'R' && charArray[k] == 'S') {
                        tempWinner.add(1);
                    }
                    if (charArray[j] == 'P' && charArray[k] == 'R') {
                        tempWinner.add(1);
                    }
                    if (charArray[j] == 'S' && charArray[k] == 'P') {
                        tempWinner.add(1);
                    }
                }
                if (tempWinner.size() != 0) {
                    winner.add(tempWinner.size());
                }
                tempWinner.clear();
            }
            Collections.sort(winner);
            Arrays.sort(winner.toArray());
            if (winner.size() == 0) {
                counter.add(players);
            } else {
                int temp = 1;
                for (int n = winner.size(); n >= 2; n--) {
                    if ((winner.get(winner.size() - 1)).equals(winner.get(n - 2))) {
                        temp++;
                    }
                }
                counter.add(temp);
            }
            index += 3;
        }
        try {
            PrintWriter out = new PrintWriter("B.txt");
            for (int m = 0; m < rows; m++) {
                out.println("Case #" + (m + 1) + ":");
                out.println(counter.get(m));
            }
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Bayan_1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
