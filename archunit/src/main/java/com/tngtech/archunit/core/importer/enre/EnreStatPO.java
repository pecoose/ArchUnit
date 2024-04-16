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

public class EnreStatPO {

    private Integer packageEntityNumber;
    private Integer fileEntityNumber;
    private Integer classEntityNumber;
    private Integer enumEntityNumber;
    private Integer enumConstantEntityNumber;
    private Integer annotationEntityNumber;
    private Integer annotationMemberEntityNumber;
    private Integer interfaceEntityNumber;
    private Integer methodEntityNumber;
    private Integer variableEntityNumber;
    private Integer typeParameterEntityNumber;
    private Integer importRelationNumber;
    private Integer inheritRelationNumber;
    private Integer implementRelationNumber;
    private Integer containRelationNumber;
    private Integer callRelationNumber;
    private Integer parameterRelationNumber;
    private Integer typedRelationNumber;
    private Integer useVarRelationNumber;
    private Integer setRelationNumber;
    private Integer modifyRelationNumber;
    private Integer annotateRelationNumber;
    private Integer castRelationNumber;
    private Integer overrideRelationNumber;
    private Integer reflectRelationNumber;
    private Integer defineRelationNumber;


    public EnreStatPO() {
    }

    public EnreStatPO(Integer packageEntityNumber, Integer fileEntityNumber, Integer classEntityNumber, Integer enumEntityNumber, Integer enumConstantEntityNumber, Integer annotationEntityNumber, Integer annotationMemberEntityNumber, Integer interfaceEntityNumber, Integer methodEntityNumber, Integer variableEntityNumber, Integer typeParameterEntityNumber, Integer importRelationNumber, Integer inheritRelationNumber, Integer implementRelationNumber, Integer containRelationNumber, Integer callRelationNumber, Integer parameterRelationNumber, Integer typedRelationNumber, Integer useVarRelationNumber, Integer setRelationNumber, Integer modifyRelationNumber, Integer annotateRelationNumber, Integer castRelationNumber, Integer overrideRelationNumber, Integer reflectRelationNumber, Integer defineRelationNumber) {
        this.packageEntityNumber = packageEntityNumber;
        this.fileEntityNumber = fileEntityNumber;
        this.classEntityNumber = classEntityNumber;
        this.enumEntityNumber = enumEntityNumber;
        this.enumConstantEntityNumber = enumConstantEntityNumber;
        this.annotationEntityNumber = annotationEntityNumber;
        this.annotationMemberEntityNumber = annotationMemberEntityNumber;
        this.interfaceEntityNumber = interfaceEntityNumber;
        this.methodEntityNumber = methodEntityNumber;
        this.variableEntityNumber = variableEntityNumber;
        this.typeParameterEntityNumber = typeParameterEntityNumber;
        this.importRelationNumber = importRelationNumber;
        this.inheritRelationNumber = inheritRelationNumber;
        this.implementRelationNumber = implementRelationNumber;
        this.containRelationNumber = containRelationNumber;
        this.callRelationNumber = callRelationNumber;
        this.parameterRelationNumber = parameterRelationNumber;
        this.typedRelationNumber = typedRelationNumber;
        this.useVarRelationNumber = useVarRelationNumber;
        this.setRelationNumber = setRelationNumber;
        this.modifyRelationNumber = modifyRelationNumber;
        this.annotateRelationNumber = annotateRelationNumber;
        this.castRelationNumber = castRelationNumber;
        this.overrideRelationNumber = overrideRelationNumber;
        this.reflectRelationNumber = reflectRelationNumber;
        this.defineRelationNumber = defineRelationNumber;
    }

    public Integer getPackageEntityNumber() {
        return packageEntityNumber;
    }

    public void setPackageEntityNumber(Integer packageEntityNumber) {
        this.packageEntityNumber = packageEntityNumber;
    }

    public Integer getFileEntityNumber() {
        return fileEntityNumber;
    }

    public void setFileEntityNumber(Integer fileEntityNumber) {
        this.fileEntityNumber = fileEntityNumber;
    }

    public Integer getClassEntityNumber() {
        return classEntityNumber;
    }

    public void setClassEntityNumber(Integer classEntityNumber) {
        this.classEntityNumber = classEntityNumber;
    }

    public Integer getEnumEntityNumber() {
        return enumEntityNumber;
    }

    public void setEnumEntityNumber(Integer enumEntityNumber) {
        this.enumEntityNumber = enumEntityNumber;
    }

    public Integer getEnumConstantEntityNumber() {
        return enumConstantEntityNumber;
    }

    public void setEnumConstantEntityNumber(Integer enumConstantEntityNumber) {
        this.enumConstantEntityNumber = enumConstantEntityNumber;
    }

