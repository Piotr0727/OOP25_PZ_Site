package images;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ImageProcessor {
    private BufferedImage img;
    public void loadImage(String imgPath){
        try {
            img = ImageIO.read(new File(imgPath));
        } catch (IOException e) {
            System.out.println("Error loading image");
        }
    }
    public void saveImage(String path){
        try {
            ImageIO.write(img, "jpg",new File(path));
        } catch (IOException e) {
            System.out.println("Error saving image");
        }
    }
    public void changeBrightness(int brightness){
        long start = System.currentTimeMillis();
        int width = img.getWidth();
        int height = img.getHeight();
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                Color c = new Color(img.getRGB(i,j));
                int r = Math.clamp(c.getRed() + brightness,0,255);
                int g = Math.clamp(c.getGreen() + brightness,0,255);
                int b = Math.clamp(c.getBlue() + brightness,0,255);
                img.setRGB(i,j, new Color(r,g,b).getRGB());
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Time taken to execute the function in miliseconds: "+(end-start));
    }
    public void changeBrightnessMultiThread(int brightness){
        long start1 = System.currentTimeMillis();
        int cores = Runtime.getRuntime().availableProcessors();
        Thread[] threads = new Thread[cores];
        int width = img.getWidth();
        int height = img.getHeight();
        int part = height / cores;
        for(int n = 0; n < cores; n++){
            int start = n * part;
            int end;
            if(n == cores-1){
                end = height;
            }
            else{
                end = start + part;
            }
            threads[n] = new Thread(() -> {
                for(int i = 0; i < width; i++){
                    for(int j = start; j < end; j++){
                        Color c = new Color(img.getRGB(i,j));
                        int r = Math.clamp(c.getRed() + brightness,0,255);
                        int g = Math.clamp(c.getGreen() + brightness,0,255);
                        int b = Math.clamp(c.getBlue() + brightness,0,255);
                        img.setRGB(i,j, new Color(r,g,b).getRGB());
                    }
                }
            });
            threads[n].start();
        }
        for(Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Error joining threads");
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Time taken to execute the function in miliseconds: "+(end-start1));
    }
    public void changeBrightnessThreadPool(int brightness){
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        int height = img.getHeight();
        int width = img.getWidth();
        for(int i = 0; i < height; i++){
            final int finalI = i;
            executorService.submit(() -> {
                for(int j = 0; j < width; j++) {
                    Color c = new Color(img.getRGB(j,finalI));
                    int r = Math.clamp(c.getRed() + brightness, 0, 255);
                    int g = Math.clamp(c.getGreen() + brightness, 0, 255);
                    int b = Math.clamp(c.getBlue() + brightness, 0, 255);
                    img.setRGB(j,finalI, new Color(r, g, b).getRGB());
                }
            });
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1,TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        System.out.println("Time taken to execute the function in miliseconds: "+(end-start));
    }
}
