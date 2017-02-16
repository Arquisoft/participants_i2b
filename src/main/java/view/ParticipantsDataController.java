package view;


import domain.User;
import domain.UserInfo;
import domain.UserInfoAdapter;
import domain.UserLoginData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @ResponseBody
    public ResponseEntity<UserInfo> userOk(@RequestBody UserLoginData info){
    	User user = part.getParticipant(info.getLogin(), info.getPassword());
    	UserInfoAdapter data = new UserInfoAdapter(user);
    	if(user == null)
    	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	else
    	    return new ResponseEntity<>(data.userToInfo(), HttpStatus.OK);
    }

}