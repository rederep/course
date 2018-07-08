package model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Visit {
    private int id;
    private int clientID;
    private int subscriptionID;
    private int workerID;
}
