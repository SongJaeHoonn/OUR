package app.Repository;

import app.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByAlias(String alias);
    Optional<Member> findByName(String name);
}
