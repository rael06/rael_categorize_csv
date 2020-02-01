import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CsvManager {
    private static final String[] colNames = new String[]{"A", "B", "C", "D", "E", "F", "G"};
    private File csv;
    private String separator = ";";

    public CsvManager(String source) {
        this.csv = Paths.get(source).toFile();
    }

    public List<String> extractCol(int i) throws IOException {
        List<String[]> csvLines = read();
        return csvLines.stream().map(l -> l[0].split(separator)[i]).collect(Collectors.toList());
    }

    private List<String[]> read() throws IOException {
        BufferedReader fr = Files.newBufferedReader(csv.toPath());
        CSVReader csvReader = new CSVReaderBuilder(fr).withSkipLines(1).build();

        return csvReader.readAll();
    }

    public List<Integer> categorize(List<String> col) {
        List<Integer> categorizedCol = new ArrayList<>();
        List<String> categories = new ArrayList<>();
        for (String row : col) {
            if (!categories.contains(row)) {
                categories.add(row);
            }
        }

        for (String row : col) {
            categorizedCol.add(categories.indexOf(row));
        }

        return categorizedCol;
    }

    public void writeCategorizedCol(List<Integer> categorizedCol, String destination) {
        try {
            Writer writer = Files.newBufferedWriter(Paths.get(destination), StandardOpenOption.APPEND);
            CSVWriter csvWriter = new CSVWriter(writer,
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            for (Integer row : categorizedCol) {
                csvWriter.writeNext(new String[]{row + ""});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
