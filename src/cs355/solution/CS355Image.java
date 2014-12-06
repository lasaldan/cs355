package cs355.solution;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

/**
 * Created by Daniel on 12/6/14. ;)
 */
public class CS355Image {

    protected Dimension size;
    protected BufferedImage original;
    protected BufferedImage altered;
    protected int[][] rawData;

    public CS355Image ( BufferedImage openImage ) {

        int width = openImage.getWidth();
        int height = openImage.getHeight();
        size = new Dimension(width, height);

        rawData = new int[height][width];

        original = openImage;
        altered = openImage;

    }

    public void adjustRawData(BufferedImage openImage) {
        for(int row = 0; row < size.height; row++) {
            for(int col = 0; col < size.width; col++) {
                // Since all grayscale, only need one color component
                rawData[row][col] = openImage.getRGB(col,row)>>16&255;
            }
        }
    }
}
