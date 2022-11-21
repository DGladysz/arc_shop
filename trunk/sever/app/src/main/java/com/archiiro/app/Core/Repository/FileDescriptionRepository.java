package com.archiiro.app.Core.Repository;

import com.archiiro.app.Core.Domain.FileDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDescriptionRepository extends JpaRepository<FileDescription, Long> {
}
