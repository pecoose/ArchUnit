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

public abstract class AbstractFacadeEntityPO {

    private Integer id;
    private Integer isIntrusive;
    private Integer isDecoupling;
    private String name;
    private Integer notAOSP;
    private Integer oldAOSP;
    private String ownership;
    private String qualifiedName;
    private Integer entityMapping;
    private Integer commitsCount;
    private String category;
    private Integer calledTimes;

    public AbstractFacadeEntityPO() {
    }

    public AbstractFacadeEntityPO(Integer id, Integer isIntrusive, Integer isDecoupling, String name, Integer notAOSP, Integer oldAOSP, String ownership, String qualifiedName, Integer entityMapping, Integer commitsCount, String category, Integer calledTimes) {
        this.id = id;
        this.isIntrusive = isIntrusive;
        this.isDecoupling = isDecoupling;
        this.name = name;
        this.notAOSP = notAOSP;
        this.oldAOSP = oldAOSP;
        this.ownership = ownership;
        this.qualifiedName = qualifiedName;
        this.entityMapping = entityMapping;
        this.commitsCount = commitsCount;
        this.category = category;
        this.calledTimes = calledTimes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIsIntrusive() {
        return isIntrusive;
    }

    public void setIsIntrusive(Integer isIntrusive) {
        this.isIntrusive = isIntrusive;
    }

    public Integer getIsDecoupling() {
        return isDecoupling;
    }

    public void setIsDecoupling(Integer isDecoupling) {
        this.isDecoupling = isDecoupling;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNotAOSP() {
        return notAOSP;
    }

    public void setNotAOSP(Integer notAOSP) {
        this.notAOSP = notAOSP;
    }

    public Integer getOldAOSP() {
        return oldAOSP;
    }

    public void setOldAOSP(Integer oldAOSP) {
        this.oldAOSP = oldAOSP;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public String getQualifiedName() {
        return qualifiedName;
    }

    public void setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

    public Integer getEntityMapping() {
        return entityMapping;
    }

    public void setEntityMapping(Integer entityMapping) {
        this.entityMapping = entityMapping;
    }

    public Integer getCommitsCount() {
        return commitsCount;
    }

    public void setCommitsCount(Integer commitsCount) {
        this.commitsCount = commitsCount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getCalledTimes() {
        return calledTimes;
    }

    public void setCalledTimes(Integer calledTimes) {
        this.calledTimes = calledTimes;
    }
}
