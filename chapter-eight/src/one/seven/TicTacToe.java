package one.seven;

public class TicTacToe {

    private final Board board;
    private final Player firstPlayer;
    private final Player secondPlayer;
    private boolean gameHasBeenWon;
    private boolean gameIsADraw;

    public TicTacToe() {
        this.board = new Board();
        firstPlayer = new Player();
        secondPlayer = new Player();
    }

    public Board getBoard() {
        return board;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public void makeAMove(int cellNumber) {
        if(cellNumber < 1 || cellNumber > 9) {
            return;
        }

        if(firstPlayer.isNextToPlay()) {
            board.markCell(cellNumber, CellValue.X);
            firstPlayer.toggleTurn();
            secondPlayer.toggleTurn();
        } else {
            if(secondPlayer.isNextToPlay()) {
                board.markCell(cellNumber, CellValue.O);
                secondPlayer.toggleTurn();
                firstPlayer.toggleTurn();
            }
        }

        System.out.println(board.display());
        analyzeGame();
    }

    private void analyzeGame() {
        if(playerHasWon(CellValue.X)) {
            firstPlayer.setWon(true);
        } else {
            if(playerHasWon(CellValue.O)) {
                secondPlayer.setWon(true);
            }
        }

        if(firstPlayer.won() || secondPlayer.won()) {
            gameHasBeenWon = true;
        } else {
            if(board.isCompletelyFilled()) {
                gameIsADraw = true;
            }
        }
    }

    private boolean playerHasWon(CellValue cellValue) {
        return board.anyRowIsFilledWith(cellValue) || board.anyColumnIsFilledWith(cellValue) ||
                board.leftDiagonalIsFilledWith(cellValue) || board.rightDiagonalIsFilledWith(cellValue);
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public boolean gameHasBeenWon() {
        return gameHasBeenWon;
    }

    public boolean gameIsADraw() {
        return gameIsADraw;
    }
}
