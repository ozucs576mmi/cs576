package com.agile.asyoumean.util;

public abstract class AbstractStringSimilarity {

	abstract public double getDistance(String source, String target);

	abstract public double getDistancePercentage(String source, String target);

}
