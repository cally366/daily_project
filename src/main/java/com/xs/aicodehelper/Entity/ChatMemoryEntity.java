package com.xs.aicodehelper.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "chat_memory")
@Getter
@Setter
public class ChatMemoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 内存ID，对应用户ID或会话ID
    @Column(nullable = false)
    private String memoryId;

    // 消息
    @Lob
    @Column(nullable = false)
    private String messages;
}