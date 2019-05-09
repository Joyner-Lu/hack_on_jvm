package org.eclipse.swt.widgets;


public class Display {

    private static Display display = new Display();


    public static Display getDefault() {
        return display;
    }

    public static void syncExec(Runnable runnable) {

    }

    public static Shell getActiveShell() {
        return null;
    }

    public static void main(String[] args) {
    }
}
