package view;

import domain.UserLoginData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * Created by Nicolás on 08/02/2017.
 */
@Controller
public class ParticipantsController {

    //The first page shown will be login.html.
    @GetMapping(value="/")
    public String getParticipantInfo() {
        return "login";
    }

    @PostMapping(value="/")
    public String redirectFromForm(@RequestBody UserLoginData info){
        return "redirect:/user";
    }

}
