package de.wallbanger.util;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageUtils {
	
	public static BufferedImage fromFile(String path) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(path));
		} catch (Exception e) {
			e.getStackTrace();
		}
		return image;
	}
}
