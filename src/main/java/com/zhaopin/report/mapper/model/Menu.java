package com.zhaopin.report.mapper.model;

public class Menu {
    private Integer menuId;

    private Integer parentCode;//父级菜单id

    private String menuCode;//菜单编码

    private String name;//菜单名称

    private String link;//菜单链接

    private Integer status;//状态1启用，2禁用

    private Integer seqNo;//顺序

    private Boolean shopHold;//门店是否拥有标识 1有，2无

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getParentCode() {
        return parentCode;
    }

    public void setParentCode(Integer parentCode) {
        this.parentCode = parentCode;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode == null ? null : menuCode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public Boolean getShopHold() {
        return shopHold;
    }

    public void setShopHold(Boolean shopHold) {
        this.shopHold = shopHold;
    }
}