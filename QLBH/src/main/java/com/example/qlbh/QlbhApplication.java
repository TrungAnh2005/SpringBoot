package com.example.qlbh;

import com.example.qlbh.model.entity.Customer;
import com.example.qlbh.model.entity.Shop;
import com.example.qlbh.model.entity.Wallet;
import com.example.qlbh.repository.CustomerRepository;
import com.example.qlbh.repository.ShopRepository;
import com.example.qlbh.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class QlbhApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(QlbhApplication.class, args);
    }

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ShopRepository shopRepository;

    @Override
    public void run(String... args) throws Exception {
//        Wallet walletCus = new Wallet();
//        walletCus.setAccount_num("N123");
//        walletCus.setBalance(75000);
//        Wallet walletShop = new Wallet();
//        walletShop.setAccount_num("S123");
//        walletShop.setBalance(10000);
//        walletRepository.saveAll(Arrays.asList(walletCus, walletShop));
//
//        Customer customer = new Customer();
//        customer.setName("Nam");
//        customer.setAddress("Hoàng Quốc Việt");
//        customer.setPhone("0777666888");
//        customer.setWallet(walletCus);
//        customerRepository.save(customer);
//
//        Shop shop = new Shop();
//        shop.setName("Yody");
//        shop.setPhone("0666888999");
//        shop.setWallet(walletShop);
//        shopRepository.save(shop);
    }
}
