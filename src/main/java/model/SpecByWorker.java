package model;

import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SpecByWorker {
    private int id;
    private int workerID;
    private LocalDate date;
    private String note;
    Specialization specialization;
}
