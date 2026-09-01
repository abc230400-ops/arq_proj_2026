package com.trokr.repository;

import com.trokr.model.Item;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

 List<Item> findByDataCriacaoBetween(LocalDateTime inicio, LocalDateTime fim);
 List<Item> findByNome(String nome);
 
}
