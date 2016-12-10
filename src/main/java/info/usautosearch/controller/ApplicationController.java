package info.usautosearch.controller;

import java.util.ArrayList;
import java.util.List;

import info.usautosearch.configuration.SearchKeys;
import info.usautosearch.dao.VehicleDAO;
import info.usautosearch.model.Customer;
import info.usautosearch.model.CustomerValidator;
import info.usautosearch.service.CustomerService;
import info.usautosearch.model.Vehicle;
import info.usautosearch.service.SecurityService;

import info.usautosearch.xml.XmlUrl;
import info.usautosearch.xml.XmlUrlSet;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.ui.ModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Controller
@RequestMapping("/")
public class ApplicationController {
    @Autowired
    private SearchKeys searchKeys;

    @Autowired
    VehicleDAO vehicleDAO;

    @Autowired
    CustomerService customerService;

    @Autowired
    private CustomerValidator customerValidator;

    @Autowired
    private SecurityService securityService;

    /**
     * public PlayerScore getHighScore() {
     ServletContext ctx = getServletConfig().getServletContext();
     AtomicReference<PlayerScore> holder
     = (AtomicReference<PlayerScore>) ctx.getAttribute("highScore");
     return holder.get();
     }

     public void updateHighScore(PlayerScore newScore) {
     ServletContext ctx = getServletConfig().getServletContext();
     AtomicReference<PlayerScore> holder
     = (AtomicReference<PlayerScore>) ctx.getAttribute("highScore");
     while (true) {
     HighScore old = holder.get();
     if (old.score >= newScore.score)
     break;
     else if (holder.compareAndSet(old, newScore))
     break;
     }} SpringMVC, AbstractController synchronizeOnSession*/

    @RequestMapping(value = "/test")
    public String listVehicles(ModelMap model) {

        return "test";
    }

    @RequestMapping(value = "/sitemap.xml", method = RequestMethod.GET)
    @ResponseBody
    public XmlUrlSet main() {
        XmlUrlSet xmlUrlSet = new XmlUrlSet();
        create(xmlUrlSet, "", XmlUrl.Priority.HIGH);
        create(xmlUrlSet, "/link-1", XmlUrl.Priority.HIGH);
        create(xmlUrlSet, "/link-2", XmlUrl.Priority.MEDIUM);

        // for loop to generate all the links by querying against database
        return xmlUrlSet;
    }

    //@RequestMapping(value = "/robots.txt", method = RequestMethod.GET)
    //public String getRobots(HttpServletRequest request) {
    //    return (Arrays.asList("mysite.com", "www.mysite.com").contains(request.getServerName())) ?
    //            "robotsAllowed" : "robotsDisallowed";
    //}

    private void create(XmlUrlSet xmlUrlSet, String link, XmlUrl.Priority priority) {
        xmlUrlSet.addUrl(new XmlUrl("http://www.mysite.com" + link, priority));
    }



    @RequestMapping(value = { "/*" }, method = RequestMethod.GET)
    public String listVehicles(@RequestParam(value="page", required=false, defaultValue = "1") int page, ModelMap model) {
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
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        String login;
        if (error != null) {
            model.addAttribute("error", "Invalid username and password!");
            login = "login";
        }

        if (logout != null) {
            model.addAttribute("message", "You've been logged out successfully.");
        }


        return "login";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }

    @RequestMapping(value = { "/register" }, method = RequestMethod.GET)
    public String register(ModelMap model) {
        model.addAttribute("customer", new Customer());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("customer") Customer customer, BindingResult bindingResult, Model model) {
        customerValidator.validate(customer, bindingResult);
        if (bindingResult.hasErrors()) {
            return "register";
        }

        customerService.save(customer);
        securityService.autologin(customer.getEmail(), customer.getPassword());

        return "redirect:/";
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

    @RequestMapping("/to-redirected")
    public RedirectView localRedirect(
            @RequestParam(value="vin", required=false, defaultValue = "") String vin,
            @RequestParam(value="lot", required=false, defaultValue = "") String lot) {

        RedirectView redirectView = new RedirectView();
        if(!vin.equals("")) redirectView.setUrl("http://epicvin.com/vin-" + vin + "/?aff_id=582838f8b1835");
        if(!lot.equals("")) redirectView.setUrl("https://abetter.bid/" + lot);
        return redirectView;
    }
}
