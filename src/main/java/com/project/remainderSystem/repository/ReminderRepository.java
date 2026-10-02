package com.project.remainderSystem.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.remainderSystem.model.Reminder;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {

	List<Reminder> findByStatusAndScheduledTimeLessThanEqual(String string, LocalDateTime now);

}
