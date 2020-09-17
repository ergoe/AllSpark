SELECT
        TOP 10
        TestRun.Id,
        TestRun.Environment
        , TCLE.TestRun_id
        , TestCaseExecution.Id AS TestCaseId
        , TestCaseExecution.TestClassExecution_id
        , TestCaseExecution.Case Result
        , TestCaseExecution.CaseName
FROM TestRun
  JOIN TestClassExecution AS TCLE ON  TCLE.TestRun_id = TestRun.Id
  JOIN TestCaseExecution ON TestCaseExecution.TestClassExecution_id = TCLE.Id
--   WHERE TestCaseExecution.CaseName = TEST_CASE_NAME
  WHERE TestRun.Environment LIKE TESTING_ENVIRONMENT AND TestCaseExecution.CaseName = TEST_CASE_NAME
  ORDER BY TestRun.Id DESC