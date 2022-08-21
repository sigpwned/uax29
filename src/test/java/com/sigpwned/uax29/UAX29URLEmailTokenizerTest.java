package com.sigpwned.uax29;

import java.io.IOException;
import java.io.StringReader;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.junit.Test;

public class UAX29URLEmailTokenizerTest {
  @Test
  public void test() throws IOException {
    UAX29URLEmailTokenizer unit=new UAX29URLEmailTokenizer();
    unit.setReader(new StringReader("Hello, world!"));
    unit.reset();
    
    while(unit.incrementToken()) {
      CharTermAttribute chars=unit.getAttribute(CharTermAttribute.class);
      TypeAttribute type=unit.getAttribute(TypeAttribute.class);
      System.out.println(chars.toString());
      System.out.println(type.type());
    }
  }
}
