package view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

}
