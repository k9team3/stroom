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

package stroom.dashboard.expression;

import org.joda.time.DateTime;

public class RoundMonth extends RoundDate {
    public static class Calc extends RoundDateCalculator {
        private static final long serialVersionUID = -5893918049538006730L;

        @Override
        protected DateTime adjust(final DateTime dateTime) {
            DateTime result = new DateTime(dateTime.getYear(), dateTime.getMonthOfYear(), 1, 0, 0, 0, 0);
            if (dateTime.getDayOfMonth() > 15 || (dateTime.getDayOfMonth() == 15
                    && (dateTime.getMillisOfSecond() > 0 || dateTime.getSecondOfMinute() > 0
                            || dateTime.getMinuteOfHour() > 0 || dateTime.getHourOfDay() > 0))) {
                result = result.plusMonths(1);
            }
            return result;
        }
    }

    public static final String NAME = "roundMonth";
    private static final Calc CALC = new Calc();

    public RoundMonth(final String name) {
        super(name);
    }

    @Override
    protected RoundCalculator getCalculator() {
        return CALC;
    }
}
