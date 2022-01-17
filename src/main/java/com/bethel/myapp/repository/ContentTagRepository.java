package com.bethel.myapp.repository;

import com.bethel.myapp.domain.ContentTag;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ContentTag entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContentTagRepository extends JpaRepository<ContentTag, Long> {}
