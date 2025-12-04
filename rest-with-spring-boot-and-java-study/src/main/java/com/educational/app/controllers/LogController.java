package com.educational.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    private Logger logger = LoggerFactory.getLogger(LogController.class.getName());

    @GetMapping("/logs")
    public String logs (){
        logger.trace("doing a TRACE log");
        logger.debug("doing a DEBUG log");
        logger.info("doing a INFO log");
        logger.warn("doing a WARN log");
        logger.error("doing a ERROR log");
        return "Logs made successfully!";
    }
}
