package com.hy.test;

import com.opensymphony.xwork2.ActionSupport;

import java.util.List;
import java.util.Map;

public class TestAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    private List<Map<String, String>> list;

    private Integer id;

    private Integer filedIds;

    private String filedCodes;

    private String tableCode;

    public List<Map<String, String>> getList() {
        return list;
    }


    public String queryFiledDate() {
        System.out.println("FiledCodes字段名--------------------" + filedCodes);
        System.out.println("TableCode表名--------------------" + tableCode);
        Demo01 demo01 = new Demo01();

        // 解析成数组

        list = demo01.queryFiledDate(filedCodes, tableCode);
        return "json";
    }

    public String queryFiledByTable() {
        System.out.println("ID--------------------" + id);
        Demo01 demo01 = new Demo01();
        if (id == null) {
            return null;
        }
        list = demo01.showField(id);
        return "json";
    }

    public String test01() {
        Demo01 demo01 = new Demo01();
        list = demo01.showInfo();
        return "json";
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void getId(Integer id) {
        this.id = id;
    }

    public Integer getFiledIds() {
        return filedIds;
    }

    public void setFiledIds(Integer filedIds) {
        this.filedIds = filedIds;
    }

    public void setFiledCodes(String filedCodes) {
        this.filedCodes = filedCodes;
    }

    public String getTableCode() {
        return tableCode;
    }

    public void setTableCode(String tableCode) {
        this.tableCode = tableCode;
    }


}

