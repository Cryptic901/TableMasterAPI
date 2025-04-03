package com.tablemaster_api.abstraction.repository;

import com.tablemaster_api.entity.Tables;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TablesRepository extends JpaRepository<Table, Long> {
}
