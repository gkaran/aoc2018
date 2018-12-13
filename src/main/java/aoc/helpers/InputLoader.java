package aoc.helpers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InputLoader {

    private final ClassLoader classLoader = getClass().getClassLoader();

    public List<String> getData(int day) throws URISyntaxException, IOException {
	return Files.lines(Paths.get(getResource(day).toURI()))
	    .collect(Collectors.toList());
    }

    public URL getResource(int day) {
	return Optional.of("inputs/day" + day + ".txt")
	    .map(classLoader::getResource)
	    .orElse(null);
    }

}
