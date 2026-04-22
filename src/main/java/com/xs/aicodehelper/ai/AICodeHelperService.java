package com.xs.aicodehelper.ai;
import com.xs.aicodehelper.ai.guardRail.SafeInputGuardrail;
import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.Result;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.guardrail.InputGuardrails;
import reactor.core.publisher.Flux;

import java.util.List;

//import dev.langchain4j.service.spring.AiService;
//
//@AiService
@InputGuardrails(SafeInputGuardrail.class)
public interface AICodeHelperService {



    @SystemMessage(fromResource = "system-prompt.txt")
    String chat( String message);

    @SystemMessage(fromResource = "system-prompt.txt")
    Report chatForReport(String userMessage);

    record Report(String name, List<String> suggestionList) {}


    @SystemMessage(fromResource = "system-prompt.txt")
    Result<String> chatWithRAG(String message);

    Flux<String> chatStream(@MemoryId int memoryId, @UserMessage String userMessage);

}
