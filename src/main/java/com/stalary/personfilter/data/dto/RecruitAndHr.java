package com.stalary.personfilter.data.dto;

import com.stalary.personfilter.data.entity.mysql.Recruit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RecruitAndHRAndCompany
 *
 * @author lirongqian
 * @since 2018/04/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecruitAndHr {

    private Recruit recruit;

    private HR hr;
}