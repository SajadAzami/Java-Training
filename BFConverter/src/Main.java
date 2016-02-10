import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        System.out.println("Enter\nCode : 1\nDecode : 2");

        Scanner sc = new Scanner(System.in);
        int command = sc.nextInt();

        System.out.println("Enter Code(1 line)");
        Scanner sc2 = new Scanner(System.in);

        String codeSample = sc2.nextLine();

        String BFsample = codeSample;
        String Esample = codeSample;
        if (command == 2) {
            String bfCodeStr = BFsample;
            char[] bfCodeCharArray = bfCodeStr.toCharArray();
            ArrayList<BFSingleCode> singleCodes = new ArrayList<>();
            StringBuilder singleCodeStr = new StringBuilder("");
            for (int i = 0; i < bfCodeCharArray.length; i++) {
                if (bfCodeCharArray[i] != '.') {
                    singleCodeStr.append(bfCodeCharArray[i]);
                } else {
                    singleCodes.add(new BFSingleCode(singleCodeStr.toString()));
                    singleCodeStr.delete(0, singleCodeStr.length());
                }
            }
            for (int i = 0; i < singleCodes.size(); i++) {
                singleCodes.get(i).BF2E();
            }
        } else if (command == 1) {
            String eCodeStr = Esample;
            char[] eCharArray = eCodeStr.toCharArray();
            ArrayList<BFSingleCode> singleCodes = new ArrayList<>();
            for (int i = 0; i < eCharArray.length; i++) {
                singleCodes.add(new BFSingleCode(Character.toString(eCharArray[i])));
            }
            for (int i = 0; i < singleCodes.size(); i++) {
                singleCodes.get(i).E2BF();
            }
        }

    }

}

class BFSingleCode {
    String code = "";

    BFSingleCode(String code) {
        this.code = code;
    }

    public void BF2E() {
        char[] codeArray = code.toCharArray();
        int tenMultiplier = 0;
        int ones = 0;
        boolean isTenMultiplier = true;
        boolean ignorePoint = false;
        for (int i = 0; i < codeArray.length; i++) {
            if (isTenMultiplier && codeArray[i] == '+') {
                tenMultiplier++;
            }
            if (isTenMultiplier && codeArray[i] != '+') {
                isTenMultiplier = false;
            }
            if (!isTenMultiplier && codeArray[i] == '[') {
                ignorePoint = true;
            } else if (!isTenMultiplier && ignorePoint && codeArray[i] == ']') {
                ignorePoint = false;
            }
            if (!isTenMultiplier && !ignorePoint && codeArray[i] == '+') {
                ones++;
            }
        }
        int ASCII = (tenMultiplier * 10) + ones;
        System.out.print(Character.toString((char) ASCII));
    }

    public void E2BF() {
        int ASCII = (int) code.toCharArray()[0];
        int tenMultiplier = ASCII / 10;
        int ones = ASCII % 10;
        StringBuilder bfCode = new StringBuilder("");
        for (int i = 0; i < tenMultiplier; i++) {
            bfCode.append('+');
        }
        bfCode.append("[<");
        Random rnd = new Random();
        char[] randomChars = {'%', '^', '&', '*', '$'};
        for (int i = 0; i < 5; i++) {
            bfCode.append(randomChars[rnd.nextInt(4)]);
        }
        bfCode.append("]>");
        for (int i = 0; i < ones; i++) {
            bfCode.append('+');
        }
        bfCode.append('.');
        System.out.print(bfCode.toString());
    }
}
