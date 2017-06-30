package org.charles.app.enums;

/**
 * 时期
 * @author YeChao
 * 2017年6月28日
 */
public enum Period {
	DAY,
	WEEK,
	MONTH,
	QUARTER,
	YEAR,
	
	DAY_3,
	DAY_5,
	DAY_10,
	DAY_20;
	
	public static Period get(String type){
		for(Period p : Period.values()){
			if(p.name().equalsIgnoreCase(type)){
				return p;
			}
		}
		return null;
	}
}
