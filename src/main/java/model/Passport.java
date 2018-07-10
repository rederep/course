package model;


import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Passport {
    private int id;
    private int workerID;
    private String info;


}
