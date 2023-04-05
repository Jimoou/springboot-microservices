package github.Jimoou.organizationservice.Repository;

import github.Jimoou.organizationservice.Entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
  Optional<Organization> findByOrganizationCode(String organizationCode);
}
