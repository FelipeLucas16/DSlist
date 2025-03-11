package com.devffl.dslist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devffl.dslist.entities.Belonging;
import com.devffl.dslist.entities.BelongingPK;

public interface BelongingRepository extends JpaRepository<Belonging, BelongingPK> {
   
}
