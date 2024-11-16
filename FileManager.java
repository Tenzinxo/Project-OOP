import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
	
	public static final String[] SupportedExtensions = {".jpeg", ".jpg", ".png",".webp"};
	public static final String[] SupportedMimetypes = {"image/jpeg", "image/jpg", "image/png","image/webp"};

	
	public static List<File> listImageFiles(String dirPATH){
		List<File> imagefile = new ArrayList<>();
		File directory = new File(dirPATH);
		
		if (!directory.isDirectory()) {
			throw new IllegalArgumentException("le chemin fourni n'est pas un repertoire!");
		}
		
		for(File file: directory.listFiles()) {
			if (isSupported(file)) {
				imagefile.add(file);
			}
		}		
		return imagefile;
	}
	
	
	public static boolean isSupported(File file) {
		String name = file.getName().toLowerCase();
		boolean extensionvalid = false;
		for (String ext : SupportedExtensions) {
			if(name.endsWith(ext)) {
				extensionvalid = true;
				break;
			}
		}
		if (!extensionvalid) {
			return false;
		}
		
		try {
			String MimeType = Files.probeContentType(file.toPath());
			if(MimeType != null) {
				for (String mime : SupportedMimetypes) {
					if (MimeType.equals(mime)) {
						return true;
					}
				}
				
			}
			
		} catch (IOException e){
			System.err.println("Erreur lors de la lecture de Mime type du" + file.getName());	
		}
		
		return false;
	}
	
	
	public static void printStatistics(List<File> files) {
		int jpegCount = 0, pngCount = 0, webpCount = 0;
		
		for (File file : files) {
			System.out.print(file.getName() + "\t");
			if(file.getName().endsWith(".jpeg")|| file.getName().endsWith(".jpg")) {
				jpegCount++;
			}
			if(file.getName().endsWith(".png")) {
				pngCount++;
			}
			if(file.getName().endsWith(".webp")) {
				webpCount++;
			}
		}
		System.out.println("\nNombre total du fichier: " + files.size());
		System.out.println("JPEG fichier: " + jpegCount);
		System.out.println("PNG fichier: " + pngCount);
		System.out.println("WEBP fichier: " + webpCount);
	}
	
	
	public static void main(String[] args) {
	    try {
	        List<File> files = listImageFiles("F:\\zz']@!\\KPOP");
	        printStatistics(files);
	    } catch (Exception e) {
	        System.err.println("Error: " + e.getMessage());
	    }
	}
}
