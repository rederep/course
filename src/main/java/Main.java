import dao.AdministratorDAO;
import dao.impl.AdministratorDAOImpl;
import dao.impl.factory.ConnectFactory;
import dao.impl.factory.CreateTableFactory;
import model.Administrator;

import java.io.IOException;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws Exception {
       /* Client c1 = new Client(LocalDate.now(), new ArrayList<>(), new ArrayList<>());
        System.out.println(c1);

        Worker w1 = new Worker(2500.0, new Passport(), new ArrayList<>());
        System.out.println(w1);

        System.out.println();

        Client c2 = Client.builder()
                .date(LocalDate.now())
                .subscriptionLists(new ArrayList<>())
                .workerLists(new ArrayList<>())
                .build();
        System.out.println(c2);*/

//        List<Specialization> specList = new ArrayList<>();
//  //      specList.add();
//        Worker w2 = Worker.builder()
//                .salary(2500)
//                .passport(new Passport("MV 78-32-15-15"))
//                .specializationsList(new ArrayList<>())
//                .build();
//        System.out.println(w2);

        AdministratorDAO adm = new AdministratorDAOImpl();
        adm.createPassAdmin(new Administrator("ololol"));
        System.out.println(adm.checkAdminPass(new Administrator("ololol")));

       // CreateTableFactory crt = new CreateTableFactory();
       // crt.createAllTableIfNotExists();
       // crt.dropAllTables();


    }

}
