package battleship;

import java.util.Scanner;

import static battleship.Game.gameFieldArrangement;
import static battleship.Game.gameStarts;

public class Main {

    int player;




    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Ship aircraft = new Ship("Aircraft Carrier (5 cells)", 5);
        Ship battleship = new Ship("Battleship (4 cells)", 4);
        Ship submarine = new Ship("Submarine (3 cells)", 3);
        Ship cruiser = new Ship("Cruiser (3 cells)", 3);
        Ship destroyer = new Ship("Destroyer (2 cells)", 2);


        Ship aircraft2 = new Ship("Aircraft Carrier (5 cells)", 5);
        Ship battleship2 = new Ship("Battleship (4 cells)", 4);
        Ship submarine2 = new Ship("Submarine (3 cells)", 3);
        Ship cruiser2 = new Ship("Cruiser (3 cells)", 3);
        Ship destroyer2 = new Ship("Destroyer (2 cells)", 2);

        Ship[] ships = new Ship[]{aircraft, battleship, submarine, cruiser, destroyer};

        Ship[] ships2 = new Ship[]{aircraft2, battleship2, submarine2, cruiser2, destroyer2};

        char[][] gameFieldPl1 = gameFieldArrangement(1, ships);

        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
        char[][] gameFieldPl2 =  gameFieldArrangement(2, ships2);
        System.out.println("Press Enter and pass the move to another player");


        scanner.nextLine();
        gameStarts(gameFieldPl1, gameFieldPl2, ships, ships2);
    }
}
