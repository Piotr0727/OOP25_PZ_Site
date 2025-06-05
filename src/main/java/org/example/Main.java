package org.example;

import images.ImageProcessor;

public class Main {
    public static void main(String[] args) {
        ImageProcessor ip = new ImageProcessor();
        ip.loadImage("img1.jpg");
        ip.changeBrightness(10);
        ip.changeBrightnessMultiThread(10);
        ip.changeBrightnessThreadPool(10);
        ip.saveImage("brighterImgThreadPool.jpg");
    }
}