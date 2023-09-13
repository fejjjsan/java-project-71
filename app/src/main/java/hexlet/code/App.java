
package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;
import static hexlet.code.Differ.generate;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<Integer> {
    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    static String p1;
    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    static String p2;
    @Option(names = {"-f", "--format"},
            defaultValue = "${FORMAT:-stylish}",
            description = "output format [default:${DEFAULT-VALUE}]")
    static String format = "stylish";
    @Option(names = {"-h", "--help"}, usageHelp = true,
            description = "Show this help message and exit.")
    boolean usageHelpRequested;
    @Option(names = {"V", "--version"}, versionHelp = true,
            description = "Print version information and exit.")
    boolean versionInfoRequested;
    @Override
    public Integer call() {
        try {
            String result = generate(p1, p2, format);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }
    public static void main(String[] args) {
        App app = CommandLine.populateCommand(new App(), args);
        if (app.usageHelpRequested) {
            CommandLine.usage(new App(), System.out);
        } else if (p1 != null && p2 != null) {
            int exitCode = new CommandLine(new App()).execute(args);
            System.exit(exitCode);
        } else if (app.versionInfoRequested) {
            System.out.println("1.0");
        }
    }
}
