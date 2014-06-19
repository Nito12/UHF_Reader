/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.h_da.fbi.rfid.gui;

import de.h_da.fbi.rfid.companysoftware.Person;
import de.h_da.fbi.rfid.companysoftware.PersonalManagement;
import de.h_da.fbi.rfid.middleware.LogRFIDManagement;
import de.h_da.fbi.rfid.uhfreader.SerialReadByEvents;

/**
 *
 * @author Sandro Neuenfeldt
 */
public class GUI {

    public static LogRFIDManagement logRFIDManagement = new LogRFIDManagement();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        PersonalManagement personalManagement = new PersonalManagement();
        
        personalManagement.addEmployee("Sandro", "Neuenfeldt", "E004556315010000", "Security", 1, "Ostend", "7", "64291", "Darmstadt");
        personalManagement.addEmployee("Lukas", "Pfeuffer", "E004E66D15010000", "HumanResources", 2, "Winkelgass", "13", "64291", "Darmstadt");
        personalManagement.addEmployee("Basti", "Seich", "E004CC3915010000", "Secretary", 3, "Bessunger", "3", "64290", "Darmstadt");
        personalManagement.addGuest("Domus", "Glaab", "E004A86D15010000", "Cisco");
        personalManagement.addGuest("Tobi", "Seil", "E004AF1415010000", "h_da");
        
        Person testPerson = personalManagement.findPerson("E004556315010000");
        System.out.println(testPerson.getCardId() + " " + testPerson.getSurname());
        
        Person testPerson2 = personalManagement.findPerson("E004A86D15010000");
        System.out.println(testPerson2.getCardId() + " " + testPerson2.getSurname());
        
//        (new SerialReadByEvents()).connect("COM1");
        
        
        
    }
    
}
