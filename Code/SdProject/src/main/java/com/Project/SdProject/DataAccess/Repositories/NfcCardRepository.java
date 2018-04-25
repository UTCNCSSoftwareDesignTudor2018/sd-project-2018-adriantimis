package com.Project.SdProject.DataAccess.Repositories;

import com.Project.SdProject.DataAccess.Entities.NfcCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NfcCardRepository extends JpaRepository<NfcCard, Integer> {
}
