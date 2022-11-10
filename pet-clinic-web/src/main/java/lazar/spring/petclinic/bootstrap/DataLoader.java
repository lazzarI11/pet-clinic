package lazar.spring.petclinic.bootstrap;

import lazar.spring.petclinic.model.*;
import lazar.spring.petclinic.services.OwnerService;
import lazar.spring.petclinic.services.PetTypeService;
import lazar.spring.petclinic.services.SpecialityService;
import lazar.spring.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();
        if(count == 0) {
            loadData();
        }
    }

    private void loadData() {
        //ADDING PET TYPES

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        //ADDING SPECIALTIES

        Speciality radiology = new Speciality();
        radiology.setDescription("radiology");

        Speciality surgery = new Speciality();
        surgery.setDescription("surgery");

        Speciality dentistry = new Speciality();
        dentistry.setDescription("dentistry");

        Speciality savedRadiology = specialityService.save(radiology);
        Speciality savedSurgery = specialityService.save(surgery);
        Speciality savedDentistry =specialityService.save(dentistry);

        //CREATING AND SAVING OWNERS

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAdress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("0603123000");

        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Jacky");
        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner1.setAdress("123 WallStreet");
        owner1.setCity("New York");
        owner1.setTelephone("0623123022");

        //CREATING AND SETTING OWNERS TO PETS

        Pet fionasPet = new Pet();
        fionasPet.setPetType(savedCatPetType);
        fionasPet.setOwner(owner2);
        fionasPet.setBirthDate(LocalDate.now());
        fionasPet.setName("Lara");
        owner2.getPets().add(fionasPet);

        ownerService.save(owner2);

        System.out.println("Owners loaded...");

        //CREATING VETS AND ADDING SPECIALITIES TO THEM

        Vet vet1 = new Vet();
        vet1.setFirstName("John");
        vet1.setLastName("Cena");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Shawn");
        vet2.setLastName("Michaels");
        vet2.getSpecialities().add(savedRadiology);

        vetService.save(vet2);

        System.out.println("Vets loaded...");
    }
}
