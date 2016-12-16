/*
 * Copyright 2016 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package stroom.index.server.analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.util.Version;

import java.io.Reader;

public class AlphaNumericAnalyzer extends Analyzer {
    private final Version matchVersion;
    private final boolean caseSensitive;

    public AlphaNumericAnalyzer(final Version matchVersion, final boolean caseSensitive) {
        this.matchVersion = matchVersion;
        this.caseSensitive = caseSensitive;
    }

    @Override
    protected TokenStreamComponents createComponents(final String fieldName, final Reader reader) {
        if (caseSensitive) {
            return new TokenStreamComponents(new AlphaNumericCSTokenizer(matchVersion, reader));
        }
        return new TokenStreamComponents(new AlphaNumericTokenizer(matchVersion, reader));
    }
}