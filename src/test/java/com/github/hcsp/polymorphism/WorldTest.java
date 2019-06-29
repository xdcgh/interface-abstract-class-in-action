package com.github.hcsp.polymorphism;

import com.github.blindpirate.extensions.CaptureSystemOutput;
import com.github.hcsp.test.helper.ProjectSourceFileReader;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WorldTest {
    @Test
    public void testClassHierarchy() {
        Assertions.assertTrue(
                Stream.of(World.麻雀.class, World.喜鹊.class, World.蝴蝶.class, World.飞机.class)
                        .allMatch(World.会飞的东西.class::isAssignableFrom));
        Assertions.assertTrue(
                Stream.of(
                                World.麻雀.class,
                                World.喜鹊.class,
                                World.蝴蝶.class,
                                World.猫.class,
                                World.狗.class)
                        .allMatch(World.动物.class::isAssignableFrom));
        Assertions.assertTrue(
                Stream.of(
                                World.麻雀.class,
                                World.喜鹊.class,
                                World.救护车.class,
                                World.猫.class,
                                World.狗.class)
                        .allMatch(World.会叫的东西.class::isAssignableFrom));
    }

    @Test
    @CaptureSystemOutput
    public void flyTest(CaptureSystemOutput.OutputCapture capture) {
        capture.expect(
                new BaseMatcher<String>() {
                    @Override
                    public boolean matches(Object actual) {
                        String output = actual.toString();
                        return StringUtils.countMatches(output, "新陈代谢") == 5
                                && StringUtils.countMatches(output, "鸟儿飞") == 2
                                && StringUtils.countMatches(output, "蝴蝶飞") == 1
                                && StringUtils.countMatches(output, "飞机飞") == 1
                                && StringUtils.countMatches(output, "叽叽喳喳") == 2
                                && StringUtils.countMatches(output, "哇呜哇呜") == 1
                                && StringUtils.countMatches(output, "喵喵喵") == 1
                                && StringUtils.countMatches(output, "汪汪汪") == 1;
                    }

                    @Override
                    public void describeTo(Description description) {}
                });
        World.会叫的东西叫();
        World.会飞的东西飞();
        World.动物都能新陈代谢();
    }

    @Test
    public void onlyAFewInstanceof() {
        Assertions.assertTrue(
                StringUtils.countMatches(
                                ProjectSourceFileReader.readAsString(World.class), "instanceof")
                        <= 3);
    }

    @Test
    public void noDuplicateCode() {
        Assertions.assertTrue(
                StringUtils.countMatches(
                                ProjectSourceFileReader.readAsString(World.class), "\"新陈代谢\"")
                        <= 1);
        Assertions.assertTrue(
                StringUtils.countMatches(
                                ProjectSourceFileReader.readAsString(World.class), "\"鸟儿飞\"")
                        <= 1);
        Assertions.assertTrue(
                StringUtils.countMatches(
                                ProjectSourceFileReader.readAsString(World.class), "\"蝴蝶飞\"")
                        <= 1);
        Assertions.assertTrue(
                StringUtils.countMatches(
                                ProjectSourceFileReader.readAsString(World.class), "\"飞机飞\"")
                        <= 1);
    }
}
