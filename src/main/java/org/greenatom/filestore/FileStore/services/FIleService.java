package org.greenatom.filestore.FileStore.services;



import org.greenatom.filestore.FileStore.models.File;
import org.greenatom.filestore.FileStore.repositories.FileRepository;
import org.greenatom.filestore.FileStore.utill.FileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class FIleService {
    private final FileRepository fileRepository;

    @Autowired
    public FIleService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Transactional
    public int save(File file){
        fileRepository.save(file);
        return file.getId();
    }

    public File findOne(int id){
        return fileRepository.findById(id).orElseThrow(FileNotFoundException::new);
    }

    public List<File> findAllOrderByCreationDate(){
        return fileRepository.findAllByOrderByCreationDateAsc();
    }


}
