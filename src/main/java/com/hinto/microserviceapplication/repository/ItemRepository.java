package com.hinto.microserviceapplication.repository;

import com.hinto.microserviceapplication.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
