/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.sling.maven.kickstart.run;

import java.util.HashMap;
import java.util.Map;

/**
 * A singleton which is responsible to provide {@link ProcessDescription}s
 */
public class ProcessDescriptionProvider {

    private static final String DEFAULT_KEY = "DEFAULT_LAUNCHPAD";

    private static ProcessDescriptionProvider ourInstance = new ProcessDescriptionProvider();
    private final Map<String, ProcessDescription> configs = new HashMap<String, ProcessDescription>();
    private final Map<String, String> lockedIds = new HashMap<String, String>();

    private ProcessDescriptionProvider() {
        // private constructor
    }

    public static ProcessDescriptionProvider getInstance() {
        return ourInstance;
    }

    /**
     * Prepare an ID for a kickstart that will be started, before saving the config.
     * @param kickstartId the id of the kickstart to lock
     * @return id key used to add to configs
     */
    public synchronized String getId(final String kickstartId) throws Exception {
        final String id = (kickstartId == null ? DEFAULT_KEY : kickstartId);
        if (configs.containsKey(id) || lockedIds.containsKey(id)) {
            throw new Exception("Kickstart Id " + id + " is already in use");
        }

        String ts = String.valueOf(System.currentTimeMillis());
        lockedIds.put(id, ts);
        return ts;
    }

    /**
     *
     * @param kickstartId
     * @param unlockKey
     * @return
     */
    public synchronized boolean cancelId(final String kickstartId, final String unlockKey) {
        final String id = (kickstartId == null ? DEFAULT_KEY : kickstartId);
        if (lockedIds.containsKey(id) && lockedIds.get(id).equals(unlockKey)) {
            lockedIds.remove(id);
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param kickstartId
     * @return
     */
    public synchronized ProcessDescription getRunConfiguration(final String kickstartId) {
        final String id = (kickstartId == null ? DEFAULT_KEY : kickstartId);
        return configs.get(id);
    }

    /**
     *
     * @param kickstartId
     * @return
     */
    public synchronized  boolean isRunConfigurationAvailable(final String kickstartId) {
        return getRunConfiguration(kickstartId) == null && !lockedIds.containsKey(kickstartId);
    }

    public synchronized void addRunConfiguration(ProcessDescription cfg, final String unlockKey) throws Exception {
        String id = cfg.getId() == null ? DEFAULT_KEY : cfg.getId();
        if (!lockedIds.containsKey(id) || !lockedIds.get(id).equals(unlockKey)) {
            throw new Exception("Cannot add configuration. Id " + id + " doesn't exist");
        }
        lockedIds.remove(cfg.getId());
        configs.put(cfg.getId(), cfg);
    }

    public synchronized void removeRunConfiguration(final String kickstartId) {
        final String id = (kickstartId == null ? DEFAULT_KEY : kickstartId);
        configs.remove(id);
    }

}
