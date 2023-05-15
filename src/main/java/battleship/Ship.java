package battleship;

public class Ship {
    private int length;
    private String name;

    String[] shipMap;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Ship(String name, int length){
        this.length = length;
        this.name = name;
        this.shipMap = new String[length];
    }
}
