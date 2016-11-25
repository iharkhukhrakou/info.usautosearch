package info.usautosearch.controller;

import java.util.List;

import info.usautosearch.configuration.SearchKeys;
import info.usautosearch.dao.VehicleDAO;
import info.usautosearch.model.Vehicle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/")
public class ApplicationController {
    @Autowired
    private SearchKeys searchKeys;

    @Autowired
    VehicleDAO vehicleDAO;

    @RequestMapping(value = "/test")
    @ResponseBody
    public String responseBody()  {


        return "Test !!!!";
    }

    @RequestMapping(value = { "/*" }, method = RequestMethod.GET)
    public String listVehicles(@RequestParam(value="page", required=false, defaultValue = "1") int page, ModelMap model) {
        int recordsPerPage = 10;
        List<Vehicle> vehicles = vehicleDAO.getAll((page-1)*recordsPerPage, recordsPerPage);

        model.addAttribute("vehicles", vehicles);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", (int) Math.ceil(vehicleDAO.getCount() * 1.0 / recordsPerPage));
        model.addAttribute("type", "param");
        return "index";
    }

    @RequestMapping(value = "/lot/{lotNumber}")
    public String viewVehicle(@PathVariable int lotNumber, ModelMap model){
        Vehicle vehicle;
        vehicle = vehicleDAO.getByLotNumber(lotNumber);

        model.addAttribute("vehicle", vehicle);

        return "viewVehicle";
    }

    @RequestMapping(value = "/car-finder", method = RequestMethod.GET)
    public String listVehicles(
            @RequestParam(value="search_query", required=false, defaultValue = "") String searchQuery,
            @RequestParam(value="page", required=false, defaultValue = "1") int page,
            ModelMap model) {
        int recordsPerPage = 10;

        List<Vehicle> vehicles = vehicleDAO.getAll((page-1)*recordsPerPage, recordsPerPage);
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", (int) Math.ceil(vehicleDAO.getCount() * 1.0 / recordsPerPage));
        model.addAttribute("type", "param");
        model.addAttribute("allTypes", searchKeys.getTypes());
        model.addAttribute("allMakes", searchKeys.getMakesByType("All Types"));
        model.addAttribute("allModels", searchKeys.getModelsByKey("All Types-All Makes"));
        model.addAttribute("minYear", searchKeys.getMinYear());
        model.addAttribute("maxYear", searchKeys.getMaxYear());

        return "carfinder";
    }
}
