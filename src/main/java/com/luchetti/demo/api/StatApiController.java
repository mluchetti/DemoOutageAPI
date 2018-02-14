package com.luchetti.demo.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luchetti.demo.model.OutageStatus;
import com.luchetti.demo.model.OutageStatus.StateEnum;

import io.swagger.annotations.ApiParam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Optional;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-02-12T22:58:02.562-06:00")

@Controller
public class StatApiController implements StatApi {

    Logger log = LogManager.getLogger(StatApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public StatApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.ofNullable(objectMapper);
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.ofNullable(request);
    }

	@Override
	public ResponseEntity<OutageStatus> statusRequest(@ApiParam(value = "",required=true) @PathVariable("account") String account) {
		log.info(String.format("Account Requesting Outage Status: %s",account));

		//TODO: Tear this code sample out and start from scratch with TDD
		ResponseEntity<OutageStatus> outStat = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
        if (getAcceptHeader().get().contains("application/json")) {
            try {
            	OutageStatus out = new OutageStatus();
            	out.account(account)
            		.customersImpacted(5)
            		.houseNumber("1234")
            		.state(StateEnum.IL)
            		.postalCode("62526")
            		.isPowerOn(false)
            		.reported(false);
            	
                outStat = new ResponseEntity<>(out, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return outStat;
	}

}
