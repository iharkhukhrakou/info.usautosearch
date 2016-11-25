package info.usautosearch.controller;

import info.usautosearch.configuration.SearchKeys;
import info.usautosearch.dao.VehicleDAO;
import info.usautosearch.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.ArrayList;

@Controller
@RequestMapping(value = {"/car-finder/"})
public class SearchController {
    @Autowired
    private SearchKeys searchKeys;

    @Autowired
    VehicleDAO vehicleDAO;

    @RequestMapping(value = "/")
    public String listVehiclesPost(
            @RequestParam(value="searchType", required=false, defaultValue = "All Types") String searchType,
            @RequestParam(value="searchMake", required=false, defaultValue = "All Makes") String searchMake,
            @RequestParam(value="searchModel", required=false, defaultValue = "All Models") String searchModel,
            @RequestParam(value="searchYearFrom", required=false, defaultValue = "0") int searchYearFrom,
            @RequestParam(value="searchYearTo", required=false, defaultValue = "0") int searchYearTo,
            ModelMap model) {

        String returnString = "";

        StringBuilder url;
        url = new StringBuilder(0);

        if(!searchType.equals("All Types")){
            url.append("type-" + searchType + "/");
        }
        if(!searchMake.equals("All Makes")){
            url.append("make-" + searchMake + "/");
        }
        if(!searchModel.equals("All Models")){
            url.append("model-" + searchModel + "/");
        }
        if(searchYearFrom != searchKeys.getMinYear() || searchYearTo != searchKeys.getMaxYear()){
            url.append("year-" + searchYearFrom + "-" + searchYearTo + "/");
        }

        if(url.toString().equals("")){
            int recordsPerPage = 10;

            List<Vehicle> vehicles = vehicleDAO.getAll(0, recordsPerPage);
            model.addAttribute("vehicles", vehicles);
            model.addAttribute("currentPage", 1);
            model.addAttribute("totalPage", (int) Math.ceil(vehicleDAO.getCount() * 1.0 / recordsPerPage));
            model.addAttribute("type", "param");
            model.addAttribute("allTypes", searchKeys.getTypes());
            model.addAttribute("allMakes", searchKeys.getMakesByType("All Types"));
            model.addAttribute("allModels", searchKeys.getModelsByKey("All Types-All Makes"));
            model.addAttribute("minYear", searchKeys.getMinYear());
            model.addAttribute("maxYear", searchKeys.getMaxYear());
            returnString = "carfinder";
        }else{
            returnString = "redirect:/" + "car-finder/" + url.toString();
        }

        return returnString;
    }

    @RequestMapping(value = {
            "/{pathOne}","/{pathOne}/"}, method = RequestMethod.GET)
    public ModelAndView listVehicles(@PathVariable String pathOne) {
        List<String> pathKeys;
        pathKeys = new ArrayList<String>();
        pathKeys.add(pathOne);

        return createModelAndView(pathKeys);
    }

    @RequestMapping(value = {
            "/{pathOne}/{pathTwo}","/{pathOne}/{pathTwo/}"}, method = RequestMethod.GET)
    public ModelAndView listVehicles(@PathVariable String pathOne,@PathVariable String pathTwo) {
        List<String> pathKeys;
        pathKeys = new ArrayList<String>();
        pathKeys.add(pathOne);
        pathKeys.add(pathTwo);

        return createModelAndView(pathKeys);
    }

    @RequestMapping(value = {
            "/{pathOne}/{pathTwo}/{pathTree}","/{pathOne}/{pathTwo}/{pathTree}/"}, method = RequestMethod.GET)
    public ModelAndView listVehicles(
            @PathVariable String pathOne,
            @PathVariable String pathTwo,
            @PathVariable String pathTree) {
        List<String> pathKeys;
        pathKeys = new ArrayList<String>();
        pathKeys.add(pathOne);
        pathKeys.add(pathTwo);
        pathKeys.add(pathTree);

        return createModelAndView(pathKeys);
    }

