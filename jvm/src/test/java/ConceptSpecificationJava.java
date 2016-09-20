/*
 * Copyright 2016 California Institute of Technology ("Caltech").
 * U.S. Government sponsorship acknowledged.
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
 * License Terms
 */
import org.junit.Test;
import org.junit.Assert;

import gov.nasa.jpl.imce.omf.schema.tables.Concept;

public class ConceptSpecificationJava {

    @Test
    public void creationTest() {
        Concept c1 = new Concept(
                "01234-abcde-4569-fehi",
                "12345-BCDEF-6789A-012345",
                false,
                "ElectricCar",
                "https://imce.jpl.nasa.gov/project/VehicleExample#ElectricCar");
        Assert.assertEquals(c1.name(), "ElectricCar");

        Concept c2 = new Concept(
                "01234-abcde-4569-fehi",
                "12345-6789A-BCDEF-012345",
                false,
                "ElectricBicycle",
                "https://imce.jpl.nasa.gov/project/VehicleExample#ElectricBicycle");
        Assert.assertEquals(c2.name(), "ElectricBicycle");
    }
}
