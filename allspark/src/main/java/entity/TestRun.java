package entity;

import java.io.Serializable;

/**
 * Created by Eric on 4/20/16.
 */
public class TestRun implements Serializable {

    private String testRunId;
    private String startTime;
    private String environment;
    private String area;
    private String buildNumber;
    private String executionHost;

    public TestRun(String testRunId, String startTime, String environment, String area, String buildNumber, String executionHost) {
        setTestRunId(testRunId);
        setStartTime(startTime);
        setEnvironment(environment);
        setArea(area);
        setBuildNumber(buildNumber);
        setExecutionHost(executionHost);
    }

    public String getTestRunId() {
        return this.testRunId;
    }

    public TestRun setTestRunId(String testRunId) {
        this.testRunId = testRunId;
        return this;
    }

    public String getStartTime() {
        return startTime;
    }

    public TestRun setStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public String getEnvironment() {
        return environment;
    }

    public TestRun setEnvironment(String environment) {
        this.environment = environment;
        return this;
    }

    public String getArea() {
        return area;
    }

    public TestRun setArea(String area) {
        this.area = area;
        return this;
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    public TestRun setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber;
        return this;
    }

    public String getExecutionHost() {
        return executionHost;
    }

    public TestRun setExecutionHost(String executionHost) {
        this.executionHost = executionHost;
        return this;
    }
}
