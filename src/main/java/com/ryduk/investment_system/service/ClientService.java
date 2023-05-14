package com.ryduk.investment_system.service;

import com.ryduk.investment_system.PaginationParameters;
import com.ryduk.investment_system.dto.EntityPageDto;
import com.ryduk.investment_system.exception.UserNotFoundException;
import com.ryduk.investment_system.model.Client;
import com.ryduk.investment_system.model.Deposit;
import com.ryduk.investment_system.model.Investment;
import com.ryduk.investment_system.model.Share;
import com.ryduk.investment_system.repo.ClientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    // Repos
    private final ClientRepo clientRepo;
    // Services
    private final InvestmentService investmentService;
    private final ShareService shareService;
    private final DepositService depositService;

    public Investment invest(int clientId, Investment investment) throws UserNotFoundException {
        Client client = getClient(clientId);
        if(client != null) {
            client.getInvestments().add(investment);
            saveClient(client);
            return investment;
        }
        throw new UserNotFoundException("Client is not found");
    }

    public List<Investment> getAllInvestmentByClient(int clientId){
        Client client = getClient(clientId);
        return client.getInvestments();
    }

    public Client saveClient(Client client){
        return clientRepo.save(client);
    }

    public Client getClient(int clientId){
        return clientRepo.findById(clientId)
                .orElse(null);
    }

    public EntityPageDto<Client> getAllClients(PaginationParameters params){
        Sort.Direction direction = Sort.Direction.valueOf(params.getDirection());

        Page<Client> page = clientRepo.findAll(PageRequest.of(params.getPage() - 1, 6).withSort(Sort.by(direction, params.getField())));
        List<Client> clients = page.stream().toList();

        EntityPageDto<Client> clients1 = new EntityPageDto<>(
                page.getTotalElements(), page.getTotalPages(), params.getPage(), 6, clients);
        System.out.println(clients1);
        return clients1;
    }

    public boolean deleteClient(int clientId){
        Client clientInDb = getClient(clientId);
        if(clientInDb == null)
            return false;

        clientRepo.deleteById(clientId);
        return true;
    }
}
