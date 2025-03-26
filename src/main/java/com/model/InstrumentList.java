package com.model;

import java.util.ArrayList;

public class InstrumentList {
    private static InstrumentList instrumentList;
    private ArrayList<Instrument> instruments;

    // Private constructor to prevent instantiation from outside
    private InstrumentList() {
        instruments = new ArrayList<>();
    }

    // Singleton instance getter
    public static InstrumentList getInstance() {
        if (instrumentList == null) {
            instrumentList = new InstrumentList();
        }
        return instrumentList;
    }

    public void addInstument(Instrument Instrument){

    }
    public void removeiInstrument(Instrument Instrument){

    }
    public ArrayList<Instrument> getInstruments(){
            return instruments;
        
    }
}

