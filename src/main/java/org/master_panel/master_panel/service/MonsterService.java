package org.master_panel.master_panel.service;

import java.util.List;
import java.util.Optional;

import org.master_panel.master_panel.model.Monster;
import org.master_panel.master_panel.repository.MonsterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonsterService {

    @Autowired
    private MonsterRepository monsterRepo;

    public List<Monster> getAll() {
        return monsterRepo.findAll();
    }

    public List<Monster> getAll(String name, String order) {

        if ((name == null || name.isBlank()) && (order == null || order.isBlank())) {

            return monsterRepo.findAll();
        } else if ((order == null || order.isBlank())) {

            return monsterRepo.findByNameContaining(name);

        } else if ((name == null || name.isBlank()) && order.equals("asc")) {

            return monsterRepo.findAllByNameContainingOrderByLevelAsc(name);

        } else if ((name == null || name.isBlank()) && order.equals("desc")) {
            return monsterRepo.findAllByNameContainingOrderByLevelDesc(name);

        } else if (order.equals("asc")) {
            return monsterRepo.findAllByNameContainingOrderByLevelAsc(name);
        }
        return monsterRepo.findAllByNameContainingOrderByLevelDesc(name);

    }

    public List<Monster> getAllByNameAsc() {
        return monsterRepo.findAllByOrderByNameAsc();
    }

    public List<Monster> getAllByLevelAsc() {
        return monsterRepo.findAllByOrderByLevelAsc();
    }

    public List<Monster> getAllByLevelDesc() {
        return monsterRepo.findAllByOrderByLevelDesc();
    }

    public List<Monster> findByName(String name) {
        List<Monster> monsters = monsterRepo.findByNameContaining(name);

        return monsters;
    }

    public Optional<Monster> findById(Integer id) {
        return monsterRepo.findById(id);
    }

    public Monster getById(Integer id) {
        Optional<Monster> monsterAttempt = monsterRepo.findById(id);
        if (monsterAttempt.isEmpty()) {
            throw new RuntimeException("il mostro non è stato trovato");
        }
        return monsterAttempt.get();
    }

    public Monster create(Monster monster) {
        return monsterRepo.save(monster);
    }

    public Monster update(Monster monster) {
        return monsterRepo.save(monster);
    }

    public void delete(Monster monster) {

        monsterRepo.delete(monster);
    }

    public void deleteById(Integer id) {
        Monster monster = getById(id);
        monsterRepo.delete(monster);
    }

}
