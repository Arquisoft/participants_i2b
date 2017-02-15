package view;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import services.Services;
import domain.User;
import domain.UserInfo;

@RestController
public class ParticipantsDataController {
    
    //This method process an POST html request once fulfilled the login.html form (clicking in the "Enter" button).
    @RequestMapping(value="/user" ,method = RequestMethod.POST)
    public ResponseEntity<User> userOk(Model model, @RequestBody UserInfo info){
    	/*User user = Services.getParticipantsService()
    			.getParticipant(info.getLogin(), info.getPassword());*/
    	User user = new User("hola", "hola2", "hola@hola", "dsadsas");
    	return new ResponseEntity<User>(user, HttpStatus.OK);
    }

}