package info.usautosearch.dao;

import info.usautosearch.configuration.SearchKeys;
import info.usautosearch.model.Vehicle;

import java.util.List;

public interface VehicleDAO {
    public List<Vehicle> getAll(int from, int count);
    public List<Vehicle> search(String formula, int from, int count);
    public int getCount();
    public Vehicle getByLotNumber(int lotNumber);
    public SearchKeys initSearchKeys();
}
