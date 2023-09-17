package hexlet.code;

public final class DiffAccumulator {

    private final String key;
    private final String status;
    private Object value;

    private Object valueBefore;
    private Object valueAfter;

    public DiffAccumulator(String key, String status, Object value) {
        this.key = key;
        this.status = status;
        this.value = value;
    }

    public DiffAccumulator(String key, String status, Object valueBefore, Object valueAfter) {
        this.key = key;
        this.status = status;
        this.valueBefore = valueBefore;
        this.valueAfter = valueAfter;
    }

    public String getKey() {
        return key;
    }

    public String getStatus() {
        return status;
    }

    public Object getValue() {
        return value;
    }

    public Object getValueBefore() {
        return valueBefore;
    }

    public Object getValueAfter() {
        return valueAfter;
    }

    @Override
    public String toString() {
        return "DiffAccumulator{"
                + "key='" + key + '\''
                + ", status='" + status + '\''
                + ", value=" + value
                + ", valueBefore=" + valueBefore
                + ", valueAfter=" + valueAfter
                + '}';
    }
}
