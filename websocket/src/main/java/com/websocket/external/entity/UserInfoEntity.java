package com.websocket.external.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@Table(name = "user_info")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private ImageDataEntity image;

    @ElementCollection
    @CollectionTable(name = "user_messages", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "message")
    private List<String> messages;


}
