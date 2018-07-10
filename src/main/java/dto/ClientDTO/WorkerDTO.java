package dto.ClientDTO;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
public class WorkerDTO {
    private int id;
    private String firstName;
    private String lastName;
    private List<String> denominations;

    @Override
    public String toString() {
        return "#" + id +
                " " + firstName +
                " " + lastName +
                " "+ denominations ;
    }
}

