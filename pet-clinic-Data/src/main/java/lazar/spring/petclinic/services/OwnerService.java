package lazar.spring.petclinic.services;

import lazar.spring.petclinic.model.Owner;

import java.util.Set;

public interface OwnerService extends CrudService<Owner,Long>{
   Owner findByLastName(String lastName);
}
