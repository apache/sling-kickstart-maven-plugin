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

import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Stop one or multiple running kickstart instance(s).
 *
 */
@Mojo(
    name = "stop",
    defaultPhase = LifecyclePhase.POST_INTEGRATION_TEST,
    threadSafe = true
)
public class StopMojo extends AbstractStartStopMojo {

    @Override
    protected void doExecute() throws MojoExecutionException, MojoFailureException {
        
        // read configurations
        final Properties kickstartConfigProps = new Properties();
        Reader reader = null;
        try {
            reader = new FileReader(this.systemPropertiesFile);
            kickstartConfigProps.load(reader);
        } catch ( final IOException ioe) {
            throw new MojoExecutionException("Unable to read kickstart runner configuration properties.", ioe);
        } finally {
            IOUtils.closeQuietly(reader);
        }

        final int instances = Integer.valueOf(kickstartConfigProps.getProperty("kickstart.instances"));
        final List<ProcessDescription> configurations = new ArrayList<ProcessDescription>();
        for(int i=1;i<=instances;i++) {
            final String id = kickstartConfigProps.getProperty("kickstart.instance.id." + String.valueOf(i));

            final ProcessDescription config = ProcessDescriptionProvider.getInstance().getRunConfiguration(id);
            if ( config == null ) {
                getLog().warn("No kickstart configuration found for instance " + id);
            } else {
                configurations.add(config);
            }
        }

        blockIfNecessary();
        if (configurations.size() > 0) {
            getLog().info(new StringBuilder("Stopping ").append(configurations.size()).append(" Kickstart instances").toString());

            for (final ProcessDescription cfg : configurations) {

                try {
                    LauncherCallable.stop(this.getLog(), cfg);
                    ProcessDescriptionProvider.getInstance().removeRunConfiguration(cfg.getId());
                } catch (Exception e) {
                    throw new MojoExecutionException("Could not stop kickstart " + cfg.getId(), e);
                }
            }
        } else {
            getLog().warn("No stored configuration file was found at " + this.systemPropertiesFile + " - no Launchapd will be stopped");
        }
    }
}
