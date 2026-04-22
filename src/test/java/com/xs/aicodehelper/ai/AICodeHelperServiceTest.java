package com.xs.aicodehelper.ai;

import dev.langchain4j.service.Result;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("local")
class AICodeHelperServiceTest {
    @Resource
    private AICodeHelperService aICodeHelperService;


    @Test
    void chat() {
        String result = aICodeHelperService.chat("你好，我是程序员地瓜瓜");
        System.out.println(result);
    }
    @Test
    void chatWithMemory() {
        String result = aICodeHelperService.chat("你好，我是程序员地瓜瓜");
        System.out.println(result);
        result = aICodeHelperService.chat("你好，我是谁");
        System.out.println(result);
    }

    @Test
    void chatForReport() {
        String userMessage="你好，我是程序员地瓜瓜。请帮我制定学习计划。";
        AICodeHelperService.Report report = aICodeHelperService.chatForReport(userMessage);
        System.out.println(report);
    }

    @Test
    void chatWithRAG() {
        Result<String> result = aICodeHelperService.chatWithRAG("怎么学习Java,有哪些常见面试题");
        System.out.println(result.sources());
        System.out.println(result.content());
    }

    @Test
    void chatWithTools() {
        String result = aICodeHelperService.chat("有哪些常见的计算机网络面试题？");
        System.out.println(result);

    }

    @Test
    void chatWithMcp() {
        String result = aICodeHelperService.chat("什么是程序员鱼皮的编程导航？");
        System.out.println(result);

    }

    @Test
    void chatWithGuardrail() {
        String result = aICodeHelperService.chat("kill the game");
        System.out.println(result);

    }
}