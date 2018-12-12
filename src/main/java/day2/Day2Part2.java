package day2;

import helpers.InputLoader;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Day2Part2 {

    String solve(List<String> codes) {
	for (String codeA : codes) {
	    for (String codeB: codes) {
	        if (codeA.equals(codeB) || codeA.length() != codeB.length()) {
	            continue;
		}
	        String commonPrefix = StringUtils.getCommonPrefix(codeA, codeB);
	        String commonSuffix = StringUtils.getCommonPrefix(StringUtils.reverse(codeA), StringUtils.reverse(codeB));
	        if (commonPrefix.length() + commonSuffix.length() == codeA.length() - 1) {
	            return commonPrefix + StringUtils.reverse(commonSuffix);
		}
	    }
	}
	return "";
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
	List<String> codes = Files.lines(Paths.get(new InputLoader().getResource(2).toURI()))
	    .collect(Collectors.toList());

	System.out.println(new Day2Part2().solve(codes));
    }
}
