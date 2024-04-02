package ui;

import model.Event;
import model.EventLog;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            new GameApp();
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    for (Event e : EventLog.getInstance()) {
                        System.out.println(e.getDescription());
                    }
                }
            });
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
