package com.jsp.ets.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RatingRepository extends JpaRepository<Rating, String> {

}
