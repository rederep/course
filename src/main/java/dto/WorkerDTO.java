package dto;

import model.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WorkerDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String telephone;
    private String note;
    private List<String> denominations = new ArrayList<>();


    public static WorkerDTO toDTO(Worker worker) {
        WorkerDTO workerDTO = new WorkerDTO();
        workerDTO.id = worker.getId();
        workerDTO.firstName = worker.getFirstName();
        workerDTO.lastName = worker.getLastName();
        workerDTO.address = worker.getAddress();
        workerDTO.telephone = worker.getTelephone();
        if (worker.getSpecByWorkerLists() != null) {
            workerDTO.denominations = worker.getSpecByWorkerLists().stream()
                    .map(specByWorker -> specByWorker.getSpecialization().getDenomination())
                    .collect(Collectors.toList());
        }
        return workerDTO;
    }

    @Override
    public String toString() {
        return
                "#" + id +
                " " + firstName +
                " " + lastName +
                 ", \ttelephone='" + telephone + '\'' +
                ", \taddress='" + address + '\'' +
                ", \tnote='" + note + '\'' +
                ", \tdenominations=" + denominations +
                 "\n --------------------------------------------------------------";
    }
}

