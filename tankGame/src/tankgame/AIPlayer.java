package tankgame;

/**
 * CPU player that uses AI
 *
 * @author Sajad Azami
 */
public class AIPlayer {

    int[][] field;//0 for empty,1 for tank,2 for players target
    int[][] targetfield;//0 for not targeted and 1 for hit,2 for miss
    int lenght;
    int orientation;
    int xPos = 0, yPos = 0;

    /**
     * Constructor
     */
    public AIPlayer() {
        field = new int[10][10];
        targetfield = new int[10][10];
    }

    /**
     * Generate a random integer between max and min
     */
    public int generateRandomNum(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    /**
     * Create AIplayer's field by asking him tank position
     */
    public void createField() {
        boolean isComplete, isError;
        boolean isForError;
        for (int tankCounter = 1; tankCounter <= 5; tankCounter++) {
            isComplete = false;
            isError = true;
            while (!isComplete || isError) {
                isForError = false;
                orientation = generateRandomNum(1, 4);
                lenght = generateRandomNum(2, 5);
                xPos = generateRandomNum(0, 9);
                yPos = generateRandomNum(0, 9);

                //Vertical to right
                if (orientation == 1) {
                    if (xPos + lenght - 1 <= 9) {
                        for (int i = 1; i <= lenght - 1 && !isForError; i++) {
                            if (field[xPos + i][yPos] != 1) {
                                isError = false;
                            } else {
                                isForError = true;
                                isError = true;
                            }
                        }
                        if (!isError) {
                            for (int j = 1; j <= lenght - 1 && !isForError; j++) {
                                field[xPos + j][yPos] = 1;
                            }
                            field[xPos][yPos] = 1;
                        }
                        isComplete = true;
                    } else {
                        isComplete = false;
                    }
                } //Vertical to left
                else if (orientation == 2) {
                    if (xPos - (lenght - 1) >= 0) {
                        for (int i = 1; i <= lenght - 1 && !isForError; i++) {
                            if (field[xPos - i][yPos] != 1) {
                                isError = false;
                            } else {
                                isForError = true;
                                isError = true;
                            }
                        }
                        if (!isError) {
                            for (int j = 1; j <= lenght - 1 && !isForError; j++) {
                                field[xPos - j][yPos] = 1;
                            }
                            field[xPos][yPos] = 1;
                        }
                        isComplete = true;
                    } else {
                        isComplete = false;
                    }
                } //Horizontal to up 
                else if (orientation == 3) {
                    if (yPos + lenght - 1 <= 9) {
                        for (int i = 1; i <= lenght - 1 && !isForError; i++) {
                            if (field[xPos][yPos + i] != 1) {
                                isError = false;
                            } else {
                                isForError = true;
                                isError = true;
                            }
                        }
                        if (!isError) {
                            for (int j = 1; j <= lenght - 1 && !isForError; j++) {
                                field[xPos][yPos + j] = 1;
                            }
                            field[xPos][yPos] = 1;
                        }
                        isComplete = true;
                    } else {
                        isComplete = false;
                    }
                } //Horizontal to down
                else if (orientation == 4) {
                    if (yPos - (lenght - 1) >= 0) {
                        for (int i = 1; i <= lenght - 1 && !isForError; i++) {
                            if (field[xPos][yPos - i] != 1) {
                                isError = false;
                            } else {
                                isForError = true;
                                isError = true;
                            }
                        }
                        if (!isError) {
                            for (int j = 1; j <= lenght - 1 && !isForError; j++) {
                                field[xPos][yPos - j] = 1;
                            }
                            field[xPos][yPos] = 1;
                        }
                        isComplete = true;
                    } else {
                        isComplete = false;
                    }
                }
            }
        }
    }

    /**
     * Print the map
     */
    public void printMap() {
        System.out.println("Your field:");
        for (int j = 9; j >= 0; j--) {
            for (int i = 0; i <= 9; i++) {
                if (field[i][j] == 1) {
                    System.out.print("#");
                }
                if (field[i][j] == 0) {
                    System.out.print(" ");
                }
                System.out.print(" | ");
                if (i == 9) {
                    System.out.println("");
                }
            }
        }
    }
}
