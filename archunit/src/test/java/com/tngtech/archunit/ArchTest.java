
package com.tngtech.archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class ArchTest {

    private static final JavaClasses archUnitClasses = new ClassFileImporter().importPackages("com.tngtech.archunit");

    @Test
    public void test() {
        classes().that().arePublic().and().areActivelyNative().and().resideInAPackage("com.tngtech.archunit").should().notBeExtendedByClassesThat().areExtensive().andShould().resideInAPackage("com.tngtech.archunit").check(archUnitClasses);
    }
}
