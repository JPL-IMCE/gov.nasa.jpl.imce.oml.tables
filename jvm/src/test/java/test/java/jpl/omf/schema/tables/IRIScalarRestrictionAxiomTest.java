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

import gov.nasa.jpl.imce.omf.schema.tables.IRIScalarRestrictionAxiom;
import gov.nasa.jpl.imce.omf.schema.tables.IRIScalarRestrictionAxiomHelper;

public class IRIScalarRestrictionAxiomTest {

    @Test
    @SuppressWarnings("unchecked")
    public void creationTest() {
        String graphUUID = "01234-abcde-4569-fehi";
        String uuid = "12345-BCDEF-6789A-012345";
        String restrictedScalarUUID = "4567-2345-ABCD-1245";
        String scalarUUID = "1245-ABCD-2345-4567";

        IRIScalarRestrictionAxiom w1 =
                (new IRIScalarRestrictionAxiom(graphUUID, uuid, restrictedScalarUUID, scalarUUID))
                .withLength(5)
                .withMaxLength(50)
                .withMinLength(1)
                .withPattern("yyy");

        String s1 = IRIScalarRestrictionAxiomHelper.toJSON(w1);

        String ls = "[" + w1.length().get() + "]";

        String maxL = "[" + w1.maxLength().get() + "]";

        String minL = "[" + w1.minLength().get() + "]";

        String ps = "[\"" + w1.pattern().get() + "\"]";

        String t1 = String.format(
                "{\"graphUUID\":\"%s\",\"uuid\":\"%s\",\"length\":%s,\"maxLength\":%s,\"minLength\":%s,\"pattern\":%s,\"restrictedScalarUUID\":\"%s\",\"scalarUUID\":\"%s\"}",
                graphUUID, uuid, ls, maxL, minL, ps, restrictedScalarUUID, scalarUUID);
        Assert.assertEquals(t1, s1);

        IRIScalarRestrictionAxiom r1 = IRIScalarRestrictionAxiomHelper.fromJSON(s1);
        Assert.assertEquals(w1.graphUUID(), r1.graphUUID());
        Assert.assertEquals(w1.uuid(), r1.uuid());
        Assert.assertEquals(w1.length(), r1.length());
        Assert.assertEquals(w1.maxLength(), r1.maxLength());
        Assert.assertEquals(w1.minLength(), r1.minLength());
        Assert.assertEquals(w1.restrictedScalarUUID(), r1.restrictedScalarUUID());
        Assert.assertEquals(w1.scalarUUID(), r1.scalarUUID());
        Assert.assertTrue(w1.equals(r1));
    }
}
