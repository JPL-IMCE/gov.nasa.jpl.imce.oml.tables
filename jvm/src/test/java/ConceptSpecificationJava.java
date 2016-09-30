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
import gov.nasa.jpl.imce.omf.schema.tables.ConceptHelper;

public class ConceptSpecificationJava {

    @Test
    public void creationTest() {
        String graphUUID = "01234-abcde-4569-fehi";
        String uuid = "12345-BCDEF-6789A-012345";
        boolean isAbstract=false;
        String name="ElectricCar";
        String resourceIRI = "https://imce.jpl.nasa.gov/project/VehicleExample#ElectricCar";

        Concept w1 = new Concept(graphUUID, uuid, isAbstract, name, resourceIRI);
        Assert.assertEquals(w1.name(), name);
        String s1 = ConceptHelper.toJSON(w1);
        String t1 = String.format(
                "{\"graphUUID\":\"%s\",\"uuid\":\"%s\",\"isAbstract\":%b,\"name\":\"%s\",\"iri\":\"%s\"}",
                graphUUID, uuid, isAbstract,name,resourceIRI);
        Assert.assertEquals(t1, s1);

        Concept r1 = ConceptHelper.fromJSON(s1);
        Assert.assertEquals(w1.graphUUID(), r1.graphUUID());
        Assert.assertEquals(w1.uuid(), r1.uuid());
        Assert.assertEquals(w1.isAbstract(), r1.isAbstract());
        Assert.assertEquals(w1.name(), r1.name());
        Assert.assertEquals(w1.iri(), r1.iri());
        Assert.assertTrue(w1.equals(r1));

    }
}
