/*참조테이블
DESC t_tableName
이름            널        유형           
------------- -------- ------------ 
TABLENAMECODE NOT NULL NUMBER       
TABLENAME     NOT NULL VARCHAR2(40) 
 */
package com.team01.dto;

public class TableNameDTO {
	private int tableNameCode;
	private String tableName;
	
	public int getTableNameCode() {
		return tableNameCode;
	}
	public void setTableNameCode(int tableNameCode) {
		this.tableNameCode = tableNameCode;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	
}
