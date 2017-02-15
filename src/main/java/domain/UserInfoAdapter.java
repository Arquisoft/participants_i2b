package domain;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Nicolás on 15/02/2017.
 */
public class UserInfoAdapter {

    private User user;

    public UserInfoAdapter(User user) {
        this.user = user;
    }

    public UserInfo userToInfo(){
        Calendar cal = Calendar.getInstance();
        int now = cal.get(Calendar.YEAR);
        Date date = user.getDateOfBirth();
        LocalDate localDate = date.toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate();
        cal.add(Calendar.YEAR, -localDate.getYear());
        int then = cal.get(Calendar.YEAR);
        return new UserInfo(user.getFirstName(), user.getLastName(),
                now - then, user.getNif(), user.getEmail());
    }
}
