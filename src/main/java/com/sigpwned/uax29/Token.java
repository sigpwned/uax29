/*-
 * =================================LICENSE_START==================================
 * uax29
 * ====================================SECTION=====================================
 * Copyright (C) 2022 Andy Boothe
 * ====================================SECTION=====================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ==================================LICENSE_END===================================
 */
package com.sigpwned.uax29;

import java.util.Objects;

public class Token {
  public static enum Type {
    ALPHANUM, NUM, SOUTHEAST_ASIAN, IDEOGRAPHIC, HIRAGANA, KATAKANA, HANGUL, URL, EMAIL, EMOJI, HASHTAG, CASHTAG, MENTION;

    public static Type forOrdinal(int ordinal) {
      return values()[ordinal];
    }
  }

  private Type type;
  private String text;
  private int line;
  private int column;

  public Token() {}

  public Token(Type type, String text, int line, int column) {
    this.type = type;
    this.text = text;
    this.line = line;
    this.column = column;
  }

  /**
   * @return the type
   */
  public Type getType() {
    return type;
  }

  /**
   * @param type the type to set
   */
  public void setType(Type type) {
    this.type = type;
  }

  /**
   * @return the text
   */
  public String getText() {
    return text;
  }

  /**
   * @param text the text to set
   */
  public void setText(String text) {
    this.text = text;
  }

  /**
   * @return the line
   */
  public int getLine() {
    return line;
  }

  /**
   * @param line the line to set
   */
  public void setLine(int line) {
    this.line = line;
  }

  /**
   * @return the column
   */
  public int getColumn() {
    return column;
  }

  /**
   * @param column the column to set
   */
  public void setColumn(int column) {
    this.column = column;
  }

  @Override
  public int hashCode() {
    return Objects.hash(column, line, text, type);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Token other = (Token) obj;
    return column == other.column && line == other.line && Objects.equals(text, other.text)
        && type == other.type;
  }

  @Override
  public String toString() {
    return "Token [type=" + type + ", text=" + text + ", line=" + line + ", column=" + column + "]";
  }
}
