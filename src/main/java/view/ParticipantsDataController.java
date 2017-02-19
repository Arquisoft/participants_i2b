package view;


import domain.UserInfo;
import domain.UserLoginData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.ParticipantsService;

@RestController
public class ParticipantsDataController {

    private final ParticipantsService part;

    @Autowired
    ParticipantsDataController(ParticipantsService part){
        this.part = part;
    }

    @PostMapping(value = "/user")
    @ResponseBody
    public ResponseEntity<UserInfo> userOkJSON(@RequestBody UserLoginData info){
        UserResponseAction act = new UserResponseAction(part);
        return act.execute(info);
    }

}