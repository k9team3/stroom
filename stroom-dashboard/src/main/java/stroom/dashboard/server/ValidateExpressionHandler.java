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

package stroom.dashboard.server;

import java.text.ParseException;

import stroom.dashboard.expression.Expression;
import stroom.dashboard.expression.ExpressionParser;
import stroom.dashboard.expression.FieldIndexMap;
import stroom.dashboard.expression.FunctionFactory;
import stroom.dashboard.expression.ParamFactory;
import stroom.dashboard.shared.ValidateExpressionAction;
import stroom.dashboard.shared.ValidateExpressionResult;
import stroom.task.server.AbstractTaskHandler;
import stroom.task.server.TaskHandlerBean;

@TaskHandlerBean(task = ValidateExpressionAction.class)
public class ValidateExpressionHandler extends AbstractTaskHandler<ValidateExpressionAction, ValidateExpressionResult> {
    @Override
    public ValidateExpressionResult exec(final ValidateExpressionAction action) {
        try {
            final FieldIndexMap fieldIndexMap = new FieldIndexMap(true);
            final ExpressionParser expressionParser = new ExpressionParser(new FunctionFactory(), new ParamFactory());
            final Expression expression = expressionParser.parse(fieldIndexMap, action.getExpression());
            String correctedExpression = "";
            if (expression != null) {
                correctedExpression = expression.toString();
            }
            return new ValidateExpressionResult(true, correctedExpression);
        } catch (final ParseException e) {
            return new ValidateExpressionResult(false, e.getMessage());
        }
    }
}
