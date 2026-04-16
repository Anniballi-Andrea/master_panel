package org.master_panel.master_panel.controllers;

import org.master_panel.master_panel.model.Trait;
import org.master_panel.master_panel.service.TraitService;
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
@RequestMapping("/trait")
public class TraitController {

    @Autowired
    private TraitService traitService;

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("trait") Trait formTrait, Model model, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "trait/create-or-edit";
        }

        traitService.create(formTrait);

        return "redirect:/monsters/" + formTrait.getMonster().getId();
    }

    @GetMapping("/edit/{id}")
    public String editTrait(@PathVariable Integer id, Model model) {
        model.addAttribute("trait", traitService.getById(id));
        model.addAttribute("edit", true);

        return "trait/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid @ModelAttribute("trait") Trait formTrait, Model model, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "trait/create-or-edit";
        }

        traitService.update(formTrait);

        return "redirect:/monsters/" + formTrait.getMonster().getId();

    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        Trait traitToDelete = traitService.getById(id);

        Integer monsterId = traitToDelete.getMonster().getId();

        traitService.delete(traitToDelete);

        return "redirect:/monsters/" + monsterId;
    }
}
