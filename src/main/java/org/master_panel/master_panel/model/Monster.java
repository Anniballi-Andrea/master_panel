package org.master_panel.master_panel.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table
public class Monster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotNull(message = "il nome non deve essere vuoto")
    @NotBlank(message = "Il nome non va lasciato vuoto")
    private String name;

    @NotNull(message = "la CA non può essere vuota")

    @Min(1)
    private int armorClass;

    @NotNull(message = "la vita non può essere vuota")
    @Min(1)
    private int lifePoint;

    @Size(max = 50)
    @NotBlank(message = "la velocità non va lasciata vuota")
    private String moviment;

    @NotNull(message = "la forza non può essere vuota")
    @Min(-5)
    @Max(10)
    private int strength;

    @NotNull(message = "la destrezza non può essere vuota")
    @Min(-5)
    @Max(10)
    private int dexterity;

    @NotNull(message = "la costituzione non può essere vuota")
    @Min(-5)
    @Max(10)
    private int constitution;

    @NotNull(message = "l'inteligenza non può essere vuota")
    @Min(-5)
    @Max(10)
    private int intelligence;

    @NotNull(message = "la sagezza non può essere vuota")
    @Min(-5)
    @Max(10)
    private int wisdom;

    @NotNull(message = "il carisma non può essere vuoto")
    @Min(-5)
    @Max(10)
    private int charisma;

    @NotNull(message = "il grado di sfida è obbligatorio")
    @DecimalMin(value = "0.00", message = "il grado di sfida non può essere negativo")
    @DecimalMax(value = "30.00", message = "il limite al grado di sfida è 30")

    private Double level;

    @Size(max = 100)
    private String savingThrow;

    @Lob
    private String sense;

    @Lob
    private String skills;

    @Lob
    private String immunity;

    @Lob
    private String resistence;

    @Size(max = 100)
    private String image;

    @OneToMany(mappedBy = "monster", cascade = { CascadeType.REMOVE })
    private List<Trait> trait;

    @OneToMany(mappedBy = "monster", cascade = { CascadeType.REMOVE })
    private List<Action> actions;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArmorClass() {
        return this.armorClass;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    public int getLifePoint() {
        return this.lifePoint;
    }

    public void setLifePoint(int lifePoint) {
        this.lifePoint = lifePoint;
    }

    public String getMoviment() {
        return this.moviment;
    }

    public void setMoviment(String moviment) {
        this.moviment = moviment;
    }

    public int getStrength() {
        return this.strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return this.dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getConstitution() {
        return this.constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getIntelligence() {
        return this.intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWisdom() {
        return this.wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getCharisma() {
        return this.charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public String getSavingThrow() {
        return this.savingThrow;
    }

    public void setSavingThrow(String savingThrow) {
        this.savingThrow = savingThrow;
    }

    public String getSense() {
        return this.sense;
    }

    public void setSense(String sense) {
        this.sense = sense;
    }

    public String getSkills() {
        return this.skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getImmunity() {
        return this.immunity;
    }

    public void setImmunity(String immunity) {
        this.immunity = immunity;
    }

    public String getResistence() {
        return this.resistence;
    }

    public void setResistence(String resistence) {
        this.resistence = resistence;
    }

    public Double getLevel() {
        return this.level;
    }

    public void setLevel(Double level) {
        this.level = level;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Trait> getTrait() {
        return this.trait;
    }

    public void setTrait(List<Trait> trait) {
        this.trait = trait;
    }

    public List<Action> getActions() {
        return this.actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    @Override

    public String toString() {
        return String.format(
                "id: %d nome: %s ca: %d pf: %d movimento: %s forza: %d destrezza: %d costituzione: %d inteligenza: %d sagezza: %d carisma: %d tiri salvezza: %s sensi: %s abilità: %s immunità: %s resistenze: %s grado di sfida: %f immagine: %s",
                id, name, armorClass, lifePoint,
                moviment,
                strength, dexterity, constitution, intelligence, wisdom, charisma, savingThrow, sense, skills, immunity,
                resistence, level, image);
    }

}
