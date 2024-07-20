package exercise;

// BEGIN
public class LabelTag implements TagInterface {
    private final String text;
    private final TagInterface childTag;

    public LabelTag(String text, TagInterface childTag) {
        this.text = text;
        this.childTag = childTag;
    }

    public String render() {
        return "<label>" + text + childTag.render() + "</label>";
    }
}
// END
