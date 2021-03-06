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

package stroom.statistics.server.common;

import stroom.util.logging.StroomLogger;
import org.springframework.stereotype.Component;

import stroom.statistics.common.StatisticStoreValidator;
import stroom.statistics.shared.StatisticStoreEntity;
import stroom.statistics.shared.StatisticType;

@Component
public class StatisticsDataSourceValidatorImpl implements StatisticStoreValidator {
    private static final StroomLogger LOGGER = StroomLogger.getLogger(StatisticsDataSourceValidatorImpl.class);

    @Override
    public boolean validateStatisticDataSource(final String statisticName, final String engineName,
            final StatisticType statisticType, final StatisticStoreEntity statisticsDataSource) {
        if (statisticsDataSource == null) {
            LOGGER.warn(
                    "No StatisticDataSource could be found with name %s and engine %s, so no statistics will be recorded for it.",
                    statisticName, engineName);
            return false;
        } else if (!statisticsDataSource.getStatisticType().equals(statisticType)) {
            LOGGER.error(
                    "The type of the event [%s] is not valid for the StatisticDataSource with name %s, engine %s and type %s.",
                    statisticType, statisticName, engineName, statisticsDataSource.getStatisticType().toString());
            return false;
        } else if (!statisticsDataSource.isEnabled()) {
            LOGGER.warn(
                    "The StatisticDataSource with name %s, engine %s and type %s is not enabled, so no statistics will be recorded for it.",
                    statisticName, engineName, statisticsDataSource.getStatisticType().toString());
            return false;
        }

        return true;
    }
}
