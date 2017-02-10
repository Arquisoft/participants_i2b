package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

	 @RequestMapping("/")
	    public String landing2(Model model) {
	    	model.addAttribute("nombre", "Amigo");
	        return "login";
	    }
}
