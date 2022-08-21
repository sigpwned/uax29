package com.sigpwned.uax29;

import java.io.IOException;
import java.io.Reader;

public class UAX29URLEmailTokenizer implements AutoCloseable {
  /** Alpha/numeric token type */
  public static final int ALPHANUM = Token.Type.ALPHANUM.ordinal();
  /** Numeric token type */
  public static final int NUM = Token.Type.NUM.ordinal();
  /** Southeast Asian token type */
  public static final int SOUTHEAST_ASIAN = Token.Type.SOUTHEAST_ASIAN.ordinal();
  /** Ideographic token type */
  public static final int IDEOGRAPHIC = Token.Type.IDEOGRAPHIC.ordinal();
  /** Hiragana token type */
  public static final int HIRAGANA = Token.Type.HIRAGANA.ordinal();
  /** Katakana token type */
  public static final int KATAKANA = Token.Type.KATAKANA.ordinal();
  /** Hangul token type */
  public static final int HANGUL = Token.Type.HANGUL.ordinal();
  /** URL token type */
  public static final int URL = Token.Type.URL.ordinal();
  /** Email token type */
  public static final int EMAIL = Token.Type.EMAIL.ordinal();
  /** Emoji token type. */
  public static final int EMOJI = Token.Type.EMOJI.ordinal();
  /** Hashtag token type. */
  public static final int HASHTAG = Token.Type.HASHTAG.ordinal();
  /** Cashtag token type. */
  public static final int CASHTAG = Token.Type.CASHTAG.ordinal();
  /** Mention token type. */
  public static final int MENTION = Token.Type.MENTION.ordinal();

  /** String token types that correspond to token type int constants */
  public static final String[] TOKEN_TYPES = new String[] {"<ALPHANUM>", "<NUM>",
      "<SOUTHEAST_ASIAN>", "<IDEOGRAPHIC>", "<HIRAGANA>", "<KATAKANA>", "<HANGUL>", "<URL>",
      "<EMAIL>", "<EMOJI>", "<HASHTAG>", "<CASHTAG>", "<MENTION>"};

  /** A private instance of the JFlex-constructed scanner */
  private final UAX29URLEmailTokenizerImpl scanner;

  /**
   * Creates a new instance of the UAX29URLEmailTokenizer. Attaches the <code>input</code> to the
   * newly created JFlex scanner.
   */
  public UAX29URLEmailTokenizer(Reader in) {
    if (in == null)
      throw new NullPointerException();
    this.scanner = new UAX29URLEmailTokenizerImpl(in);
  }

  public Token nextToken() throws IOException {
    return nextToken(null);
  }

  public Token nextToken(Token token) throws IOException {
    int type = scanner.yylex();

    if (type == UAX29URLEmailTokenizerImpl.YYEOF)
      return null;

    String text = scanner.yytext();
    int line = scanner.getLine();
    int column = scanner.getColumn();

    if (token == null)
      token = new Token();

    token.setType(Token.Type.forOrdinal(type));
    token.setText(text);
    token.setLine(line);
    token.setColumn(column);

    return token;
  }

  @Override
  public void close() throws IOException {
    scanner.yyclose();
  }

  public void reset(Reader in) throws IOException {
    if (in == null)
      throw new NullPointerException();
    scanner.yyreset(in);
  }
}
