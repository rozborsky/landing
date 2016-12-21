package ua.rozborsky.classes;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by roman on 19.12.2016.
 */

@Component
public class CvManager {

    public void saveImage(MultipartFile image, String dirPath)
            throws RuntimeException{
        try{
            System.out.println(dirPath + image.getOriginalFilename());
            File file = new File(dirPath + image.getOriginalFilename());
            FileUtils.writeByteArrayToFile(file, image.getBytes());
        } catch (IOException exception) {
            throw new RuntimeException();
        }
    }
}
