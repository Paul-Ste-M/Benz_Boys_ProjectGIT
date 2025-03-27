package com.model;

import java.util.ArrayList;

/**
 * Singleton class that holds a global list of {@link Instrument} objects.
 * This class ensures a single shared list of all instrument definitions used
 * throughout the application. It provides functionality for adding instruments,
 * retrieving the list, and saving it to JSON using {@link DataWriter}.
 */
public class InstrumentList {

    // Static reference to the singleton instance
    private static InstrumentList instrumentList;

    // The list of instruments maintained in memory
    private ArrayList<Instrument> instruments;

    /**
     * Private constructor to enforce singleton pattern.
     * Initializes the instruments list.
     */
    private InstrumentList() {
        instruments = new ArrayList<>();
    }

    /**
     * Returns the singleton instance of InstrumentList.
     * If no instance exists yet, one is created.
     *
     * @return the global InstrumentList instance
     */
    public static InstrumentList getInstance() {
        if (instrumentList == null) {
            instrumentList = new InstrumentList();
        }
        return instrumentList;
    }

    /**
     * Saves the current list of instruments to the instruments JSON file.
     * Delegates the actual file writing to {@link DataWriter#saveInstruments(ArrayList)}.
     *
     * @return true if the file was saved successfully, false otherwise
     */
    public boolean saveInstruments() {
        boolean result = DataWriter.saveInstruments(instruments);
        return result;
    }

    /**
     * Adds a new instrument to the in-memory list.
     *
     * @param instrument the instrument to add
     */
    public void addInstrument(Instrument instrument) {
        instruments.add(instrument);
    }

    /**
     * Removes an instrument from the in-memory list.
     * <p>
     * Currently a placeholder with no implementation.
     * Could be implemented in the future to support deletion.
     *
     * @param instrument the instrument to remove
     */
    public void removeInstrument(Instrument instrument) {
        // TODO: Implement instrument removal logic
    }

    /**
     * Returns the full list of instruments currently loaded in memory.
     *
     * @return an {@code ArrayList} of {@link Instrument} objects
     */
    public ArrayList<Instrument> getInstruments() {
        return instruments;
    }
}

