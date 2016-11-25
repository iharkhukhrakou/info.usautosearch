package info.usautosearch.dao;

import info.usautosearch.model.Vehicle;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleMapper implements RowMapper<Vehicle> {
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
}
