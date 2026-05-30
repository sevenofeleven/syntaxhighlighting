package highlighting.regex;

import highlighting.core.HighlightRegion;
import highlighting.core.SyntaxHighlighter;
import highlighting.presets.MiniJavaTokens;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class RegexHighlighter extends SyntaxHighlighter {

  // {@code HighlightRegion}s, and combine all of these regions into a single list.
  @Override
  public List<HighlightRegion> collectMatches(String text) {
    List<HighlightRegion> regions = new ArrayList<>();

    List<Token> tokens = MiniJavaTokens.defaultTokens();
    for (Token t : tokens) {
      Matcher m = t.pattern().matcher(text);
      while (m.find()) {
        regions.add(new HighlightRegion(m.start(), m.end(), t.colour()));
      }
    }

    return regions;
  }

  @Override
  public List<HighlightRegion> resolveConflicts(List<HighlightRegion> regions) {
    List<HighlightRegion> newRegions = new ArrayList<>();

    int i = 0;
    while (i < regions.size()) {
      HighlightRegion r = regions.get(i);
      newRegions.add(r);

        // skip ..
        do {
            i++;
        } while (i < regions.size() && regions.get(i).start() < r.end());
    }
    return newRegions;
  }
}
