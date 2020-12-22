package app.controller;

import app.exception.ServiceException;
import app.model.User;
import app.service.OrchService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/orch")
public class OrchController {

    private static final Logger logger = LogManager.getLogger(OrchController.class);

    @Autowired
    private OrchService orchService;

    @GetMapping(value = "/v1/user/{userId}")
    public ResponseEntity<User> getUser(@RequestParam String userId) throws ServiceException {
        logger.info("/"+userId+"/friends | Request received");
        User userDetails = orchService.getUserDetails(userId);
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }

    @GetMapping(value = "/v1/user/{userId}/friends")
    public ResponseEntity<List<User>> getFriends(@RequestParam String userId) throws ServiceException {
        logger.info("/getFriends Received request: "+userId);
        List<User> friendsList = orchService.getFriendsList(userId);
        return new ResponseEntity<>(friendsList, HttpStatus.OK);
    }
}
