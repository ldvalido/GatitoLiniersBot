package com.digitkauconsulting.gatitoliniersbot.service.eventManagement;

import com.digitkauconsulting.gatitoliniersbot.model.Event;

public class EventRunner {
    public EventRunnable evaluationEventCriteria;

    public EventRunner(EventRunnable evaluationEventCriteria){
        this.evaluationEventCriteria=evaluationEventCriteria;
    }
}
