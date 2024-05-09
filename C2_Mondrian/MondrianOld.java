import java.util.*;
import java.awt.*;

public class MondrianOld {

    public void paintBasicMondrian(Color[][] pixels) {
        int initialWidth = pixels[0].length;
        int initialHeight = pixels.length;
        Random rand = new Random();
        divideCanvas(pixels, 0, pixels.length, 0, pixels[0].length, initialWidth / 4, initialHeight / 4, rand);
    }

    public void paintComplexMondrian(Color[][] pixels) {
        int initialWidth = pixels[0].length;
        int initialHeight = pixels.length;
    }

    public void divideCanvas(Color[][] pixels, int x1, int x2, int y1, int y2, int limitWidth, int limitHeight, Random rand) {
        int canvasWidth = pixels[0].length;
        int canvasHeight = pixels.length;
    
        if (x2 - x1 < limitWidth && y2 - y1 < limitHeight) {
            fill(pixels, x1, x2, y1, y2, randomColor());
        } else if (x2 - x1 < limitWidth) {
            int splitY = rand.nextInt(y1 + canvasHeight / 4, y2 - canvasHeight / 4);
        
            divideCanvas(pixels, x1, x2, y1, splitY, limitWidth, limitHeight, rand); // Top
            divideCanvas(pixels, x1, x2, splitY, y2, limitWidth, limitHeight, rand); // Bottom

        } else if (y2 - y1 < limitHeight) {
            int splitX = rand.nextInt(x1 + canvasWidth / 4, x2 - canvasWidth / 4);
        
            divideCanvas(pixels, x1, splitX, y1, y2, limitWidth, limitHeight, rand); // Left
            divideCanvas(pixels, splitX, x2, y1, y2, limitWidth, limitHeight, rand); // Right
        } else {
            int splitX = rand.nextInt(x1 + canvasWidth / 4, x2 - canvasWidth / 4);
            int splitY = rand.nextInt(y1 + canvasHeight / 4, y2 - canvasHeight / 4);
        
            divideCanvas(pixels, x1, splitX, y1, splitY, limitWidth, limitHeight, rand); // Top-left
            divideCanvas(pixels, splitX, x2, y1, splitY, limitWidth, limitHeight, rand); // Top-right
            divideCanvas(pixels, x1, splitX, splitY, y2, limitWidth, limitHeight, rand); // Bottom-left
            divideCanvas(pixels, splitX, x2, splitY, y2, limitWidth, limitHeight, rand); // Bottom-right
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

    // public Color fromLocation(initialWidth, initialHeight) {
    //     return Color.WHITE;
    //     // will need to interpolate between the starting size the client chooses for the image
    // }

    // NOTE: This method is only good for filling with a partial border
    // Behavior: takes in an array of pixel rgb values and sets all values 1 pixel inside the
    //           the region to be white
    // Parameters: pixels - this is the array of rgb values for all pixels in the region we wish
    //                      to manipulate
    //             x1 - this is the x-ccordinate of the top left corner of the region that we
    //                  are working with
    //             x2 - this is the bottom right corner of the region we're working with's x-value
    //             y1 - this is the top left corner of the region we're working with's y-value
    //             y2 - this is the bottom right corner of the regionn we're working with y-value
    // Returns: n/a
    // Exceptions: checks if the values presented as parameters don't make mathematical sense for
    //             changing the pixel values of the region we wish to use. If so, there will be an
    //             IllegalArgumentException thrown
    public void fill(Color[][] pixels, int x1, int x2, int y1, int y2, Color color) {
        if (x1 >= x2 || y1 >= y2 || x1 < 0 || y1 < 0) {
            throw new IllegalArgumentException("ooof are you dumb");
        }

        for (int i = y1 + 1; i <= y2 - 1; i++) { // Rows is the y-axis
            for (int j = x1 + 1; j <= x2 - 1; j++) { // Columns is the x-axis... a bit tricky here
                pixels[i][j] = color;
            }
        }
    }
}