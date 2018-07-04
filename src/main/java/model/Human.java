package model;

import lombok.*;

@Builder(toBuilder = true)
@RequiredArgsConstructor
@Getter
@Setter
@ToString


public abstract class Human {
    private String firstName;
    private String lastName;
    private String address;
    private String telephone;
}
