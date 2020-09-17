package com.allspark;

import model.MSSqlDao;
import model.User;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eric on 6/22/16.
 */
@CrossOrigin
@RestController
public class TestCaseHistoryController {

//    @CrossOrigin
//    @RequestMapping(
//            value = "/testCaseHistory/{testCaseName}",
////            params = "environment={environment}",
//            method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Collection<User>> getTestCaseHistory(@PathVariable String testCaseName, @RequestParam(required = false) String environment) {
//
//        Map<String, String> replacements = new HashMap<String, String>();
//        String testNameWithQuotes = new StringBuilder(testCaseName.length()+2).append('\'').append(testCaseName).append('\'').toString();
//
//        // Adding quotes around parameters to use in SQL query
//        String environmentWithQuotes;
//        if (environment != null && !environment.isEmpty()) {
//            environmentWithQuotes = new StringBuilder(environment.length()+2).append('\'').append('%').append(environment).append('%').append('\'').toString();
//        } else {
//            environmentWithQuotes = new StringBuilder(3).append('\'').append('%').append('\'').toString();
//        }
//        replacements.put("TESTING_ENVIRONMENT", environmentWithQuotes);
//        replacements.put("TEST_CASE_NAME", testNameWithQuotes);
//        MSSqlDao dao = new MSSqlDao();
//        String testRuns = dao.getTestCaseHistory(replacements);
//
//        ResponseEntity entity = new ResponseEntity(testRuns, HttpStatus.OK);
//        return entity;
//    }

    @CrossOrigin
    @RequestMapping(
            value = "/testCaseHistory/{testRunId}",
//            params = "environment={environment}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<User>> getTestCaseHistory(@PathVariable String testRunId, @RequestParam(required = false) String environment) {

        Map<String, String> replacements = new HashMap<String, String>();
        String testRunIdWithQuotes = new StringBuilder(testRunId.length()+2).append('\'').append(testRunId).append('\'').toString();

        // Adding quotes around parameters to use in SQL query
        String environmentWithQuotes;
        if (environment != null && !environment.isEmpty()) {
            environmentWithQuotes = new StringBuilder(environment.length()+2).append('\'').append('%').append(environment).append('%').append('\'').toString();
        } else {
            environmentWithQuotes = new StringBuilder(3).append('\'').append('%').append('\'').toString();
        }
        replacements.put("TESTING_ENVIRONMENT", environmentWithQuotes);
        replacements.put("TEST_RUN_ID", testRunIdWithQuotes);
        MSSqlDao dao = new MSSqlDao();
        String testRuns = dao.getTestCaseHistory(replacements);

        ResponseEntity entity = new ResponseEntity(testRuns, HttpStatus.OK);
        return entity;
    }
}
