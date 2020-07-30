package org.njax.trinetco.netgrid.java.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.njax.trinetco.netgrid.java.app.models.UserRepository;
import org.njax.trinetco.netgrid.java.app.models.UserEntity;

@Controller // This means that this class is a Controller
@RequestMapping(path="/users")
public class UserController {
    @Autowired // This means to get the bean called UserRepository
               // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository UserRepository;


    @Autowired // https://www.vojtechruzicka.com/spring-boot-version/
    org.springframework.boot.info.BuildProperties buildProperties;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String title,
                                             @RequestParam String body) {
        // @ResponseBody means the returned String is the response, not a view title
        // @RequestParam means it is a parameter from the GET or POST request

        UserEntity n = new UserEntity();
        n.setTitle(title);
        n.setBody(body);
        UserRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<UserEntity> getAllUsers() {
        // This returns a JSON or XML with the users
        return UserRepository.findAll();
    }

    @GetMapping(value = "")
    public ResponseEntity<Object> root() {
        String responseString = "";
        String artifactId = buildProperties.getArtifact();
        String group = buildProperties.getGroup();
        String version = buildProperties.getVersion();
        org.njax.trinetco.netgrid.java.app.models.version.Version modelVersion = new org.njax.trinetco.netgrid.java.app.models.version.Version();
        org.njax.trinetco.netgrid.java.api.version.Version appVersion = new org.njax.trinetco.netgrid.java.api.version.Version();

        responseString += "Web App: " + appVersion.info() + "<br>\n";
        responseString += "DB Models: " + modelVersion.info() + "<br>\n";

        responseString += "Count: " + UserRepository.count() + "<br>\n";
        responseString += "Cool, huh?" + "<br>\n";

        return new ResponseEntity<>(responseString, HttpStatus.OK);
    }

}
