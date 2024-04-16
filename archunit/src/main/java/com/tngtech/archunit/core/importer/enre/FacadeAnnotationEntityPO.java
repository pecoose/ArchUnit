/*
 * Copyright 2014-2024 TNG Technology Consulting GmbH
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
package com.tngtech.archunit.core.importer.enre;

public class FacadeAnnotationEntityPO extends AbstractFacadeEntityPO {

    private String File;
    private String packageName;
    private String rawType;
    private EnreLocationPO location;
    private String modifiers;

    public FacadeAnnotationEntityPO() {
    }

    public FacadeAnnotationEntityPO(Integer id, Integer isIntrusive, Integer isDecoupling, String name, Integer notAOSP, Integer oldAOSP, String ownership, String qualifiedName, Integer entityMapping, Integer commitsCount, String category, Integer calledTimes, String file, String packageName, String rawType, EnreLocationPO location, String modifiers) {
        super(id, isIntrusive, isDecoupling, name, notAOSP, oldAOSP, ownership, qualifiedName, entityMapping, commitsCount, category, calledTimes);
        File = file;
        this.packageName = packageName;
        this.rawType = rawType;
        this.location = location;
        this.modifiers = modifiers;
    }

    public String getFile() {
        return File;
    }

    public void setFile(String file) {
        File = file;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getRawType() {
        return rawType;
    }

    public void setRawType(String rawType) {
        this.rawType = rawType;
    }

    public EnreLocationPO getLocation() {
        return location;
    }

    public void setLocation(EnreLocationPO location) {
        this.location = location;
    }

    public String getModifiers() {
        return modifiers;
    }

    public void setModifiers(String modifiers) {
        this.modifiers = modifiers;
    }
}
