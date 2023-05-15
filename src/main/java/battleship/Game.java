package battleship;

import java.util.Scanner;

public class Game {
    public static char[][] battleFieldMatrix(int size) {
        char[][] matrix = new char[size][size];
        String alphabet = "ABCDEFGHIJ";
        System.out.print(" ");
        for (int j = 1; j <= size; j++) {
            // System.out.print(" " + j);
        }
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            //System.out.print(alphabet.charAt(i) + " ");
            for (int k = 0; k < matrix[i].length; k++) {
                matrix[i][k] = '~';
                // System.out.print(matrix[i][k] + " ");
            }
            //System.out.println(" ");
        }
        return matrix;


    }


    public static char[][] shipPlacement(char[][] matrix, Coordinate[] coordinates) {
        Coordinate from = coordinates[0];
        Coordinate to = coordinates[1];


        if (from.getLine() == to.getLine()) {

            if (from.getColumn() < to.getColumn()) {
                for (int j = from.getColumn(); j <= to.getColumn(); j++) {
                    matrix[from.getLine()][j] = 'O';


                }
            } else if (from.getColumn() > to.getColumn()) {
                for (int k = to.getColumn(); k <= from.getColumn(); k++) {
                    matrix[from.getLine()][k] = 'O';


                }

            }

        } else if (from.getColumn() == to.getColumn()) {


            if (from.getLine() < to.getLine()) {
                for (int j = from.getLine(); j <= to.getLine(); j++) {
                    matrix[j][from.getColumn()] = 'O';


                }
            } else if (from.getLine() > to.getLine()) {
                for (int j = to.getLine(); j <= from.getLine(); j++) {
                    matrix[j][from.getColumn()] = 'O';


                }

            }


        }
        return matrix;

    }

    public static boolean sinkShip(Ship[] ships, Coordinate coordinate) {

        String coord = coordinate.getLine() + "" + coordinate.getColumn();

        for (int i = 0; i < ships.length; i++) {
            for (int j = 0; j < ships[i].shipMap.length; j++) {
                if (coord.equals(ships[i].shipMap[j])) {
                    ships[i].shipMap[j] = "X";
                    boolean sunk = true;
                    for (int k = 0; k < ships[i].shipMap.length; k++) {
                        if (!"X".equals(ships[i].shipMap[k])) {
                            sunk = false;
                            break;
                        }
                    }
                    return sunk;

                }
            }
        }

        return false;
    }

    /**
     * Creates an array of ships saved in a String[], helps to check whether the ship is sunk.
     *
     * @param coordinates
     * @param ship
     * @return
     */
    public static String[] createShipMap(Coordinate[] coordinates, Ship ship) {
        Coordinate from = coordinates[0];
        Coordinate to = coordinates[1];
        String[] shipMap = new String[ship.getLength()];


        int i = 0;
        if (from.getLine() == to.getLine()) {
            if (from.getColumn() < to.getColumn()) {
                for (int j = from.getColumn(); j <= to.getColumn(); j++) {
                    shipMap[i] = from.getLine() + "" + j;
                    i++;
                }
            } else if (from.getColumn() > to.getColumn()) {
                for (int k = to.getColumn(); k <= from.getColumn(); k++) {
                    shipMap[i] = from.getLine() + "" + k;
                    i++;
                }
            }

        } else if (from.getColumn() == to.getColumn()) {
            if (from.getLine() < to.getLine()) {
                for (int j = from.getLine(); j <= to.getLine(); j++) {
                    shipMap[i] = j + "" + from.getColumn();
                    i++;
                }
            } else if (from.getLine() > to.getLine()) {
                for (int j = to.getLine(); j <= from.getLine(); j++) {
                    shipMap[i] = j + "" + from.getColumn();
                    i++;
                }
            }
        }
        return shipMap;
    }


    public static void printField(char[][] matrix) {
        String alphabet = "ABCDEFGHIJ";
        System.out.print(" ");
        for (int k = 1; k <= matrix.length; k++) {
            System.out.print(" " + k);
        }
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(alphabet.charAt(i) + " ");
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static Coordinate[] shipCheck(Ship shipType, char[][] matrix) {

        Coordinate[] coordinates = new Coordinate[2];
        System.out.printf("Enter the coordinates of the %s:", shipType.getName());
        Scanner scanner = new Scanner(System.in);
        Coordinate from = new Coordinate(scanner.next());
        Coordinate to = new Coordinate(scanner.next());


        //check if coordinates are valid
        if (from.getLine() != to.getLine() && from.getColumn() != to.getColumn()) {
            System.out.println("Error! Please use your brain.");
            return shipCheck(shipType, matrix);
        }


        //check if coordinates and length of the ship is valid
        if ((from.getLine() == to.getLine() && Math.abs(from.getColumn() - to.getColumn()) != shipType.getLength() - 1) || (from.getColumn() == to.getColumn() && Math.abs(from.getLine() - to.getLine()) != shipType.getLength() - 1)) {
            System.out.printf("Error! Wrong length of the %s! Try again: %n", shipType.getName());
            return shipCheck(shipType, matrix);

        }

        if (from.getLine() == to.getLine()) {
            if (from.getColumn() < to.getColumn()) {
                for (int j = from.getColumn(); j <= to.getColumn(); j++) {
                    if (matrix[from.getLine()][j] == 'O' || (from.getLine() + 1 < 10 && matrix[from.getLine() + 1][j] == 'O') || (j + 1 < 10 && matrix[from.getLine()][j + 1] == 'O') || (j - 1 >= 0 && matrix[from.getLine()][j - 1] == 'O') || (from.getLine() - 1 >= 0 && matrix[from.getLine() - 1][j] == 'O')) {
                        System.out.println("Error. You placed it too close to another one. Try again:");
                        return shipCheck(shipType, matrix);
                    }
                }
            } else if (from.getColumn() > to.getColumn()) {
                for (int j = to.getColumn(); j <= from.getColumn(); j++) {
                    if (matrix[from.getLine()][j] == 'O' || (from.getLine() + 1 < 10 && matrix[from.getLine() + 1][j] == 'O') || (j + 1 < 10 && matrix[from.getLine()][j + 1] == 'O') || (j - 1 >= 0 && matrix[from.getLine()][j - 1] == 'O') || (from.getLine() - 1 >= 0 && matrix[from.getLine() - 1][j] == 'O')) {
                        System.out.println("Error. You placed it too close to another one. Try again:");
                        return shipCheck(shipType, matrix);
                    }
                }
            }
        } else if (from.getColumn() == to.getColumn()) {
            if (from.getLine() < to.getLine()) {
                for (int j = from.getLine(); j <= to.getLine(); j++) {
                    if (matrix[j][from.getColumn()] == 'O' || (from.getColumn() + 1 < 10 && matrix[j][from.getColumn() + 1] == 'O') || (j + 1 < 10 && matrix[j + 1][from.getColumn()] == 'O') || (j - 1 >= 0 && matrix[j - 1][from.getColumn()] == 'O') || (from.getColumn() - 1 >= 0 && matrix[j][from.getColumn() - 1] == 'O')) {
                        System.out.println("Error. You placed it too close to another one. Try again:");
                        return shipCheck(shipType, matrix);
                    }

                }
            } else if (from.getLine() > to.getLine()) {
                for (int j = to.getLine(); j <= from.getLine(); j++) {
                    if (matrix[j][from.getColumn()] == 'O' || (from.getColumn() + 1 < 10 && matrix[j][from.getColumn() + 1] == 'O') || (j + 1 < 10 && matrix[j + 1][from.getColumn()] == 'O') || (j - 1 >= 0 && matrix[j - 1][from.getColumn()] == 'O') || (from.getColumn() - 1 >= 0 && matrix[j][from.getColumn() - 1] == 'O')) {
                        System.out.println("Error. You placed it too close to another one. Try again:");
                        return shipCheck(shipType, matrix);
                    }

                }
            }
        }

        coordinates[0] = from;
        coordinates[1] = to;
        return coordinates;
    }

    //checks if the shot coordinates are valid
    public static Coordinate checkShot(char[][] matrix) {
        Scanner scanner = new Scanner(System.in);
        Coordinate shotCoordinates = new Coordinate(scanner.next());
        if (shotCoordinates.getLine() == -1 || shotCoordinates.getColumn() >= 10) {
            System.out.println("Error! You entered the wrong coordinates! Try again: ");
            return checkShot(matrix);
        } else {
            return shotCoordinates;
        }

    }

    public static boolean shot(char[][] matrix, Coordinate shotCoordinates) {

        boolean hit = false;
        if (matrix[shotCoordinates.getLine()][shotCoordinates.getColumn()] == 'O' || matrix[shotCoordinates.getLine()][shotCoordinates.getColumn()] == 'X') {
            matrix[shotCoordinates.getLine()][shotCoordinates.getColumn()] = 'X';


            hit = true;

        } else if (matrix[shotCoordinates.getLine()][shotCoordinates.getColumn()] == '~' || matrix[shotCoordinates.getLine()][shotCoordinates.getColumn()] == 'M') {
            matrix[shotCoordinates.getLine()][shotCoordinates.getColumn()] = 'M';


        }
        return hit;


    }

    public static void fog(boolean hit, Coordinate shotCoordinates, char[][] matrix) {
        if (hit) {
            matrix[shotCoordinates.getLine()][shotCoordinates.getColumn()] = 'X';

        } else {
            matrix[shotCoordinates.getLine()][shotCoordinates.getColumn()] = 'M';

        }

    }

    public static boolean gameContinues(char[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 'O') {
                    return true;
                }
            }
        }
        return false;
    }


    public static char[][] gameFieldArrangement(int player, Ship[] ships) {

        System.out.printf("Player %d, place your ships on the game field %n", player);
        char[][] matrix = battleFieldMatrix(10);
        printField(matrix);


//            Ship aircraft = new Ship("Aircraft Carrier (5 cells)", 5);
        //         Ship battleships = new Ship("Battleship (4 cells)", 4);
//            Ship submarine = new Ship("Submarine (3 cells)", 3);
//            Ship cruiser = new Ship("Cruiser (3 cells)", 3);
//            Ship destroyer = new Ship("Destroyer (2 cells)", 2);


        Coordinate[] aircraftCoordinatesFromTo = shipCheck(ships[0], matrix);
        printField(shipPlacement(matrix, aircraftCoordinatesFromTo));
        ships[0].shipMap = createShipMap(aircraftCoordinatesFromTo, ships[0]);

        Coordinate[] battleshipCoordinateFromTo = shipCheck(ships[1], matrix);
        ships[1].shipMap = createShipMap(battleshipCoordinateFromTo, ships[1]);
        printField(shipPlacement(matrix, battleshipCoordinateFromTo));

        Coordinate[] submarineCoordinateFromTo = shipCheck(ships[2], matrix);
        printField(shipPlacement(matrix, submarineCoordinateFromTo));
        ships[2].shipMap = createShipMap(submarineCoordinateFromTo, ships[2]);

        Coordinate[] cruiserCoordinateFromTo = shipCheck(ships[3], matrix);
        printField(shipPlacement(matrix, cruiserCoordinateFromTo));
        ships[3].shipMap = createShipMap(cruiserCoordinateFromTo, ships[3]);

        Coordinate[] destroyerCoordinateFromTo = shipCheck(ships[4], matrix);
        printField(shipPlacement(matrix, destroyerCoordinateFromTo));
        ships[4].shipMap = createShipMap(destroyerCoordinateFromTo, ships[4]);


        return matrix;


    }

    public static void gameStarts(char[][] matrix1, char[][] matrix2, Ship[] ships, Ship[] ships2) {
        //System.out.println("The game starts!");
        char[][] foggyMatrix1 = battleFieldMatrix(10);
        char[][] foggyMatrix2 = battleFieldMatrix(10);

        Scanner scanner = new Scanner(System.in);

        boolean hit = false;
        boolean gameContinues = true;
        boolean sunk = false;
        boolean hit2 = false;
        boolean gameContinues2 = true;
        boolean sunk2 = false;
        //printField(foggyMatrix1);

        do {
            printField(foggyMatrix2);
            System.out.println("---------------------");
            printField(matrix1);

            System.out.println(" ");


            System.out.println("Player 1, it's your turn:");

            Coordinate shotCoordinates = checkShot(matrix2);
            hit = shot(matrix2, shotCoordinates);
            fog(hit, shotCoordinates, foggyMatrix2);

            sunk = sinkShip(ships2, shotCoordinates);
            gameContinues = gameContinues(matrix2);


            if (hit) {
                if (sunk) {
                    if (gameContinues == false) {
                        System.out.println("You sank the last ship. You won. Congratulations! ");
                        break;

                    } else {

                        System.out.println("You sank a ship!  \nPress Enter and pass the move to another player ");
                        System.out.println("...");
                        scanner.nextLine();
                    }
                } else {
                    System.out.println("You hit a ship!  \nPress Enter and pass the move to another player ");
                    System.out.println("...");
                    scanner.nextLine();
                }
            } else {
                System.out.println("You missed! \nPress Enter and pass the move to another player ");
                System.out.println("...");
                scanner.nextLine();

            }


            printField(foggyMatrix1);
            System.out.println("---------------------");

            printField(matrix2);
            System.out.println(" ");
            //scanner.nextLine();


            System.out.println("Player 2, it's your turn: \n");
            Coordinate shotCoordinates2 = checkShot(matrix1);
            hit2 = shot(matrix1, shotCoordinates2);
            fog(hit2, shotCoordinates2, foggyMatrix1);

            sunk2 = sinkShip(ships, shotCoordinates2);
            gameContinues2 = gameContinues(matrix1);


            if (hit2) {
                if (sunk2) {
                    if (gameContinues2 == false) {
                        System.out.println("You sank the last ship. You won. Congratulations! ");
                        break;

                    } else {

                        System.out.println("You sank a ship!  \nPress Enter and pass the move to another player ");
                        System.out.println("...");
                        scanner.nextLine();
                    }
                } else {
                    System.out.println("You hit a ship!  \nPress Enter and pass the move to another player ");
                    System.out.println("...");
                    scanner.nextLine();
                }
            } else {
                System.out.println("You missed! \nPress Enter and pass the move to another player ");
                System.out.println("...");
                scanner.nextLine();
            }



        } while (gameContinues && gameContinues2);

        System.out.println("You sank the last ship. You won. Congratulations!");
        // printField(matrix1);
    }

}
