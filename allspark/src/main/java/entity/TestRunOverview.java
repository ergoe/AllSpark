package entity;

import java.io.Serializable;

/**
 * Created by Eric on 1/24/17.
 */
public class TestRunOverview implements Serializable {
    public String testRunId;
    public int failedCount;
    public int impossibleCount;
    public int passCount;
    public int resultNotCapturedCount;

    public TestRunOverview(String testRunId, int newFailedCount, int newImpossibleCount, int newPassCount, int newResultNotCapturedCount) {
        this.testRunId = testRunId;
        this.failedCount = newFailedCount;
        this.impossibleCount = newImpossibleCount;
        this.passCount = newPassCount;
        this.resultNotCapturedCount = newResultNotCapturedCount;
    }




}
