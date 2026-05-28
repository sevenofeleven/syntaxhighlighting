package highlighting_test.Presets_test;

import static org.junit.jupiter.api.Assertions.*;

import highlighting.presets.MiniJavaTokens;
import highlighting.regex.Token;
import java.util.List;
import java.util.regex.Matcher;
import org.junit.jupiter.api.Test;

class MiniJavaToken_test {
  // highlighting.presets.Texts.START_TEXT;

  /* inspect 's' by testing all tokens from MiniJavaTokens in order, looking for the first
   * to match with 'detector'. once found, require all elements in 'more' to match as well */
  private boolean expect_tokens(String s, String detector, String[] more) {
    List<Token> t = MiniJavaTokens.defaultTokens();
    boolean found = false;

    for (Token i : t) {
      Matcher matcher = i.pattern().matcher(s);
      if (!matcher.find()) continue;

      found = matcher.group(0).equals(detector);
      if (!found) continue;

      for (String m : more) {
        assert (matcher.find());
        assertEquals(m, matcher.group(0));
      }
      break;
    }

    return found;
  }

  @Test
  public void strings_test() {
    // given
    String s = "24124 \"banal\" 124\"124\"124";
    // when then
    assert (expect_tokens(s, "\"banal\"", new String[] {"\"124\""}));
  }

  @Test
  public void annotation_test() {
    // given
    String s = "} @anni @anno öffentliche leere";

    assert (expect_tokens(s, "@anni", new String[] {"@anno"}));
  }

  @Test
  public void number_test() {
    // given
    String s = "24124 \"banal\" 124\"239\"666";

    assert (expect_tokens(s, "24124", new String[] {"124", "239", "666"}));
  }

  // einzeilige kommentare
  @Test
  public void comment_test() {
    String s = "comment: // hier steht ein kommentar.";
    assert (expect_tokens(s, "// hier steht ein kommentar.", new String[] {}));
  }

  //mehrzeilige
  @Test
  public void long_comment_test() {
    String s = "/* hier steht ein kommentar.*/ hier nicht mehr";
    assert (expect_tokens(s, "/* hier steht ein kommentar.*/", new String[] {}));
  }

  // javadoc comment
  @Test
  public void javadoc_comment_test() {
    String s = "comment: /** hier steht ein kommentar.*/ hier nicht mehr";
    assert (expect_tokens(s, "/** hier steht ein kommentar.*/", new String[] {}));
  }

  // keywords
  @Test
  public void keywords_test() {
    String s = " for i = 0 comment: // hier steht ein kommentar. if";
    assert (expect_tokens(s, "for", new String[] {"if"}));
  }

  // char
  @Test
  public void char_test() {
    String s = "f q \"fda\" 'f' + '\\t' 123 'kkkk '\\u1234'";
    assert (expect_tokens(s, "'f'", new String[] {"'\\t'", "'\\u1234'"}));
  }

  // operators
  @Test
  public void operators_test() {
    String s = " for i <= 0 for (var comment : comments) 1 / 4 > 0.";
    assert (expect_tokens(s, "<=", new String[] {"/", ">"}));
  }
}
