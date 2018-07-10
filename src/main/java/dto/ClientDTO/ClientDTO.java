package dto.ClientDTO;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String telephone;

    private List<WorkerDTO> workerDTOList = new ArrayList<>();
    private List<SubscriptionDTO> subsList = new ArrayList<>();


    public static ClientDTO toDTO(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.id = client.getId();
        clientDTO.firstName = client.getFirstName();
        clientDTO.lastName = client.getLastName();
        clientDTO.telephone = client.getTelephone();

        if (client.getSubscriptionLists() != null) {
            for (Subscription subs : client.getSubscriptionLists()) {
                SubscriptionDTO subscDTO = SubscriptionDTO.builder()
                        .type(subs.getSubsType().getTitle())
                        .numberVisit(subs.getSubsType().getNumber_visits())
                        .numberVisitsLeft(subs.getNumberVisitsLeft())
                        .dateBegin(subs.getDateBegin())
                        .dateEnd(subs.getDateEnd())
                        .note(subs.getSubsType().getNote()).build();
                clientDTO.subsList.add(subscDTO);
            }
        }

        if (client.getWorkerLists() != null) {
            for (Worker worker : client.getWorkerLists()) {
                WorkerDTO workerDTO = WorkerDTO.builder()
                        .id(worker.getId())
                        .firstName(worker.getFirstName())
                        .lastName(worker.getLastName())
                        .denominations(worker.getSpecByWorkerLists().stream()
                                .map(specByWorker -> specByWorker.getSpecialization().getDenomination())
                                .collect(Collectors.toList()))
                        .build();

                clientDTO.workerDTOList.add(workerDTO);
            }
        }
        return clientDTO;
    }



    @Override
    public String toString() {
        return  "#" + id +
                " " + firstName +
                " " + lastName +
                ", \ttelephone: " + telephone +
                "\nTrainersList: " + workerDTOList +
                "\nSubsList: " + subsList +
                "\n ----------------------------------------------------------------------------------------------------";
    }



}

