package com.example.qlbh.service;

import com.example.qlbh.model.dto.FeedbackDTO;
import com.example.qlbh.model.entity.Customer;
import com.example.qlbh.model.entity.Feedback;
import com.example.qlbh.model.entity.Order;
import com.example.qlbh.repository.CustomerRepository;
import com.example.qlbh.repository.FeedbackRepository;
import com.example.qlbh.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeedbackServiceIplm implements FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    OrderRepository orderRepository;

    @Override
    public String insertFeedback(FeedbackDTO feedbackDTO) {
        Feedback feedback = new Feedback();
        feedback.setRate(feedbackDTO.getRate());
        if (feedback.getRate() < 3) {
            feedback.setMessage("Không hài lòng");
        } else {
            feedback.setMessage("chất lượng tốt");
        }
        Order order = orderRepository.findById(feedbackDTO.getOrder_id()).get();
        feedback.setOrder(order);
        Customer customer = customerRepository.findById(feedbackDTO.getCustomer_id()).get();
        feedback.setCustomer(customer);
        feedbackRepository.save(feedback);
        return "Đã feedback";
    }
}
