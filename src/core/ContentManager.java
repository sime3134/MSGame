package core;

import utils.ImgUtils;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ContentManager {
    private Map<String, Image> sprites;

    public ContentManager() {
        sprites = new HashMap<>();
        loadContent();
    }

    private void loadContent() {
        loadSprites("/sprites");
    }

    private void loadSprites(String filePath) {
        String[] imagesInFolder = getImagesInFolder(filePath);

        for(String fileName : imagesInFolder) {
            sprites.put(fileName.substring(0, fileName.length() - 4),
                    ImgUtils.loadImage(filePath + "/" + fileName));
        }
    }

    private String[] getImagesInFolder(String filePath) {
        URL resource = ContentManager.class.getResource(filePath);
        File file = new File(resource.getFile());
        return file.list((current, name) -> new File(current, name).isFile());
    }

    public Image getSprite(String name){
        return sprites.get(name);
    }
}
