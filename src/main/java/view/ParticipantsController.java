package view;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import domain.UserInfo;

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

    //Controlling a POST access to the page.
    @PostMapping(value="/")
    public ModelAndView letRegister(@RequestBody UserInfo info, HttpServletRequest request){
    	request.setAttribute("Content-Type", "application/json");
        return new ModelAndView("forward:/user");
    }
}
