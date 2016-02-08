/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradesorter;

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
 * @author Espinas
 */
public class GradeSorter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get("1.txt"), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            Logger.getLogger(GradeSorter.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).equals("غایب")) {
                lines.remove(i);
            }
            if (lines.get(i).equals("صفر")) {
                lines.remove(i);
            }
        }
        ArrayList<String> ids = new ArrayList<>();
        ArrayList<String> grades = new ArrayList<>();
        for (int i = 1; i < lines.size(); i += 8) {
            ids.add(lines.get(i));
        }
        for (int i = 7; i < lines.size(); i += 8) {
            grades.add(lines.get(i));
        }
        ArrayList<Info> fullInfo = new ArrayList<>();
        for (int i = 0; i < grades.size(); i++) {
            fullInfo.add(new Info(Integer.parseInt(ids.get(i)), Float.parseFloat(grades.get(i))));
        }
        for (int i = 0; i < (fullInfo.size()) - 1; i++) {
            for (int j = 0; j < (fullInfo.size()) - 1; j++) {
                if (fullInfo.get(j).grade < fullInfo.get(j + 1).grade) {
                    Info temp = fullInfo.get(j);
                    fullInfo.set(j, fullInfo.get(j + 1));
                    fullInfo.set(j + 1, temp);
                }
            }
        }
        int average = 0;
        int studentCount = fullInfo.size();
        int sum = 0;
        for (int i = 0; i < fullInfo.size(); i++) {
            sum += fullInfo.get(i).grade;
        }
        average = sum / studentCount;
        try {
            PrintWriter out = new PrintWriter("B.txt");
            out.println(average);
            for (int j = 0; j < ids.size(); j++) {
                out.print((j + 1) + " - " + fullInfo.get(j).id + " - " + fullInfo.get(j).grade);
                out.println();
            }

            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GradeSorter.class.getName()).log(Level.SEVERE, null, ex);
        }

        //COMPUTER
        ArrayList<Info> computerInfo = new ArrayList<>();
        for (int i = 0; i < fullInfo.size(); i++) {
            if ((fullInfo.get(i).id) - ((fullInfo.get(i).id) % 1000) == 9231000) {
                computerInfo.add(fullInfo.get(i));
            }
        }
        int computerAverage = 0;
        int computerSum = 0;
        for (int i = 0; i < computerInfo.size(); i++) {
            computerSum += computerInfo.get(i).grade;
        }
        computerAverage = computerSum / (computerInfo.size());
        try {
            PrintWriter out = new PrintWriter("Computer.txt");
            out.println(computerAverage);
            for (int j = 0; j < computerInfo.size(); j++) {
                out.print((j + 1) + " - " + computerInfo.get(j).id + " - " + computerInfo.get(j).grade);
                out.println();
            }

            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GradeSorter.class.getName()).log(Level.SEVERE, null, ex);
        }

        //INDUSTRIAL
        ArrayList<Info> industrialInfo = new ArrayList<>();
        for (int i = 0; i < fullInfo.size(); i++) {
            if ((fullInfo.get(i).id) - ((fullInfo.get(i).id) % 1000) == 9225000) {
                industrialInfo.add(fullInfo.get(i));
            }
        }
        int industrialAverage = 0;
        int industrialSum = 0;
        for (int i = 0; i < industrialInfo.size(); i++) {
            industrialSum += industrialInfo.get(i).grade;
        }
        industrialAverage = industrialSum / (industrialInfo.size());
        try {
            PrintWriter out = new PrintWriter("Industrial.txt");
            out.println(industrialAverage);
            for (int j = 0; j < industrialInfo.size(); j++) {
                out.print((j + 1) + " - " + industrialInfo.get(j).id + " - " + industrialInfo.get(j).grade);
                out.println();
            }

            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GradeSorter.class.getName()).log(Level.SEVERE, null, ex);
        }

        //CHEMICAL ENGINEERING
        ArrayList<Info> cheInfo = new ArrayList<>();
        for (int i = 0; i < fullInfo.size(); i++) {
            if ((fullInfo.get(i).id) - ((fullInfo.get(i).id) % 1000) == 9222000) {
                cheInfo.add(fullInfo.get(i));
            }
        }
        int cheAverage = 0;
        int cheSum = 0;
        for (int i = 0; i < cheInfo.size(); i++) {
            cheSum += cheInfo.get(i).grade;
        }
        cheAverage = cheSum / (cheInfo.size());
        try {
            PrintWriter out = new PrintWriter("Chemical Engineering.txt");
            out.println(cheAverage);
            for (int j = 0; j < cheInfo.size(); j++) {
                out.print((j + 1) + " - " + cheInfo.get(j).id + " - " + cheInfo.get(j).grade);
                out.println();
            }

            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GradeSorter.class.getName()).log(Level.SEVERE, null, ex);
        }

        //OMRAN
        ArrayList<Info> omranInfo = new ArrayList<>();
        for (int i = 0; i < fullInfo.size(); i++) {
            if ((fullInfo.get(i).id) - ((fullInfo.get(i).id) % 1000) == 9224000) {
                omranInfo.add(fullInfo.get(i));
            }
        }
        int omranAverage = 0;
        int omranSum = 0;
        for (int i = 0; i < omranInfo.size(); i++) {
            omranSum += omranInfo.get(i).grade;
        }
        omranAverage = omranSum / (omranInfo.size());
        try {
            PrintWriter out = new PrintWriter("Omran Engineering.txt");
            out.println(omranAverage);
            for (int j = 0; j < omranInfo.size(); j++) {
                out.print((j + 1) + " - " + omranInfo.get(j).id + " - " + omranInfo.get(j).grade);
                out.println();
            }

            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GradeSorter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
