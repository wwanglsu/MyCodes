package wang.c11;

public class Coordinate implements Cloneable{
    public int row;
    public int column;

    public Coordinate(int r, int c){
        this.row=r;
        this.column=c;
    }

    public boolean inBounds(int[][] matrix){
        return row>=0 && column>=0 && row<matrix.length && column<matrix[0].length;
    }

    public boolean isBefore(Coordinate p){
        return row <= p.row && column <= p.column;
    }

    @Override
    public Object clone(){
        return new Coordinate(row, column);
    }

    public void moveDownRight(){
        row++;
        column++;
    }

    public void setToAverage(Coordinate min, Coordinate max){
        row=(min.row + max.row)/2;
        column=(min.column + max.column)/2;
    }
}
