package org.master_panel.master_panel.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.master_panel.master_panel.model.Action;
import org.master_panel.master_panel.model.Monster;
import org.master_panel.master_panel.model.Trait;
import org.master_panel.master_panel.service.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/monsters")
public class MonstersController {

    @Autowired
    private MonsterService monsterService;

    @GetMapping
    public String index(Model model, @RequestParam(required = false) String name,
            @RequestParam(required = false) String order) {
        List<Monster> monsters = monsterService.getAll(name, order);

        model.addAttribute("monsters", monsters);
        model.addAttribute("name", name);
        model.addAttribute("order", order);
        return "monsters/index";
    }

    @GetMapping("/monstersByLevelAsc")
    private String byLevelAsc(Model model) {
        List<Monster> monsters = monsterService.getAllByLevelAsc();
        model.addAttribute("monsters", monsters);
        return "monsters/index";

    }

    @GetMapping("/monstersByLevelDesc")
    private String byLevelDesc(Model model) {
        List<Monster> monsters = monsterService.getAllByLevelDesc();
        model.addAttribute("monsters", monsters);
        return "monsters/index";

    }

    @GetMapping("/searchByName")
    public String searchName(@RequestParam(name = "name") String name, Model model) {
        List<Monster> pizzas = monsterService.findByNameContainig(name);

        model.addAttribute("monsters", pizzas);

        return "monsters/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Monster monster = monsterService.getById(id);

        model.addAttribute("monster", monster);

        return "monsters/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("monster", new Monster());
        return "monsters/create-or-edit";
    }

    @PostMapping("/create")
    public String storeWithImage(@Valid @ModelAttribute("monster") Monster formMonster, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "monsters/create-or-edit";
        }

        monsterService.create(formMonster);
        return "redirect:/monsters";
    }

    @GetMapping("/edit/{id}")
    public String editMonster(@PathVariable Integer id, Model model) {
        Monster monster = monsterService.getById(id);
        model.addAttribute("monster", monster);
        model.addAttribute("edit", true);

        return "monsters/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String updateMonster(@Valid @ModelAttribute("monster") Monster formMonster,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "monsters/create-or-edit";
        }

        monsterService.update(formMonster);

        return "redirect:/monsters";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        monsterService.deleteById(id);
        ;
        return "redirect:/monsters";
    }

    @GetMapping("/{id}/trait")
    public String trait(@PathVariable Integer id, Model model) {
        Trait trait = new Trait();
        trait.setMonster(monsterService.getById(id));

        model.addAttribute("trait", trait);

        return "trait/create-or-edit";
    }

    @GetMapping("/{id}/actions")
    public String actions(@PathVariable Integer id, Model model) {
        Action action = new Action();

        action.setMonster(monsterService.getById(id));

        model.addAttribute("action", action);

        return "actions/create-or-edit";
    }
}
