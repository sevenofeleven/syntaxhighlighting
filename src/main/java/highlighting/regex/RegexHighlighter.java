package highlighting.regex;

import highlighting.core.HighlightRegion;
import highlighting.core.SyntaxHighlighter;
import highlighting.presets.MiniJavaTokens;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

// TODO: Implement a simple regex-based highlighting strategy. Unlike the scanning approach, this
// strategy applies each token independently to the entire input text and collects all resulting
// {@code HighlightRegion}s, even if they overlap. Conflicts are resolved in a separate step.

// TODO: Make this class extend {@code SyntaxHighlighter}, implement the abstract method {@code
// collectMatches}, and override {@code resolveConflicts} to handle overlapping regions produced by
// the naive regex-based strategy.

// alle Token aus MiniJavaTokens auf den Eingabetext angewendet werden,
// alle gefundenen Regionen in einer gemeinsamen Liste gesammelt werden,
// die Liste zurückgegeben wird (Sortierung übernimmt normalize).
public class RegexHighlighter extends SyntaxHighlighter {

  // TODO: For each token, find all matches of its pattern in the input text, convert them into
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

  // TODO: Resolve overlapping regions. Assume that {@code regions} has been normalised and sorted.
  // For any overlapping regions, keep the one that appears first in this list (which reflects the
  // token order) and discard all later overlapping regions. Longer regions that start at the same
  // position are preferred because of the sorting in {@code normalize}.
  @Override
  public List<HighlightRegion> resolveConflicts(List<HighlightRegion> regions) {
    List<HighlightRegion> newRegions = new ArrayList<>();

    int i = 0;
    while (i < regions.size()) {
      HighlightRegion r = regions.get(i);
      newRegions.add(r);

      i++;
      // skip ..
      while (i < regions.size() && regions.get(i).start() < r.end()) {
        i++;
      }
    }
    return newRegions;
  }
}
