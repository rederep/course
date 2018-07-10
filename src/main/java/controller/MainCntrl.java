package controller;

import dao.impl.ClientDAOImpl;
import dao.impl.SubscriptionDAOImpl;
import dao.impl.WorkerDAOImpl;
import dao.impl.factory.DAOImplFactory;
import exception.BD.FileNotFoundBDConfigEX;
import exception.ModelNotFoundEX;
import model.Administrator;
import service.AdministratorSrvc;
import service.ClientSrvc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class MainCntrl {
    static {
        showHelloMMessage();
    }

    public static void doWork() {

        showMenu();
        ChoiceMain();
        System.out.println();
    }


    private static void showHelloMMessage() {
        System.out.println("*********************");
        System.out.println("Fitness Center Columb");
        System.out.println("*********************");
        System.out.println();
    }

    protected static void showMenu() {
        System.out.println("Make your choice:");
        System.out.println("1. Show Clients");
        System.out.println("2. Show Workers");
        System.out.println("3. Show Subscription Types");
        System.out.println("4. Add...");
        System.out.println("5. Update...");
        System.out.println("6. Remove...(Client)");
        System.out.println("9. Administrator utility");
        System.out.println("0. Exit");
        System.out.print("Enter Value: ");

    }

    protected static void ChoiceMain() {
        Scanner s = new Scanner(System.in);
        int choice = s.nextInt();

        switch (choice) {
            case 1: {
                ClientSrvc clientSrvc = new ClientSrvc();
                try {
                    clientSrvc.getFullAllClients().forEach(System.out::println);
                } catch (FileNotFoundBDConfigEX fileNotFoundBDConfigEX) {
                    fileNotFoundBDConfigEX.printStackTrace();
                } catch (ModelNotFoundEX modelNotFoundEX) {
                    modelNotFoundEX.printStackTrace();
                }
                break;
            }
            case 2: {
                System.out.println("Sorry, method in development");
                WorkerDAOImpl workerDAO = DAOImplFactory.getWorkerInstance();

                try {
                    workerDAO.getAllWorkers().forEach(System.out::println);
                } catch (FileNotFoundBDConfigEX fileNotFoundBDConfigEX) {
                    fileNotFoundBDConfigEX.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
            case 3: {
                System.out.println("Sorry, method in development");
                SubscriptionDAOImpl subsDAO = DAOImplFactory.getSubscriptionInstance();
                try {
                    subsDAO.getAllSubscriptions().forEach(System.out::println);
                } catch (FileNotFoundBDConfigEX fileNotFoundBDConfigEX) {
                    fileNotFoundBDConfigEX.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
            case 4: {
                System.out.println("Sorry, method in development");
                break;
            }
            case 5: {
                System.out.println("Sorry, method in development");
                break;
            }
            case 6: {
                System.out.println("Sorry, method in development");
                System.out.print("Enter ID Client who must be destroyed");
                Scanner str = new Scanner(System.in);
                ClientDAOImpl clientDAO = DAOImplFactory.getClientInstance();
                try {
                    clientDAO.deleteClient(str.nextInt());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (FileNotFoundBDConfigEX fileNotFoundBDConfigEX) {
                    fileNotFoundBDConfigEX.printStackTrace();
                }
                break;
            }
            case 9: {
                System.out.print("Enter password:");
                Scanner str = new Scanner(System.in);
                AdministratorSrvc admSrv = new AdministratorSrvc();
                if (admSrv.checkAdminPass(new Administrator(str.nextLine()))) {
                    AdministratorCntrl administratorCntrl = new AdministratorCntrl();
                    administratorCntrl.doWork();
                } else {
                    System.out.println("Wrong Password!");
                }
                break;
            }
            case 0: {
                System.out.println("Exit...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(1);
            }
            default: {
                System.out.println("Bad choice");
            }

        }
    }


}
