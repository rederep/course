package controller;

import dao.impl.ClientDAOImpl;
import dao.impl.factory.DAOImplFactory;
import dto.ClientDTO.ClientDTO;
import dto.WorkerDTO;
import exception.BD.FileNotFoundBDConfigEX;
import exception.ModelNotFoundEX;
import model.Administrator;
import model.Client;
import model.Worker;
import service.AdministratorSrvc;
import service.ClientSrvc;
import service.SubscriptionSrvc;
import service.WorkerSrvc;

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
        System.out.println("3. Show...");
        System.out.println("4. Add...");
        System.out.println("5. Update...");
        System.out.println("6. Remove...(Client)");
        System.out.println("7. Fined Clients by First/Last Name");
        System.out.println("8. Fined Workers by First/Last Name");
        System.out.println("9. Subscriptions Info (Discounts, Types)");
        System.out.println("10. Administrator utility");
        System.out.println("0. Exit");
        System.out.print("Enter Value: ");

    }

    protected static void ChoiceMain() {
        Scanner s = new Scanner(System.in);
        int choice = s.nextInt();

        switch (choice) {
            case 1: {
                ClientSrvc clientSrvc = new ClientSrvc();
                System.out.println();
                try {
                    for (Client client : clientSrvc.getFullAllClients()) {
                        System.out.println(ClientDTO.toDTO(client));
                    }
                } catch (FileNotFoundBDConfigEX fileNotFoundBDConfigEX) {
                    fileNotFoundBDConfigEX.printStackTrace();
                } catch (ModelNotFoundEX modelNotFoundEX) {
                    modelNotFoundEX.printStackTrace();
                }
                break;
            }
            case 2: {
                WorkerSrvc workerSrvc = new WorkerSrvc();
                System.out.println();
                try {
                    for (Worker worker : workerSrvc.getFullAllWorkers()) {
                        System.out.println(WorkerDTO.toDTO(worker));
                    }
                } catch (FileNotFoundBDConfigEX fileNotFoundBDConfigEX) {
                    fileNotFoundBDConfigEX.printStackTrace();
                } catch (ModelNotFoundEX modelNotFoundEX) {
                    modelNotFoundEX.printStackTrace();
                }

                break;
            }
            case 3: {
                do {
                    new ShowCntrl().doWork();
                } while (!ShowCntrl.exit);
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
            case 7: {
                System.out.print("Enter FirsName or LastName of Client:");
                Scanner str = new Scanner(System.in);
                try {
                    new ClientSrvc().getClientsByName(str.nextLine()).forEach(client -> System.out.println(ClientDTO.toDTO(client)));
                } catch (ModelNotFoundEX modelNotFoundEX) {
                    modelNotFoundEX.printStackTrace();
                } catch (FileNotFoundBDConfigEX fileNotFoundBDConfigEX) {
                    fileNotFoundBDConfigEX.printStackTrace();
                }
                break;
            }
            case 8: {
                System.out.print("Enter FirsName or LastName of Worker:");
                Scanner str = new Scanner(System.in);
                try {
                    new WorkerSrvc().getWorkersByName(str.nextLine()).forEach(worker -> System.out.println(WorkerDTO.toDTO(worker)));
                } catch (ModelNotFoundEX modelNotFoundEX) {
                    modelNotFoundEX.printStackTrace();
                } catch (FileNotFoundBDConfigEX fileNotFoundBDConfigEX) {
                    fileNotFoundBDConfigEX.printStackTrace();
                }
                break;
            }
            case 9:{
                try {
                    System.out.println();
                    System.out.println("TYPE OF SUBSCRIPTIONS:");
                    new SubscriptionSrvc().getAllSubsType().forEach(System.out::println);
                    System.out.println();
                    System.out.println("DISCOUNTS:");
                    new SubscriptionSrvc().getAllDiscounts().forEach(System.out::println);


                } catch (FileNotFoundBDConfigEX fileNotFoundBDConfigEX) {
                    fileNotFoundBDConfigEX.printStackTrace();
                } catch (ModelNotFoundEX modelNotFoundEX) {
                    modelNotFoundEX.printStackTrace();
                }
                break;
            }
            case 10: {
                System.out.print("Enter password:");
                Scanner str = new Scanner(System.in);
                AdministratorSrvc admSrv = new AdministratorSrvc();
                if (admSrv.checkAdminPass(new Administrator(str.nextLine()))) {
                    AdministratorCntrl administratorCntrl = new AdministratorCntrl();
                    do {
                        administratorCntrl.doWork();
                    } while (!AdministratorCntrl.exit);

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
