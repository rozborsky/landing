package ua.rozborsky.classes;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by roman on 19.12.2016.
 */
public class CvManager {

    final String DIR_WITH_IMAGES = "C:/Users/roman/IdeaProjects/landingPage/src/main/java/ua/rozborskyRoman/images/";

    public void validateImage(MultipartFile image) {
        if (!image.getContentType().equals("image/jpeg")) {
            throw new RuntimeException();
        }
    }

    public void saveImage(String fileName, MultipartFile image)
            throws RuntimeException{
        try{
            File file = new File(DIR_WITH_IMAGES + fileName);
            FileUtils.writeByteArrayToFile(file, image.getBytes());
        } catch (IOException exception) {
            throw new RuntimeException();
        }




//
//
//        try {
//            UploadItem uploadItem
//            MultipartFile file = uploadItem.getFileData();
//            String fileName = null;
//            InputStream inputStream = null;
//            OutputStream outputStream = null;
//            if (file.getSize() > 0) {
//                inputStream = file.getInputStream();
//                if (file.getSize() > 10000) {
//                    System.out.println("File Size:::" + file.getSize());
//                    return "/uploadfile";
//                }
//                System.out.println("size::" + file.getSize());
//                fileName = request.getRealPath("") + "/images/"
//                        + file.getOriginalFilename();
//                outputStream = new FileOutputStream(fileName);
//                System.out.println("fileName:" + file.getOriginalFilename());
//
//                int readBytes = 0;
//                byte[] buffer = new byte[10000];
//                while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
//                    outputStream.write(buffer, 0, readBytes);
//                }
//                outputStream.close();
//                inputStream.close();
//            }
//
//            // ..........................................
//            session.setAttribute("uploadFile", file.getOriginalFilename());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }











    }
}
