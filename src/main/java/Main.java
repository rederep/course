import dao.DBVar;
import dao.impl.factory.CreateTableFactory;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
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


        CreateTableFactory crt = new  CreateTableFactory();
        try {
            try {
                crt.createAllTableIfNotExists();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            System.out.println("All Table Create Successful");
        }

//        try {
//            crt.dropAllTables();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }

}
