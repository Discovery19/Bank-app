package org.project.bankingapp.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.bankingapp.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {
    private final UserRepository userRepository;

    public Integer getBalance(String login){
        var user =userRepository.findByLogin(login);
        if (user.isPresent()) {

            log.info("User \"{}\" balance is {}", userRepository.findByLogin(login).get().getLogin(), userRepository.findByLogin(login).get().getBalance());
            return userRepository.findByLogin(login).get().getBalance();
        }
        return null;
    }

    public boolean transferMoney(String current, String to, Integer amount) {
        var currentUser = userRepository.findByLogin(current);
        var toUser = userRepository.findByLogin(to);
        if (currentUser.isPresent() && toUser.isPresent()) {
            var cU = currentUser.get();
            var tU = toUser.get();
            if (cU.getBalance() - amount > 0) {
                log.info("User \"{}\" was sent {}$ to the user \"{}\"", currentUser, amount, to);
                cU.setBalance(cU.getBalance() - amount);
                tU.setBalance(tU.getBalance() + amount);
                return true;
            }
            else {
                log.error("User \"{}\" have no money", currentUser);
                //exception
            }
        }
        log.error("User to \"{}\" is not present", to);
        //exc no user
        return false;
    }
}
