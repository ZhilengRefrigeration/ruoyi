package com.ruoyi.system.utils;

import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.system.domain.CompetitionOfTeam;
import com.ruoyi.system.domain.CompetitionTeamVsTeam;
import com.ruoyi.system.domain.vo.BegerArrangementVo;
import com.ruoyi.system.domain.vo.CompetitionOfTeamVo;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 单循环比赛的"贝格尔"编排法 工具类
 */
public class BegerSingleCycleUtil {
    /**
     * 单循环比赛的"贝格尔"编排法
     */
    public void begerSingleCycle1(int team_Num){
       // int team_Num;//队伍的数量
        int team_Arr[];//队伍数组
        int team_temp[];
        boolean empty=false;//是否有轮空
        int jump;//调动幅度
        int round;//比赛轮数
        int flag;//标志，队伍的最大的，或者0，其他队伍在移动的时候，如果碰到他，将跳过
        int tempNum,tempNum1;//队伍在迭代时候保存临时变量的东西
        //--------------------初始化一些数据
    //    Scanner cin = new Scanner(System.in);
        System.out.print("输入队伍的数量: "+team_Num);

     //   team_Num = cin.nextInt();
        if(team_Num%2 != 0)//队伍个数为奇数时
        {
            empty = true;
            team_Num++;
        }
        round = team_Num-1;
        jump = ((team_Num+1)/2)-1;
        team_Arr = new int[team_Num];
        team_temp = new int[team_Num];
        for(int i = 0;i< team_Num;i++){
            team_Arr[i] = i+1;
        }
        if(empty)
        {
            team_Arr[team_Num-1]=0;
        }
        flag = team_Num-1;
        //---------------------开始计算了--------------
        for(int j = 0;j< round;j++)
        {
            System.out.println("第"+(j+1)+"轮:");
            for(int m = 0;m< team_Num/2;m++)
            {
                System.out.println(team_Arr[m]+"----"+team_Arr[team_Num-m-1]);
            }
            for(int g = 0;g< team_Num;g++)
            {
                team_temp[g] = team_Arr[g];
            }
            if(flag != 0 )
            {
                tempNum = team_Arr[flag];//temp 一开始总是记录0队或者最大队伍
                flag = 0;//flag 跳动
                tempNum1 = team_Arr[flag];
                team_Arr[flag] = tempNum;

            }
            else
            {
                tempNum =team_Arr[flag];//temp 一开始总是记录0队或者最大队伍
                tempNum1 = team_Arr[team_Num-1];
                flag = team_Num-1;//flag 跳动
                team_Arr[flag]=	team_temp[flag] = tempNum;
                team_Arr[0]=team_temp[0] = tempNum1;

            }
            for(int k = 0;k< team_Num-1;k++)//走动
            {
                int t = k;

                if(t >= team_Num)
                    t = t - team_Num;
                int z = t;

                for(int u = 0;u< jump;u++)
                {
                    t++;
                    if(t == team_Num)
                        t = t - team_Num;
                    if(t == flag)
                        t++;
                    if(t == team_Num)
                        t = t-team_Num;
                }

                team_Arr[t] = team_temp[z];//
            }
        }
    }
    /**
     * 单循环比赛的"贝格尔"编排法
     */
    public static List<BegerArrangementVo> begerSingleCycle(List<CompetitionOfTeamVo> teamList){

        Date date = new Date();
        //获取批次号
        String batchNumber = new SimpleDateFormat("yyyyMMddHHmmss").format(date);

        //遍历一遍方map 里面
        Map<Integer,CompetitionOfTeam> teamMap = new HashMap();
        Integer numbers = 0;
        for (CompetitionOfTeamVo t:teamList) {
            numbers++;
            //System.out.println(numbers);
            teamMap.put(numbers,t);
        }
        List<BegerArrangementVo> begerArrangementVos = new ArrayList<>();
        // int team_Num;//队伍的数量
        int team_Arr[];//队伍数组
        int team_temp[];
        boolean empty=false;//是否有轮空
        int jump;//调动幅度
        int round;//比赛轮数
        int flag;//标志，队伍的最大的，或者0，其他队伍在移动的时候，如果碰到他，将跳过
        int tempNum,tempNum1;//队伍在迭代时候保存临时变量的东西
        //--------------------初始化一些数据
        //    Scanner cin = new Scanner(System.in);
        // System.out.print("输入队伍的数量: "+teamList.size());


        int team_Num = teamList.size();
        //   team_Num = cin.nextInt();
        if(team_Num %2 != 0)//队伍个数为奇数时
        {
            empty = true;
            team_Num++;
        }
        round = team_Num-1;
        jump = ((team_Num+1)/2)-1;
        team_Arr = new int[team_Num];
        team_temp = new int[team_Num];
        for(int i = 0;i< team_Num;i++){
            team_Arr[i] = i+1;
        }
        if(empty)
        {
            team_Arr[team_Num-1]=0;
        }
        flag = team_Num-1;
        //---------------------开始计算了--------------
        for(int j = 0;j< round;j++)
        {
            BegerArrangementVo vo = new BegerArrangementVo();
            vo.setRound(j+1);
            System.out.println("第"+(j+1)+"轮:");
            List<CompetitionTeamVsTeam> vsTeamList = new ArrayList<>();
            for(int m = 0;m< team_Num/2;m++)
            {
                CompetitionTeamVsTeam vs = new CompetitionTeamVsTeam();
                System.out.println(team_Arr[m]+"----"+team_Arr[team_Num-m-1]);
                int mainIdx = team_Arr[m];
                int guestIdx = team_Arr[team_Num-m-1];
                //todo "0" 表示 轮空；不计入赛程
                if(mainIdx>0&&guestIdx>0) {
                    CompetitionOfTeam mainTeam = teamMap.get(mainIdx);
                    CompetitionOfTeam guestTeam = teamMap.get(guestIdx);
                    if (StringUtils.isEmpty(mainTeam)) {
                        throw new ServiceException("球队分组数据的序号{}有误，匹配失败", mainIdx);
                    }
                    if (StringUtils.isEmpty(guestTeam)) {
                        throw new ServiceException("球队分组数据的序号{}有误，匹配失败", guestIdx);
                    }
                    //设置球队值
                    vs.setMainTeamName(mainTeam.getTeamName());
                    vs.setMainTeamId(mainTeam.getTeamId());

                    vs.setGuestTeamId(guestTeam.getTeamId());
                    vs.setGuestTeamName(guestTeam.getTeamName());

                    vs.setVsType("循环赛");
                    vs.setCompetitionId(mainTeam.getCompetitionId());
                    vs.setCompetitionGroup(mainTeam.getCompetitionGroup());
                    vs.setBatchNumber(batchNumber);
                    vsTeamList.add(vs);
                }
            }
            vo.setVsTeamList(vsTeamList);

            begerArrangementVos.add(vo);

            for(int g = 0;g< team_Num;g++)
            {
                team_temp[g] = team_Arr[g];
            }
            if(flag != 0 )
            {
                tempNum = team_Arr[flag];//temp 一开始总是记录0队或者最大队伍
                flag = 0;//flag 跳动
                tempNum1 = team_Arr[flag];
                team_Arr[flag] = tempNum;

            }
            else
            {
                tempNum =team_Arr[flag];//temp 一开始总是记录0队或者最大队伍
                tempNum1 = team_Arr[team_Num-1];
                flag = team_Num-1;//flag 跳动
                team_Arr[flag]=	team_temp[flag] = tempNum;
                team_Arr[0]=team_temp[0] = tempNum1;

            }
            for(int k = 0;k< team_Num-1;k++)//走动
            {
                int t = k;

                if(t >= team_Num)
                    t = t - team_Num;
                int z = t;

                for(int u = 0;u< jump;u++)
                {
                    t++;
                    if(t == team_Num)
                        t = t - team_Num;
                    if(t == flag)
                        t++;
                    if(t == team_Num)
                        t = t-team_Num;
                }

                team_Arr[t] = team_temp[z];//
            }
        }
        return begerArrangementVos;
    }
    /**
     * 简化后的方法
     * @param team_Num
     */
    public void begerSimplifySingleCycle(int team_Num){
        int n,m;
        System.out.print("输入队伍的数量: "+team_Num);
        n= team_Num;
        if(n%2==0) m=n;
        else m=n+1;
        int a=1,b=1,index=1,loop=0;
        for(int i=1; i<=(m-1)*(m/2); i++)
        {
            if(a>=m) a=1;
            if(index>m/2) index=1;
            if(index==1){
                loop++;
                if(i==1){
                    b=m;
                }else{
                    b=a;
                }
                System.out.println("第"+loop+"轮");;
                if(((i-1)/(m/2))%2==0){
                    System.out.println(a+"--"+m);
                }else{
                    System.out.println(m+"--"+a);
                }
            }else if(index>1 && index<=m/2){
                if(b>1) b--;
                else b=m-1;
                System.out.println(a+"--"+b);
            }
            index++;
            a++;
        }
    }
    public static void main(String args[]){
        BegerSingleCycleUtil util= new BegerSingleCycleUtil();
       // util.begerSingleCycle(4);
       // util.begerSimplifySingleCycle(4);
    }
}
