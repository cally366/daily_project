package com.xs.aicodehelper.ai;
import com.xs.aicodehelper.ai.tools.InterviewQuestionTool;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import dev.langchain4j.store.memory.chat.InMemoryChatMemoryStore;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class AICodeHelperServiceFactory {

    @Resource
    private ChatModel qwenChatModel;

    @Resource
    private ChatModel myQwenChatModel;

    @Resource
    private ContentRetriever contentRetriever;

    @Resource
    private McpToolProvider mcpToolProvider;

    @Resource
    private StreamingChatModel qwenStreamingChatModel;


    @Bean
    public AICodeHelperService aICodeHelperService() {
//支持区分用户的会话记忆
//        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
        ChatMemoryStore store = new InMemoryChatMemoryStore();
        //构造AIservice
        AICodeHelperService aiCodeHelperService = AiServices.builder(AICodeHelperService.class)
                .chatModel(myQwenChatModel)
                .streamingChatModel(qwenStreamingChatModel)
//                .chatMemory(chatMemory)
                .chatMemoryProvider(memoryId ->
                        MessageWindowChatMemory.builder()
                                .id(memoryId)
                                .maxMessages(10)
                                .chatMemoryStore(store)
                                .build()
                )
                .contentRetriever(contentRetriever)
                .tools(new InterviewQuestionTool())
                .toolProvider(mcpToolProvider)
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
