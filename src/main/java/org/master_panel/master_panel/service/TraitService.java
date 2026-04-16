package org.master_panel.master_panel.service;

import java.util.Optional;

import org.master_panel.master_panel.model.Trait;
import org.master_panel.master_panel.repository.TraitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraitService {

    @Autowired
    private TraitRepository traitRepo;

    public Trait getById(Integer id) {
        Optional<Trait> trait = traitRepo.findById(id);

        if (trait.isEmpty()) {
            throw new RuntimeException("il tratto non è stato trovato");
        }

        return trait.get();
    }

    public Trait create(Trait trait) {
        return traitRepo.save(trait);
    }

    public Trait update(Trait trait) {
        return traitRepo.save(trait);
    }

    public void delete(Trait trait) {

        traitRepo.delete(trait);
    }

    public void deleteById(Integer id) {
        Trait trait = getById(id);
        traitRepo.delete(trait);
    }

}
