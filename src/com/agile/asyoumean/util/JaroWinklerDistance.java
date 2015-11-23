package com.agile.asyoumean.util;

import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * 
 * The Jaro-Winkler distance metric is designed and best suited for short strings such as person names. The score is normalized such that 0 equates to no similarity and 1 is an exact match.
 * 
 * The Jaro distance metric states that given two strings s1 and s2 , their distance is d':
 * 
 * d' = 1/3*((m/lens1)+(m/lens2)+((m-t)/m))
 * 
 * where:
 * m : is the number of matching characters
 * t : is the number of transpositions
 * 
 * Each character of  is compared with all its matching characters in . The number of matching (but different sequence order) characters divided by two defines the number of transpositions.
 * 
 * Jaro-Winkler distance uses a prefix scale  which gives more favourable ratings to strings that match from the beginning for a set prefix length . Given two strings s1 and s2, 
 * their Jaro-Winkler distance d'' is:
 * 
 * d'' = d' + (l*p*(1-d'))
 * 
 * where:
 * d': is the Jaro distance for strings and
 * l : is the length of common prefix at the start of the string up to a maximum of 4 characters
 * p : is a constant scaling factor for how much the score is adjusted upwards for having common prefixes. The standard value for this constant in Winkler's work is
 * 
 * select utl_match.JARO_WINKLER_SIMILARITY('abba','baab') from dual
 * 
 *
 */
public class JaroWinklerDistance extends AbstractStringSimilarity{
	public static void main(String[] args) {
		JaroWinklerDistance temp = new JaroWinklerDistance();
		System.out.println(temp.getDistancePercentage("ABBA", "BAAB"));
		System.out.println(temp.getDistancePercentage("ABA", "ACA"));
		System.out.println(temp.getDistancePercentage("AB", "B"));
		System.out.println(temp.getDistancePercentage("AB", "BA"));
	
		
		System.out.println(temp.getDistancePercentage("KONTER", "KONTORTRANSFER"));
		System.out.println(temp.getDistancePercentage("ISTIYORUM", "KONTORTRANSFER"));
		System.out.println(temp.getDistancePercentage("KONTERTRANSFER", "TRANSFER"));
		System.out.println(temp.getDistancePercentage("ABCVWXYZ", "CABVWXYZ"));
		
		System.out.println(temp.getDistancePercentage("GELEBILIRIM", "GELEBÝLÝRÝM"));
		System.out.println(temp.getDistancePercentage("BILIYORUM", "GELEBILIRIM"));
		
	}
	
	
	
	public double getDistance(String source,String target) {
		if(source == null || target == null) {
			return 0.0;
		}
		double sourceLength = source.length();
		double targetLength = target.length();
		
        if (sourceLength == 0) {
        	if(targetLength == 0) {
        		return 1.0;
        	}
        	return 0.0;
        }
		
		int 	  searchRange  		 = (int) Math.floor(Math.max(sourceLength, targetLength)/2) - 1;
		double 	  matchCandidate     = 0;
		double 	  transposeCandidate = 0;
        boolean[] sourceMatch 		 = new boolean[(int)sourceLength];
        boolean[] targetMatch 		 = new boolean[(int)targetLength];
        Arrays.fill(sourceMatch,false);
        Arrays.fill(targetMatch,false);

		//step1 : find matching characters
		for(int index=0;index<sourceLength;index++) {
			int start 		   = Math.max(0, index-searchRange);
			int end   		   = Math.min((int)targetLength, index+searchRange+1);
			for(int tempIndex = start;tempIndex<end;tempIndex++) {
				if(source.charAt(index) == target.charAt(tempIndex) && !targetMatch[tempIndex]) {
					matchCandidate++;
					sourceMatch[index] = true;
					targetMatch[tempIndex] = true;
					break;
				}
			}	
			
			/**
			if(!sourceMatch[index]) {
				for(int tempIndex = start;tempIndex<end;tempIndex++) {
					if(source.charAt(index) == target.charAt(tempIndex)) {
						if(index != tempIndex) {
							transposeCandidate++;
							break;
						}
					}
				}					
			}
			**/
		}
		
		if(matchCandidate == 0) {
			return 0.0;
		}
				
		//step2: find transposes
		int tempIndex = 0;
		for(int index=0;index<sourceLength;index++) {
			if(!sourceMatch[index]) {
				continue;
			} else {
		        while (!targetMatch[tempIndex]) {
		        	tempIndex++;
		        }
		        if (source.charAt(index) != target.charAt(tempIndex)) {
		            transposeCandidate++;
		        }
		        tempIndex++;				
			}
	   }

		double	transpose = transposeCandidate/2;

		//step3 : calculate Jaro distance
		double jaroDistance = ((matchCandidate/sourceLength)+(matchCandidate/targetLength)+((matchCandidate-transpose)/matchCandidate))/3;
		//step4 : calculate Jaro-Winkler distance
		int    commonPrefixSearchRange = (int) Math.min(4, Math.min(sourceLength, targetLength)); // prefix range is at most 4
		double scalingFactor 		   = 0.1;
		int    commonPrefix 		   = 0;
		for(;commonPrefix<commonPrefixSearchRange;commonPrefix++) {
			if(source.charAt(commonPrefix) != target.charAt(commonPrefix)) {
				break;
			}
		}
		
		double jaroWinklerDistance = jaroDistance + (commonPrefix*scalingFactor*(1-jaroDistance));

		return jaroWinklerDistance;
	}	
	
	public double getDistancePercentage(String source,String target) {
		return 100 * getDistance(source, target);
	}
}
