/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.h_da.fbi.rfid.middleware;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author istsaneue
 */
public class LogRFIDManagement {

    private int defaultRefreshTimer;
    private LogRFIDManagement logRFIDManagement;
    private Map<String, String> personRoom;

    public LogRFIDManagement() {
        this.personRoom = new HashMap<String, String>();
        this.defaultRefreshTimer = 5;
        this.logRFIDManagement = this;
    }

    private void refresh() {
        
    }

    public void convertForMap(String read) {
        String cardId, antenna;
        if (read.length() > 27) {
            cardId = read.substring(6, 21);
            antenna = read.substring(25, 27);
            addToMap(cardId, antenna);
        }
    }

    private void addToMap(String cardId, String antenna) {
        if (!this.personRoom.containsKey(cardId)) {
            this.personRoom.put(cardId, antenna);
        }
    }

    /**
     * @return the defaultRefreshTimer
     */
    public int getDefaultRefreshTimer() {
        return defaultRefreshTimer;
    }

    /**
     * @param defaultRefreshTimer the defaultRefreshTimer to set
     */
    public void setDefaultRefreshTimer(int defaultRefreshTimer) {
        this.defaultRefreshTimer = defaultRefreshTimer;
    }

    /**
     * @return the personRoom
     */
    public Map<String, String> getPersonRoom() {
        return personRoom;
    }

    /**
     * @param personRoom the personRoom to set
     */
    public void setPersonRoom(Map<String, String> personRoom) {
        this.personRoom = personRoom;
    }

    /**
     * @return the logRFIDManagement
     */
    public LogRFIDManagement getLogRFIDManagement() {
        return logRFIDManagement;
    }

}
