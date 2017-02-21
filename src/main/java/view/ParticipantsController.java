package view;

import domain.User;
import domain.UserInfo;
import domain.UserInfoAdapter;
import domain.UserLoginData;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import services.ParticipantsService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


/**
 * Created by Nicolás on 08/02/2017.
 */
@Controller
public class ParticipantsController {
	
	private final ParticipantsService part;

    ParticipantsController(ParticipantsService part){
        this.part = part;
    }

    //The first page shown will be login.html.
    @GetMapping(value="/")
    public String getParticipantInfo(Model model) {
        model.addAttribute("userinfo", new UserLoginData());
        return "login";
    }
    
  //This method process an POST html request once fulfilled the login.html form (clicking in the "Enter" button).
  @RequestMapping(value = "/userForm", method = RequestMethod.POST, consumes = {
          MediaType.APPLICATION_JSON_VALUE,
          MediaType.APPLICATION_XML_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
    public String showInfo(Model model, @ModelAttribute UserLoginData data){
    	 User user = part.getParticipant(data.getLogin(), data.getPassword());
        if(user == null){
            return null;
        }
        else {
            UserInfoAdapter adapter = new UserInfoAdapter(user);
            UserInfo info = adapter.userToInfo();
            model.addAttribute("fName", info.getFirstName());
            model.addAttribute("lName", info.getLastName());
            model.addAttribute("age", info.getAge());
            model.addAttribute("email", info.getEmail());
             return "data";
         }
     }
    	
}


