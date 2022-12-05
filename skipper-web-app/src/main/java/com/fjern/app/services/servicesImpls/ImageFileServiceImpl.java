package com.fjern.app.services.servicesImpls;

import com.fjern.app.persistence.entities.fileEntities.ImageDao;
import com.fjern.app.services.ImageFileService;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
@Service
public class ImageFileServiceImpl implements ImageFileService {

    @Override
    public void save(ImageDao imageDao) {
        File directory = new File(Path.of(imageDao.getDirectory(), imageDao.getId().toString()).toString());
        if (!directory.exists()){
            directory.mkdir();
        }
        File file = Path.of(String.valueOf(directory),
                imageDao.getName() +
                "." + imageDao.getExtension()).
                toFile();
        try {
            ImageIO.write(imageDao.getBufferedImage(), imageDao.getExtension(), file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
