package exercise;

import java.util.StringJoiner;
import java.util.Map;

// BEGIN
public abstract class Tag {
    private String name;
    private Map<String, String> attributes;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public abstract String toString();

    protected String getAttrSequence() {
        StringJoiner attrSeq = new StringJoiner(" ", attributes.isEmpty() ? "" : " ", "");

        for (String key : attributes.keySet()) {
            attrSeq.add(key + "=\"" + attributes.get(key) + "\"");
        }

        return attrSeq.toString();
    }
}
// END
