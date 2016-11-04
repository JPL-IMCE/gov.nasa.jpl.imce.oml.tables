package test.java.jpl.omf.schema.tables;/*
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

import gov.nasa.jpl.imce.omf.schema.tables.PlainLiteralScalarRestrictionAxiom;
import gov.nasa.jpl.imce.omf.schema.tables.PlainLiteralScalarRestrictionAxiomJava;
import gov.nasa.jpl.imce.omf.schema.tables.PlainLiteralScalarRestrictionAxiomHelper;

import scala.compat.java8.OptionConverters;

import java.util.Optional;

public class PlainLiteralScalarRestrictionAxiomEmptyTest {

    @Test
    @SuppressWarnings("unchecked")
    public void creationTest() {
        String graphUUID = "01234-abcde-4569-fehi";
        String uuid = "12345-BCDEF-6789A-012345";

        Optional language = Optional.empty();
        Optional length = Optional.empty();
        Optional maxLength = Optional.empty();
        Optional minLength = Optional.empty();
        Optional pattern = Optional.empty();

        String restrictedScalarUUID = "4567-2345-ABCD-1245";
        String scalarUUID = "1245-ABCD-2345-4567";

        PlainLiteralScalarRestrictionAxiom w1 = PlainLiteralScalarRestrictionAxiomJava.javaPlainLiteralScalarRestrictionAxiom(graphUUID, uuid, language, length, maxLength, minLength, pattern, restrictedScalarUUID, scalarUUID);

        Assert.assertEquals(w1.language(), OptionConverters.toScala(language));
        String s1 = PlainLiteralScalarRestrictionAxiomHelper.toJSON(w1);


        // converting None value to an empty array for JSON comparison
        scala.Option lan = OptionConverters.toScala(language);
        String lan_s = (lan.isEmpty()) ? "[]" : lan.toString();

        scala.Option len = OptionConverters.toScala(length);
        String ls = (len.isEmpty()) ? "[]" : len.toString();

        scala.Option maxLen = OptionConverters.toScala(maxLength);
        String maxL = (maxLen.isEmpty()) ? "[]" : maxLen.toString();

        scala.Option minLen = OptionConverters.toScala(minLength);
        String minL = (minLen.isEmpty()) ? "[]" : minLen.toString();

        scala.Option pat = OptionConverters.toScala(pattern);
        String ps = (pat.isEmpty()) ? "[]" : pat.toString();

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
