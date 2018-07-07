package model;



import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder(toBuilder = true)
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Client extends Human {
    private LocalDate date;
    @Singular
    private List<Worker> workerLists;
    @Singular
    private List<Subscription>  subscriptionLists;



}


