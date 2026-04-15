package org.master_panel.master_panel.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.master_panel.master_panel.model.Action;
import org.master_panel.master_panel.model.Monster;
import org.master_panel.master_panel.model.Trait;
import org.master_panel.master_panel.repository.MonsterRepository;
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
    private MonsterRepository repo;

    @GetMapping
    public String index(Model model) {
        List<Monster> monsters = repo.findAll();

        model.addAttribute("monsters", monsters);
        return "monsters/index";
    }

    @GetMapping("/monstersByLevelAsc")
    private String byLevelAsc(Model model) {
        List<Monster> monsters = repo.findAllByOrderByLevelAsc();
        model.addAttribute("monsters", monsters);
        return "monsters/index";

    }

    @GetMapping("/monstersByLevelDesc")
    private String byLevelDesc(Model model) {
        List<Monster> monsters = repo.findAllByOrderByLevelDesc();
        model.addAttribute("monsters", monsters);
        return "monsters/index";

    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Monster monster = repo.findById(id).get();

        model.addAttribute("monster", monster);

        return "monsters/monsterPage";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("monster", new Monster());
        return "monsters/create-or-edit";
    }

    @PostMapping("/create")
    public String storeWithImage(@Valid @ModelAttribute("monster") Monster formMonster, BindingResult bindingResult,
            @RequestParam(name = "imageFile", required = false) MultipartFile file, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("-------------------------------------------");
            System.out.println("-------------------------------------------");
            bindingResult.getAllErrors().forEach(System.out::println);
            return "monsters/create-or-edit";
        }

        if (!file.isEmpty()) {
            try {
                String uploadDir = "src/main/resources/static/images/";
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

                Path uploadPath = Paths.get(uploadDir);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                System.out.println("Salvataggio in corso in: " + uploadPath.toAbsolutePath());

                Path filePath = uploadPath.resolve(fileName);

                Files.write(filePath, file.getBytes());

                formMonster.setImage(fileName);

            } catch (IOException e) {
                System.err.println("Errore fatale di I/O: " + e.getMessage());
                e.printStackTrace();

            }
        }

        repo.save(formMonster);
        return "redirect:/monsters";
    }

    @GetMapping("/edit/{id}")
    public String editMonster(@PathVariable Integer id, Model model) {
        model.addAttribute("monster", repo.findById(id).get());
        model.addAttribute("edit", true);
        return "monsters/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String updateMonster(@Valid @ModelAttribute("monster") Monster formMonster, BindingResult bindingResult,
            Model model, @RequestParam(name = "imageFile", required = false) MultipartFile file) {

        if (bindingResult.hasErrors()) {
            return "monsters/create-or-edit";
        }

        if (!file.isEmpty()) {
            try {
                String uploadDir = "src/main/resources/static/images/";
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

                Path uploadPath = Paths.get(uploadDir);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                System.out.println("Salvataggio in corso in: " + uploadPath.toAbsolutePath());

                Path filePath = uploadPath.resolve(fileName);

                Files.write(filePath, file.getBytes());

                formMonster.setImage(fileName);

            } catch (IOException e) {
                System.err.println("Errore fatale di I/O: " + e.getMessage());
                e.printStackTrace();

            }
        }

        repo.save(formMonster);

        return "redirect:/monsters";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        repo.deleteById(id);
        return "redirect:/monsters";
    }

    @GetMapping("/{id}/trait")
    public String trait(@PathVariable Integer id, Model model) {
        Trait trait = new Trait();
        trait.setMonster(repo.findById(id).get());

        model.addAttribute("trait", trait);

        return "trait/createTrait";
    }

    @GetMapping("/{id}/actions")
    public String actions(@PathVariable Integer id, Model model) {
        Action action = new Action();

        action.setMonster(repo.findById(id).get());

        model.addAttribute("action", action);

        return "actions/createActions";
    }
}