    public Integer getAnnotationEntityNumber() {
        return annotationEntityNumber;
    }

    public void setAnnotationEntityNumber(Integer annotationEntityNumber) {
        this.annotationEntityNumber = annotationEntityNumber;
    }

    public Integer getAnnotationMemberEntityNumber() {
        return annotationMemberEntityNumber;
    }

    public void setAnnotationMemberEntityNumber(Integer annotationMemberEntityNumber) {
        this.annotationMemberEntityNumber = annotationMemberEntityNumber;
    }

    public Integer getInterfaceEntityNumber() {
        return interfaceEntityNumber;
    }

    public void setInterfaceEntityNumber(Integer interfaceEntityNumber) {
        this.interfaceEntityNumber = interfaceEntityNumber;
    }

    public Integer getMethodEntityNumber() {
        return methodEntityNumber;
    }

    public void setMethodEntityNumber(Integer methodEntityNumber) {
        this.methodEntityNumber = methodEntityNumber;
    }

    public Integer getVariableEntityNumber() {
        return variableEntityNumber;
    }

    public void setVariableEntityNumber(Integer variableEntityNumber) {
        this.variableEntityNumber = variableEntityNumber;
    }

    public Integer getTypeParameterEntityNumber() {
        return typeParameterEntityNumber;
    }

    public void setTypeParameterEntityNumber(Integer typeParameterEntityNumber) {
        this.typeParameterEntityNumber = typeParameterEntityNumber;
    }

    public Integer getImportRelationNumber() {
        return importRelationNumber;
    }

    public void setImportRelationNumber(Integer importRelationNumber) {
        this.importRelationNumber = importRelationNumber;
    }

    public Integer getInheritRelationNumber() {
        return inheritRelationNumber;
    }

    public void setInheritRelationNumber(Integer inheritRelationNumber) {
        this.inheritRelationNumber = inheritRelationNumber;
    }

    public Integer getImplementRelationNumber() {
        return implementRelationNumber;
    }

    public void setImplementRelationNumber(Integer implementRelationNumber) {
        this.implementRelationNumber = implementRelationNumber;
    }

    public Integer getContainRelationNumber() {
        return containRelationNumber;
    }

    public void setContainRelationNumber(Integer containRelationNumber) {
        this.containRelationNumber = containRelationNumber;
    }

    public Integer getCallRelationNumber() {
        return callRelationNumber;
    }

    public void setCallRelationNumber(Integer callRelationNumber) {
        this.callRelationNumber = callRelationNumber;
    }

    public Integer getParameterRelationNumber() {
        return parameterRelationNumber;
    }

    public void setParameterRelationNumber(Integer parameterRelationNumber) {
        this.parameterRelationNumber = parameterRelationNumber;
    }

    public Integer getTypedRelationNumber() {
        return typedRelationNumber;
    }

    public void setTypedRelationNumber(Integer typedRelationNumber) {
        this.typedRelationNumber = typedRelationNumber;
    }

    public Integer getUseVarRelationNumber() {
        return useVarRelationNumber;
    }

    public void setUseVarRelationNumber(Integer useVarRelationNumber) {
        this.useVarRelationNumber = useVarRelationNumber;
    }

    public Integer getSetRelationNumber() {
        return setRelationNumber;
    }

    public void setSetRelationNumber(Integer setRelationNumber) {
        this.setRelationNumber = setRelationNumber;
    }

    public Integer getModifyRelationNumber() {
        return modifyRelationNumber;
    }

    public void setModifyRelationNumber(Integer modifyRelationNumber) {
        this.modifyRelationNumber = modifyRelationNumber;
    }

    public Integer getAnnotateRelationNumber() {
        return annotateRelationNumber;
    }

    public void setAnnotateRelationNumber(Integer annotateRelationNumber) {
        this.annotateRelationNumber = annotateRelationNumber;
    }

    public Integer getCastRelationNumber() {
        return castRelationNumber;
    }

    public void setCastRelationNumber(Integer castRelationNumber) {
        this.castRelationNumber = castRelationNumber;
    }

    public Integer getOverrideRelationNumber() {
        return overrideRelationNumber;
    }

    public void setOverrideRelationNumber(Integer overrideRelationNumber) {
        this.overrideRelationNumber = overrideRelationNumber;
    }

    public Integer getReflectRelationNumber() {
        return reflectRelationNumber;
    }

    public void setReflectRelationNumber(Integer reflectRelationNumber) {
        this.reflectRelationNumber = reflectRelationNumber;
    }

    public Integer getDefineRelationNumber() {
        return defineRelationNumber;
    }

    public void setDefineRelationNumber(Integer defineRelationNumber) {
        this.defineRelationNumber = defineRelationNumber;
    }
}