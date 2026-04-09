package com.xs.aicodehelper.repository;


import com.xs.aicodehelper.Entity.ChatMemoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatMemoryRepository extends JpaRepository<ChatMemoryEntity, Long> {
    Optional<ChatMemoryEntity> findByMemoryId(String memoryId);
    void deleteByMemoryId(String memoryId);
}