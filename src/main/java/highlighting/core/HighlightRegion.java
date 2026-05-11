package highlighting.core;

import java.awt.Color;

/** Represents a highlighted region within the text together with its colour. */
public final class HighlightRegion {
  private final int start;
  private final int end;
  private final Color colour;

  /**
   * @param start the start offset of the highlighted region (inclusive)
   * @param end the end offset of the highlighted region (exclusive)
   * @param colour the colour used to highlight this region
   */
  public HighlightRegion(int start, int end, Color colour) {
    this.start = start;
    this.end = end;
    this.colour = colour;
  }

  public int start() {
    return start;
  }

  public int end() {
    return end;
  }

  public Color colour() {
    return colour;
  }
}
