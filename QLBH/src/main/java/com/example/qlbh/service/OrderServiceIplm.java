package com.example.qlbh.service;

import com.example.qlbh.model.dto.OrderDTO;
import com.example.qlbh.model.dto.ProductDTO;
import com.example.qlbh.model.entity.*;
import com.example.qlbh.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceIplm implements OrderService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private WalletRepository walletRepository;

    @Transactional

    @Override
    public Order createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        ModelMapper mapper = new ModelMapper();

        Customer customer = customerRepository.findByPhone(orderDTO.getCustomer().getPhone());
        if (customer != null) {
            order.setCustomer(customer);
        } else {
            Wallet wallet = new Wallet();
            wallet.setAccount_num(orderDTO.getCustomer().getWallet().getAccount_num());
            wallet.setBalance(orderDTO.getCustomer().getWallet().getBalance());
            walletRepository.save(wallet);
            Customer customerNew = mapper.map(orderDTO.getCustomer(), Customer.class);
            customerNew.setWallet(wallet);
            customerRepository.save(customerNew);
            order.setCustomer(customerNew);
        }

        Shop shop = shopRepository.findById(orderDTO.getShop().getId()).orElse(null);
        order.setShop(shop);

        order.setStatus("Waiting processing...");
        Date currentDate = new Date(System.currentTimeMillis());
        System.out.println("Current: " + currentDate);
        order.setTimeOrder(currentDate);
        order.setTotal(totalPrice(orderDTO.getProducts()));
        orderRepository.save(order);

        Set<Product> products = orderDTO.getProducts().stream().map(productDTO -> mapper.map(productDTO, Product.class)).collect(Collectors.toSet());
        for (Product p : products) {
            p.setOrder(order);
        }
        productRepository.saveAll(products);

        return order;
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public String updateStatus(int id, OrderDTO orderDTO) {
        Order order = orderRepository.findById(id).orElse(null);
        Customer customer = order.getCustomer();
        order.setStatus("successfully");
        double shipPrice = totalPrice(orderDTO.getProducts());
        double balance = customer.getWallet().getBalance();
        if (balance > shipPrice) {
            customer.getWallet().setBalance(balance - shipPrice);
            order.setCustomer(customer);
        } else {
            double chargeMoney = 100000;
            customer.getWallet().setBalance(chargeMoney + balance - shipPrice);
            order.setCustomer(customer);
        }
        Shop shop = order.getShop();
        shop.getWallet().setBalance(shop.getWallet().getBalance() + shipPrice);
        order.setShop(shop);
        orderRepository.save(order);
        return "Đã thanh toán ";
    }

    private double totalPrice(Set<ProductDTO> products) {
        return products.stream().mapToDouble(ProductDTO::getPrice).sum();
    }
}
