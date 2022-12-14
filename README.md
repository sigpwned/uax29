# UAX29 [![tests](https://github.com/sigpwned/uax29/actions/workflows/tests.yml/badge.svg)](https://github.com/sigpwned/uax29/actions/workflows/tests.yml) [![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=sigpwned_uax29&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=sigpwned_uax29) [![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=sigpwned_uax29&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=sigpwned_uax29) [![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=sigpwned_uax29&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=sigpwned_uax29) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.sigpwned/uax29/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.sigpwned/uax29)

Java implementation of [UAX #29 text segmentation algorithm](https://unicode.org/reports/tr29/), plus token types for URLs, emoji, emails, hashtags, cashtags, and mentions.

# Usage

The tokenizer produces the following token types:

* `ALPHANUM` -- A sequence of alphabetic and numeric characters, e.g., hello, test123
* `NUM` -- A number, e.g., 123
* `SOUTHEAST_ASIAN` -- A sequence of characters from South and Southeast Asian languages, including Thai, Lao, Myanmar, and Khmer
* `IDEOGRAPHIC` -- A single CJKV ideographic character
* `HIRAGANA` -- A single hiragana character
* `KATAKANA` -- A sequence of katakana characters
* `HANGUL` -- A sequence of Hangul characters
* `URL` -- A URL, e.g., https://www.example.com/
* `EMAIL` -- An email address or mailto link, e.g., info@example.com
* `EMOJI` -- A sequence of Emoji characters, e.g., 🙂
* `HASHTAG` -- A social media hashtag, e.g., #hashtag
* `CASHTAG` -- A social media cashtag, e.g., $CASH
* `MENTION` -- A social media mention, e.g., @twitter

To process text into tokens, use code like the following:

    try (UAX29URLEmailTokenizer tokenizer=new UAX29URLEmailTokenizer("example text")) {
        for(Token token=tokenizer.nextToken();token!=null;token=tokenizer.nextToken(token)) {
            // Process the token here
        }
    }
