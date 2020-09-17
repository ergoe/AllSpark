package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
//import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;
import entity.TestCaseHistory;
import entity.TestRun;
import entity.TestRunOverview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by Eric on 4/8/16.
 */
public class MSSqlDao {

    private static final Logger LOG = LoggerFactory.getLogger(MSSqlDao.class);
    QueryCreator queryCreator = null;

    public String getTestRuns(Map<String, String> replacements) {
        queryCreator = new QueryCreator();
        SqlConnection sqlConnection = new SqlConnection();
        LOG.trace("getting database connection");
        Connection conn = sqlConnection.getConnection();;
        Statement statement = null;
        ResultSet resultSet = null;
        String jsonValue = "";


        try {

            //conn = sqlConnection.getConnection();
            if (conn != null) {
                //PreparedStatement preparedStatement = conn.prepareStatement(queryCreator.createQuery("testRuns.sql"));
                PreparedStatement preparedStatement = conn.prepareStatement(queryCreator.createQueryWithReplacements("testRuns.sql", replacements));
                resultSet = preparedStatement.executeQuery();
                List<TestRun> objects = createTestRunObjectsFromResultSet(resultSet);
                ObjectMapper mapper = new ObjectMapper();

                try {
                    LOG.trace("converting testruns LIST to json");
                    jsonValue = mapper.writeValueAsString(objects);

                } catch (Exception ex) {
                    LOG.error(ex.getLocalizedMessage(), "ERROR converting to JSON");
                }
            }

        } catch(SQLException ex) {
            LOG.error("Instantiation exception for Class.forName", ex.getLocalizedMessage());
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return jsonValue;
    }

    public String getTestRunOverview(String testRunId, Map<String, String> replacements) {
        queryCreator = new QueryCreator();
        SqlConnection sqlConnection = new SqlConnection();
        LOG.trace("getting database connection");
        Connection conn = sqlConnection.getConnection();;
        Statement statement = null;
        ResultSet resultSet = null;
        String jsonValue = "";


        try {

            //conn = sqlConnection.getConnection();
            if (conn != null) {
                //PreparedStatement preparedStatement = conn.prepareStatement(queryCreator.createQuery("testRuns.sql"));
                PreparedStatement preparedStatement = conn.prepareStatement(queryCreator.createQueryWithReplacements("testRunOverview.sql", replacements));
                resultSet = preparedStatement.executeQuery();
                TestRunOverview objects = createTestRunOverviewObject(testRunId, resultSet);
                ObjectMapper mapper = new ObjectMapper();

                try {
                    LOG.trace("converting testruns LIST to json");
                    jsonValue = mapper.writeValueAsString(objects);

                } catch (Exception ex) {
                    LOG.error(ex.getLocalizedMessage(), "ERROR converting to JSON");
                }
            }

        } catch(SQLException ex) {
            LOG.error("Instantiation exception for Class.forName", ex.getLocalizedMessage());
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return jsonValue;
    }

    public String getTestCaseHistory(Map<String, String> replacements) {
        queryCreator = new QueryCreator();
        SqlConnection sqlConnection = new SqlConnection();
        LOG.trace("getting database connection");
        Connection conn = sqlConnection.getConnection();;
        Statement statement = null;
        ResultSet resultSet = null;
        String jsonValue = "";


        try {

            //conn = sqlConnection.getConnection();
            if (conn != null) {
                PreparedStatement preparedStatement = conn.prepareStatement(queryCreator.createQueryWithReplacements("TestCaseHistory1.sql", replacements));
                resultSet = preparedStatement.executeQuery();
                List<TestCaseHistory> objects = createTestCaseHistoryObjectsFromResultSet(resultSet);
                ObjectMapper mapper = new ObjectMapper();

                try {
                    LOG.trace("converting testruns LIST to json");
                    jsonValue = mapper.writeValueAsString(objects);

                } catch (Exception ex) {
                    LOG.error(ex.getLocalizedMessage(), "ERROR converting to JSON");
                }
            }

        } catch(SQLException ex) {
            LOG.error("Instantiation exception for Class.forName", ex.getLocalizedMessage());
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return jsonValue;
    }

    public List<TestCaseHistory> createTestCaseHistoryObjectsFromResultSet(ResultSet resultSet) {
        List<TestCaseHistory> testCaseHistories = new ArrayList<>();

        try {
            while (resultSet.next()) {
                TestCaseHistory testCaseHistory = new TestCaseHistory(
                        resultSet.getString("Id"),
                        resultSet.getString("TestClassExecution_id"),
                        resultSet.getString("Environment"),
                        resultSet.getString("CaseResult"),
                        resultSet.getString("CaseName"),
                        resultSet.getString("TestCaseId")
                );
                testCaseHistories.add(testCaseHistory);
            }
        } catch (Exception ex) {

        }
        return testCaseHistories;
    }

    public List<TestRun> createTestRunObjectsFromResultSet(ResultSet resultSet) {
        List<TestRun> testRuns = new ArrayList<>();

        try {
            while (resultSet.next()) {
                TestRun testRun = new TestRun(
                        resultSet.getString("id"),
                        resultSet.getString("startTime"),
                        resultSet.getString("environment"),
                        resultSet.getString("area"),
                        resultSet.getString("buildNum"),
                        resultSet.getString("executionHost")
                );
                testRuns.add(testRun);
            }
            System.out.println();
        } catch (Exception ex) {

        }
        return testRuns;
    }

    private TestRunOverview createTestRunOverviewObject(String testRunId, ResultSet resultSet) {
        int failedCount = 0;
        int passCount = 0;
        int resultNotCapturedCount = 0;
        int impossibleCount = 0;

        TestRunOverview testRunOverview = null;
        try {
            while (resultSet.next()) {
                System.out.println(resultSet.getString("result"));
                if (resultSet.getString("result").equalsIgnoreCase("failed")) {
                    failedCount = resultSet.getInt("count");
                } else if (resultSet.getString("result").equalsIgnoreCase("pass")) {
                    passCount = resultSet.getInt("count");
                } else if (resultSet.getString("result").equalsIgnoreCase("impossible")) {
                    impossibleCount = resultSet.getInt("count");
                } else {
                    resultNotCapturedCount = resultSet.getInt("count");
                }
            }
        } catch (Exception ex) {

        }
        testRunOverview = new TestRunOverview(testRunId, failedCount, impossibleCount, passCount, resultNotCapturedCount);

        return testRunOverview;
    }


}
