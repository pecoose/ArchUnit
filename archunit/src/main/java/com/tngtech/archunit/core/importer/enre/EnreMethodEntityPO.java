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

public class EnreMethodEntityPO extends EnreInternalEntityPO {

    private String rawType;
    private String file;
    private String modifiers;
    private EnreEnhancementPO enhancement;
    private EnreParameterPO parameters;
    private EnreLocationPO location;


    public EnreMethodEntityPO(Integer entityId, String entityName, String qualifiedName, String category, String ownership, Integer parentId, String rawType, String file, String modifiers, EnreEnhancementPO enhancement, EnreParameterPO parameters, EnreLocationPO location) {
        super(entityId, entityName, qualifiedName, category, ownership, parentId);
        this.rawType = rawType;
        this.file = file;
        this.modifiers = modifiers;
        this.enhancement = enhancement;
        this.parameters = parameters;
        this.location = location;
    }

    public String getRawType() {
        return rawType;
    }

    public void setRawType(String rawType) {
        this.rawType = rawType;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getModifiers() {
        return modifiers;
    }

    public void setModifiers(String modifiers) {
        this.modifiers = modifiers;
    }

    public EnreEnhancementPO getEnhancement() {
        return enhancement;
    }

    public void setEnhancement(EnreEnhancementPO enhancement) {
        this.enhancement = enhancement;
    }

    public EnreParameterPO getParameters() {
        return parameters;
    }

    public void setParameters(EnreParameterPO parameters) {
        this.parameters = parameters;
    }

    public EnreLocationPO getLocation() {
        return location;
    }

    public void setLocation(EnreLocationPO location) {
        this.location = location;
    }
}