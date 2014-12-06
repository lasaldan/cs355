package cs355.solution.model;

import cs355.solution.model.Image;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Daniel on 12/6/14. ;)
 */
public abstract class ImageTools {

    public static Double sum(List<Integer> list) {
        Double sum=0.0;
        for (Integer i:list)
            sum = sum + i;
        return sum;
    }
    public static Double average(List<Integer> list) {
        return sum(list)/list.size();
    }

    public static Double median(List<Integer> list) {
        Collections.sort(list);
        return list.get(4)*1.0;
    }

    public static void brighten(Image img, int adjustmentVal) {

        int[][] image = img.getImageData();
        int width = image[0].length;
        int height = image.length;

        for(int row = 0; row < height; row++) {
            for(int col = 0; col < width; col++) {
                int greyscaleColor = image[row][col];

                // make adjustment
                greyscaleColor += adjustmentVal;

                // make sure we didn't go too high or too low
                if( greyscaleColor > 255 )
                    greyscaleColor = 255;
                if( greyscaleColor < 0 )
                    greyscaleColor = 0;

                image[row][col] = greyscaleColor;
            }
        }
    }

    public static void contrast(Image img, int adjustmentVal) {

        int[][] image = img.getImageData();
        int width = image[0].length;
        int height = image.length;

        for(int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int greyscaleColor = image[row][col];

                double c = adjustmentVal;

                c = adjustmentVal + 100; // c+100
                c /= 100; // divide by 100

                c *= c; // pow(2)
                c *= c; // pow(4)

                c *= (greyscaleColor-128); // r-128

                c += 128; // +128 : Should be final "s"

                greyscaleColor = (int)c;

                // make sure we didn't go too high or too low
                if( greyscaleColor > 255 )
                    greyscaleColor = 255;
                if( greyscaleColor < 0 )
                    greyscaleColor = 0;

                image[row][col] = greyscaleColor;
            }
        }
    }

    public static void blur(Image img) {

        int[][] image = img.getImageData();
        int width = image[0].length;
        int height = image.length;

        int[][] buffer = new int[height][width];

        for(int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                List<Integer> points = new ArrayList();
                int greyscaleColor = image[row][col];


                // add upperleft
                if(row == 0) {
                    if(col == 0)
                        points.add(greyscaleColor);
                    else
                        points.add(image[row][col-1]);
                }
                else {
                    if(col == 0)
                        points.add(image[row-1][col]);
                    else
                        points.add(image[row-1][col-1]);
                }

                // add upper middle
                if(row == 0)
                    points.add(greyscaleColor);
                else
                    points.add(image[row-1][col]);

                // add upper right
                if(row == 0) {
                    if(col == width-1)
                        points.add(greyscaleColor);
                    else
                        points.add(image[row][col+1]);
                }
                else {
                    if(col == width-1)
                        points.add(image[row-1][col]);
                    else
                        points.add(image[row-1][col+1]);
                }

                // add left middle
                if(col == 0)
                    points.add(greyscaleColor);
                else
                    points.add(image[row][col-1]);

                // add Center
                points.add(greyscaleColor);

                // add right middle
                if(col == width-1)
                    points.add(greyscaleColor);
                else
                    points.add(image[row][col+1]);

                // add lower left
                if(row == height-1) {
                    if(col == 0)
                        points.add(greyscaleColor);
                    else
                        points.add(image[row][col-1]);
                }
                else {
                    if(col == 0)
                        points.add(image[row+1][col]);
                    else
                        points.add(image[row+1][col-1]);
                }

                // add lower middle
                if(row == height-1)
                    points.add(greyscaleColor);
                else
                    points.add(image[row+1][col]);

                // add lower right
                if(row == height-1) {
                    if(col == width-1)
                        points.add(greyscaleColor);
                    else
                        points.add(image[row][col+1]);
                }
                else {
                    if(col == width-1)
                        points.add(image[row+1][col]);
                    else
                        points.add(image[row+1][col+1]);
                }

                assert points.size() == 9;
                double newVal = average(points);

                buffer[row][col] = (int)newVal;
            }
        }

        // copy buffer back into image
        for(int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                image[row][col] = buffer[row][col];
                //System.out.println("Row: " + row + " Col: " + col + " Val: " + image[row][col]);
            }
        }
    }
    public static void median(Image img) {

        int[][] image = img.getImageData();
        int width = image[0].length;
        int height = image.length;

        int[][] buffer = new int[height][width];

        for(int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                List<Integer> points = new ArrayList();
                int greyscaleColor = image[row][col];


                // add upperleft
                if(row == 0) {
                    if(col == 0)
                        points.add(greyscaleColor);
                    else
                        points.add(image[row][col-1]);
                }
                else {
                    if(col == 0)
                        points.add(image[row-1][col]);
                    else
                        points.add(image[row-1][col-1]);
                }

                // add upper middle
                if(row == 0)
                    points.add(greyscaleColor);
                else
                    points.add(image[row-1][col]);

                // add upper right
                if(row == 0) {
                    if(col == width-1)
                        points.add(greyscaleColor);
                    else
                        points.add(image[row][col+1]);
                }
                else {
                    if(col == width-1)
                        points.add(image[row-1][col]);
                    else
                        points.add(image[row-1][col+1]);
                }

                // add left middle
                if(col == 0)
                    points.add(greyscaleColor);
                else
                    points.add(image[row][col-1]);

                // add Center
                points.add(greyscaleColor);

                // add right middle
                if(col == width-1)
                    points.add(greyscaleColor);
                else
                    points.add(image[row][col+1]);

                // add lower left
                if(row == height-1) {
                    if(col == 0)
                        points.add(greyscaleColor);
                    else
                        points.add(image[row][col-1]);
                }
                else {
                    if(col == 0)
                        points.add(image[row+1][col]);
                    else
                        points.add(image[row+1][col-1]);
                }

                // add lower middle
                if(row == height-1)
                    points.add(greyscaleColor);
                else
                    points.add(image[row+1][col]);

                // add lower right
                if(row == height-1) {
                    if(col == width-1)
                        points.add(greyscaleColor);
                    else
                        points.add(image[row][col+1]);
                }
                else {
                    if(col == width-1)
                        points.add(image[row+1][col]);
                    else
                        points.add(image[row+1][col+1]);
                }

                assert points.size() == 9;
                double newVal = median(points);

                buffer[row][col] = (int)newVal;
            }
        }

        // copy buffer back into image
        for(int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                image[row][col] = buffer[row][col];
                //System.out.println("Row: " + row + " Col: " + col + " Val: " + image[row][col]);
            }
        }
    }
    public static void edgeDetect(Image img) {

        double[][] xComponent = sorbel_x(img);
        double[][] yComponent = sorbel_y( img );

        int[][] imgData = img.getImageData();

        for(int row = 0; row < img.getHeight(); row++) {
            for (int col = 0; col < img.getWidth(); col++) {

                double x2 = xComponent[row][col] * xComponent[row][col];
                double y2 = yComponent[row][col] * yComponent[row][col];

                double newVal = Math.sqrt(x2 + y2);
                imgData[row][col] = (int) newVal + 128;
            }
        }
    }
    public static double[][] sorbel_x(Image img) {

        int[][] image = img.getImageData();
        int width = image[0].length;
        int height = image.length;

        double[][] buffer = new double[height][width];

        for(int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                List<Integer> points = new ArrayList();
                int greyscaleColor = image[row][col];


                // add upperleft
                if(row == 0) {
                    if(col == 0)
                        points.add(-greyscaleColor);
                    else
                        points.add(-image[row][col-1]);
                }
                else {
                    if(col == 0)
                        points.add(-image[row-1][col]);
                    else
                        points.add(-image[row-1][col-1]);
                }

                // add upper right
                if(row == 0) {
                    if(col == width-1)
                        points.add(greyscaleColor);
                    else
                        points.add(image[row][col+1]);
                }
                else {
                    if(col == width-1)
                        points.add(image[row-1][col]);
                    else
                        points.add(image[row-1][col+1]);
                }

                // add left middle
                if(col == 0)
                    points.add(-2*greyscaleColor);
                else
                    points.add(-2*image[row][col-1]);

                // add right middle
                if(col == width-1)
                    points.add(2*greyscaleColor);
                else
                    points.add(2*image[row][col+1]);

                // add lower left
                if(row == height-1) {
                    if(col == 0)
                        points.add(-greyscaleColor);
                    else
                        points.add(-image[row][col-1]);
                }
                else {
                    if(col == 0)
                        points.add(-image[row+1][col]);
                    else
                        points.add(-image[row+1][col-1]);
                }

                // add lower right
                if(row == height-1) {
                    if(col == width-1)
                        points.add(greyscaleColor);
                    else
                        points.add(image[row][col+1]);
                }
                else {
                    if(col == width-1)
                        points.add(image[row+1][col]);
                    else
                        points.add(image[row+1][col+1]);
                }

                assert points.size() == 6;
                double newVal = sum(points) / 8;

                buffer[row][col] = newVal;
            }
        }
        return buffer;
    }

    public static double[][] sorbel_y(Image img) {

        int[][] image = img.getImageData();
        int width = image[0].length;
        int height = image.length;

        double[][] buffer = new double[height][width];

        for(int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                List<Integer> points = new ArrayList();
                int greyscaleColor = image[row][col];


                // add upperleft
                if(row == 0) {
                    if(col == 0)
                        points.add(-greyscaleColor);
                    else
                        points.add(-image[row][col-1]);
                }
                else {
                    if(col == 0)
                        points.add(-image[row-1][col]);
                    else
                        points.add(-image[row-1][col-1]);
                }

                // add upper middle
                if(row == 0)
                    points.add(-2*greyscaleColor);
                else
                    points.add(-2*image[row-1][col]);

                // add upper right
                if(row == 0) {
                    if(col == width-1)
                        points.add(-greyscaleColor);
                    else
                        points.add(-image[row][col+1]);
                }
                else {
                    if(col == width-1)
                        points.add(-image[row-1][col]);
                    else
                        points.add(-image[row-1][col+1]);
                }


                // add lower left
                if(row == height-1) {
                    if(col == 0)
                        points.add(greyscaleColor);
                    else
                        points.add(image[row][col-1]);
                }
                else {
                    if(col == 0)
                        points.add(image[row+1][col]);
                    else
                        points.add(image[row+1][col-1]);
                }

                // add lower middle
                if(row == height-1)
                    points.add(2*greyscaleColor);
                else
                    points.add(2*image[row+1][col]);

                // add lower right
                if(row == height-1) {
                    if(col == width-1)
                        points.add(greyscaleColor);
                    else
                        points.add(image[row][col+1]);
                }
                else {
                    if(col == width-1)
                        points.add(image[row+1][col]);
                    else
                        points.add(image[row+1][col+1]);
                }

                assert points.size() == 6;
                double newVal = sum(points) / 8;

                buffer[row][col] = newVal;
            }
        }
        return buffer;
    }
    public static void sharpen(Image img) {

        int[][] image = img.getImageData();
        int width = image[0].length;
        int height = image.length;

        int[][] buffer = new int[height][width];

        for(int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                List<Integer> points = new ArrayList();
                int greyscaleColor = image[row][col];


                // add upper middle
                if(row == 0)
                    points.add(-greyscaleColor);
                else
                    points.add(-image[row-1][col]);

                // add left middle
                if(col == 0)
                    points.add(-greyscaleColor);
                else
                    points.add(-image[row][col-1]);

                // add Center
                points.add(6*greyscaleColor);

                // add right middle
                if(col == width-1)
                    points.add(-greyscaleColor);
                else
                    points.add(-image[row][col+1]);

                // add lower middle
                if(row == height-1)
                    points.add(-greyscaleColor);
                else
                    points.add(-image[row+1][col]);


                assert points.size() == 5;
                double newVal = sum(points) / 2;

                // make sure we didn't go too high or too low
                if( newVal > 255 )
                    newVal = 255;
                if( newVal < 0 )
                    newVal = 0;

                buffer[row][col] = (int)newVal;
            }
        }

        // copy buffer back into image
        for(int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                image[row][col] = buffer[row][col];
                //System.out.println("Row: " + row + " Col: " + col + " Val: " + image[row][col]);
            }
        }
    }

    public static void kernel(Image img) {

        int[][] image = img.getImageData();
        int width = image[0].length;
        int height = image.length;

        int[][] kernel = {
            {1,1,1},
            {1,1,1},
            {1,1,1}
        };

        for(int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                List<Integer> points = new ArrayList();
                int greyscaleColor = image[row][col];

                for(int krow = 0; krow < kernel.length; krow++) {
                    for( int kcol = 0; kcol < kernel[0].length; kcol++) {

                    }
                }
                points.add(greyscaleColor);





                image[row][col] = greyscaleColor;
            }
        }
    }

    public static void noChange(Image img, int adjustmentVal) {

        int[][] image = img.getImageData();
        int width = image[0].length;
        int height = image.length;

        for(int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int greyscaleColor = image[row][col];

                image[row][col] = greyscaleColor;
            }
        }
    }
}
