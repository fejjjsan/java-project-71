package hexlet.code.Utils.formatters;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffAccumulator;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class JsonDiffFormatter implements DiffFormatter {
    public String formatDiffs(List<DiffAccumulator> diffs) throws Exception {

        Map<String, Object> diffsMap = new TreeMap<>();

        diffs.forEach(o -> {
            if (o.getStatus().equals("removed") || o.getStatus().equals("added")) {
                diffsMap.put(o.getKey(), o.getValue());
            } else if (o.getStatus().equals("updated")) {
                diffsMap.put(o.getKey(), o.getValueAfter());
            } else {
                diffsMap.put(o.getKey(), o.getValue());
            }
        });


        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(diffsMap);
    }
}
