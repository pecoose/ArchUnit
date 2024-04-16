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

public class FacadeRelationPO {

    private Integer srcId;
    private Integer destId;
    private String category;
    private Integer bindVar;
    private Boolean modifyAccessible;
    private Boolean invoke;

    public FacadeRelationPO() {
    }

    public FacadeRelationPO(Integer srcId, Integer destId, String category, Integer bindVar, Boolean modifyAccessible, Boolean invoke) {
        this.srcId = srcId;
        this.destId = destId;
        this.category = category;
        this.bindVar = bindVar;
        this.modifyAccessible = modifyAccessible;
        this.invoke = invoke;
    }

    public Integer getSrcId() {
        return srcId;
    }

    public void setSrcId(Integer srcId) {
        this.srcId = srcId;
    }

    public Integer getDestId() {
        return destId;
    }

    public void setDestId(Integer destId) {
        this.destId = destId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getBindVar() {
        return bindVar;
    }

    public void setBindVar(Integer bindVar) {
        this.bindVar = bindVar;
    }

    public Boolean getModifyAccessible() {
        return modifyAccessible;
    }

    public void setModifyAccessible(Boolean modifyAccessible) {
        this.modifyAccessible = modifyAccessible;
    }

    public Boolean getInvoke() {
        return invoke;
    }

    public void setInvoke(Boolean invoke) {
        this.invoke = invoke;
    }
}
