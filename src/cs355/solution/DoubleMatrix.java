package cs355.solution;

/**
 * Created by Daniel on 12/8/14. ;)
 */
public class DoubleMatrix {

    int rows;
    int cols;
    double[][] data;

    public DoubleMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new double[rows][cols];
    }

    public void setIdentity() {
        for(int i = 0; i < rows && i < cols; i++)
            data[i][i] = 1.0;
    }

    public void concatenate( DoubleMatrix other ) {
        double[][] temp = new double[rows][cols];

        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                for (int k = 0; k < cols; k++) {
                    temp[row][col] += data[row][k] * other.get(k, col);
                }

        data = temp;
    }

    public Double get(int row, int col) {
        return data[row][col];
    }

    public void set(int row, int col, Double val) {
        data[row][col] = val;
    }

    public String toString() {
        String output = "";

        for (int row = 0; row < rows; row++) {
            output += "[";

            for (int col = 0; col < cols; col++) {
                output += data[row][col] + ", ";
            }

            output += "]";
            output += "\n";
        }

        return output;
    }
}
