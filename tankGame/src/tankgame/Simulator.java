package tankgame;

import java.util.Scanner;

/**
 * Simulate implementing the Tank game
 *
 * @author Sajad Azami
 */
public class Simulator {

    RightCommand rightCommand = new RightCommand();
    Player player = new Player();
    Player player2 = new Player();
    AIPlayer aiPlayer = new AIPlayer();
    Scanner scanner = new Scanner(System.in);
    int xPosition, yPosition;
    int Xtemp = -1, Ytemp = -1;
    boolean xFlag, yFlag;
    int[][] shot = new int[10][10];//AIPlayer's shots,0 not shot,1 shot
    int mode;
    boolean playerturn;//true : player1 turn , false : player2 turn

    /**
     * Constructor
     */
    public Simulator() {

    }

    /**
     * Implement the game
     */
    public void startGame() {
        boolean isFinished = false;
        help();
        mode = scanner.nextInt();
        if (mode == 1) {//Multiplayer
            System.out.println("Choose your map player1");
            player.createField();
            System.out.println("Choose your map player2");
            player2.createField();
            System.out.println("############################");
            System.out.println("######The game started######");
            while (!isFinished) {
                playerturn = true;
                System.out.println("############################");
                System.out.println("Player1's turn:");
                player.printMap();
                player.showTargetField();
                System.out.println("############################");
                getPositions();
                if (xFlag && yFlag) {
                    doCommand(xPosition, yPosition);
                }
                playerturn = false;
                System.out.println("############################");
                System.out.println("Player2's turn:");
                player2.printMap();
                player2.showTargetField();
                System.out.println("############################");
                getPositions();
                if (xFlag && yFlag) {
                    doCommand(xPosition, yPosition);
                }
                if (player2.checkPlayerStatus()) {
                    isFinished = true;
                    System.out.println("player1 won the game!");
                }
                if (player.checkPlayerStatus()) {
                    isFinished = true;
                    System.out.println("player2 won the game!");
                }
            }

        }
        if (mode == 2) {//Play with AI
            player.createField();
            aiPlayer.createField();
            System.out.println("############################");
            System.out.println("######The game started######");
            while (!isFinished) {
                System.out.println("############################");
                player.printMap();
                player.showTargetField();
                System.out.println("############################");
                getPositions();
                if (xFlag && yFlag) {
                    doCommand(xPosition, yPosition);
                }
                AIPlay();
                if (checkEnemyStatus()) {
                    isFinished = true;
                    System.out.println("Congratulations! You win the game!");
                }
                if (player.checkPlayerStatus()) {
                    isFinished = true;
                    System.out.println("Ohh! You lost!");
                }
            }
        }
    }

    /**
     * Get target positions
     */
    void getPositions() {
        xFlag = false;
        yFlag = false;
        while (!xFlag || !yFlag) {
            System.out.println("Enter X position");

            xPosition = scanner.nextInt();
            xFlag = processCommand(xPosition);

            if (!xFlag) {
                System.out.println("Wrong position entered");
            }

            System.out.println("Enter Y position");
            yPosition = scanner.nextInt();
            yFlag = processCommand(yPosition);

            if (!yFlag) {
                System.out.println("Wrong position entered");
            }
        }
    }

    /**
     * Simulate AI player's shots
     */
    void AIPlay() {
        boolean forFlag = false;
        for (int i = 0; i <= 9 && !forFlag; i++) {
            for (int j = 0; j <= 9; j++) {
                int tempRand = aiPlayer.generateRandomNum(1, 4);
                if (aiPlayer.targetfield[i][j] == 1 && tempRand == 1) {
                    AIShot(i + 1, j);
                    forFlag = true;
                    break;
                }
                if (aiPlayer.targetfield[i][j] == 1 && tempRand == 2) {
                    AIShot(i - 1, j);
                    forFlag = true;
                    break;
                }
                if (aiPlayer.targetfield[i][j] == 1 && tempRand == 3) {
                    AIShot(i, j + 1);
                    forFlag = true;
                    break;
                }
                if (aiPlayer.targetfield[i][j] == 1 && tempRand == 4) {
                    AIShot(i, j - 1);
                    forFlag = true;
                    break;
                }
            }
        }
        if (!forFlag) {
            randomShot();
        }

    }

