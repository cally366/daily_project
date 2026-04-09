package com.xs.aicodehelper.ai;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("local")
class AICodeHelperPersistentServiceTest {
    @Resource
    AICodeHelperPersistentService aiCodeHelperPersistentService;

    @Test
    void chat() {
        aiCodeHelperPersistentService.chat("user-001", "你好");
        aiCodeHelperPersistentService.chat("user-001", "继续刚才的话题");
        aiCodeHelperPersistentService.chat("user-002", "我是另一个用户");
    }

}