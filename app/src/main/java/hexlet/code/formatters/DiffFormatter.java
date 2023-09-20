package hexlet.code.formatters;

import hexlet.code.DiffAccumulator;
import java.util.List;

public interface DiffFormatter {

    String format(List<DiffAccumulator> diffs) throws Exception;
}
