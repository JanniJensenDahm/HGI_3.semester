package dk.hgigym.repository;

import dk.hgigym.model.User;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<User, Long> {
}
