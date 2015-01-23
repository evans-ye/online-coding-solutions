// This version of WholeWeeks can figure out the Jan. 1st's weekday by a given year, and then calculate whole weeks by given two months

#include <stdio.h>
#include <stdlib.h>
#include <math.h>       /* round, floor, ceil, trunc */

int run(intYears,intMonths) {

    //wholeweeks definition: 0~6 (Sunday to Saturday)
    int base=2000, weekday=6; //2000/1/1 = 6 (Saturday)
    int months[12]= {31,28,31,30,31,30,31,31,30,31,30,31};
    int r_months[12]= {31,29,31,30,31,30,31,31,30,31,30,31};
    int shiftdays = 0;

    if(intMonths<1 || intMonths>12) {
        printf("invalid month!");
        return -1;
    }
    // find the first date of the year and its weekday
    // do not support years before 1500 because 1500 is a leap year, but why?
    // the year before 2000 will goes to this part
    if (intYears<2000 && intYears>1500) {
        for (int i=intYears ; i<2000 ; i++) {
            if (i==1582) {
                shiftdays+=5;
            }
            else if((i%4==0 && i%100!=0)||(i%400==0)) {
                shiftdays+=2;
            }
            else {
                shiftdays+=1;
            }
        }
        weekday-=shiftdays%7;
        if (weekday<0) {weekday+=7;}
        //printf("weekday=%i\n",weekday);
    }

    // the year equal or after 2000 will goes to this part
    else if (intYears >= 2000) {
        shiftdays=0;
        for (int i=2000 ; i<intYears ; i++) {
            if((i%4==0 && i%100!=0)||(i%400==0)) {
                shiftdays+=2;
            }
            else {
                shiftdays+=1;
            }
        }
        weekday=(weekday+shiftdays)%7;
        //printf("weekday=%i\n",weekday);
    }
    else {
        printf("year do not supported\n");
        return -1;
    }

    //find the weekday of the month's first day
    shiftdays=0;
    int leap = 0;
    if((intYears%4==0 && intYears%100!=0)||(intYears%400==0)) {
        leap=1;
    }
    for (int i=1; i<intMonths; i++) {
        if (leap) {
            shiftdays+=r_months[i-1];
        }
        else {
            shiftdays+=months[i-1];
        }
    }
    weekday=(weekday+shiftdays)%7;
    printf("weekday=%i\n",weekday);

    // count wholeweeks for two months.
    // The idea is to subtract the first incomplete week, and then / the number of days by 7
    int availDays=0;
    if (leap) {
        availDays = r_months[intMonths-1]+r_months[intMonths%12]; //get two months' days
    }
    else {
        availDays = months[intMonths-1]+months[intMonths%12];
    }
    if (weekday>0) {
        availDays-=(7-weekday);
    }
    return floor(availDays/7);
}

int main(void)
{
    while(1) {
        int intYears=0;
        int intMonths=0;
        scanf("%d",&intYears);
        scanf("%d",&intMonths);
        int wholeWeeks=run(intYears,intMonths);
        printf("Year=%i, Month=%i, WholeWeeks=%i\n",intYears, intMonths, wholeWeeks);
    }
}
