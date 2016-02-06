/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binaryconverter;

import java.util.Scanner;

/**
 *
 * @author Sajad Azami
 */
public class BinaryConverter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Enter A Positive Number:");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        if (number < 0) {
            System.out.println("Negetive number entered!");
            return;
        } else {
            reverser(number);
        }

    }

    private static void reverser(int number) {
        int remainder;

        if (number <= 1) {
            System.out.print(number);
            return;
        }

        remainder = number % 2;
        reverser(number >> 1);
        System.out.print(remainder);
    }
}
