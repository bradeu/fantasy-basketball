package ui;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            new GameApp();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
