package com.xs.aicodehelper.ai;
import dev.langchain4j.service.SystemMessage;

import java.util.List;

//import dev.langchain4j.service.spring.AiService;
//
//@AiService
public interface AICodeHelperService {



    @SystemMessage(fromResource = "system-prompt.txt")
    String chat( String message);

    @SystemMessage(fromResource = "system-prompt.txt")
    Report chatForReport(String userMessage);

    record Report(String name, List<String> suggestionList) {}


}
