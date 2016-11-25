package info.usautosearch.configuration;

import java.util.Calendar;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.LinkedHashMap;

public class SearchKeys {
    private Map<String, String> types;
    private Map<String, Set<String>> makes;
    private Map<String, Set<String>> models;
    private int minYear;
    private int maxYear;

    public SearchKeys(){
        types = new LinkedHashMap<String, String>();
        makes = new LinkedHashMap<String, Set<String>>();
        models = new LinkedHashMap<String, Set<String>>();
        minYear = Calendar.getInstance().get(Calendar.YEAR) - 10;
        maxYear = Calendar.getInstance().get(Calendar.YEAR) - 10;
    }

    public void setTypes(Map<String, String> types) {
        this.types = types;
    }
    public Map<String, String> getTypes() {
        LinkedHashMap<String, String> returnSet = new LinkedHashMap<String, String>();
        returnSet.put("All Types", "All Types");
        if(types != null) returnSet.putAll(types);
        return returnSet;
    }

    public void setMakes(Map<String, Set<String>> makes) {
        this.makes = makes;
    }

    public Map<String, Set<String>> getMakes() {
        return makes;
    }

    public Set<String> getMakesByType(String type) {
        LinkedHashSet<String> returnSet = new LinkedHashSet<String>();
        returnSet.add("All Makes");
        Set<String> entry;
        entry = makes.get(type);
        if(entry != null) returnSet.addAll(entry);
        return returnSet;
    }

    public void setModels(Map<String, Set<String>> models) {
        this.models = models;
    }

    public Map<String, Set<String>> getModels() {
        return models;
    }

    public Set<String> getModelsByKey(String key) {
        LinkedHashSet<String> returnSet = new LinkedHashSet<String>();
        returnSet.add("All Models");
        Set<String> entry;
        entry = models.get(key);
        if(entry != null) returnSet.addAll(entry);
        return returnSet;
    }

    public void setMaxYear(int maxYear) {
        this.maxYear = maxYear;
    }

    public int getMaxYear() {
        if(maxYear == 0) maxYear = Calendar.getInstance().get(Calendar.YEAR);
        return maxYear;
    }

    public void setMinYear(int minYear) {
        this.minYear = minYear;
    }

    public int getMinYear() {
        if(minYear == 0) minYear = Calendar.getInstance().get(Calendar.YEAR) - 10;
        return minYear;
    }
}
