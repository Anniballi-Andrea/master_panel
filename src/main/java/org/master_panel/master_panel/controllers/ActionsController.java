
package org.master_panel.master_panel.controllers;

import org.master_panel.master_panel.model.Action;
import org.master_panel.master_panel.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/actions")
public class ActionsController {

    @Autowired
    private ActionRepository repo;

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("action") Action formAction, Model model, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "actions/create-or-edit";
        }

        repo.save(formAction);

        return "redirect:/monsters/" + formAction.getMonster().getId();
    }

    @GetMapping("/edit/{id}")
    public String editAction(@PathVariable Integer id, Model model) {
        model.addAttribute("action", repo.findById(id).get());
        model.addAttribute("edit", true);

        return "actions/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid @ModelAttribute("action") Action formAction, Model model, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "actions/create-or-edit";
        }

        repo.save(formAction);

        return "redirect:/monsters/" + formAction.getMonster().getId();

    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        Action actionToDelete = repo.findById(id).get();

        Integer monsterId = actionToDelete.getMonster().getId();

        repo.delete(actionToDelete);

        return "redirect:/monsters/" + monsterId;
    }
}