package cs355.solution.model;

/**
 * Created by Daniel on 12/6/14. ;)
 */
public class Image {

    protected int[][] imageData;

    int width;
    int height;

    public Image ( int[][] data ) {
        imageData = data;
        width = data[0].length;
        height = data.length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPixel(int row, int col) {
        return imageData[row][col];
    }

    public int[][] getImageData() {
        return imageData;
    }

}
