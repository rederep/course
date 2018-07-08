package model;

import lombok.*;



import java.util.List;

@Builder(toBuilder = true)
@Setter
@Getter
@ToString(callSuper = true)

public class Worker extends Human {
    private double salary;
    private Passport passport;
    @Singular
    private List<SpecByWorker> specByWorkerLists;

    public Worker() {
    }

    public Worker(int id, String firstName, String lastName, String address, String telephone, double salary, Passport passport) {
        super(id, firstName, lastName, address, telephone);
        this.salary = salary;
        this.passport = passport;
    }

    public Worker(double salary, Passport passport, List<SpecByWorker> specByWorkerLists) {
        this.salary = salary;
        this.passport = passport;
        this.specByWorkerLists = specByWorkerLists;
    }
}