    @RequestMapping(value = {
            "/{pathOne}/{pathTwo}/{pathTree}/{pathFour}","/{pathOne}/{pathTwo}/{pathTree}/{pathFour}/"}, method = RequestMethod.GET)
    public ModelAndView listVehicles(
            @PathVariable String pathOne,
            @PathVariable String pathTwo,
            @PathVariable String pathTree,
            @PathVariable String pathFour) {
        List<String> pathKeys;
        pathKeys = new ArrayList<String>();
        pathKeys.add(pathOne);
        pathKeys.add(pathTwo);
        pathKeys.add(pathTree);
        pathKeys.add(pathFour);

        return createModelAndView(pathKeys);
    }

    @RequestMapping(value = {
            "/{pathOne}/{pathTwo}/{pathTree}/{pathFour}/{pathFive}","/{pathOne}/{pathTwo}/{pathTree}/{pathFour}/{pathFive}/"}, method = RequestMethod.GET)
    public ModelAndView listVehicles(
            @PathVariable String pathOne,
            @PathVariable String pathTwo,
            @PathVariable String pathTree,
            @PathVariable String pathFour,
            @PathVariable String pathFive) {
        List<String> pathKeys;
        pathKeys = new ArrayList<String>();
        pathKeys.add(pathOne);
        pathKeys.add(pathTwo);
        pathKeys.add(pathTree);
        pathKeys.add(pathFour);
        pathKeys.add(pathFive);

        return createModelAndView(pathKeys);
    }

    private ModelAndView createModelAndView(List<String> pathKeys){
        StringBuilder searchFormula;
        searchFormula = new StringBuilder(0);
        searchFormula.append("");

        String currentType = "All Types";
        String currentMake = "All Makes";
        String currentModel = "All Models";
        String currentYearFrom = "";
        String currentYearTo = "";
        int currentPage = 1;

        for(String pathKey : pathKeys){
            if(pathKey.contains("-")){
                String[] values;
                values = pathKey.split("-");

                switch(values[0]) {
                    case "type":{
                        searchFormula.append("vehicletype='" + values[1] + "'");
                        currentType = values[1];
                    } break;
                    case "make":{
                        if(!searchFormula.toString().equals("")) searchFormula.append(" AND ");
                        searchFormula.append("make='" + values[1] + "'");
                        currentMake = values[1];
                    } break;
                    case "model":{
                        if(!searchFormula.toString().equals("")) searchFormula.append(" AND ");
                        searchFormula.append("modelgroup='" + values[1] + "'");
                        currentModel = values[1];
                    } break;
                    case "year":{
                        if(!searchFormula.toString().equals("")) searchFormula.append(" AND ");
                        searchFormula.append("year>=" + values[1]);
                        if(!searchFormula.toString().equals("")) searchFormula.append(" AND ");
                        searchFormula.append("year<=" + values[2]);

                        currentYearFrom = values[1];
                        currentYearTo = values[2];
                    } break;
                    case "page":{
                        currentPage = Integer.parseInt(values[1]);
                    } break;
                }
            }
        }

        int recordsPerPage = 10;

        List<Vehicle> vehicles = vehicleDAO.search( "WHERE " + searchFormula.toString(),(currentPage-1)*recordsPerPage, recordsPerPage);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("carfinder");
        mav.addObject("vehicles", vehicles);
        mav.addObject("allTypes", searchKeys.getTypes());
        mav.addObject("currentType", currentType);
        mav.addObject("allMakes", searchKeys.getMakesByType(currentType));
        mav.addObject("currentMake", currentMake);
        mav.addObject("allModels", searchKeys.getModelsByKey(currentType + "-" + currentMake));
        mav.addObject("currentModel", currentModel);
        mav.addObject("minYear", searchKeys.getMinYear());
        mav.addObject("maxYear", searchKeys.getMaxYear());
        mav.addObject("currentYearFrom", currentYearFrom);
        mav.addObject("currentYearTo", currentYearTo);
        return mav;
    }

    @RequestMapping(value = "/updateSearchKeys")
    @ResponseBody
    public String updateSearchKeys() {
        SearchKeys newSearchKeys;
        newSearchKeys = vehicleDAO.initSearchKeys();

        searchKeys.setTypes(newSearchKeys.getTypes());
        searchKeys.setMakes(newSearchKeys.getMakes());
        searchKeys.setModels(newSearchKeys.getModels());
        searchKeys.setMinYear(newSearchKeys.getMinYear());
        searchKeys.setMaxYear(newSearchKeys.getMaxYear());

        return "END";
    }
}
