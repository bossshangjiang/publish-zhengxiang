//
//  main.cpp
//  thoughtworks
//
//  Created by cc on 2017/9/11.
//  Copyright © 2017年 cc. All rights reserved.
//

#include <iostream>
#include <unordered_map>
#include <map>
#include <vector>
#include <string>
using namespace std;

map<string ,vector<int>> mapa;//无key则未预定 已预订
map<string ,vector<int>> mapb;
map<string ,vector<int>> mapc;
map<string ,vector<int>> mapd;

std::string strSummaryA;
std::string strSummaryB;
std::string strSummaryC;
std::string strSummaryD;
//ACTIVITY_INFO结构体定义如下：
typedef struct _ACTIVITY_INFO
{
    int userid;
    int nYear;
    int nMonth;
    int nDay;
    int nStartTime;//时间必为整小时，开始时间
    int nEndTime;//结束时间
    char book;//活动人数
    char cancelbook='A';
    std::string strRawTime;//输入时间，格式为：年-月-日
}ACTIVITY_INFO;


typedef struct _TOTAL_MONEY
{
    int totala;
    int totalb;
    int totalc;
    int totald;
    int total;
}TOTAL_MONEY;


//枚举类型DAY_OF_WEEK定义
enum DAY_OF_WEEK
{
    EM_DAY_MON = 1,
    EM_DAY_TUE,
    EM_DAY_WED,
    EM_DAY_THU,
    EM_DAY_FRI,
    EM_DAY_SAT,
    EM_DAY_SUN
};

//周内和周末每个时间段的租金均不同，建立一个哈希Map来存储其映射关系。
//map<int, int>---<时间，价格>
std::unordered_map<int, int> g_setWeekPay = { { 9, 30 }, { 10, 30 }, { 11, 30 }, { 12, 50 },
    { 13, 50 }, { 14, 50 }, { 15, 50 }, { 16, 50 }, { 17, 50 }, { 18, 80 }, { 19, 80 },
    { 20, 60 }, { 21, 60 } };

std::unordered_map<int, int> g_setWeekendPay = { { 9, 40 }, { 10, 40 }, { 11, 40 }, { 12, 50 },
    { 13, 50 }, { 14, 50 }, { 15, 50 }, { 16, 50 }, { 17, 50 }, { 18, 60 },
    { 19, 60 }, { 20, 60 }, { 21, 60 } };

int GetDayOfWeek(int nYear, int nMonth, int nDay);
int GetTotalPay(int nDayOfWeek, int nStartTime, int nEndTime);
bool ParseInputTime(const std::string &strTime, ACTIVITY_INFO &structInfo);
std::string generateEachLineSummary(const std::string &strEachLine);
std::string generateSummary(const std::string &input);
TOTAL_MONEY GetTotalMoney(const std::string &strSummary);

TOTAL_MONEY totalmoney;



int main(int argc, const char * argv[]) {
    
    std::string strInputA("");
    std::string strInputB("");
    std::string strInputC("");
    std::string strInputD("");
    
    std::string consoleInput("");
    
    ACTIVITY_INFO tmActivity;
    while (getline(std::cin, consoleInput))
    {
        if (consoleInput == " ")
            break;
        if (!ParseInputTime(consoleInput, tmActivity)){//判断非法输入
            continue;
        }
        else{
            printf("Success: the booking is accepted!\n");
            switch (tmActivity.book) {
                case 'A':
                    strInputA += consoleInput + "\n";
                    break;
                case 'B':
                    strInputA += consoleInput + "\n";
                    break;
                case 'C':
                    strInputA += consoleInput + "\n";
                    break;
                case 'D':
                    strInputA += consoleInput + "\n";
                    break;
                default:
                    break;
            }
        }
    }
    std::string strSummaryA = generateSummary(strInputA);
    std::string strSummaryB = generateSummary(strInputB);
    std::string strSummaryC = generateSummary(strInputC);
    std::string strSummaryD = generateSummary(strInputD);
    
    printf("收入汇总\n---\n");
    printf("场地：A\n");
    printf("%s", strSummaryA.c_str());
    printf("\n场地：B\n");
    printf("%s", strSummaryB.c_str());
    printf("\n场地：C\n");
    printf("%s", strSummaryC.c_str());
    printf("\n场地：D\n");
    printf("%s", strSummaryD.c_str());
    printf("---\n总计：");
    return 0;
}

