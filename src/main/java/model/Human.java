package model;

import lombok.*;


@Getter
@Setter
@ToString


public abstract class Human {
    private String firstName;
    private String lastName;
    private String address;
    private String telephone;

}
