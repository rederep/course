package model;

import lombok.*;



import java.util.List;

@Builder(toBuilder = true)
@AllArgsConstructor
@Setter
@Getter
@ToString(callSuper = true)

public class Worker extends Human {
    private double salary;
    private Passport passport;
    @Singular
    private List<Specialization> specializationsLists;


}
