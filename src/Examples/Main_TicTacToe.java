package Examples;

import java.util.Scanner;

class Player {
    private int id;
    private char marker;

    public Player(int id, char marker) {
        this.id = id;
        this.marker = marker;
    }

    public int getId() {
        return id;
    }

    public char getMarker() {
        return marker;
    }
}

class Move {
    private int x;
    private int y;
    private Player player;

    public Move(int x, int y, Player player) {
        this.x = x;
        this.y = y;
        this.player = player;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Player getPlayer() {
        return player;
    }
}

class Board {
    private int size;
    private char[][] state;
    private int remainingCount;

    public Board(int size) {
        this.size = size;
        reset();
    }

    public void reset() {
        state = new char[size][size];
        remainingCount = size * size;
    }

    public void placeMove(Move move) {
        int x = move.getX();
        int y = move.getY();
        if (state[x][y] != '\u0000') {
            throw new IllegalArgumentException("Illegal move, position already occupied");
        }
        state[x][y] = move.getPlayer().getMarker();
        remainingCount--;
    }

    public boolean checkWinner(Move lastMove) {
        if (lastMove == null) {
            return false;
        }
        return checkRow(lastMove.getX(), lastMove.getPlayer()) ||
                checkColumn(lastMove.getY(), lastMove.getPlayer()) ||
                checkDiagonal(lastMove.getPlayer()) ||
                checkCrossDiagonal(lastMove.getPlayer());
    }

    private boolean checkRow(int row, Player player) {
        for (int j = 0; j < size; j++) {
            if (state[row][j] != player.getMarker()) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(int column, Player player) {
        for (int i = 0; i < size; i++) {
            if (state[i][column] != player.getMarker()) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonal(Player player) {
        for (int i = 0; i < size; i++) {
            if (state[i][i] != player.getMarker()) {
                return false;
            }
        }
        return true;
    }

    private boolean checkCrossDiagonal(Player player) {
        for (int i = 0; i < size; i++) {
            if (state[i][size - 1 - i] != player.getMarker()) {
                return false;
            }
        }
        return true;
    }

    public boolean isOver() {
        return remainingCount == 0;
    }
}

class Game {
    private Player player1;
    private Player player2;
    private Board board;

    public Game(Player player1, Player player2, Board board) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = board;
    }

    public void playGame() {
        Player currentPlayer = player2;
        Move lastMove = null;
        Scanner scanner = new Scanner(System.in);

        while (!board.isOver() && !board.checkWinner(lastMove)) {
            currentPlayer = (currentPlayer.getId() % 2 == 1) ? player2 : player1;
            System.out.print("Enter player " + currentPlayer.getId() + "'s x move: ");
            int x = scanner.nextInt();
            System.out.print("Enter player " + currentPlayer.getId() + "'s y move: ");
            int y = scanner.nextInt();
            lastMove = new Move(x, y, currentPlayer);
            board.placeMove(lastMove);
        }

        if (board.checkWinner(lastMove)) {
            System.out.println("Player " + currentPlayer.getId() + " is the winner");
        } else {
            System.out.println("Game ended in a tie");
        }
        scanner.close();
    }
}

public class Main_TicTacToe {
    public static void main(String[] args) {
        Player player1 = new Player(1, 'X');
        Player player2 = new Player(2, 'O');
        Board board = new Board(3);
        Game game = new Game(player1, player2, board);
        game.playGame();
    }
}
