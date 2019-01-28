package com.stalary.personfilter.data.entity.mysql;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stalary.personfilter.data.dto.SkillRule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * @model Recruit
 * @description 招聘信息
 * @field companyId 关联的公司id
 * @field hrId 关联的hrId
 * @field content 招聘内容
 * @field title 招聘标题
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recruit")
@Entity
public class Recruit extends BaseEntity {

    private Long companyId;

    private Long hrId;

    private String content;

    private String title;

    @Transient
    @JsonIgnore
    private List<SkillRule> skillList;

    @JsonIgnore
    private String skillStr;

    @JsonIgnore
    public void serializeFields() {
        this.skillStr = JSONObject.toJSONString(skillList);
    }

    @JsonIgnore
    public void deserializeFields() {
        this.skillList = JSONObject.parseObject(skillStr, new TypeReference<List<SkillRule>>(){});
    }

}