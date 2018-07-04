package model;

import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public abstract class Human {
    private String firstName;
    private String lastName;
    private String address;
    private String telephone;

}
