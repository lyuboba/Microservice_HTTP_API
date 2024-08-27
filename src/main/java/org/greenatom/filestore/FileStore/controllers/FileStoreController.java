package org.greenatom.filestore.FileStore.controllers;

import jakarta.validation.Valid;
import org.greenatom.filestore.FileStore.dto.FileDTO;
import org.greenatom.filestore.FileStore.models.File;
import org.greenatom.filestore.FileStore.services.FIleService;
import org.greenatom.filestore.FileStore.utill.FileErrorResponse;
import org.greenatom.filestore.FileStore.utill.FileNotCreatedException;
import org.greenatom.filestore.FileStore.utill.FileNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/file_store")
public class FileStoreController {
    private final FIleService fileService;
    private final ModelMapper modelMapper;

    @Autowired
    public FileStoreController(final FIleService fileService,final ModelMapper modelMapper) {
        this.fileService = fileService;
        this.modelMapper = modelMapper;
    }


    @PostMapping()
    public int createFile(@RequestBody @Valid FileDTO fileDTO,
                                                 BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            for(FieldError error: bindingResult.getFieldErrors())
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";  ");

            throw new FileNotCreatedException(errorMsg.toString());
        }
        return fileService.save(convertToFile(fileDTO));
    }

    @GetMapping()
    public List<FileDTO> getAllFilesOrderByCreationDate() {
        return fileService.findAllOrderByCreationDate().stream()
                .map(this::convertToFileDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public FileDTO getFile(@PathVariable("id") int id){
        return convertToFileDTO(fileService.findOne(id));
    }


    private File convertToFile(FileDTO fileDTO) {
        return modelMapper.map(fileDTO, File.class);
    }

    private FileDTO convertToFileDTO(File file){
        return modelMapper.map(file, FileDTO.class);
    }


    @ExceptionHandler
    private ResponseEntity<FileErrorResponse> handleException(FileNotFoundException ex){
        FileErrorResponse responseError = new FileErrorResponse(
                "Файл с заданным id не найден!",
                LocalDateTime.now()
        );

        return new ResponseEntity<>(responseError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<FileErrorResponse> handleException(FileNotCreatedException ex){
        FileErrorResponse responseError = new FileErrorResponse(
                ex.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }
}
