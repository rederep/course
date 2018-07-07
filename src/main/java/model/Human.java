package model;

import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public abstract class Human {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String telephone;

}
