package org.master_panel.master_panel.repository;

import java.util.List;

import org.master_panel.master_panel.model.Monster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonsterRepository extends JpaRepository<Monster, Integer> {

    public List<Monster> findAllByOrderByLevelAsc();

    public List<Monster> findAllByOrderByLevelDesc();
}
