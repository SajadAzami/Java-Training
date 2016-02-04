/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balancechecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sajad Azami
 */
public class BalanceChecker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Press:\n1 to Read From The File(test.txt)\n2 to Read From The Console");
        Scanner scanner = new Scanner(System.in);
        int temp = scanner.nextInt();
        List<String> lines = new ArrayList<>();
        if (temp == 1) {
            try {
                lines = Files.readAllLines(Paths.get("test.txt"), StandardCharsets.UTF_8);
            } catch (IOException ex) {
                Logger.getLogger(BalanceChecker.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (temp == 2) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String tempStr = null;
            try {
                tempStr = br.readLine();
                lines.add(tempStr);
            } catch (IOException ex) {
                Logger.getLogger(BalanceChecker.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Error entering the number!");
            return;
        }
        int rows = lines.size();
        if (rows > 1) {
            System.out.println("Error!More than one line entered!");
            return;
        }
        char[] charArray;
        charArray = lines.get(0).toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < charArray.length; i++) {
            switch (charArray[i]) {
                case '(':
                    stack.push('(');
                    break;
                case ')':
                    if (!stack.pop('(')) {
                        return;
                    }
                    break;
                case '{':
                    stack.push('{');
                    break;
                case '}':
                    if (!stack.pop('{')) {
                        return;
                    }
                    break;
                case '[':
                    stack.push('[');
                    break;
                case ']':
                    if (!stack.pop('[')) {
                        return;
                    }
                    break;
            }
        }
        if (stack.isEmpty()) {
            System.out.println("The line is balanced!");
        } else {
            System.out.println("The line is not balanced!");
        }
    }

}
