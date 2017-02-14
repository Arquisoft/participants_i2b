package services;

import services.impl.PartipantsServiceImpl;

/**
 * Created by Nicolás on 14/02/2017.
 */
public class Services {

    public static ParticipantsService getParticipantsService(){
        return new PartipantsServiceImpl();
    }
}
