import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvManager {
    private static final String[] colNames = new String[] {"A", "B", "C", "D", "E", "F", "G"};
    private File csv;
    private String separator = ";";

    public CsvManager(String source) {
        this.csv = Paths.get(source).toFile();
    }

    public List<String> extractCol(int i) throws IOException {
        List<String[]> csvLines = read();
        return csvLines.stream().map(l->l[0].split(separator)[i]).collect(Collectors.toList());
    }

    private List<String[]> read() throws IOException {
        BufferedReader fr = Files.newBufferedReader(csv.toPath());
        CSVReader csvReader = new CSVReaderBuilder(fr).withSkipLines(1).build();

        return csvReader.readAll();
    }
}
