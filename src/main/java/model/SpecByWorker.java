package model;

import lombok.*;

import java.time.LocalDate;

@Builder(toBuilder = true)
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SpecByWorker {
    private int workerID;
    private int id;
    private LocalDate date;
    private String note;
    Specialization specialization;
}
