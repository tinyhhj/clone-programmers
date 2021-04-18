package com.programmers.programmers.apis;

import com.programmers.programmers.apis.dto.ApiResult;
import com.programmers.programmers.apis.dto.RunProgramInput;
import com.programmers.programmers.apis.dto.TestDto;
import com.programmers.programmers.models.Test;
import com.programmers.programmers.models.TestCase;
import com.programmers.programmers.models.TestHistory;
import com.programmers.programmers.models.User;
import com.programmers.programmers.repositories.TestHistoryRepository;
import com.programmers.programmers.repositories.TestRepository;
import com.programmers.programmers.repositories.TestUserRepository;
import com.programmers.programmers.services.TestHistoryService;
import com.programmers.programmers.services.TestService;
import com.programmers.programmers.services.TestUserservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.util.StreamReaderDelegate;
import java.io.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class Apis {

    @Autowired
    private TestHistoryService testHistoryService;

    @Autowired
    private TestUserservice testUserservice;

    private final TestService testService;
    public Apis(TestService testService)  {
        this.testService = testService;
    }

    @GetMapping("/tests")
    public ApiResult<List<TestDto>> findTests() {
        return ApiResult.succeed(this.testService.getTests().stream().map(TestDto::new)
                .collect(Collectors.toList()));
    }

    @GetMapping("/histories")
    public ApiResult<List<TestHistory>> findTestHistories(
            @RequestParam("testId") Long testId,
            Principal principal) {
        List<TestHistory> histories = new ArrayList<>();
        if( principal != null) {
            histories = this.testHistoryService.findByTestIdAndUserLoginId(testId,principal.getName());
        }
        return ApiResult.succeed(histories);
    }

    @PostMapping("/run")
    public ApiResult<TestHistory> runTest(
            @RequestParam("testId") Long testId,
            Principal principal,
            @RequestBody RunProgramInput program) throws IOException, InterruptedException {
        // code
        // 실행 정답 & 프로그램 실행
        Test test = this.testService.getTest(testId);
        User user = this.testUserservice.findByLoginId(principal.getName());
        TestHistory history = new TestHistory(test,user,"",true,1000);
        for(TestCase testCase: test.getTestCases()) {
            ProcessBuilder builder = new ProcessBuilder();
            builder.directory(new File("/Users/tinyhhj/vs-projects/test-vm"));
            builder.command("./vm2.js","-c",program.getCode(), "-i", testCase.getInput(), "-o",testCase.getOutput());
//            builder.command("ls");
            Process process = builder.start();
            StreamGobbler streamGobbler =
                    new StreamGobbler(process.getInputStream(), System.out::println);
            StreamGobbler streamGobbler1 = new StreamGobbler(process.getErrorStream(), System.out::println);
            Executors.newSingleThreadExecutor().submit(streamGobbler);
            Executors.newSingleThreadExecutor().submit(streamGobbler1);

            int exitCode = process.waitFor();

            if( exitCode != 0) {
                history = new TestHistory(test,user,"error",false,10000);
                break;
            }
        }
        return ApiResult.succeed(this.testHistoryService.createHistory(history));
    }

    private class StreamGobbler implements Runnable{
        InputStream inputStream;
        Consumer<String> consumer;
        public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
            this.inputStream = inputStream;
            this.consumer = consumer;
        }
        @Override
        public void run() {
            new BufferedReader(new InputStreamReader(inputStream)).lines().forEach(consumer);
        }
    }

}
