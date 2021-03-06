package service;

import dao.impl.AdministratorDAOImpl;
import dao.GenericObjectT;

import dao.impl.factory.DAOImplFactory;
import exception.BD.*;
import exception.FileNotFoundBDConfigAdm;
import exception.IllegalArgumentEX;
import model.*;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdministratorSrvc {


    private AdministratorDAOImpl admDAO;
    private static final String FILE_ADMIN_KEY = "admin.key";
    private Password password;


    public AdministratorSrvc() {
        admDAO = DAOImplFactory.getAdministratorInstance();
    }

    public void createAllTables() {
        try {
            admDAO.createAllTables();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundBDConfigEX fileNotFoundBDConfigEX) {
            fileNotFoundBDConfigEX.printStackTrace();
        }
    }

    public void deleteAllTables() {
        try {
            admDAO.deleteAllTables();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundBDConfigEX fileNotFoundBDConfigEX) {
            fileNotFoundBDConfigEX.printStackTrace();
        }
    }

    public void insertAllTables() {
        List<GenericObjectT<Discount>> dicounList = new ArrayList<>();
        dicounList.add(new GenericObjectT<>(new Discount(1, "none", "")));
        dicounList.add(new GenericObjectT<>(new Discount(2, "Summer", "-20%")));
        dicounList.add(new GenericObjectT<>(new Discount(3, "Summer-forever", "Every second towel is free of charge")));
        dicounList.add(new GenericObjectT<>(new Discount(1, "Be a strong", "-15%")));
        dicounList.add(new GenericObjectT<>(new Discount(1, "King lower", "-30% for private lessons")));
        dicounList.add(new GenericObjectT<>(new Discount(1, "Money back", "-50% for the next month")));
        dicounList.add(new GenericObjectT<>(new Discount(1, "Last champion", "+25% time for classes training")));

        List<GenericObjectT<Specialization>> specList = new ArrayList<>();
        specList.add(new GenericObjectT<>(new Specialization(1, "Trainer")));
        specList.add(new GenericObjectT<>(new Specialization(2, "Bodybuilder")));
        specList.add(new GenericObjectT<>(new Specialization(3, "Masseur")));
        specList.add(new GenericObjectT<>(new Specialization(4, "Fitness trainer")));
        specList.add(new GenericObjectT<>(new Specialization(5, "Boxer")));
        specList.add(new GenericObjectT<>(new Specialization(6, "Kick boxer")));
        specList.add(new GenericObjectT<>(new Specialization(7, "Judoka")));
        specList.add(new GenericObjectT<>(new Specialization(8, "Cleaner")));
        specList.add(new GenericObjectT<>(new Specialization(9, "Manager")));
        specList.add(new GenericObjectT<>(new Specialization(10, "Accountant")));
        specList.add(new GenericObjectT<>(new Specialization(11, "Director")));
        specList.add(new GenericObjectT<>(new Specialization(12, "Seller")));

        List<GenericObjectT<SubsType>> subsTypeList = new ArrayList<>();
        subsTypeList.add(new GenericObjectT<>(new SubsType(1, "Lite subs", 8, 0, "Homeless subscription")));
        subsTypeList.add(new GenericObjectT<>(new SubsType(1, "Middle subs", 18, 0, "")));
        subsTypeList.add(new GenericObjectT<>(new SubsType(1, "Pro subs", 0, 30, "")));
        subsTypeList.add(new GenericObjectT<>(new SubsType(1, "Unlim subs", 0, 90, "")));
        subsTypeList.add(new GenericObjectT<>(new SubsType(1, "King subs", 0, 180, "")));
        subsTypeList.add(new GenericObjectT<>(new SubsType(1, "Master subs", 10, 10, "")));
        subsTypeList.add(new GenericObjectT<>(new SubsType(1, "Student subs", 10, 0, "")));

        List<GenericObjectT<Client>> clientList = new ArrayList<>();
        clientList.add(new GenericObjectT<>(new Client(0, "Vlad", "Magadanov", "Ukraine Street 12", "752-52-12", LocalDate.of(2015, 1, 1))));
        clientList.add(new GenericObjectT<>(new Client(0, "Roman", "Magadanov", "USA Street 10", "336-52-18", LocalDate.of(2016, 10, 11))));
        clientList.add(new GenericObjectT<>(new Client(0, "Givi", "Gavrilov", "BMG", "336-88-21", LocalDate.of(2016, 10, 16))));
        clientList.add(new GenericObjectT<>(new Client(0, "Olya", "Polotencev", "BMG", "057-775-70-10", LocalDate.of(2017, 12, 1))));
        clientList.add(new GenericObjectT<>(new Client(0, "Petya", "Petrov", "Street 23, b.12", "895-33-15", LocalDate.of(2018, 2, 18))));
        clientList.add(new GenericObjectT<>(new Client(0, "Vlad", "Grutkovskiy", "Ukraine", "758-25-411", LocalDate.now())));
        clientList.add(new GenericObjectT<>(new Client(0, "Vasya", "Markov", "Asrahan", "365-55-15", LocalDate.now())));
        clientList.add(new GenericObjectT<>(new Client(0, "Vassa", "Oblomalov", "Rome str.52", "248-569-44", LocalDate.now())));
        clientList.add(new GenericObjectT<>(new Client(0, "Ronald", "Kirpedonov", "Russian, Mosckov", "652-28-11", LocalDate.now())));
        clientList.add(new GenericObjectT<>(new Client(0, "Petya", "WindowsSuks", "Dergachi, bomg", "093-57-12-223", LocalDate.now())));
        clientList.add(new GenericObjectT<>(new Client(0, "Lora", "Niganova", "Kharkov str", "3093-99-11-222", LocalDate.now())));
        clientList.add(new GenericObjectT<>(new Client(0, "Lida", "Petrovna", "Asrahan", "365-55-15", LocalDate.now())));

        List<GenericObjectT<Worker>> workerList = new ArrayList<>();
        workerList.add(new GenericObjectT<>(new Worker(0, "Petya", "Vasilev", "Ukraine", "063-358-55-18", 6500.2, Passport.builder().info("MB 22-66-16-18").build())));
        workerList.add(new GenericObjectT<>(new Worker(0, "Vova", "Hernakov", "Ukraine", "057-334-92-87-1", 3600, Passport.builder().info("NF 82-72-39-51").build())));
        workerList.add(new GenericObjectT<>(new Worker(0, "Petya", "Zaebalov", "USA", "063-358-55-18", 3600, Passport.builder().info("GG 36-79-61-55").build())));
        workerList.add(new GenericObjectT<>(new Worker(0, "Vova", "Lomonov", "Canada", "063-268-61-10", 7200.26, Passport.builder().info("MB 14-17-11-20").build())));
        workerList.add(new GenericObjectT<>(new Worker(0, "Ira", "Vasileva", "Ukraine", "063-88-19-67", 5500.18, Passport.builder().info("MB 66-67-21-00").build())));
        workerList.add(new GenericObjectT<>(new Worker(0, "Vlad", "Bogila", "Ukraine street Del", "095-358-15-77", 25550.69, Passport.builder().info("ST 36-37-01-15").build())));
        workerList.add(new GenericObjectT<>(new Worker(0, "Gosha", "Petrovich", "Ukraine", "063-586-12-21", 4000.51, Passport.builder().info("ST 36-37-01-92").build())));
        workerList.add(new GenericObjectT<>(new Worker(0, "Gosha", "Petrovich", "Ukraine, Kiev", "338-65-15", 6321.12, Passport.builder().info("ST 36-37-01-13").build())));
        workerList.add(new GenericObjectT<>(new Worker(0, "Gosha", "Petrovich", "Ukraine, Kharkov", "097-562-26-48", 3800, Passport.builder().info("ST 36-37-01-82").build())));
        workerList.add(new GenericObjectT<>(new Worker(0, "Gosha", "Petrovich", "Ukraine, bomg", "098-66-11-231", 4500, Passport.builder().info("ST 36-37-01-11").build())));

        List<GenericObjectT<Subscription>> subsList = new ArrayList<>();

        subsList.add(new GenericObjectT<>(Subscription.builder().dateBegin(LocalDate.of(2018, 1, 1)).dateEnd(LocalDate.of(2018, 7, 1)).price(4000)
                .discount(Discount.builder().id(5).build())
                .subsType(SubsType.builder().id(1).build()).build()));
        subsList.add(new GenericObjectT<>(Subscription.builder().dateBegin(LocalDate.of(2018, 2, 6)).dateEnd(LocalDate.of(2018, 9, 1)).price(6500)
                .discount(Discount.builder().id(1).build())
                .subsType(SubsType.builder().id(3).build()).build()));
        subsList.add(new GenericObjectT<>(Subscription.builder().numberVisitsLeft(3).price(2100)
                .discount(Discount.builder().id(6).build())
                .subsType(SubsType.builder().id(4).build()).build()));
        subsList.add(new GenericObjectT<>(Subscription.builder().dateBegin(LocalDate.of(2018, 1, 1)).dateEnd(LocalDate.of(2018, 7, 1)).price(2700)
                .discount(Discount.builder().id(7).build())
                .subsType(SubsType.builder().id(7).build()).build()));
        subsList.add(new GenericObjectT<>(Subscription.builder().numberVisitsLeft(15).price(2000)
                .discount(Discount.builder().id(1).build())
                .subsType(SubsType.builder().id(2).build()).build()));
        subsList.add(new GenericObjectT<>(Subscription.builder().dateBegin(LocalDate.of(2018, 3, 1)).dateEnd(LocalDate.of(2018, 6, 1)).price(1600).numberVisitsLeft(5)
                .discount(Discount.builder().id(4).build())
                .subsType(SubsType.builder().id(6).build()).build()));
        subsList.add(new GenericObjectT<>(Subscription.builder().dateBegin(LocalDate.of(2018, 4, 1)).dateEnd(LocalDate.of(2018, 7, 13)).price(2500).numberVisitsLeft(5)
                .discount(Discount.builder().id(4).build())
                .subsType(SubsType.builder().id(6).build()).build()));
        subsList.add(new GenericObjectT<>(Subscription.builder().dateBegin(LocalDate.of(2018, 1, 1)).dateEnd(LocalDate.of(2018, 6, 18)).price(1200).numberVisitsLeft(5)
                .discount(Discount.builder().id(5).build())
                .subsType(SubsType.builder().id(7).build()).build()));
        subsList.add(new GenericObjectT<>(Subscription.builder().dateBegin(LocalDate.of(2018, 1, 1)).dateEnd(LocalDate.of(2018, 7, 21)).price(3600).numberVisitsLeft(5)
                .discount(Discount.builder().id(4).build())
                .subsType(SubsType.builder().id(4).build()).build()));
        subsList.add(new GenericObjectT<>(Subscription.builder().dateBegin(LocalDate.of(2018, 1, 6)).dateEnd(LocalDate.of(2018, 1, 1)).price(3600).numberVisitsLeft(5)
                .discount(Discount.builder().id(1).build())
                .subsType(SubsType.builder().id(2).build()).build()));
        subsList.add(new GenericObjectT<>(Subscription.builder().dateBegin(LocalDate.of(2018, 1, 6)).dateEnd(LocalDate.of(2018, 1, 1)).price(2600).numberVisitsLeft(5)
                .discount(Discount.builder().id(6).build())
                .subsType(SubsType.builder().id(6).build()).build()));
// subsList.add(new GenericObjectT<>(Subscription.builder().dateBegin(LocalDate.of(2018,1,1)).dateEnd(LocalDate.of(2018,7,1)).price(3600).numberVisitsLeft(5)
//                    .discount(new SubscriptionDAOImpl().getDiscount(4))
//                    .subsType(new SubscriptionDAOImpl().getSubsType(6)).build()));


        List<GenericObjectT<Visit>> visitList = new ArrayList<>();
        visitList.add(new GenericObjectT<>(new Visit(0, 2, 1, 1)));
        visitList.add(new GenericObjectT<>(new Visit(0, 2, 3, 5)));
        visitList.add(new GenericObjectT<>(new Visit(0, 2, 5, 6)));
        visitList.add(new GenericObjectT<>(new Visit(0, 3, 4, 1)));
        visitList.add(new GenericObjectT<>(new Visit(0, 4, 2, 4)));
        visitList.add(new GenericObjectT<>(new Visit(0, 7, 6, 3)));
        visitList.add(new GenericObjectT<>(new Visit(0, 7, 10, 3)));
        visitList.add(new GenericObjectT<>(new Visit(0, 8, 7, 8)));
        visitList.add(new GenericObjectT<>(new Visit(0, 9, 9, 9)));
        visitList.add(new GenericObjectT<>(new Visit(0, 10, 8, 3)));
        visitList.add(new GenericObjectT<>(new Visit(0, 12, 11, 1)));




        List<GenericObjectT<SpecByWorker>> speOfWorktList = new ArrayList<>();
        speOfWorktList.add(new GenericObjectT<>(SpecByWorker.builder().workerID(1).date(LocalDate.of(2016, 6, 10)).note("V acreditaciya")
                .specialization(Specialization.builder().id(1).build()).build()));
        speOfWorktList.add(new GenericObjectT<>(SpecByWorker.builder().workerID(1).date(LocalDate.of(2016, 7, 16)).note("Cooll worker")
                .specialization(Specialization.builder().id(2).build()).build()));
        speOfWorktList.add(new GenericObjectT<>(SpecByWorker.builder().workerID(2).date(LocalDate.of(2017, 8, 6)).note("Stuped")
                .specialization(Specialization.builder().id(5).build()).build()));
        speOfWorktList.add(new GenericObjectT<>(SpecByWorker.builder().workerID(2).date(LocalDate.of(2018, 1, 21)).note("LA-lala")
                .specialization(Specialization.builder().id(6).build()).build()));
        speOfWorktList.add(new GenericObjectT<>(SpecByWorker.builder().workerID(3).date(LocalDate.of(2015, 1, 16))
                .specialization(Specialization.builder().id(3).build()).build()));
        speOfWorktList.add(new GenericObjectT<>(SpecByWorker.builder().workerID(4).date(LocalDate.of(2015, 2, 28))
                .specialization(Specialization.builder().id(7).build()).build()));
        speOfWorktList.add(new GenericObjectT<>(SpecByWorker.builder().workerID(5).date(LocalDate.of(2018, 6, 18))
                .specialization(Specialization.builder().id(1).build()).build()));
        speOfWorktList.add(new GenericObjectT<>(SpecByWorker.builder().workerID(6).date(LocalDate.of(2016, 3, 3))
                .specialization(Specialization.builder().id(9).build()).build()));
        speOfWorktList.add(new GenericObjectT<>(SpecByWorker.builder().workerID(6).date(LocalDate.of(2016, 8, 22)).note("Imbo Percer")
                .specialization(Specialization.builder().id(10).build()).build()));
        speOfWorktList.add(new GenericObjectT<>(SpecByWorker.builder().workerID(1).date(LocalDate.of(2018, 4, 18))
                .specialization(Specialization.builder().id(8).build()).build()));
        speOfWorktList.add(new GenericObjectT<>(SpecByWorker.builder().workerID(7).date(LocalDate.of(2018, 4, 18)).note("I am the Power STOTA")
                .specialization(Specialization.builder().id(4).build()).build()));
        speOfWorktList.add(new GenericObjectT<>(SpecByWorker.builder().workerID(8).date(LocalDate.of(2018, 4, 18)).note("must 1000 UAH!")
                .specialization(Specialization.builder().id(12).build()).build()));
        speOfWorktList.add(new GenericObjectT<>(SpecByWorker.builder().workerID(9).date(LocalDate.of(2018, 4, 18)).note("WTF?")
                .specialization(Specialization.builder().id(11).build()).build()));
        speOfWorktList.add(new GenericObjectT<>(SpecByWorker.builder().workerID(10).date(LocalDate.of(2018, 4, 18))
                .specialization(Specialization.builder().id(1).build()).build()));


        try {
            for (GenericObjectT<Discount> t : dicounList) {
                admDAO.insertAllTables(t);
            }
            for (GenericObjectT<Specialization> t : specList) {
                admDAO.insertAllTables(t);
            }
            for (GenericObjectT<SubsType> t : subsTypeList) {
                admDAO.insertAllTables(t);
            }
            for (GenericObjectT<Client> t : clientList) {
                admDAO.insertAllTables(t);
            }
            for (GenericObjectT<Worker> t : workerList) {
                admDAO.insertAllTables(t);
            }
            for (GenericObjectT<Subscription> t : subsList) {
                admDAO.insertAllTables(t);
            }
            for (GenericObjectT<Visit> t : visitList) {
                admDAO.insertAllTables(t);
            }
            for (GenericObjectT<SpecByWorker> t : speOfWorktList) {
                admDAO.insertAllTables(t);
            }
        } catch (ChoosingSubscriptionEX choosingSubscriptionEX) {
            choosingSubscriptionEX.printStackTrace();
        } catch (ChoosingWorkerEX choosingWorkerEX) {
            choosingWorkerEX.printStackTrace();
        } catch (ChoosingClientEX choosingClientEX) {
            choosingClientEX.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundBDConfigEX fileNotFoundBDConfigEX) {
            fileNotFoundBDConfigEX.printStackTrace();
        } catch (NoPramToInsertBDEX noPramToInsertBDEX) {
            noPramToInsertBDEX.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    private Password getInstancePass() {
        if (password == null) {
            password = new Password();
        }
        return password;
    }

    public void createPassAdmin(Administrator administrator) throws FileNotFoundBDConfigAdm {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(FILE_ADMIN_KEY));
            bw.write(getInstancePass().getSaltedHash(administrator.getPassword()));
            bw.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundBDConfigAdm();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkAdminPass(Administrator administrator) throws  FileNotFoundBDConfigAdm  {
        boolean isPass = false;
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_ADMIN_KEY));
            isPass = Password.check(administrator.getPassword(), br.readLine());
            br.close();
        }catch (IllegalArgumentEX e ){
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            throw new FileNotFoundBDConfigAdm();
        } catch (Exception e) {
             e.printStackTrace();
        }
        return isPass;
    }
}



