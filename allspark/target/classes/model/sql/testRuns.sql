SELECT Id AS id
        , RunStartTime AS startTime
        , Environment AS environment
        , Area AS area
        , BuildNum AS buildNum
        , ExecutionHost AS executionHost
FROM TestRun
WHERE
--          area = 'commerce' AND environment LIKE '%stage%'
         area = 'commerce' AND environment LIKE TESTING_ENVIRONMENT
         AND buildNum LIKE BUILD_NAME
         AND executionHost LIKE TEST_HOST
ORDER BY
         id DESC