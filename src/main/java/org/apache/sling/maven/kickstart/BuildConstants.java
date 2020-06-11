/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.apache.sling.maven.kickstart;

import java.util.ArrayList;
import java.util.List;

public abstract class BuildConstants {

    // Types
    public static final String TYPE_JAR = "jar";

    public static final String PACKAGING_SLINGQUICKSTART = "slingkickstart";

    // Classifiers
    public static final String CLASSIFIER_APP = "app";

    // Manifest attributes

    public static final String ATTR_BUILT_BY = "Built-By";

    public static final String ATTR_CREATED_BY = "Created-By";

    public static final String ATTR_IMPLEMENTATION_VERSION = "Implementation-Version";

    public static final String ATTR_IMPLEMENTATION_VENDOR = "Implementation-Vendor";

    public static final String ATTR_IMPLEMENTATION_BUILD = "Implementation-Build";

    public static final String ATTR_IMPLEMENTATION_VENDOR_ID = "Implementation-Vendor-Id";

    public static final String ATTR_IMPLEMENTATION_TITLE = "Implementation-Title";

    public static final String ATTR_SPECIFICATION_TITLE = "Specification-Title";

    public static final String ATTR_SPECIFICATION_VENDOR = "Specification-Vendor";

    public static final String ATTR_SPECIFICATION_VERSION = "Specification-Version";

    public static final List<String> ATTRS_EXCLUDES = new ArrayList<String>();
    static {
        ATTRS_EXCLUDES.add(ATTR_BUILT_BY);
        ATTRS_EXCLUDES.add(ATTR_CREATED_BY);
        ATTRS_EXCLUDES.add(ATTR_IMPLEMENTATION_VERSION);
        ATTRS_EXCLUDES.add(ATTR_IMPLEMENTATION_VENDOR);
        ATTRS_EXCLUDES.add(ATTR_IMPLEMENTATION_BUILD);
        ATTRS_EXCLUDES.add(ATTR_IMPLEMENTATION_VENDOR_ID);
        ATTRS_EXCLUDES.add(ATTR_IMPLEMENTATION_TITLE);
        ATTRS_EXCLUDES.add(ATTR_SPECIFICATION_TITLE);
        ATTRS_EXCLUDES.add(ATTR_SPECIFICATION_VENDOR);
        ATTRS_EXCLUDES.add(ATTR_SPECIFICATION_VERSION);
    }
}
