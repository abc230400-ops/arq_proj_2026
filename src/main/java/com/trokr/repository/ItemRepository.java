package com.trokr.repository;

import com.trokr.model.Item;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

 public void findByDataCriacaoBetween(LocalDateTime inicio, LocalDateTime fim);
 public void findByNome(String nome);
 
}
