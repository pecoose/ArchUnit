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

import java.util.List;

public class FacadeModel {

    private List<AbstractFacadeEntityPO> variables;
    private List<FacadeRelationPO> cells;

    public FacadeModel(List<AbstractFacadeEntityPO> variables, List<FacadeRelationPO> cells) {
        this.variables = variables;
        this.cells = cells;
    }

    public List<AbstractFacadeEntityPO> getVariables() {
        return variables;
    }

    public void setVariables(List<AbstractFacadeEntityPO> variables) {
        this.variables = variables;
    }

    public List<FacadeRelationPO> getCells() {
        return cells;
    }

    public void setCells(List<FacadeRelationPO> cells) {
        this.cells = cells;
    }
}
