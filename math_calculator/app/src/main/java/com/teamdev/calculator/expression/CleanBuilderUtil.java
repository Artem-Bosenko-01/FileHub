package com.teamdev.calculator.expression;

public final class CleanBuilderUtil {

    public static void clean(StringBuilder builder){builder.delete(0, builder.capacity());}
}
