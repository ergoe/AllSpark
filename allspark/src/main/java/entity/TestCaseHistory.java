package entity;

import java.io.Serializable;

/**
 * Created by Eric on 4/20/16.
 */
public class TestCaseHistory implements Serializable {

    private String testRunId;
    private String testClassExecution_id;
    private String environment;
    private String caseResult;
    private String caseName;
    private String caseId;


    public TestCaseHistory(String testRunId, String testClassExecution_id, String environment, String caseResult, String caseName, String caseId) {
        setTestRunId(testRunId);
        setTestClassExecutionId(testClassExecution_id);
        setEnvironment(environment);
        setCaseResult(caseResult);
        setCaseName(caseName);
        setCaseId(caseId);
    }

    public String getTestRunId() {
        return this.testRunId;
    }

    public TestCaseHistory setTestRunId(String testRunId) {
        this.testRunId = testRunId;
        return this;
    }

    public String getTestClassExecution_id() {
        return testClassExecution_id;
    }

    public TestCaseHistory setTestClassExecutionId (String testClassExecutionId) {
        this.testClassExecution_id = testClassExecutionId;
        return this;
    }

    public String getEnvironment() {
        return environment;
    }

    public TestCaseHistory setEnvironment(String environment) {
        this.environment = environment;
        return this;
    }

    public String getCaseResult() {
        return caseResult;
    }

    public TestCaseHistory setCaseResult(String caseResult) {
        this.caseResult = caseResult;
        return this;
    }

    public String getCaseName() {
        return caseName;
    }

    public TestCaseHistory setCaseName(String caseName) {
        this.caseName = caseName;
        return this;
    }

    public String getCaseId() { return this.caseId; }

    public TestCaseHistory setCaseId(String caseId) {
        this.caseId = caseId;
        return this;
    }


}
