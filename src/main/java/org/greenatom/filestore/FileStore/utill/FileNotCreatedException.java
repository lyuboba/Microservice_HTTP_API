package org.greenatom.filestore.FileStore.utill;

public class FileNotCreatedException extends RuntimeException{
    public FileNotCreatedException(String msg){
        super(msg);
    }
}
