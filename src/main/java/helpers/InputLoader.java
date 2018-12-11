package helpers;

import java.io.File;
import java.net.URL;
import java.util.Optional;

public class InputLoader {

    private final ClassLoader classLoader = getClass().getClassLoader();

    public File getResource(int day, int part) {
	return Optional.of("day" + day + "/part" + part +"_input.txt")
	    .map(classLoader::getResource)
	    .map(URL::getFile)
	    .map(File::new)
	    .orElse(null);
    }

}
