package view;

import domain.User;
import domain.UserInfo;
import domain.UserInfoAdapter;
import domain.UserLoginData;
import org.springframework.web.bind.annotation.ModelAttribute;
import services.ParticipantsService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


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
    @PostMapping(value = "/userForm")
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


