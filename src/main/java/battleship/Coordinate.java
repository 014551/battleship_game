package battleship;

public class Coordinate {

    private char lineChar;
    private int line;
    private int columnNum;
    private int column;


    public Coordinate (String coordinate) {
        this.lineChar = coordinate.charAt(0);
        this.line = convertLine(lineChar);
        this.columnNum = Integer.parseInt(coordinate.substring(1));
        this.column = columnNum - 1;
    }

    public int getLine() {
        return line;
    }


    public int getColumn() {
        return column;
    }

    private int convertLine(char line) {
        String alphabet = "ABCDEFGHIJ";
        int fromLine = -1;
        for (int l = 0; l < alphabet.length(); l++) {
            if (line == alphabet.charAt(l)) {
                fromLine = l;
            }
        }
        return fromLine;
    }


}
