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

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class UAX29URLEmailTokenizerTest {
  @Test
  public void helloWorldTest() throws IOException {
    Token token1, token2, token3;
    try (UAX29URLEmailTokenizer unit =
        new UAX29URLEmailTokenizer(new StringReader("Hello, world!"))) {
      token1 = unit.nextToken();
      token2 = unit.nextToken();
      token3 = unit.nextToken();
    }
    assertThat(asList(token1, token2, token3),
        is(asList(new Token(Token.Type.ALPHANUM, "Hello", 0, 0),
            new Token(Token.Type.ALPHANUM, "world", 0, 7), null)));
  }

  @Test
  public void complexMultilineTest() throws IOException {
    List<Token> tokens = new ArrayList<>();
    try (UAX29URLEmailTokenizer unit = new UAX29URLEmailTokenizer(new StringReader(
        "Hello 123 #hashtag @mention $CASH\n" + "info@example.com http://www.example.com/ 🙂\n"
            + "한글    アメリカ    いえ    当世界    ดตถ\n"))) {
      for (Token token = unit.nextToken(); token != null; token = unit.nextToken()) {
        tokens.add(token);
      }
      tokens.add(null);
    }
    assertThat(tokens, is(asList(new Token(Token.Type.ALPHANUM, "Hello", 0, 0),
        new Token(Token.Type.NUM, "123", 0, 6), new Token(Token.Type.HASHTAG, "#hashtag", 0, 10),
        new Token(Token.Type.MENTION, "@mention", 0, 19),
        new Token(Token.Type.CASHTAG, "$CASH", 0, 28),
        new Token(Token.Type.EMAIL, "info@example.com", 1, 0),
        new Token(Token.Type.URL, "http://www.example.com/", 1, 17),
        new Token(Token.Type.EMOJI, "🙂", 1, 41), new Token(Token.Type.HANGUL, "한글", 2, 0),
        new Token(Token.Type.KATAKANA, "アメリカ", 2, 6), new Token(Token.Type.HIRAGANA, "い", 2, 14),
        new Token(Token.Type.HIRAGANA, "え", 2, 15), new Token(Token.Type.IDEOGRAPHIC, "当", 2, 20),
        new Token(Token.Type.IDEOGRAPHIC, "世", 2, 21),
        new Token(Token.Type.IDEOGRAPHIC, "界", 2, 22),
        new Token(Token.Type.SOUTHEAST_ASIAN, "ดตถ", 2, 27), null)));
  }
}
