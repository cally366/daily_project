package com.xs.aicodehelper.ai;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AICodeHelperServiceFactory {

    @Resource
    private ChatModel qwenChatModel;

    @Bean
    public AICodeHelperService aICodeHelperService() {
//支持区分用户的会话记忆
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
        //构造AIservice
        AICodeHelperService aiCodeHelperService = AiServices.builder(AICodeHelperService.class)
                .chatModel(qwenChatModel)
                .chatMemory(chatMemory)
                .build();
        return aiCodeHelperService;

    }
    @Bean
    public AICodeHelperPersistentService aICodeHelperPersistentService(PersistentChatMemoryStore store) {

        return AiServices.builder(AICodeHelperPersistentService.class)
                .chatModel(qwenChatModel)
                .chatMemoryProvider(memoryId ->
                        MessageWindowChatMemory.builder()
                                .id(memoryId)
                                .maxMessages(10)
                                .chatMemoryStore(store) // 你实现的数据库存储
                                .build()
                )
                .build();
    }
}
