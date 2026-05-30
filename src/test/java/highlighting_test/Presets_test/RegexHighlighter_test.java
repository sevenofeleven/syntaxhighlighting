package highlighting_test.Presets_test;

import highlighting.core.HighlightRegion;
import highlighting.presets.MiniJavaColours;
import highlighting.regex.RegexHighlighter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class RegexHighlighter_test {
  RegexHighlighter rh = new RegexHighlighter();

  @Test
    public void collect_matches_test() {
      String text = "/* return */";
      var arr = rh.collectMatches(text);

      HighlightRegion[] a = new HighlightRegion[] {
          new HighlightRegion(0,12, MiniJavaColours.BLOCK_COMMENT_COLOUR),
          new HighlightRegion(3,9, MiniJavaColours.KEYWORD_COLOUR),
          new HighlightRegion(0,1, MiniJavaColours.OPERATORS_COLOUR),
          new HighlightRegion(1,2, MiniJavaColours.OPERATORS_COLOUR),
          new HighlightRegion(10,11, MiniJavaColours.OPERATORS_COLOUR),
          new HighlightRegion(11,12, MiniJavaColours.OPERATORS_COLOUR),
      };

      Assertions.assertArrayEquals(a, arr.toArray(new HighlightRegion[0]));
  }

    public void expectResolve(String s, HighlightRegion[] expected) {
      List<HighlightRegion> arr = rh.collectMatches(s);
      var normalized = rh.normalize(arr);

      var resolved = rh.resolveConflicts(normalized);

      /*for (var r : resolved) {
          System.out.println("region " + r.start() + " " + r.end() + " " + r.colour());
      }*/
      Assertions.assertArrayEquals(expected, resolved.toArray(new HighlightRegion[0]));
  }
    //mit überlappung
    @Test
        public void overlapping_regions(){
      String s = "//kommentar @Annotation <=";
        HighlightRegion[] expected = new HighlightRegion[] {
            new HighlightRegion(0,26, MiniJavaColours.LINE_COMMENT_COLOUR),
        };
        expectResolve(s,expected);
    }
    //aufeinanderfolgenden Regionen
    @Test
    public void consecutive_regions(){
      String s = "2+1";
        HighlightRegion[] expected = new HighlightRegion[] {
            new HighlightRegion(0,1,MiniJavaColours.NUMBERS_COLOUR),
            new HighlightRegion(1,2,MiniJavaColours.OPERATORS_COLOUR),
            new HighlightRegion(2,3,MiniJavaColours.NUMBERS_COLOUR),
        };
        expectResolve(s,expected);
    }
    //leerer String
    @Test
    public void empty_test(){
      String s = "";
        HighlightRegion[] expected = new HighlightRegion[] {};
        expectResolve(s,expected);
    }
}
