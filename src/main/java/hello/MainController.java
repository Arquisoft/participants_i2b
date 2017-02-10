package hello;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import domain.User;

@Controller
public class MainController {

	//The first page shown will be login.html.
    @RequestMapping("/")
    public String landing(Model model) {
    	model.addAttribute("nombre", "Amigo");
        return "login";
    }
    
    //This one only works as manual testing.
    @RequestMapping("/hola")
    public String hola(Model model) {
    	model.addAttribute("nombre", "Luis");
        return "saludo";
    }
    
    @RequestMapping("/test")
    public String landing2(Model model) {
    	model.addAttribute("nombre", "Amigo");
        return "login";
    }
    
    //This method process an POST html request once fulfilled the login.html form (clicking in the "Enter" button).
    @RequestMapping(value="/info" ,method = RequestMethod.POST)
    public String userOk(Model model, @RequestParam(value="name") String name,
    		@RequestParam(value="password") String password){
    	model.addAttribute("nombre", name);
    	model.addAttribute("pass", password);
    	return "saludo"; //It should lead to an html file that shows in a user-friendly mode the ifnromation about the user logged.
    }
    
    //This method processes an POST html request when the "Register" button in the login.html form is clicked.
    @RequestMapping(value="/register", method =RequestMethod.POST)
    public String letRegister(){
    	return "register"; //It just shows a simple .html page to be modified.
    }

}