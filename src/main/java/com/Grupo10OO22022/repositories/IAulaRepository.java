package com.Grupo10OO22022.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Grupo10OO22022.entities.Aula;

@Repository("aulaRepository")
public interface IAulaRepository extends  JpaRepository<Aula, Integer> {

}
