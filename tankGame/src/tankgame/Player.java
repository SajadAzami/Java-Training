package tankgame;

import java.util.Scanner;

/**
 *
 * @author Sajad Azami
 */
public class Player {

    int[][] field;//0 for empty,1 for tank,2 for enemy's target
    int[][] targetfield;//0 for not targeted and 1 for hit,2 for miss
    Scanner scanner = new Scanner(System.in);

    public Player() {
        this.field = new int[10][10];
        this.targetfield = new int[10][10];
    }

    /**
     * Print the map
     */
    public void printMap() {
        System.out.println("# :     Tank    * : Enemy shots");
        System.out.println("Your field:");
        for (int j = 9; j >= 0; j--) {
            for (int i = 0; i <= 9; i++) {
                if (field[i][j] == 1) {
                    System.out.print("#");
                }
                if (field[i][j] == 0) {
                    System.out.print(" ");
                }
                if (field[i][j] == 2) {
                    System.out.print("*");
                }
                System.out.print(" | ");
                if (i == 9) {
                    System.out.println("");
                }
            }
        }
    }

    /**
     * Show enemy's field and targeted places
     */
    public void showTargetField() {
        System.out.println("* : Hit     @ : Miss");
        System.out.println("Enemy field:");
        for (int j = 9; j >= 0; j--) {
            for (int i = 0; i <= 9; i++) {
                if (targetfield[i][j] == 1) {
                    System.out.print("*");
                }
                if (targetfield[i][j] == 0) {
                    System.out.print(" ");
                }
                if (targetfield[i][j] == 2) {
                    System.out.print("@");
                }
                System.out.print(" | ");
                if (i == 9) {
                    System.out.println("");
                }
            }
        }

    }

    /**
     * Check to see if player has any tank or not
     */
    boolean checkPlayerStatus() {
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                if (field[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Create player's field by asking him tank position
     */
    public void createField() {
        boolean isComplete, isError;
        boolean xflag, yflag;
        boolean isForError;
        int xPos = 0, yPos = 0;
        for (int tankCounter = 1; tankCounter <= 5; tankCounter++) {
            isComplete = false;
            isError = true;
            while (!isComplete || isError) {
                xflag = false;
                yflag = false;
                isForError = false;
                System.out.println("Enter your tank's lenght(2,3,4,5)");
                int lenght = scanner.nextInt();
                System.out.println("Enter orientation of the tank:");
                System.out.println("1:Vertical to right     2:Vertical to left");
                System.out.println("3:Horizontal to up   4:Horizontal to down");
                int orientation = scanner.nextInt();
                while (!xflag) {
                    System.out.println("Enter tanks first tile's x location");
                    System.out.println("Note that the tank will be start with this tile");
                    xPos = scanner.nextInt();
                    if (xPos <= 9 && xPos >= 0) {
                        xflag = true;
                    } else {
                        System.out.println("Enter number between 0 and 9");
                    }
                }
                while (!yflag) {
                    System.out.println("Enter tanks first tile's y location");
                    yPos = scanner.nextInt();
                    if (yPos <= 9 && yPos >= 0) {
                        yflag = true;
                    } else {
                        System.out.println("Enter number between 0 and 9");
                    }
                }

                //Vertical to right
                if (orientation == 1) {
                    if (xPos + lenght - 1 <= 9) {
                        for (int i = 1; i <= lenght - 1 && !isForError; i++) {
                            if (field[xPos + i][yPos] != 1) {
                                isError = false;
                            } else {
                                isForError = true;
                                System.out.println("The tile was filled before,choose another position");
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
                        System.out.println("Your orientation and first tile position is not compatible");
                        System.out.println("Choose another position");
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
                                System.out.println("The tile was filled before,choose another position");
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
                        System.out.println("Your orientation and first tile position is not compatible");
                        System.out.println("Choose another position");
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
                                System.out.println("The tile was filled before,choose another position");
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
                        System.out.println("Your orientation and first tile position is not compatible");
                        System.out.println("Choose another position");
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
                                System.out.println("The tile was filled before,choose another position");
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
                        System.out.println("Your orientation and first tile position is not compatible");
                        System.out.println("Choose another position");
                        isComplete = false;
                    }
                }
            }
            printMap();
        }
    }
}
