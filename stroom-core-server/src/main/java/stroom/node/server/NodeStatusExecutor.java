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

package stroom.node.server;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import stroom.jobsystem.server.JobTrackedSchedule;
import stroom.statistics.common.StatisticsFactory;
import stroom.util.logging.StroomLogger;
import stroom.util.spring.StroomScope;
import stroom.util.spring.StroomSimpleCronSchedule;

@Component
@Scope(value = StroomScope.TASK)
public class NodeStatusExecutor {
    private static final StroomLogger LOGGER = StroomLogger.getLogger(NodeStatusExecutor.class);

    @Resource
    private NodeStatusServiceUtil nodeStatusServiceUtil;
    @Resource
    private StatisticsFactory statisticsFactory;

    /**
     * Gets a task if one is available, returns null otherwise.
     *
     * @return A task.
     */
    @StroomSimpleCronSchedule(cron = "* * *")
    @JobTrackedSchedule(jobName = "Node Status", advanced = false, description = "Job to record status of node (CPU and Memory usage)")
    public void exec() {
        LOGGER.debug("Updating the status for this node.");
        statisticsFactory.instance().putEvents(nodeStatusServiceUtil.buildNodeStatus());
    }
}
