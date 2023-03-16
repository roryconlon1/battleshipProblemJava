public class Battleship {

    static int[][] gameBoard;

    public static void setUpGame(String[] ships) {
//            2d array to make a 10 by 10 game board of all value 0, whereby can change value depending on if
//            it contains a ship, if the ship has been hit or miss etc. and if the ship is sunk or not
        gameBoard = new int[10][10];
        int shipsCount = 0;
        for (int i = 0; i < ships.length; i++) {
//             for each ship in the array split by the comma to get the start and end points of the ship
//            from this, split coordinate of start/end by colon and find x and y of both
            String[] individualShip = ships[i].split(",");
            String[] startCoordinates = individualShip[0].split(":");
            String[] endCoordinates = individualShip[1].split(":");
            int startXposition = Integer.parseInt(startCoordinates[0]);
            int endXposition = Integer.parseInt(endCoordinates[0]);
            int startYpositon = Integer.parseInt(startCoordinates[1]);
            int endYposition = Integer.parseInt(endCoordinates[1]);
            if (startXposition > 9 || endXposition > 9 || startYpositon > 9 || endYposition > 9) {
                throw new ArrayIndexOutOfBoundsException("Ship Placement out of bounds");
            }
//              for each new ship, give it a new id by incrementing the value of how many total ships there are
//            determine if horizontal or vertical and assign the value of those positions to the same as the ships id
            int shipId = shipsCount + 1;
            if (startXposition != endXposition && startYpositon != endYposition) {
                throw new IllegalArgumentException("Invalid Ship Placement");
            }
//               vertical
            if (startXposition == endXposition) {
                for (int j = startYpositon; j <= endYposition; j++) {
                    gameBoard[startXposition][j] = shipId;
                }
            } else {
//              horizontal
                for (int l = startXposition; l <= endXposition; l++) {
                    gameBoard[l][startYpositon] = shipId;
                }
            }
            shipsCount++;
        }
    }

    //        check if an individual guess is a hit or not by checking if it is 0
//        or if it is greater and therefore a ship, returning bool value
    public static boolean hitOrMiss(String guess) {
        String[] guessCoordinates = guess.split(":");
        int x = Integer.parseInt(guessCoordinates[0]);
        int y = Integer.parseInt(guessCoordinates[1]);
        if (x > 9 || y > 9) {
            throw new ArrayIndexOutOfBoundsException("Guessed Position out of bounds");
        }
        return gameBoard[x][y] > 0;
    }

//        method to check if a ship is sunk, iterate over every element and if none match
//        a certain shipId then that ship is sunk

    public static boolean isSunk(int shipId) {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j] == shipId) {
                    return false;
                }
            }
        }
        return true;
    }

    // iterate over the guesses in final method and check with HitOrMiss function to see if it is a boat or not
    // if it is a boat, denote with -1

    public static int play(String[] ships, String[] guesses) {
        setUpGame(ships);
        int boatsSunk = 0;
        for (int i = 0; i < guesses.length; i++) {
            if (hitOrMiss(guesses[i])) {
                String[] guessCoordinates = guesses[i].split(":");
                int x = Integer.parseInt(guessCoordinates[0]);
                int y = Integer.parseInt(guessCoordinates[1]);

                int idTracker = gameBoard[x][y];
                gameBoard[x][y] = -1;
                if (isSunk(idTracker)) {
                    boatsSunk++;
                }
            }
        }
        return boatsSunk;
    }

}
