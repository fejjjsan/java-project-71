package hexlet.code.Utils.formatters;

import hexlet.code.DiffAccumulator;
import java.util.List;

public interface DiffFormatter {

    String formatDiffs(List<DiffAccumulator> diffs) throws Exception;
}
