package model;


import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder(toBuilder = true)
@RequiredArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)

public class Client extends Human {
    private LocalDate date;
    List<Worker> workerList;
    List<Subscription>  subscriptionList;
}


