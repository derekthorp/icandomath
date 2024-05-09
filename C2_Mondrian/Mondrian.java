import java.util.*;
import java.awt.*;

public class Mondrian {
    
    // Behavior: produces a 'basic' mondrian painting
    // Parameters: pixels - the color array for all rgb values for the canvas to produce
    // Returns: n/a
    // Exceptions: n/a
    public void paintBasicMondrian(Color[][] pixels) {
        String fillType = "basic";
        divideCanvas(pixels, 0, pixels[0].length, 0, pixels.length, fillType);
    }

    // Behavior: produces a 'complex' mondrian painting
    // Parameters: pixels - the color array for all rgb values for the canvas to produce
    // Returns: n/a
    // Exceptions: n/a
    public void paintComplexMondrian(Color[][] pixels) {
        String fillType = "complex";
        divideCanvas(pixels, 0, pixels[0].length, 0, pixels.length, fillType);
    }
    
    // Behavior: divides a given canvas into segments and then fills them in accordance to their
    //           assigned fill type
    // Parameters: pixels - the color array for all rgb values for the canvas to produce
    //             x1 - the x-value for the top left corner of the current chunk of the canvas
    //             x2 - the x-value for the bottom right corner of the current chunk of the canvas
    //             y1 - the y-value for the top left corner of the current chunk of the canvas
    //             y2 - the y-value for the bottom right corner of the current chunk of the canvas
    //             fillType - the designation for what kind of colors should fill each chunk
    // Returns: n/a
    // Exceptions: n/a
    public void divideCanvas(Color[][] pixels, int x1, int x2, int y1, int y2, String fillType) {
        int initialWidth = pixels[0].length;
        int initialHeight = pixels.length;
    
        Random rand = new Random();

        int cutXRange = x2 - 9 - (x1 + 10);
        int cutYRange = y2 - 9 - (y1 + 10);
    
        if (x2 - x1 >= initialWidth / 4 && y2 - y1 >= initialHeight / 4) {
            if (cutXRange > 0 && cutYRange > 0) {
                int cutX = rand.nextInt(cutXRange) + (x1 + 10);
                int cutY = rand.nextInt(cutYRange) + (y1 + 10);
    
                divideCanvas(pixels, x1, cutX, y1, cutY, fillType);
                divideCanvas(pixels, cutX, x2, y1, cutY, fillType);
                divideCanvas(pixels, x1, cutX, cutY, y2, fillType);
                divideCanvas(pixels, cutX, x2, cutY, y2, fillType);
            }
        } else if (y2 - y1 >= initialHeight / 4) {
            if (cutYRange > 0) {
                int cutY = rand.nextInt(cutYRange) + (y1 + 10);
    
                divideCanvas(pixels, x1, x2, y1, cutY, fillType);
                divideCanvas(pixels, x1, x2, cutY, y2, fillType);
            } else {
                fill(pixels, x1, x2, y1, y2, fillType);
            }
        } else if (x2 - x1 >= initialWidth / 4) {
            if (cutXRange > 0) {
                int cutX = rand.nextInt(cutXRange) + (x1 + 10);
    
                divideCanvas(pixels, x1, cutX, y1, y2, fillType);
                divideCanvas(pixels, cutX, x2, y1, y2, fillType);
            } else {
                fill(pixels, x1, x2, y1, y2, fillType);
            }
        } else {
            fill(pixels, x1, x2, y1, y2, fillType);
        }
    }
    
    // Behavior: takes in an array of pixel rgb values and sets all values 1 pixel inside the
    //           the region to be white. the 1 pixel border remains black.
    // Parameters: pixels - this is the array of rgb values for all pixels in the region we wish
    //                      to manipulate
    //             x1 - this is the x-ccordinate of the top left corner of the region that we
    //                  are working with
    //             x2 - this is the bottom right corner of the region we're working with's x-value
    //             y1 - this is the top left corner of the region we're working with's y-value
    //             y2 - this is the bottom right corner of the regionn we're working with y-value
    //             color - this is the color assigned to the region we'd like to fill
    // Returns: n/a
    // Exceptions: checks if the values presented as parameters don't make mathematical sense for
    //             changing the pixel values of the region we wish to use. If so, there will be an
    //             IllegalArgumentException thrown
    public void fill(Color[][] pixels, int x1, int x2, int y1, int y2, String fillType) {
        if (x1 >= x2 || y1 >= y2 || x1 < 0 || y1 < 0) {
            throw new IllegalArgumentException("ooof are you dumb");
        }

        Color color = randomColor();
        Color location = colorFromLocation(x1, y1);

        for (int i = y1 + 1; i < y2 - 1; i++) { // Rows is the y-axis
            for (int j = x1 + 1; j < x2 - 1; j++) { // Columns is the x-axis... a bit tricky here
                if (fillType.equals("basic")) {
                    pixels[i][j] = color;
                } else {
                    pixels[i][j] = location;
                }
            }
        }
    }

    // Behavior: randomly selects a color between red, yellow, cyan, and white
    // Parameters: n/a
    // Returns: a color that has randomly been selected
    // Exceptions: n/a
    public Color randomColor() {
        Random rand = new Random();
        int number = rand.nextInt(4);

        if (number == 0) {
            return Color.RED;
        } else if (number == 1) {
            return Color.YELLOW;
        } else if (number == 2) {
            return Color.CYAN;
        } else {
            return Color.WHITE;
        }
    }

    // Behavior: will generate a color based off the location of the fill on the board
    // Parameters: xVal, yVal - the top left coordinates of the region we wish to fill
    // Returns: a color that reflects the position of the fill on the canvas
    // Exceptions: n/a
    public Color colorFromLocation(int xVal, int yVal) {
        int redValue = xVal % 255;
        int blueValue = yVal % 255;
        int greenValue = 100;

        return new Color(redValue, greenValue, blueValue);
    }
}
