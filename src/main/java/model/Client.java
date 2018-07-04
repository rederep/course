package model;


import jdk.nashorn.internal.objects.annotations.Constructor;
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
    private List<Worker> workers;
    @Singular
    private List<Subscription>  subscriptions;



}


