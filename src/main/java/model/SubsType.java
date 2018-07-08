package model;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SubsType {
    private int id;
    private String title;
    private int number_visits;
    private int number_days;
    private String note;
   }
