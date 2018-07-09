
import controller.MainCntrl;
import dao.AdministratorDAO;
import dao.impl.*;
import dao.impl.factory.DAOImplFactory;
import service.*;
import model.*;



public class Main {
    public static void main(String[] args) throws Exception {
        //    Client c1 = new Client(LocalDate.now(), new ArrayList<>(), new ArrayList<>());
        //     System.out.println(c1);
        //  Client.builder().

//        Worker w1 = new Worker(2500.0, new Passport(), new ArrayList<>());
//        System.out.println(w1);
//
//        System.out.println();


//        List<Specialization> specList = new ArrayList<>();
//  //      specList.add();
//        Worker w2 = Worker.builder()
//                .salary(2500)
//                .passport(new Passport("MV 78-32-15-15"))
//                .specializationsList(new ArrayList<>())
//                .build();
//        System.out.println(w2);


        AdministratorSrv adm = new AdministratorSrv();
        //adm.createAllTables();
        // adm.insertAllTables();


        //adm.deleteAllTables();


        // CreateTableFactory crt = new CreateTableFactory();
        // crt.createAllTableIfNotExists();
        // crt.dropAllTables();


        ClientDAOImpl clientDAO = new ClientDAOImpl();

        //     clientDAO.addClient(new Client(0,"Pervok", "ololol", "Street 22", "338-44-11", LocalDate.now()));

        //    System.out.println(clientDAO.getAllClients());

//           SpecializationDAOImpl sp = new SpecializationDAOImpl();
//      //  sp.addSpecByWorker(new SpecByWorker(1,2, LocalDate.now(),"NORM",Specialization.builder().id(1).build()));
//       System.out.println(sp.getAllSpecByWorkers());
//        System.out.println(sp.getSpecByWorkers(2));


        //     WorkerDAOImpl worker = new WorkerDAOImpl();
        // worker.addWorker(new Worker(0,"Roman", "Putin", "Street 432", "338-44-11", 525.0, new Passport(0,"test")));
        //  worker.deleteWorker(6);
        //  System.out.println(worker.getAllWorkers());
        // System.out.println(worker.getWorker(8));

        SubscriptionDAOImpl subs = new SubscriptionDAOImpl();
        //  System.out.println(subs.getAllDiscounts());
        //  subs.addSubscription(Subscription.builder().subsType(SubsType.builder().id(3).build()).price(500).dateBegin(LocalDate.now()).dateEnd(LocalDate.now().plusMonths(3)).build());
        //  System.out.println(subs.getAllSubscriptions());
        //    subs.addSubsType(new SubsType(1, "Lite subs", 8,0,"Homeless subscription" ));

        //  System.out.println(subs.getAllSubsType());


        VisitDAOImpl v = new VisitDAOImpl();
        //  v.addVisit(1,2,1);
        //      System.out.println(v.getAllVisitsID());

        //   ClientDAOImpl cl = new ClientDAOImpl();
        // cl.deleteClient(2);

        while (true){MainCntrl.doWork();}
    }
}
