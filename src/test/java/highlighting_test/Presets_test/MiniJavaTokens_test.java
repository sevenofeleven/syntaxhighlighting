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
}
