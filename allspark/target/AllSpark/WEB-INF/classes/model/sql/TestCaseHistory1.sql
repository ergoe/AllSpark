SELECT
        history.Id
        , TestCaseExecution.CaseName
        , history.CaseResult
        , history.Environment
        , history.ExecutionHost
        , history.TestCaseId
        , history.TestClassExecution_id
FROM TestRun
        JOIN TestClassExecution AS TCLE ON  TCLE.TestRun_id = TestRun.Id
        JOIN TestCaseExecution ON TestCaseExecution.TestClassExecution_id = TCLE.Id
        JOIN (SELECT
--                 Top 10
                TestRun.Id,
                TestRun.Environment
                , TestRun.ExecutionHost
                , TCLE.TestRun_id
                , TestCaseExecution.Id AS TestCaseId
                , TestCaseExecution.TestClassExecution_id
                , TestCaseExecution.CaseResult
                , TestCaseExecution.CaseName,
         ROW_NUMBER() over (Partition BY TestCaseExecution.CaseName
         ORDER BY TestCaseExecution.Id DESC ) AS stupid
        FROM TestRun
          JOIN TestClassExecution AS TCLE ON  TCLE.TestRun_id = TestRun.Id
          JOIN TestCaseExecution ON TestCaseExecution.TestClassExecution_id = TCLE.Id
          WHERE TestRun.Id <= TEST_RUN_ID

--          WHERE TestRun.Environment LIKE '%stage' AND TestCaseExecution.CaseName = 'buildBXGX_dollar'
--         WHERE TestCaseExecution.CaseName = 'searchBySkuFindsResults[CHROME,LOWER_CASE,{2}]'
--         ORDER BY TestRun.Id DESC
             ) AS history ON history.CaseName = TestCaseExecution.CaseName
WHERE TestRun.Id = TEST_RUN_ID AND stupid <= 10
ORDER BY  TestCaseExecution.CaseName, history.Id DESC