//判断是周几
int GetDayOfWeek(int nYear, int nMonth, int nDay)
{
    //2016-06-02 20:00~22:00
    int iWeek = 0;
    unsigned int y = 0, c = 0, m = 0, d = 0;
    
    if (nYear == 1 || nMonth == 2)
    {
        c = (nYear - 1) / 100;
        y = (nYear - 1) % 100;
        m = nMonth + 12;
        d = nDay;
    }
    else
    {
        c = nYear / 100;
        y = nYear % 100;
        m = nMonth;
        d = nDay;
    }
    //蔡勒公式
    iWeek = y + y / 4 + c / 4 - 2 * c + 26 * (m + 1) / 10 + d - 1;
    
    iWeek = iWeek >= 0 ? (iWeek % 7) : (iWeek % 7 + 7);
    if (iWeek == 0)
    {
        iWeek = 7;
    }
    
    return iWeek;
}
int GetCancelfee(int nDayOfWeek, int nStartTime, int nEndTime){
    //时间必然为整小时
    int nTotalPay = 0;
    int nCurrentHour = nStartTime;
    switch (nDayOfWeek)
    {
        case EM_DAY_MON:
        case EM_DAY_TUE:
        case EM_DAY_WED:
        case EM_DAY_THU:
        case EM_DAY_FRI:
            while (nCurrentHour < nEndTime)
            {
                auto iter = g_setWeekPay.find(nCurrentHour);
                if (iter != g_setWeekPay.end())
                    nTotalPay += iter->second;
                nCurrentHour++;
            }
            nTotalPay *=0.5;
            break;
        case EM_DAY_SAT:
        case EM_DAY_SUN:
            while (nCurrentHour < nEndTime)
            {
                auto iter = g_setWeekendPay.find(nCurrentHour);
                if (iter != g_setWeekendPay.end())
                    nTotalPay += iter->second;
                nCurrentHour++;
            }
            nTotalPay *=0.75;

            break;
        default:
            break;
    }
    return nTotalPay;
}

int GetTotalPay(int nDayOfWeek, int nStartTime, int nEndTime)
{
    //时间必然为整小时
    int nTotalPay = 0;
    int nCurrentHour = nStartTime;
    switch (nDayOfWeek)
    {
        case EM_DAY_MON:
        case EM_DAY_TUE:
        case EM_DAY_WED:
        case EM_DAY_THU:
        case EM_DAY_FRI:
            while (nCurrentHour < nEndTime)
            {
                auto iter = g_setWeekPay.find(nCurrentHour);
                if (iter != g_setWeekPay.end())
                    nTotalPay += iter->second;
                nCurrentHour++;
            }
            break;
        case EM_DAY_SAT:
        case EM_DAY_SUN:
            while (nCurrentHour < nEndTime)
            {
                auto iter = g_setWeekendPay.find(nCurrentHour);
                if (iter != g_setWeekendPay.end())
                    nTotalPay += iter->second;
                nCurrentHour++;
            }
            break;
        default:
            break;
    }
    return nTotalPay;
}


