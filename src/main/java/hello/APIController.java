package hello;


import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.UsersRepository;

@RestController
public class APIController {

    @Autowired
    private UsersRepository repository;

    @RequestMapping("/user")
    public User participant() {
        return repository.findAll().get(0);
    }

}