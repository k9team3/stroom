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

package stroom.streamstore.server;

import java.io.IOException;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import stroom.AbstractCoreIntegrationTest;
import stroom.streamstore.shared.FindStreamTypeCriteria;
import stroom.streamstore.shared.StreamType;
import stroom.streamstore.shared.StreamType.Purpose;
import stroom.streamstore.shared.StreamTypeService;
import stroom.util.test.FileSystemTestUtil;

public class TestStreamTypeServiceImpl extends AbstractCoreIntegrationTest {
    @Resource
    private StreamTypeService streamTypeService;

    @Test
    public void testSimple() throws IOException {
        final StreamType newStreamType = new StreamType();
        newStreamType.setName(FileSystemTestUtil.getUniqueTestString());
        newStreamType.setPath(FileSystemTestUtil.getUniqueTestString());
        newStreamType.setExtension(FileSystemTestUtil.getUniqueTestString());
        newStreamType.setPurpose(Purpose.PROCESSED);

        streamTypeService.save(newStreamType);

        for (final StreamType streamType : streamTypeService.find(new FindStreamTypeCriteria())) {
            final StreamType reload = streamTypeService.load(streamType);
            Assert.assertNotNull(reload);
        }
    }
}
