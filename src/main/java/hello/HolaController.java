package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Nicolás on 08/02/2017.
 */
@Controller
public class HolaController {

    //The first page shown will be login.html.
    @GetMapping(value="/")
    public String landing() {
        return "login";
    }

    //Controlling a POST access to the page.
    @PostMapping(value="/")
    public ModelAndView letRegister(@RequestParam(value="name") String name,
                                    @RequestParam(value="password") String password){
        return new ModelAndView("forward:/user");
        //return new ModelAndView(String.format("forward:/user?name=%s&password=%s", name, password));
    }
}
