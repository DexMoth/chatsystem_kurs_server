package com.edu.chatsystem.repository;

import com.edu.chatsystem.model.ChatEntity;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<ChatEntity, Long> {
    @Query("SELECT DISTINCT c FROM ChatEntity c JOIN c.members m WHERE m.id = :userId")
    List<ChatEntity> findByUserId(@Param("userId") Long userId);
    @Query("SELECT c FROM ChatEntity c LEFT JOIN FETCH c.members WHERE c.id = :id")
    Optional<ChatEntity> findByIdWithMembers(@Param("id") Long id);

    @Query("select c from ChatEntity c join fetch c.members m")
    List<ChatEntity> findAll();
}