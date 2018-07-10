package dto.ClientDTO;

import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Builder
public class SubscriptionDTO {
    private String type;
    private int numberVisit;
    private int numberVisitsLeft;
    private LocalDate dateBegin;
    private LocalDate dateEnd;
    private String note;

    @Override
    public String toString() {
        return  "\'" + type + '\'' +
                ", \tnumberVisit=" + numberVisit +
                ", \tnumberVisitsLeft=" + numberVisitsLeft +
                ", \tdateBegin=" + dateBegin +
                ", \tdateEnd=" + dateEnd +
                ", \tnote='" + note + "\';";
    }
}
