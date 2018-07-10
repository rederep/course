package controller;

import exception.BD.FileNotFoundBDConfigEX;
import exception.ModelNotFoundEX;
import model.Administrator;
import model.SpecByWorker;
import service.*;

import java.util.Scanner;

public class ShowCntrl {
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
        System.out.println("*******************************");
        System.out.println("Show INFO for Technical display");
        System.out.println("*******************************");
        System.out.println();
    }

    private void showMenu() {
        System.out.println("What you wont to see:");
        System.out.println("1. Clients");
        System.out.println("2. Workers");
        System.out.println("3. Subscriptions");
        System.out.println("4. Discount of Subs");
        System.out.println("5. Subscriptions Type");
        System.out.println("6. Visits");
        System.out.println("7. Passports");
        System.out.println("8. Specializations by workers");
        System.out.println("9. Specializations");
        System.out.println("0. Return");
        System.out.print("Enter Value: ");

    }

    public void ChoiceMain() {
        Scanner s = new Scanner(System.in);
        int choice = s.nextInt();

        switch (choice) {
            case 1: {
                System.out.println();
                try {
                    new ClientSrvc().getFullAllClients().forEach(System.out::println);
                } catch (FileNotFoundBDConfigEX fileNotFoundBDConfigEX) {
                    fileNotFoundBDConfigEX.printStackTrace();
                } catch (ModelNotFoundEX modelNotFoundEX) {
                    modelNotFoundEX.printStackTrace();
                }
                break;
            }
            case 2: {
                System.out.println();
                try {
                    new WorkerSrvc().getFullAllWorkers().forEach(System.out::println);
                } catch (FileNotFoundBDConfigEX fileNotFoundBDConfigEX) {
                    fileNotFoundBDConfigEX.printStackTrace();
                } catch (ModelNotFoundEX modelNotFoundEX) {
                    modelNotFoundEX.printStackTrace();
                }
                break;
            }
            case 3: {
                System.out.println();
                try {
                    new SubscriptionSrvc().getAllSubscriptions().forEach(System.out::println);
                } catch (FileNotFoundBDConfigEX fileNotFoundBDConfigEX) {
                    fileNotFoundBDConfigEX.printStackTrace();
                } catch (ModelNotFoundEX modelNotFoundEX) {
                    modelNotFoundEX.printStackTrace();
                }
                break;
            }
            case 4: {
                System.out.println();
                try {
                    new SubscriptionSrvc().getAllDiscounts().forEach(System.out::println);
                } catch (FileNotFoundBDConfigEX fileNotFoundBDConfigEX) {
                    fileNotFoundBDConfigEX.printStackTrace();
                } catch (ModelNotFoundEX modelNotFoundEX) {
                    modelNotFoundEX.printStackTrace();
                }
                break;
            }
            case 5: {
                System.out.println();
                try {
                    new SubscriptionSrvc().getAllSubsType().forEach(System.out::println);
                } catch (FileNotFoundBDConfigEX fileNotFoundBDConfigEX) {
                    fileNotFoundBDConfigEX.printStackTrace();
                } catch (ModelNotFoundEX modelNotFoundEX) {
                    modelNotFoundEX.printStackTrace();
                }
                break;
            }
            case 6: {
                System.out.println();
                try {
                    new VisitSrvc().getAllVisitsID().forEach(System.out::println);
                } catch (FileNotFoundBDConfigEX fileNotFoundBDConfigEX) {
                    fileNotFoundBDConfigEX.printStackTrace();
                } catch (ModelNotFoundEX modelNotFoundEX) {
                    modelNotFoundEX.printStackTrace();
                }
                break;
            }
            case 7: {
                System.out.println();
                try {
                    new WorkerSrvc().getAllPassports().forEach(System.out::println);
                } catch (FileNotFoundBDConfigEX fileNotFoundBDConfigEX) {
                    fileNotFoundBDConfigEX.printStackTrace();
                } catch (ModelNotFoundEX modelNotFoundEX) {
                    modelNotFoundEX.printStackTrace();
                }
                break;
            }
            case 8: {
                System.out.println();
                try {
                    new SpecializationSrvc().getAllSpecByWorkers().forEach(System.out::println);
                } catch (FileNotFoundBDConfigEX fileNotFoundBDConfigEX) {
                    fileNotFoundBDConfigEX.printStackTrace();
                } catch (ModelNotFoundEX modelNotFoundEX) {
                    modelNotFoundEX.printStackTrace();
                }


                break;
            }
            case 9: {
                System.out.println();
                try {
                    new SpecializationSrvc().getAllSpecializations().forEach(System.out::println);
                } catch (FileNotFoundBDConfigEX fileNotFoundBDConfigEX) {
                    fileNotFoundBDConfigEX.printStackTrace();
                } catch (ModelNotFoundEX modelNotFoundEX) {
                    modelNotFoundEX.printStackTrace();
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
