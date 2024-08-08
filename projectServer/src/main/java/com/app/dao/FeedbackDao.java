package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Feedback;

public interface FeedbackDao extends JpaRepository<Feedback, Long> {

}
