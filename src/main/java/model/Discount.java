package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Discount {
    private int id;
    private String title;
    private String note;
}
