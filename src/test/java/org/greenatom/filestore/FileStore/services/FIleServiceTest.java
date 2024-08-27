package org.greenatom.filestore.FileStore.services;

import org.greenatom.filestore.FileStore.models.File;
import org.greenatom.filestore.FileStore.repositories.FileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FIleServiceTest {
    private static final int ID = 1;

    @Mock
    private FileRepository repository;

    @InjectMocks
    private FIleService service;


    @Test
    void save_shouldCallRepository() {
        final File file = mock(File.class);

        service.save(file);

        verify(repository).save(file);
    }

    @Test
    void findOne_shouldCallRepository() {
        final File file = mock(File.class);
        when(repository.findById(ID)).thenReturn(Optional.of(file));

        final File actual = service.findOne(ID);

        assertNotNull(actual);
        assertEquals(file, actual);
        verify(repository).findById(ID);
    }

    @Test
    void findAllOrderByCreationDate_shouldCallRepository() {
        final File file1 = mock(File.class);
        final File file2 = mock(File.class);
        List<File> files = new ArrayList<>(List.of(file1, file2));
        when(repository.findAllByOrderByCreationDateAsc()).thenReturn(files);

        final List<File> actual = service.findAllOrderByCreationDate();

        assertNotNull(actual);
        assertEquals(files, actual);
        verify(repository).findAllByOrderByCreationDateAsc();
    }
}