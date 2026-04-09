package com.xs.aicodehelper.ai;

import com.alibaba.dashscope.common.ResponseFormat;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Bean
    public ChatMemory chatMemory(PersistentChatMemoryStore store) {
        return MessageWindowChatMemory.builder()
                .id("user-123")
                .maxMessages(10)
                .chatMemoryStore(store)
                .build();
    }

}