//判断输入是否为非法字符，若非法直接返回false。用到了C的格式化输入`sscanf`
bool ParseInputTime(const std::string &strTime, ACTIVITY_INFO &structInfo)
{
    //2016-06-02 20:00~22:00 A C
    //strTime为原始每一行的输入：年-月-日 开始时间~结束时间 预定场地 取消预订
    
    int nRet = sscanf(strTime.c_str(), "U%d %d-%d-%d %d:00~%d:00 %c %c",
                      &structInfo.userid,&structInfo.nYear, &structInfo.nMonth, &structInfo.nDay,
                      &structInfo.nStartTime, &structInfo.nEndTime,&structInfo.book, &structInfo.cancelbook);
    
    if (7 == nRet||6==nRet){
        printf("Error: the booking is invalid!\n");
        return false;

    }
    bool bRet = true;

    if(7==nRet){
    int nIntervalTime = structInfo.nEndTime - structInfo.nStartTime;
    if( nIntervalTime<=0){
        printf("Error: the booking is invalid!\n");
        return false;
    }
    auto iterBlank = strTime.rfind(" ");
    if (iterBlank != std::string::npos)
        structInfo.strRawTime = strTime.substr(0, iterBlank);
    else
        bRet = false;
    int nDayOfWeek;
    int nPay;
    int cancelfee;
   
    switch (structInfo.book) {
        case 'A':
            nDayOfWeek = GetDayOfWeek(structInfo.nYear, structInfo.nMonth, structInfo.nDay);
            nPay = GetTotalPay(nDayOfWeek, structInfo.nStartTime, structInfo.nEndTime);
            if(mapa.find(strTime)!=mapa.end()){
                cancelfee = GetCancelfee(nDayOfWeek, structInfo.nStartTime, structInfo.nEndTime);
                totalmoney.totala -=cancelfee;
                mapa.erase(strTime);
                printf("Success: the booking is accepted!\n");
            }
            else{
                printf("Error: the booking being cancelled does not exist!\n");
            }
            
            break;
        case 'B':
            nDayOfWeek = GetDayOfWeek(structInfo.nYear, structInfo.nMonth, structInfo.nDay);
            nPay = GetTotalPay(nDayOfWeek, structInfo.nStartTime, structInfo.nEndTime);
            if(mapb.find(strTime)!=mapb.end()){
                cancelfee = GetCancelfee(nDayOfWeek, structInfo.nStartTime, structInfo.nEndTime);
                totalmoney.totalb -=cancelfee;
                mapb.erase(strTime);
                printf("Success: the booking is accepted!\n");
            }
            else{
                printf("Error: the booking being cancelled does not exist!\n");
            }
            break;
        case 'C':
            nDayOfWeek = GetDayOfWeek(structInfo.nYear, structInfo.nMonth, structInfo.nDay);
            nPay = GetTotalPay(nDayOfWeek, structInfo.nStartTime, structInfo.nEndTime);
            if(mapc.find(strTime)!=mapc.end()){
                cancelfee = GetCancelfee(nDayOfWeek, structInfo.nStartTime, structInfo.nEndTime);
                totalmoney.totalc -=cancelfee;
                mapc.erase(strTime);
                printf("Success: the booking is accepted!\n");
            }
            else{
                printf("Error: the booking being cancelled does not exist!\n");
            }
            break;
        case 'D':
            nDayOfWeek = GetDayOfWeek(structInfo.nYear, structInfo.nMonth, structInfo.nDay);
            nPay = GetTotalPay(nDayOfWeek, structInfo.nStartTime, structInfo.nEndTime);
            if(mapd.find(strTime)!=mapd.end()){
                cancelfee = GetCancelfee(nDayOfWeek, structInfo.nStartTime, structInfo.nEndTime);
                totalmoney.totald -=cancelfee;
                mapd.erase(strTime);
                printf("Success: the booking is accepted!\n");
            }
            else{
                printf("Error: the booking being cancelled does not exist!\n");
            }
            break;
        default:
            break;
    }
    }
    if(6==nRet){
        int nIntervalTime = structInfo.nEndTime - structInfo.nStartTime;
        //每场预定时间为一小时以上
        if( nIntervalTime<=0){
            printf("Error: the booking is invalid!\n");
            return false;
        }
        int nDayOfWeek;
        int nPay;
        
        structInfo.strRawTime = strTime;
        
        
        switch (structInfo.book) {
            case 'A':
                nDayOfWeek = GetDayOfWeek(structInfo.nYear, structInfo.nMonth, structInfo.nDay);
                nPay = GetTotalPay(nDayOfWeek, structInfo.nStartTime, structInfo.nEndTime);
                if(mapa.find(strTime)!=mapa.end()){//只判断有没有 不判断冲突
                    mapa[strTime]= {};
                    totalmoney.totala +=nPay;
                    printf("Success: the booking is accepted!\n");
                    
                }
                break;
            case 'B':
                nDayOfWeek = GetDayOfWeek(structInfo.nYear, structInfo.nMonth, structInfo.nDay);
                
                nPay = GetTotalPay(nDayOfWeek, structInfo.nStartTime, structInfo.nEndTime);
                if(mapb.find(strTime)!=mapb.end()){//只判断有没有 不判断冲突
                    mapb[strTime]= {};
                    totalmoney.totalb +=nPay;
                    printf("Success: the booking is accepted!\n");
                    
                }
                break;
            case 'C':
                nDayOfWeek = GetDayOfWeek(structInfo.nYear, structInfo.nMonth, structInfo.nDay);
                nPay = GetTotalPay(nDayOfWeek, structInfo.nStartTime, structInfo.nEndTime);
                if(mapc.find(strTime)!=mapc.end()){//只判断有没有 不判断冲突
                    mapc[strTime]= {};
                    totalmoney.totalc +=nPay;
                    printf("Success: the booking is accepted!\n");
                    
                }
                break;
            case 'D':
                nDayOfWeek = GetDayOfWeek(structInfo.nYear, structInfo.nMonth, structInfo.nDay);
                nPay = GetTotalPay(nDayOfWeek, structInfo.nStartTime, structInfo.nEndTime);
                if(mapd.find(strTime)!=mapd.end()){//只判断有没有 不判断冲突
                    mapd[strTime]= {};
                    totalmoney.totald +=nPay;
                    printf("Success: the booking is accepted!\n");
                    
                }
                break;
            default:
                break;
        }
    
    }
    return bRet;
}

std::string generateEachLineSummary(const std::string &strEachLine)
{
    std::string eachLineOutPut("");
    
    
    
    
    return eachLineOutPut;
}

std::string generateSummary(const std::string &input)
{
    std::string strSummary("");
    
    std::vector<std::string> strEachLine;
    auto iter = input.find("\n");
    int nStartPos = 0;
    while (iter != std::string::npos)
    {
        strEachLine.push_back(input.substr(nStartPos, iter - nStartPos));
        nStartPos = iter + 1;
        iter = input.find("\n", iter + 1);
    }
    
    if (!input.substr(nStartPos).empty())
        strEachLine.push_back(input.substr(nStartPos));
    
    for (size_t i = 0; i < strEachLine.size(); i++)
    {
        strSummary += generateEachLineSummary(strEachLine[i]);
        strSummary += "\n";
    }
    
    return strSummary;
}

TOTAL_MONEY GetTotalMoney(const std::string &strSummary)
{
    //2016-06-09 16:00~18:00 +480 -300 +180
    TOTAL_MONEY totalMoney{ 0, 0, 0 };
    auto iter = strSummary.find("\n");
    int nStartPos = 0;
    while (iter != std::string::npos)
    {

    }
    return totalMoney;
}
