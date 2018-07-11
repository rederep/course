package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class SubsType {
    private int id;
    private String title;
    private int number_visits;
    private int number_days;
    private String note;

    public static Builder builder() {
        return new SubsType().new Builder();
    }

    public Builder build() {
        return this.new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder id(int id) {
            SubsType.this.id = id;
            return this;
        }

        public Builder title(String title) {
            SubsType.this.title = title;
            return this;
        }

        public Builder number_visits(int number_visits) {
            SubsType.this.number_visits = number_visits;
            return this;
        }

        public Builder number_days(int number_days) {
            SubsType.this.number_days = number_days;
            return this;
        }

        public Builder note(String note) {
            SubsType.this.note = note;
            return this;
        }

        public SubsType build(){
            return SubsType.this;
        }

    }
}


