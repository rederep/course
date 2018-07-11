package controller;

import exception.BD.FileNotFoundBDConfigEX;
import exception.DeletedGood;
import exception.ModelNotFoundEX;
import exception.NotDeletedWorkerEX;
import model.Client;
import model.Worker;
import service.*;

import java.util.Scanner;

public class DeleteCntrl {
    protected static boolean exit = false;

    public void doWork() {
        exit = false;
        showHelloMMessage();
        showMenu();
        ChoiceMain();
        System.out.println();
    }

    private static void showHelloMMessage() {
        System.out.println();
        System.out.println("**************************");
        System.out.println("Delete one of record by ID ");
        System.out.println("**************************");
        System.out.println();
    }

    private void showMenu() {
        System.out.println("What record you wont to be delete:");
        System.out.println("1. Client");
        System.out.println("2. Worker");
//        System.out.println("3. Subscription");
//        System.out.println("4. Discount of Sub");
//        System.out.println("5. Subscriptions Typ");
//        System.out.println("6. Visit");
//        System.out.println("7. Passport");
//        System.out.println("8. Specializations by worker");
//        System.out.println("9. Specialization");
        System.out.println("0. Return");
        System.out.print("Enter Value: ");

    }

    public void ChoiceMain() {
        Scanner s = new Scanner(System.in);
        int choice = s.nextInt();

        switch (choice) {
            case 1: {
                ClientSrvc srvc = new ClientSrvc();
                Scanner intS = new Scanner(System.in);
                Scanner str = new Scanner(System.in);
                System.out.println();
                System.out.print("Enter ID client to deleted:");
                int id = intS.nextInt();
                try {
                    Client result = null;
                    if (srvc.getClient(id) != null) {
                        result = srvc.getClient(id);
                        System.out.println(result);
                        System.out.println("Are you sure wont to delete: " + result.getFirstName() + " " + result.getLastName());
                        System.out.print("Enter \"yes\" to deleted:");
                        if ("yes".equals(str.nextLine())) {
                            srvc.deleteClient(id);
                            System.out.println("\n Clint wos deleted successful");
                        } else {
                            System.out.println("Wrong YES");
                        }
                    } else {
                        throw new ModelNotFoundEX(String.valueOf("ID Client="+ id));
                    }
                } catch (FileNotFoundBDConfigEX fileNotFoundBDConfigEX) {
                    fileNotFoundBDConfigEX.printStackTrace();
                } catch (ModelNotFoundEX modelNotFoundEX) {
                    //modelNotFoundEX.printStackTrace();
                }
                break;
            }
            case 2: {
                WorkerSrvc srvc = new WorkerSrvc();
                Scanner intS = new Scanner(System.in);
                Scanner str = new Scanner(System.in);
                System.out.println();
                System.out.print("Enter ID worker to deleted:");
                int id = intS.nextInt();

                try {
                   Worker result = null;
                    if (srvc.getWorker(id) != null) {
                        result = srvc.getWorker(id);
                        System.out.println(result);
                        System.out.println("Are you sure wont to delete: " + result.getFirstName() + " " + result.getLastName());
                        System.out.print("Enter \"yes\" to deleted:");
                        if ("yes".equals(str.nextLine())) {
                            try {
                                srvc.deleteWorker(id);
                            } catch (NotDeletedWorkerEX notDeletedWorkerEX) {
                                System.out.println(notDeletedWorkerEX.getMessage());
                            }catch (DeletedGood notDeletedWorkerEX) {
                                System.out.println(notDeletedWorkerEX.getMessage());
                            }
                        } else {
                            System.out.println("Wrong YES");
                        }
                    } else {
                        throw new ModelNotFoundEX(String.valueOf("ID Worker="+id));
                    }
                } catch (FileNotFoundBDConfigEX fileNotFoundBDConfigEX) {
                    fileNotFoundBDConfigEX.printStackTrace();
                } catch (ModelNotFoundEX modelNotFoundEX) {
                  //  modelNotFoundEX.printStackTrace();
                }
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
