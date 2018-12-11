package helpers;

import java.io.File;
import java.net.URL;
import java.util.Optional;

public class InputLoader {

    private final ClassLoader classLoader = getClass().getClassLoader();

    public URL getResource(int day) {
	return Optional.of("day" + day + "/input.txt")
	    .map(classLoader::getResource)
	    .orElse(null);
    }

    public File getResourceAsFile(int day) {
        return Optional.ofNullable(getResource(day))
	    .map(URL::getFile)
	    .map(File::new)
	    .orElse(null);
    }

}
