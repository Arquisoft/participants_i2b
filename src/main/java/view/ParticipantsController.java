package view;

import domain.User;
import domain.UserInfo;
import domain.UserInfoAdapter;
import domain.UserLoginData;
import org.springframework.web.bind.annotation.*;
import services.ParticipantsService;
import util.JasyptEncryptor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


/**
 * Created by Nicolás on 08/02/2017.
 */
@Controller
public class ParticipantsController {
	
	private final ParticipantsService part;
	
	private User loggedUser;

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
    @RequestMapping(value = "/userForm", method = RequestMethod.POST)
    public String showInfo(Model model, @ModelAttribute UserLoginData data){
        System.out.println(data.getLogin());
        User user = part.getParticipant(data.getLogin(), data.getPassword());
        System.out.println(user);
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
            model.addAttribute("user", user);
            loggedUser=user;
            return "data";
        }
    }
  
    @RequestMapping(value="/passMenu", method = RequestMethod.GET)
    public String showMenu(Model model){
          //Just in case there must be more processing.
          return "changePassword";
    }

    @RequestMapping(value="/userChangePassword",method = RequestMethod.POST)
    public String changePassword(Model model, @RequestParam String password,
                                @RequestParam String newPassword, @RequestParam String newPasswordConfirm){
          JasyptEncryptor encryptor= new JasyptEncryptor();
          if(encryptor.checkPassword(password, loggedUser.getPassword()) &&
                                    newPassword.equals(newPasswordConfirm)){
                  part.updateInfo(loggedUser, newPassword);
                  return "data";
          }
          return "changePassword";
    }
    	
}


