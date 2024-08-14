package com.app.dao;

import com.app.entity.OfferingReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferingReviewDao extends JpaRepository<OfferingReview, Long> {
    
}

