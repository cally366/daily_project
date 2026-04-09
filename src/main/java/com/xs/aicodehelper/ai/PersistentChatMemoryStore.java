package com.xs.aicodehelper.ai;

import com.xs.aicodehelper.Entity.ChatMemoryEntity;
import com.xs.aicodehelper.repository.ChatMemoryRepository;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ChatMessageDeserializer;
import dev.langchain4j.data.message.ChatMessageSerializer;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PersistentChatMemoryStore implements ChatMemoryStore {

    private final ChatMemoryRepository repository;

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        return repository.findByMemoryId(memoryId.toString())
                .map(entity -> ChatMessageDeserializer.messagesFromJson(entity.getMessages()))
                .orElse(List.of());
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> messages) {
        String json = ChatMessageSerializer.messagesToJson(messages);
        ChatMemoryEntity entity = repository.findByMemoryId(memoryId.toString())
                .orElseGet(() -> {
                    ChatMemoryEntity e = new ChatMemoryEntity();
                    e.setMemoryId(memoryId.toString());
                    return e;
                });
        entity.setMessages(json);
        repository.save(entity);
    }

    @Override
    public void deleteMessages(Object memoryId) {
        repository.deleteByMemoryId(memoryId.toString());
    }
}