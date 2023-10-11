package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffAccumulator;
import java.util.List;

public final class JsonDiffFormatter implements DiffFormatter {
    public String format(List<DiffAccumulator> diffs) throws Exception {

        ObjectWriter ow = new ObjectMapper().writer();
        return ow.writeValueAsString(diffs);
    }
}
