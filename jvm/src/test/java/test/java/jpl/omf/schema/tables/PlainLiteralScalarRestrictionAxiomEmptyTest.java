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

import gov.nasa.jpl.imce.omf.schema.tables.PlainLiteralScalarRestrictionAxiom;
import gov.nasa.jpl.imce.omf.schema.tables.PlainLiteralScalarRestrictionAxiomHelper;

public class PlainLiteralScalarRestrictionAxiomEmptyTest {

    @Test
    @SuppressWarnings("unchecked")
    public void creationTest() {
        String graphUUID = "01234-abcde-4569-fehi";
        String uuid = "12345-BCDEF-6789A-012345";

//        Optional language = Optional.empty();
//        Optional length = Optional.empty();
//        Optional maxLength = Optional.empty();
//        Optional minLength = Optional.empty();
//        Optional pattern = Optional.empty();

        String restrictedScalarUUID = "4567-2345-ABCD-1245";
        String scalarUUID = "1245-ABCD-2345-4567";

        PlainLiteralScalarRestrictionAxiom w1 = new PlainLiteralScalarRestrictionAxiom(graphUUID, uuid, restrictedScalarUUID, scalarUUID);

        String s1 = PlainLiteralScalarRestrictionAxiomHelper.toJSON(w1);

        String lan_s = (w1.language().isEmpty()) ? "[]" : w1.language().toString();

        String ls = (w1.length().isEmpty()) ? "[]" : w1.length().toString();

        String maxL = (w1.maxLength().isEmpty()) ? "[]" : w1.maxLength().toString();

        String minL = (w1.minLength().isEmpty()) ? "[]" : w1.minLength().toString();

        String ps = (w1.pattern().isEmpty()) ? "[]" : w1.pattern().toString();


        String t1 = String.format(
                "{\"graphUUID\":\"%s\",\"uuid\":\"%s\",\"language\":%s,\"length\":%s,\"maxLength\":%s,\"minLength\":%s,\"pattern\":%s,\"restrictedScalarUUID\":\"%s\",\"scalarUUID\":\"%s\"}",
                graphUUID, uuid, lan_s, ls, maxL, minL, ps, restrictedScalarUUID, scalarUUID);
        Assert.assertEquals(t1, s1);

        PlainLiteralScalarRestrictionAxiom r1 = PlainLiteralScalarRestrictionAxiomHelper.fromJSON(s1);
        Assert.assertEquals(w1.graphUUID(), r1.graphUUID());
        Assert.assertEquals(w1.uuid(), r1.uuid());
        Assert.assertEquals(w1.length(), r1.length());
        Assert.assertEquals(w1.maxLength(), r1.maxLength());
        Assert.assertEquals(w1.minLength(), r1.minLength());
        Assert.assertEquals(w1.pattern(), r1.pattern());
        Assert.assertEquals(w1.restrictedScalarUUID(), r1.restrictedScalarUUID());
        Assert.assertEquals(w1.scalarUUID(), r1.scalarUUID());
        Assert.assertTrue(w1.equals(r1));
    }
}