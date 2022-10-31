package battleship;

import java.util.Scanner;

public class Player {
    private String name;
    private boolean isWinner;
    private Board board;
    private Scanner scanner;

    Player(String name) {
        this.name = name;
        this.isWinner = false;
        this.board = new Board();
        this.scanner = new Scanner(System.in);
    }

    public boolean isWinner(Player opponent) {
        return wasShipLast(opponent);
    }

    private boolean isAShipNearby(Point first, Point second) {
        int rightY = Math.max(first.y, second.y);
        int leftY = Math.min(first.y, second.y);
        int bottomX = Math.max(first.x, second.x);
        int upperX = Math.min(first.x, second.x);
        int rightEdge = rightY == 10 ? 10 : rightY + 1;
        int bottomEdge = bottomX == 10 ? 10 : bottomX + 1;

        for (int i = upperX - 1; i < bottomEdge + 1; i++) {
            for (int j = leftY - 1; j < rightEdge + 1; j++) {
                if (board.getCeil(i, j) == 'O') {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isSankAShip(Point point, Player opponent) {
        int i;
        int j;

        j = point.y;
        i = point.x - 1;
        while (i > 0 && (opponent.board.getCeil(i, j) != '~' && opponent.board.getCeil(i, j) != 'M')) {
            if (opponent.board.getCeil(i, j) == 'O') {
                return false;
            }
            i--;
        }
        i = point.x + 1;
        while (i < 11 && (opponent.board.getCeil(i, j) != '~' && opponent.board.getCeil(i, j) != 'M')) {
            if (opponent.board.getCeil(i, j) == 'O') {
                return false;
            }
            i++;
        }

        i = point.x;
        j = point.y - 1;
        while (j > 0 && (opponent.board.getCeil(i, j) != '~' && opponent.board.getCeil(i, j) != 'M')) {
            if (opponent.board.getCeil(i, j) == 'O') {
                return false;
            }
            j--;
        }
        j = point.y + 1;
        while (j < 11 && (opponent.board.getCeil(i, j) != '~' && opponent.board.getCeil(i, j) != 'M')) {
            if (opponent.board.getCeil(i, j) == 'O') {
                return false;
            }
            j++;
        }

        return true;
    }

    private boolean wasShipLast(Player opponent) {
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                if (opponent.board.getCeil(i, j) == 'O') {
                    return false;
                }
            }
        }
        System.out.println("You sank the last ship. You won. Congratulations!");

        return true;
    }
    private boolean isShotFired(String shot, Player opponent) {
        Point shotPoint = new Point(shot.charAt(0), shot.substring(1));

        if (shotPoint.x < 1 || shotPoint.x > 10 || shotPoint.y < 1 || shotPoint.y > 10) {
            System.out.println("Error! You entered the wrong coordinates! Try again:");
            return false;
        } else if (opponent.board.getCeil(shotPoint.x, shotPoint.y) == 'O'
                || board.getCeil(shotPoint.x, shotPoint.y) == 'X') {
            opponent.board.setCeil(shotPoint.x, shotPoint.y, 'X');
            opponent.board.printBoardWithFogOfWar();
            if (isSankAShip(shotPoint, opponent) && !wasShipLast(opponent)) {
                System.out.println("You sank a ship! Specify a new target:");
            } else {
                System.out.println("You hit a ship! Try again:");
            }
        } else {
            opponent.board.setCeil(shotPoint.x, shotPoint.y, 'M');
            opponent.board.printBoardWithFogOfWar();
            System.out.println("You missed! Try again:");
        }

        return true;
    }

    public void move(Player opponent) {
        String shot;

        opponent.board.printBoardWithFogOfWar();
        System.out.println("---------------------");
        board.printBoard();

        System.out.printf("%s, it's your turn:", this.name);
        do {
            shot = scanner.nextLine().toUpperCase();
        } while (!isShotFired(shot, opponent));
    }

    private void placeAShip(Point first, Point second) {
        int rightY = Math.max(first.y, second.y);
        int leftY = Math.min(first.y, second.y);
        int bottomX = Math.max(first.x, second.x);
        int upperX = Math.min(first.x, second.x);

        for (int i = upperX; i < bottomX + 1; i++) {
            for (int j = leftY; j < rightY + 1; j++) {
                board.setCeil(i, j, 'O');
            }
        }
        board.printBoard();
    }

    private boolean isShipPlaced(Point first, Point second, Ships ship) {
        if (first.x != second.x && first.y != second.y) {
            System.out.println("Error! Wrong ship location! Try again:");
        } else if (Math.abs(first.x - second.x) != ship.getLength() - 1
                && Math.abs(first.y - second.y) != ship.getLength() - 1) {
            System.out.printf("Error! Wrong length of the %s! Try again:\n", ship.getTypeOfShip());
        } else if (isAShipNearby(first, second)) {
            System.out.println("Error! You placed it too close to another one. Try again:");
        } else {
            placeAShip(first, second);
            return true;
        }

        return false;
    }

    public void arangementOfShips() {
        Point firstCoordinate;
        Point secondCoordinate;

        System.out.printf("%s, place your ships to the game field\n", this.name);
        board.printBoard();
        for (Ships ship : Ships.values()) {
            System.out.printf("Enter the coordinates of the %s (%d cells):\n", ship.getTypeOfShip(), ship.getLength());
            do {
                String[] shipLocation = scanner.nextLine().toUpperCase().split(" ");
                firstCoordinate = new Point(shipLocation[0]. charAt(0), shipLocation[0].substring(1));
                secondCoordinate = new Point(shipLocation[1].charAt(0), shipLocation[1].substring(1));
            } while (!isShipPlaced(firstCoordinate, secondCoordinate, ship));
        }
    }
}
