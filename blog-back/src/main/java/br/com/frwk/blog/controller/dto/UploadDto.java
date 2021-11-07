package br.com.frwk.blog.controller.dto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

/**
 * @author Cleber Leão
 */
public class UploadDto {

    private MultipartFile[] files;

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "UploadModel{" +
                ", files=" + Arrays.toString(files) +
                '}';
    }
}
