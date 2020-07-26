package namesurfer;

import java.util.LinkedHashMap;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a list of user inputs that are available in the database. NameSurferEntry contains
 * names and a list giving the popularity of that names for each decade stretching back to 1900.
 */
public class NameSurferEntry implements NameSurferConstants {

    private final NameSurferModel model;
    private final LinkedHashMap<String, int[]> entryList;

    protected NameSurferEntry() {
        model = new NameSurferModel(NAMES_DATA_FILE);
        entryList = new LinkedHashMap<>();
    }

    protected void findEntry(String name) {
        String newName = firstLetterUpperCase(name);
        if (model.dataBase.containsKey(newName)) {
            entryList.put(newName, model.dataBase.get(newName));
        }
    }

    protected String firstLetterUpperCase(String name) {
        if (!name.isEmpty()) {
            String newName = name.toLowerCase();
            return newName.substring(0, 1).toUpperCase() + newName.substring(1);
        }
        return null;
    }

    protected LinkedHashMap<String, int[]> getEntryList() {
        return entryList;
    }

    protected void clear() {
        entryList.clear();
    }
}


