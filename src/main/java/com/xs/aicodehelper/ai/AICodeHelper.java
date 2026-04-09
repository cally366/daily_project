package com.xs.aicodehelper.ai;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.request.ResponseFormat;
import dev.langchain4j.model.chat.request.json.JsonObjectSchema;
import dev.langchain4j.model.chat.request.json.JsonSchema;
import dev.langchain4j.model.chat.response.ChatResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static dev.langchain4j.model.chat.request.ResponseFormatType.JSON;

@Service
@Slf4j
public class AICodeHelper {

    @Resource
    private ChatModel qwenChatModel;


    private static final String SYSTEM_MESSAGE = """
        你是编程领域的小助手，帮助用户解答编程学习和求职面试相关的问题，并给出建议。重点关注 4 个方向：
        1. 规划清晰的编程学习路线
        2. 提供项目学习建议
        3. 给出程序员求职全流程指南（比如简历优化、投递技巧）
        4. 分享高频面试题和面试技巧
        请用简洁易懂的语言回答，助力用户高效学习与求职。
        """;

    ResponseFormat responseFormat = ResponseFormat.builder()
            .type(JSON)
            .jsonSchema(JsonSchema.builder()
                    .name("Report")
                    .rootElement(JsonObjectSchema.builder()
                            .addStringProperty("name")
                            .addStringProperty("suggestionList") // 用字符串存数组
                            .required("name", "suggestionList")
                            .build())
                    .build())
            .build();

    public String chat(String message){
        SystemMessage systemMessage=SystemMessage.from(SYSTEM_MESSAGE);
        UserMessage userMessage = UserMessage.from(message);
        ChatResponse chatResponse=qwenChatModel.chat(systemMessage,userMessage);
        AiMessage aiMessage=chatResponse.aiMessage();
        log.info("AI输出: "+aiMessage.toString());
        return aiMessage.text();
    }

    public String chatWithMessage(UserMessage userMessage){
        ChatResponse chatResponse=qwenChatModel.chat(userMessage);
        AiMessage aiMessage=chatResponse.aiMessage();
        log.info("AI输出: "+aiMessage.toString());
        return aiMessage.text();
    }

    public String chatWithConstruction(UserMessage userMessage) throws JsonProcessingException {
        ChatRequest chatRequest = ChatRequest.builder()
                .responseFormat(responseFormat)
                .messages(userMessage)
                .build();

        ChatResponse chatResponse=qwenChatModel.chat(chatRequest);
        AiMessage aiMessage=chatResponse.aiMessage();
        String output=aiMessage.text();
        ObjectMapper mapper = new ObjectMapper();
        AICodeHelperService.Report report = mapper.readValue(output, AICodeHelperService.Report.class);
        System.out.println(report);
        log.info("AI输出: "+output);
        return output;
    }




}
