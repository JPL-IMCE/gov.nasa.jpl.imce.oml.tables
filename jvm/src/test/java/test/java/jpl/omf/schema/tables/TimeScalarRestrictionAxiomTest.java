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

package test.java.jpl.omf.schema.tables;

import org.junit.Test;
import org.junit.Assert;

import gov.nasa.jpl.imce.omf.schema.tables.TimeScalarRestrictionAxiom;
import gov.nasa.jpl.imce.omf.schema.tables.TimeScalarRestrictionAxiomHelper;

public class TimeScalarRestrictionAxiomTest {

    @Test
    @SuppressWarnings("unchecked")
    public void creationTest() {
        String graphUUID = "01234-abcde-4569-fehi";
        String uuid = "12345-BCDEF-6789A-012345";
        String restrictedScalarUUID = "4567-2345-ABCD-1245";
        String scalarUUID = "1245-ABCD-2345-4567";

        TimeScalarRestrictionAxiom w1 =
                (new TimeScalarRestrictionAxiom(graphUUID, uuid, restrictedScalarUUID, scalarUUID))
                .withMaxExclusive("123")
                .withMinExclusive("4567")
                .withMaxInclusive("333")
                .withMinInclusive("678");

        String s1 = TimeScalarRestrictionAxiomHelper.toJSON(w1);

        String maxE_s = "[\"" + w1.maxExclusive().get() + "\"]";

        String maxI_s = "[\"" + w1.maxInclusive().get() + "\"]";

        String minE_s = "[\"" + w1.minExclusive().get() + "\"]";

        String minI_s = "[\"" + w1.minInclusive().get() + "\"]";

        String t1 = String.format(
                "{\"graphUUID\":\"%s\",\"uuid\":\"%s\",\"maxExclusive\":%s,\"maxInclusive\":%s,\"minExclusive\":%s,\"minInclusive\":%s,\"restrictedScalarUUID\":\"%s\",\"scalarUUID\":\"%s\"}",
                graphUUID, uuid, maxE_s, maxI_s, minE_s, minI_s, restrictedScalarUUID, scalarUUID);
        Assert.assertEquals(t1, s1);


        TimeScalarRestrictionAxiom r1 = TimeScalarRestrictionAxiomHelper.fromJSON(s1);
        Assert.assertEquals(w1.graphUUID(), r1.graphUUID());
        Assert.assertEquals(w1.uuid(), r1.uuid());
        Assert.assertEquals(w1.maxExclusive(), r1.maxExclusive());
        Assert.assertEquals(w1.minInclusive(), r1.minInclusive());
        Assert.assertEquals(w1.restrictedScalarUUID(), r1.restrictedScalarUUID());
        Assert.assertEquals(w1.scalarUUID(), r1.scalarUUID());
        Assert.assertTrue(w1.equals(r1));
    }
}