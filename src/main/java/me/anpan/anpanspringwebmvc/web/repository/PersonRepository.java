package me.anpan.anpanspringwebmvc.web.repository;

import me.anpan.anpanspringwebmvc.web.dto.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
