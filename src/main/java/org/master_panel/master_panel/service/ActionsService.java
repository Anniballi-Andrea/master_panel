package org.master_panel.master_panel.service;

import java.util.Optional;

import org.master_panel.master_panel.model.Action;
import org.master_panel.master_panel.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActionsService {

    @Autowired
    private ActionRepository actionRepo;

    public Action getById(Integer id) {
        Optional<Action> actions = actionRepo.findById(id);

        if (actions.isEmpty()) {
            throw new RuntimeException("l'azione non è stata trovata");
        }
        return actions.get();
    }

    public Action create(Action action) {
        return actionRepo.save(action);
    }

    public Action update(Action action) {
        return actionRepo.save(action);
    }

    public void delete(Action action) {
        actionRepo.delete(action);
    }

    public void deleteById(Integer id) {
        Action action = getById(id);

        actionRepo.delete(action);
    }

}
