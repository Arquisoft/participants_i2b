package view;

import domain.User;
import domain.UserInfo;
import domain.UserInfoAdapter;
import domain.UserLoginData;
import services.ParticipantsService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


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
    public String showInfo(Model model, @RequestParam(value = "login") String login, @RequestParam(value= "password") String password){
    	 User user = part.getParticipant(login, password);
         UserInfoAdapter data = new UserInfoAdapter(user);
         UserInfo info = data.userToInfo();
         if(user == null){
             return null;
         }
         else
        	 model.addAttribute("fName", info.getFirstName());
     		 model.addAttribute("lName", info.getLastName());
     		 model.addAttribute("age", info.getAge());
     		 model.addAttribute("email", info.getEmail());
             return "data";
     }
    	
}


