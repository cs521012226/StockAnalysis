package org.charles.app.enums;

/**
 * 交易类型
 * @author YeChao
 * 2017年6月29日
 */
public enum TradeType {
	BUY("B"),
	SALE("S");
	
	private String name;
	TradeType(String name){
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public boolean eq(String name){
		return this.name.equalsIgnoreCase(name);
	}
	public boolean eq(TradeType type){
		return this.equals(type);
	}
	
}
