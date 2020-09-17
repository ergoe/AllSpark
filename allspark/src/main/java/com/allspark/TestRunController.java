package com.allspark;

import entity.TestRunOverview;
import model.MSSqlDao;
import model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestRunController {

    @RequestMapping(
            value = "/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<User>> getTestRuns(
            @RequestParam(required = false) String environment,
            @RequestParam(required = false) String host,
            @RequestParam(required = false) String build) {
        Map<String, String> replacements = new HashMap<>();

        // Adding quotes around parameters to use in SQL query
        String environmentWithQuotes;
        if (environment != null && !environment.isEmpty()) {
            environmentWithQuotes = new StringBuilder(environment.length()+2).append('\'').append('%').append(environment).append('%').append('\'').toString();
        } else {
            environmentWithQuotes = new StringBuilder(3).append('\'').append('%').append('\'').toString();
        }
        String hostWithQuotes;
        if (host != null && !host.isEmpty()) {
            hostWithQuotes = new StringBuilder(host.length()+2).append('\'').append('%').append(host).append('%').append('\'').toString();
        } else {
            hostWithQuotes = new StringBuilder(3).append('\'').append('%').append('\'').toString();
        }
        String buildWithQuotes;
        if (build != null && !build.isEmpty()) {
            buildWithQuotes = new StringBuilder(build.length()+2).append('\'').append('%').append(build).append('%').append('\'').toString();
        } else {
            buildWithQuotes = new StringBuilder(3).append('\'').append('%').append('\'').toString();
        }
        replacements.put("TESTING_ENVIRONMENT", environmentWithQuotes);
        replacements.put("BUILD_NAME", buildWithQuotes);
        replacements.put("TEST_HOST", hostWithQuotes);


//        Collection<User> users = userMap.values();
        MSSqlDao dao = new MSSqlDao();
        String testRuns = dao.getTestRuns(replacements);

        return new ResponseEntity(testRuns, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/testRunOverview",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TestRunOverview> getTestRunOverview(@RequestParam(required=true) String runId) {
        Map<String, String> replacements = new HashMap<>();

        String runIdWithQuotes;
        runIdWithQuotes = new StringBuilder(runId.length()+2).append('\'').append(runId).append('\'').toString();

        replacements.put("TEST_RUN_ID", runIdWithQuotes);
        MSSqlDao dao = new MSSqlDao();
        String testRunOverview = dao.getTestRunOverview(runId, replacements);

        return new ResponseEntity(testRunOverview, HttpStatus.OK);
    }

}
