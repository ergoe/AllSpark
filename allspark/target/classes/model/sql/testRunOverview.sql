SELECT DISTINCT
          CaseResult AS result
        , COUNT(CaseResult) AS count
FROM TestCaseExecution
INNER JOIN TestClassExecution ON TestCaseExecution.TestClassExecution_id = TestClassExecution.Id
WHERE TestRun_id = TEST_RUN_ID
GROUP BY CaseResult
