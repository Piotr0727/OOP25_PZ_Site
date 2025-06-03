package org.example;

import images.ImageProcessor;

public class Main {
    public static void main(String[] args) {
        ImageProcessor ip = new ImageProcessor();
        ip.loadImage("img1.jpg");
        ip.changeBrightnessThreadPool(70);
        ip.saveImage("brighterImgThreadPool.jpg");
    }
}