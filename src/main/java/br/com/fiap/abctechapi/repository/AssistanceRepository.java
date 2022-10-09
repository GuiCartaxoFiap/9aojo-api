package br.com.fiap.abctechapi.repository;

import br.com.fiap.abctechapi.model.Assistance;

import java.util.List;

import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssistanceRepository extends JpaRepository<Assistance, Long> {

    @ReadOnlyProperty
    List<Assistance> findByIdIn(List<Long> assignments);
}
