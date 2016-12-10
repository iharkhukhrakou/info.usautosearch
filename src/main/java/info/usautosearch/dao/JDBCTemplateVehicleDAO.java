package info.usautosearch.dao;

import info.usautosearch.configuration.SearchKeys;
import org.springframework.jdbc.core.JdbcTemplate;

import info.usautosearch.model.Vehicle;

import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.LinkedHashMap;
import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTemplateVehicleDAO implements VehicleDAO {
    private JdbcTemplate jdbcTemplate;
    private int rowCount;

    public JDBCTemplateVehicleDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Vehicle> getAll(int from, int count) {
        String sql = "SELECT * FROM tbl_vehicle LIMIT " + from + "," + count;
        List<Vehicle> getAll = jdbcTemplate.query(sql, new RowMapper<Vehicle>() {

            @Override
            public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
                Vehicle vehicle = new Vehicle();
                vehicle.setID(rs.getInt("id"));
                vehicle.setYardNumber(rs.getInt("yardnumber"));
                vehicle.setYardName(rs.getString("yardname"));
                vehicle.setSaleDate(rs.getDate("saledate"));
                vehicle.setDayOfWeek(rs.getString("dayofweek"));
                vehicle.setSaleTime(rs.getInt("saletime"));
                vehicle.setTimeZone(rs.getString("timezone"));
                vehicle.setItem(rs.getInt("item"));
                vehicle.setLotNumber(rs.getInt("lotnumber"));
                vehicle.setVehicleType(rs.getString("vehicletype"));
                vehicle.setYear(rs.getInt("year"));
                vehicle.setMake(rs.getString("make"));
                vehicle.setModelGroup(rs.getString("modelgroup"));
                vehicle.setModelDetail(rs.getString("modeldetail"));
                vehicle.setBodyStyle(rs.getString("bodystyle"));
                vehicle.setColor(rs.getString("color"));
                vehicle.setDamageDescription(rs.getString("damagedescription"));
                vehicle.setSecondaryDamage(rs.getString("secondarydamage"));
                vehicle.setSaleTitleState(rs.getString("saletitlestate"));
                vehicle.setSaleTitleType(rs.getString("saletitletype"));
                vehicle.setHasKeys(rs.getString("haskeys"));
                vehicle.setLotCond(rs.getString("lotcond"));
                vehicle.setVin(rs.getString("vin"));
                vehicle.setOdometer(rs.getInt("odometer"));
                vehicle.setOdometerBrand(rs.getString("odometerbrand"));
                vehicle.setRetailValue(rs.getDouble("retailvalue"));
                vehicle.setRepairCost(rs.getDouble("repaircost"));
                vehicle.setEngine(rs.getString("engine"));
                vehicle.setDrive(rs.getString("drive"));
                vehicle.setTransmission(rs.getString("transmission"));
                vehicle.setFuelType(rs.getString("fueltype"));
                vehicle.setCylinders(rs.getString("cylinders"));
                vehicle.setRunsDrives(rs.getString("runsdrives"));
                vehicle.setSaleStatus(rs.getString("salestatus"));
                vehicle.setHighBid(rs.getString("highbid"));
                vehicle.setSpecialNote(rs.getString("specialnote"));
                vehicle.setLocationCity(rs.getString("locationcity"));
                vehicle.setLocationState(rs.getString("locationstate"));
                vehicle.setLocationZIP(rs.getString("locationzip"));
                vehicle.setLocationCountry(rs.getString("locationcountry"));
                vehicle.setCurrencyCode(rs.getString("currencycode"));
                vehicle.setImageThumbnail(rs.getString("imagethumbnail"));
                vehicle.setCreateDate(rs.getString("createdate"));
                vehicle.setGridRow(rs.getString("gridrow"));
                vehicle.setMakeAnOfferEligible(rs.getString("makeanoffereligible"));
                vehicle.setBuyItNowPrice(rs.getDouble("buyitnowprice"));
                return vehicle;
            }

        });

        sql = "SELECT COUNT(*) FROM tbl_vehicle";
        rowCount = jdbcTemplate.queryForObject(sql, Integer.class);

        return getAll;
    }

    @Override
    public List<Vehicle> search(String formula, int from, int count) {
        String sql = "SELECT * FROM tbl_vehicle " + formula + " LIMIT " + from + "," + count;
        List<Vehicle> search = jdbcTemplate.query(sql, new VehicleMapper());
        sql = "SELECT COUNT(*) FROM tbl_vehicle "  + formula;
        rowCount = jdbcTemplate.queryForObject(sql, Integer.class);
        return search;
    }

    @Override
    public int getCount() {
        return this.rowCount;
    }

    @Override
    public Vehicle getByLotNumber(int lotNumber) {
        String sql = "SELECT * FROM tbl_vehicle WHERE lotnumber = ?";
        Vehicle vehicle = (Vehicle) jdbcTemplate.queryForObject(sql, new Object[] { lotNumber }, new VehicleMapper());
        return vehicle;

    }

    @Override
    public SearchKeys initSearchKeys(){
        Map<String, String> types;
        Map<String, Set<String>> makes;
        Map<String, Set<String>> models;
        int minYear;
        int maxYear;

        types = new LinkedHashMap<String, String>();
        makes = new LinkedHashMap<String,Set<String>>();
        models = new LinkedHashMap<String,Set<String>>();
        minYear = Calendar.getInstance().get(Calendar.YEAR) - 10;
        maxYear =Calendar.getInstance().get(Calendar.YEAR) - 10;

        String type = "", currentType = "";
        String make = "", currentMake = "";
        String model = "", currentModel = "";
        Integer year = 0, currentYear = 0;

        String sql = "SELECT vehicletype,make,modelgroup,year FROM tbl_vehicle ORDER BY vehicletype,make,modelgroup,year";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            type = (String) row.get("vehicletype");
            make = (String) row.get("make");
            model = (String) row.get("modelgroup");
            year = (Integer) row.get("year");

            if(year != currentYear) {

                if(minYear > year){
                    minYear = year;
                }

                if(maxYear < year){
                    maxYear = year;
                }
                currentYear = year;
            }

            if(!type.equals(currentType)) {
                currentType = type;
                currentMake = "";
                currentModel = "";
                types.put(getTypeNameByKey(type), type);
            }

            if(!make.equals(currentMake)) {
                currentMake = make;
                currentModel = "";

                Set<String> entry;
                entry = makes.get("All Types");
                if(entry == null) {
                    entry = new LinkedHashSet<String>();
                }
                entry.add(make);
                makes.put("All Types", entry);

                entry = makes.get(type);
                if(entry == null) {
                    entry = new LinkedHashSet<String>();
                }
                entry.add(make);
                makes.put(type, entry);
            }

            if(!model.equals(currentModel)) {
                currentModel = model;

                Set<String> entry;
                entry = models.get("All Types" + '-' + "All Makes");
                if(entry == null) {
                    entry = new LinkedHashSet<String>();
                }
                entry.add(model);
                models.put("All Types"+ '-' + "All Makes", entry);

                entry = models.get("All Types" + '-' + make);
                if(entry == null) {
                    entry = new LinkedHashSet<String>();
                }
                entry.add(model);
                models.put("All Types" + "-" + make, entry);

                entry = models.get(type + '-' + "All Makes");
                if(entry == null) {
                    entry = new LinkedHashSet<String>();
                }
                entry.add(model);
                models.put(type + "-" + "All Makes", entry);

                entry = models.get(type + '-' + make);
                if(entry == null) {
                    entry = new LinkedHashSet<String>();
                }
                entry.add(model);
                models.put(type + "-" + make, entry);
            }
        }

        SearchKeys newSearchKeys = new SearchKeys();
        newSearchKeys.setTypes(types);
        newSearchKeys.setMakes(makes);
        newSearchKeys.setModels(models);
        newSearchKeys.setMinYear(minYear);
        newSearchKeys.setMaxYear(maxYear);

        return newSearchKeys;
    }

    public String getTypeNameByKey(String key){
        String name;
        name = "";

        if (key.equals("A")) {
            name = "ATV";
        }else if(key.equals("C")){
            name = "Motorcycle";
        }else if(key.equals("D")){
            name = "Dirt Bike";
        }else if(key.equals("E")){
            name = "Industrial Equipment";
        }else if(key.equals("H")){
            name = "H";
        }else if(key.equals("I")){
            name = "I";
        }else if(key.equals("J")){
            name = "Jet Ski";
        }else if(key.equals("K")){
            name = "Medium Duty/Box Trucks";
        }else if(key.equals("L")){
            name = "Trailers";
        }else if(key.equals("M")){
            name = "Boat";
        }else if(key.equals("R")){
            name = "Recreational Vehicle";
        }else if(key.equals("S")){
            name = "Snowmobile";
        }else if(key.equals("U")){
            name = "Heavy Duty Trucks";
        }else if(key.equals("V")){
            name = "Automobile";
        }

        switch(key) {
            case "ATV" : name = "A"; break;
            case "Motorcycle" : name = "C"; break;
            case "Dirt Bike" : name = "D"; break;
            case "Industrial Equipment" : name = "E"; break;
            case "H" : name = "H"; break;
        }

        return name;
    }
}
