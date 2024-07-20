package exercise;

import java.util.Map;
import java.util.List;
import java.util.StringJoiner;

// BEGIN
public class PairedTag extends Tag {
    private final String text;
    private final List<Tag> childTags;
    public PairedTag(String name, Map<String, String> attributes, String tagText, List<Tag> childTags) {
        setName(name);
        setAttributes(attributes);
        this.text = tagText;
        this.childTags = childTags;
    }

    private String getChildTagsSequence() {
        StringJoiner childTagsSeq = new StringJoiner("");

        for (Tag childTag : childTags) {
            childTagsSeq.add(childTag.toString());
        }

        return childTagsSeq.toString();
    }

    @Override
    public String toString() {
        String name = super.getName();
        String attrSeq = super.getAttrSequence();
        String childTagsSeq = getChildTagsSequence();

        return "<" + name + attrSeq + ">" + text + childTagsSeq + "</" + name + ">";
    }
}
// END
