package com.xs.aicodehelper.ai;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

import java.util.List;

//import dev.langchain4j.service.spring.AiService;
//
//@AiService
public interface AICodeHelperPersistentService {
    @SystemMessage(fromResource = "system-prompt.txt")
    String chat(@MemoryId String userId, @UserMessage String message);

}

