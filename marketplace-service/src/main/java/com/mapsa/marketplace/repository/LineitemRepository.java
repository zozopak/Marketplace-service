package com.mapsa.marketplace.repository;

import com.mapsa.marketplace.model.Lineitem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineitemRepository extends JpaRepository <Lineitem,Long> {

}
