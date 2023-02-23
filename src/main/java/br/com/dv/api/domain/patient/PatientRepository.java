package br.com.dv.api.domain.patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Page<Patient> findAllByIsActiveIsTrue(Pageable pageable);

    @Query(value = """
            SELECT is_active FROM patients
            WHERE id = :patientId
            """, nativeQuery = true)
    Boolean findIsActiveById(Long patientId);

}
