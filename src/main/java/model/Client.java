package model;



import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter

@ToString(callSuper = true)
public class Client extends Human {
    private LocalDate date;
    @Singular
    private List<Worker> workerLists;
    @Singular
    private List<Subscription>  subscriptionLists;

    public Client() {
    }

    public Client(int id, String firstName, String lastName, String address, String telephone, LocalDate date) {
        super(id, firstName, lastName, address, telephone);
        this.date = date;
    }

    public Client(LocalDate date, List<Worker> workerLists, List<Subscription> subscriptionLists) {
        this.date = date;
        this.workerLists = workerLists;
        this.subscriptionLists = subscriptionLists;
    }
}


