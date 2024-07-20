package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag {
    public SingleTag(String name, Map<String, String> attributes) {
        setName(name);
        setAttributes(attributes);
    }

    @Override
    public String toString() {
        return "<" + super.getName() + super.getAttrSequence() + ">";
    }
}
// END
