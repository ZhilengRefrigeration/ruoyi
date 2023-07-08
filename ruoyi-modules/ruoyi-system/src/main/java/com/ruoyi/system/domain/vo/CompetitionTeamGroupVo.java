package com.ruoyi.system.domain.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompetitionTeamGroupVo {

    private List<CompetitionOfTeamVo> isNotGroup;

    private List<IsGroupBean> isGroup;
    @Getter
    @Setter
    public static class IsGroupBean {
        /**
         * competitionOfTeamList : [{"createdTime":"2020-10-28 21:27:33","lastUpdatedTime":"2020-11-03 10:09:38","createdBy":"柯里","modifiedBy":"柯里","isDeleted":0,"id":9,"competitionId":26,"teamId":3,"teamName":"爱的表达","competitionGroup":"A","status":1,"remark":"","contacts":"张三","contactsTel":"15202838161","contactsAreaCode":"86","captcha":null,"teamLogo":"https://mall.lzsport.cn/image/2020/10/29/ae3756ab-c721-4277-bf0f-506d83c34818.jpg","teamImg":"https://adu.shjmall.cn/liguanghui/image//2020/10/28/19b257f9-433e-403c-abfd-f6d4fec88953.jpg","userId":null,"teamManagerUserId":6},{"createdTime":"2020-10-28 15:55:24","lastUpdatedTime":"2020-11-03 10:09:32","createdBy":"辉","modifiedBy":"博博","isDeleted":0,"id":5,"competitionId":26,"teamId":6,"teamName":"超燃队","competitionGroup":"A","status":1,"remark":"","contacts":"看看哇","contactsTel":"18202860065","contactsAreaCode":"86","captcha":null,"teamLogo":"https://adu.shjmall.cn/liguanghui/image//2020/10/29/8178f499-3114-4512-af3b-28ec535cee6a.jpg","teamImg":"https://adu.shjmall.cn/liguanghui/image//2020/09/18/ff110b5f-3e4c-4a4f-a3a3-dcfcfbf6560e.jpg","userId":null,"teamManagerUserId":5}]
         * competitionGroup : A
         */
        private String competitionGroup;
        private List<CompetitionOfTeamVo> competitionOfTeamList;

    }
}
