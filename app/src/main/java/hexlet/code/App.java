
package hexlet.code;

import static hexlet.code.Utils.formatters.Formatter.formatPlain;
import static hexlet.code.Utils.formatters.Formatter.formatStylish;
import hexlet.code.Utils.parser.ParseJson;
import hexlet.code.Utils.parser.ParseYaml;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;

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
    static String format;
    @Option(names = {"-h", "--help"}, usageHelp = true,
            description = "Show this help message and exit.")
    boolean usageHelpRequested;
    @Option(names = {"V", "--version"}, versionHelp = true,
            description = "Print version information and exit.")
    boolean versionInfoRequested;
    @Override
    public Integer call() {
        String json = "json";
        String yaml = "yml";
        try {
            if (p1.endsWith(json) && p2.endsWith(json) && format.equals("plain")) {
                System.out.println(formatPlain(DiffGenerator.generate(p1, p2, new ParseJson())));
            } else if (p1.endsWith(yaml) && p2.endsWith(yaml) && format.equals("plain")) {
                System.out.println(formatPlain(DiffGenerator.generate(p1, p2, new ParseYaml())));
            } else if (p1.endsWith(json) && p2.endsWith(json)) {
                System.out.println(formatStylish(DiffGenerator.generate(p1, p2, new ParseJson())));
            } else if (p1.endsWith(yaml) && p2.endsWith(yaml)) {
                System.out.println(formatStylish(DiffGenerator.generate(p1, p2, new ParseYaml())));
            } else {
                throw new Exception("wrong file extension or option provided");
            }
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
