package controller;

import model.Administrator;
import service.AdministratorSrvc;

import java.util.Scanner;

public class AdministratorCntrl {
    private AdministratorSrvc admSrv = new AdministratorSrvc();
    protected static boolean exit = false;

    static {
        showHelloMMessage();
    }

    public void doWork() {
        exit = false;
        showMenu();
        ChoiceMain();
        System.out.println();
    }

    private static void showHelloMMessage() {
        System.out.println();
        System.out.println("******************");
        System.out.println("Administrator Menu");
        System.out.println("******************");
        System.out.println();
    }

    private void showMenu() {
        System.out.println("Make your choice:");
        System.out.println("1. Create All Tables");
        System.out.println("2. Insert All Tables");
        System.out.println("3. Drop All Tables");
        System.out.println("4. Change password");
        System.out.println("0. Return");
        System.out.print("Enter Value: ");

    }

    public void ChoiceMain() {
        Scanner s = new Scanner(System.in);
        int choice = s.nextInt();

        switch (choice) {
            case 1: {
                admSrv.createAllTables();
                System.out.println("All tables create successful!");
                break;
            }
            case 2: {
                admSrv.insertAllTables();
                System.out.println("All inserts successful!");
                break;
            }
            case 3: {
                System.out.println("Are you sure wont drop all tables with informations?");
                System.out.print("Enter \"yes\" to drop:");
                Scanner str = new Scanner(System.in);
                if ("yes".equals(str.nextLine())) {
                    admSrv.deleteAllTables();
                    System.out.println("All tables drop successful!");
                } else {
                    System.out.println("Wrong YES");
                }
                break;
            }
            case 4: {
                Scanner str = new Scanner(System.in);
                System.out.print("Enter new password:");
                admSrv.createPassAdmin(new Administrator(str.nextLine()));
                System.out.println("Password change successful!");

                break;
            }
            case 0: {
                exit = true;
                break;
            }
            default: {
                System.out.println("Bad choice");
            }

        }
    }
}
