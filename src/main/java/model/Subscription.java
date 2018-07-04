package model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@ToString

public class Subscription {
    private String title;
    private double price;
    private int number_visits;
    private int number_days;
    private LocalDate dateBegin;
    private LocalDate dateEnd;
    private Discount discount;

}
