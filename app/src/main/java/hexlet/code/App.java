
package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;
import static hexlet.code.Differ.generate;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")

public final class App implements Callable<Integer> {
    static final int ERROR = 1;
    static final int SUCCESS = 0;
    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private static String p1;
    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private static String p2;
    @Option(names = {"-f", "--format"},
            defaultValue = "stylish",
            paramLabel = "format",
            description = "output format [default:${DEFAULT-VALUE}]")
    private String format;

    @Override
    public Integer call() {
        try {
            String result = generate(p1, p2, format);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }
    public static void main(String[] args) {
        CommandLine.populateCommand(new App(), args);
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
