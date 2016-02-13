package tankgame;

/**
 *
 * @author Sajad Azami
 */
public class RightCommand {

    //Holds all the right commands
    static final int RightCommands[] = {
       0,1,2,3,4,5,6,7,8,9,10,
        11,12,13,14,15,16,17,18,19,20,
        21,22,23,24
    };

    /**
     * Constructor
     */
    RightCommand() {
    }

    /**
     * Check a string to see if it is true or not
     *
     * @param string string we want to check
     */
    boolean isRightCommand(int num) {
        for (int RightCommand : RightCommands) {
            if (RightCommand == num) {
                return true;
            }
        }
        return false;
    }

    /*
     * Print all right commands
     */
    void showAll() {
        System.out.println("Right commands:");
        for (int RightCommand : RightCommands) {
            System.out.println(RightCommand);
        }
        System.out.println();
    }

}
