package highlighting_test.Presets_test;

import highlighting.core.HighlightRegion;
import highlighting.presets.MiniJavaColours;
import highlighting.regex.RegexHighlighter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class RegexHighlighter_test {
  RegexHighlighter rh = new RegexHighlighter();

  @Test
    public void collect_matches_test() {
      String text = "/* return */";
      var arr = rh.collectMatches(text).toArray(new HighlightRegion[0]);

      HighlightRegion[] a = new HighlightRegion[] {
          new HighlightRegion(0,12, MiniJavaColours.BLOCK_COMMENT_COLOUR),
          new HighlightRegion(3,9, MiniJavaColours.KEYWORD_COLOUR),
          new HighlightRegion(0,1, MiniJavaColours.OPERATORS_COLOUR),
          new HighlightRegion(1,2, MiniJavaColours.OPERATORS_COLOUR),
          new HighlightRegion(10,11, MiniJavaColours.OPERATORS_COLOUR),
          new HighlightRegion(11,12, MiniJavaColours.OPERATORS_COLOUR),
      };

      Assertions.assertArrayEquals(a,arr);
  }
}
