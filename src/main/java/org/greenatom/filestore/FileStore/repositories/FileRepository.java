package org.greenatom.filestore.FileStore.repositories;

import org.greenatom.filestore.FileStore.models.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {
    List<File> findAllByOrderByCreationDateAsc();

}
