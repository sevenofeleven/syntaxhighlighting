package highlighting.presets;

import highlighting.regex.Token;
import java.awt.*;
import java.util.List;
import java.util.regex.Pattern;

public final class MiniJavaTokens {

  // TODO (Phase I+II: RegexHighlighter/ScanningHighlighter)
  // TODO: Define the MiniJava tokens used by the highlighters. Each token is a mapping from a
  // regular expression to a colour (and, if applicable, a specific matching group). The order of
  // tokens in this list determines their relative priority during highlighting. One example token
  // definition is provided below; define the remaining tokens in an analogous way.

  // Basic token set for MiniJava. Extend this list with further tokens as needed (e.g. identifiers,
  // numeric literals, operators, brackets, whitespace), following the same pattern. Each token is
  // defined by a regular expression and a colour. Optionally, a specific capturing group within the
  // pattern can be selected as the "highlighted" region.
  public static List<Token> defaultTokens() {
    return List.of(
        // string literals
        Token.of(Pattern.compile("\"([^\"\\\\]|\\\\.)*\""), MiniJavaColours.STRING_LITERAL_COLOUR),
        // annotations
        Token.of(Pattern.compile("@(\\S+)"), MiniJavaColours.ANNOTATION_COLOUR),
        // comments
        Token.of(Pattern.compile("//.*$"), MiniJavaColours.BLOCK_COMMENT_COLOUR),
        // javadoc
        Token.of(Pattern.compile("/\\*\\*(\\w+)\\*/"), MiniJavaColours.JAVADOC_COMMENT_COLOUR),
        // mehrzeilig
        Token.of(Pattern.compile("/\\*(\\w+)\\*/"), MiniJavaColours.BLOCK_COMMENT_COLOUR),
        // keywords
        Token.of(
            Pattern.compile(
                "(abstract|continue|for|new|switch|assert|default|goto|package|synchronized|boolean|do|if|private|this|break|double|implements|protected|throw|byte|else|import|public|throws|case|enum|instanceof|return|transient|catch|extends|int|short|try|char|final|interface|static|void|class|finally|long|strictfp|volatile|const|float|native|super|while)"),
            MiniJavaColours.KEYWORD_COLOUR),
        // numbers
        Token.of(
            Pattern.compile("(\\d+)(\\.(\\d+)(e[+-]?\\d+)?f?)?"), MiniJavaColours.NUMBERS_COLOUR),
        // characters
        Token.of(Pattern.compile("\\."), MiniJavaColours.CHAR_LITERAL_COLOUR),
        // operators
        Token.of(
            Pattern.compile("(<=|>=|==|!=|\\+\\+|--|\\+=|-=|\\|\\||&&|[+\\-*/%=!^&|<>])"),
            MiniJavaColours.OPERATORS_COLOUR)
        // identifiers
        );
  }
}
