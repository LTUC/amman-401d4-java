package com.d4coders.helloworld.controller;

import com.d4coders.helloworld.model.Information;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * `@Controller` tells spring to load this class as a controller and look for routes
 * `@RequestMapping` tells spring the path for the controller (prefix of the individual routes)
 */
@Controller
@RequestMapping("/v1")
public class GreetingController {

    /**
     * GetMapping("/greeting") is a shortcut @RequestMapping(value = "/greeting", method = RequestMethod.GET)
     *
     * @return
     */
    @GetMapping("/greeting")
    public String greetings(@RequestParam(name = "name", required = false, defaultValue = "world") String name,
                           Model model) {
        model.addAttribute("name", name); // this is passed to the template automatically
        return "greeting"; // this represents the name of the html template which will be rendered
    }

    /**
     * RequestMapping maps a path to a method.
     * RequestMethods can get GET, POST, UPDATE, etc.
     * <p>
     * ResponseBody tells spring that the contents of return value should be sent
     * to the requester.
     *
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("message", "Welcome To D4 Coders Application Jason");
        return "index";
    }

    @ResponseBody
    @GetMapping("/information")
    public String getInformation() {
        return "information"; // just returns a string
    }

    @ResponseBody
    @GetMapping("/class-information")
    public Information getClassInformation() {
        Information information = new Information("Class D4 Coders", 28); // returns the JSON representation of the object
        information.setStudentNames("Heba");
        information.setStudentNames("Sanaa");
        information.setStudentNames("Ghadeer");
        information.setStudentNames("Khair");
        information.setStudentNames("Deyaa");

        return information;
    }

    /**
     * http://localhost:8080/v1/class-info/{name}
     * <p>
     * To get a Path parameter (one embedded in the request path)
     * You use {variableName} syntax.
     * <p>
     * It will be parsed by spring and passed in to the matching PathVariable
     *
     * @param name
     * @return
     */
    @GetMapping("/class-info/{name}")
    public String getClassInformation(@PathVariable String name, Model model) {
        model.addAttribute("name", name);
        return "information";
    }

    /**
     * http://localhost:8080/v1/greetings/{name}?greeting={greeting}
     * <p>
     * by adding a @RequestParam we can look for parameters on the query string.
     * In this case, we set it as required = false (by default it's true)
     * and set a default value
     *
     * @param name
     * @param greeting
     * @return
     */
    @GetMapping("/greetings/{name}")
    @ResponseBody
    public String getGreetingForName(
            @PathVariable String name,
            @RequestParam(required = false, defaultValue = "Hello, ") String greeting
    ) {
        String results = greeting + name;
        return results;
    }
}
