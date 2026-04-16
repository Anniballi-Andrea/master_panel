package org.master_panel.master_panel.controllers;

import java.util.List;
import java.util.Optional;

import org.master_panel.master_panel.model.Monster;
import org.master_panel.master_panel.service.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/monsters")
public class MonsterRestController {

    @Autowired
    private MonsterService monsterService;

    @GetMapping()
    public List<Monster> index() {
        List<Monster> monsters = monsterService.getAll();

        return monsters;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Monster> show(@PathVariable Integer id) {
        Optional<Monster> monsterAttempt = monsterService.findById(id);
        if (monsterAttempt.isEmpty()) {
            return new ResponseEntity<Monster>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Monster>(monsterAttempt.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Monster> store(@RequestBody Monster monster) {
        return new ResponseEntity<Monster>(monsterService.create(monster), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Monster> update(@RequestBody Monster monster, @PathVariable Integer id) {
        if (monsterService.findById(id).isEmpty()) {
            return new ResponseEntity<Monster>(HttpStatus.OK);
        }

        monster.setId(id);
        return new ResponseEntity<Monster>(monsterService.update(monster), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Monster> delete(@PathVariable Integer id) {
        if (monsterService.findById(id).isEmpty()) {
            return new ResponseEntity<Monster>(HttpStatus.NOT_FOUND);
        }
        monsterService.deleteById(id);
        return new ResponseEntity<Monster>(HttpStatus.OK);
    }

}
