package com.digitkauconsulting.gatitoliniersbot.model;

import com.digitkauconsulting.gatitoliniersbot.service.eventManagement.EventRunnable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Event {



    @Getter @Setter
    private Integer messageId;

    @Getter @Setter
    private Long chatId;

    @Getter @Setter
    private EventRunnable runnable;

    @Getter @Setter
    public String symbolName;

    @Getter @Setter
    public String symbolValue;
}
