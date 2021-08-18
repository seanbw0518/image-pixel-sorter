//SEAN WOMACK
//PROGRAM TO SORT PIXELS IN AN IMAGE FILE BY BINARY COLOR REPRESENTATION

import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    @Nullable
    public static int[] getImgDimensions(String name) {
        // opening the file
        String path = "C:\\Users\\seanb\\Documents\\Java Projects\\Pixel Sort\\data\\" + name;
        File f = new File(path);
        int[] dims = new int[2];
        try {
            BufferedImage image = ImageIO.read(f);
            dims[0] = image.getWidth();
            dims[1] = image.getHeight();

            return dims;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    public static BufferedImage openFile(String fileName) {
        // opening the file
        String path = "D:\\OneDrive\\Documents\\Programming\\Java\\Pixel Sort 2\\data\\" + fileName;
        File f = new File(path);

        try {
            // making 2d array of pixels
            BufferedImage image = ImageIO.read(f);
            BufferedImage out = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
            int[][] pixArray = new int[image.getWidth()][image.getHeight()];
            System.out.println("Image opened!");
            System.out.println("Working...Please Wait.");

            int[] rowArray = new int[image.getHeight()];
            // for each pixel in image...
            for (int i = 0; i < image.getWidth(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {

                    rowArray[j] = image.getRGB(i, j);
                    Arrays.sort(rowArray);
                    pixArray[i][j] = rowArray[j];

                }
            }

            // for each pixel in new image, set the pixel values
            for (int i = 0; i < image.getWidth(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {
                    out.setRGB(i, j, pixArray[i][j]);
                }
            }

            return out;
        } catch (IOException e) {
            System.out.println("Cannot open file!");
            return null;
        }
    }

    public static void exportImage(BufferedImage out, String name) {

        // write to file
        try {
            ImageIO.write(out, "png",
                    new File("D:\\OneDrive\\Documents\\Programming\\Java\\Pixel Sort 2\\data\\" + name));
            System.out.println("Done!");
            System.out.println("Output image in same directory as input image.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Place desired image file in 'D:\\OneDrive\\Documents\\Programming\\Java\\Pixel Sort 2\\data'.");
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the input filename (with type extension)");
        String filename = scan.nextLine();
        System.out.println("Enter the output filename (with type extension)");
        String filenameOut = scan.nextLine();
        exportImage(openFile(filename), filenameOut);
    }
}