package com.domus;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.Test;

public class ArchUnitTest {

    @Test
    public void layeredArchitectureTest() {
        JavaClasses importedClasses = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("com.domus");

        Architectures.LayeredArchitecture architecture = Architectures.layeredArchitecture()
                .consideringOnlyDependenciesInLayers()
                .layer("Controllers").definedBy("com.domus.controller..")
                .layer("Services").definedBy("com.domus.service..")
                .layer("Repositories").definedBy("com.domus.repository..")
                .whereLayer("Controllers").mayNotAccessAnyLayer()
                .whereLayer("Services").mayNotAccessAnyLayer()
                .whereLayer("Repositories").mayNotAccessAnyLayer();

        architecture.check(importedClasses);

    }
}