package com.example.ql;

import com.example.ql.model.AccountModel;
import com.example.ql.model.WalletModel;
import com.example.ql.repository.AccountRepository;
import com.example.ql.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QlApplication implements CommandLineRunner {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private WalletRepository walletRepository;


    public static void main(String[] args) {
        SpringApplication.run(QlApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        WalletModel walletModel = new WalletModel();
//        walletModel.setBalance(180000);
//        walletRepository.save(walletModel);
//        AccountModel accountModel = new AccountModel();
//        accountModel.setUsername("Nam");
//        accountModel.setPassword("Nam1234");
//        accountModel.setWalletModel(walletModel);
//        accountRepository.save(accountModel);

    }
}