    /**
     * Shoot a tile
     *
     * @param xdest x position of the target
     * @param ydest y position of the target
     */
    void AIShot(int xdest, int ydest) {
        boolean xNotShot = true, yNotShot = true;
        while (xNotShot && yNotShot) {
            Xtemp = xdest;
            Ytemp = ydest;
            while (true) {
                if (shot[Xtemp][Ytemp] == 1) {//if shot before
                    break;
                } else {
                    shot[Xtemp][Ytemp] = 1;
                    xNotShot = false;
                    yNotShot = false;
                    if (player.field[Xtemp][Ytemp] == 1) {
                        aiPlayer.targetfield[Xtemp][Ytemp] = 1;
                        System.out.println("Enemy destroyed one of your tanks!");
                        player.field[Xtemp][Ytemp] = 2;
                    } else {
                        aiPlayer.targetfield[Xtemp][Ytemp] = 2;
                        System.out.println("Enemys missed their shot!");
                        player.field[Xtemp][Ytemp] = 2;
                    }
                    break;
                }
            }
        }

    }

    /**
     * Randomize a shot from AI player
     */
    void randomShot() {
        boolean xNotShot = true, yNotShot = true;
        while (xNotShot && yNotShot) {
            Xtemp = aiPlayer.generateRandomNum(0, 9);
            Ytemp = aiPlayer.generateRandomNum(0, 9);
            while (true) {
                if (shot[Xtemp][Ytemp] == 1) {//if shot before
                    break;
                } else {
                    shot[Xtemp][Ytemp] = 1;
                    xNotShot = false;
                    yNotShot = false;
                    if (player.field[Xtemp][Ytemp] == 1) {
                        aiPlayer.targetfield[Xtemp][Ytemp] = 1;
                        System.out.println("Enemy destroyed one of your tanks!");
                        player.field[Xtemp][Ytemp] = 2;
                    } else {
                        aiPlayer.targetfield[Xtemp][Ytemp] = 2;
                        System.out.println("Enemys missed their shot!");
                        player.field[Xtemp][Ytemp] = 2;
                    }
                    break;
                }
            }
        }
    }

    /**
     * Process the command
     */
    boolean processCommand(int command
    ) {
        if (!rightCommand.isRightCommand(command)) {
            System.out.println("Wrong command entered,enter two numbers");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Implement the command
     */
    void doCommand(int x, int y) {
        if (mode == 1 && playerturn) {//first player's turn
            if (player2.field[x][y] == 1) {
                player.targetfield[x][y] = 1;
                System.out.println("You hit an enemy tank!");
                player2.field[x][y] = 2;
            } else {
                player.targetfield[x][y] = 2;
                System.out.println("Thats a miss!");
                player2.field[x][y] = 2;
            }
        }
        if (mode == 1 && !playerturn) {//second player's turn
            if (player.field[x][y] == 1) {
                player2.targetfield[x][y] = 1;
                System.out.println("You hit an enemy tank!");
                player.field[x][y] = 2;
            } else {
                player2.targetfield[x][y] = 2;
                System.out.println("Thats a miss!");
                player.field[x][y] = 2;
            }
        }

        if (mode == 2) {
            if (aiPlayer.field[x][y] == 1) {
                player.targetfield[x][y] = 1;
                System.out.println("You hit an enemy tank!");
                aiPlayer.field[x][y] = 2;
            } else {
                player.targetfield[x][y] = 2;
                System.out.println("Thats a miss!");
                aiPlayer.field[x][y] = 2;
            }
        }

    }

    /**
     * Check to see if enemy has any tank or not
     */
    boolean checkEnemyStatus() {
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                if (aiPlayer.field[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Print some information about the game
     */
    void help() {
        System.out.println("Welcome to tank game!");
        System.out.println("At first,you should place your tanks by creating 5 tanks");
        System.out.println("Each turn,you and enemy will shoot a missile");
        System.out.println("You only know about your field,enemy's shot and your shot");
        System.out.println("Enter 1 for multiplayer,2 for singleplayer");
        System.out.println("Good luck!");
        System.out.println("");
    }

}
