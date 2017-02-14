package view;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import domain.User;

@RestController
public class ParticipantsDataController {
    
    //This method process an POST html request once fulfilled the login.html form (clicking in the "Enter" button).
    @RequestMapping(value="/user" ,method = RequestMethod.POST)
    public User userOk(Model model, @RequestParam(value="name") String name,
    		@RequestParam(value="password") String password){
        User user = new User(name, "holaApellidos", "hola@hola", password);
    	return user;
    }

}