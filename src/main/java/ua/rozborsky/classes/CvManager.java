package ua.rozborsky.classes;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ua.rozborsky.exceptions.LandingException;

import java.io.File;
import java.io.IOException;

/**
 * Created by roman on 19.12.2016.
 */

@Component
public class CvManager {

    public void saveCV(MultipartFile cv, String dirPath) throws LandingException{
        try{
            File file = new File(dirPath + cv.getOriginalFilename());
            FileUtils.writeByteArrayToFile(file, cv.getBytes());
        } catch (IOException exception) {
            throw new LandingException();
        }
    }
}
