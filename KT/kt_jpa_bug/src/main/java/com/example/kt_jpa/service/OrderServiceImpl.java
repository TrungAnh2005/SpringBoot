package com.example.kt_jpa.service;

import com.example.kt_jpa.model.dto.OrderDTO;
import com.example.kt_jpa.model.dto.ProductDTO;
import com.example.kt_jpa.model.entities.*;
import com.example.kt_jpa.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ShipperRepository shipperRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private VoteRepository voteRepository;

    @Transactional
    @Override
    public Order createOrder(OrderDTO dto) {
        Order order = new Order();
        ModelMapper mapper = new ModelMapper();
        Shiper shiper = shipperRepository.findById(dto.getShipperId()).orElse(null);
        if (shiper == null) {
            System.out.println("Not found shipper with id: " + dto.getShipperId());
            return null;
        }

        Customer customer = customerRepository.findByPhone(dto.getCustomer().getPhone());
        if (customer != null) {
            double shipPrice = totalPrice(dto.getProducts());
            double balance = customer.getWallet().getBalance();
            if (shipPrice < balance) {
                customer.getWallet().setBalance(balance - shipPrice);
                order.setCustomer(customer);
            } else {

                double chargeMoney = 50000;
                customer.getWallet().setBalance(balance + chargeMoney - shipPrice);
                order.setCustomer(customer);
            }

        } else {

            Wallet wallet = new Wallet();
            wallet.setAccountNumber(dto.getCustomer().getWallet().getAccountNumber());
            wallet.setBalance(dto.getCustomer().getWallet().getBalance());
            walletRepository.save(wallet);

            Customer customerNew = mapper.map(dto.getCustomer(), Customer.class);
            customerNew.setWallet(wallet);
            customerRepository.save(customerNew);
            order.setCustomer(customerNew);
        }

        order.setAddress(dto.getAddress());
        order.setStatus("Waiting processing...");
        Date currentDate = new Date(System.currentTimeMillis());
        System.out.println("Current: " + currentDate);
        order.setTimeOrder(currentDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE, 7);
        currentDate = calendar.getTime();
        System.out.println("Estimate: " + currentDate);
        order.setEstimateTime(currentDate);

        order.setShiper(shiper);
        order.setPrice(totalPrice(dto.getProducts()));



        Set<Product> products = dto.getProducts().stream().map(productDTO -> mapper.map(productDTO, Product.class)).collect(Collectors.toSet());
        for (Product p : products) {
            p.setOrder(order);
        }

        productRepository.saveAll(products); 
        order.setProducts(products);
        orderRepository.save(order);
        return order;
    }

    @Override
    public String updateOrder(OrderDTO dto, int id) {
        Order order = orderRepository.findById(id).orElse(null);
        order.setStatus("successfully");
        Shiper shiper = order.getShiper();
        shiper.getWallet().setBalance(shiper.getWallet().getBalance() + order.getPrice() * 0.1);
        orderRepository.save(order);
        shipperRepository.save(shiper);
        return "đã giao hàng thành công";
    }

    @Override
    public List<OrderDTO> getAllOrder() {
        ModelMapper mapper = new ModelMapper();
        List<Order> orders = orderRepository.findAll();
        List<OrderDTO> orderDTOS = orders.stream().map(order -> mapper.map(order, OrderDTO.class)).collect(Collectors.toList());
        return orderDTOS;
    }


    private double totalPrice(Set<ProductDTO> products) {

        // Cách 1
        /*
        double weight =0;
        for (Product p: products) {
            weight+= p.getWeight();
        }*/
        // Cách 2: sử dụng stream
        double weight = products.stream().mapToDouble(ProductDTO::getWeight).sum();
        return weight <= 10 ? 15000.0 : weight <= 20 ? 20000.0 : 30000.0;
    }
}
