package main.project;

import main.project.model.Game;
import main.project.services.GameService;
import main.project.services.LogService;

import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    private static final String[] menu = {"quit", "next turn", "show field", "buy something", "new game"};

    public static void main(String[] args) {
        consoleMenu();
    }

    public static void consoleMenu() {
        Scanner scn = new Scanner(System.in);
        GameService gs = new GameService();
        try {
            PrintStream ps = new PrintStream(System.out);
            System.setOut(new PrintStream("log/logfile.log"));
            LogService ls = new LogService();
            int a;
            Game g = new Game();
            ps.println("New game started");
            ls.info("New game started");
            do {
                ps.println(getMenu());
                a = scn.nextInt();
                ls.info("User input: " + a + " (" + getMenu(a) + ")");
                String out;
                switch (a) {
                    case 1:
                        out = gs.nextTurn(g);
                        ps.println(out);
                        ls.info(out);
                        System.out.println();
                        System.out.println();

                        break;
                    case 3:
                        out = gs.nextTurn(g);
                        ps.println(out);
                        ls.info(out);
                        System.out.println();

                        break;
                    case 2:
                        out = gs.printField(g);
                        ps.println(out);
                        ls.info(out);
                        break;
                    default:
                        a = 0;
                        break;
                }
                if (gs.checkWin(g) != null) {
                    a = 0;
                }
            } while (a != 0);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static String getMenu() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < menu.length + 1; i++) {
            sb.append(i).append(" - ").append(getMenu(i)).append("\n");
        }
        return sb.toString();
    }

    private static String getMenu(int a) {
        return a < menu.length ? menu[a] : menu[0];
    }
}
