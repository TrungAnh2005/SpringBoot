package com.example.kt_jpa.service;

import com.example.kt_jpa.model.dto.VoteDTO;
import com.example.kt_jpa.model.entities.Customer;
import com.example.kt_jpa.model.entities.Order;
import com.example.kt_jpa.model.entities.Shiper;
import com.example.kt_jpa.model.entities.Vote;
import com.example.kt_jpa.repository.CustomerRepository;
import com.example.kt_jpa.repository.OrderRepository;
import com.example.kt_jpa.repository.ShipperRepository;
import com.example.kt_jpa.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceIplm implements VoteService {
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private ShipperRepository shipperRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public String insertVote(VoteDTO voteDTO) {
        Vote vote = new Vote();
        vote.setRate(voteDTO.getRate());
        vote.setMessage(voteDTO.getMessage());
        Order order = orderRepository.findById(voteDTO.getOrder_id()).get();
        vote.setOrder(order);
        Shiper shiper = shipperRepository.findById(voteDTO.getShipper_id()).get();
        vote.setShiper(shiper);
        Customer customer = customerRepository.findById(voteDTO.getOrder_id()).get();
        if (vote.getRate() < 3) {
            double punish = order.getPrice() * 0.05;
            shiper.getWallet().setBalance(shiper.getWallet().getBalance() - punish);
            shipperRepository.save(shiper);
        }
        vote.setCustomer(customer);
        voteRepository.save(vote);
        return "đơn hàng đã được vote ";
    }
}
