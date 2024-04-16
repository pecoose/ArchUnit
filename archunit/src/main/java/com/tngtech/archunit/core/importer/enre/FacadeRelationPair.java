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

public class FacadeRelationPair {

    private AbstractFacadeEntityPO src;
    private AbstractFacadeEntityPO dest;
    private FacadeRelationPO relation;

    public FacadeRelationPair() {
    }

    public FacadeRelationPair(AbstractFacadeEntityPO src, AbstractFacadeEntityPO dest, FacadeRelationPO relation) {
        this.src = src;
        this.dest = dest;
        this.relation = relation;
    }

    public AbstractFacadeEntityPO getSrc() {
        return src;
    }

    public void setSrc(AbstractFacadeEntityPO src) {
        this.src = src;
    }

    public AbstractFacadeEntityPO getDest() {
        return dest;
    }

    public void setDest(AbstractFacadeEntityPO dest) {
        this.dest = dest;
    }

    public FacadeRelationPO getRelation() {
        return relation;
    }

    public void setRelation(FacadeRelationPO relation) {
        this.relation = relation;
    }
}
