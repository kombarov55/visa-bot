package com.example.samplebot.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.example.samplebot.data.UserVO;

public interface UserRepository extends CrudRepository<UserVO, UUID> {
    Optional<UserVO> findByChatId(Long chatId);
    boolean existsByChatId(Long chatId);
}
