package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.Feedback;

public interface FeedbackDao extends JpaRepository<Feedback, Long> {
	
	@Query("select f from Feedback f where f.business.id=:id")
	List<Feedback> getfeedback(Long id);

}
