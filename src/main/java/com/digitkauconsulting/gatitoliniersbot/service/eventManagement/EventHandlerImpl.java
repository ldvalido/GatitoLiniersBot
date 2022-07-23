package com.digitkauconsulting.gatitoliniersbot.service.eventManagement;

import com.digitkauconsulting.gatitoliniersbot.model.Event;
import com.digitkauconsulting.gatitoliniersbot.service.TelegramService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class EventHandlerImpl implements EventHandler {

    static Logger log = LoggerFactory.getLogger(EventHandlerImpl.class);

     List<Event> events = new ArrayList();
    TelegramService tgSrv;

    public EventHandlerImpl(TelegramService tgSrv){
        this.tgSrv = tgSrv;
    }
    @Override
    public void addEvent(Event e) {

        events.add(e);

        createSchedule(e);
    }

    private void createSchedule(Event e){
        TimerTask t = new TimerTask(){
            @Override
            public void run() {
                if (e.getRunnable().publish(e.getSymbolName(), e.getSymbolValue())) {
                    //TODO: Tech Debt. Decouple telegram from this class
                    tgSrv.postMesage(e.getChatId().toString(), e.getSymbolName() + " alert triggered");
                }
            }
        };
        Timer timer = new Timer("");
        timer.scheduleAtFixedRate(t, 0, 1 * 1000L);
    }
}
