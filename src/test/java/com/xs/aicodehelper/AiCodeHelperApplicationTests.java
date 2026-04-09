package com.xs.aicodehelper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xs.aicodehelper.ai.AICodeHelper;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
class AiCodeHelperApplicationTests {
    @Resource
    private AICodeHelper aiCodeHelper;


    @Test
    void chat() {
        aiCodeHelper.chat("你好！我是程序员地瓜瓜");
    }

    @Test
    void chatWithMessage() {
        UserMessage userMessage=UserMessage.from(
                TextContent.from("描述图片"),
                ImageContent.from("https://c-ssl.duitang.com/uploads/blog/202208/03/20220803190422_38404.jpg")
        );
        aiCodeHelper.chatWithMessage(userMessage);
    }

    @Test
    void chatWithConstruction() throws JsonProcessingException {
        UserMessage userMessage=UserMessage.from("你好！我是程序员地瓜瓜。可以给我一些学习建议吗");
        aiCodeHelper.chatWithConstruction(userMessage);
    }
}
