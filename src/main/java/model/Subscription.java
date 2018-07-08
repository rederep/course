package model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class Subscription {
    private int id;
    private SubsType subsType;
    private double price;
    private int numberVisitsLeft;
    private LocalDate dateBegin;
    private LocalDate dateEnd;
    private Discount discount;
}
