package com.example.kt_jpa;

import com.example.kt_jpa.model.entities.Customer;
import com.example.kt_jpa.model.entities.Shiper;
import com.example.kt_jpa.model.entities.Wallet;
import com.example.kt_jpa.repository.CustomerRepository;
import com.example.kt_jpa.repository.OrderRepository;
import com.example.kt_jpa.repository.ShipperRepository;
import com.example.kt_jpa.repository.WalletRepository;
import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class KtJpaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(KtJpaApplication.class, args);
    }

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ShipperRepository shipperRepository;
    @Override
    public void run(String... args) throws Exception {

        //Create shipper's wallet
//        Wallet walletShiper = new Wallet();
//        walletShiper.setAccountNumber("Sh123");
//        walletShiper.setBalance(20000);
        //Create customer's wallet
//        Wallet walletCus = new Wallet();
//        walletCus.setAccountNumber("C123");
//        walletCus.setBalance(30000);
//        walletRepository.saveAll(Arrays.asList(walletCus, walletShiper));
//
//        Shiper shiper = new Shiper();
//        shiper.setName("Ship1");
//        shiper.setPhone("0967221544");
//        shiper.setWallet(walletShiper);
//        shipperRepository.save(shiper);
//
//        Customer customer = new Customer();
//        customer.setName("Cus1");
//        customer.setPhone("0927435266");
//        customer.setWallet(walletCus);
//        customerRepository.save(customer);
    }
}
