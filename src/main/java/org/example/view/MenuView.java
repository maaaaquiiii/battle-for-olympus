package org.example.view;

import java.util.Scanner;

public class MenuView {
    private Scanner scanner;

    public MenuView() {
        this.scanner = new Scanner(System.in);
    }

    public int getUserInput() {
        return scanner.nextInt();
    }
}
