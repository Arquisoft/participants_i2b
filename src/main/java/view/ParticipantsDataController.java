package view;


import domain.User;
import domain.UserInfo;
import domain.UserInfoAdapter;
import domain.UserLoginData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import services.ParticipantsService;

@RestController
public class ParticipantsDataController {

    private final ParticipantsService part;

    @Autowired
    ParticipantsDataController(ParticipantsService part){
        this.part = part;
    }
    
    //This method process an POST html request once fulfilled the login.html form (clicking in the "Enter" button).
    @RequestMapping(value="/user" ,method = RequestMethod.POST)
    public ResponseEntity<User> userOk(Model model, @RequestBody UserLoginData info){
    	User user = part.getParticipant(info.getLogin(), info.getPassword());
    	if(user == null)
    	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	else
    	    return new ResponseEntity<>(user, HttpStatus.OK);
    }

}