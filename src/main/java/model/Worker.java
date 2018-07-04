package model;

import lombok.*;



import java.util.List;

@Builder(toBuilder = true)
@RequiredArgsConstructor
@Setter
@Getter
@ToString(callSuper = true)

public class Worker extends Human {
    private double salary;
    private Passport passport;
    private List<Specialization> specializationsList;
}
