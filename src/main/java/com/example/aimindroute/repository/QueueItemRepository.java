package com.example.aimindroute.repository;

import com.example.aimindroute.entity.queue.QueueItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueueItemRepository extends JpaRepository<QueueItem, Long> {
}
